package java8.validation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class J8When<T> {
	private List<T> items = new ArrayList<T>();
	protected J8ValidationResult result = new J8ValidationResult();
	
	public J8Must<T> when(Predicate<T> predicate) {
		J8Must<T> must = new J8Must<T>();
		must.setItems(items.stream().filter(predicate).collect(Collectors.toList()));
		must.result = new J8ValidationResult(result);
		return must;
	}
	
	public J8Must<T> all() {
		J8Must<T> must = new J8Must<T>();
		must.setItems(items);
		must.result = new J8ValidationResult(result);
		return must;
	}
	
	List<T> getItems() {
		return items;
	}

	void setItems(List<T> items) {
		this.items = items;
	}
}
