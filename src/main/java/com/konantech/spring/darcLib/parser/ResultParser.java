package com.konantech.spring.darcLib.parser;

import com.konantech.spring.darcLib.SchemaViewManager;
import com.konantech.spring.darcLib.exception.DarcRuntimeException;
import com.konantech.spring.darcLib.model.Code;
import com.konantech.spring.darcLib.spi.FieldDefinition;
import com.konantech.spring.darcLib.spi.SchemaView;
import com.konantech.spring.darcLib.xml.XMLBean;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultParser {
//
//	public static boolean parseResult(String api, String xml) {
//		try {
//			XMLBean bean = new XMLBean(xml);
//			SoapUtils.checkAndThrow(bean);
//			String result = bean.getString(api);
//			return "success".equals(result);
//		} catch (Exception e) {
//			throw wrapException(e);
//		}
//	}
//
//	public static Asset parseUser(String xml) {
//		XMLBean bean;
//		try {
//			bean = new XMLBean(xml);
//			SoapUtils.checkAndThrow(bean);
//			HierarchicalConfiguration node = bean.configurationAt("openuser.metaset");
//			Asset user = parseAsset(null, node);
//			node = bean.configurationAt("openuser.user");
//			user.setIdentifier(node.getString("username"));
//			user.setName(node.getString("realname"));
//			user.setProperty("passwordmodify", node.getLong("passwordmodify", 0));
//			user.setProperty("passwordspan", node.getInt("passwordspan", 0));
//			user.setProperty("passwordwarn", node.getInt("passwordwarn", 0));
//			return user;
//		} catch (Exception e) {
//			throw wrapException(e);
//		}
//	}
//
//	public static Folder parseFolder(String xml) {
//		try {
//			XMLBean bean = new XMLBean(xml);
//			SoapUtils.checkAndThrow(bean);
//			HierarchicalConfiguration node = bean
//					.configurationAt("opentreerootnode.treenode");
//			return parseFolder(node);
//		} catch (Exception e) {
//			throw wrapException(e);
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public static List<Folder> parseFolderList(String xml) {
//		try {
//			XMLBean bean = new XMLBean(xml);
//			SoapUtils.checkAndThrow(bean);
//			List<Folder> list = new ArrayList<Folder>();
//			List<HierarchicalConfiguration> nodes = bean
//					.configurationsAt("opentreechildnode.treenode");
//			for (HierarchicalConfiguration node : nodes) {
//				list.add(parseFolder(node));
//			}
//			return list;
//		} catch (Exception e) {
//			throw wrapException(e);
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public static QueryResult parseQueryResult(Query query,
//			SearchControls controls, String xml) {
//		try {
//			XMLBean bean = new XMLBean(xml);
//			SoapUtils.checkAndThrow(bean);
//			QueryResult result = new QueryResult();
//			int totalcount = Integer.parseInt(bean.getString("search.resultcount"));
//			result.setTotalCount(totalcount);
//			result.setOffset(controls.getOffset());
//
//			SchemaView sv = SchemaViewManager.getInstance()
//					.getSchemaFetchView(query.getMediaType());
//			List<HierarchicalConfiguration> items = bean
//					.configurationsAt("search.metaset");
//			for (HierarchicalConfiguration node : items) {
//				Asset asset = parseAsset(sv, node);
//				asset.setIdentifier(node.getString("pkvalue"));
//				result.addAsset(asset);
//			}
//			return result;
//		} catch (Exception e) {
//			throw wrapException(e);
//		}
//	}
//
//	public static Asset parseAsset(String type, String api, String xml) {
//		try {
//			XMLBean bean = new XMLBean(xml);
//			SoapUtils.checkAndThrow(bean);
//			SchemaView sv = SchemaViewManager.getInstance().getSchemaAssetView(type);
//			HierarchicalConfiguration node = bean.configurationAt(api
//					+ ".metaset");
//			Asset asset = parseAsset(sv, node);
//			asset.setIdentifier(node.getString("pkvalue"));
//			return asset;
//		} catch (Exception e) {
//			throw wrapException(e);
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public static List<Asset> parseAssetList(String type, String api, String xml) {
//		try {
//			XMLBean bean = new XMLBean(xml);
//			SoapUtils.checkAndThrow(bean);
//			int count = Integer.parseInt(bean.getString(api + ".assetcount"));
//			List<Asset> result = new ArrayList<Asset>(count);
//			SchemaView sv = SchemaViewManager.getInstance().getSchemaAssetView(type);
//
//			List<HierarchicalConfiguration> items = bean.configurationsAt(api
//					+ ".metaset");
//			for (HierarchicalConfiguration node : items) {
//				Asset asset = parseAsset(sv, node);
//				result.add(asset);
//			}
//			return result;
//		} catch (Exception e) {
//			throw wrapException(e);
//		}
//	}
//
	@SuppressWarnings("unchecked")
	public static void parseSchemaSearchView(String xml) {
		try {
			XMLBean bean = new XMLBean(xml);
			SoapUtils.checkAndThrow(bean);
			List<HierarchicalConfiguration> items = bean
					.configurationsAt("getschemasearchview.schemasearch");
			for (HierarchicalConfiguration node : items) {
				String viewName = node.getString("searchviewname");
				String primaryKey = node.getString("primarykeyname");

				SchemaView schemaSearchView = new SchemaView();
				schemaSearchView.setPrimaryKey(primaryKey);
				parseSearchFields(schemaSearchView,
						node.configurationsAt("searchfieldset.searchfield"),
						"dbfieldname");
				SchemaViewManager.registerSchemaSearchView(viewName,
						schemaSearchView);

				SchemaView schemaFetchView = new SchemaView();
				schemaFetchView.setPrimaryKey(primaryKey);
				parseSearchFields(schemaFetchView,
						node.configurationsAt("fetchfieldset.fetchfield"),
						"dbfieldname");
				SchemaViewManager.registerSchemaFetchView(viewName,
						schemaFetchView);
			}
		} catch (Exception e) {
			throw wrapException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void parseSchemaAssetView(String xml) {
		try {
			XMLBean bean = new XMLBean(xml);
			SoapUtils.checkAndThrow(bean);
			List<HierarchicalConfiguration> items = bean
					.configurationsAt("getschemaassetview.schemaasset");
			for (HierarchicalConfiguration node : items) {
				String viewName = node.getString("assetviewname");
				String primaryKey = node.getString("primarykeyname");

				SchemaView schemav = new SchemaView();
				schemav.setPrimaryKey(primaryKey);
				parseAssetFields(schemav,
						node.configurationsAt("fieldset.field"), "fieldname");

				SchemaViewManager.registerSchemaAssetView(viewName, schemav);
			}
		} catch (Exception e) {
			throw wrapException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void parseCodes(String xml) {
		try {
			XMLBean bean = new XMLBean(xml);
			SoapUtils.checkAndThrow(bean);
			List<HierarchicalConfiguration> nodes = bean
					.configurationsAt("getlistcode.code");
			for (HierarchicalConfiguration node : nodes) {
				String codename = node.getString("codename");
				Code code = new Code(codename, node.getString("caption"));
				List<HierarchicalConfiguration> items = node
						.configurationsAt("codeitem");
				for (HierarchicalConfiguration item : items) {
					Code.CodeItem codeitem = new Code.CodeItem(
							item.getString("codeitemname"),
							item.getString("caption"),
							item.getInt("order"),
							item.getString("exstrvalue"),
							item.getInt("exintvalue"));
					code.add(codeitem);
				}
				SchemaViewManager.registerCode(code);
			}
		} catch (Exception e) {
			throw wrapException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static String[] parsePermissions(String xml, String specialflag) {
		try {
			XMLBean bean = new XMLBean(xml);
			SoapUtils.checkAndThrow(bean);
			List<String> permissions = new ArrayList<String>();
			List<HierarchicalConfiguration> nodes = bean
					.configurationsAt("getspecialuserauthoritylist.authority");
			for (HierarchicalConfiguration node : nodes) {
				if (specialflag.equals(node.getString("specialflag"))) {
					permissions.add(node.getString("authorityname"));
				}
			}
			return permissions.toArray(new String[permissions.size()]);
		} catch (Exception e) {
			throw wrapException(e);
		}
	}
	
	
	public static void checkExError(String xml) {
		try {
			XMLBean bean = new XMLBean(xml);
			SoapUtils.checkAndThrow(bean);			
		} catch (Exception e) {
			throw wrapException(e);
		}
	}
//
//	private static Folder parseFolder(HierarchicalConfiguration node) {
//		int size = Integer.parseInt(node.getString("childcount"));
//		String identifier = node.getString("nodename");
//		String name = node.getString("caption");
//		Folder folder = ObjectFactory.createFolder(identifier, name);
//		folder.setProperty("hasChild", size > 0);
//		return folder;
//	}
//
//	@SuppressWarnings("unchecked")
//	private static Asset parseAsset(SchemaView schemav,
//			HierarchicalConfiguration node) {
//		Asset asset = ObjectFactory.createAsset(null);
//		List<HierarchicalConfiguration> metafields = node
//				.configurationsAt("metafield");
//		for (HierarchicalConfiguration metafield : metafields) {
//			String fieldName = metafield.getString("fieldname");
//			String fieldValue = metafield.getString("fieldvalue");
//			String fieldType = metafield.getString("fieldtype");
//			String dbValue = metafield.getString("dbvalue");
//			String caption = metafield.getString("caption");
//
//			FieldConverterFactory.get(fieldType).connvert(asset, fieldName,
//					fieldValue, dbValue, caption, schemav);
//		}
//		return asset;
//	}

	private static void parseSearchFields(SchemaView schemav,
                                          List<HierarchicalConfiguration> fields, String key) {
		for (HierarchicalConfiguration field : fields) {
			String fieldName = field.getString(key);
			String fieldType = field.getString("fieldtype");
			String ref = field.getString("ref");
			String caption = field.getString("caption");

			FieldDefinition fdef = new FieldDefinition(fieldType,
					caption);
			if (StringUtils.isNotEmpty(ref)) {
				fdef.setReferenceValue(ref);
			}
			
			String property = field.getString("property");
			parseFieldProperty(fdef, property);

			schemav.setFieldDefinition(fieldName, fdef);
		}
	}
	
	private static void parseFieldProperty(FieldDefinition fdef, String property) {
		if (property != null && property.length() > 0) {
			
			XMLBean bean;
			try {
				bean = new XMLBean(property);
				
			} catch (ConfigurationException e) {
				return;
			} catch (IOException e) {
				return;
			}
			if (bean.getRoot().getChildrenCount("uistyle") > 0) {
				SubnodeConfiguration uistyle = bean.configurationAt("uistyle");
				if (uistyle.containsKey("visible")) {
					fdef.setVisible(uistyle.getBoolean("visible"));
				}
				if (uistyle.containsKey("controltype")) {
					fdef.setControlType(uistyle.getString("controltype"));
				}	
			}								
		}
	}

	private static void parseAssetFields(SchemaView schemav,
                                         List<HierarchicalConfiguration> fields, String key) {
		for (HierarchicalConfiguration field : fields) {
			String fieldName = field.getString(key);
			String fieldType = field.getString("fieldtype");
			String ref = field.getString("ref");
			String caption = field.getString("caption");
			boolean readOnly = field.getBoolean("readonly", false);
			String keyFieldType = field.getString("keyfieldtype");
			if ("primary".equals(keyFieldType)
					&& schemav.getPrimaryKey() == null) {
				schemav.setPrimaryKey(fieldName);
			}		
			
			String property = field.getString("userproperty");			
			
			FieldDefinition fdef = new FieldDefinition(fieldType,
					caption, readOnly);
			if (StringUtils.isNotEmpty(ref)) {
				fdef.setReferenceValue(ref);
			}
			
			parseFieldProperty(fdef, property);

			schemav.setFieldDefinition(fieldName, fdef);
		}
	}
	
	public static boolean parseResultByBool(String api, String xml) {
		try {
			XMLBean bean = new XMLBean(xml);
			SoapUtils.checkAndThrow(bean);
			String result = bean.getString(api);
			
			return "true".equals(result) ? true : false;  
		} catch (Exception e) {  
			throw wrapException(e); 
		} 
	}

	private static DarcRuntimeException wrapException(Exception e) {
		return new DarcRuntimeException(e);
	}

}
