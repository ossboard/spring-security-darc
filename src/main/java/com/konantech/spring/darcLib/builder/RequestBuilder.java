package com.konantech.spring.darcLib.builder;

import com.konantech.spring.darcLib.EscapeUtils;
import com.konantech.spring.darcLib.SchemaViewManager;
import com.konantech.spring.darcLib.query.Query;
import com.konantech.spring.darcLib.query.SearchControls;
import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class RequestBuilder {
	
	public static String search(Query query, SearchControls controls) {
		String pattern = Messages.getString("session.request.search"); //$NON-NLS-1$
		String view = SchemaViewManager.getSearchViewName(query.getMediaType());
		int page = controls.getLimit() == 0 ? 0 : controls.getOffset() / controls.getLimit();
		return MessageFormat.format(pattern,
				query.getSelectMode(),
				page,
				controls.getLimit(),
				condition(query),
				sorting(query),
				view
		);
	}
	
	private static String condition(Query query) {
		return new ConditionBuilder().build(query);
	}
	
	private static String sorting(Query query) {
		List<Query.SortingOrder> ordering = query.getSortingOrder();
		if (ordering.isEmpty()) {
			return StringUtils.EMPTY;
		}
		String pattern = Messages.getString("session.request.search.sort"); //$NON-NLS-1$
		String sorting = "";
		
		for (Query.SortingOrder aOrder : ordering) {
			sorting += MessageFormat.format(pattern,
					new Object[] { aOrder.getOrdering(), aOrder.isAscending() });
		}
		
		return sorting;
	}
	
	public static String asset(Properties properties) {
		String metaset = Messages.getString("request.metaset");
		String pattern = Messages.getString("metafield");
		StringBuffer buffer = new StringBuffer();
		for (String key : properties.stringPropertyNames()) {
			String data = properties.getProperty(key);
			buffer.append(MessageFormat.format(pattern, key,
					EscapeUtils.escapeXml(data)));
		}
		return MessageFormat.format(metaset, buffer.toString());
	}

	public static String buildDarc(String messageName, Object[] paramValue) {
		String buffer = Messages.getString(messageName);
		if (paramValue != null || paramValue.length > 0) {
			String requestXML = MessageFormat.format(buffer, paramValue);
			return requestXML;
		} else {
			return buffer;
		}

	}

}
