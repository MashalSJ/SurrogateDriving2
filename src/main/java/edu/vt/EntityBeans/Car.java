
package edu.vt.EntityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
The @Entity annotation designates this class as a JPA Entity POJO class
representing the Car table in the SurrogateDrivingDB database.
 */
@Entity

// Name of the database table represented
@Table(name = "Car")
/*
CREATE TABLE Car
(
plate_number VARCHAR(16) NOT NULL,
color VARCHAR(32) NOT NULL,
model VARCHAR(32) NOT NULL,
brand VARCHAR(32) NOT NULL,
year INT NOT NULL,
car_type VARCHAR(32) NOT NULL,
transmission VARCHAR(32) NOT NULL,
customer_id INT NOT NULL,
FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);
 */
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "SELECT u FROM Car u")
        , @NamedQuery(name = "Driver.findByCarId", query = "SELECT u FROM Car u WHERE u.car_id = :car_id")
        , @NamedQuery(name = "Car.findByColor", query = "SELECT u FROM Car u WHERE u.color = :color")
        , @NamedQuery(name = "Car.findByModel", query = "SELECT u FROM Car u WHERE u.model = :model")
        , @NamedQuery(name = "Car.findByBrand", query = "SELECT u FROM Car u WHERE u.brand = :brand")
        , @NamedQuery(name = "Car.findByYear", query = "SELECT u FROM Car u WHERE u.year = :year")
        , @NamedQuery(name = "Car.findByCarType", query = "SELECT u FROM Car u WHERE u.car_type = :car_type")
        , @NamedQuery(name = "Car.findTransmission", query = "SELECT u FROM Car u WHERE u.transmission = :transmission")
        , @NamedQuery(name = "Car.findCustomerID", query = "SELECT u FROM Car u WHERE u.customer_id = :customer_id")

})

public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "car_id")
    private Integer car_id;

    @Basic(optional = false)
    @Size(min = 1, max = 16)
    @Column(name = "plate_number")
    private String plate_number;

    @Basic(optional = false)
    @Size(min = 1, max = 32)
    @NotNull
    @Column(name = "color")
    private String color;

    @Basic(optional = false)
    @Size(min = 1, max = 32)
    @NotNull
    @Column(name = "model")
    private String model;

    @Basic(optional = false)
    @Size(min = 1, max = 32)
    @NotNull
    @Column(name = "brand")
    private String brand;

    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private Integer year;

    @Basic(optional = false)
    @Size(min = 1, max = 32)
    @NotNull
    @Column(name = "car_type")
    private String car_type;

    @Basic(optional = false)
    @Size(min = 1, max = 32)
    @NotNull
    @Column(name = "transmission")
    private String transmission;

    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_id")
    private Integer customer_id;

    /*
    ===============================================================
    Class constructors for instantiating a Car entity object to
    represent a row in the Car table in the SurrogateDrivingDB database.
    ===============================================================
     */

    // Used in createAccount method in CarController
    public Car() {
    }

    public Car(Integer car_id) {
        this.car_id = car_id;
    }

    public Car(String plate_number) {
        this.plate_number = plate_number;
    }

    public Car(String plate_number, String color, String model, String brand, Integer year, String car_type, String transmission, Integer customer_id) {
        this.plate_number = plate_number;
        this.color = color;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.car_type = car_type;
        this.transmission = transmission;
        this.customer_id = customer_id;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
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
        hash += (car_id != null ? car_id.hashCode() : 0);
        return hash;
    }

    /*
     Checks if the Car object identified by 'object' is the same as the Car object identified by 'id'
     Parameter object = Car object identified by 'object'
     Returns True if the Car 'object' and 'id' are the same; otherwise, return False
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        return (this.car_id != null || other.car_id == null) && (this.car_id == null || this.car_id.equals(other.car_id));
    }

    // Return String representation of database primary key id
    @Override
    public String toString() {
        return car_id.toString();
    }

}
