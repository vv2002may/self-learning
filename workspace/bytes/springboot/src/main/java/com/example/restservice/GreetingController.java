package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

 /**
  * This function generates a personalized greeting message based on the provided name.
  * If no name is provided, it defaults to "World".
  *
  * @param name The name of the person to be greeted.
  * @return A Greeting object containing a unique ID and the personalized greeting message.
  */
 @GetMapping("/greeting")
 public String learningisfun(@RequestParam(value = "name", defaultValue = "World") String name) {
     System.out.println("Learn By Doing");
     Greeting greetingMessage = new Greeting(counter.incrementAndGet(), String.format(template, name));
     return greetingMessage.toString();
 }

	@PostMapping("/json-request")
	public String processJson(@RequestBody CreditCard creditCard) {
		System.out.println("Thank you for providing your credit card details."
			+ "We promise to take care of all your purchases from now on ;-) ");
		System.out.println(creditCard);
		return "You are the true Santa! "+creditCard;
	}

	// Handy curl request.
	// curl --header "Content-Type: application/json" \
	//   --request POST \
	//   --data '{"name":"Santa","creditCardNumber":"0202-2323-2323-1999"}' \
	//   http://localhost:8081/json-request
}
