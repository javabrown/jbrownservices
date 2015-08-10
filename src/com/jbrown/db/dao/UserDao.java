package com.jbrown.db.dao;

import java.util.List;

import com.jbrown.user.BrownUserI;

public interface UserDao {
  boolean createUser(BrownUserI user);

  List<BrownUserI> getAllUsers();

  BrownUserI getUserByEmail(String email);

  boolean updateUser(BrownUserI user);

  boolean deleteUser(BrownUserI user);
}
