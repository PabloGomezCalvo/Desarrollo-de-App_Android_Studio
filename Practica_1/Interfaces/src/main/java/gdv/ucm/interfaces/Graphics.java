/**
    Interfaz cuyo objetivo es abstraer de la plataforma las librerías para el uso de gráficos.
*/
package gdv.ucm.interfaces;

import java.io.IOException;

import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public interface Graphics {
/**
    Crea una Imagen que dependerá de la plataforma.
    @param name Ruta del recurso.
*/
    Image newImage(String name) throws IOException;
/**
    Borrado de la pantalla.
    @param color Color con el cual se hace el borrado.
*/
    void clear(int color);
/**
    Dibujado de imagenes.
    @param image Imagen a dibujar.
    @param x Posición X donde pintar.
    @param y Posición Y donde pintar.
*/    
    void drawImage(Image image, int x, int y);
/**
    Dibujado de imagenes.
    @param image Imagen a dibujar.
    @param rectOrigin Rectángulo del que partimos
    @param rectGoal Rectángulo donde queremos pintar.
*/   
    void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal);
/**
    Dibujado de imagenes.
    @param image Imagen a dibujar.
    @param rectOrigin Rectángulo del que partimos
    @param rectGoal Rectángulo donde queremos pintar.
    @param alpha Transparencia con la que pintar la imagen
*/ 
    void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, float alpha);
/**
    Dibujado de imagenes.
    @param image Imagen a dibujar.
    @param rectOrigin Rectángulo del que partimos
    @param rectGoal Rectángulo donde queremos pintar.
    @param color Color con el que pintar la imagen
*/ 
    void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, int color);
/**
	Devuelve el color de la imagen dada.
    @param image Imagen para sacar el color.
    @param x coordenada x de la esquina superior izquierda del rectángulo del que sacar los píxeles de la imagen.
    @param y coordenada y de la esquina superior izquierda del rectángulo del que sacar los píxeles de la imagen.
    @param w ancho del rectangulo de donde sacar los pixeles
    @param h alto del rectangulo de donde sacar los pixeles
    @return Color
*/ 
    int getColorSprite(Image image, int x, int y, int w, int h);
/**
    @return Ancho del Graphics
*/
    int getWidth();
/**
    @return Alto del Graphics
*/
    int getHeight();
}
