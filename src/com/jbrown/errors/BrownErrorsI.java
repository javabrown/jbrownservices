package com.jbrown.errors;

import java.util.List;

public interface BrownErrorsI {
   String BROWN_TAG = "*BROWN-ERROR*";
   boolean addError(BrownMessageI error);
   boolean add(String errorKey, Object... args);
   void clear();
   List<BrownMessageI> getErrorMessages();
   int nErrors();
}
