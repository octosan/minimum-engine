package pl.nag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CutsceneActivity extends Activity {

    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.episode1)
    TextView description;
    @InjectView(R.id.showMovie)
    Button showMovie;

    private int index;
    private String videoId;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        continueGame(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cutscene);
        ButterKnife.inject(this);

        Intent incomingIntent = this.getIntent();
        index = incomingIntent.getIntExtra(ExtraKey.INDEX.name(), 0);
        ScriptManager scriptManager = ((NakApp) getApplication()).getScriptManager();
        scriptManager.setNodeIndex(index);

        name.setText(scriptManager.getName());
        description.setText(scriptManager.getDescription());
        GuiHelper.updateImageViewByRaw(this, R.id.imageView, scriptManager.getImageName());
        videoId = incomingIntent.getStringExtra(ExtraKey.VIDEO_ID.name());
        if (videoId == null || videoId.isEmpty()) {
            showMovie.setVisibility(View.GONE);
        }
    }

    public void showMovie(View view) {
        new Cutscene(this).startIntent(videoId);

    }

    public void continueGame(View view) {
        Intent nextIntent = new Intent(this, ((NakApp) getApplication()).getNextActivityClass());
        nextIntent.putExtra(ExtraKey.INDEX.name(), index + 1);
        nextIntent.putExtra(ExtraKey.POINTS.name(), getIntent().getDoubleExtra(ExtraKey.POINTS.name(), 0.0f));
        Log.i("Navi", "Going to index " + (index + 1));
        startActivity(nextIntent);
    }

}
