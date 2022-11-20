package ma.octo.assignement.models;

import lombok.Data;
import ma.octo.assignement.utils.EventType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "AUDITS")
public class Audit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 1000)
  private String message;

  @Enumerated(EnumType.STRING)
  private EventType eventType;

}
