package pl.nag;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import pl.nag.model.Script;

public class Parser {

    private static final String script = "src/test/resource/script.json";

    public Script parse() throws UnsupportedEncodingException {
        Reader reader = new InputStreamReader(Parser.class.getResourceAsStream(script), "UTF-8");

        Gson gson = new Gson();
        return gson.fromJson(reader, Script.class);
    }



}
