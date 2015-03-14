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
    private String image;
    private ScriptManager scriptManager;

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
        image = incomingIntent.getStringExtra(ExtraKey.IMAGE.name());
        scriptManager = ((NakApp) getApplication()).getScriptManager();
        scriptManager.setNodeIndex(index);

        GuiHelper.setTextOrHide(name, scriptManager.getName());
        GuiHelper.setTextOrHide(description, scriptManager.getDescription());
        GuiHelper.updateImageViewByRaw(this, R.id.imageView, scriptManager.getImageName());
        videoId = scriptManager.getMovie();
        if (videoId == null || videoId.isEmpty()) {
            showMovie.setVisibility(View.GONE);
        }
        if(scriptManager.getImageName() != null){
            image = scriptManager.getImageName();
        }
        GuiHelper.updateImageViewByRaw(this, R.id.imageView, image);
    }

    public void showMovie(View view) {
        new Cutscene(this).startIntent(videoId);
    }

    public void continueGame(View view) {
        int nextIndex = index + 1;
        Intent nextIntent = new Intent(this, ((NakApp) getApplication()).getNextActivityClass());
        nextIntent.putExtra(ExtraKey.INDEX.name(), nextIndex);
        // pass the image
        nextIntent.putExtra(ExtraKey.IMAGE.name(), image);
        // pass the points
        nextIntent.putExtra(ExtraKey.POINTS.name(), getIntent().getDoubleExtra(ExtraKey.POINTS.name(), 0.0f));
        nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Log.i("Navi", "Going to index " + (index + 1));

        startActivity(nextIntent);
    }

    @Override
    public void onBackPressed() {
        GuiHelper.popModal(this);
    }
}
