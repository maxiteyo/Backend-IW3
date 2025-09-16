package ar.edu.iua.iw3.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandartResponse {

	private String message;

	@JsonIgnore
	private Throwable ex;

	@JsonIgnore //Le dice al programa que esto lo ignore
	private HttpStatus httpStatus;

	public int getCode() {
		return httpStatus.value();
	}

	@JsonIgnore
	private boolean devInfoEnabled;

	public String getDevInfo() {
		if(devInfoEnabled) { //Esta variable la puedo modificar para mostrar o no el stack trace
			if(ex!=null) {
				return ExceptionUtils.getStackTrace(ex);
			} else {
				return "No stack trace";
			}
		} else {
			return null;
		}
	}

	public String getMessage() {
		if(message!=null) {
			return message;
		}
		if (ex!=null) {
			return ex.getMessage();
		}
		return null;
	}
}

//Formato del JSON:
/*
 {
  "message": "lfdkjsldkfjl",
  "dfevInfo":"kjdhkfghskdjfh"
 }
  */
