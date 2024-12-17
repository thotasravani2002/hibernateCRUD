package com.codegnan.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity is for mapping the class with DB table
@Entity
/*
 * @Table is optional, its useful when Table name and classname are different
 */
@Table(name = "employee1")
public class Employee {
	@Id
	@Column(name = "eid")
	private int eid;
	@Column(name = "ename")
	private String ename;
	@Column(name = "esal")
	private float esal;
	@Column(name = "eaddr")
	private String eaddr;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public float getEsal() {
		return esal;
	}

	public void setEsal(float esal) {
		this.esal = esal;
	}

	public String getEaddr() {
		return eaddr;
	}

	public void setEaddr(String eaddr) {
		this.eaddr = eaddr;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", esal=" + esal + ", eaddr=" + eaddr + "]";
	}

	
	
}
