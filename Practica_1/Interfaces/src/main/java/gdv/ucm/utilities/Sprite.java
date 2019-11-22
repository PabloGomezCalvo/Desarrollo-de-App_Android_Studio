package gdv.ucm.utilities;

import gdv.ucm.interfaces.Image;

public class Sprite {

    public Sprite(Image image, Rectangle rectTexture){
        _rectTexture = rectTexture;
        _img = image;
    }

    public Rectangle get_rectTexture() {
        return _rectTexture;
    }

    public Image get_img() {
        return _img;
    }

    private Image _img;
    private Rectangle _rectTexture;
}
