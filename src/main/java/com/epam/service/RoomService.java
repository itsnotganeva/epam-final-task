package com.epam.service;

import com.epam.dao.AccountDao;
import com.epam.dao.RoomDao;
import com.epam.entity.Account;
import com.epam.entity.Application;
import com.epam.entity.Room;

import java.util.List;

public class RoomService {

    private static RoomService instance;

    private RoomService() {
        instance = this;
    }

    public static RoomService getInstance() {
        if (instance == null) {
            RoomService.instance = new RoomService();
        }
        return instance;
    }

    public Room getRoomByApp(Application application) {
        return RoomDao.getInstance().getRoomByApplication(application);
    }

    public Room update(Room room) {
        return RoomDao.getInstance().save(room);
    }

    public Room getById(Long id) {
        return RoomDao.getInstance().getById(id);
    }

}
