package cyril_and_methodius.bukvar.levels.level_1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import cyril_and_methodius.bukvar.R;
import cyril_and_methodius.bukvar.applicataion_launcher.MainActivity;
import cyril_and_methodius.bukvar.information_manager.ResultManager;
import cyril_and_methodius.bukvar.speech_recognition.SpeechRecognition;

public class Level0120 extends AppCompatActivity {
    private static final String WORD = "усмивка";
    private int points = 1;
    private SpeechRecognition speechRecognition;
    private Button btnSpeak;
    private String userSpeechInput;
    private Intent nextActivity;
    private Intent previousActivity;
    private Intent resultActivity;
    private Class nextActivityClass;
    private Class previusActivityClass;
    private GestureDetectorCompat gestureDetectorCompat;
    private ImageView imageView;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1_20_u_letter);

        //Image Media Player
        mediaPlayer = MediaPlayer.create(this, R.raw.sound_20u);
        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMediaPlayer();
            }
        });

        //Start Description
        this.startMediaPlayer();
        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer player) {
                startSpeechRecognition();
            }});

        //Speech Recognition
        this.speechRecognition = new SpeechRecognition(this);
        this.btnSpeak = (Button) findViewById(R.id.btnSpeak);
        this.btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpeechRecognition();
            }
        });

        //Change Activity on Swipe
        this.gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
        this.nextActivityClass = Level0121.class;
        this.nextActivity = new Intent(this, this.nextActivityClass);
        this.previusActivityClass = Level0119.class;
        this.previousActivity = new Intent(this, this.previusActivityClass);

        //Show Results
        this.resultActivity = new Intent(this, ResultManager.class);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.mediaPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mediaPlayer.release();
        this.finish();
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
                        //If match
                        //Points
                        MainActivity.getUser().setLevelOneCurrentPoints(points);
                        this.points = 0;

                        //Set Results
                        ResultManager.setNextActivity(this.nextActivityClass);
                        ResultManager.setResultType("Success");
                        //Set Activity

                        this.startActivity(this.resultActivity);
                    } else {
                        // If not match
                        //Set Results
                        ResultManager.setNextActivity(this.getClass());
                        ResultManager.setResultType("Failure");

                        //Set Activity
                        this.startActivity(this.resultActivity);
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
            } else if (event1.getX() < event2.getX()) {
                startActivity(previousActivity);
            }

            return true;
        }
    }

    private void startSpeechRecognition(){
        speechRecognition.promptSpeechInput();
    }

    private void startMediaPlayer(){
        mediaPlayer.start();
    }
}
