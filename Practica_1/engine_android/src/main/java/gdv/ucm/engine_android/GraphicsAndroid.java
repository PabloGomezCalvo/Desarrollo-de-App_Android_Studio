package gdv.ucm.engine_android;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Image;

public class GraphicsAndroid extends SurfaceView implements Graphics {

    public GraphicsAndroid(Context context) {
        super(context);
    }

    @Override
    public Image newImage(String name) {
        return null;
    }

    @Override
    public void clear(int color) {

    }

    @Override
    public void drawImage(Image image) {

    }

    SurfaceHolder holder;
}
