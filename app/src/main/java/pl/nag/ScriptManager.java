package pl.nag;

import android.content.Context;

import java.io.UnsupportedEncodingException;

import pl.nag.model.Answer;
import pl.nag.model.Node;
import pl.nag.model.Question;
import pl.nag.model.Script;

public class ScriptManager {

    private int currentIndex;
    private Script script;
    private String videoId;

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
        currentIndex++;
        Node node = null;
        if (script.getNodes().size() > currentIndex)
            node = getCurrentNode();
        currentIndex--;

        if (node != null)
            return node.getType();
        else
            return null;
    }

    public String getMovie() {
        return getCurrentNode().getMovie();
    }
}
