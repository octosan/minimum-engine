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
    private String name;

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
        Node node = null;
        int nextIndex = currentIndex + 1;
        if (script.getNodes().size() > nextIndex) {
            return script.getNodes().get(nextIndex).getType();
        }
        else {
            // TODO: (mrvoid) move to enum...
            return "finish";
        }
    }

    public String getMovie() {
        if (currentIndex < script.getNodes().size())
            return getCurrentNode().getMovie();
        else
            return null;
    }

    public int countNodesOfType(String type) {
        int count = 0;
        for (Node n : script.getNodes()) {
            count += type.equals(n.getType()) ? 1 : 0;
        }
        return count;
    }

    public String getName() {
        return getCurrentNode().getName();
    }

    public String getFirstNodeType() {
        if (script.getNodes().size() > 0) {
            return script.getNodes().get(0).getType();
        } else {
            return "finish";
        }
    }

    public Answer currentAnswer() {
        return getCurrentNode().getAnswer();
    }
}
