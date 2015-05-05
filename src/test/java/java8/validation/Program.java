package java8.validation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import java8.validation.J8ValidationResult;


public class Program {
	SnakeValidator validator = new SnakeValidator();
	List<Snake> snakes = new ArrayList<Snake>();
	List<Snake> petSnakes = new ArrayList<Snake>();
	public Program() {
		for (int i = 0; i < 20; i++) {
			Snake snake = new Snake();
			snakes.add(new Snake());
			if(snake.isVenomous()) continue;
			petSnakes.add(snake);
		}
	}
	
	@Test
	public void isValidListsTest() throws Exception {
		J8ValidationResult result = validator.validateLists(snakes, petSnakes);
		assertTrue(!result.isValid);
	}
	@Test
	public void isValidMust() {
		Snake snake = petSnakes.get(2);
		J8ValidationResult result = validator.validateSnake(snake);
		assertTrue(result.isValid);
	}
}
