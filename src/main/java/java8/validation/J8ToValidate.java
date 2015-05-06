package java8.validation;

public class J8ToValidate<T> {
	protected J8ValidationResult result = new J8ValidationResult();
	public J8ValidationResult toValidate() {
		return result;
	}
}
