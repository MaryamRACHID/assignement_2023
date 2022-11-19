package ma.octo.assignement.controllers.api;

import ma.octo.assignement.utils.EventType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import static ma.octo.assignement.utils.Constants.APP_ROOT;

public interface AuditAPI {

    @PostMapping(value = APP_ROOT + "/audit/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void audit(Object event, EventType eventType);

}
