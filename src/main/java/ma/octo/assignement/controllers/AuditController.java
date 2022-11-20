package ma.octo.assignement.controllers;

import ma.octo.assignement.controllers.api.AuditAPI;
import ma.octo.assignement.utils.EventType;
import ma.octo.assignement.services.AuditService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditController implements AuditAPI {


    private AuditService auditService;

    AuditController(AuditService auditService){ this.auditService = auditService; }

    @Override
    public void audit(Object event, EventType eventType) {
        auditService.audit(event, eventType);
    }
}
