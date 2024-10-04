package fr.doranco.tp.main;

import org.hibernate.Session;

import fr.doranco.tp.model.HibernateConnector;

public class Application {

	public static void main(String[] args) {

		Session session = HibernateConnector.getInstance().getSession();
		System.out.println(session);
	}
}
