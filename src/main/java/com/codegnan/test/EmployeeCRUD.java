package com.codegnan.test;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.codegnan.beans.Employee;

public class EmployeeCRUD {
	private static SessionFactory sessionFactory;// declare sessionFactory

	public static void main(String[] args) {
		// initilize hibernate sessionFactory
		sessionFactory = createSessionFactory();
		// start the CRUD Operations
		EmployeeCRUD employeeCRUD = new EmployeeCRUD();
		employeeCRUD.run();
		sessionFactory.close();
	}

	public static SessionFactory createSessionFactory() {
		// create configuration Object
		Configuration configuration = new Configuration();
		// set hibernate properties.
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/jfs");
		configuration.setProperty("hibernate.connection.username", "root");
		configuration.setProperty("hibernate.connection.password", "root");
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.addAnnotatedClass(Employee.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("Choose An Operation ");
			System.out.println("1. create Employee");
			System.out.println("2. Read Employee");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Exit");
			System.out.println("=====================");
			System.out.println("Enter your Choice");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				createEmployee(scanner);// call method create an employee
				break;
			case 2:
				readEmployee(scanner);// call method to read an employee
				break;
			case 3:
				updateEmployee(scanner);// call method to update an employee
				break;
			case 4:
				deleteEmployee(scanner);// call method to delee an employee
				break;
			case 5:
				System.out.println("Existing....");
				break;
			default:
				System.out.println("Invalid Choice! Please try again");
			}
		} while (choice != 5);
		scanner.close();
	}

	public void createEmployee(Scanner scanner) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Employee employee = new Employee();
			System.out.print("Enter Employee Id : ");
			employee.setEid(scanner.nextInt());
			System.out.println("Enter Employee Name : ");
			employee.setEname(scanner.next());
			System.out.println("Enter Employee Salary : ");
			employee.setEsal(scanner.nextFloat());
			System.out.println("Enter Employee Address : ");
			employee.setEaddr(scanner.next());
			session.save(employee);
			transaction.commit();
			System.out.println("Employee created succesfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void readEmployee(Scanner scanner) {
		Session session = sessionFactory.openSession();
		try {
			System.out.print("Enter employee Id to Read : ");
			int eid = scanner.nextInt();
			Employee employee = session.get(Employee.class, eid);
			if (employee != null) {
				System.out.println(employee);
			} else {
				System.out.println("Employee not found with ID :" + eid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void updateEmployee(Scanner scanner) {
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			System.out.print("Enter Employee id to Update : ");
			int eid = scanner.nextInt();
			Employee employee = session.get(Employee.class, eid);
			if (employee != null) {
				scanner.nextLine();
				System.out.println("Enter new Employee Name : ");
				employee.setEname(scanner.next());
				System.out.println("Enter new Employee salary : ");
				employee.setEsal(scanner.nextFloat());
				System.out.println("Enter new Employee Address : ");
				employee.setEaddr(scanner.next());
				session.update(employee);
				transaction.commit();
				System.out.println("Employee Updated Succesfully ");
			} else {
				System.out.println("Employee Not Found With Id : " + eid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteEmployee(Scanner scanner) {
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			System.out.print("Enter Employee Id To Delete : ");
			int eid = scanner.nextInt();
			Employee employee = session.get(Employee.class, eid);
			if (employee != null) {
				session.delete(employee);
				transaction.commit();
				System.out.println("Employee record deleted Successfully....");
			} else {
				System.out.println("Employee Not Found With id : " + eid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
