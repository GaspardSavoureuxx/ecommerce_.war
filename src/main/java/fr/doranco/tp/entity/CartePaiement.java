package fr.doranco.tp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carte_paiement")
@Data
@NoArgsConstructor
public class CartePaiement {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 32, nullable = false)
	private String titulaire;
	
	@Column(length = 16, nullable = false)
	private String numero;
	
	@Column(name = "date_expiration", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateExpiration;
	
	@Column(length = 3, nullable = false)
	private String crypto;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
