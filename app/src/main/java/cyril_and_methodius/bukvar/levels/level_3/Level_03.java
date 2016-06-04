package cyril_and_methodius.bukvar.levels.level_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import cyril_and_methodius.bukvar.R;
import cyril_and_methodius.bukvar.levels.level_4.Level_04;


public class Level_03 extends AppCompatActivity {
    private Intent nextActivity;
    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_3_long_words);
        nextActivity = new Intent(this, Level_04.class);
        this.gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
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
                startActivity(nextActivity);
            }
            return true;
        }
    }
}
