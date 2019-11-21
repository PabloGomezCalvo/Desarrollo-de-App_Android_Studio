package gdv.ucm.utilities;

import java.io.IOException;

import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Image;

public abstract class AbstractGraphics implements Graphics {

    protected abstract Image newPrivateImage(String name) throws IOException;
    protected abstract void privateClear(int color);
    protected abstract void drawPrivateImage(Image image, int x, int y);//shit to finish
    protected abstract void drawPrivateRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal);
    protected abstract void drawPrivateRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, int color);
    protected abstract int getPrivateColorSprite(Image image, int x, int y, int w, int h);
    protected abstract void drawPrivateRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, float alpha);


    public AbstractGraphics(int width, int height){
        _paintSpace = new Rectangle(0,0,0,0);

        _paintSpace._width = (width/_aspectRatioOriginalX);
        _paintSpace._height = (height/_aspectRatioOriginalY);

        if(_paintSpace._width > _paintSpace._height) {
            _paintSpace._width = _paintSpace._height * _aspectRatioOriginalX;
            _paintSpace._height = _paintSpace._height * _aspectRatioOriginalY;
        }
        else{
            _paintSpace._height = _paintSpace._width * _aspectRatioOriginalY;
            _paintSpace._width = _paintSpace._width * _aspectRatioOriginalX;
        }
        if(_paintSpace._width != width)
            _paintSpace._x = (width - _paintSpace._width)/2;
        else
            _paintSpace._x = 0;
        if(_paintSpace._height != height)
            _paintSpace._y = (height - _paintSpace._height)/2;
        else
            _paintSpace._y = 0;
    }


    public void changeResolutionRatio(int width, int height){

        _paintSpace._width = (width/_aspectRatioOriginalX);
        _paintSpace._height = (height/_aspectRatioOriginalY);

        if(_paintSpace._width > _paintSpace._height) {
            _paintSpace._width = _paintSpace._height * _aspectRatioOriginalX;
            _paintSpace._height = _paintSpace._height * _aspectRatioOriginalY;
        }
        else{
            _paintSpace._height = _paintSpace._width * _aspectRatioOriginalY;
            _paintSpace._width = _paintSpace._width * _aspectRatioOriginalX;
        }
        if(_paintSpace._width != width)
            _paintSpace._x = (width - _paintSpace._width)/2;
        else
            _paintSpace._x = 0;
        if(_paintSpace._height != height)
            _paintSpace._y = (height - _paintSpace._height)/2;
        else
            _paintSpace._y = 0;
    }

    @Override
    public Image newImage(String name) throws IOException {
        return newPrivateImage(name);
    }

    @Override
    public void clear(int color) {
        privateClear(color);
    }

    public void drawImage(Image image, int x, int y) {
        int newX = (x*_paintSpace._width)/_gameResolutionX;
        int newY = (y*_paintSpace._height)/_gameResolutionY;


        drawPrivateImage(image,newX,newY);
    }


    public void drawRectToRect(Image image,Rectangle rectOrigin, Rectangle rectGoal){
        int newX = (rectGoal._x*_paintSpace._width)/_gameResolutionX;
        int newY = (rectGoal._y*_paintSpace._height)/_gameResolutionY;
        int newW = (rectGoal._width*_paintSpace._width)/_gameResolutionX;
        int newH = (rectGoal._height*_paintSpace._height)/_gameResolutionY;
        newX += _paintSpace._x;
        newY += _paintSpace._y;

        Rectangle rectGoalScalated = new Rectangle(newX, newY, newW,newH);

        drawPrivateRectToRect(image,rectOrigin,rectGoalScalated);
    }

    @Override
    public void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, int color) {
        int newX = (rectGoal._x*_paintSpace._width)/_gameResolutionX;
        int newY = (rectGoal._y*_paintSpace._height)/_gameResolutionY;
        int newW = (rectGoal._width*_paintSpace._width)/_gameResolutionX;
        int newH = (rectGoal._height*_paintSpace._height)/_gameResolutionY;
        newX += _paintSpace._x;
        newY += _paintSpace._y;
        Rectangle rectGoalScalated = new Rectangle(newX, newY, newW,newH);

        drawPrivateRectToRect(image,rectOrigin,rectGoalScalated,color);
    }

    @Override
    public void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, float alpha){
        int newX = (rectGoal._x*_paintSpace._width)/_gameResolutionX;
        int newY = (rectGoal._y*_paintSpace._height)/_gameResolutionY;
        int newW = (rectGoal._width*_paintSpace._width)/_gameResolutionX;
        int newH = (rectGoal._height*_paintSpace._height)/_gameResolutionY;
        newX += _paintSpace._x;
        newY += _paintSpace._y;
        Rectangle rectGoalScalated = new Rectangle(newX, newY, newW,newH);

        drawPrivateRectToRect(image,rectOrigin,rectGoalScalated,alpha);
    }

    @Override
    public int getColorSprite(Image image, int x, int y, int w, int h) {
        return getPrivateColorSprite(image,x,y,w,h);
    }
    public int changeToGamelCoordenatesX(int x){
        if(x <= _paintSpace._width + _paintSpace._x && x >= _paintSpace._x) {
            if (_paintSpace._x != 0)
                return (x-_paintSpace._x) * _gameResolutionX / _paintSpace._width;
            else
                return x * _gameResolutionX / _paintSpace._width;
        }
        return -1;
    }
    public int changeToGamelCoordenatesY(int y){
        if(y <= _paintSpace._height + _paintSpace._y && y >= _paintSpace._y) {
            if (_paintSpace._y != 0)
                return (y-_paintSpace._y) * _gameResolutionY / _paintSpace._height;
            else
                return y * _gameResolutionY / _paintSpace._height;
        }
        return -1;
    }
    private int _gameResolutionX = 1080;
    private int _gameResolutionY = 1920;
    private int _aspectRatioOriginalX = 9;
    private int _aspectRatioOriginalY = 16;
    private Rectangle _paintSpace;
}
