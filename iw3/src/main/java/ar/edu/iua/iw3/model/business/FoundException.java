package ar.edu.iua.iw3.model.business;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor

//Esperaba que algo no estuviese pero esta (por ejemplo si agrego algo a mi BD espero que eso NO este)
//Este seria el caso de algo que quiero que NO este pero SI esta
public class FoundException extends Exception{

    @Builder //Patron builder
    public FoundException(String message, Throwable ex) {
        super(message, ex);
    }

    @Builder
    public FoundException(String message) {
        super(message);
    }

    @Builder
    public FoundException(Throwable ex) {
        super(ex);
    }
}
