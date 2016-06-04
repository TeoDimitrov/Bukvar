package cyril_and_methodius.bukvar.speech_recognition;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Locale;

import cyril_and_methodius.bukvar.R;

/**
 * Created by teo on 03/06/16.
 */
public class SpeechRecognition extends FragmentActivity{
    public static final int REQ_CODE_SPEECH_INPUT = 100;
    public static final int DELAYED_PROMPT = 5000;
    private AppCompatActivity appCompatActivity;

    public SpeechRecognition(AppCompatActivity appCompatActivity){
        this.appCompatActivity=appCompatActivity;
    }

    public void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "bg-BG");
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,this.DELAYED_PROMPT);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS,this.DELAYED_PROMPT);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, this.DELAYED_PROMPT);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,R.string.speech_prompt);
        try {
            appCompatActivity.startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
