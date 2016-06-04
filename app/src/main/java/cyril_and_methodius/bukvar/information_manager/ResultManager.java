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
    private static final int DELAY = 2000;
    private static Class nextActivity;
    private static String resultType;

    public ResultManager() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean isSuccess = ResultManager.resultType.equals("Success");
        boolean isFailure = ResultManager.resultType.equals("Failure");
        if (isSuccess) {
            this.setContentView(R.layout.level_results_success);
        } else if (isFailure) {
            this.setContentView(R.layout.level_results_failure);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(ResultManager.this, ResultManager.nextActivity));
            }
        }, ResultManager.DELAY);
    }

    public static void setNextActivity(Class nextActivity) {
        ResultManager.nextActivity = nextActivity;
    }

    public static void setResultType(String resultType) {
        ResultManager.resultType = resultType;
    }
}
