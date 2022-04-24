package edu.vt.controllers;

import edu.vt.EntityBeans.Customer;
import edu.vt.EntityBeans.Driver;
import edu.vt.FacadeBeans.DriverFacade;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;
import edu.vt.globals.Password;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Date;

@Named("driverController")
@SessionScoped
public class DriverController implements Serializable {
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private int driver_id;
    private int ssn;
    private String license_number;
    private String name;
    private Date Date_of_Birth;
    private String lastName;
    private String phone_number;

    private Driver selected;

    /*
    The @EJB annotation directs the EJB Container Manager to inject (store) the object reference of the
    UserFacade bean into the instance variable 'userFacade' after it is instantiated at runtime.
     */
    @EJB
    private DriverFacade driverFacade;


    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) { this.driver_id = driver_id; }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_of_Birth() {
        return Date_of_Birth;
    }

    public void setDate_of_Birth(Date Date_of_Birth) {
        this.Date_of_Birth = Date_of_Birth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Driver getSelected() {
        if (selected == null) {
            // Store the object reference of the signed-in User into the instance variable selected.
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            selected = (Driver) sessionMap.get("driver");
        }
        // Return the object reference of the selected (i.e., signed-in) User object
        return selected;
    }

    public void setSelected(Driver selected) {
        this.selected = selected;
    }

    /*
    ================
    Instance Methods
    ================

    **********************************
    Return True if a User is Signed In
    **********************************
     */
    public boolean isDriver() {
        /*
        The username of a signed-in user is put into the SessionMap in the
        initializeSessionMap() method in LoginManager upon user's sign in.
        If there is a username, that means, there is a signed-in user.
         */
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return sessionMap.get("driver_id") != null;
    }

    public void createDriver(Driver driver) {
        driver.setName(name);
        driver.setSsn(ssn);
        driver.setPhone_number(phone_number);
        driver.setDate_Of_Birth(Date_of_Birth);
        driver.setLicense_number(license_number);
        // Create the customer in the database
        driverFacade.create(driver);
    }
}
