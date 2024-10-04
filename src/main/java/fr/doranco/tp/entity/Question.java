package fr.doranco.tp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "question", nullable = false)
	private String texte;
	
	@Column(name = "reponse", nullable = false)
	private String reponseTexte;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	

}
