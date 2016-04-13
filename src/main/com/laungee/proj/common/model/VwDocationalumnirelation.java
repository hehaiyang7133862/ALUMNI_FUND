package com.laungee.proj.common.model;



/**
 * VwDocationalumnirelationId entity. @author MyEclipse Persistence Tools
 */

public class VwDocationalumnirelation  implements java.io.Serializable {


    // Fields    

     private Long alumniId;
     private String mailFirst;
     private String nameCn;
     private String telephoneFirst;
     private String handsetFirst;
     private String studyYear;
     private String studyMajor;
     private String studyClass;
     private String studyAcademy;
     private String studyDepartment;
     private String studyDegree;
     private String liveYear;
     private String address;
     private String postcode;
 	 private String confidenceFid; 
 	private Long alumniLevel;
	private String alumniOrgan;
    // Constructors

    /** default constructor */
    public VwDocationalumnirelation() {
    }

	/** minimal constructor */
    public VwDocationalumnirelation(Long alumniId) {
        this.alumniId = alumniId;
    }
    
    /** full constructor */
    public VwDocationalumnirelation(Long alumniId, String mailFirst, String nameCn, String telephoneFirst, String handsetFirst, String studyYear, String studyMajor, String studyClass, String studyAcademy, String studyDepartment, String studyDegree, String liveYear, String address, String postcode) {
        this.alumniId = alumniId;
        this.mailFirst = mailFirst;
        this.nameCn = nameCn;
        this.telephoneFirst = telephoneFirst;
        this.handsetFirst = handsetFirst;
        this.studyYear = studyYear;
        this.studyMajor = studyMajor;
        this.studyClass = studyClass;
        this.studyAcademy = studyAcademy;
        this.studyDepartment = studyDepartment;
        this.studyDegree = studyDegree;
        this.liveYear = liveYear;
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

    public String getTelephoneFirst() {
        return this.telephoneFirst;
    }
    
    public void setTelephoneFirst(String telephoneFirst) {
        this.telephoneFirst = telephoneFirst;
    }

    public String getHandsetFirst() {
        return this.handsetFirst;
    }
    
    public void setHandsetFirst(String handsetFirst) {
        this.handsetFirst = handsetFirst;
    }

    public String getStudyYear() {
        return this.studyYear;
    }
    
    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    public String getStudyMajor() {
        return this.studyMajor;
    }
    
    public void setStudyMajor(String studyMajor) {
        this.studyMajor = studyMajor;
    }

    public String getStudyClass() {
        return this.studyClass;
    }
    
    public void setStudyClass(String studyClass) {
        this.studyClass = studyClass;
    }

    public String getStudyAcademy() {
        return this.studyAcademy;
    }
    
    public void setStudyAcademy(String studyAcademy) {
        this.studyAcademy = studyAcademy;
    }

    public String getStudyDepartment() {
        return this.studyDepartment;
    }
    
    public void setStudyDepartment(String studyDepartment) {
        this.studyDepartment = studyDepartment;
    }

    public String getStudyDegree() {
        return this.studyDegree;
    }
    
    public void setStudyDegree(String studyDegree) {
        this.studyDegree = studyDegree;
    }

    public String getLiveYear() {
        return this.liveYear;
    }
    
    public void setLiveYear(String liveYear) {
        this.liveYear = liveYear;
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

	public String getConfidenceFid() {
		return confidenceFid;
	}

	public void setConfidenceFid(String confidenceFid) {
		this.confidenceFid = confidenceFid;
	}

	public Long getAlumniLevel() {
		return alumniLevel;
	}

	public void setAlumniLevel(Long alumniLevel) {
		this.alumniLevel = alumniLevel;
	}

	public String getAlumniOrgan() {
		return alumniOrgan;
	}

	public void setAlumniOrgan(String alumniOrgan) {
		this.alumniOrgan = alumniOrgan;
	}
    


}