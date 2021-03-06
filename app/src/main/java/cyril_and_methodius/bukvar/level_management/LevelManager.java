package cyril_and_methodius.bukvar.level_management;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import cyril_and_methodius.bukvar.R;
import cyril_and_methodius.bukvar.levels.level_1.Level0101;

/**
 * Created by teo on 02/06/16.
 */
public class LevelManager extends AppCompatActivity {
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private Intent mainActivity;
    private ImageButton btnSpeak;
    private TextView txtSpeechInput;
    private Button btnGoBack;
    private TextView txtFeedback;
    private String userSpeechInput;
    private TextToSpeech txtToSpeech;
    private Button btnTextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.level_1_alphabet);
        Button startFirstLevel = (Button) findViewById(R.id.go_to_level);
        final Intent levelOneIntent = new Intent(this, Level0101.class);
        startFirstLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(levelOneIntent);
            }
        });
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "bg-BG");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {


                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    this.userSpeechInput = result.get(0);
                    txtSpeechInput.setText(this.userSpeechInput);
                }

                if (this.userSpeechInput.toLowerCase().equals("котка")) {
                    this.txtFeedback.setText("Браво, добре четеш");
                } else {
                    this.txtFeedback.setText("Ти каза: " + this.userSpeechInput + ", а не \"Котка\". Пробвай пак! :)");
                }
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

