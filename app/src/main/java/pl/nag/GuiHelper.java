package pl.nag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

    public static void setTextOrHide(TextView view, String text) {
        if (text != null && !text.isEmpty()) {
            view.setText(text);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    public static void popModal(final Context ctx) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage("Powrot do menu?");
        builder.setPositiveButton(/* R.string.ok */ "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(ctx, MenuActivity.class);
                ctx.startActivity(intent);
            }
        });
        builder.setNegativeButton(/*R.string.cancel*/ "NOK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Do nothing
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
