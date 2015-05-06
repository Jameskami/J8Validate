package java8.validation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class J8Must<T> {
	private List<T> items = new ArrayList<T>();
	protected J8ValidationResult result = new J8ValidationResult();
	public J8WithMessage<T> must(Predicate<T> predicate) {
		J8WithMessage<T> withMessage = new J8WithMessage<T>();
		withMessage.result = new J8ValidationResult(result);
		if(!items.stream().allMatch(predicate)) {
			withMessage.result.isValid = false;
			withMessage.result.isMessageNeeded = true;
		}
		return withMessage;
	}
	
	public J8WithMessage<T> mustNot(Predicate<T> predicate) {
		J8WithMessage<T> withMessage = new J8WithMessage<T>();
		withMessage.result = new J8ValidationResult(result);
		if(items.stream().anyMatch(predicate)) {
			withMessage.result.isValid = false;
			withMessage.result.isMessageNeeded = true;
		}
		return withMessage;
	}

	public J8WithMessage<T> CustomMust(boolean isCustomMethodReturnTrue) {
		J8WithMessage<T> withMessage = new J8WithMessage<T>();
		withMessage.result = new J8ValidationResult(result);
		if(!isCustomMethodReturnTrue) {
			withMessage.result.isValid = false;
			withMessage.result.isMessageNeeded = true;
		}
		return withMessage;
	}
	
	List<T> getItems() {
		return items;
	}

	void setItems(List<T> items) {
		this.items = items;
	}
}
