package com.example.realestate.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RequestDto {
	public List<SearchControlDto> searchControlDto;
	/*
	 * private GlobalOperator globalOperator;
	 * 
	 * public enum GlobalOperator { AND, OR; }
	 */

}
