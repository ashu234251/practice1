package dao;

import dto.Person;

public interface IPersonDao {


	//registering a record
	String register(Person person);
	
	//for login 
	String login(Person person);
	
	//for deposit
	String deposit(Person person, Double updatedAmount);
	
	Person getPerson(Integer aNo);
}
