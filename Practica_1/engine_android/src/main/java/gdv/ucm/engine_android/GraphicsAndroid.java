package gdv.ucm.engine_android;

import android.view.SurfaceView;

import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Image;

public class GraphicsAndroid implements Graphics {

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
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    SurfaceView _surfaceView;
}
