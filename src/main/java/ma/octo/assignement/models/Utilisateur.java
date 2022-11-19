package ma.octo.assignement.models;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "UTILISATEURS")
public class Utilisateur implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 10, nullable = false, unique = true)
  private String username;

  @Column(length = 10, nullable = false)
  private String gender;

  @Column(length = 60, nullable = false)
  private String lastName;

  @Column(length = 60, nullable = false)
  private String firstName;

  @Temporal(TemporalType.DATE)
  private Date birthDate;

}
