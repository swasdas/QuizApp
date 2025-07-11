package com.swastik.quizapp.controller;

import com.swastik.quizapp.model.Question;
import com.swastik.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{givenCategory}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String givenCategory){
        return questionService.getAllQuestionsByCategory(givenCategory);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

}
