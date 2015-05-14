package java8.validation;

import java.util.List;


public class SnakeValidator extends AbstractJ8Validator<Snake> {
	private final String dangerousSnakes = "Warning: dangerous snakes.";
	
	public J8ValidationResult<String> validateLists(List<Snake> snakes, List<Snake> petSnakes) {
		J8ValidationResult<String> result =
				from(snakes, new J8ValidationResult<String>())
				.all()
				.mustNot(snake -> snake.isConstrictor() && snake.isVenomous())
				.withMessage("Snakes should not be both constrictors and venomous.")
				.warning()
				.toValidate();
				
		result = from(petSnakes, result)
				.all()
				.mustNot(snake -> snake.isVenomous())
				.withMessage("Pet snakes should not be venomous.")
				.warning()
				.toValidate();
				
				
		result = from(snakes, result)
				.all()
				.mustNot(snake -> snakes.size() + petSnakes.size() > 10)
				.withMessage("I HAVE HAD IT WITH THESE m%th#rf^ck^&g SNAKES ON THIS m%th#rf^ck^&g PLANE")
				.fatal()
				.toValidate();
				
		result = from(snakes, result)
				.all()
				.mustNot(this::validateDangerousSnakes)
				.withMessage(dangerousSnakes)
				.warning()
				.toValidate();
		
		return result;
	}
	
	public J8ValidationResult<String> validateSnake(Snake snake) {
		return from(snake, new J8ValidationResult<String>()).all().must(s->!s.isVenomous())
				.withMessage("Your pet is poisonous.").critical().toValidate();
	}
	
	public J8ValidationResult<String> validateMustNotSnake(Snake snake) {
		return from(snake, new J8ValidationResult<String>()).all().mustNot(s->s.isVenomous())
				.withMessage("Your pet is poisonous.").warning().toValidate();
	}

	private boolean validateDangerousSnakes(Snake snake) {
		if(snake.getLength() >= 10 && snake.isVenomous() || snake.isConstrictor()) return true;
		return false;
	}
	
}
