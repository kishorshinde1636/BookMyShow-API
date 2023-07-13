package com.bookmyshow.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseStructure<T> {
	private int statusCode;
	private String message;
	private Object data;

}
