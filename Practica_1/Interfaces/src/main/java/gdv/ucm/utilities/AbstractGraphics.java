/**
    Clase abstracta que implementa la interfaz Graphics.
    Implementa los escalados dependiendo de la resolución de la ventana.
*/
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

/**
    Escala la pantalla a la resolución óptima.
*/
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
/**
    Crea una imagen.
    @param name Ruta del recurso.
*/
    @Override
    public Image newImage(String name) throws IOException {
        return newPrivateImage(name);
    }
/**
    Borrado de la pantalla.
    @param color Color con el que se borra la pantalla.
*/
    @Override
    public void clear(int color) {
        privateClear(color);
    }
/**
    Dibuja la imagen dada con la nueva X e Y tras ser escalada.
    @param image Imagen a dibujar
    @param x Posición X donde dibujar la imagen
    @param Y Posición Y donde dibujar la imagen.
*/
    public void drawImage(Image image, int x, int y) {
        int newX = (x*_paintSpace._width)/_gameResolutionX;
        int newY = (y*_paintSpace._height)/_gameResolutionY;


        drawPrivateImage(image,newX,newY);
    }

/**
    Dibuja la imagen dada en un rectángulo inicial a otro dado.
    @param image Imagen a dibujar
    @param rectOrigin Rectángulo origen de la imagen.
    @param rectGoal Rectángulo destino donde dibujar la imagen.
*/
    public void drawRectToRect(Image image,Rectangle rectOrigin, Rectangle rectGoal){
        Rectangle rectGoalScalated = tranformCoordenates(rectGoal);
        drawPrivateRectToRect(image,rectOrigin,rectGoalScalated);
    }
/**
    Dibuja la imagen dada en un rectángulo inicial a otro dado.
    @param image Imagen a dibujar
    @param rectOrigin Rectángulo origen de la imagen.
    @param rectGoal Rectángulo destino donde dibujar la imagen.
    @param color Color con el que pintar la imagen.
*/
    @Override
    public void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, int color) {
        Rectangle rectGoalScalated = tranformCoordenates(rectGoal);
        drawPrivateRectToRect(image,rectOrigin,rectGoalScalated,color);
    }
/**
    Dibuja la imagen dada en un rectángulo inicial a otro dado.
    @param image Imagen a dibujar
    @param rectOrigin Rectángulo origen de la imagen.
    @param rectGoal Rectángulo destino donde dibujar la imagen.
    @param alpha Transparencia de la imagen.
*/
    @Override
    public void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, float alpha){
        Rectangle rectGoalScalated = tranformCoordenates(rectGoal);
        drawPrivateRectToRect(image,rectOrigin,rectGoalScalated,alpha);
    }
/**
    Devuelve el color de la imagen dada.
    @param image Imagen para sacar el color.
    @param x coordenada x de la esquina superior izquierda del rectángulo del que sacar los píxeles de la imagen.
    @param y coordenada y de la esquina superior izquierda del rectángulo del que sacar los píxeles de la imagen.
    @param w ancho del rectangulo de donde sacar los pixeles
    @param h alto del rectangulo de donde sacar los pixeles
    @return Color
*/ 
    @Override
    public int getColorSprite(Image image, int x, int y, int w, int h) {
        return getPrivateColorSprite(image,x,y,w,h);
    }
/**
    Cambia la x dada a coordenadas de juego.
    @param x Coorderanda x a cambiar.
    @return Coordenada X escalada.
*/ 
    public int changeToGamelCoordenatesX(int x){
        if(x <= _paintSpace._width + _paintSpace._x && x >= _paintSpace._x) {
            if (_paintSpace._x != 0)
                return (x-_paintSpace._x) * _gameResolutionX / _paintSpace._width;
            else
                return x * _gameResolutionX / _paintSpace._width;
        }
        return -1;
    }
/**
    Cambia la y dada a coordenadas de juego.
    @param y Coorderanda y a cambiar.
    @return Coordenada Y escalada.
*/ 
    public int changeToGamelCoordenatesY(int y){
        if(y <= _paintSpace._height + _paintSpace._y && y >= _paintSpace._y) {
            if (_paintSpace._y != 0)
                return (y-_paintSpace._y) * _gameResolutionY / _paintSpace._height;
            else
                return y * _gameResolutionY / _paintSpace._height;
        }
        return -1;
    }
/**
    Escala las coordenadas de un rectángulo dado.
    @param rectGoal Rectángulo a escalar.
    @return Rectángulo escalado
*/ 
    private Rectangle tranformCoordenates(Rectangle rectGoal){
        int newX = (rectGoal._x*_paintSpace._width)/_gameResolutionX;
        int newY = (rectGoal._y*_paintSpace._height)/_gameResolutionY;
        int newW = (rectGoal._width*_paintSpace._width)/_gameResolutionX;
        int newH = (rectGoal._height*_paintSpace._height)/_gameResolutionY;
        newX += _paintSpace._x;
        newY += _paintSpace._y;
        return new Rectangle(newX, newY, newW,newH);
    }

    private int _gameResolutionX = 1080;
    private int _gameResolutionY = 1920;
    private int _aspectRatioOriginalX = 9;
    private int _aspectRatioOriginalY = 16;
    private Rectangle _paintSpace;
}
