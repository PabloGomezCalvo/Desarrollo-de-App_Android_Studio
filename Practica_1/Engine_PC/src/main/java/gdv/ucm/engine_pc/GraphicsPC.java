/**
    Clase que implementa la sobre carga de los métodos de AbstactGraphics.
*/
package gdv.ucm.engine_pc;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.PixelGrabber;
import java.io.IOException;

import javax.swing.JFrame;

import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Image;
import gdv.ucm.utilities.AbstractGraphics;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;


public class GraphicsPC extends AbstractGraphics {

    public GraphicsPC(int width, int height){
        super(width, height);
        _width = width;
        _height = height;
        pathToAssets = "assets/";
    }
/**
    Crea una Imagen que dependerá de la plataforma.
    @param name Ruta del recurso.
*/
    @Override
    public Image newPrivateImage(String name){
        try {
            java.awt.Image imgLoaded = javax.imageio.ImageIO.read(new java.io.File(pathToAssets + name));
            return new ImagePC(imgLoaded);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
/**
    Guarda el valor del Graphics de AWT.
    @param g Referencia a la implementación de AWT de Graphics.
*/
    public void setNewGraphics(java.awt.Graphics g){
        _graphics = g;
    }
/**
    Borrado de la pantalla.
    @param color Color con el cual se hace el borrado.
*/
    @Override
    public void privateClear(int color) {
        Color c = new Color(color);
        _graphics.setColor(c);
        _graphics.fillRect(0,0,_width,_height);
    }
/**
    Dibujado de imagenes.
    @param image Imagen a dibujar.
    @param x Posición X donde pintar.
    @param y Posición Y donde pintar.
*/   
    @Override
    public void drawPrivateImage(Image image, int x, int y) {
        _graphics.drawImage(((ImagePC)image).getAWTImage(),x ,y, null);
    }
/**
    Dibujado de imagenes.
    @param image Imagen a dibujar.
    @param rectOrigin Rectángulo del que partimos
    @param rectGoal Rectángulo donde queremos pintar.
*/  
    @Override
    public void drawPrivateRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal) {
        _graphics.drawImage(((ImagePC)image).getAWTImage(),
                rectGoal._x,rectGoal._y,rectGoal._x + rectGoal._width,rectGoal._y + rectGoal._height,
                rectOrigin._x,rectOrigin._y,rectOrigin._x + rectOrigin._width,
                rectOrigin._y + rectOrigin._height,
                null);
    }
/**
    Dibujado de imagenes.
    @param image Imagen a dibujar.
    @param rectOrigin Rectángulo del que partimos
    @param rectGoal Rectángulo donde queremos pintar.
    @param color Color con el que pintar la imagen
*/ 
    @Override
    public void drawPrivateRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, int color) {
       Color c = new Color(color);
       _graphics.drawImage(((ImagePC)image).getAWTImage(),
                rectGoal._x,rectGoal._y,rectGoal._x + rectGoal._width,rectGoal._y + rectGoal._height,
                rectOrigin._x,rectOrigin._y,rectOrigin._x + rectOrigin._width,
                rectOrigin._y + rectOrigin._height,c,
                null);
    }
/**
    Dibujado de imagenes.
    @param image Imagen a dibujar.
    @param rectOrigin Rectángulo del que partimos
    @param rectGoal Rectángulo donde queremos pintar.
    @param alpha Transparencia con la que pintar la imagen
*/ 
    @Override
    protected void drawPrivateRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, float alpha) {
        Graphics2D graphics2D = (Graphics2D) _graphics;
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));

        graphics2D.drawImage(((ImagePC)image).getAWTImage(),
                rectGoal._x,rectGoal._y,rectGoal._x + rectGoal._width,rectGoal._y + rectGoal._height,
                rectOrigin._x,rectOrigin._y,rectOrigin._x + rectOrigin._width,
                rectOrigin._y + rectOrigin._height, null);
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
    public int getPrivateColorSprite(Image image, int x, int y, int w, int h) {
        int pixels [] = new int[w*h];
        PixelGrabber pg =
                new PixelGrabber(((ImagePC)image).getAWTImage(),x,  y, w,  h,
                        pixels, 0, w);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pixels[0];
    }



/**
    @return Ancho del Graphics
*/
    @Override
    public int getWidth() {
        return  _width;
    }
/**
    @return Alto del Graphics
*/
    @Override
    public int getHeight() {
        return _height;
    }
/**
    Cambia la resolución a la pasada por parámetro.
    @param w Ancho de la nueva resolución.
    @param h Alto de la nueva resolución.
*/
    public void setResolution(int w, int h){
        _width = w;
        _height = h;
    }

    private String pathToAssets;
    private java.awt.Graphics _graphics;
    private int _height;
    private int _width;


}
