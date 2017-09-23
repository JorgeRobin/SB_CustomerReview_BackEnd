package com.customerreview.ExceptionsAndErrors;

import java.io.Serializable;

public class CurseException extends Exception implements Serializable {
	private static final long serialVersionUID = 4664456874499611218L;
    public CurseException(String comment) {
        super("there is a curse word in " + comment);
    }
}
