/**
    Clase que almacena un rectágulo a partir de su esquina superior izquierda, alto y ancho.
*/
package gdv.ucm.utilities;


public class Rectangle {

    public Rectangle(int x, int y, int w, int h){
        _x = x;
        _y = y;
        _width = w;
        _height = h;
    }
    public int _x;
    public int _y;
    public int _width;
    public int _height;
}
