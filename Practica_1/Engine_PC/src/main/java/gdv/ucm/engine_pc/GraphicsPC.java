package gdv.ucm.engine_pc;

import javax.swing.JFrame;

import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Image;


public class GraphicsPC implements Graphics{

    @Override
    public Image newImage(String name) {

        return null;
    }

    @Override
    public void clear(int color) {

    }

    @Override
    public void drawImage(Image image, int x, int y) {

    }

    @Override
    public int getWidth() {
        return _ventana.getWidth();
    }

    @Override
    public int getHeight() {
        return _ventana.getHeight();
    }

    JFrame _ventana;

}
