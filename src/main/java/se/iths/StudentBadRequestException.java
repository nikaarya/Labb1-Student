package se.iths;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class StudentBadRequestException extends WebApplicationException {

    public StudentBadRequestException (String errorMessage) {

        throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                .entity(errorMessage).type(MediaType.APPLICATION_JSON).build());
    }
}
