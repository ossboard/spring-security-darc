package com.konantech.spring.darcLib.spi;

import java.util.LinkedHashMap;
import java.util.Map;

public class SchemaView  {

	private Map<String, FieldDefinition> fdefinitions = new LinkedHashMap<String, FieldDefinition>();
	
	private String primaryKey;

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	public FieldDefinition getFieldDefinition(String fieldName) {
		return fdefinitions.get(fieldName);
	}
	
	public Map<String, FieldDefinition> getFieldDefinitions() {
		return fdefinitions;
	}

	public void setFieldDefinition(String fieldName, FieldDefinition fdef) {
		fdefinitions.put(fieldName, fdef);
	}

	public String[] getDeclaredFieldNames() {
		return fdefinitions.keySet().toArray(new String[0]);
	}

}
