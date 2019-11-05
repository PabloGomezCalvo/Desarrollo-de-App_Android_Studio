package gdv.ucm.logica;

import gdv.ucm.interfaces.Image;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class Entity {

    public Entity(Sprite sprite, Rectangle position){
        _sprite = sprite;
        _rectFinal = position;
        _x = 0;
        _y = 0;
    }

    public Image getImage(){
        return _sprite.get_img();
    }

    public Rectangle getRectOrigin(){
        return _sprite.get_rectTexture();
    }

    public float getPosX(){
        return _x;
    }

    public float getPosY(){
        return _y;
    }

    public Rectangle getPosRectangle(){
        return _rectFinal;
    }

    public void moveEntity(float x, float y){
        _x = x;
        _y = y;

        _rectFinal._x = (int)_x;
        _rectFinal._y = (int)_y;
    }


    private float _x;
    private float _y;
    private Rectangle _rectFinal;
    private Sprite _sprite;

}
