package fr.doranco.tp.model;

import fr.doranco.tp.entity.CartePaiement;

public interface ICartePaiementDao {

	void addCartePaiement(CartePaiement cp) throws Exception;
}
