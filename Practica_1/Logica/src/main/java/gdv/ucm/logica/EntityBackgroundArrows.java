package gdv.ucm.logica;

import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class EntityBackgroundArrows extends Entity {

    public EntityBackgroundArrows(Sprite sprite, Rectangle position, int color) {
        super(sprite, position);
        _color = color;
        _yStart = sprite.get_rectTexture()._y;
        _tamPerTile = sprite.get_img().getHeight() / 5;
        _offset = _tamPerTile - ((_tamPerTile * 4) - sprite.get_rectTexture()._height);
    }

    @Override
    public void moveSurfaceImage(float x, float y){
        _xImage = x;
        _yImage = y;


        if(y >= _tamPerTile - _offset)
            getRectOrigin()._y = (int)_yImage;
        else{
            _yImage = _yStart;
            getRectOrigin()._y =  _yStart;
        }
    }

    public int getColor(){
        return _color;
    }

    private int _color;
    private int _yStart;
    private int _tamPerTile;
    private int _offset;
}
