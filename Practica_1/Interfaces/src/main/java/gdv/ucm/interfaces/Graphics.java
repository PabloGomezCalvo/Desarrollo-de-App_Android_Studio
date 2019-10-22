package gdv.ucm.interfaces;

public interface Graphics {

    Image newImage(String name);
    void clear(int color);
    void drawImage(Image image, int x, int y);//shit to finish
    int getWidth();
    int getHeight();
}
