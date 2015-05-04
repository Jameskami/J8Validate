import java.util.List;
import java.util.function.Predicate;


public interface IJ8Validator<T> {
	public J8ValidationResult toValidate();
	
	public J8Validator<T> fromList(List<T> defendants);
	
	public J8Validator<T> from(T defendant);
	
	public J8Validator<T> when(Predicate<T> predicate);

	public J8Validator<T> must(Predicate<T> predicate);
	
	public J8Validator<T> mustNot(Predicate<T> predicate);

	public J8Validator<T> withMessage(String message);

	public J8Validator<T> withSeverity(J8Validator.Severity severity);
	
	public J8Validator<T>  noNulls();
}
