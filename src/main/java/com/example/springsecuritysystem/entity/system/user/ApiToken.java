package com.example.springsecuritysystem.entity.system.user;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the API_TOKEN database table.
 * 
 */
@Entity
@Table(name="API_TOKEN")
@NamedQuery(name="ApiToken.findAll", query="SELECT a FROM ApiToken a")
public class ApiToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date ed;

	@Column(name="KEY_STRING")
	private String keyString;

	@Temporal(TemporalType.DATE)
	private Date td;

	public ApiToken() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getEd() {
		return this.ed;
	}

	public void setEd(Date ed) {
		this.ed = ed;
	}

	public String getKeyString() {
		return this.keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public Date getTd() {
		return this.td;
	}

	public void setTd(Date td) {
		this.td = td;
	}

}