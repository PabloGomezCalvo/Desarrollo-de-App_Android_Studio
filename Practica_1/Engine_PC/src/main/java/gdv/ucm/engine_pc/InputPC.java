package gdv.ucm.engine_pc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import gdv.ucm.interfaces.Input;
import gdv.ucm.utilities.Transform;


public class InputPC implements Input, MouseListener {

    public InputPC(Transform transform){
        _tranform = transform;
        _inputStream = new ArrayList<>();
    }

    @Override
    synchronized public List<TouchEvent> getTouchEvents() {
        return _inputStream;
    }

    synchronized private void addNewEvent(TouchEvent event){
        _inputStream.add(event);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        TouchEvent _event = new TouchEvent();
        _event.x = _tranform.changeToGamelCoordenatesX(mouseEvent.getX());
        _event.y = _tranform.changeToGamelCoordenatesY(mouseEvent.getY());
        _event._eventType = TouchEvent.EventType.Release;
        _event.pointerId = mouseEvent.getButton();


        addNewEvent(_event);

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        TouchEvent _event = new TouchEvent();
        _event.x = _tranform.changeToGamelCoordenatesX(mouseEvent.getX());
        _event.y = _tranform.changeToGamelCoordenatesY(mouseEvent.getY());
        _event._eventType = TouchEvent.EventType.Press;
        _event.pointerId = mouseEvent.getButton();


        addNewEvent(_event);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        TouchEvent _event = new TouchEvent();
        _event.x = _tranform.changeToGamelCoordenatesX(mouseEvent.getX());
        _event.y = _tranform.changeToGamelCoordenatesY(mouseEvent.getY());
        _event._eventType = TouchEvent.EventType.Release;
        _event.pointerId = mouseEvent.getButton();
        addNewEvent(_event);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    Transform _tranform;
    List<TouchEvent> _inputStream;
}
