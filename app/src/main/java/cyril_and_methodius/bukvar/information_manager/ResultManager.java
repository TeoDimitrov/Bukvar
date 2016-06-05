package cyril_and_methodius.bukvar.information_manager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import cyril_and_methodius.bukvar.R;
import cyril_and_methodius.bukvar.applicataion_launcher.MainActivity;

/**
 * Created by teo on 04/06/16.
 */
public final class ResultManager extends AppCompatActivity {
    private static final int DELAY = 2000;
    private static Class nextActivity;
    private static String resultType;
    private MediaPlayer mediaPlayer;
    private ProgressBar progressBar;
    private int progressStatus;


    public ResultManager() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set Result
        boolean isSuccess = ResultManager.resultType.equals("Success");
        boolean isFailure = ResultManager.resultType.equals("Failure");
        if (isSuccess) {
            this.setContentView(R.layout.level_results_success);
            this.mediaPlayer = mediaPlayer.create(ResultManager.this, R.raw.success1);
            this.startMediaPlayer();
        } else if (isFailure) {
            this.setContentView(R.layout.level_results_failure);
            this.mediaPlayer = mediaPlayer.create(ResultManager.this, R.raw.fail);
            this.startMediaPlayer();
        }

        //Set Progress Bar
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.updateProgressBar();
    }

    @Override
    protected void onStart() {
        super.onStart();

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

    private void startMediaPlayer() {
        this.mediaPlayer.start();
    }

    private void setProgressStatus(int progressStatus) {
        this.progressStatus = progressStatus;
    }

    public void updateProgressBar() {
        this.setProgressStatus(MainActivity.getUser().getLevelOneCurrentPoints());
        this.progressBar.setProgress(this.progressStatus);
    }
}
