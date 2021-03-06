/*
 * Created by Team13 on 2021.7.15
 * Copyright © 2021 Team13. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.Customer;
import edu.vt.EntityBeans.Transaction;
import edu.vt.EntityBeans.User;
import edu.vt.FacadeBeans.TransactionFacade;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;
import edu.vt.globals.Password;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Calendar start_time;
    private Calendar end_time;
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

    //private List<Transaction> listOfRequests = null;
    private List<Transaction> allTransactions = null;
    private List<Transaction> driverTransactions = null;
    private List<Transaction> requests = null;
    private List<Transaction> customerTransactions = null;
    private List<Transaction> customerNeedsRating = null;
    private double prices;

    
    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    
    public List<Transaction> getAllTransactions() {
        if (allTransactions == null) {
            allTransactions = transactionFacade.findAll();
        }
        return allTransactions;
    }

    public List<Transaction> getDriverTransactions(Integer driverID) {
        if (driverTransactions == null) {
            driverTransactions = transactionFacade.driverIdQuery(driverID);
        }
        return driverTransactions;
    }

    public List<Transaction> getRequests() {
        if (requests == null) {
            requests = transactionFacade.jobsQuery();
        }
        return requests;
    }

    public double calculatePrice() {
        List<Object> avgs = null;
        if (price == null) {
           avgs = transactionFacade.averagePrice();
        }
        return (Double) avgs.get(0);
    }

    public List<Transaction> getCustomerTransactions(Integer customerID) {
        if (customerTransactions == null) {
            customerTransactions = transactionFacade.customerHistory(customerID);
        }
        return customerTransactions;
    }

    public List<Transaction> getCustomerNeedsRating(Integer customerID) {
        if (customerNeedsRating == null) {
            customerNeedsRating = transactionFacade.needsRating(customerID);
        }
        return customerNeedsRating;
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

    public String getEnd_loaction() {
        return end_loaction;
    }

    public void setEnd_loaction(String end_loaction) {
        this.end_loaction = end_loaction;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice() {
        int min = 5;
        int max = 20;

        //Generate random int value from 50 to 100 
        //System.out.println("Random value in int from "+min+" to "+max+ ":");
        double random_int = (double)Math.floor(Math.random()*(max-min+1)+min);
        this.price = random_int;
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
    public void createTransaction(UserController userController) {
        Transaction transaction = new Transaction();
        transaction.setStart_location(start_location);
        transaction.setEnd_location(end_loaction);
        transaction.setStart_time(Calendar.getInstance());
        transaction.setCustomer_id(userController.getSelected().getCustomer_id());
        transaction.setPrice();
        // Create the customer in the database
        transactionFacade.create(transaction);
    }

    public void setSelected(Transaction selected) {
        this.selected = selected;
    }

    /*
     **************************************
     *   Unselect Selected Transaction Object   *
     **************************************
     */
    public void unselect() {
        selected = null;
    }

    /*
     *************************************
     *   Cancel and Display List.xhtml   *
     *************************************
     */
    public String cancel() {
        // Unselect previously selected transaction object if any
        selected = null;
        return "/lists/JobList?faces-redirect=true";
    }

    /*
    *************************************
    UPDATE Selected Transaction in the Database
    *************************************
     */
    public void update() {
        Methods.preserveMessages();

        persist(PersistAction.UPDATE,"Transaction was Successfully Updated!");

        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The UPDATE operation is successfully performed.
            selected = null;        // Remove selection
            customerTransactions = null;    // Invalidate listOfTransaction to trigger re-query.
            requests = null;
            driverTransactions = null;
            allTransactions = null;
            customerNeedsRating = null;
        }
    }

    public void driverRate(Integer driverId){
        selected.setDriver_id(driverId);
        selected.setEnd_time(Calendar.getInstance());
        update();
    }

    /*
     **********************************************************************************************
     *   Perform CREATE, UPDATE (EDIT), and DELETE (DESTROY, REMOVE) Operations in the Database   *
     **********************************************************************************************
     */
    /**
     * @param persistAction refers to CREATE, UPDATE (Edit) or DELETE action
     * @param successMessage displayed to inform the user about the result
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    /*
                     -------------------------------------------------
                     Perform CREATE or EDIT operation in the database.
                     -------------------------------------------------
                     The edit(selected) method performs the SAVE (STORE) operation of the "selected"
                     object in the database regardless of whether the object is a newly
                     created object (CREATE) or an edited (updated) object (EDIT or UPDATE).

                     TransactionFacade inherits the edit(selected) method from the AbstractFacade class.
                     */
                    transactionFacade.edit(selected);
                } else {
                    /*
                     -----------------------------------------
                     Perform DELETE operation in the database.
                     -----------------------------------------
                     The remove(selected) method performs the DELETE operation of the "selected"
                     object in the database.

                     TransactionFacade inherits the remove(selected) method from the AbstractFacade class.
                     */
                    transactionFacade.remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex,"A persistence error occurred.");
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex,"A persistence error occurred.");
            }
        }
    }

    public int count(){
        return transactionFacade.count();
    }
}