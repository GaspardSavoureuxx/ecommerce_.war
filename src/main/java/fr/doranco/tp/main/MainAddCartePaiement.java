package fr.doranco.tp.main;

import fr.doranco.tp.entity.CartePaiement;
import fr.doranco.tp.entity.User;
import fr.doranco.tp.model.CartePaiementDao;
import fr.doranco.tp.model.UserDao;
import fr.doranco.tp.utils.Dates;

public class MainAddCartePaiement {

	public static void main(String[] args) {
		
		final UserDao userDao = new UserDao();
		final CartePaiementDao cpDao = new CartePaiementDao();

		try {
			User user = userDao.getUserByEmail("eistein@gmail.com");
			
			CartePaiement cp = new CartePaiement();
			cp.setTitulaire("BERT Paul");
			cp.setNumero("1234567890123456");
			cp.setCrypto("999");
			cp.setDateExpiration(Dates.convertStringToDate("23/04/2026"));
			
			cp.setUser(user);
			
			cpDao.addCartePaiement(cp);
			System.out.println("Carte de paiement ajoutée avec succès.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
