/**
    Lanzador de la actividad de Android.
    Inicializa la lógica y los recursos.
*/
package gdv.ucm.practica_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gdv.ucm.engine_android.GameAndroid;
import gdv.ucm.logica.LogicStateManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogicStateManager logicStateManager = new LogicStateManager();
        _gameAndroid = new GameAndroid(this.getAssets(),this,logicStateManager);
        logicStateManager.init(_gameAndroid);
        setContentView(_gameAndroid.get_surfaceView());
    }
/**
    Estado de la aplicación cuando se vuelve de una pausa o se inicia la apliación.
*/
    @Override
    protected void onResume() {
        super.onResume();
        _gameAndroid.resume();
    }
/**
    Estado de la aplicación cuando se pone en pausa.
*/
    @Override
    protected void onPause() {
        super.onPause();
        _gameAndroid.pause();
    }

    private GameAndroid _gameAndroid;
}
