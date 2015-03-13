package pl.nag.model;

public class Node {
    private String type;
    private String name;
    private String movie;
    private String description;
    private String image;
    private Question question;
    private Answer answer;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Node{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", movie='" + movie + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", question=" + question +
                ", answer=" + answer +
                '}';
    }
}
