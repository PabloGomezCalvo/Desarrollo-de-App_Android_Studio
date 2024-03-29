/**

    Clase para el manejo de la Animación del sistema de partículas al morir y ganar puntos.
    @param sprite sprite de la partícula dentro del sprite sheet de balls.
    @param int x Posición x del sprite sheet de bolas.
    @param int y Posición y del sprite sheet de bolas.
    @param float alpha Transparencia de la partícula

*/

package gdv.ucm.logica;

import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;


public class Animation{
    public Animation(Sprite sprite, int x, int y, float alpha) {
        
        _sprite = sprite;
        _isActive = false;
        _alpha = alpha;
        _alphaPerTick = alpha;
        _acelerationY = -0.2f;
        _acelerationX = 0.5f;
        _x = x;
        _y = y;
        _posPerObj = new Bola[5];

        for (int i = 0; i < _posPerObj.length; i++ ) {
            int timesDiv = (int)(Math.random() * 3 + 2);
            int w = sprite.get_rectTexture()._width/timesDiv;
            int h = sprite.get_rectTexture()._height/timesDiv;
            int velX = (int)(Math.random()* 200 - 100);
            int velY = (int)(Math.random()* 200 - 100);
            _posPerObj[i] = new Bola(new Rectangle(x,y,w,h),velX,velY);
        }
    }
/**
    @return bool Animación activa

*/
    public boolean isActive(){
        return _isActive;
    }
/**

    Getter de transparencia de las partículas
    @return float alpha

*/
    public float getAlpha(){
        if(_alphaPerTick < 0)
            return 0.0f;
        return _alphaPerTick;
    }
/**

    Cambio de color de las partículas

*/
    public void PutColor(int color){
        if(color == 0){
            _sprite.get_rectTexture()._y = 0;
        }
        else {
            _sprite.get_rectTexture()._y = _sprite.get_rectTexture()._height;
        }
    }
/**

    Getters para el uso de partículas.

*/
    public int getTotalBalls(){
        return 5;
    }

    public Rectangle getFinalRect(int index){
        return _posPerObj[index]._rec;
    }

    public Sprite getSprite(){
        return _sprite;
    }
/**

    Reset de la animación de muerte para que cada una sea diferente.

*/
    public void resetAnimation(){
        _isActive = true;
        _alphaPerTick = _alpha;
        for (Bola bola : _posPerObj ) {
            int timesDiv = (int)(Math.random() * 3 + 2);
            bola._rec._x = _x;
            bola._rec._y = _y;
            bola._rec._width = _sprite.get_rectTexture()._width/timesDiv;
            bola._rec._height = _sprite.get_rectTexture()._height/timesDiv;
            bola._velX = (int)(Math.random()* 200 - 100); // para negativo y positivo
            bola._velY = (int)(Math.random()* 200 - 100);
        }
    }
/**

    Simulación del movimiento de las partículas.

*/
    public void simulate(){
        for(Bola bola : _posPerObj){
            bola._rec._x = bola._rec._x + (int)(bola._velX * _acelerationX);
            bola._rec._y = bola._rec._y + (int)(bola._velY * _acelerationY);
            _alphaPerTick -= 0.0025;
        }
        if(_alphaPerTick <= 0.0f){
            _isActive = false;
            _alphaPerTick = _alpha;
        }
    }
/**

    Clase para el manejo de cada partícula.

*/
    private class Bola{
        Rectangle _rec;
        int _velX;
        int _velY;
        public Bola(Rectangle rec, int velx, int vely){
            _rec = rec;
            _velX = velx;
            _velY = vely;
        }
    }

    private float _alpha;
    private float _alphaPerTick;
    private int _x;
    private int _y;
    private Bola[] _posPerObj;
    private boolean _isActive;
    private Sprite _sprite;
    private float _acelerationY;
    private float _acelerationX;
}
