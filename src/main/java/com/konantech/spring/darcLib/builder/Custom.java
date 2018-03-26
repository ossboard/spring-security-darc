package com.konantech.spring.darcLib.builder;

import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

public class Custom {
	private static final String BUNDLE_NAME = "builder.custom"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Custom() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}
	
	public static Set<String> keySet(String base) {
		String prefix = new StringBuffer(base).append('.').toString();
		Set<String> set = new HashSet<String>();
		for (String key : RESOURCE_BUNDLE.keySet()) {
			if (key.startsWith(prefix)) {
				set.add(key);
			}
		}
		return set;
	}

}
