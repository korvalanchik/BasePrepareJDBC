package com.basejdbc.test;

import com.basejdbc.dao.ClientDao;
import com.basejdbc.dao.ProjectDao;
import com.basejdbc.dao.ProjectWorkerDao;
import com.basejdbc.dao.WorkerDao;
import com.basejdbc.emptity.Client;
import com.basejdbc.emptity.Project;
import com.basejdbc.emptity.ProjectWorker;
import com.basejdbc.emptity.Worker;
import com.basejdbc.service.ClientService;
import com.basejdbc.service.ProjectService;
import com.basejdbc.service.ProjectWorkerService;
import com.basejdbc.service.WorkerService;
import com.basejdbc.storage.DatabaseInitService;
import com.basejdbc.storage.Storage;
import lombok.SneakyThrows;

import java.util.List;

public class App {
    @SneakyThrows
    public static void main(String[] args){
        Storage storage = Storage.getInstance();
        new DatabaseInitService().initDb(storage);
        List<Worker> workers = new WorkerService().receiveWorker();
        WorkerDao.save(workers);
        List<Client> clients = new ClientService().receiveClient();
        ClientDao.save(clients);
        List<Project> projects = new ProjectService().receiveProject();
        ProjectDao.save(projects);
        List<ProjectWorker> projectWorkers = new ProjectWorkerService().receiveProjectWorker();
        ProjectWorkerDao.save(projectWorkers);
        if(!storage.getConnection().isClosed()) {
            storage.getConnection().close();
        }
        storage.close();
    }
}
