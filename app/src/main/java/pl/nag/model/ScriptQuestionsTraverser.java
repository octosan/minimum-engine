package pl.nag.model;

public class ScriptQuestionsTraverser {
    int currentIndex = 0;

    Script script;

    public ScriptQuestionsTraverser(Script script) {
        this.script = script;
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

    private Node getCurrentNode() {
        return script.getNodes().get(currentIndex);
    }

    private String notNull(String text) {
        return text != null ? text : "";
    }
}
