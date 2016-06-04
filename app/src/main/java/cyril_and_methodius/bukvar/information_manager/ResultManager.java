package cyril_and_methodius.bukvar.information_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cyril_and_methodius.bukvar.R;

/**
 * Created by teo on 04/06/16.
 */
public class ResultManager extends AppCompatActivity {
    private static final int DELAY = 5000;
    private Intent nextActivity;

    public ResultManager(Intent nextActivity){
        this.nextActivity = nextActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.level_results);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            Thread.sleep(this.DELAY);
            this.startActivity(this.nextActivity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Intent getRunningActivity() {
        return nextActivity;
    }
}
