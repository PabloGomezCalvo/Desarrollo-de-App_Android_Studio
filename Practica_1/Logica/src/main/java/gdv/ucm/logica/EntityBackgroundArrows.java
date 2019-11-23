/**

    Clase que extiende la superclase Entity para tener funcionalidad extra en la Entidad de las flechas del fondo del juego.

*/

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
/**

    Método sobrecargado de la clase que realiza la animación de la imagen de fondo.

    @param x Posición X de la textura
    @param y Posición Y de la textura

*/
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
/**
    @return color de fondo.
*/
    public int getColor(){
        return _color;
    }

    private int _color;
    private int _yStart;
    private int _tamPerTile;
    private int _offset;
}
