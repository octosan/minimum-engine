package pl.nag.model;

import android.content.Context;
import android.util.Log;

import java.io.UnsupportedEncodingException;

import pl.nag.GuiHelper;
import pl.nag.Parser;

public class ScriptManager {

    private int currentIndex;
    private Script script;

    public ScriptManager(Context context) {
        try {
            script = new Parser().parse(context);
        } catch (UnsupportedEncodingException e) {
            GuiHelper.toast(context, e.getMessage());
        }
    }

    private boolean isDialog() {
        return getCurrentNode().getType().equals("dialog");
    }

    public Answer getNextAnswer() {
        while (currentIndex < script.getNodes().size() && !isDialog()) {
            currentIndex++;
        }
        if (currentIndex == script.getNodes().size()) {
            return null;
        }
        currentIndex++;
        return script.getNodes().get(currentIndex - 1).getAnswer();
    }

    public String getDescription() {
        return notNull(getCurrentNode().getDescription());
    }

    public String getQuestion() {
        Question question = getCurrentNode().getQuestion();
        String result = "";
        if (question != null) {
            result = notNull(question.getName()) + ": " + notNull(question.getText());
        }
        return result;
    }

    public String getImageName() {
        return getCurrentNode().getImage();
    }

    private Node getCurrentNode() {
        return script.getNodes().get(currentIndex);
    }

    private String notNull(String text) {
        return text != null ? text : "";
    }

    public void setNodeIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getNextNodeType() {
        currentIndex ++;
        Node node = null;
        if (script.getNodes().size() > currentIndex)
            node = getCurrentNode();
        currentIndex--;

        if (node != null)
            return node.getType();
        else
            return null;
    }
}
