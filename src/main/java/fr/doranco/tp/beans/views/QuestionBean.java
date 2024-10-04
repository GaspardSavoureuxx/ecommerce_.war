package fr.doranco.tp.beans.views;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.doranco.tp.entity.Question;
import fr.doranco.tp.entity.User;
import fr.doranco.tp.metier.UserMetier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ManagedBean(name = "questionbean")
@NoArgsConstructor
@SessionScoped
@Getter
@Setter
@ToString
public class QuestionBean {
	
	private String texte;
	private String reponseTexte;
	private String errorMessage;
	private String successMessage;
	
	
	
	public String addQuestion() {
		errorMessage = "";
		successMessage = "";
		
		try {
			Question question =  new Question();
			question.setTexte(texte);
			question.setReponseTexte(reponseTexte);
			
		} catch (Exception e) {
			errorMessage = "Erreur technique : " + e.getMessage();
			e.printStackTrace();
		}
		return"";
	}
	

}