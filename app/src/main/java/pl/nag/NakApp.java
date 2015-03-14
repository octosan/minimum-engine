package pl.nag;

import android.app.Application;
import android.util.Log;

/**
 * Controller
 */
public class NakApp extends Application {

    public static final int TIME_LEFT_SECONDS = 10;
    public ScriptManager scriptManager = null;

    public NakApp() {

    }

    public ScriptManager getScriptManager() {
        return scriptManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        scriptManager = new ScriptManager(this.getApplicationContext());
    }

    public Class getNextActivityClass() {
        return getNodeClass(scriptManager.getNextNodeType());
    }


    public Class getFirstActivityClass() {
        return getNodeClass(scriptManager.getFirstNodeType());
    }

    private Class getNodeClass(String nextNodeType) {
        Log.d("Navi", "Using node type: " + nextNodeType);
        if (nextNodeType.equals("scene")) {
            return CutsceneActivity.class;
        }
        if (nextNodeType.equals("cutscene")) {
            return CutsceneActivity.class;
        }
        if (nextNodeType.equals("dialog")) {
            return DialogActivity.class;
        }
        return ScoreActivity.class;
    }
}
