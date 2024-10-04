package fr.doranco.tp.main;

import fr.doranco.tp.entity.User;
import fr.doranco.tp.model.UserDao;

public class Main {

	public static void main(String[] args) {

		UserDao userDao = new UserDao();

		try {
//			User user = new User(null, "CAMUS", "Albert", "albert@gmail.com", "victorpass", null, false);
//			userDao.addUser(user);
//			System.out.println(user);

//			System.out.println(userDao.getUserById(1));

//			System.out.println(userDao.getUserByEmail("victor@gmail.com"));
	
//			userDao.getAllUsers().forEach(u -> System.out.println(u));
	
//			userDao.deleteUser("victor@gmail.com");
//			System.out.println("Utilisateur supprimé avec succès");

//			User user = userDao.getUserByEmail("pascal@gmail.com");
//			user.setNom(user.getNom() + "-MAJ");
//			user.setPrenom(user.getPrenom() + "-MAJ");
//			userDao.updateUser(user);
//			System.out.println("Utilisateur mis à jour avec succès.");
//			System.out.println(user);
			
//			userDao.changePassword(1, "Coucou");
//			System.out.println(userDao.getUserById(1));
			
			userDao.getAllByNameContaining("se").forEach(u -> System.out.println(u));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
