package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Person;
import util.JdbcUtil;

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
	public String deposit(Person person, Double updatedAmount) {
		String status=null;
		
		Integer aNo=person.getaNo();
		System.out.println("DAO11--"+aNo);
		
		String sqlUpdateQuery="update person SET balance=? where ano=?";
		
		PreparedStatement pstmt=null;
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null)
				pstmt=connection.prepareStatement(sqlUpdateQuery);
				if(pstmt!=null)
				{
					pstmt.setDouble(1, updatedAmount);
					pstmt.setInt(2, person.getaNo());
				}
				if(pstmt!=null)
				{
					int rowAffected=pstmt.executeUpdate();
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
		catch(SQLException | IOException e)
		{
			e.printStackTrace();
			status="failure";
		}
		
		return status;
	}


	@Override
	public Person getPerson(Integer aNo) {
		Person person=null;
		
        String sqlSelectQuery="SELECT * FROM person WHERE ano=?";
		
		PreparedStatement pstmt=null;
		ResultSet resultSet = null;
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			    if(connection!=null)
				   pstmt=connection.prepareStatement(sqlSelectQuery);
				if(pstmt!=null)
				{
					pstmt.setInt(1, aNo);
					resultSet = pstmt.executeQuery();
					System.out.println("Ano is "+ aNo);
				}
				if(resultSet!=null)
				{
				  if(resultSet.next())
				  {
					person = new Person();
					
					System.out.println("Copying resultSet data to Person object");
					System.out.println(resultSet.getNString(2));
	                System.out.println(resultSet.getString(2));				
	
					person.setaNo(resultSet.getInt(1));
					person.setPname(resultSet.getString(2));
					person.setFname(resultSet.getString(3));
					person.setMname(resultSet.getString(4));
					person.setPage(resultSet.getInt(5));
					person.setMno(Double.valueOf(resultSet.getDouble(6)));
		    	    person.setAtype(resultSet.getString(7));
				    person.setPaddr(resultSet.getString(8));
					person.setPwd(resultSet.getString(9));
					person.setAmount(resultSet.getDouble(10));
					
					System.out.println("Person ready to sent..");
					return person;
				  }
				}
				else
					System.out.println("ResultSet is null");
			}
		catch(SQLException | IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
