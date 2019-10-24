package gdv.ucm.engine_pc;

import gdv.ucm.interfaces.Image;


/**
 * Class used to represent an image allocated as a bitmap
 */
public class ImagePC implements Image {

    /**
     * Contructor that saves the image given
     *
     * @param image from awt library to storage it
     */
    public ImagePC(java.awt.Image image){
        _image = image;
    }

    /**
     *  Returns the Width of the image allocated
     *
     * @return Returns the Width of the image allocated
     */
    @Override
    public int getWidth() {
        return _image.getWidth(null);
    }

    /**
     * Returns the Height of the image allocated
     *
     * @return Returns the Height of the image allocated
     */
    @Override
    public int getHeight() {
        return _image.getHeight(null);
    }

    /**
     * Returns the image allocated as java.awt.Image
     *
     * @return returns the image allocated as java.awt.Image
     */
    public java.awt.Image getAWTImage(){
        return _image;
    }

    private java.awt.Image _image;
}
