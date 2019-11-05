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
        _xImage = sprite.get_rectTexture()._x;
        _yImage = sprite.get_rectTexture()._y;
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

    public float getPosImgX(){
        return _xImage;
    }

    public float getPosImgY(){
        return _yImage;
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
    public void moveSurfaceImage(float x, float y){
        _xImage = x;
        _yImage = y;


        if(x >= 0 && x + getRectOrigin()._width <= getImage().getWidth())
            getRectOrigin()._x = (int)_xImage;
        else{
            getRectOrigin()._x = 0;
            _xImage = 0;
        }

        if(y >= 0 && y + getRectOrigin()._height <= getImage().getHeight())
            getRectOrigin()._y = (int)_yImage;
        else{
            getRectOrigin()._y =  getImage().getHeight() - getRectOrigin()._height;
            _yImage = getImage().getHeight() - getRectOrigin()._height;;
        }
    }

    private float _xImage;
    private float _yImage;
    private float _x;
    private float _y;
    private Rectangle _rectFinal;
    private Sprite _sprite;

}