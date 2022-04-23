/*
 * Created by Osman Balci on 2021.7.15
 * Copyright © 2021 Osman Balci. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.Customer;
import edu.vt.FacadeBeans.CustomerFacade;
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
    public String getCustomername() {
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

}