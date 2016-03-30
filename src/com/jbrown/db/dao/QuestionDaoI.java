package com.jbrown.db.dao;

import java.util.List;

import com.jbrown.quiz.questions.model.Question;

public interface QuestionDaoI {
  boolean insertQuestion(Question question);
  Question[] getAllQuestions();
}
