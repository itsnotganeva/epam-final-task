package com.epam.dao;

import com.epam.dao.jdbc.ConnectionPoolProvider;
import com.epam.dao.mapping.PersonMapper;
import com.epam.dao.mapping.RoomMapper;
import com.epam.entity.Application;
import com.epam.entity.Person;
import com.epam.entity.Room;
import com.epam.exception.EntityNotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RoomDao extends AbstractDao<Room> {

    private static RoomDao instance;

    private RoomDao() {
        super(new RoomMapper(), "room");
        instance = this;
    }

    public static RoomDao getInstance() {
        if (instance == null) {
            RoomDao.instance = new RoomDao();
        }
        return instance;
    }

    @Override
    protected Map<String, String> updateValues(Room room) {
        return Map.of("seats_number",room.getSeatsNumber().toString(),
                "apartments_class", String.valueOf(room.getApartmentsClass().ordinal()),
                "release_date",  "'" + room.getReleaseDate().toString() + "'",
                "price", room.getPrice().toString());
    }

    public Room getRoomByApplication(Application application) {
        String SELECT_BY_APP = String.format(SELECT_STATEMENT, " room ").concat("WHERE seats_number = ")
                .concat(application.getSeatsNumber().toString()).concat(" AND apartments_class = ")
                .concat(String.valueOf(application.getApartmentsClass().ordinal()))
                .concat(" AND release_date::date <= ").concat("'")
                .concat(application.getCheckInDate().toString()).concat("'::date");
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            ResultSet resultSet = connection.createStatement().
                    executeQuery(String.format(SELECT_BY_APP));

            if (resultSet.next()) {
                Room room = getRm().toObject(resultSet);
                return room;
            } else {
                return null;
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during user retrieval by login=", e);
            throw new EntityNotFoundException(e);
        }

    }
}
