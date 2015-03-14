package pl.nag;

import android.content.Context;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import pl.nag.model.Script;

public class Parser {

    private static final String script = "goldeneyeepisod1";

    public Script parse(Context context) throws UnsupportedEncodingException {
        InputStream ins = context.getResources().openRawResource(
                context.getResources().getIdentifier(script,
                        "raw", context.getPackageName()));

        Reader reader = new InputStreamReader(ins, "UTF-8");

        Gson gson = new Gson();
        return gson.fromJson(reader, Script.class);
    }


}
