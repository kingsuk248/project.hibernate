package com.practice.self.project.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
	
	private static SessionFactory sessionFactory;
	
	public static void main( String[] args) {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Failed to create a session factory");
			System.err.println(e.getMessage());
			throw new ExceptionInInitializerError();
		}
		
		ManageEmployee me = new ManageEmployee();
		Address address = me.addAddress("CV Raman Nagar", "Bengaluru", "Karnataka", "560091"); 
		Project project = me.addProject("Bit Optimization", "Google");
		SortedSet<Certificate> certificates1 = new TreeSet<Certificate>();
		List<Achievement> achivements1 = new ArrayList<Achievement>();
		achivements1.add(new Achievement("StrSerArc"));
		achivements1.add(new Achievement("TunPerMas"));
		certificates1.add(new Certificate("BDaMa"));
		certificates1.add(new Certificate("BApAr"));
		Map<String, Skill> skills1 = new TreeMap<String, Skill>();
		skills1.put("backend", new Skill("Java"));
		skills1.put("frontend", new Skill("UI"));
		Integer id1 = me.addEmployee("John", "Smith", 55246, certificates1, achivements1, skills1, address, project);
	
		SortedSet<Certificate> certificates2 = new TreeSet<Certificate>(); 
		certificates2.add(new Certificate("MTeAu"));
		certificates2.add(new Certificate("DQuAs"));
		List<Achievement> achivements2 = new ArrayList<Achievement>();
		achivements2.add(new Achievement("HacSecLoo"));
		achivements2.add(new Achievement("AutManMon"));
		Map<String, Skill> skills2 = new TreeMap<String, Skill>();
		skills2.put("auto", new Skill("Cucumber"));
		skills2.put("manual", new Skill("Excel"));
		Integer id2 = me.addEmployee("Sara", "Powlowsky", 45680, certificates2, achivements2, skills2, address, project);

		SortedSet<Certificate> certificates3 = new TreeSet<Certificate>(); 
		certificates2.add(new Certificate("BMeBa"));
		certificates2.add(new Certificate("MaChPa"));
		List<Achievement> achivements3 = new ArrayList<Achievement>();
		achivements3.add(new Achievement("TenDeliDea"));
		Map<String, Skill> skills3 = new TreeMap<String, Skill>();
		skills3.put("method", new Skill("Agile"));
		Integer id3 = me.addEmployee("Adam", "Stauss", 28705, certificates3, achivements3, skills3, address, project);
		
		me.listEmployees();
		me.updateEmployeeSalary(id1, 56020);
		me.updateEmployeeLastName(id2, "Frost");
		me.deleteEmployee(id3);
		System.out.println();
		me.listEmployees();
	}
	
	private Project addProject(String name, String vendor) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		//Integer addressId = null;
		Project project = null;
		try {
			tx = session.beginTransaction();
			project = new Project(name, vendor);
			session.save(project);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
		return project;
	}
	
	private Address addAddress(String street, String city, String state, String zipCode) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		//Integer addressId = null;
		Address address = null;
		try {
			tx = session.beginTransaction();
			address = new Address(street, city, state, zipCode);
			session.save(address);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
		return address;
	}

	private Integer addEmployee(String firstName, String lastName, int salary, 
			SortedSet<Certificate> certificates, List<Achievement> achivements, Map<String, Skill> skills, Address address, Project project) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer employeeId = null;
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(firstName, lastName, salary, address, project);
			employee.setCertificates(certificates);
			employee.setAchievements(achivements);
			employee.setSkills(skills);
			//employee.setAddress(address);
			employeeId = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
		return employeeId;
	}

	private void updateEmployeeSalary(Integer id, int salary) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, id);
			employee.setSalary(salary);
			session.update(employee);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private void updateEmployeeLastName(Integer id, String lastName) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, id);
			employee.setLastName(lastName);
			session.update(employee);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void deleteEmployee(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, id);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void listEmployees() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Employee> employees = session.createQuery("FROM Employee").list();
			for (Employee employee : employees) {
				System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName() + ", Salary: " + employee.getSalary());
				System.out.print("Certificates -> ");
				for (Certificate certificate : employee.getCertificates()) {
					System.out.print(certificate.getName() + " ");
				}
				System.out.print("Achievements -> ");
				for (Achievement achievement : employee.getAchievements()) {
					System.out.print(achievement.getName() + " ");
				}
				System.out.println();
				System.out.print("Skills -> ");
				for (Map.Entry<String, Skill> skills : employee.getSkills().entrySet()) {
					System.out.print(skills.getKey() + ": " + skills.getValue().getName());
				}
				System.out.println();
			}
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null)
				tx.rollback();
			he.printStackTrace();
		} finally {
			session.close();
		}
		
	}
}
