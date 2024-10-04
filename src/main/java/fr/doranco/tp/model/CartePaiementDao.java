package fr.doranco.tp.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.doranco.tp.entity.CartePaiement;

public class CartePaiementDao implements ICartePaiementDao {

	@Override
	public void addCartePaiement(CartePaiement cp) throws Exception {
		
		if (cp == null) {
			throw new NullPointerException("La carte de paiement à ajouter doit être non NULL !");
		}
		// vérifier tous les patramètres de cp s'ils sont non nulls et non vides
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateConnector.getInstance().getSession();
			tx = session.beginTransaction();
			session.save(cp);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
	}

}
