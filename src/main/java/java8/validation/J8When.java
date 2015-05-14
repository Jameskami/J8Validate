package java8.validation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class J8When<T, R> {
	private List<T> items = new ArrayList<T>();
	protected J8ValidationResult<R> result = new J8ValidationResult<R>();
	
	public J8Must<T, R> when(Predicate<T> predicate) {
		J8Must<T, R> must = new J8Must<T, R>();
		must.setItems(items.stream().filter(predicate).collect(Collectors.toList()));
		must.result = new J8ValidationResult<R>(result);
		return must;
	}
	
	public J8Must<T, R> all() {
		J8Must<T, R> must = new J8Must<T, R>();
		must.setItems(items);
		must.result = new J8ValidationResult<R>(result);
		return must;
	}
	
	List<T> getItems() {
		return items;
	}

	void setItems(List<T> items) {
		this.items = items;
	}
}
