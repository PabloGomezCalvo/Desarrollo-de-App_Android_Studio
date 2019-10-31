package gdv.ucm.utilities;

import gdv.ucm.interfaces.Graphics;
import gdv.ucm.interfaces.Image;

public class Sprite {
    private Image _img;
    private Rectangle _rectTexture;


    public Rectangle get_rectTexture() {
        return _rectTexture;
    }

    public Image get_img() {
        return _img;
    }

    public Sprite(Image image, Rectangle rectTexture){
        _rectTexture = rectTexture;
        _img = image;
    }
///tunearlo para q se pinte solo centrado y demas
    public void draw(Graphics g, Rectangle rectGoal){
        g.drawRectToRect(_img,_rectTexture,rectGoal);
    }
}
