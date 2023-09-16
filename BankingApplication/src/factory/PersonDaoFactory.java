package factory;

import dao.IPersonDao;
import dao.PersonDaoImpl;

public class PersonDaoFactory {

	private static IPersonDao personDao=null;
	
	public PersonDaoFactory()
	{
		
	}
	public static IPersonDao getPersonDao()
	{
		if(personDao==null)
		
		personDao=new PersonDaoImpl();
			
		return personDao;
	}
}
