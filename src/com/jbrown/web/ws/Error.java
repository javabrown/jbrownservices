package com.jbrown.web.ws;

public class Error {
	private int _errorNumber;
	private String _errorMsg;

	public Error(int errorNumber, String errorMsg) {
		this._errorNumber = errorNumber;
		this._errorMsg = errorMsg;
	}

	public int getErrorNumber() {
		return _errorNumber;
	}

	public String getErrorMsg() {
		return _errorMsg;
	}

	@Override
	public String toString() {
		return "Error [_errorNumber=" + _errorNumber + ", _errorMsg="
				+ _errorMsg + "]";
	}
}
