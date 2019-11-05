package gdv.ucm.utilities;


import java.io.IOException;

import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Image;

public class Transform  implements Graphics{


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

    public Transform(Graphics graphics, int width, int height){
        _graphics = graphics;
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


    @Override
    public Image newImage(String name) throws IOException {
        return _graphics.newImage(name);
    }

    @Override
    public void clear(int color) {
        _graphics.clear(color);
    }

    public void drawImage(Image image, int x, int y) {
        int newX = (x*_paintSpace._width)/_gameResolutionX;
        int newY = (y*_paintSpace._height)/_gameResolutionY;


        _graphics.drawImage(image,newX, newY);
    }


    public void drawRectToRect(Image image,Rectangle rectOrigin, Rectangle rectGoal){
        int newX = (rectGoal._x*_paintSpace._width)/_gameResolutionX;
        int newY = (rectGoal._y*_paintSpace._height)/_gameResolutionY;
        int newW = (rectGoal._width*_paintSpace._width)/_gameResolutionX;
        int newH = (rectGoal._height*_paintSpace._height)/_gameResolutionY;
        newX += _paintSpace._x;
        newY += _paintSpace._y;

        Rectangle rectGoalScalated = new Rectangle(newX, newY, newW,newH);

        _graphics.drawRectToRect(image,rectOrigin,rectGoalScalated);

    }

    @Override
    public void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, int alpha) {
        int newX = (rectGoal._x*_paintSpace._width)/_gameResolutionX;
        int newY = (rectGoal._y*_paintSpace._height)/_gameResolutionY;
        int newW = (rectGoal._width*_paintSpace._width)/_gameResolutionX;
        int newH = (rectGoal._height*_paintSpace._height)/_gameResolutionY;
        newX += _paintSpace._x;
        newY += _paintSpace._y;
        Rectangle rectGoalScalated = new Rectangle(newX, newY, newW,newH);

        _graphics.drawRectToRect(image,rectOrigin,rectGoalScalated,alpha);
    }

    @Override
    public int getWidth() {
        return _graphics.getWidth();
    }

    @Override
    public int getHeight() {
        return _graphics.getHeight();
    }

    private int _gameResolutionX = 1080;
    private int _gameResolutionY = 1920;
    private int _aspectRatioOriginalX = 9;
    private int _aspectRatioOriginalY = 16;
    private Rectangle _paintSpace;
    private Graphics _graphics;
}
