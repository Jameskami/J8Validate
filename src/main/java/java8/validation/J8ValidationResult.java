package java8.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class J8ValidationResult<T> {
	public boolean isValid = true;
	boolean isMessageNeeded = false;
	public Map<T, Severity> errors = new HashMap<T, Severity>();
	protected T message;
	
	public J8ValidationResult(){}
	public J8ValidationResult(J8ValidationResult<T> other) {
		isValid = other.isValid;
		for (Map.Entry<T, J8ValidationResult.Severity> entry : other.errors.entrySet()) {
			errors.put(entry.getKey(), entry.getValue());
		}
	}
	
	public List<T> getErrorMessages() {
		List<T> errorMsgs = new ArrayList<T>();
		for(Map.Entry<T, Severity> entry : errors.entrySet()) {
			errorMsgs.add(entry.getKey());
		}
		return errorMsgs;
	}
	
	public List<T> getErrorMessagesBySeverity(Severity severity) {
		List<T> errorMsgs = new ArrayList<T>();
		for(Map.Entry<T, Severity> entry : errors.entrySet()) {
			if(entry.getValue() == severity) {
				errorMsgs.add(entry.getKey());
			}
		}
		return errorMsgs;
	}
	
	public boolean anyFatal() {
		for(Map.Entry<T, Severity> entry : errors.entrySet()) {
			if(entry.getValue() == Severity.Fatal) {
				return true;
			}
		}
		return false;
	}
	
	public boolean anyCritical() {
		for(Map.Entry<T, Severity> entry : errors.entrySet()) {
			if(entry.getValue() == Severity.Critical) {
				return true;
			}
		}
		return false;
	}
	
	public boolean anyWarning() {
		for(Map.Entry<T, Severity> entry : errors.entrySet()) {
			if(entry.getValue() == Severity.Warning) {
				return true;
			}
		}
		return false;
	}
	
	public enum Severity {
		Info,
		Warning,
		Critical,
		Fatal
	}
}
