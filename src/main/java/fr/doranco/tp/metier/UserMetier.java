package fr.doranco.tp.metier;

import java.util.List;

import fr.doranco.tp.entity.User;
import fr.doranco.tp.model.UserDao;

public class UserMetier implements IUserMetier {

	final UserDao userDao = new UserDao();
	
	@Override
	public Boolean updatePassword(Integer id, String oldPwd, String newPwd) throws Exception {

		// Vérifier tous les paramètres de la méthode.
		
		User user = userDao.getUserById(id);
		if (!oldPwd.equals(user.getPassword())) {
			return false;
		}
		userDao.changePassword(id, newPwd);
		return true;
	}

	@Override
	public boolean addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
		if(user == null) {
			throw new NullPointerException("Le user créé est null");
		}
		boolean emailExists =userDao.findEmail(user.getEmail());
		
			if(emailExists = false) {
				return false;
			}
			userDao.addUser(user);
			return true;
		
	}
	// Interragit avec la méthode de UserBean et fait un controle avec les méthodes de la couche DAO :
	// appelle findEmail pour vérifier si l'adresse email existe deja dans la data_base
	// si c'est pas le cas appelle addUser
	
	
	public boolean verifyUser (String email, String password) {
		 	UserDao userDao = new UserDao();
			try {
				if ( userDao.verifyEmailPassword(email, password)) {
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;	
	}
	// Verify user prend en paramètre l'email et le mdp avec lesquels on l'a appelé dans Userbean
	// Ensuite elle appelle verifyEmailPassword de UserDao avec ces memes paramètres 
	// et si verifyEmailPassword return true verifyUser return true aussi
	
	public boolean UserRole () {
	 	UserDao userDao = new UserDao();
		try {
			if ( userDao.) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return false;
	}
	
	public List<User> getUsers() throws Exception {
		return userDao.getAllUsers();
	}
	
	

}
