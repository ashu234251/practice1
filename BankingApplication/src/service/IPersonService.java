package service;

import dto.Person;

public interface IPersonService {

	//registering a person
		String register(Person person);
		
		//for login 
		String login(Person person);
		
		String deposit(Person person, Double updateAmount);
		
		Person getPerson(Integer aNo);
}
