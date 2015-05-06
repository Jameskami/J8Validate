package java8.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class J8ValidationResult {
	public boolean isValid = true;
	boolean isMessageNeeded = false;
	public Map<String, Severity> errors = new HashMap<String, Severity>();
	protected String message = "";
	
	public J8ValidationResult(){}
	public J8ValidationResult(J8ValidationResult other) {
		isValid = other.isValid;
		for (Map.Entry<String, J8ValidationResult.Severity> entry : other.errors.entrySet()) {
			errors.put(entry.getKey(), entry.getValue());
		}
	}
	
	public List<String> getErrorMessages() {
		List<String> errorMsgs = new ArrayList<String>();
		for(Map.Entry<String, Severity> entry : errors.entrySet()) {
			errorMsgs.add(entry.getKey());
		}
		return errorMsgs;
	}
	
	public List<String> getErrorMessagesBySeverity(Severity severity) {
		List<String> errorMsgs = new ArrayList<String>();
		for(Map.Entry<String, Severity> entry : errors.entrySet()) {
			if(entry.getValue() == severity) {
				errorMsgs.add(entry.getKey());
			}
		}
		return errorMsgs;
	}
	
	public boolean anyFatal() {
		for(Map.Entry<String, Severity> entry : errors.entrySet()) {
			if(entry.getValue() == Severity.Fatal) {
				return true;
			}
		}
		return false;
	}
	
	public boolean anyCritical() {
		for(Map.Entry<String, Severity> entry : errors.entrySet()) {
			if(entry.getValue() == Severity.Critical) {
				return true;
			}
		}
		return false;
	}
	
	public boolean anyWarning() {
		for(Map.Entry<String, Severity> entry : errors.entrySet()) {
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
