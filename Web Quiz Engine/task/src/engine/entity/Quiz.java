package engine.entity;

import com.fasterxml.jackson.annotation.*;
import engine.answer.Answer;
import engine.result.Result;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"id", "title", "text", "options"})
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @ElementCollection
    @NotNull
    @Size(min = 2)
    private List<String> options;

    @ElementCollection
    private List<Integer> answer;

    public Quiz(){
    }

    public Quiz(String title, String text, List<String> options, ArrayList<Integer> answer){
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
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

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public List<Integer> getAnswer() {
        return answer == null ? new ArrayList<Integer>() : answer;
    }

    @JsonProperty
    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
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
