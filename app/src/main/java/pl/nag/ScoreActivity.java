package pl.nag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ScoreActivity extends Activity {

    @InjectView(R.id.scoreBar)
    RatingBar scoreBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ButterKnife.inject(this);

        // Setup score
        Intent incomingIntent = this.getIntent();
        double score = incomingIntent.getDoubleExtra(ExtraKey.POINTS.name(), 0.0);
        int maximumScore = ((NakApp)getApplication()).getScriptManager().countNodesOfType("dialog");
        setScore(maximumScore > 0 ? (float)score / maximumScore : 0.0f);
    }

    /**
     * @param successfull percentage, max 1.0
     */
    private void setScore(float successfull) {
        int stars = new Score(successfull).getStars();
        scoreBar.setRating(stars);
    }

    public void replayGame(View view) {
        Intent intent = getMainActivity();
        startActivity(intent);
    }

    public void continueGame(View view) {
        Intent intent = getMainActivity();
        // TODO
        intent.putExtra(ExtraKey.EPISODE.name(), 0);
        startActivity(intent);
    }


    private Intent getMainActivity() {
        return new Intent(this, MenuActivity.class);
    }
}
