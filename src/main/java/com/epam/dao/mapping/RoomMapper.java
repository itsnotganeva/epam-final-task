package com.epam.dao.mapping;

import com.epam.entity.ApartmentClass;
import com.epam.entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room>{

    @Override
    public Room toObject(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getLong("id"));
        room.setSeatsNumber(rs.getInt("seats_number"));
        int ordinal = rs.getInt("apartments_class");
        room.setApartmentsClass(ApartmentClass.getByOrdinal(ordinal));
        room.setReleaseDate(rs.getDate("release_date"));
        room.setPrice(rs.getDouble("price"));
        return room;
    }
}
