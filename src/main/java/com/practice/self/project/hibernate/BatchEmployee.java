package com.practice.self.project.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BatchEmployee {
	
	private static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Failed to create a session factory");
			System.err.println(e.getMessage());
			throw new ExceptionInInitializerError();
		}
		BatchEmployee be = new BatchEmployee();
		be.addEmployees();
	}

	private void addEmployees() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (int i = 0; i < 100000; i++) {
				String firstName = "FirstName" + i;
				String lastName = "LastName" + i;
				int salary = (i + 1) * 1000;
				Employee employee = new Employee(firstName, lastName, salary, null, null);
				session.save(employee);
				if (i % 50 == 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		
	}
}
