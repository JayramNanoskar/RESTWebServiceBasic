package com.jayram.rest.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jayram.rest.messenger.model.ErrorMessage;

@Provider //This registers current class in JAX-RS
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "https://www.w3.org/Protocols/HTTP/HTRESP.html");
		return Response.status(Status.NOT_FOUND)
						.entity(errorMessage)
						.build();
	}

}
