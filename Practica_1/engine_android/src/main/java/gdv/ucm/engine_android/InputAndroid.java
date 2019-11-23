/**
    Implementación de la clase Input específica de Android.
*/
package gdv.ucm.engine_android;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gdv.ucm.interfaces.Input;
import gdv.ucm.utilities.AbstractGraphics;

public class InputAndroid implements Input, View.OnTouchListener {
/**
    @param abstractGraphics Necesario para hacer el escalado de la coordenada donde se haga el evento de Input.
*/
    public InputAndroid(AbstractGraphics abstractGraphics){
        _abstractGraphics = abstractGraphics;
        _inputStream = new ArrayList<>();
    }
/**
    @return Lista de los eventos.
*/
    @Override
    synchronized public List<TouchEvent> getTouchEvents() {
        return _inputStream;
    }
/**
    Comprueba y escala las coordenadas de pulsación con abstractGraphics y genera el evento correspondiente.
*/
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(_abstractGraphics.changeToGamelCoordenatesX((int)event.getX()) != -1 && _abstractGraphics.changeToGamelCoordenatesY((int)event.getY()) != -1) {
            TouchEvent _event = new TouchEvent();

            _event.x = _abstractGraphics.changeToGamelCoordenatesX((int) event.getX());
            _event.y = _abstractGraphics.changeToGamelCoordenatesY((int) event.getY());
            if (event.getAction() == MotionEvent.ACTION_UP)
                _event._eventType = TouchEvent.EventType.Release;
            else if (event.getAction() == MotionEvent.ACTION_DOWN)
                _event._eventType = TouchEvent.EventType.Press;
            else if (event.getAction() == MotionEvent.ACTION_MOVE)
                _event._eventType = TouchEvent.EventType.Dragg;
            _event.pointerId = 0;

            synchronized (this) {
                _inputStream.add(_event);
            }
        }
        return true;
    }
    private AbstractGraphics _abstractGraphics;
    private List<TouchEvent> _inputStream;
}
