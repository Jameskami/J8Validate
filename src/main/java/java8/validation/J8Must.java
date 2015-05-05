package java8.validation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class J8Must<T> {
	private List<T> items = new ArrayList<T>();
	
	public J8WithMessage<T> must(Predicate<T> predicate) {
		boolean isValid = true;
		if(!items.stream().allMatch(predicate)) {
			isValid = false;
		}
		J8WithMessage<T> withMessage = new J8WithMessage<T>();
		withMessage.result.isValid = isValid;
		return withMessage;
	}
	
	public J8WithMessage<T> mustNot(Predicate<T> predicate) {
		boolean isValid = true;
		if(items.stream().anyMatch(predicate)) {
			isValid = false;
		}
		J8WithMessage<T> withMessage = new J8WithMessage<T>();
		withMessage.result.isValid = isValid;
		return withMessage;
	}

	public J8WithMessage<T> CustomMust(boolean isCustomMethodReturnTrue) {
		boolean isValid = true;
		if(!isCustomMethodReturnTrue) {
			isValid = false;
		}
		J8WithMessage<T> withMessage = new J8WithMessage<T>();
		withMessage.result.isValid = isValid;
		return withMessage;
	}
	
	List<T> getItems() {
		return items;
	}

	void setItems(List<T> items) {
		this.items = items;
	}
}
