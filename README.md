### J8Validate is a small validation library that allows lambda expressions and method chaining to write clean and concise code.

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
		
		J8ValidationResult result = validator.validate(snakes);
		
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