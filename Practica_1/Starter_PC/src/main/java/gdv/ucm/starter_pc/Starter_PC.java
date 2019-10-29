package gdv.ucm.starter_pc;

import java.awt.Graphics;
import java.awt.Paint;

import javax.swing.JFrame;

import gdv.ucm.engine_pc.GamePC;
import gdv.ucm.engine_pc.GraphicsPC;
import gdv.ucm.interfaces.GameLogic;
import gdv.ucm.interfaces.Image;
import gdv.ucm.logica.Logica;

public class Starter_PC {
    public static void main(String[] args){
        Logica logica = new Logica();
        GamePC game = new GamePC(400,400,logica);
        logica.init(game);
        game.init();
        game.run();
    }
}
