package fr.doranco.tp.beans.views;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.doranco.tp.entity.Question;
import fr.doranco.tp.entity.User;
import fr.doranco.tp.enums.UserRoleEnum;
import fr.doranco.tp.metier.UserMetier;
import fr.doranco.tp.model.UserDao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Permet à la classe d'etre visible dans la partie view (WebContent)
@ManagedBean(name = "userbean")
@NoArgsConstructor
@SessionScoped
@Getter
@Setter
@ToString
public class UserBean {

	// @ManagedProperty(name="nom", value="Camus")
	private String nom;

	// @ManagedProperty(name="prenom", value="Albert")
	private String prenom;

	private String sexe;

	// @ManagedProperty(name="email", value="camuu@gmail.com")
	private String email;

	// @ManagedProperty(name="password", value="camcamcam")
	private String password;

	private String confirmPassword;

	private Date dateNaissance;

	private Boolean isActif;
	
	private String authentification;

	private String errorMessage;
	private String successMessage;

	private final UserMetier userMetier = new UserMetier();

	public String addUser() {
		errorMessage = "";
		successMessage = "";
		try {
			if (!password.equals(confirmPassword)) {
				errorMessage = "Mot de passe non vérifié";
				return "";
			}
			User user = new User();
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setSexe(sexe);
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(UserRoleEnum.C.toString());
			user.setIsActif(false);

			boolean isUserAdded = userMetier.addUser(user);
			if (!isUserAdded) {
				errorMessage = "Utilisateur deja inscrit";
				return "";
			}
			successMessage = "User créé avec succès, vous pouvez vous connecter";
			return "log_in"; 

		} catch (Exception e) {
			errorMessage = "Erreur technique : " + e.getMessage();
			e.printStackTrace();
		}
		return "";
	}

	// Génère un nouvel user si les mdp et confirmation correspondent et dirige vers
	// la méthode addUser de la couche UserMetier
	
	
	public String verifyUser () {
		errorMessage = "";
		successMessage = "";
		try {
				if(!authentification.equals("v"))
					{ errorMessage = "Validez l'authentification"; return ""; }
					boolean accountExist = userMetier.verifyUser(email, password);
					boolean accountAdmin = false;
						if(accountExist == true)
						{	if(accountAdmin == true) { return "users"; }
							else {	return "confirmation"; }
						}
						errorMessage = "Email ou mot de passe non reconnu";
							return "";
		} catch (Exception e) {
			errorMessage = "Erreur technique : " + e.getMessage();
			e.printStackTrace();		}
		return "";
	}
	// Si les champs ont bien été rempli, on appelle la fonction "verifyUser" de User métier avec l'email et le mdp entré par l'user en paramètre
	// et si elle renvoie true on affiche un message de succès, sinon on affiche un message d'erreur
	
	
	
	
	public List<User> getUsers() throws Exception {
			return userMetier.getUsers();
		}
		
	}
	


