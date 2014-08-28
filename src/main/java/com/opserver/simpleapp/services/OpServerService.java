package com.opserver.simpleapp.services;

import com.opserver.simpleapp.model.ServerEntity;

import java.util.List;

public interface OpServerService {

    public ServerEntity findById(String entityId);

    public List<ServerEntity> findAll();

    public String save(String name);

    public void update(String entityId, String name);

    public void delete(String entityId);

    public String loadXMLFile(String pathToFile);
}
