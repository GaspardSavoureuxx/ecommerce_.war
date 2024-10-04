package fr.doranco.tp.metier;

import fr.doranco.tp.entity.User;

public interface IUserMetier {

	Boolean updatePassword(Integer id, String oldPwd, String newPwd) throws Exception;
	
	boolean addUser(User user) throws Exception;
	
	 
	boolean verifyUser (String email, String password)  throws Exception;
}
