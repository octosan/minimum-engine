package pl.nag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CutsceneActivity extends Activity {

    private int index;
    private String videoId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutscene);

        Intent incomingIntent = this.getIntent();
        index = incomingIntent.getIntExtra(ExtraKey.INDEX.name(), 0);
        ((NakApp) getApplication()).getScriptManager().setNodeIndex(index);

        videoId = incomingIntent.getStringExtra(ExtraKey.VIDEOID.name());
        if (videoId == null && videoId.isEmpty()) {
            Button showMovie = (Button) findViewById(R.id.showMovie);
            showMovie.setVisibility(View.GONE);
        }
    }

    public void showMovie(View view) {
        new Cutscene(this).startIntent(videoId);

    }

    public void continueGame(View view) {
        Intent nextIntent = new Intent(this, ((NakApp) getApplication()).whatsNext());
        nextIntent.putExtra(ExtraKey.INDEX.name(), index + 1);
        nextIntent.putExtra(ExtraKey.POINTS.name(), getIntent().getDoubleExtra(ExtraKey.POINTS.name(), 0.0f));
        startActivity(nextIntent);
    }

}
