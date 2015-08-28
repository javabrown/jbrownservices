package com.jbrown.quiz.questions.model;

import java.io.Serializable;

public class Subject implements Serializable{
  private static final long serialVersionUID = 1L;
  
  String _name;
	String _topic;
	String _level;
	
  public Subject(String name, String topic, String level) {
    _name = name;
    _topic = topic;
    _level = level;
  }

  public String getName() {
    return _name;
  }

  public String getTopic() {
    return _topic;
  }

  public String getLevel() {
    return _level;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_level == null) ? 0 : _level.hashCode());
    result = prime * result + ((_name == null) ? 0 : _name.hashCode());
    result = prime * result + ((_topic == null) ? 0 : _topic.hashCode());
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
    Subject other = (Subject) obj;
    if (_level == null) {
      if (other._level != null)
        return false;
    } else if (!_level.equals(other._level))
      return false;
    if (_name == null) {
      if (other._name != null)
        return false;
    } else if (!_name.equals(other._name))
      return false;
    if (_topic == null) {
      if (other._topic != null)
        return false;
    } else if (!_topic.equals(other._topic))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Subject [_name=" + _name + ", _topic=" + _topic + ", _level="
        + _level + "]";
  }
}
