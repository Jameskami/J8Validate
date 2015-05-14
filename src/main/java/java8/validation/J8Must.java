package java8.validation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class J8Must<T, R> {
	private List<T> items = new ArrayList<T>();
	protected J8ValidationResult<R> result = new J8ValidationResult<R>();
	
	public J8WithMessage<T, R> must(Predicate<T> predicate) {
		J8WithMessage<T, R> withMessage = new J8WithMessage<T, R>();
		withMessage.result = new J8ValidationResult<R>(result);
		if(!items.stream().allMatch(predicate)) {
			withMessage.result.isValid = false;
			withMessage.result.isMessageNeeded = true;
		}
		return withMessage;
	}
	
	public J8WithMessage<T, R> mustNot(Predicate<T> predicate) {
		J8WithMessage<T, R> withMessage = new J8WithMessage<T, R>();
		withMessage.result = new J8ValidationResult<R>(result);
		if(items.stream().anyMatch(predicate)) {
			withMessage.result.isValid = false;
			withMessage.result.isMessageNeeded = true;
		}
		return withMessage;
	}

	public J8WithMessage<T, R> CustomMust(boolean isCustomMethodReturnTrue) {
		J8WithMessage<T, R> withMessage = new J8WithMessage<T, R>();
		withMessage.result = new J8ValidationResult<R>(result);
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
