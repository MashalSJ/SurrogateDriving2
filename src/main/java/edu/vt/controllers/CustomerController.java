/*
 * Created by Osman Balci on 2021.7.15
 * Copyright © 2021 Osman Balci. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.Customer;
import edu.vt.EntityBeans.Driver;
import edu.vt.EntityBeans.User;
import edu.vt.FacadeBeans.CustomerFacade;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;
import edu.vt.globals.Password;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named("customerController")
@SessionScoped
public class CustomerController implements Serializable {
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private String name;

    private Integer ssn;
    private Date birthdate;
    private String phoneNum;

    private Customer selected;

    /*
    The @EJB annotation directs the EJB Container Manager to inject (store) the object reference of the
    CustomerFacade bean into the instance variable 'customerFacade' after it is instantiated at runtime.
     */
    @EJB
    private CustomerFacade customerFacade;


    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Customer getSelected() {
        if (selected == null) {
            // Store the object reference of the signed-in Customer into the instance variable selected.
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            selected = (Customer) sessionMap.get("customer");
        }
        // Return the object reference of the selected (i.e., signed-in) Customer object
        return selected;
    }

    public void setSelected(Customer selected) {
        this.selected = selected;
    }

    public void createCustomer(Customer customer) {
            customer.setBirthdate(birthdate);
            customer.setName(name);
            customer.setSsn(ssn);
            customer.setPhoneNum(phoneNum);
            // Create the customer in the database
            customerFacade.create(customer);
    }

    public int count(){
        return customerFacade.count();
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
    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    /*
                     -------------------------------------------------
                     Perform CREATE or EDIT operation in the database.
                     -------------------------------------------------
                     The edit(selected) method performs the SAVE (STORE) operation of the "selected"
                     object in the database regardless of whether the object is a newly
                     created object (CREATE) or an edited (updated) object (EDIT or UPDATE).

                     TransactionFacade inherits the edit(selected) method from the AbstractFacade class.
                     */
                    customerFacade.edit(selected);
                } else {
                    /*
                     -----------------------------------------
                     Perform DELETE operation in the database.
                     -----------------------------------------
                     The remove(selected) method performs the DELETE operation of the "selected"
                     object in the database.

                     TransactionFacade inherits the remove(selected) method from the AbstractFacade class.
                     */
                    customerFacade.remove(selected);
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


    public void update() {
        Methods.preserveMessages();

        persist(JsfUtil.PersistAction.UPDATE,"Customer was Successfully Updated!");

        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The UPDATE operation is successfully performed.
            selected = null;        // Remove selection
        }
    }
}