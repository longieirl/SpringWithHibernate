package com.opserver.simpleapp.validator;

import com.opserver.simpleapp.model.ServerEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ServerEntityValidator implements Validator {

    /*
     * Validate server entity
     */
    public boolean supports(Class clazz) {
        return ServerEntity.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        ServerEntity p = (ServerEntity) obj;
        if (p.getServerId() == null || p.getServerId().length() == 0) {
            e.rejectValue("serverId", "item has not been saved");
        } else if (p.getName() == null || p.getName().length() > 50) {
            e.rejectValue("name", "name is not valid");
        }
    }
}
