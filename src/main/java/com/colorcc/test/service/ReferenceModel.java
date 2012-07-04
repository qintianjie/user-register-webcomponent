package com.colorcc.test.service;

public class ReferenceModel {
	
	private long count;
	
	private String name;
	
	private String desc;
	
	public ReferenceModel(long count, String name, String desc) {
		this.count = count;
		this.name = name;
		this.desc = desc;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}