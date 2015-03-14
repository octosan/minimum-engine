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
        return script.getNodes().get(currentIndex).getType().equals("dialog");
    }
}
