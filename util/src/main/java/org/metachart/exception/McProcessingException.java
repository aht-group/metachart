package org.metachart.exception;

import java.io.Serializable;

public class McProcessingException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1;

	public McProcessingException() 
	{ 
	} 
 
	public McProcessingException(String s) 
	{ 
		super(s); 
	} 
}
