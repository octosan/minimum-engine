package pl.nag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class ScoreActivity extends Activity {

    private RatingBar scoreBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreBar = (RatingBar) findViewById(R.id.scoreBar);

        // TODO
        Intent incomingIntent = this.getIntent();
        setScore(0.75f);
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
        return new Intent(this, DialogActivity.class);
    }
}
