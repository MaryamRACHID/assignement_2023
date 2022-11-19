package ma.octo.assignement.repositories;

import ma.octo.assignement.models.Audit;
import ma.octo.assignement.utils.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuditRepository extends JpaRepository<Audit, Long> {

    Optional<Audit> findByEventType(EventType type);

}
