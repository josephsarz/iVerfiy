package com.codegene.femicodes.cscproject.model;

/**
 * Created by femicodes on 1/12/2018.
 */

public class Report {
    private String productName;
    private String productType;
    private String storeLocation;
    private String complainDetails;
    private String complaintName;
    private String complaintPhoneNo;
    private String dateOfComplain;
    private String nafdacNumber;

    public Report(String productName, String productType, String nafdacNumber, String storeLocation, String complainDetails, String complaintName, String complaintPhoneNo, String dateOfComplain) {
        this.productName = productName;
        this.productType = productType;
        this.storeLocation = storeLocation;
        this.complainDetails = complainDetails;
        this.complaintName = complaintName;
        this.complaintPhoneNo = complaintPhoneNo;
        this.nafdacNumber = nafdacNumber;
        this.dateOfComplain = dateOfComplain;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getComplainDetails() {
        return complainDetails;
    }

    public void setComplainDetails(String complainDetails) {
        this.complainDetails = complainDetails;
    }

    public String getComplaintName() {
        return complaintName;
    }

    public void setComplaintName(String complaintName) {
        this.complaintName = complaintName;
    }

    public String getComplaintPhoneNo() {
        return complaintPhoneNo;
    }

    public void setComplaintPhoneNo(String complaintPhoneNo) {
        this.complaintPhoneNo = complaintPhoneNo;
    }

    public String getDateOfComplain() {
        return dateOfComplain;
    }

    public void setDateOfComplain(String dateOfComplain) {
        this.dateOfComplain = dateOfComplain;
    }

    public String getNafdacNumber() {
        return nafdacNumber;
    }

    public void setNafdacNumber(String nafdacNumber) {
        this.nafdacNumber = nafdacNumber;
    }
}
