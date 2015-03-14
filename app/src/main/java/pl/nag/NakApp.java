package pl.nag;

import android.app.Application;

import pl.nag.model.ScriptManager;

/**
 * Controller
 */
public class NakApp extends Application {
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

    public Class whatsNext() {
        return getNextNodeType(scriptManager.getNextNodeType());
    }

    private Class getNextNodeType(String nextNodeType) {
        if (nextNodeType != null) {
            if (nextNodeType.equals("scene")) {
                return CutsceneActivity.class;
            }
            if (nextNodeType.equals("cutscene")) {
                return CutsceneActivity.class;
            }
            if (nextNodeType.equals("dialog")) {
                return DialogActivity.class;
            }
        }
        return ScoreActivity.class;
    }
}
