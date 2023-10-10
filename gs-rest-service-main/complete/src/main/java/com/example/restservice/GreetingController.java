package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*In Spring’s approach to building RESTful web services,
HTTP requests are handled by a controller.
These components are identified by the @RestController*/
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	/*@GetMapping annotation ensures that HTTP GET requests
	to /greeting are mapped to the greeting()*/
	/*
	There are companion annotations for other HTTP verbs (e.g. @PostMapping for POST).
	There is also a @RequestMapping annotation that they all derive from,
	and can serve as a synonym (e.g. @RequestMapping(method=GET)).*/
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	/*@RequestParam binds the value of the query string parameter name into the name parameter
	of the greeting() method.
	If the name parameter is absent in the request, the defaultValue of World is used.*/

	/*AtomicLong s initail value is 0 and incremented by 1*/

	/*A key difference between a traditional MVC controller and the RESTful web service controller
	shown earlier is the way that the HTTP response body is created.
	Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML,
	this RESTful web service controller populates and returns a Greeting object.
	The object data will be written directly to the HTTP response as JSON*/

	/*This code uses Spring @RestController annotation,
	which marks the class as a controller where every method returns a domain object instead of a view.
	It is shorthand for including both @Controller and @ResponseBody*/

	/*The Greeting object must be converted to JSON.
	Thanks to Spring’s HTTP message converter support,
	you need not do this conversion manually.
	Because Jackson 2 is on the classpath,
	Spring’s MappingJackson2HttpMessageConverter is automatically chosen to convert the
	Greeting instance to JSON.*/


	/*@SpringBootApplication is a convenience annotation that adds all of the following:
	@Configuration: Tags the class as a source of bean definitions for the application context.
	@EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings,
	other beans, and various property settings. For example, if spring-webmvc is on the classpath,
	this annotation flags the application as a web application and activates key behaviors,
	such as setting up a DispatcherServlet.
	@ComponentScan: Tells Spring to look for other components, configurations, and services
	in the com/example package, letting it find the controllers.*/

	/*Notice also how the id attribute has changed from 1 to 2.
	This proves that you are working against the same GreetingController
	instance across multiple requests and that its counter field is being
	incremented on each call as expected.*/

	/*please refer the test package also*/
}
