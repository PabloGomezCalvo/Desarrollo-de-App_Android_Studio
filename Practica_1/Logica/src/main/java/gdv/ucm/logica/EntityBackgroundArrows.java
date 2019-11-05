package gdv.ucm.logica;

import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class EntityBackgroundArrows extends Entity {
    public EntityBackgroundArrows(Sprite sprite, Rectangle position) {
        super(sprite, position);
    }


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

    private int _yStart = 1150;
    private int _tamPerTile = 614;
    private int _offset = 78;
}
