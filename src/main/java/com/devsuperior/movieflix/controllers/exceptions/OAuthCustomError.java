package com.devsuperior.movieflix.controllers.exceptions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthCustomError implements Serializable{
	private static final long serialVersionUID = 1L;

	private String error;
	
	@JsonProperty("error_description")
	private String errorDescption;
	
	public OAuthCustomError() {
	}

	public OAuthCustomError(String error, String errorDescption) {
		this.error = error;
		this.errorDescption = errorDescption;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDescption() {
		return errorDescption;
	}

	public void setErrorDescption(String errorDescption) {
		this.errorDescption = errorDescption;
	}
}
