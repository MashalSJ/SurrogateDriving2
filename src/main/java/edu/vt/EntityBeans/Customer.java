/*
 * Created by Yongjae Lim on 2022.4.23
 * Copyright © 2022 Yongjae Lim. All rights reserved.
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
The @Entity annotation designates this class as a JPA Entity POJO class
representing the Customer table in the SurrogateDrivingDB database.
 */
@Entity

// Name of the database table represented
@Table(name = "Customer")

@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "SELECT u FROM Customer u")
        , @NamedQuery(name = "Customer.findById", query = "SELECT u FROM Customer u WHERE u.customer_id = :costomer_id")
        , @NamedQuery(name = "Customer.findBySSN", query = "SELECT u FROM Customer u WHERE u.ssn = :SSN")
        , @NamedQuery(name = "Customer.findByName", query = "SELECT u FROM Customer u WHERE u.name = :name")
        , @NamedQuery(name = "Customer.findByDateOfBirth", query = "SELECT u FROM Customer u WHERE u.birthdate = :birthdate")
        , @NamedQuery(name = "Customer.findByPhoneNumber", query = "SELECT u FROM Customer u WHERE u.phoneNum = :phoneNum")})

public class Customer implements Serializable {
    /*
    ========================================================
    Instance variables representing the attributes (columns)
    of the Customer table in the SurrogateDrivingDB database.
    CREATE TABLE Customer
    (
        customer_id INT PRIMARY KEY AUTO_INCREMENT,
        ssn INT NOT NULL,
        name VARCHAR(100) NOT NULL,
        Date_Of_Birth DATE NOT NULL,
        phone_number VARCHAR(32) NOT NULL
    );
    ========================================================
     */
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
    @Column(name = "customer_id")
    private Integer customer_id;

    // SSN INT NOT NULL,
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 32)
    @Column(name = "ssn")
    private Integer ssn;

    // name VARCHAR(100) NOT NULL
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "name")
    private String name;

    // Date_Of_Birth DATE NOT NULL
    // Date_Of_Birth is recorded in the database as YYYY-MM-DD so that it is sortable
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Of_Birth")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    // phone_number VARCHAR(32) NOT NULL
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "phone_number")
    private String phoneNum;

    /*
    ===============================================================
    Class constructors for instantiating a Customer entity object to
    represent a row in the Customer table in the SurrogateDrivingDB database.
    ===============================================================
     */

    // Used in createAccount method in CustomerController
    public Customer() {
    }

    // Not used but kept for potential future use
    public Customer(Integer id) {
        this.customer_id = id;
    }

    // Not used but kept for potential future use
    public Customer(Integer id, String name, String password, Date birthdate,
                    String phoneNum, Integer ssn) {
        this.customer_id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.phoneNum = phoneNum;
        this.ssn = ssn;
    }

    /*
    ======================================================
    Getter and Setter methods for the attributes (columns)
    of the Customer table in the SurrogateDrivingDB database.
    ======================================================
     */
    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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
        hash += (customer_id != null ? customer_id.hashCode() : 0);
        return hash;
    }

    /*
     Checks if the Customer object identified by 'object' is the same as the Customer object identified by 'id'
     Parameter object = Customer object identified by 'object'
     Returns True if the Customer 'object' and 'id' are the same; otherwise, return False
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        return (this.customer_id != null || other.customer_id == null) && (this.customer_id == null || this.customer_id.equals(other.customer_id));
    }

    // Return String representation of database primary key id
    @Override
    public String toString() {
        return customer_id.toString();
    }

}