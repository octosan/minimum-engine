package pl.nag;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @author ts
 */
public class Cutscene {

    private Activity context;

    public Cutscene(Activity activity) {
        this.context = activity;
    }

    public void startIntent(String videoId) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
            context.startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(toUrl(videoId)));
            context.startActivityForResult(intent, 0);
        }
    }

    /**
     * http://www.techairlines.com/useful-youtube-url-tricks/
     *
     * @param videoId
     * @return
     */
    private String toUrl(String videoId) {
        return "http://www.youtube.com/watch?v=" + videoId + "&autoplay=1&rel=0&modestbranding=1&showinfo=0&fs=1";
    }
}
