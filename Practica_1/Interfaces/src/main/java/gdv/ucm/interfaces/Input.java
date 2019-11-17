package gdv.ucm.interfaces;

import java.util.List;

public interface Input {


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
