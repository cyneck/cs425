package com.group8.project.domain;

import com.group8.project.domain.dto.CreditCardDto;

import java.io.Serializable;
import java.time.LocalDate;

public class PropertyBooking implements Serializable {

    private String propertyId;
    private LocalDate bookingDate;
    private String status;
    private Renter renter;
    private CreditCardDto creditCard;

    public PropertyBooking() {
    }

    public PropertyBooking(String propertyId, LocalDate bookingDate, String status, Renter renter, CreditCardDto creditCard) {
        this.propertyId = propertyId;
        this.bookingDate = bookingDate;
        this.status = status;
        this.renter = renter;
        this.creditCard = creditCard;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public CreditCardDto getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardDto creditCard) {
        this.creditCard = creditCard;
    }
}
