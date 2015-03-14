package pl.nag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * @author ts
 */
public class ImageHelper {

    private Context context;

    public ImageHelper(Context context) {
        this.context = context;
    }

    public Bitmap getRawByFileName(String imageName) {
        if (imageName != null && !imageName.isEmpty()) {
            if (imageName.contains(".")) {
                imageName = imageName.split("\\.")[0];
            }
            InputStream stream = context.getResources().openRawResource(
                    getRawId(context, imageName));
            return BitmapFactory.decodeStream(stream);
        } else {
            return null;
        }
    }

    private static int getRawId(Context context, String imageName) {
        return context.getResources().getIdentifier(imageName,
                "raw", context.getPackageName());
    }
}
