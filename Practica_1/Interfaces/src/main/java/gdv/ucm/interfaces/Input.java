package gdv.ucm.interfaces;

import org.w3c.dom.events.Event;

import java.util.List;

public interface Input {

    enum EventType{
        Press,
        Release,
        Dragg
    }

    class TouchEvent{

        EventType getType(){
            return _event;
        }

        public int x;
        public int y;

        public EventType _event;

        public int fingerId;
    };

    List<TouchEvent> getTouchEvents();

}
