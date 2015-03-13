package pl.nag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends Activity {

    public static enum ExtraKey {INDEX, POINTS, EPISODE, VIDEOID}

    private int points;
    private int index;
    private int episode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent incomingIntent = this.getIntent();

        index = incomingIntent.getIntExtra(ExtraKey.INDEX.name(), 0);
        points = incomingIntent.getIntExtra(ExtraKey.POINTS.name(), 0);
        episode = incomingIntent.getIntExtra(ExtraKey.EPISODE.name(), 0);

        Log.d("StateLog", "Application index: " + index);
        Log.d("StateLog", "Points: " + points);

        int buttonCount = 4;
        Button[] b = {
                (Button) findViewById(R.id.answer0),
                (Button) findViewById(R.id.answer1),
                (Button) findViewById(R.id.answer2),
                (Button) findViewById(R.id.answer3)};

        Random r = new Random();
        r.setSeed(2531 + index * 123143 + points * 43223);
        for (int i = 0; i < buttonCount; i++) {
            b[i].setText(Integer.toString(r.nextInt()));
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

    public void onClick(View view) {
        Intent nextIntent;

        switch (view.getId()) {
            case (R.id.answer0):
                nextIntent = new Intent(this, MainActivity.class);
                break;
            case (R.id.answer1):
                nextIntent = new Intent(this, MainActivity.class);
                break;
            case (R.id.answer2):
                nextIntent = new Intent(this, MainActivity.class);
                break;
            case (R.id.answer3):
                nextIntent = new Intent(this, Cutscene.class);
                nextIntent.putExtra(ExtraKey.VIDEOID.name(), "mSvuHSqqGSw");
                break;
            default:
                return;
        }

        nextIntent.putExtra(ExtraKey.POINTS.name(), points);
        nextIntent.putExtra(ExtraKey.INDEX.name(), index + 1);
        nextIntent.putExtra(ExtraKey.EPISODE.name(), episode);
        startActivity(nextIntent);
    }
}
