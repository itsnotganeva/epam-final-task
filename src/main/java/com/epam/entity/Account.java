package com.epam.entity;

import com.epam.dao.WithId;

public class Account implements WithId {
    private Long id;
    private Long personId;
    private Long roomId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", personId=" + personId +
                ", roomIdl=" + roomId +
                '}';
    }
}
