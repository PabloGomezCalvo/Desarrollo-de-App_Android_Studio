package gdv.ucm.engine_android;


import android.graphics.Bitmap;

import gdv.ucm.interfaces.Image;

public class ImageAndroid implements Image {

    public ImageAndroid(Bitmap image){
        _image = image;
    }

    public Bitmap getBitmap(){
        return _image;
    }

    @Override
    public int getWidth() {
        return _image.getWidth();
    }

    @Override
    public int getHeight() {
        return _image.getHeight();
    }

    Bitmap _image;

}
