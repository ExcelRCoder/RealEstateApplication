package com.example.realestate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchControlDto {

	private String column;
	private String value;
	 Operation operation;
	    String joinTable;

	    public enum Operation{
	        EQUAL, LIKE, IN, GREATER_THAN, LESS_THAN, BETWEEN, JOIN;
	    }
}
