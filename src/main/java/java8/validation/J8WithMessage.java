package java8.validation;

public class J8WithMessage<T> {
	protected J8ValidationResult result = new J8ValidationResult();
	
	public J8WithSeverity<T> withMessage(String message) {
		J8ValidationResult nextResult = new J8ValidationResult();
		J8WithSeverity<T> severity = new J8WithSeverity<T>();
		if(!result.isValid) {
			nextResult.isValid = result.isValid;
			nextResult.errors.put(message, J8ValidationResult.Severity.Warning);
		}
		severity.result = nextResult;
		return severity;
	}
}
