package com.laungee.proj.common.model;

/**
 * VwAlumnirelation generated by MyEclipse Persistence Tools
 */

public class VwAlumnirelation implements java.io.Serializable {

	// Fields

	private Long alumniId;
	private String mailFirst;
	private String nameCn;
	private String relationName;
	private String address;
	private String postcode;

	// Constructors

	/** default constructor */
	public VwAlumnirelation() {
	}

	/** minimal constructor */
	public VwAlumnirelation(Long alumniId) {
		this.alumniId = alumniId;
	}

	/** full constructor */
	public VwAlumnirelation(Long alumniId, String mailFirst, String nameCn,
			String relationName, String address, String postcode) {
		this.alumniId = alumniId;
		this.mailFirst = mailFirst;
		this.nameCn = nameCn;
		this.relationName = relationName;
		this.address = address;
		this.postcode = postcode;
	}

	// Property accessors

	public Long getAlumniId() {
		return this.alumniId;
	}

	public void setAlumniId(Long alumniId) {
		this.alumniId = alumniId;
	}

	public String getMailFirst() {
		return this.mailFirst;
	}

	public void setMailFirst(String mailFirst) {
		this.mailFirst = mailFirst;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getRelationName() {
		return this.relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}