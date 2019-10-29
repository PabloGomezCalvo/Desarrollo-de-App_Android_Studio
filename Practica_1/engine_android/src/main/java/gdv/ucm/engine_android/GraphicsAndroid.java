package gdv.ucm.engine_android;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;

import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Image;

public class GraphicsAndroid implements Graphics {
    public GraphicsAndroid(int width, int height, AssetManager assetManager){
        _width = width;
        _height = height;
        _assetManager = assetManager;
    }
    @Override
    public Image newImage(String name) {
        InputStream inputStream = null;
        Bitmap _sprite = null;
        try{
            inputStream = _assetManager.open(name);
            _sprite = BitmapFactory.decodeStream(inputStream);
        }
        catch (IOException io){
            android.util.Log.e("MainActivity", "Error al cagar imagen");
        }
        finally {
            try {
                inputStream.close();
            }catch (IOException io){}
        }
        if(_sprite != null)
            return new ImageAndroid(_sprite);
        else
            return null;
    }

    public void setNewCanvas(Canvas c){
        _canvas = c;
    }

    @Override
    public void clear(int color) {

        _canvas.drawColor(color); // ARGB

    }

    @Override
    public void drawImage(Image image, int x, int y) {
        _canvas.drawBitmap(((ImageAndroid)image).getBitmap(),x,y,null);
    }

    @Override
    public int getWidth() {
        return _width;
    }

    @Override
    public int getHeight() {
        return _height;
    }

    private AssetManager _assetManager;
    private int _width;
    private int _height;
    private Canvas _canvas;
}
