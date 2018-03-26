package com.konantech.spring.darcLib.model;

import java.util.ArrayList;
import java.util.List;

public class Code {

	private String name;
	private String caption;
	private List<CodeItem> items;
	
	public Code(String name, String caption) {
		super();
		this.name = name;
		this.caption = caption;
		this.items = new ArrayList<CodeItem>();
	}
	
	public String getName() {
		return name;
	}

	public String getCaption() {
		return caption;
	}

	public void add(CodeItem item) {
		items.add(item);
	}
	
	public String getCaption(String code) {
		for (CodeItem item : items) {
			if (item.getName().equals(code)) {
				return item.getCaption();
			}
		}
		return null;
	}

	public List<CodeItem> getCodeItems() {
		return items;
	}

	public static class CodeItem {
		private String name;
		private String caption;
		private String exStrValue;
		private int exIntValue;
		private int order;
		
		public CodeItem(String name, String caption, int order, String exStrValue, int exIntValue) {
			super();
			this.name = name;
			this.caption = caption;
			this.order = order;
			this.exStrValue = exStrValue;
			this.exIntValue = exIntValue;
		}

		public String getName() {
			return name;
		}

		public String getCaption() {
			return caption;
		}
		
		public int getOrder() {
			return order;
		}
		
		public String getExStrValue() {
			return exStrValue;
		}

		public int getExIntValue() {
			return exIntValue;
		}
	}
	
}
