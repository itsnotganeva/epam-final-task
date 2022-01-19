package com.epam.service;

import com.epam.dao.ApplicationDao;
import com.epam.entity.ApartmentClass;
import com.epam.entity.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class ApplicationService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);

    private static ApplicationService instance;
    ApplicationDao applicationDao = ApplicationDao.getInstance();

    private ApplicationService() {
        instance = this;
    }

    public static ApplicationService getInstance() {
        if (instance == null) {
            ApplicationService.instance = new ApplicationService();
        }
        return instance;
    }

    public Application apply(Application application) {

        Application save = ApplicationDao.getInstance().save(application);

        return save;
    }

    public Application getById(Long id) {
        return applicationDao.getById(id);
    }

    public List<Application> getAll() {
        return ApplicationDao.getInstance().findAll();
    }

    public void delete(Application application) {
        ApplicationDao.getInstance().deleteById(application.getId());
    }
}
