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

public class MainActivity extends Activity implements View.OnClickListener {
    public static String INTENT_INDEX = "INDEX";
    public static String INTENT_POINTS = "POINTS";

    int points;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent incomingIntent = this.getIntent();

        index = incomingIntent.getIntExtra(INTENT_INDEX, 0);
        points = incomingIntent.getIntExtra(INTENT_POINTS, 0);

        Log.d("StateLog", "Application index: " + index);
        Log.d("StateLog", "Points: " + points);

        int buttonCount = 4;
        Button[] b =  {
                (Button)findViewById(R.id.answer0),
                (Button)findViewById(R.id.answer1),
                (Button)findViewById(R.id.answer2),
                (Button)findViewById(R.id.answer3)};

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

    @Override
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
                nextIntent.putExtra("VIDEOID", "mSvuHSqqGSw");
                break;
            default:
                return;
        }

        nextIntent.putExtra("POINTS", points);
        nextIntent.putExtra("INDEX", index+1);
        startActivity(nextIntent);
    }
}
