package com.opserver.simpleapp.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class DBTest {

    @Autowired
    private SessionFactory sessionFactory;
    private Session currentSession;

    @Before
    public void openSession() {
        currentSession = sessionFactory.getCurrentSession();
    }

    @Test
    public void shouldHaveASessionFactory() {
        assertNotNull(sessionFactory);
    }

    @Test
    public void shouldHaveNoObjectsAtStart() {
        List<?> results = currentSession.createQuery("from ServerEntity").list();
        assertTrue(results.isEmpty());
    }
}
