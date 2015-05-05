package java8.validation;

import java.util.List;


public abstract class AbstractJ8Validator<T> {
	protected IJ8Validator<T> validator = new J8Validator<T>();
	public abstract J8ValidationResult validate(List<T> items);
}
