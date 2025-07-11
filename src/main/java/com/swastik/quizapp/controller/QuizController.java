package com.swastik.quizapp.controller;

import com.swastik.quizapp.model.Question;
import com.swastik.quizapp.model.QuestionWrapper;
import com.swastik.quizapp.model.Response;
import com.swastik.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
//        return new ResponseEntity<>("Quiz created!", HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submiQuiz(@PathVariable Integer id, @RequestBody List<Response> answers){
        return quizService.calculateResult(id, answers);
    }
}