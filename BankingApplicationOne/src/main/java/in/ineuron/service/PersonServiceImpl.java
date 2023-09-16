package in.ineuron.service;

import in.ineuron.dao.IPersonDao;
import in.ineuron.dto.Person;
import in.ineuron.factory.PersonDaoFactory;

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
	@Override
	public String withdraw(Person person, Double updatedAmount) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.deposit(person, updatedAmount);
	}


	
}
