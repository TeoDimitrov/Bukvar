package cyril_and_methodius.bukvar.applicataion_launcher;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import cyril_and_methodius.bukvar.R;
import cyril_and_methodius.bukvar.levels.level_1.Level0100;
import cyril_and_methodius.bukvar.users.User;

public class MainActivity extends AppCompatActivity {
    private static Intent runningActivity;
    private Intent levelsActivity;
    private Toolbar toolbar;
    private static User user;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.user = new User();
        this.levelsActivity = new Intent(this, Level0100.class);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button nextSlide = (Button) findViewById(R.id.go_to_levels);
        nextSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(levelsActivity);
            }
        });
        mp = MediaPlayer.create(MainActivity.this, R.raw.main);
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

    public static User getUser() {
        return user;
    }

    public static Intent getRunningActivity() {
        return runningActivity;
    }

    public static void setRunningActivity(Intent runningActivity) {
        MainActivity.runningActivity = runningActivity;
    }
}
