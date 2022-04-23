
package edu.vt.EntityBeans;

import java.io.Serializable;
import java.sql.Calendar;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
The @Entity annotation designates this class as a JPA Entity POJO class
representing the Transaction table in the SurrogateDrivingDB database.
 */
@Entity

// Name of the database table represented
@Table(name = "Transaction")
/*
CREATE TABLE Transaction
(
transaction_id INT PRIMARY KEY AUTO_INCREMENT,
start_time DATETIME NOT NULL,
end_time DATETIME,
start_location VARCHAR(64) NOT NULL,
end_location VARCHAR(64) NOT NULL,
price DECIMAL(5,2) NOT NULL,
customer_rating INT,
CONSTRAINT customer_rating_constraint CHECK (customer_rating BETWEEN 1 AND 5),
driver_rating INT,
CONSTRAINT driver_rating_constraint CHECK (driver_rating BETWEEN 1 AND 5),
customer_id INT NOT NULL,
FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
driver_id INT NOT NULL,
FOREIGN KEY (driver_id) REFERENCES Driver(driver_id)
);
 */
@NamedQueries({
        @NamedQuery(name = "Transaction.findAll", query = "SELECT u FROM Transaction u")
        , @NamedQuery(name = "Transaction.findByTransactionId", query = "SELECT u FROM Transaction u WHERE u.transaction_id = :transaction_id")
        , @NamedQuery(name = "Transaction.findByStartTime", query = "SELECT u FROM Transaction u WHERE u.start_time = :start_time")
        , @NamedQuery(name = "Transaction.findByEndTime", query = "SELECT u FROM Transaction u WHERE u.end_time = :end_time")
        , @NamedQuery(name = "Transaction.findByCustomerRating", query = "SELECT u FROM Transaction u WHERE u.customer_rating = :customer_rating")
        , @NamedQuery(name = "Transaction.findByDriverRating", query = "SELECT u FROM Transaction u WHERE u.driver_rating = :driver_rating")
        , @NamedQuery(name = "Transaction.findByCustomerID", query = "SELECT u FROM Transaction u WHERE u.customer_id = :customer_id")
        , @NamedQuery(name = "Transaction.findByDriverID", query = "SELECT u FROM Transaction u WHERE u.driver_id = :driver_id")
})

public class Transaction implements Serializable {
    /*
    CREATE TABLE Transaction
    (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    start_time DATETIME NOT NULL,
    end_time DATETIME,
    start_location VARCHAR(64) NOT NULL,
    end_location VARCHAR(64) NOT NULL,
    price DECIMAL(5,2) NOT NULL,
    customer_rating INT,
    CONSTRAINT customer_rating_constraint CHECK (customer_rating BETWEEN 1 AND 5),
    driver_rating INT,
    CONSTRAINT driver_rating_constraint CHECK (driver_rating BETWEEN 1 AND 5),
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    driver_id INT NOT NULL,
    FOREIGN KEY (driver_id) REFERENCES Driver(driver_id)
    );
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transaction_id")
    private Integer transaction_id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "start_time")
    @Temporal(TemporalType.Calendar)
    private Calendar start_time;

    @Basic(optional = false)
    @Column(name = "end_time")
    @Temporal(TemporalType.Calendar)
    private Calendar end_time;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "start_location")
    private String start_location;

    @Basic(optional = false)
    @Size(min = 1, max = 64)
    @Column(name = "end_location")
    private String end_location;

    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private Double price;

    @Basic(optional = false)
    @Column(name = "customer_rating")
    private Integer customer_rating;

    @Basic(optional = false)
    @Column(name = "driver_rating")
    private Integer driver_rating;

    @Basic(optional = false)
    @Column(name = "customer_id")
    private Integer customer_id;

    @Basic(optional = false)
    @Column(name = "driver_id")
    private Integer driver_id;

    /*
    ===============================================================
    Class constructors for instantiating a Transaction entity object to
    represent a row in the Transaction table in the SurrogateDrivingDB database.
    ===============================================================
     */

    // Used in createAccount method in TransactionController
    public Transaction() {
    }

    public Transaction(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Transaction(Integer transaction_id, Calendar start_time, Calendar end_time, String start_location, String end_location, Double price, Integer customer_rating, Integer driver_rating, Integer customer_id, Integer driver_id) {
        this.transaction_id = transaction_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.start_location = start_location;
        this.end_location = end_location;
        this.price = price;
        this.customer_rating = customer_rating;
        this.driver_rating = driver_rating;
        this.customer_id = customer_id;
        this.driver_id = driver_id;
    }

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Calendar getStart_time() {
        return start_time;
    }

    public void setStart_time(Calendar start_time) {
        this.start_time = start_time;
    }

    public Calendar getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Calendar end_time) {
        this.end_time = end_time;
    }

    public String getStart_location() {
        return start_location;
    }

    public void setStart_location(String start_location) {
        this.start_location = start_location;
    }

    public String getEnd_location() {
        return end_location;
    }

    public void setEnd_location(String end_location) {
        this.end_location = end_location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCustomer_rating() {
        return customer_rating;
    }

    public void setCustomer_rating(Integer customer_rating) {
        this.customer_rating = customer_rating;
    }

    public Integer getDriver_rating() {
        return driver_rating;
    }

    public void setDriver_rating(Integer driver_rating) {
        this.driver_rating = driver_rating;
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
        hash += (transaction_id != null ? transaction_id.hashCode() : 0);
        return hash;
    }

    /*
     Checks if the Transaction object identified by 'object' is the same as the Transaction object identified by 'id'
     Parameter object = Transaction object identified by 'object'
     Returns True if the Transaction 'object' and 'id' are the same; otherwise, return False
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        return (this.transaction_id != null || other.transaction_id == null) && (this.transaction_id == null || this.transaction_id.equals(other.transaction_id));
    }

    // Return String representation of database primary key id
    @Override
    public String toString() {
        return transaction_id.toString();
    }

}
