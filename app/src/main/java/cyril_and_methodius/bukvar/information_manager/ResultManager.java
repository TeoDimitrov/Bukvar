package cyril_and_methodius.bukvar.information_manager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import cyril_and_methodius.bukvar.R;

/**
 * Created by teo on 04/06/16.
 */
public final class ResultManager extends AppCompatActivity {
    private static final int DELAY = 5000;
    private static Class nextActivity;

    public ResultManager(){
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.setContentView(R.layout.level_results);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(ResultManager.this, ResultManager.nextActivity));
            }
        }, 2000);
    }

    public static void setNextActivity(Class nextActivity) {
        ResultManager.nextActivity = nextActivity;
    }
}
