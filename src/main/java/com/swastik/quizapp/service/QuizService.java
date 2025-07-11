package com.swastik.quizapp.service;

import com.swastik.quizapp.dao.QuestionDao;
import com.swastik.quizapp.dao.QuizDao;
import com.swastik.quizapp.model.Question;
import com.swastik.quizapp.model.QuestionWrapper;
import com.swastik.quizapp.model.Quiz;
import com.swastik.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questionFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(),
                    q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> answers) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions(); // its just limited set of questions for that quiz id
        int score = 0;
        int i = 0;
        for(Response ans : answers){
            if(ans.getSelectedOption().equals(questions.get(i++).getRightOption()))
                score++;
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
