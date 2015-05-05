package java8.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class J8Validator<T> implements IJ8Validator<T> {
	private List<T> defendants = new ArrayList<T>();
	private List<T> filteredDefendants = new ArrayList<T>();
	private boolean isMessageApplicable;
	private boolean isWhen;
	protected J8ValidationResult result = new J8ValidationResult();
	Severity severity = Severity.Warning;
	
	public J8ValidationResult toValidate() {
		return result;
	}
	
	public J8Validator<T> fromList(List<T> defendants) {
		this.defendants = defendants;
		reset();
		return this;
	}
	
	public J8Validator<T> from(T defendant) {
		List<T> wrapper = new ArrayList<T>();
		wrapper.add(defendant);
		defendants = wrapper;
		reset();
		return this;
	}
	
	public J8Validator<T> when(Predicate<T> predicate) {
		filteredDefendants = defendants.stream().filter(predicate).collect(Collectors.toList());
		isWhen = true;
		return this;
	}

	public J8Validator<T> must(Predicate<T> predicate) {
		if(isWhen && !filteredDefendants.stream().allMatch(predicate)) {
			isMessageApplicable = true;
			result.isValid = false;
		}
		else if(!isWhen && !defendants.stream().allMatch(predicate)) {
			isMessageApplicable = true;
			result.isValid = false;
		}
		return this;
	}
	
	public J8Validator<T> mustNot(Predicate<T> predicate) {
		if(isWhen && filteredDefendants.stream().anyMatch(predicate)) {
			isMessageApplicable = true;
			result.isValid = false;
		}
		if(!isWhen && defendants.stream().anyMatch(predicate)) {
			isMessageApplicable = true;
			result.isValid = false;
		}
		return this;
	}

	public J8Validator<T> withMessage(String message) {
		if(!isMessageApplicable) return this;
		message = message == null ? "" : message;
		result.errors.put(message, mapSeverity());
		isMessageApplicable = false;
		severity = Severity.Warning;
		return this;
	}

	public J8Validator<T> withSeverity(Severity severity) {
		this.severity = severity;
		return this;
	}
	
	public J8Validator<T>  noNulls() {
		if(isWhen && filteredDefendants.stream().anyMatch( t -> t == null)) {
			isMessageApplicable = true;
		}
		else if(!isWhen && defendants.stream().anyMatch( t -> t == null)) {
			isMessageApplicable = true;
		}
		return this;
	}
	
	private J8ValidationResult.Severity mapSeverity() {
		J8ValidationResult.Severity severityResult = J8ValidationResult.Severity.Warning;
		switch(severity) {
			case Warning:
				severityResult = J8ValidationResult.Severity.Warning;
				break;
			case Critical:
				severityResult = J8ValidationResult.Severity.Critical;
				break;
			case Fatal:
				severityResult = J8ValidationResult.Severity.Fatal;
				break;
		}
		return severityResult;
	}
	
	private void reset() {
		isMessageApplicable = false;
		severity = Severity.Warning;
		result.isValid = true;
		isWhen = false;
	}
	
	public enum Severity {
		Warning,
		Critical,
		Fatal
	}

	public J8Validator<T> customMust(boolean returned) {
		if(!returned) {
			isMessageApplicable = true;
			result.isValid = false;
		}
		return this;
	}
}
