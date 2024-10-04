package fr.doranco.tp.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@NamedQueries({
	@NamedQuery(name = "User::findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User::findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
	@NamedQuery(name = "User::findByNameContaining", query = "SELECT u FROM User u WHERE u.nom LIKE ?1")
})
@Getter @Setter @ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nom", length = 25, nullable = false)
	private String nom;
	
	@Column(name = "prenom", length = 25, nullable = false)
	private String prenom;
	
	@Column(name = "sexe", length= 2, nullable = false)
	private String sexe;
	
	@Column(name = "email", length = 32, nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", length = 32, nullable = false)
	@Length(min = 12, max = 32, message = "Le password doit être compris entre 12 et 32 caractères !")
	private String password;
	
	@Column(name = "date_naissance")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	
	@Column(name = "actif", nullable = false)
	private Boolean isActif;
	
	@Column(name = "role", nullable = false)
	private String role; 
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	Set<Adresse> adresses;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private CartePaiement cartePaiement;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Question reponseQuestion;

	public User() {
		this.adresses = new HashSet<>();
	}
	
	
}
