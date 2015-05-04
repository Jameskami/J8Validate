J8Validate is a small validation library that allows lambda expressions and method chaining to write clean and concise code.

Example console program:

import java.util.ArrayList;
import java.util.List;


public class Program {
	public static final String dangerousSnakes = "Warning: very dangerous snakes aboard";
	
	public static void main(String[] args) {
		Program program = new Program();
		
		List<Snake> snakes = new ArrayList<Snake>();
		List<Snake> petSnakes = new ArrayList<Snake>();
		
		for (int i = 0; i < 20; i++) {
			Snake snake = new Snake();
			snakes.add(new Snake());
			if(snake.isVenomous()) continue;
			petSnakes.add(snake);
		}
		
		J8Validator<Snake> validator = new J8Validator<Snake>();
		
		J8ValidationResult result = validator
				//from or fromList sets up the objects to be validated
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
				.mustNot(program::validateDangerousSnakes)
				.withMessage(dangerousSnakes)
				//finally, calling toValidate returns the result
				.toValidate();
		
		result.getErrorMessages().forEach(err -> System.out.println(err));
	}
	
	private boolean validateDangerousSnakes(Snake snake) {
		if(snake.getLength() >= 10 && snake.isVenomous() || snake.isConstrictor()) return true;
		return false;
	}
}



//Snake class

import java.util.Random;

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
