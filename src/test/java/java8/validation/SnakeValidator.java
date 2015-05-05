package java8.validation;

import java.util.List;


public class SnakeValidator extends AbstractJ8Validator<Snake> {
	private final String dangerousSnakes = "Warning: dangerous snakes.";
	
	public J8ValidationResult validateLists(List<Snake> snakes, List<Snake> petSnakes) {
		J8ValidationResult result = validator
				.fromList(snakes)
				.withSeverity(J8Validator.Severity.Critical)
				.when(snake -> snake.isConstrictor() && snake.isVenomous())
				.withMessage("Snakes should not be both constrictors and venomous.")
				.fromList(petSnakes)
				.mustNot(snake -> snake.isVenomous())
				.withMessage("Pet snakes should not be venomous.")
				.fromList(snakes)
				.mustNot(snake -> snakes.size() + petSnakes.size() > 15)
				.withSeverity(J8Validator.Severity.Fatal)
				.withMessage("I HAVE HAD IT WITH THESE m%th#rf^ck^&g SNAKES ON THIS m%th#rf^ck^&g PLANE")
				.fromList(snakes)
				.mustNot(this::validateDangerousSnakes)
				.withMessage(dangerousSnakes)
				.toValidate();
		return result;
	}
	
	public J8ValidationResult validateSnake(Snake snake) {
		return validator.from(snake).must(s->!s.isVenomous())
				.withMessage("Your pet is poisonous.").toValidate();
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
