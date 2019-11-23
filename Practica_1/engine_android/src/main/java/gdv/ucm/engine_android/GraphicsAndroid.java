/**
    Clase que implementa la sobre carga de los métodos de AbstactGraphics.
*/
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
/**
    Crea una Imagen que dependerá de la plataforma.
    @param name Ruta del recurso.
*/
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
/**
    Setea el canvas dado.
    @param c Canvas a setear.
*/
    public void setNewCanvas(Canvas c){
        _canvas = c;
    }
/**
    Borrado de la pantalla.
    @param color Color con el cual se hace el borrado.
*/
    @Override
    public void privateClear(int color) {
        color = color | 0xFF000000;
        _canvas.drawColor(color); // ARGB
    }
/**
    Dibujado de imagenes.
    @param image Imagen a dibujar.
    @param x Posición X donde pintar.
    @param y Posición Y donde pintar.
*/ 
    @Override
    public void drawPrivateImage(Image image, int x, int y) {
        _canvas.drawBitmap(((ImageAndroid)image).getBitmap(),x,y,null);
    }
/**
    Dibujado de imagenes.
    @param image Imagen a dibujar.
    @param rectOrigin Rectángulo del que partimos
    @param rectGoal Rectángulo donde queremos pintar.
*/ 
    @Override
    public void drawPrivateRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal){
        Rect rectOri = new Rect(rectOrigin._x,rectOrigin._y,
                rectOrigin._x + rectOrigin._width, rectOrigin._y + rectOrigin._height);
        Rect rectGo = new Rect(rectGoal._x,rectGoal._y,
                rectGoal._x + rectGoal._width, rectGoal._y + rectGoal._height);
        _canvas.drawBitmap(((ImageAndroid)image).getBitmap(),rectOri,rectGo, null);
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
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color,0);
        p.setColorFilter(filter);


        Rect rectOri = new Rect(rectOrigin._x,rectOrigin._y,
                rectOrigin._x + rectOrigin._width, rectOrigin._y + rectOrigin._height);
        Rect rectGo = new Rect(rectGoal._x,rectGoal._y,
                rectGoal._x + rectGoal._width, rectGoal._y + rectGoal._height);
        _canvas.drawBitmap(((ImageAndroid)image).getBitmap(),rectOri,rectGo, p);
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
        Paint p = new Paint();
        p.setAlpha((int)(255*alpha));
        Rect rectOri = new Rect(rectOrigin._x,rectOrigin._y,
                rectOrigin._x + rectOrigin._width, rectOrigin._y + rectOrigin._height);
        Rect rectGo = new Rect(rectGoal._x,rectGoal._y,
                rectGoal._x + rectGoal._width, rectGoal._y + rectGoal._height);
        _canvas.drawBitmap(((ImageAndroid)image).getBitmap(),rectOri,rectGo, p);
/**
    Devuelve el color de la imagen dada.
    @param image Imagen para sacar el color.
    @param x coordenada x de la esquina superior izquierda del rectángulo del que sacar los píxeles de la imagen.
    @param y coordenada y de la esquina superior izquierda del rectángulo del que sacar los píxeles de la imagen.
    @param w ancho del rectangulo de donde sacar los pixeles
    @param h alto del rectangulo de donde sacar los pixeles
    @return Color
*/ 
    }
    @Override
    public int getPrivateColorSprite(Image image, int x, int y, int w, int h) {
        int color = ((ImageAndroid)image).getBitmap().getPixel(x, y);
        return color;
    }
/**
    @return Ancho del Graphics
*/
    @Override
    public int getWidth() {
        return _width;
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

    private AssetManager _assetManager;
    private int _width;
    private int _height;
    private Canvas _canvas;
}
