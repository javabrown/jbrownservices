package com.jbrown.web.ws.responder;

import java.util.HashMap;
import java.util.Map;

import com.jbrown.cache.BrownCache;
import com.jbrown.core.util.BrownAuthUtil;
import com.jbrown.db.dao.QuestionDao;
import com.jbrown.errors.BrownErrorsI;
import com.jbrown.quiz.questions.model.Question;
import com.jbrown.user.BrownUserI;
import com.jbrown.web.ws.BrownRequestI;
import com.jbrown.web.ws.Responder;

public class QuizAllQuestionsResponder extends Responder {
  @Override
  protected Map<String, Object> perform(BrownRequestI request) {
    Question[] questions = this.getAllQuestionsFromCache(request);

    Map<String, Object> result = new HashMap<String, Object>();
    result.put(RESPONSE_K, questions != null);
    result.put(QUESTIONS_K, questions);

    return result;
  }

  @Override
  protected BrownErrorsI validate(BrownRequestI request) {
    BrownErrorsI errors = request.getErrors();
 
//    String email = (String) request.get(EMAIL_K);
//    String password = (String) request.get(PASSWORD_K);
//    String authCode = (String) request.get(AUTH_CODE_K);
//
//    if (isEmpty(email, password) &&  isEmpty(authCode)) {
//      errors.add(String.format(
//          "Mandatory Fields Missing :[%s | %s ] OR [ %s ]", EMAIL_K, 
//          PASSWORD_K, AUTH_CODE_K));
//    } else if (isUnsafeString(email, password, authCode)) {
//      errors.add(String.format("Special chars not allowed:",
//          StringUtil.UNSAFE_STRING));
//    } else if (!StringUtil.isEmpty(email) && !StringUtil.isValidEmail(email)) {
//      errors.add(String.format("Invalid Email Format %s", email));
//    }
//    else if(isUserAlreadyLogin(request)){
//      String code = BrownAuthUtil.getBrownUser(request).getEncryptedKey();
//      errors.add(String.format("Already authenticated. %s=%s", AUTH_CODE_K, code));
//    }

    return errors;
  }

  private boolean isUserAlreadyLogin(BrownRequestI req) {
    BrownUserI user = BrownAuthUtil.getBrownUser(req);
   
        
    if (user != null) {
      return true;
    }

    return false;
  }
  
  private Question[] getAllQuestionsFromCache(BrownRequestI req) {
    Question[] questions = (Question[]) BrownCache.getInstance().get(ALL_QUIZ_QUESTIONS_K);
    
    if (questions == null || questions.length == 0) {
      questions = getAllQuestionsFromDB(req);
      return questions;
    }
    
    return null;
  }
  
  private Question[] getAllQuestionsFromDB(BrownRequestI req) {
    Question[] questions = new QuestionDao(req).getAllQuestions();
    
    if (questions != null && questions.length > 0) {
       BrownCache.getInstance().set(ALL_QUIZ_QUESTIONS_K, questions);
      return questions;
    }
    
    return null;
  }
}