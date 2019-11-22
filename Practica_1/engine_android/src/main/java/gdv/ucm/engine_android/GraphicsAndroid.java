package gdv.ucm.engine_android;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import java.io.IOException;
import java.io.InputStream;
import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Image;
import gdv.ucm.utilities.AbstractGraphics;
import gdv.ucm.utilities.Rectangle;

public class GraphicsAndroid extends AbstractGraphics {
    public GraphicsAndroid(int width, int height, AssetManager assetManager){
        super(width, height);
        _width = width;
        _height = height;
        _assetManager = assetManager;
    }
    @Override
    public Image newPrivateImage(String name) {
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
    public void privateClear(int color) {
        color = color | 0xFF000000;
        _canvas.drawColor(color); // ARGB
    }

    @Override
    public void drawPrivateImage(Image image, int x, int y) {
        _canvas.drawBitmap(((ImageAndroid)image).getBitmap(),x,y,null);
    }

    @Override
    public void drawPrivateRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal){
        Rect rectOri = new Rect(rectOrigin._x,rectOrigin._y,
                rectOrigin._x + rectOrigin._width, rectOrigin._y + rectOrigin._height);
        Rect rectGo = new Rect(rectGoal._x,rectGoal._y,
                rectGoal._x + rectGoal._width, rectGoal._y + rectGoal._height);
        _canvas.drawBitmap(((ImageAndroid)image).getBitmap(),rectOri,rectGo, null);
    }

    @Override
    public void drawPrivateRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, int color) {
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color,0);
        p.setColorFilter(filter);


        Rect rectOri = new Rect(rectOrigin._x,rectOrigin._y,
                rectOrigin._x + rectOrigin._width, rectOrigin._y + rectOrigin._height);
        Rect rectGo = new Rect(rectGoal._x,rectGoal._y,
                rectGoal._x + rectGoal._width, rectGoal._y + rectGoal._height);
        _canvas.drawBitmap(((ImageAndroid)image).getBitmap(),rectOri,rectGo, p);
    }

    @Override
    protected void drawPrivateRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, float alpha) {
        Paint p = new Paint();
        p.setAlpha((int)(255*alpha));
        Rect rectOri = new Rect(rectOrigin._x,rectOrigin._y,
                rectOrigin._x + rectOrigin._width, rectOrigin._y + rectOrigin._height);
        Rect rectGo = new Rect(rectGoal._x,rectGoal._y,
                rectGoal._x + rectGoal._width, rectGoal._y + rectGoal._height);
        _canvas.drawBitmap(((ImageAndroid)image).getBitmap(),rectOri,rectGo, p);

    }
    @Override
    public int getPrivateColorSprite(Image image, int x, int y, int w, int h) {
        int color = ((ImageAndroid)image).getBitmap().getPixel(x, y);
        return color;
    }

    @Override
    public int getWidth() {
        return _width;
    }

    @Override
    public int getHeight() {
        return _height;
    }

    public void setResolution(int w, int h){
        _width = w;
        _height = h;
    }

    private AssetManager _assetManager;
    private int _width;
    private int _height;
    private Canvas _canvas;
}
