package gdv.ucm.interfaces;

import java.io.IOException;

public interface Graphics {

    Image newImage(String name) throws IOException;
    void clear(int color);
    void drawImage(Image image, int x, int y);//shit to finish
    int getWidth();
    int getHeight();
}
