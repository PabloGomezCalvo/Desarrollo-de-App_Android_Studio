/**

    Clase cuya función es cambiar el rectángulo de la textura siempre y cuando dicho rectángulo sea colindante con el rectágulo que queremos cambiar. 
    @param sprite Sprite de la Entidad.
    @param position Posiciones globales de la entidad.
    @param mode Modo en el que me encuentro: 0 es abajo o izquierda y 1 es derecha o arriba. Esto sirve para después hacer el swap dependiendo el modo en el que nos encontremos.
    @param isVertical Nos sirve para saber si el cambio de las texturas va a ser en vertical o no.
*/
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
/**

    Hace el swap de los rectágulos para modificar la imagen.
    Hace distinción entre swap vertical y swap horizontal.

*/
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
/**

    @return modo en el que estamos.

*/
    public int getMode(){
        return _mode;
    }
/**

    @param mode modo al que vamos a cambiar.

*/
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
