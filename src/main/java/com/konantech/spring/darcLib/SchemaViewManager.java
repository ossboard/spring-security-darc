package com.konantech.spring.darcLib;

import _1._0._0._127.darc_wsdl.DarcPortType;
import com.konantech.spring.darcLib.builder.Custom;
import com.konantech.spring.darcLib.model.Code;
import com.konantech.spring.darcLib.parser.ResultParser;
import com.konantech.spring.darcLib.spi.SchemaView;

import java.util.HashMap;
import java.util.Map;

public class SchemaViewManager {
	
	private Map<String, SchemaView> fdefinitions = new HashMap<String, SchemaView>();
	private Map<String, SchemaView> sdefinitions = new HashMap<String, SchemaView>();
	private Map<String, Code> codes = new HashMap<String, Code>();
	private static SchemaViewManager instance = null;
	
	public static SchemaViewManager getInstance() {
		return instance;
	}
	
	public SchemaView getSchemaSearchView(String type) {
		return sdefinitions.get(getSearchViewName(type));
	}
	
	public SchemaView getSchemaFetchView(String type) {
		return fdefinitions.get(getSearchViewName(type));
	}
	
	public SchemaView getSchemaAssetView(String type) {
		return fdefinitions.get(getAssetViewName(type));
	}

	public Object getCode(String key) {
		return codes.get(key);
	}
	
	public static void registerSchemaSearchView(String view, SchemaView ftypes) {
		instance.sdefinitions.put(view, ftypes);
	}

	public static void registerSchemaFetchView(String view, SchemaView ftypes) {
		instance.fdefinitions.put(view, ftypes);
	}

	public static void registerSchemaAssetView(String view, SchemaView ftypes) {
		instance.fdefinitions.put(view, ftypes);
	}

	public static void registerCode(Code code) {
		instance.codes.put(code.getName(), code);
	}
	
	public static String getAssetViewName(String type) {
		String result = Custom.getString("ast_br." + type);
		if (result == null) {
			throw new IllegalArgumentException("Illegal media type '" + type
					+ "'");
		}
		return result;
	}
	
	public static String getSearchViewName(String type) {
		String result = Custom.getString("sch_br." + type);
		if (result == null) {
			throw new IllegalArgumentException("Illegal media type '" + type
					+ "'");
		}
		return result;
	}
	
	public static String getHierarchyAssetViewName(String type) {
		String result = Custom.getString("hast_br." + type);
		return result;
	}
	
	public static void init(DarcPortType port) {
		instance = new SchemaViewManager();
		for (String key : Custom.keySet("sch_br")) {
			String resp = port.getSchemaSearchView(0, Custom.getString(key));
			ResultParser.parseSchemaSearchView(resp);
		}
		for (String key : Custom.keySet("ast_br")) {
			String resp = port.getSchemaAssetView(0, Custom.getString(key));
			ResultParser.parseSchemaAssetView(resp);
		}
		String resp = port.getListCode(0);
		ResultParser.parseCodes(resp);
	}
	
}
