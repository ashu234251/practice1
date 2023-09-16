package service;

import dao.IPersonDao;
import dto.Person;
import factory.PersonDaoFactory;

public class PersonServiceImpl implements IPersonService {

	IPersonDao personDao;
	@Override
	public String register(Person person) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.register(person);
	}
	@Override
	public String login(Person person) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.login(person);
	}
	@Override
	public String deposit(Person person, Double updatedAmount) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.deposit(person, updatedAmount);
	}
	
	@Override
	public Person getPerson(Integer aNo) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.getPerson(aNo);
	}


	
}
