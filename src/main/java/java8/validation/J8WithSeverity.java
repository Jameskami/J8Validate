package java8.validation;


public class J8WithSeverity<T> {
	J8ValidationResult result = new J8ValidationResult();
	
	public J8ToValidate<T> warning() {
		J8ToValidate<T> toValidate = new J8ToValidate<T>();
		toValidate.result = setResult(J8ValidationResult.Severity.Warning);
		return toValidate;
	}
	
	public J8ToValidate<T> critical() {
		J8ToValidate<T> toValidate = new J8ToValidate<T>();
		toValidate.result = setResult(J8ValidationResult.Severity.Critical);
			return toValidate;
	}
	
	public J8ToValidate<T> fatal() {
		J8ToValidate<T> toValidate = new J8ToValidate<T>();
		toValidate.result = setResult(J8ValidationResult.Severity.Fatal);
		return toValidate;
	}
	
	private J8ValidationResult setResult(J8ValidationResult.Severity severity) {
		J8ValidationResult newResult = new J8ValidationResult(result);
		if(result.isMessageNeeded) {
			newResult.errors.put(result.message, severity);
		}
		return newResult;
	}
}
