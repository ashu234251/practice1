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
	public String deposit(Person person) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.deposit(person);
	}


	
}
