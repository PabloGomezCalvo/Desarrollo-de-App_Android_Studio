package gdv.ucm.starter_pc;

import java.awt.Graphics;
import java.awt.Paint;

import javax.swing.JFrame;

import gdv.ucm.engine_pc.GraphicsPC;
import gdv.ucm.interfaces.Image;

public class Starter_PC {
    public static void main(String[] args){
        System.out.println("Switch blyat game");

        JFrame ventana = new JFrame("jeg");
        ventana.setSize(400,400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setIgnoreRepaint(true);
        ventana.setVisible(true);


        int veces = 100;
        do{
            try{
                ventana.createBufferStrategy(2);
                break;
            }
            catch(Exception e){
            }
        }while(veces-- > 0);

        if(veces == 0){
            System.err.println("Ha petao");
            return;
        }

        java.awt.image.BufferStrategy strategy;
        strategy = ventana.getBufferStrategy();


        GraphicsPC pintor = new GraphicsPC(400,400);
        Image image = pintor.newImage("balls.png"); // asi estaRA EN LOGICA

        int x = 0;

        while(true){

            do{
                do{
                    java.awt.Graphics g = strategy.getDrawGraphics();
                    pintor.setNewGraphics(g);
                    try{
                        pintor.clear(255);
                        pintor.drawImage(image,++x,0);
                    }
                    finally{
                        g.dispose();
                    }
                }while(strategy.contentsRestored());
                strategy.show();

            }while(strategy.contentsLost());
            try{
                Thread.sleep(1);
            }
            catch(Exception e){}
        }

        //Image.load("sprites/player.png");
    }
}
