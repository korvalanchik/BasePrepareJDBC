package com.basejdbc.test;

import com.basejdbc.dao.ClientDao;
import com.basejdbc.emptity.Client;
import com.basejdbc.service.ClientService;
import com.basejdbc.storage.DatabaseInitService;
import com.basejdbc.storage.DatabasePopulateService;
import com.basejdbc.storage.Storage;
import lombok.SneakyThrows;

import java.util.List;


@SuppressWarnings("java:S106")
public class App {
    @SneakyThrows
    public static void main(String[] args){
        Storage storage = Storage.getInstance();
        new DatabaseInitService().initDb(storage);
//        new DatabasePopulateService().setDbPopulate(storage);
        List<Client> clients = new ClientService().receiveClient();
        ClientDao.save(clients);
        storage.close();
    }
}
