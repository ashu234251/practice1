package in.ineuron.service;

import in.ineuron.dto.Person;

public interface IPersonService {

	//registering a person
		String register(Person person);
		
		//for login 
		String login(Person person);
		
		String deposit(Person person);
}
