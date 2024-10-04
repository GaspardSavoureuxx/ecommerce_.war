package fr.doranco.tp.model;

import java.util.List;

import fr.doranco.tp.entity.User;

public interface IUserDao {

	void addUser(User user) throws Exception;
	User getUserById(Integer id) throws Exception;
	User getUserByEmail(String email) throws Exception;
	List<User> getAllUsers() throws Exception;
	List<User> getAllByNameContaining(String chaine) throws Exception;
	void updateUser(User user) throws Exception;
	void deleteUser(String email) throws Exception;
	void changePassword(Integer userId, String newPwd) throws Exception;
	
	boolean findEmail(String email) throws Exception;
	public boolean verifyEmailPassword(String email, String passsword) throws Exception;
	String getUserRole(User user) throws Exception;
	User getUserRole(String email) throws Exception;
}
