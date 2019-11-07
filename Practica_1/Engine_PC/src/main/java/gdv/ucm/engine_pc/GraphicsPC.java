package gdv.ucm.engine_pc;

import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.io.IOException;

import javax.swing.JFrame;

import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Image;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;


public class GraphicsPC implements Graphics{

    public GraphicsPC(int width, int height){
        _width = width;
        _height = height;
        pathToAssets = "assets/";
    }

    @Override
    public Image newImage(String name){
        try {
            java.awt.Image imgLoaded = javax.imageio.ImageIO.read(new java.io.File(pathToAssets + name));
            return new ImagePC(imgLoaded);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setNewGraphics(java.awt.Graphics g){
        _graphics = g;
    }

    @Override
    public void clear(int color) {
        Color c = new Color(color);
        _graphics.setColor(c);
        _graphics.fillRect(0,0,_width,_height);
    }

    @Override
    public void drawImage(Image image, int x, int y) {
        _graphics.drawImage(((ImagePC)image).getAWTImage(),x ,y, null);
    }

    @Override
    public void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal) {
        _graphics.drawImage(((ImagePC)image).getAWTImage(),
                rectGoal._x,rectGoal._y,rectGoal._x + rectGoal._width,rectGoal._y + rectGoal._height,
                rectOrigin._x,rectOrigin._y,rectOrigin._x + rectOrigin._width,
                rectOrigin._y + rectOrigin._height,
                null);
    }

   /* @Override
    public void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, int alpha) {
        _graphics.drawImage(((ImagePC)image).getAWTImage(),
                rectGoal._x,rectGoal._y,rectGoal._x + rectGoal._width,rectGoal._y + rectGoal._height,
                rectOrigin._x,rectOrigin._y,rectOrigin._x + rectOrigin._width,
                rectOrigin._y + rectOrigin._height,
                null);    }
*/
    @Override
    public void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, int color) {
       Color c = new Color(color);
       _graphics.drawImage(((ImagePC)image).getAWTImage(),
                rectGoal._x,rectGoal._y,rectGoal._x + rectGoal._width,rectGoal._y + rectGoal._height,
                rectOrigin._x,rectOrigin._y,rectOrigin._x + rectOrigin._width,
                rectOrigin._y + rectOrigin._height,c,
                null);
    }

    @Override
    public int getColorSprite(Image image, int x, int y, int w, int h) {
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

    @Override
    public int getWidth() {
        return  _width;
    }

    @Override
    public int getHeight() {
        return _height;
    }

    public void setResolution(int w, int h){
        _width = w;
        _height = h;
    }
    private String pathToAssets;
    private java.awt.Graphics _graphics;
    private int _height;
    private int _width;
}
