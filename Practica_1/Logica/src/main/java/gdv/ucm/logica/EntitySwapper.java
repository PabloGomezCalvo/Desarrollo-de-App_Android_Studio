package gdv.ucm.logica;

import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class EntitySwapper extends Entity {
    public EntitySwapper(Sprite sprite, Rectangle position,int mode) {
        super(sprite, position);
        _hSwapper = sprite.get_rectTexture()._height;
        _mode = mode;
    }

    public void swapper(){

        if(_mode == 0){
            _sprite.get_rectTexture()._y = _hSwapper;
            _mode = 1;
        }
        else {
            _sprite.get_rectTexture()._y = 0;
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
            _sprite.get_rectTexture()._y = _hSwapper;
            _mode = 1;
        }
    }

    private int _hSwapper;
    private int _mode;
}
