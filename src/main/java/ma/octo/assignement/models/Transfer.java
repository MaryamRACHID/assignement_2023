package ma.octo.assignement.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "TRANSFERS")
public class Transfer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  private Compte compteEmetteur;

  @ManyToOne
  private Compte compteBeneficiaire;

  @Column(precision = 16, scale = 2, nullable = false)
  private BigDecimal montantTransfer;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateExecution;


  @Column(length = 200)
  private String motifTransfer;

}
