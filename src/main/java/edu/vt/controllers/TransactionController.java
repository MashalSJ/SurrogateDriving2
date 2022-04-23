/*
 * Created by Osman Balci on 2021.7.15
 * Copyright © 2021 Osman Balci. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.Transaction;
import edu.vt.FacadeBeans.TransactionFacade;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;
import edu.vt.globals.Password;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named("transactionController")
@SessionScoped
public class TransactionController implements Serializable {
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private Timestamp start_time;
    private Timestamp end_time;
    private String start_location;
    private String end_loaction;
    private Double price;
    private Integer customer_rating;
    private Integer driver_rating;
    private Integer customer_id;
    private Integer driver_id;

    private Transaction selected;

    /*
    The @EJB annotation directs the EJB Container Manager to inject (store) the object reference of the
    TransactionFacade bean into the instance variable 'transactionFacade' after it is instantiated at runtime.
     */
    @EJB
    private TransactionFacade transactionFacade;


    /*
    =========================
    Getter and Setter Methods
    =========================
     */

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public String getStart_location() {
        return start_location;
    }

    public void setStart_location(String start_location) {
        this.start_location = start_location;
    }

    public String getEnd_loaction() {
        return end_loaction;
    }

    public void setEnd_loaction(String end_loaction) {
        this.end_loaction = end_loaction;
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

    public Transaction getSelected() {
        if (selected == null) {
            // Store the object reference of the signed-in Transaction into the instance variable selected.
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            selected = (Transaction) sessionMap.get("transaction");
        }
        // Return the object reference of the selected (i.e., signed-in) Transaction object
        return selected;
    }

    public void setSelected(Transaction selected) {
        this.selected = selected;
    }

}