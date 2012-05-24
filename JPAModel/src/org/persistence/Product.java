package org.persistence;

import javax.persistence.*;

@Entity
@Table(name = "T_PRODUCT")
@NamedQuery(name = "AllProducts", query = "select p from Product p")
public class Product {

	@Id
	private int id;
	@Basic
	private String pName;
	@Basic
	private String pDescription;
	@Basic
	private String pPrice;
	@Basic
	private String pQuantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPName(String param) {
		this.pName = param;
	}

	public String getPName() {
		return pName;
	}

	public void setPDescription(String param) {
		this.pDescription = param;
	}

	public String getPDescription() {
		return pDescription;
	}

	public void setPPrice(String param) {
		this.pPrice = param;
	}

	public String getPPrice() {
		return pPrice;
	}

	public void setPQuantity(String param) {
		this.pQuantity = param;
	}

	public String getPQuantity() {
		return pQuantity;
	}

}