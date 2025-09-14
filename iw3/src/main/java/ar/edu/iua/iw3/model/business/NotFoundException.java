package ar.edu.iua.iw3.model.business;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor

//Este seria el caso de algo que quiero que SI este pero NO esta
public class NotFoundException extends Exception{

    @Builder //Patron builder
    public NotFoundException(String message, Throwable ex) {
        super(message, ex);
    }

    @Builder
    public NotFoundException(String message) {
        super(message);
    }

    @Builder
    public NotFoundException(Throwable ex) {
        super(ex);
    }
}
