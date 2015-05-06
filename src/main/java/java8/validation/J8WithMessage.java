package java8.validation;

public class J8WithMessage<T> {
	protected J8ValidationResult result = new J8ValidationResult();
	
	public J8WithSeverity<T> withMessage(String message) {
		J8ValidationResult nextResult = new J8ValidationResult(result);
		J8WithSeverity<T> severity = new J8WithSeverity<T>();
		if(result.isMessageNeeded) {
			nextResult.message = message;
			nextResult.isMessageNeeded = true;
		}
		severity.result = nextResult;
		return severity;
	}
}
