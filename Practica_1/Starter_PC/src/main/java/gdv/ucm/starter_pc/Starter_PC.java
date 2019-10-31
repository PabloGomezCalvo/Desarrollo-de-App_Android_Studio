package gdv.ucm.starter_pc;


import gdv.ucm.engine_pc.GamePC;
import gdv.ucm.logica.LogicStateManager;

public class Starter_PC {
    public static void main(String[] args){
        LogicStateManager logicStateManager = new LogicStateManager();
        GamePC game = new GamePC(800,600,logicStateManager);
        logicStateManager.init(game);
        game.init();
        game.run();
    }
}
