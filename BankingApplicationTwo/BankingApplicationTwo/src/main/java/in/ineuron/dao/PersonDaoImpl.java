package in.ineuron.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Person;
import in.ineuron.util.JdbcUtil;

public class PersonDaoImpl implements IPersonDao {

	Connection connection=null;
	@Override
	public String register(Person person) {
		String SqlInsertQuery="insert into Person(`pname`,`mname`,`fname`,`page`,`mno`,`atype`,`paddr`,`pwd`) values(?,?,?,?,?,?,?,?)";
		PreparedStatement psmt=null;
		String status=null;
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null)
			psmt=connection.prepareStatement(SqlInsertQuery);
			if(psmt!=null)	
			{
				psmt.setString(1, person.getPname());
				psmt.setString(2, person.getMname());
				psmt.setString(3, person.getFname());
				psmt.setInt(4, person.getPage());
				psmt.setDouble(5, person.getMno());
				psmt.setString(6, person.getAtype());
				psmt.setString(7, person.getPaddr());
				psmt.setString(8, person.getPwd());
			}	
			if(psmt!=null)
			{
				int rowAffected=psmt.executeUpdate();
				if(rowAffected==1)
				{
					status="success";
				}
				else
				{
					status="failure";
				}
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			status="failure";
		}
		catch(IOException e)
		{
			e.printStackTrace();
			status="failure";
		}
		return status;
	}

//	@Override
//	public Person login(String pname, String pwd, Integer ano) {
//		String sqlSelectQuery="select ano,pname,pwd from person where ano=?";
//		PreparedStatement psmt=null;
//		Person person=null;
//		try
//		{
//			connection=JdbcUtil.getJdbcConnection();
//			if(connection!=null)
//				psmt=connection.prepareStatement(sqlSelectQuery);
//			if(psmt!=null)
//			{
//				psmt.setInt(1, ano);
//			}
//			if(psmt!=null)
//			{
//				ResultSet resultSet=psmt.executeQuery();
//				if(resultSet.next())
//				{
//					//copy the resultset data to StudentDTO and transfer to the view
//					person=new Person();
//					person.setaNo(resultSet.getInt(1));
//					person.setPname(resultSet.getString(2));
//					person.setPwd(resultSet.getString(3));
//					
//				}
//				
//			}
//		}
//		catch(SQLException se)
//		{
//			se.printStackTrace();
//			
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//			
//		}
//		return person;
//		
//		
//		
//	}
	
	@Override
	public String login(Person person) {
		
		String status=null;
		Integer anouser=person.getaNo();
		System.out.println("DAO11"+anouser);
		String pnameuser=person.getPname();
		System.out.println("DAO11"+pnameuser);
		String pwduser=person.getPwd();
		System.out.println("DAO11"+pwduser);
		
		String sqlSelectQuery="select ano,pname,pwd from person where ano=?";
		PreparedStatement psmt=null;
		
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				psmt=connection.prepareStatement(sqlSelectQuery);
				if(psmt!=null)
				{
					psmt.setInt(1, anouser);
				}
				if(psmt!=null)
				{
					ResultSet resultSet=psmt.executeQuery();
					if(resultSet.next())
					{
						String pname=resultSet.getString(2);
						System.out.println("DAO"+pname);
						String pwd=resultSet.getString(3);
						System.out.println("DAO"+pwd);
						
						if(pnameuser.equals(pname) && pwduser.equals(pwd))
						{
							status="success";
						}
						else
						{
							status="failure";
						}
					}
				}
			}
		}
		
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		return status;
	}

	@Override
	public String deposit(Person person) {
		String status=null;
//		double balance=balance+amount;
		
		Integer anouser=person.getaNo();
		System.out.println("DAO11--"+anouser);
		
		double amount=person.getAmount();
		System.out.println("DAO11--"+amount);
		
		double bal= 0;
		bal=bal+amount;
		
		String sqlUpdateQuery="update person SET balance=ba where ano=?";
		
		PreparedStatement psmt=null;
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null)
				psmt=connection.prepareStatement(sqlUpdateQuery);
				if(psmt!=null)
				{
					psmt.setInt(1, person.getaNo());
				}
				if(psmt!=null)
				{
					int rowAffected=psmt.executeUpdate();
					if(rowAffected==1)
					{
						status="success";
					}
					else
					{
						status="failure";
					}
				}
			}catch(SQLException | IOException e)
			{
				e.printStackTrace();
				status="failure";
			}
				
		
		
		return null;
	}
}
