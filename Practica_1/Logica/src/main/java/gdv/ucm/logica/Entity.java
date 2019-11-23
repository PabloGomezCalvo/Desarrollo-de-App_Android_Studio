/**

    Clase para la lógica de cada una de las Entidades del juego.
    @param sprite Sprite con el cual se va a pintar la Entidad.
    @param position Posiciones globales de la entidad.

*/

package gdv.ucm.logica;

import gdv.ucm.interfaces.Image;
import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public class Entity {

    public Entity(Sprite sprite, Rectangle position){
        _sprite = sprite;
        _rectFinal = position;
        _x = position._x;
        _y = position._y;
        _xImage = sprite.get_rectTexture()._x;
        _yImage = sprite.get_rectTexture()._y;
    }
/**

    @return Imagen que tiene contenido al Sprite.

*/
    public Image getImage(){
        return _sprite.get_img();
    }
/**

    @return Rectángulo de la textura.

*/
    public Rectangle getRectOrigin(){
        return _sprite.get_rectTexture();
    }
/**

    @return Posición X global de la entidad.

*/
    public float getPosX(){
        return _x;
    }
/**

    @return Posición Y global de la entidad.

*/
    public float getPosY(){
        return _y;
    }
/**

    @return Posición X de la textura.

*/
    public float getPosImgX(){
        return _xImage;
    }
/**

    @return Posición Y de la textura.

*/
    public float getPosImgY(){
        return _yImage;
    }
/**

    @return Rectángulo de posiciones globales de la Entidad.

*/
    public Rectangle getPosRectangle(){
        return _rectFinal;
    }
/**
    Función que se encarga de mover una entidad de una posición a otra.

    @param x posición X a la que mover la Entidad.
    @param y posición Y a la que mover la Entidad.

*/
    public void moveEntity(float x, float y){
        _x = x;
        _y = y;

        _rectFinal._x = (int)_x;
        _rectFinal._y = (int)_y;
    }
 /**
    Función que se encarga de mover el rectángulo de la textura y en caso de salirse de la textura, vuelve a la posición 0.

    @param x posición X de la textura.
    @param y posición Y de la textura.

*/
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
            getRectOrigin()._y =  0;
            _yImage = 0;
        }
    }

    protected float _xImage;
    protected float _yImage;
    protected float _x;
    protected float _y;
    protected Rectangle _rectFinal;
    protected Sprite _sprite;

}
