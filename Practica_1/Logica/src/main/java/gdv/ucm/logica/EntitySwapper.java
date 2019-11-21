package gdv.ucm.logica;

import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class EntitySwapper extends Entity {
    public EntitySwapper(Sprite sprite, Rectangle position,int mode,boolean isVertical) {
        super(sprite, position);
        _isVertical = isVertical;
        if(isVertical)
            _swapper = sprite.get_rectTexture()._height;
        else
            _swapper = sprite.get_rectTexture()._width;
        _mode = mode;
    }

    public void swapper(){

        if(_mode == 0){
            if(_isVertical)
                _sprite.get_rectTexture()._y = _swapper;
            else
                _sprite.get_rectTexture()._x += _swapper;
            _mode = 1;
        }
        else {
            if(_isVertical)
                _sprite.get_rectTexture()._y = 0;
            else
                _sprite.get_rectTexture()._x -= _swapper;
            _mode = 0;
        }
    }

    public int getMode(){
        return _mode;
    }

    public void setMode(int mode){
        _mode = mode;
        if(_mode == 0){
            _sprite.get_rectTexture()._y = 0;
            _mode = 0;
        }
        else {
            _sprite.get_rectTexture()._y = _swapper;
            _mode = 1;
        }
    }

    private boolean _isVertical;
    private int _swapper;
    private int _mode;
}
