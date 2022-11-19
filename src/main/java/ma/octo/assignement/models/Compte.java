package ma.octo.assignement.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "COMPTES")
public class Compte {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 16, unique = true)
  private String nrCompte;

  private String rib;

  @Column(precision = 16, scale = 2)
  private BigDecimal solde;

  @ManyToOne()
  @JoinColumn(name = "utilisateur_id")
  private Utilisateur utilisateur;

}
