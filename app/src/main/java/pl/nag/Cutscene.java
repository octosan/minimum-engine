package pl.nag;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @author ts
 */
public class Cutscene {

    private Context context;

    public Cutscene(Context context) {
        this.context = context;
    }

    public void startIntent(String videoId) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(toUrl(videoId)));
            context.startActivity(intent);
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
