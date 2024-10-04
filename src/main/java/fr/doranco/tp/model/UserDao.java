package fr.doranco.tp.model;

import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.doranco.tp.entity.Adresse;
import fr.doranco.tp.entity.Question;
import fr.doranco.tp.entity.User;

public class UserDao implements IUserDao {

	@Override
	public void addUser(User user) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			if (user == null) {
				throw new NullPointerException("Le user à créer ne doit pas être vide !");
			}
			if (user.getNom() == null || user.getNom().trim().isEmpty()
					|| user.getPrenom() == null || user.getPrenom().trim().isEmpty()
					|| user.getEmail() == null || user.getEmail().trim().isEmpty()
					|| user.getPassword() == null || user.getPassword().trim().isEmpty()
					|| user.getIsActif() == null) {
				throw new IllegalArgumentException("Un ou plusieurs paramètres sont manquants !");
			}
			
			session = HibernateConnector.getInstance().getSession();
			tx = session.beginTransaction();
			session.save(user);
			if (user.getAdresses() != null && !user.getAdresses().isEmpty()) {
				for (Adresse a : user.getAdresses()) {
					session.save(a); 
				}
			}	
				
				if(user.getReponseQuestion() !=null) {
					Question q = user.getReponseQuestion(); 
					session.save(q);
					}
				else { System.out.println(" Aucune question retrouvée "); }
			tx.commit();
		} catch (RollbackException e) {
			tx.rollback();
		}
		
		finally {
			if (session != null && session.isOpen())
				session.close();
		}
	}

	@Override
	public User getUserById(Integer id) throws Exception {
		
		if (id == null || id <= 0)
			throw new IllegalArgumentException("L'id doit être > 0 !");
		
		Session session = null;
		try {
			session = HibernateConnector.getInstance().getSession();
			//session.get(User.class, id);
			return session.find(User.class, id);
			
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
	}

	@Override
	public User getUserByEmail(String email) throws Exception {
		if (email == null || email.trim().isEmpty())
			throw new IllegalArgumentException("L'email est obligatoire !");
		
		Session session = null;
		try {
			session = HibernateConnector.getInstance().getSession();
			
			//1ère façon de faire : Utiliser une requête prédéfinie (NamedQuery)
//			return session.createNamedQuery("User::findByEmail", User.class)
//						.setParameter("email", email)
//						.uniqueResult();
			
			//2ème façon de faire : utiliser une requête JPQL
//			String requete = "SELECT u FROM User u WHERE email = :email";			
//			return session.createQuery("SELECT u FROM User u WHERE email = :email", User.class)
//					.setParameter("email", email)
//					.uniqueResult();

//			String requete = "SELECT u FROM User u WHERE email = ?1";
//			return session.createQuery(requete, User.class)
//					.setParameter(1, email)
//					.uniqueResult();
			
			//3ème façon de faire : utiliser une requête native (pur SQL)
			String requete = "SELECT * FROM user WHERE email = :email";
			return session.createNativeQuery(requete, User.class)
					.setParameter("email", email)
					.uniqueResult();
			
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
	}
	
	

	@Override
	public List<User> getAllByNameContaining(String chaine) throws Exception {
		if (chaine == null || chaine.trim().isEmpty())
			throw new IllegalArgumentException("La chaine doit être renseignée");
		
		Session session = HibernateConnector.getInstance().getSession();
		try {
			return session.createQuery("FROM User u WHERE u.nom LIKE ?1", User.class)
					.setParameter(1, "%" + chaine + "%")
					.list();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		} 
	}

	@Override
	public void updateUser(User user) throws Exception {
		if (user == null) {
			throw new NullPointerException("Le user à mettre à jour doit être renseigné !");
		}
		if (user.getNom() == null || user.getNom().trim().isEmpty()
				|| user.getPrenom() == null || user.getPrenom().trim().isEmpty()
				|| user.getEmail() == null || user.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("Les paramètres nom, prenom et email sont obligatoires !");
		}
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateConnector.getInstance().getSession();
			User userDb = session.createNamedQuery("User::findByEmail", User.class)
					.setParameter("email", user.getEmail())
					.uniqueResult();
			if (userDb == null) {
				throw new Exception("Le user à mettre à jour n'existe pas !");
			}
			userDb.setNom(user.getNom());
			userDb.setPrenom(user.getPrenom());
			userDb.setDateNaissance(user.getDateNaissance());
			
			tx = session.beginTransaction();
			session.update(userDb);
			tx.commit();
		} catch (RollbackException e) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}	
	}

	@Override
	public void deleteUser(String email) throws Exception {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("L'email est obligatoire !");
		}
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateConnector.getInstance().getSession();
			User user = session.createNamedQuery("User::findByEmail", User.class)
					.setParameter("email", email)
					.uniqueResult();
			if (user == null)
				return;
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
		} catch (RollbackException e) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}	
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		Session session = HibernateConnector.getInstance().getSession();
		return session.createNamedQuery("User::findAll", User.class)
						.list();
	}

	@Override
	public void changePassword(Integer userId, String newPwd) throws Exception {
		if (userId == null || userId <= 0) {
			throw new IllegalArgumentException("L'id du user doit être > 0 !");
		}
		if (newPwd == null || newPwd.trim().isEmpty()) {
			throw new IllegalArgumentException("Le nouveau mot de passe est obligatoire !");
		}
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateConnector.getInstance().getSession();
			User user = session.get(User.class, userId);
			if (user == null)
				throw new Exception("L'utilisateur n'existe pas : id = " + userId);
			user.setPassword(newPwd);
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (RollbackException e) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
	}

		// Retrouver si l'email user entré dans la partie web existe deja
	@Override
	public boolean findEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("L'email est obligatoire !");
		}
		Session session = null;
		try {
			session = HibernateConnector.getInstance().getSession();
			String requete = "SELECT * FROM user WHERE email = :email";
			User user = session.createNativeQuery(requete, User.class)
			.setParameter("email", email)
			.uniqueResult();
			if(user != null) {
				return true; }
			return false;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
	}
	
	public boolean verifyEmailPassword(String e, String p) throws Exception {
		// TODO Auto-generated method stub
		if (e == null || e.trim().isEmpty() || p == null || p.trim().isEmpty() ) {
			throw new IllegalArgumentException("L'email et le mot de passe sont obligatoires !");
		}
		Session session = null;
		try {
			session = HibernateConnector.getInstance().getSession();
			String requete = "SELECT * FROM user WHERE email = :email";
			User user = session.createNativeQuery(requete, User.class)
			.setParameter("email", e)
			.uniqueResult();
			System.out.println(user);
			if(user != null && user.getPassword().equals(p)) {
				return true; }
			return false;
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
	}
	// cette fonction prend les paramètres email et password qu'on lui a donné quand on l'a appelé dans user metier et fait une requete avec
	// si un user es retrouvé dans la database avec un mail qui correspond au 1er paramètre et un password qui correspond qau 2ème paramètre, alors elle retournera true, sinon false
	
	
	@Override
	public User getUserRole(String email) throws Exception {
		if (email == null || email.trim().isEmpty())
			throw new IllegalArgumentException("L'email est obligatoire !");
		
		Session session = null;		
		try {
			session = HibernateConnector.getInstance().getSession();
			String requete = "SELECT * FROM user WHERE email = :email";
			return session.createNativeQuery(requete, User.class)
					.setParameter("email", email)
					.uniqueResult(); 
			
			
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}	
	}

	@Override
	public String getUserRole(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


}
