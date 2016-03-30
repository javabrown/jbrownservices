package com.jbrown.db.dao.model;

public class User {
  private int _id;
  private String _name;
  private String _email;
  private String _phone;
  private String _password;

  public User(int id, String name, String email, String phone, String password) {
    _id = id;
    _name = name;
    _email = email;
    _phone = phone;
    _password = password;
  }

  public int getId() {
    return _id;
  }

  public String getName() {
    return _name;
  }

  public String getEmail() {
    return _email;
  }

  public String getPhone() {
    return _phone;
  }

  public String getPassword() {
    return _password;
  }

  public void setName(String _name) {
    this._name = _name;
  }

  public void setEmail(String _email) {
    this._email = _email;
  }

  public void setPhone(String _phone) {
    this._phone = _phone;
  }

  public void setPassword(String _password) {
    this._password = _password;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((_email == null) ? 0 : _email.hashCode());
    result = prime * result + _id;
    result = prime * result + ((_name == null) ? 0 : _name.hashCode());
    result = prime * result + ((_password == null) ? 0 : _password.hashCode());
    result = prime * result + _phone.hashCode();
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
    User other = (User) obj;
    if (_email == null) {
      if (other._email != null)
        return false;
    } else if (!_email.equals(other._email))
      return false;
    if (_id != other._id)
      return false;
    if (_name == null) {
      if (other._name != null)
        return false;
    } else if (!_name.equals(other._name))
      return false;
    if (_password == null) {
      if (other._password != null)
        return false;
    } else if (!_password.equals(other._password))
      return false;
    if (_phone != other._phone)
      return false;
    return true;
  }
}