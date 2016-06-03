package cyril_and_methodius.bukvar.levels.level_1;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cyril_and_methodius.bukvar.R;
import cyril_and_methodius.bukvar.speech_recognition.SpeechRecognition;

/**
 * Created by Teo on 6/3/2016.
 */
public class Level0117 extends AppCompatActivity {
    private static final String WORD = "рак";
    private SpeechRecognition speechRecognition;
    private Button btnSpeak;
    private String userSpeechInput;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1_17_r_letter);
        speechRecognition = new SpeechRecognition(this);
        this.btnSpeak = (Button) findViewById(R.id.btnSpeak);
        this.btnSpeak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                speechRecognition.promptSpeechInput();
            }
        });
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
}
