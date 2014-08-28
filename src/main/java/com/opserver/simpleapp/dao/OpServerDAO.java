package com.opserver.simpleapp.dao;

import com.opserver.simpleapp.model.ServerEntity;

import java.util.List;

public interface OpServerDAO {

    // Simple CRUD actions
    ServerEntity findById(String id);

    List<ServerEntity> findAll();

    String save(ServerEntity serverEntity);

    void update(ServerEntity serverEntity);

    void delete(ServerEntity serverEntity);
}
