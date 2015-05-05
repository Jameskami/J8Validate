package java8.validation;
import java.util.ArrayList;
import java.util.List;

public class J8ToValidate<T> {
	private List<T> items = new ArrayList<T>();
	protected J8ValidationResult result = new J8ValidationResult();
	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
}
