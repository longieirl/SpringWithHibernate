package com.opserver.simpleapp.services.impl;

import com.opserver.simpleapp.dao.OpServerDAO;
import com.opserver.simpleapp.model.ServerEntity;
import com.opserver.simpleapp.services.OpServerService;
import com.opserver.simpleapp.utils.Consts;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.hibernate.annotations.Immutable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Dev-Note: These CRUD operations are the entry point for all DAO operations. Each invocation of these methods creates
 * a new transaction. Enabled readonly transaction on find* methods so that they dont suspended read/write transactions.
 */
@Service
public class OpServerServiceImpl implements OpServerService {

    public static final Logger LOGGER = LoggerFactory.getLogger(OpServerServiceImpl.class);

    @Autowired
    private OpServerDAO opServerDAO;

    @Override
    @Immutable
    @Transactional(readOnly = true)
    public final ServerEntity findById(final String entityId) {
        LOGGER.debug("findById(entityId=" + entityId + ")");

        return opServerDAO.findById(entityId);
    }

    @Override
    @Immutable
    @Transactional(readOnly = true)
    public final List<ServerEntity> findAll() {
        LOGGER.debug(">>findAll()");

        return opServerDAO.findAll();
    }

    @Override
    @Transactional
    public final String save(final String name) {
        LOGGER.debug(">>save(name=" + name + ") ");

        final ServerEntity serverEntity = new ServerEntity(name);
        return opServerDAO.save(serverEntity);
    }

    @Override
    @Transactional
    public final void update(final String entityId, final String name) {
        LOGGER.debug(">>update(entityId=" + entityId + ", name=" + name + ") ");

        final ServerEntity serverEntity = new ServerEntity(entityId, name);
        opServerDAO.update(serverEntity);
    }

    @Override
    @Transactional
    public final void delete(final String serverEntityId) {
        LOGGER.debug(">>delete(serverEntityId=" + serverEntityId + ")");

        ServerEntity serverEntity = findById(serverEntityId);
        if(serverEntity != null){
            opServerDAO.delete(serverEntity);
            System.out.println(String.format(Consts.ENTITY_REMOVED, serverEntityId));
        } else {
            // TODO Look at exception handling for this use case
            System.out.println("Item was not found ");
        }
    }

    @Override
    @Transactional
    public final String loadXMLFile(final String pathToFile) {
        LOGGER.debug(">>loadXMLFile(pathToFile=" + pathToFile + ")");

        String entityId = "";
        try {
            // Support Namespace of xs2
            QNameMap qmap = new QNameMap();
            qmap.setDefaultNamespace("http://www.opsource.net/simpleapp");
            qmap.setDefaultPrefix("xs2");
            StaxDriver staxDriver = new StaxDriver(qmap);

            InputStream in = new FileInputStream(pathToFile);
            XStream xstream = new XStream(staxDriver);     // init XStream

            // define root alias so XStream knows which element and which class are equivalent
            xstream.alias("server", ServerEntity.class);
            final ServerEntity newServer = (ServerEntity) xstream.fromXML(in);

            // Save server name to database, will create a new generated UUID
            entityId = this.save(newServer.getName());

        } catch (Throwable ex) {
            LOGGER.error("loadXMLFile(), exception=" + ex.getMessage());
        }

        return entityId; // Single exit point of method

    }

}
