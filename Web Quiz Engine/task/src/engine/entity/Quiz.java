package engine.entity;

import com.fasterxml.jackson.annotation.*;
import engine.answer.Answer;
import engine.result.Result;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@JsonPropertyOrder({"id", "title", "text", "options"})
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @ElementCollection
    @NotNull(message = "Options field is required")
    @Size(min = 2, message = "There should be at least 2 options")
    private List<String> options;

    @ElementCollection
    @NotNull(message = "Answer field is required")
    private Set<Integer> answer;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH)
    private User author;

    public Quiz() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @JsonIgnore
    public Set<Integer> getAnswer() {
        return answer;
    }

    @JsonProperty
    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Result solve(Answer answer){
        List<Integer> answerSet = answer.getAnswer();

        if ((this.answer == null && answerSet.isEmpty())
                || (this.answer != null && this.answer.equals(answerSet))) {
            return Result.correctResult;
        }

        return Result.incorrectResult;
    }
}
