package com.group8.project.domain.dto;

import com.group8.project.domain.Address;
import com.group8.project.domain.Renter;

import java.io.Serializable;

public class CreditCardDto implements Serializable {

    private String cardNo;

    private Renter renter;

    private Address address;

    private String bank;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
