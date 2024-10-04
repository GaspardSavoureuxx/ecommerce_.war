package fr.doranco.tp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Adresse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "numero", length = 7)
	private String numero;
	
	@Column(name = "rue", length = 32, nullable = false)
	private String rue;

	@Column(name = "ville", length = 32, nullable = false)
	private String ville;

	@Column(name = "code_postal", length = 5, nullable = false)
	private String codePostal;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	

}
