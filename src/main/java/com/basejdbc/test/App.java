package com.basejdbc.test;

import com.basejdbc.dao.ClientDao;
import com.basejdbc.dao.ServiceDao;
import com.basejdbc.emptity.Client;
import com.basejdbc.storage.DatabaseInitService;
import com.basejdbc.storage.DatabasePopulateService;
import com.basejdbc.storage.Storage;
import lombok.SneakyThrows;

import java.sql.Connection;

@SuppressWarnings("java:S106")
public class App {
    @SneakyThrows
    public static void main(String[] args){
        Storage storage = Storage.getInstance();
        Connection conn = Storage.getInstance().getConnection();

//        new DatabaseInitService().initDb(storage);
//        new DatabasePopulateService().setDbPopulate(storage);
//
//        ServiceDao serviceDao = new ServiceDao(conn);
//        System.out.println(serviceDao.findMaxProjectsClient());
//        System.out.println(serviceDao.findMaxSalaryWorker());
//        System.out.println(serviceDao.findYoungestEldestWorkers());
//        System.out.println(serviceDao.findLongestProject());
//        System.out.println(serviceDao.printProjectPrice());

        Client client = new Client(null,"Smith");
        ClientDao.save(client);

        storage.close();
    }
}
