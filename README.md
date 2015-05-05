### J8Validate is a small validation library that allows lambda expressions and method chaining to write concise and decoupled validation.

#####Creating a validator class:
There are four types to be aware of:
Type | Description
-----------------------|-------------------------------------------------------------------
AbstractJ8Validator<T> | any validator class should inherit from this class
J8Validator<T> | implements the validation methods
IJ8Validator<T> | J8Validator<T> interface with AbstractJ8Validator<T>
J8ValidationResult | holds errors and has various methods to be returned after validation

Step one: create a class to validate a type by inheriting from AbstractJ8Validator<T>.
You must override the validate method that takes a list of the chosen type, but more validation methods can be added as desired.
Step two: use the "validator" field inherited from the abstract class to validate the arguments passed into the validate method.

#####IJ8Validator<T> Interface Methods:
Method | Description
--------------|---------------------------------------
from | a single object to validate
fromList | a list to validate
withSeverity | default is warning
when | filter based on a predicate
must | all objects should pass a predicate check
mustNot | No object should be true for the predicate
customMust | takes a boolean. Should be true
noNulls | no object should be null
withMessage | if any failures, add error message
toValidate | returns a validation result

customMust allows for more complicate validation that takes multiple arguments. Example:
validator.from(list).customMust(veryComplexValidation(list, 3, 87, true, "propName"))
.withMessage("Warning: failed").toValidate()


####Example console program:

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
		
		J8ValidationResult result = validator.validateLists(snakes, petSnakes);
		
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



###Validator class

```java
public class SnakeValidator extends AbstractJ8Validator<Snake> {
	private final String dangerousSnakes = "Warning: dangerous snakes.";
	
	public J8ValidationResult validateLists(List<Snake> snakes, List<Snake> petSnakes) {
		J8ValidationResult result = validator
				.fromList(snakes)
				.withSeverity(J8Validator.Severity.Critical)
				.when(snake -> snake.isConstrictor() && snake.isVenomous())
				.withMessage("Snakes should not be both constrictors and venomous.")
				.fromList(petSnakes)
				.withSeverity(J8Validator.Severity.Critical)
				.mustNot(snake -> snake.isVenomous())
				.withMessage("Pet snakes should not be venomous.")
				.fromList(snakes)
				.mustNot(snake -> snakes.size() + petSnakes.size() > 15)
				.withMessage("I HAVE HAD IT WITH THESE m%th#rf^ck^&g SNAKES ON THIS m%th#rf^ck^&g PLANE")
				.fromList(snakes)
				.mustNot(this::validateDangerousSnakes)
				.withMessage(dangerousSnakes)
				.toValidate();
		return result;
	}

	@Override
	public J8ValidationResult validate(List<Snake> items) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean validateDangerousSnakes(Snake snake) {
		if(snake.getLength() >= 10 && snake.isVenomous() || snake.isConstrictor()) return true;
		return false;
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