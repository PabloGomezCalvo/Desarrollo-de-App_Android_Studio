/**
    Clase contenedora de imagenes en Android.
*/
package gdv.ucm.engine_android;


import android.graphics.Bitmap;

import gdv.ucm.interfaces.Image;

public class ImageAndroid implements Image {

    public ImageAndroid(Bitmap image){
        _image = image;
    }
/**
    @return Bitmap de Android.
*/
    public Bitmap getBitmap(){
        return _image;
    }
/**
    @return Ancho del bitmap
*/
    @Override
    public int getWidth() {
        return _image.getWidth();
    }
/**
    @return Alto del bitmap
*/
    @Override
    public int getHeight() {
        return _image.getHeight();
    }

    private Bitmap _image;
}
