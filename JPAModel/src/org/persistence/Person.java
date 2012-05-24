package org.persistence;

import javax.persistence.*;

@Entity
@Table(name = "T_PERSON")
@NamedQuery(name = "AllPerson", query = "select p from Person p\r\n")
public class Person {

	@Id
	@GeneratedValue
	private long id;
	@Basic
	private String firstName;
	@Basic
	private String lastName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String param) {
		this.firstName = param;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String param) {
		this.lastName = param;
	}

	public String getLastName() {
		return lastName;
	}

}