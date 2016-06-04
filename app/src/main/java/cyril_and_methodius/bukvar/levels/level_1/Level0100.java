package cyril_and_methodius.bukvar.levels.level_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;

import cyril_and_methodius.bukvar.R;

/**
 * Created by Teo on 6/3/2016.
 */
public class Level0100 extends AppCompatActivity {
    private Intent nextActivity;
    private GestureDetectorCompat gestureDetectorCompat;
    private Button btnGoToLevel;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1_alphabet);
        this.btnGoToLevel = (Button) findViewById(R.id.go_to_level);
        this.nextActivity = new Intent(this, Level0200.class);
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
