package com.opserver.simpleapp.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Server entity
 * DEV-Note: Class is NOT Immutable as it impacts the hibernate CRUD requests i.e. save/delete. DAO Implmentation enforces
 * immutability
 */

@Entity
@ToString()
@EqualsAndHashCode() // Look at Object properties to see if both objects are the same when comparing
@Table
public class ServerEntity implements Serializable {

    /**
     * Serialization id
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @Column(length = 50, nullable = false, unique = true, updatable = true)
    private String name;

    private ServerEntity() {
        super();
    }

    public ServerEntity(String name) {
        super();
        this.name = name;
    }

    public ServerEntity(String id, String name) {
        super();
        this.name = name;
        this.id = id;
    }

    public String getServerId() {
        return id;
    }

    public String getName() {
        return name;
    }

}