package engine.answer;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Answer {

    @NotNull
    private List<Integer> answer;

    public Answer(){ }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
