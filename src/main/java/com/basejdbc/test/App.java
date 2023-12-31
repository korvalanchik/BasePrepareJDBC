package com.basejdbc.test;

import com.basejdbc.dao.ClientDao;
import com.basejdbc.dao.WorkerDao;
import com.basejdbc.emptity.Client;
import com.basejdbc.emptity.Worker;
import com.basejdbc.service.ClientService;
import com.basejdbc.service.WorkerService;
import com.basejdbc.storage.DatabaseInitService;
import com.basejdbc.storage.Storage;
import lombok.SneakyThrows;

import java.util.List;


@SuppressWarnings("java:S106")
public class App {
    @SneakyThrows
    public static void main(String[] args){
        Storage storage = Storage.getInstance();
        new DatabaseInitService().initDb(storage);
        List<Worker> workers = new WorkerService().receiveWorker();
        WorkerDao.save(workers);
        List<Client> clients = new ClientService().receiveClient();
        ClientDao.save(clients);
        if(!storage.getConnection().isClosed()) {
            storage.getConnection().close();
        }
        storage.close();
    }
}
