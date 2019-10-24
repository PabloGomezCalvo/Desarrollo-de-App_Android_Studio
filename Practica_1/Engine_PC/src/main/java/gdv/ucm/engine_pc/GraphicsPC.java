package gdv.ucm.engine_pc;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Image;


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
        _graphics.clearRect(0,0,_width,_height);
    }

    @Override
    public void drawImage(Image image, int x, int y) {
        _graphics.drawImage(((ImagePC)image).getAWTImage(),x ,y, null);
    }

    @Override
    public int getWidth() {
        return  _width;
    }

    @Override
    public int getHeight() {
        return _height;
    }
    private String pathToAssets;
    private java.awt.Graphics _graphics;
    private int _height;
    private int _width;
}
