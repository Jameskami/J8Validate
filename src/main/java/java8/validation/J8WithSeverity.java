package java8.validation;


public class J8WithSeverity<T, R> {
	J8ValidationResult<R> result = new J8ValidationResult<R>();
	
	public J8ToValidate<T, R> info() {
		J8ToValidate<T, R> toValidate = new J8ToValidate<T, R>();
		toValidate.result = setResult(J8ValidationResult.Severity.Info);
		return toValidate;
	}
	
	public J8ToValidate<T, R> warning() {
		J8ToValidate<T, R> toValidate = new J8ToValidate<T, R>();
		toValidate.result = setResult(J8ValidationResult.Severity.Warning);
		return toValidate;
	}
	
	public J8ToValidate<T, R> critical() {
		J8ToValidate<T, R> toValidate = new J8ToValidate<T, R>();
		toValidate.result = setResult(J8ValidationResult.Severity.Critical);
			return toValidate;
	}
	
	public J8ToValidate<T, R> fatal() {
		J8ToValidate<T, R> toValidate = new J8ToValidate<T, R>();
		toValidate.result = setResult(J8ValidationResult.Severity.Fatal);
		return toValidate;
	}
	
	private J8ValidationResult<R> setResult(J8ValidationResult.Severity severity) {
		J8ValidationResult<R> newResult = new J8ValidationResult<R>(result);
		if(result.isMessageNeeded) {
			newResult.errors.put(result.message, severity);
		}
		return newResult;
	}
}
