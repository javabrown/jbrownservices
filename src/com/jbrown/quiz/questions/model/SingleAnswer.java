package com.jbrown.quiz.questions.model;

public class SingleAnswer extends Answer {
  private static final long serialVersionUID = 1L;
  
  private String _answer;
	 
  public SingleAnswer(String answers){
    _answer = answers;
  }
  
  public String getAnswer(){
    return _answer;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_answer == null) ? 0 : _answer.hashCode());
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
    SingleAnswer other = (SingleAnswer) obj;
    if (_answer == null) {
      if (other._answer != null)
        return false;
    } else if (!_answer.equals(other._answer))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "SingleAnswer [_answer=" + _answer + "]";
  }
}