package gdv.ucm.engine_pc;

import gdv.ucm.interfaces.Image;


/**
    Clase que representa una Imagen.
 */
public class ImagePC implements Image {

    /**
     Contructor que guarda la imagen dada
     @param image from awt library to storage it
     */
    public ImagePC(java.awt.Image image){
        _image = image;
    }

   /**
     @return Alto de la imagen.
     */
    @Override
    public int getWidth() {
        return _image.getWidth(null);
    }

    /**
     @return Ancho de la imagen.
     */
    @Override
    public int getHeight() {
        return _image.getHeight(null);
    }

    /**
     @return Imagen del tipo java.awt.Image
     */
    public java.awt.Image getAWTImage(){
        return _image;
    }

    private java.awt.Image _image;
}
