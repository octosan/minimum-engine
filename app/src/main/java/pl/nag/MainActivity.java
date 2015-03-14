package pl.nag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.nag.model.Answer;
import pl.nag.model.Script;
import pl.nag.model.ScriptManager;

public class MainActivity extends Activity {

    public static Script script = null;
    public static ScriptManager scriptManager;

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

        try {
            if (script == null) {
                script = new Parser().parse(this);
                scriptManager = new ScriptManager(script);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        Log.d("StateLog", "Application index: " + index);
        Log.d("StateLog", "Points: " + points);

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(scriptManager.getDescription());
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(scriptManager.getQuestion());
        Button[] b = {
                (Button) findViewById(R.id.answer0),
                (Button) findViewById(R.id.answer1),
                (Button) findViewById(R.id.answer2),
                (Button) findViewById(R.id.answer3)};
        Answer answer = scriptManager.getNextAnswer();
        List<Button> buttons = Arrays.asList(b);
        Collections.shuffle(buttons);
        for (int i = 0; i < b.length; i++) {
            buttons.get(i).setText(answer.getOptions().get(i).getText());
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
                nextIntent = new Intent(this, CutsceneActivity.class);
                nextIntent.putExtra(ExtraKey.VIDEOID.name(), "mSvuHSqqGSw"); // TODO
                break;
            default:
                nextIntent = null;
        }

        nextIntent.putExtra(ExtraKey.POINTS.name(), points);
        nextIntent.putExtra(ExtraKey.INDEX.name(), index + 1);
        startActivity(nextIntent);
    }
}
