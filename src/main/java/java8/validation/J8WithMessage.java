package java8.validation;

public class J8WithMessage<T, R> {
	protected J8ValidationResult<R> result = new J8ValidationResult<R>();
	
	public J8WithSeverity<T, R> withMessage(R message) {
		J8ValidationResult<R> nextResult = new J8ValidationResult<R>(result);
		J8WithSeverity<T, R> severity = new J8WithSeverity<T, R>();
		if(result.isMessageNeeded) {
			nextResult.message = message;
			nextResult.isMessageNeeded = true;
		}
		severity.result = nextResult;
		return severity;
	}
}
