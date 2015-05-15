### J8Validate is a small validation library that allows lambda expressions and method chaining to write concise and decoupled validation.
####Validation can remain stateless and safe to use dependancy injection or threads.

####J8ValidationResult's message property is now generic, so if one needs more than what is supplied out of the box, a custome class can be passed as the message

#####Creating a validator class:
There are a few types to be aware of:

Type | Description
----------|--------------
AbstractJ8Validator | any validator class should inherit from this class
J8ValidationResult | holds errors and has various methods to be returned
J8When | returned by AbstractJ8Validator's from method. Filters objects to validate by predicate
J8Must | returned by J8When's methods. Takes a predicate to test objects validity
J8WithMessage | returned by J8Must. Adds a message to the errors if test fails.
J8WithSeverity | returned by J8WithMessage. Adds levels of severity to errors
J8ToValidate | returned by J8WithSeverity. Returns a J8ValidationResult object

Step one: create a class to validate a type by inheriting from AbstractJ8Validator<T>.
```java
public class MyValidator extends AbstractJ8Validator<MyType>
```
And create a method that returns a result:
```java
	public J8ValidationResult<String> validate(List<MyType> items, J8ValidationResult<String> result) {
		return result;
	}
```

Step two: use the from method to validate the arguments passed into the validate method.
```java
	public J8ValidationResult<String> validate(List<MyType> items, J8ValidationResult<String> result) {
		return
			from(items, result)
			.when(item -> item.isSomething() && item.values.Size() > 0)
			.must(item -> item.getParent() != null)
			.withMessage("Error: item(s) not valid.")
			.critical()
			.toValidate();
	}
```

#####Validation methods:
Method | Description
--------------|---------------------------------------
from | object(s) to validate
when | filter based on a predicate or all
all | no filter
must | all objects should pass a predicate check
mustNot | No object should be true for the predicate
customMust | takes a boolean. Should be true
withMessage | add error message if failure
info | adds info severity if failure
warning | adds warning severity if failure
critical | adds critical severity if failure
fatal | adds fatal severity if failure
toValidate | returns a validation result

customMust allows for more complicate validation that takes multiple arguments. Example:
```java
from(list).all().customMust(veryComplexValidation(list, 3, 87, true, "propName"))
.withMessage("Warning: failed").warning().toValidate()
```

##Example console program:


###Validator class

```java
public class SnakeValidator extends AbstractJ8Validator<Snake> {
	private final String dangerousSnakes = "Warning: dangerous snakes.";
	
	public J8ValidationResult validateLists(List<Snake> snakes, List<Snake> petSnakes, J8ValidationResult<String> validationResult) {
		J8ValidationResult result = 
				from(petSnakes, validationResult)
				.all()
				.must(snake -> !snake.isVenomous())
				.withMessage("Danger: pet snakes should not be venomous.")
				.critical()
				.toValidate();
		
		result =
				from(snakes, result)
				.when(snake -> snake.isConstrictor())
				.mustNot(snake -> snake.isVenomous())
				.withMessage("Alert: snakes should not be both constrictors and venomous.")
				.fatal()
				.toValidate();
		
		result =
				from(snakes, result)
				.all()
				.mustNot(this::validateDangerousSnakes)
				.withMessage("Warning: some snakes extremely lethal.")
				.warning()
				.toValidate();
				
		result =
				from(snakes, result)
				.all()
				.mustNot(snake -> snakes.size() + petSnakes.size() > 15)
				.withMessage("I HAVE HAD IT WITH THESE m%th#rf^ck^&g SNAKES ON THIS m%th#rf^ck^&g PLANE")
				.fatal()
				.toValidate();
		
		result = from(snakes, result)
				.all()
				.mustNot(snake -> snake.isVenomous())
				.withMessage("Warning: some poisonous snakes.")
				.warning()
				.toValidate();
		
		return result;
	}

	private boolean validateDangerousSnakes(Snake snake) {
		if(snake.getLength() >= 10 && snake.isVenomous() || snake.isConstrictor()) return true;
		return false;
	}
	
}
```

###Entry Point

```java
public class Program {
	
	public static void main(String[] args) throws Exception {
		Program program = new Program();
		
		List<Snake> snakes = new ArrayList<Snake>();
		List<Snake> petSnakes = new ArrayList<Snake>();
		
		for (int i = 0; i < 20; i++) {
			Snake snake = new Snake();
			snakes.add(new Snake());
			if(snake.isVenomous()) continue;
			petSnakes.add(snake);
		}
		
		SnakeValidator validator = new SnakeValidator();
		
		J8ValidationResult result = validator.validateLists(snakes, petSnakes, new J8ValidationResult<String>());
		
		result.getErrorMessages().forEach(err -> System.out.println(err));
		
		if(result.anyFatal()) {
			handleSnakeProblem();
		}
	}

	private static void handleSnakeProblem() {
		System.out.println("eject...");
	}
}
```

###Snake class


```java
public class Snake {
	private boolean isVenomous;
	private boolean isConstrictor;
	private int length;
	
	public boolean isVenomous() {
		return isVenomous;
	}
	
	public boolean isConstrictor() {
		return isConstrictor;
	}
	
	public int getLength() {
		return length;
	}
	
	public Snake() {
		Random rand = new Random();
		length = rand.nextInt(12);
		isVenomous = rand.nextInt(10) > 5? true : false;
		isConstrictor = !isVenomous && rand.nextInt(10) > 5? true : false;
	}
}
```