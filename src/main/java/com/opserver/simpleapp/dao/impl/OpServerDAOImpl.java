package com.opserver.simpleapp.dao.impl;

import com.google.common.base.Preconditions;
import com.opserver.simpleapp.dao.OpServerDAO;
import com.opserver.simpleapp.model.ServerEntity;
import org.hibernate.annotations.Immutable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate4.HibernateObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Note 1. Preconditions have been added to ensure that everything at this stage is valid
 *
 * Note 2. Possible changes to have a generic exception handling approach
 * Dev-note: Catch exception and stop checked exceptions percolating across the application
 * catch(DataAccessException ex){
 *      throws new OpServerException(); // Our Business/Custom exception
 *      }
 * catch(Exception ex){
 *      throws new OpServerException();  //  Our Business/Custom exception
 * }
 *
 */

@Repository
public class OpServerDAOImpl extends BaseDAO implements OpServerDAO {

    public static final Logger LOGGER = LoggerFactory.getLogger(OpServerDAOImpl.class);

    @Override
    @Immutable
    public final ServerEntity findById(final String entityId) {
        LOGGER.debug(">>findById(id=" + entityId + ")");
        Preconditions.checkNotNull(entityId);
        return (ServerEntity) this.getCurrentSession().get(ServerEntity.class, entityId);
    }

    @Override
    @Immutable
    public final List<ServerEntity> findAll() {
        LOGGER.debug("findAll()");
        return this.getCurrentSession().createQuery("from ServerEntity").list();
    }

    @Override
    public final String save(final ServerEntity serverEntity) throws DataIntegrityViolationException {
        LOGGER.debug(">>start(serverEntity=" + serverEntity + ")");
        Preconditions.checkNotNull(serverEntity);
        return (String)this.getCurrentSession().save(serverEntity);
    }

    @Override
    public final void update(final ServerEntity serverEntity) {
        LOGGER.debug(">>update(serverEntity=" + serverEntity + ")");
        Preconditions.checkNotNull(serverEntity);
        this.getCurrentSession().merge(serverEntity);
    }

    @Override
    public final void delete(final ServerEntity serverEntity) {
        LOGGER.debug(">>delete(serverEntity=" + serverEntity + ")");
        Preconditions.checkNotNull(serverEntity);
        this.getCurrentSession().delete(serverEntity);
    }

}
