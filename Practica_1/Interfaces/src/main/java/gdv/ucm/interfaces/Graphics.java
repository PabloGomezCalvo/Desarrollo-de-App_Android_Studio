package gdv.ucm.interfaces;

import java.io.IOException;

import gdv.ucm.utilities.Rectangle;
import gdv.ucm.utilities.Sprite;

public interface Graphics {

    Image newImage(String name) throws IOException;
    void clear(int color);
    void drawImage(Image image, int x, int y);//shit to finish
    void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal);
    void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, float alpha);
    void drawRectToRect(Image image, Rectangle rectOrigin, Rectangle rectGoal, int color);
    int getColorSprite(Image image, int x, int y, int w, int h);
    int getWidth();
    int getHeight();
}
