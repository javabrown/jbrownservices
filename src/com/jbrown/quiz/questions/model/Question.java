package com.jbrown.quiz.questions.model;

import java.io.Serializable;

public class Question implements Serializable{
  private static final long serialVersionUID = 1L;
  
  private String _grade;
  private Subject _subject;
  private Category _category;
  private String _question;
  private String _hint;
  private Answer _answer;

  public Question(String grade, Subject subject, Category category,
      String question, String hint, String answer) {
    _grade = grade;
    _subject = subject;
    _category = category;
    _question = question;
    _hint = hint;
     
    if(category.typeOf(Category.MULTI_FILL_IN_THE_BLANK)
          || category.typeOf(Category.MULTI_FILL_IN_THE_BLANK)){
      _answer = new MultipleAnswer(new String[]{answer});
    }
    else{
      _answer = new SingleAnswer(answer);
    }
  }

  public String getGrade() {
    return _grade;
  }

  public Subject getSubject() {
    return _subject;
  }

  public Category getCategory() {
    return _category;
  }

  public String getQuestion() {
    return _question;
  }

  public String getHint() {
    return _hint;
  }

  public Answer getAnswer() {
    return _answer;
  }
}
