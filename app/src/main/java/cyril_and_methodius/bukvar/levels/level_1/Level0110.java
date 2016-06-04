package cyril_and_methodius.bukvar.levels.level_1;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cyril_and_methodius.bukvar.R;
import cyril_and_methodius.bukvar.speech_recognition.SpeechRecognition;

public class Level0110 extends AppCompatActivity {
    private static final String WORD = "йо-йо";
    private SpeechRecognition speechRecognition;
    private Button btnSpeak;
    private String userSpeechInput;
    private Intent nextActivity;
    private Intent previousActivity;
    private GestureDetectorCompat gestureDetectorCompat;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1_10_j_letter);
        speechRecognition = new SpeechRecognition(this);
        this.btnSpeak = (Button) findViewById(R.id.btnSpeak);
        this.btnSpeak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                speechRecognition.promptSpeechInput();
            }
        });
        this.gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
        nextActivity = new Intent(this, Level0111.class);
        previousActivity = new Intent(this, Level0109.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SpeechRecognition.REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    this.userSpeechInput = result.get(0);

                    if (this.userSpeechInput.toLowerCase().equals(this.WORD)) {
                        // if match
                    } else {
                        // if not match
                    }
                }

                break;
            }
        }
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
            } else if (event2.getX() > event1.getX()) {
                startActivity(previousActivity);
            }
            return true;
        }
    }
}
