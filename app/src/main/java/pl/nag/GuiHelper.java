package pl.nag;

import android.content.Context;
import android.widget.Toast;

/**
 * @author ts
 */
public class GuiHelper {

    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
