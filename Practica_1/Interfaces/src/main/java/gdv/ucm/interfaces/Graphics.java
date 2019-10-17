package gdv.ucm.interfaces;

public interface Graphics {

    Image newImage(String name);
    void clear(int color);
    void drawImage(Image image);//shit to finish
    int getWidth();
    int getHeight();
}
