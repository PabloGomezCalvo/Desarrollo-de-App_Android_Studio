package gdv.ucm.logica;

import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;
//(int)(Math.random()* (_totalColors-1));
public class Animation{
    public Animation(Sprite sprite, int x, int y, float alpha) {
        _sprite = sprite;
        _isActive = false;
        _alpha = alpha;
        _x = x;
        _y = y;
        _posPerObj = new Bola[5];
        for (int i = 0; i < _posPerObj.length; i++ ) {
            int timesDiv = (int)(Math.random() * 3 + 2);
            int w = sprite.get_rectTexture()._width/timesDiv;
            int h = sprite.get_rectTexture()._height/timesDiv;
            int velX = (int)(Math.random()* 250);
            int velY = (int)(Math.random()* 250);
            _posPerObj[i] = new Bola(new Rectangle(x,y,w,h),velX,velY);
        }
    }

    public boolean isActive(){
        return _isActive;
    }

    public float getAlpha(){
        return _alpha;
    }

    public void PutColor(int color){
        if(color == 0){
            _sprite.get_rectTexture()._y = 0;
        }
        else {
            _sprite.get_rectTexture()._y = _sprite.get_rectTexture()._height;
        }
    }

    public int getTotalBalls(){
        return 5;
    }

    public Rectangle getFinalRect(int index){
        return _posPerObj[index]._rec;
    }

    public Sprite getSprite(){
        return _sprite;
    }

    public void resetAnimation(){
        _isActive = true;
        for (Bola bola : _posPerObj ) {
            int timesDiv = (int)(Math.random() * 3 + 2);
            bola._rec._x = _x;
            bola._rec._y = _y;
            bola._rec._width = _sprite.get_rectTexture()._width/timesDiv;
            bola._rec._height = _sprite.get_rectTexture()._height/timesDiv;
            bola._velX = (int)(Math.random()* 250);
            bola._velX = (int)(Math.random()* 250);
        }
    }

    public void simulate(){

    }

    private float _alpha;
    private int _x;
    private int _y;
    private Bola[] _posPerObj;
    private boolean _isActive;
    private Sprite _sprite;

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

}
