package java8.validation;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractJ8Validator<T> {
	
	public abstract J8ValidationResult validate(List<T> items);
	
	public J8When<T> from(T item, J8ValidationResult result) {
		J8When<T> when = new J8When<T>();
		List<T> items = new ArrayList<T>();
		items.add(item);
		when.setItems(items);
		when.result = new J8ValidationResult(result);
		when.result.isMessageNeeded = false;
		return when;
	}
	
	public J8When<T> from(List<T> items, J8ValidationResult result) {
		J8When<T> when = new J8When<T>();
		when.setItems(items);
		when.result = new J8ValidationResult(result);
		when.result.isMessageNeeded = false;
		return when;
	}
	
	public J8When<T> from(T item) {
		J8When<T> when = new J8When<T>();
		List<T> items = new ArrayList<T>();
		items.add(item);
		when.setItems(items);
		when.result = new J8ValidationResult();
		when.result.isMessageNeeded = false;
		return when;
	}
	
	public J8When<T> from(List<T> items) {
		J8When<T> when = new J8When<T>();
		when.setItems(items);
		when.result = new J8ValidationResult();
		when.result.isMessageNeeded = false;
		return when;
	}
}
