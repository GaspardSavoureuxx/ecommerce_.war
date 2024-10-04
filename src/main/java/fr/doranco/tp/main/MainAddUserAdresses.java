package fr.doranco.tp.main;

import fr.doranco.tp.entity.Adresse;
import fr.doranco.tp.entity.User;
import fr.doranco.tp.model.UserDao;

public class MainAddUserAdresses {

	public static void main(String[] args) {

		try {
			User user = new User();
			user.setNom("EISTEIN");
			user.setPrenom("Albert");
			user.setEmail("eistein@gmail.com");
			user.setPassword("eisteinpass");
			user.setIsActif(true);

			Adresse a1 = new Adresse();
			a1.setNumero("7");
			a1.setRue("Rue de la gare");
			a1.setVille("Paris");
			a1.setCodePostal("75000");
			a1.setUser(user);

			Adresse a2 = new Adresse();
			a2.setNumero("9bis");
			a2.setRue("Avenue de France");
			a2.setVille("Lyon");
			a2.setCodePostal("69000");
			a2.setUser(user);

			user.getAdresses().add(a1);
			user.getAdresses().add(a2);

			UserDao userDao = new UserDao();
			userDao.addUser(user);
			System.out.println("user créé avec succès.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
