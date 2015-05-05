package java8.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class J8ValidationResult {
	public boolean isValid = true;
	public Map<String, Severity> errors = new HashMap<String, Severity>();
	
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
		Warning,
		Critical,
		Fatal
	}
}
