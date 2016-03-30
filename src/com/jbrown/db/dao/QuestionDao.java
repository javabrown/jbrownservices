package com.jbrown.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jbrown.db.core.DbUtil;
import com.jbrown.quiz.questions.model.Category;
import com.jbrown.quiz.questions.model.Question;
import com.jbrown.quiz.questions.model.Subject;
import com.jbrown.web.ws.BrownRequestI;

public class QuestionDao extends AbstractDao implements QuestionDaoI {
  private Statement statement;
  private ResultSet rs;
  
  static String INSERT_SQL = "insert into questions (category, subject_level, subject_topic, subject_name, question, hint, answer) "
      + "values ('%s', '%s', '%s', '%s', '%s', '%s', '%s')";
  
  static String SELECT_ALL_SQL = "select id, category, subject_level, subject_topic, "
      + "subject_name, question, hint, answer from questions";

  
  public QuestionDao(BrownRequestI request) {
    super(request);
  }
  
  @Override
  public Question[] getAllQuestions() {
    List<Question> questionList = new ArrayList<Question>();

    String selectSql = String.format(SELECT_ALL_SQL);

    try {
      statement = getConnection().createStatement();
      rs = statement.executeQuery(selectSql);

      while (rs.next()) {
        int id = rs.getInt(1);
        String subjectCategory = rs.getString(2);
        String subjectLevel = rs.getString(3);
        String subjectTopic = rs.getString(4);
        String subjectName = rs.getString(5);
        String questionStr = rs.getString(6);
        String hint = rs.getString(7);
        String ans = rs.getString(8);
        
        Category category = Category.find(subjectCategory);
        Subject subject = new Subject(subjectName, subjectTopic, subjectLevel);
        
        
        Question question = new Question("grade"+id, subject, category, 
            questionStr, hint, ans);
        
        questionList.add(question);
      }
    } catch (SQLException ex) {
      System.out.printf("Error in getAllUsers => %s", ex);
    } finally {
      DbUtil.close(rs);
      DbUtil.close(statement);
    }

    return questionList.toArray(new Question[0]);
  }

  @Override
  public boolean insertQuestion(Question q) {
    String insertSql = String.format(INSERT_SQL, q.getCategory().getName(), q
        .getSubject().getLevel(), q.getSubject().getTopic(), q.getSubject()
        .getName(), q.getQuestion(), q.getHint(), q.getAnswer());

    try {
      statement = getConnection().createStatement();
      int row = statement.executeUpdate(insertSql);
      
      if(row > 0){
        return true;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      DbUtil.close(statement);
    }

    return false;
  }
}
