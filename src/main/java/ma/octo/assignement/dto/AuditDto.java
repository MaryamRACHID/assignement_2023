package ma.octo.assignement.dto;

import lombok.Builder;
import lombok.Data;
import ma.octo.assignement.models.Audit;
import ma.octo.assignement.utils.EventType;


@Builder
@Data
public class AuditDto {

    private String message;

    private EventType eventType;

    public static AuditDto fromEntity(Audit audit){

        if(audit == null){
            return null;
        }
        return AuditDto.builder()
                .eventType(audit.getEventType())
                .message(audit.getMessage())
                .build();

    }

    public static Audit toEntity(AuditDto auditDto){

        if(auditDto == null){
            return null;
        }
        Audit audit = new Audit();
        audit.setEventType(audit.getEventType());
        audit.setMessage(audit.getMessage());
        return audit;
    }

}
