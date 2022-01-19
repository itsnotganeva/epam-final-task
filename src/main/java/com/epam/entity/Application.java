package com.epam.entity;

import com.epam.dao.WithId;

import java.sql.Date;
import java.util.Objects;

public class Application implements WithId {
    private Long id;
    private Integer seatsNumber;
    private ApartmentClass apartmentsClass;
    private Date checkInDate;
    private Date checkOutDate;
    private Long personId;
    private Person person;

    public Application() {

    }

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

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(id, that.id) && Objects.equals(seatsNumber, that.seatsNumber) && apartmentsClass == that.apartmentsClass && Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate) && Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seatsNumber, apartmentsClass, checkInDate, checkOutDate, personId);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", seatsNumber=" + seatsNumber +
                ", apartmentsClass=" + apartmentsClass +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", personId=" + personId +
                ", person=" + person +
                '}';
    }
}
