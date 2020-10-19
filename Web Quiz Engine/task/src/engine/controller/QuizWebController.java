package engine.controller;

import engine.answer.Answer;
import engine.entity.User;
import engine.repository.QuizRepository;
import engine.entity.Quiz;
import engine.repository.UserRepository;
import engine.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.*;

@RestController
@RequestMapping("/api/quizzes")
@Validated
public class QuizWebController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository users;

    @PostMapping(consumes = "application/json")
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal User user){
        quiz.setAuthor(user);
        return quizRepository.save(quiz);
    }

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long id){
        Optional<Quiz> quiz = quizRepository.findById(id);
        if(quiz.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such quiz");
        }
        return quiz.get();
    }

    @GetMapping
    public Collection<Quiz> getAllQuizzes(){
        return quizRepository.findAll();
    }

    @PostMapping("/{id}/solve")
    public Result solveQuiz(@PathVariable Long id, @RequestBody(required = false) Answer answer) {
        if (answer.getAnswer().equals(getQuiz(id).getAnswer())) {
            return Result.correctResult;
        }
        return Result.incorrectResult;
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable("id") @Min(0) Long id, @AuthenticationPrincipal User user) {
        Quiz question = getQuiz(id);
        if (question.getAuthor().equals(user)) {
            quizRepository.delete(question);
            return;
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not allowed");
    }



}

