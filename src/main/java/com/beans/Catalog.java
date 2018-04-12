package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="catalog")
public class Catalog {

	@Id
	@Column(name="catalog_id")
	private int catalog_id;
	@Column(name="catalog_name")
	private String catalog_name;
	@Column(name="target_gender")
	private String target_gender;
	
	public Catalog() {}
	
	public Catalog(int catalog_id, String catalog_name, String target_gender) {
		this.catalog_id = catalog_id;
		this.catalog_name = catalog_name;
		this.target_gender = target_gender;
	}

	public int getCatalog_id() {
		return catalog_id;
	}

	public void setCatalog_id(int catalog_id) {
		this.catalog_id = catalog_id;
	}

	public String getCatalog_name() {
		return catalog_name;
	}

	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}

	public String getTarget_gender() {
		return target_gender;
	}

	public void setTarget_gender(String target_gender) {
		this.target_gender = target_gender;
	}
	
	@Override
	public String toString () {
		return "Catalog [catalog_id=" + catalog_id + ", catalog_name=" + catalog_name + ", target_gender=" + target_gender + "]";
	}
}
