package com.jack.core.permission;

public enum Modul {
	USER(1,"用户");
	private Modul(int id,String name){
		this.id=id;
		this.name=name;
	}
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
