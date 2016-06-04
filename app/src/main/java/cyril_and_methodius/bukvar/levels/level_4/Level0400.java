package cyril_and_methodius.bukvar.levels.level_4;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import cyril_and_methodius.bukvar.R;
import cyril_and_methodius.bukvar.levels.level_2.Level0200;
import cyril_and_methodius.bukvar.levels.level_3.Level0300;

/**
 * Created by Teo on 6/3/2016.
 */
public class Level0400 extends AppCompatActivity {
    private GestureDetectorCompat gestureDetectorCompat;
    private Intent previousActivity;
    private MediaPlayer mp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_4_sentences);
        previousActivity = new Intent(this, Level0300.class);
        this.gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
        mp = MediaPlayer.create(Level0400.this, R.raw.level_04);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            if (event2.getX() < event1.getX()) {
                //startActivity(nextActivity);
            } else if (event2.getX() > event1.getX()) {
                startActivity(previousActivity);
            }
            return true;
        }
    }
}
