package com.basejdbc.test;

import com.basejdbc.dao.ClientDao;
import com.basejdbc.dao.ProjectDao;
import com.basejdbc.dao.WorkerDao;
import com.basejdbc.emptity.Client;
import com.basejdbc.emptity.Project;
import com.basejdbc.emptity.Worker;
import com.basejdbc.service.ClientService;
import com.basejdbc.service.ProjectService;
import com.basejdbc.service.WorkerService;
import com.basejdbc.storage.Storage;
import lombok.SneakyThrows;

import java.util.List;


@SuppressWarnings("java:S106")
public class App {
    @SneakyThrows
    public static void main(String[] args){
        Storage storage = Storage.getInstance();
//        new DatabaseInitService().initDb(storage);
        List<Worker> workers = new WorkerService().receiveWorker();
        WorkerDao.save(workers);
        List<Client> clients = new ClientService().receiveClient();
        ClientDao.save(clients);
        List<Project> projects = new ProjectService().receiveProject();
        ProjectDao.save(projects);
        if(!storage.getConnection().isClosed()) {
            storage.getConnection().close();
        }
        storage.close();
    }
}
