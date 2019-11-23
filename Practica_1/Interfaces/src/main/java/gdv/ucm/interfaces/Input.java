/**
    Interfaz cuyo objetivo es encapsular lo correspondiente con la parte de Input del juego.
*/
package gdv.ucm.interfaces;

import java.util.List;

public interface Input {

/**
    Clase que encapsula los diferentes eventos que pueden realizarse con el Input.
*/
    public class TouchEvent{
        public enum EventType{
            Press,
            Release,
            Dragg,
            EnteredScreen,
            ExitScreen
        }

        public EventType _eventType;
        public int x;
        public int y;
        public int pointerId;
    };

    List<TouchEvent> getTouchEvents();

}
