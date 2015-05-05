package java8.validation;

import java.util.Map;
import java.util.Map.Entry;

public class J8WithSeverity<T> {
	protected J8ValidationResult result = new J8ValidationResult();
	
	public J8ToValidate<T> warning() {
		
		return null;
	}
	
	public J8ToValidate<T> critical() {
			
			return null;
		}
	
	public J8ToValidate<T> fatal() {
		
		return null;
	}
	
	private J8ValidationResult cloneAndSetResult(J8ValidationResult original, J8ValidationResult.Severity severity) {
		J8ValidationResult newResult = new J8ValidationResult();
		newResult.isValid = original.isValid;
		for (Map.Entry<String, J8ValidationResult.Severity> entry : original.errors.entrySet()) {
			newResult.errors.put(entry.getKey(), severity);
		}
		return newResult;
	}
}
