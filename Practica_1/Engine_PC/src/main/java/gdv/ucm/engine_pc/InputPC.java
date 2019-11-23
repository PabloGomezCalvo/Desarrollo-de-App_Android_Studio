/**
    Implementación de la clase Input específica de PC.
*/
package gdv.ucm.engine_pc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import gdv.ucm.interfaces.Input;
import gdv.ucm.utilities.AbstractGraphics;


public class InputPC implements Input, MouseListener {
/**
    @param abstractGraphics Necesario para hacer el escalado de la coordenada donde se haga el evento de Input.
*/
    public InputPC(AbstractGraphics abstractGraphics){
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
    Añade el evento de Release a la lista de eventos.
*/
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        TouchEvent _event = createEvent(mouseEvent);
        _event._eventType = TouchEvent.EventType.Release;
        synchronized (this) {
            _inputStream.add(_event);
        }
    }
/**
    Añade el evento de Press a la lista de eventos.
*/
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        TouchEvent _event = createEvent(mouseEvent);
        _event._eventType = TouchEvent.EventType.Press;
        synchronized (this) {
            _inputStream.add(_event);
        }
    }
/**
    Añade el evento de Release a la lista de eventos.
*/
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        TouchEvent _event = createEvent(mouseEvent);
        _event._eventType = TouchEvent.EventType.Release;
        synchronized (this) {
            _inputStream.add(_event);
        }
    }
/**
    Añade el evento cuando el ratón entra en la pantalla de juego.
*/
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        TouchEvent _event = createEvent(mouseEvent);
        _event._eventType = TouchEvent.EventType.EnteredScreen;
        synchronized (this) {
            _inputStream.add(_event);
        }

    }
/**
    Añade el evento cuando el ratón sale en la pantalla de juego.
*/
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        TouchEvent _event = createEvent(mouseEvent);
        _event._eventType = TouchEvent.EventType.ExitScreen;
        synchronized (this) {
            _inputStream.add(_event);
        }
    }
/**
    Crea un evento en la posición x e y donde se pulse.
    @param mouseEvent evento a generar.
*/
    private TouchEvent createEvent(MouseEvent mouseEvent) {
        TouchEvent _event = new TouchEvent();
        _event.x = _abstractGraphics.changeToGamelCoordenatesX(mouseEvent.getX());
        _event.y = _abstractGraphics.changeToGamelCoordenatesY(mouseEvent.getY());
        _event.pointerId = mouseEvent.getButton();
        return _event;
    }

    private AbstractGraphics _abstractGraphics;
    private List<TouchEvent> _inputStream;
}
