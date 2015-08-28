package com.jbrown.quiz.questions.model;

import java.util.Arrays;

public class MultipleAnswer extends Answer {
  private static final long serialVersionUID = 1L;
  
  String[] _answers;
	
	public MultipleAnswer(String[] answers){
	  _answers = answers;
	}
	
	public String[] getAnswers(){
	  return _answers;
	}
	
  @Override
  public String toString() {
    return "MultipleAnswer [_answers=" + Arrays.toString(_answers) + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(_answers);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MultipleAnswer other = (MultipleAnswer) obj;
    if (!Arrays.equals(_answers, other._answers))
      return false;
    return true;
  }
}

