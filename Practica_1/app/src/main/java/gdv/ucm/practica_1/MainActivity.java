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

    @Override
    protected void onResume() {
        super.onResume();
        _gameAndroid.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        _gameAndroid.pause();
    }

    GameAndroid _gameAndroid;
}
