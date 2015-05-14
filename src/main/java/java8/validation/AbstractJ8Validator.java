package java8.validation;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractJ8Validator<T> {
	
	//public abstract <R> J8ValidationResult<R> validate(List<T> items);
	
	public <R> J8When<T, R> from(T item, J8ValidationResult<R> result) {
		J8When<T, R> when = new J8When<T, R>();
		List<T> items = new ArrayList<T>();
		items.add(item);
		when.setItems(items);
		when.result = new J8ValidationResult<R>(result);
		when.result.isMessageNeeded = false;
		return when;
	}
	
	public <R> J8When<T, R> from(List<T> items, J8ValidationResult<R> result) {
		J8When<T, R> when = new J8When<T, R>();
		when.setItems(items);
		when.result = new J8ValidationResult<R>(result);
		when.result.isMessageNeeded = false;
		return when;
	}
	
//	public <R> J8When<T, R> from(T item) {
//		J8When<T, R> when = new J8When<T, R>();
//		List<T> items = new ArrayList<T>();
//		items.add(item);
//		when.setItems(items);
//		when.result = new J8ValidationResult<R>();
//		when.result.isMessageNeeded = false;
//		return when;
//	}
//	
//	public <R> J8When<T, R> from(List<T> items) {
//		J8When<T, R> when = new J8When<T, R>();
//		when.setItems(items);
//		when.result = new J8ValidationResult<R>();
//		when.result.isMessageNeeded = false;
//		return when;
//	}
}
