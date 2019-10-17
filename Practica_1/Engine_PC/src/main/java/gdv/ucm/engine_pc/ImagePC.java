package gdv.ucm.engine_pc;

import gdv.ucm.interfaces.Image;



public class ImagePC implements Image {
    @Override
    public int getWidth() {
        return _image.getWidth(null);
    }

    @Override
    public int getHeight() {
        return _image.getHeight(null);
    }

    private java.awt.Image _image;
}
