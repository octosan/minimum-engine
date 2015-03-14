package pl.nag;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.nag.model.Answer;
import pl.nag.model.Script;
import pl.nag.model.ScriptManager;

public class DialogActivity extends Activity {
    private Map<Integer, Double> map = new HashMap<Integer, Double>();
    private Class nextNodeType;

    public static Script script = null;
    public static ScriptManager scriptManager;

    private double points;
    private int index;
    private int episode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent incomingIntent = this.getIntent();

        index = incomingIntent.getIntExtra(ExtraKey.INDEX.name(), 0);
        points = incomingIntent.getDoubleExtra(ExtraKey.POINTS.name(), 0);
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

        // View setup
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(scriptManager.getDescription());
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(scriptManager.getQuestion());
        updateImage(scriptManager.getImage());

        // Shuffling of buttons
        List<Integer> buttons = Arrays.asList(R.id.answer0, R.id.answer1, R.id.answer2, R.id.answer3);
        Answer answer = scriptManager.getNextAnswer();
        Collections.shuffle(buttons);
        for (int i = 0; i < buttons.size(); i++) {
            Button button = (Button) findViewById(buttons.get(i));
            if (answer != null && answer.getOptions() != null && answer.getOptions().get(i) != null) {
                button.setText(answer.getOptions().get(i).getText());
            }
            map.put(buttons.get(i), answer.getOptions().get(i).getValue());
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
        if (map.containsKey(view.getId())) {
            nextIntent = new Intent(this, DialogActivity.class);
            nextIntent.putExtra(ExtraKey.POINTS.name(), points + map.get(view.getId()));
            nextIntent.putExtra(ExtraKey.INDEX.name(), index + 1);
        } else {
            return;
        }
        startActivity(nextIntent);
    }

    private void updateImage(String image) {
        if (image != null) {
            try {
                InputStream ins = getResources().openRawResource(
                        getResources().getIdentifier(image,
                                "raw", getPackageName()));
                Bitmap myBitmap = BitmapFactory.decodeStream(ins);
                ImageView myImage = (ImageView) findViewById(R.id.imageView);
                myImage.setImageBitmap(myBitmap);
            } catch (Resources.NotFoundException e) {
                GuiHelper.toast(this, image + " Not Found");
            }
        }
    }
}
