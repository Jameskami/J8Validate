package java8.validation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import java8.validation.J8ValidationResult;


public class MainTest {
	SnakeValidator validator = new SnakeValidator();
	List<Snake> snakes = new ArrayList<Snake>();
	List<Snake> petSnakes = new ArrayList<Snake>();
	public MainTest() {
		for (int i = 0; i < 20; i++) {
			Snake snake = new Snake();
			snakes.add(new Snake());
			if(snake.isVenomous()) continue;
			petSnakes.add(snake);
		}
	}
	
	@Test
	public void isCustomMessageTest() throws Exception {
		SnakeValidator validator = new SnakeValidator();
		
		J8ValidationResult<CustomMessage> result = new J8ValidationResult<CustomMessage>();
		result = validator.customMsg(petSnakes, result);
		
		for(CustomMessage msg : result.getErrorMessages()) {
			assertTrue(msg.getCode() == 2344);
			assertTrue(msg.getId() == 126574876);
			assertTrue(msg.getMessage().equals("No mutant constrictors 100 feet or longer :("));
			assertNotNull(msg.getDate());
		}
	}
	
	@Test
	public void isResultMessageValidTest() throws Exception {
		J8ValidationResult<String> result = validator.validateLists(snakes, petSnakes);
		boolean isMessageNotEmpty = true;
		for(String msg : result.getErrorMessages()) {
			if(msg == null || msg.length() < 1) {
				isMessageNotEmpty = false;
			}
		}
		assertTrue(isMessageNotEmpty);
	}
	
	@Test
	public void isValidMust() {
		Snake snake = petSnakes.get(2);
		J8ValidationResult<String> result = validator.validateSnake(snake);
		assertTrue(result.isValid);
	}
	
	@Test
	public void isValidMustNot() {
		Snake snake = petSnakes.get(2);
		J8ValidationResult<String> result = validator.validateMustNotSnake(snake);
		assertTrue(result.isValid);
	}
	
}
