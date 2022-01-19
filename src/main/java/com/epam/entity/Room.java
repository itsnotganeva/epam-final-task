package com.epam.entity;

import com.epam.dao.WithId;

import java.sql.Date;

public class Room implements WithId {
    private Long id;
    private Integer seatsNumber;
    private ApartmentClass apartmentsClass;
    private Date releaseDate;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public ApartmentClass getApartmentsClass() {
        return apartmentsClass;
    }

    public void setApartmentsClass(ApartmentClass apartmentsClass) {
        this.apartmentsClass = apartmentsClass;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
