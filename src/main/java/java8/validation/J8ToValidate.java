package java8.validation;

public class J8ToValidate<T, R> {
	protected J8ValidationResult<R> result = new J8ValidationResult<R>();
	public J8ValidationResult<R> toValidate() {
		return result;
	}
}
