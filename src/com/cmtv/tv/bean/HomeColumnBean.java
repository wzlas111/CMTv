package com.cmtv.tv.bean;

import java.util.List;

public class HomeColumnBean {
	
	private List<HomeColumn> columns;

	public class HomeColumn {
		private int id;
		private String code;
		private String name;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

	public List<HomeColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<HomeColumn> columns) {
		this.columns = columns;
	}
	
}
