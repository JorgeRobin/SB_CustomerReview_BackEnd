package com.customerreview.ExceptionsAndErrors;

import java.io.Serializable;

public class RatingException extends Exception implements Serializable {
	private static final long serialVersionUID = 4664456874499611218L;
    public RatingException(double rating) {
        super("rating is less than zero " + rating);
    }
}
