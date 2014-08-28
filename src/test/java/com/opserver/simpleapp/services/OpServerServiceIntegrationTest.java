package com.opserver.simpleapp.services;

import com.google.common.collect.ImmutableSet;
import com.opserver.simpleapp.model.ServerEntity;
import com.opserver.simpleapp.spring.PersistenceXmlConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = {PersistenceXmlConfig.class}, loader = AnnotationConfigContextLoader.class)
public class OpServerServiceIntegrationTest {

    @Autowired
    private OpServerService opServerService;

    @Test
    public final void testCRUDOperations() {
        // Setup. Create initial entity
        final ServerEntity testServerEntityOne = new ServerEntity("Test1");
        final ServerEntity testServerEntityTwo = new ServerEntity("Test2");

        // 1. Save
        opServerService.save(testServerEntityOne.getName());
        opServerService.save(testServerEntityTwo.getName());
        List<ServerEntity> entityList = opServerService.findAll();
        assertTrue(!entityList.isEmpty());
        ServerEntity entity = null;
        for (ServerEntity obj : entityList) {
            if (obj.getName().equals(testServerEntityOne.getName()))
                entity = obj;
        }
        assert entity != null;

        // 2. Read
        final ServerEntity readServerEntity = opServerService.findById(entity.getServerId());
        assert readServerEntity != null;
        assertEquals(readServerEntity, entity); // Validates EqualsAndHashCode on entity

        // 3. Update
        opServerService.update(readServerEntity.getServerId(), "UpdateTest");
        Set<ServerEntity> updatedSet = ImmutableSet.copyOf(opServerService.findAll());
        assertTrue(!updatedSet.isEmpty());
        assertTrue(updatedSet.contains(new ServerEntity(readServerEntity.getServerId(), "UpdateTest"))); // Validates EqualsAndHashCode on entity

        // 4. Delete
        opServerService.delete(readServerEntity.getServerId());
        Set<ServerEntity> deleteSet = ImmutableSet.copyOf(opServerService.findAll());
        assertTrue(!deleteSet.isEmpty());
        assertTrue(!deleteSet.contains(readServerEntity)); // Validate item has been removed

    }

    public final void testDeletingNonExistentItem() {
        final ServerEntity serverEntity = new ServerEntity("DeletedItem");

        opServerService.save(serverEntity.getName());
        List<ServerEntity> entityList = opServerService.findAll();
        assertTrue(!entityList.isEmpty());

        ServerEntity entity = null;
        for (ServerEntity obj : entityList) {
            if (obj.getName().equals(serverEntity.getName()))
                entity = obj;
        }
        assert entity != null;

        opServerService.delete(entity.getServerId());
        opServerService.delete(entity.getServerId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public final void testDuplicateItems() {
        final ServerEntity serverEntity1 = new ServerEntity("DuplicateItem");

        opServerService.save(serverEntity1.getName());
        opServerService.save(serverEntity1.getName());
    }

    @Test
    public void testStreamToString() {
        assertNotNull("Test file missing", getClass().getResource("/testServer.xml"));
    }

//    @Test
//    public final void testLoadingXMLFile() {
//
//        final String pathToFile = "/src/test/resources/testServer.xml";
//        assert opServerService.loadXMLFile(pathToFile).containsKey(true);
//
//        Set<ServerEntity> set = ImmutableSet.copyOf(opServerService.findAll());
//        assertTrue(!set.isEmpty());
//
//        // Validate file was loaded and saved
//        boolean found = false;
//        for (Iterator<ServerEntity> it = set.iterator(); it.hasNext(); ) {
//            ServerEntity serverEntity = it.next();
//
//            if (serverEntity.getName().equalsIgnoreCase("MyServerTest")) {
//                found = true;
//            }
//        }
//        assert found == true;
//
//
//    }

}