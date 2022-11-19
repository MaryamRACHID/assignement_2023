package ma.octo.assignement.services;

import ma.octo.assignement.utils.EventType;

public interface AuditService {

    void audit(Object event, EventType eventType);


}
