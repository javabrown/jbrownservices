package com.jbrown.errors;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BrownErrors implements BrownErrorsI {
	private Map<String, List<BrownMessageI>> _brownErrors;

	@Override
	public boolean addError(BrownMessageI error) {
		insertError(error, BROWN_TAG);
		return false;
	}

	@Override
	public boolean add(String errorKey, Object... args) {
		assert (args != null);
		Set<String> errorSet = extractErrorTag(args);
		Object[] messageArgs = extractMessageArgs(errorSet.size() + 1, args);
		BrownMessageI message = new BrownMessage(errorKey, messageArgs);
		if (errorSet.size() == 0) {
			errorSet = Collections.singleton(BROWN_TAG);
		}
		return addError(message);
	}

	@Override
	public void clear() {
		if (_brownErrors != null)
			_brownErrors.clear();
	}
	
	@Override
	public int nErrors() {
		if (_brownErrors == null) {
			return 0;
		} else {
			int numErrors = 0;
			for (String errorTag : _brownErrors.keySet()) {
				numErrors += _brownErrors.get(errorTag).size();
			}
			return numErrors;
		}
	}
	
	@Override
	public List<BrownMessageI> getErrorMessages() {
		if (_brownErrors == null) {
			return Collections.EMPTY_LIST;
		} else {
			List<BrownMessageI> errors = new LinkedList<BrownMessageI>();
			for (String tag : _brownErrors.keySet()) {
				errors.addAll(_brownErrors.get(tag));
			}
			return errors;
		}
	}

	private void insertError(BrownMessageI brownMessage, String errorTagtName) {
		if (brownMessage == null || brownMessage.getKey() == null
				|| brownMessage.getKey().trim().length() == 0) {
			throw new RuntimeException("added null/empty message to errors");
		}
		if (_brownErrors == null)
			_brownErrors = new HashMap<String, List<BrownMessageI>>();
		List<BrownMessageI> errors = _brownErrors.get(errorTagtName);
		if (errors == null) {
			_brownErrors.put(errorTagtName,
					errors = new LinkedList<BrownMessageI>());
		}
		errors.add(brownMessage);
	}

	private static Set<String> extractErrorTag(Object... args) {
		if (args.length == 0 || args[0] == null) {
			return Collections.EMPTY_SET;
		} else {
			Set<String> errorWidgets = new HashSet<String>();
			for (Object arg : args) {
				if (arg == null) {
					break;
				} else {
					errorWidgets.add((String) arg);
				}
			}
			return errorWidgets;
		}
	}

	private static Object[] extractMessageArgs(int index, Object... args) {
		Object[] emptyArray = new Object[0];
		return (index >= args.length) ? emptyArray : Arrays.copyOfRange(args,
				index, args.length);
	}
}