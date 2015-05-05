package java8.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class AbstractJ8Validator<T> {
	protected IJ8Validator<T> validator = new J8Validator<T>();
	public abstract J8ValidationResult validate(List<T> items);
	
	public J8When<T> from(T item, J8ValidationResult result) {
		J8When<T> when = new J8When<T>();
		List<T> items = new ArrayList<T>();
		items.add(item);
		when.setItems(items);
		return when;
	}
	
	public J8When<T> from(List<T> items, J8ValidationResult result) {
		J8When<T> when = new J8When<T>();
		when.setItems(items);
		return when;
	}
	static J8ValidationResult copyResult(J8ValidationResult original) {
		J8ValidationResult newResult = new J8ValidationResult();
		newResult.isValid = original.isValid;
		for (Map.Entry<String, J8ValidationResult.Severity> entry : original.errors.entrySet()) {
			newResult.errors.put(entry.getKey(), entry.getValue());
		}
		return newResult;
	}
}
