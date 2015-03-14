package pl.nag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.nag.model.Answer;

public class DialogActivity extends Activity {

    private static final List<Integer> ANSWERS_IDS = Arrays.asList(R.id.answer0, R.id.answer1, R.id.answer2, R.id.answer3);
    public static ScriptManager scriptManager = null;

    @InjectView(R.id.episode1)
    TextView description;
    @InjectView(R.id.question)
    TextView question;
    @InjectView(R.id.timeLeft)
    ProgressBar timeLeftBar;

    private Map<Integer, Double> pointsMap = new HashMap<Integer, Double>();
    private double points;
    private int index;
    private int episode;
    private String image;
    private List<Button> buttons = new ArrayList<Button>();
    private CountDownTimer timeLeftTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.inject(this);

        Intent incomingIntent = this.getIntent();

        index = incomingIntent.getIntExtra(ExtraKey.INDEX.name(), 0);
        points = incomingIntent.getDoubleExtra(ExtraKey.POINTS.name(), 0);
        episode = incomingIntent.getIntExtra(ExtraKey.EPISODE.name(), 0);
        image = incomingIntent.getStringExtra(ExtraKey.IMAGE.name());


        scriptManager = ((NakApp)getApplication()).getScriptManager();
        scriptManager.setNodeIndex(index);

        Log.d("StateLog", "Application index: " + index);
        Log.d("StateLog", "Points: " + points);

        // View setup
        description.setText(scriptManager.getDescription());
        question.setText(scriptManager.getQuestion());
        if(scriptManager.getImageName() != null){
            image = scriptManager.getImageName();
        }
        GuiHelper.updateImageViewByRaw(this, R.id.imageView, image);

        timeLeftTimer = new TimeLeftTimer(timeLeftBar, new OnFinishCallback() {
            @Override
            public void onFinish() {
                GuiHelper.toast(DialogActivity.this, "...");
                continueToNextNode();
            }
        }).start();

        // Shuffling of buttons
        shuffleButtons();
    }

    private void shuffleButtons() {
        Answer answer = scriptManager.getNextAnswer();
        Collections.shuffle(ANSWERS_IDS);
        for (int i = 0; i < ANSWERS_IDS.size(); i++) {
            Button button = (Button) findViewById(ANSWERS_IDS.get(i));
            //if (answer != null && answer.getOptions() != null && answer.getOptions().get(i) != null) {
            button.setText(answer.getOptions().get(i).getText());
            //}
            pointsMap.put(ANSWERS_IDS.get(i), answer.getOptions().get(i).getValue());
            buttons.add(button);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings ? true : super.onOptionsItemSelected(item);
    }

    private void continueToNextNode() {
        // TODO: (mrvoid) Is this needed?
        timeLeftTimer.cancel();

        Intent nextIntent;
        nextIntent = new Intent(this, ((NakApp) getApplication()).getNextActivityClass());
        nextIntent.putExtra(ExtraKey.IMAGE.name(), image);
        nextIntent.putExtra(ExtraKey.VIDEO_ID.name(), scriptManager.getMovie());
        nextIntent.putExtra(ExtraKey.POINTS.name(), points);
        nextIntent.putExtra(ExtraKey.INDEX.name(), index + 1);
        nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Log.i("Navi", "Going to index " + (index + 1));
        startActivity(nextIntent);
    }

    public void onClick(View view) {
        if (pointsMap.containsKey(view.getId())) {
            points += pointsMap.get(view.getId());
        } else {
            return;
        }

        continueToNextNode();
    }

    @Override
    protected void onStop() {
        super.onStop();
        timeLeftTimer.cancel();
    }
}
