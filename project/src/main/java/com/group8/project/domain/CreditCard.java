package com.group8.project.domain;

import java.io.Serializable;


public class CreditCard implements Serializable {
    private static final long serialVersionUID = -71926779120315354L;
    
    private String cardNo;
    
    private String email;
    
    private String addressId;

    private String address;
    
    private String bank;

    public CreditCard() {
    }

    public CreditCard(String cardNo, String email, String addressId, String bank) {
        this.cardNo = cardNo;
        this.email = email;
        this.addressId = addressId;
        this.bank = bank;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

}

