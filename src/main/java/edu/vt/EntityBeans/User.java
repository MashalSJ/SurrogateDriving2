/*
 * Created by Team13 on 2021.7.20
 * Copyright © 2021 Team13. All rights reserved.
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
The @Entity annotation designates this class as a JPA Entity POJO class
representing the User table in the SurrogateDrivingDB database.
 */
@Entity

// Name of the database table represented
@Table(name = "User")

@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
        , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
        , @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
        , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
        , @NamedQuery(name = "User.findByDriverID", query = "SELECT u FROM User u WHERE u.driver_id = :driver_id")
        , @NamedQuery(name = "User.findByCustomerID", query = "SELECT u FROM User u WHERE u.customer_id = :customer_id")
        , @NamedQuery(name = "User.findAccountType", query = "SELECT u FROM User u WHERE u.account_type = :account_type")
})

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
    Primary Key id is auto generated by the system as an Integer value
    starting with 1 and incremented by 1, i.e., 1,2,3,...
    A deleted entity object's primary key number is not reused.
     */
    // id INT UNSIGNED NOT NULL AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    // username VARCHAR(32) NOT NULL
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "username")
    private String username;

    // To store Salted and Hashed Password Parts
    // password VARCHAR(256) NOT NULL
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "password")
    private String password;

    @Column(name = "customer_id")
    private Integer customer_id;

    @Column(name = "driver_id")
    private Integer driver_id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "account_type")
    @Size(min = 1, max = 32)
    private String account_type;

    /*
    ===============================================================
    Class constructors for instantiating a User entity object to
    represent a row in the User table in the SurrogateDrivingDB database.
    ===============================================================
     */

    // Used in createAccount method in UserController
    public User() {
    }

    // Not used but kept for potential future use
    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String username, String password, Integer customer_id, Integer driver_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.customer_id = customer_id;
        this.driver_id = driver_id;
    }

    /*
        ======================================================
        Getter and Setter methods for the attributes (columns)
        of the User table in the SurrogateDrivingDB database.
        ======================================================
         */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    /*
    ================================
    Instance Methods Used Internally
    ================================
     */

    // Generate and return a hash code value for the object with database primary key id
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /*
     Checks if the User object identified by 'object' is the same as the User object identified by 'id'
     Parameter object = User object identified by 'object'
     Returns True if the User 'object' and 'id' are the same; otherwise, return False
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    // Return String representation of database primary key id
    @Override
    public String toString() {
        return id.toString();
    }

}
