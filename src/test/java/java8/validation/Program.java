package java8.validation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import java8.validation.J8ValidationResult;


public class Program {
	
	@Test
	public void mainTest() throws Exception {		
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

	private void handleSnakeProblem() {
		System.out.println("eject...");
	}
}
