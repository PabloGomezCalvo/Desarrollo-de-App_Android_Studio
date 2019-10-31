package gdv.ucm.interfaces;

import java.io.IOException;

import gdv.ucm.utilities.Rectangle;

public interface Graphics {

    Image newImage(String name) throws IOException;
    void clear(int color);
    void drawImage(Image image, int x, int y);//shit to finish
    void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal);
    void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, int alpha);
    int getWidth();
    int getHeight();
}
