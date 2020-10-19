package engine.answer;

public class SolvedQuiz {
    private Long id;

    public SolvedQuiz() {}
    
    public SolvedQuiz(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
