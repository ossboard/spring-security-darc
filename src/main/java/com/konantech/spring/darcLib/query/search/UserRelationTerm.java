package com.konantech.spring.darcLib.query.search;

import lombok.Data;

@Data
public class UserRelationTerm extends PropertyTerm{
	
	private static final long serialVersionUID = 1550468627644987623L;
	
	String userName;
	String type;
	int subtype;

	public UserRelationTerm(String propertyName, String type, String userName, int subtype) {
		super(propertyName);
		
		this.userName = userName;
		this.type = type;
		this.subtype = subtype;
	}

}
