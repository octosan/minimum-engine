package pl.nag;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author ts
 */
public class GuiHelper {

    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void updateImageViewByRaw(Activity activity, int imageView, String imageName) {
        try {
            Bitmap bitmap = new ImageHelper(activity).getRawByFileName(imageName);
            if (bitmap != null) {
                ((ImageView) activity.findViewById(R.id.imageView)).setImageBitmap(bitmap);
            }
        } catch (Resources.NotFoundException e) {
            GuiHelper.toast(activity, imageName + "  not found");
        }
    }
}
