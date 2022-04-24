/*
 * Created by Yongjae Lim on 2022.4.23
 * Copyright © 2022 Yongjae Lim. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.Car;
import edu.vt.EntityBeans.Driver;
import edu.vt.FacadeBeans.CarFacade;
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

@Named("carController")
@SessionScoped
public class CarController implements Serializable {
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private String plateNumber;

    private String color;
    private String model;
    private String brand;
    private Integer year;
    private String carType;
    private String transmission;
    private Integer customer_id;

    private Car selected;

    /*
    The @EJB annotation directs the EJB Container Manager to inject (store) the object reference of the
    CarFacade bean into the instance variable 'CarFacade' after it is instantiated at runtime.
     */
    @EJB
    private CarFacade carFacade;


    /*
    =========================
    Getter and Setter Methods
    =========================
     */

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
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

    public CarFacade getCarFacade() {
        return carFacade;
    }

    public void setCarFacade(CarFacade carFacade) {
        carFacade = carFacade;
    }

    public Car getSelected() {
        if (selected == null) {
            // Store the object reference of the signed-in Car into the instance variable selected.
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            selected = (Car) sessionMap.get("Car");
        }
        // Return the object reference of the selected (i.e., signed-in) Car object
        return selected;
    }

    public void createCar(Car car, Integer customer_id) {
        car.setBrand(brand);
        car.setCar_type(carType);
        car.setColor(color);
        car.setModel(model);
        car.setTransmission(transmission);
        car.setPlate_number(plateNumber);
        car.setYear(year);
        car.setCustomer_id(customer_id);
        // Create the customer in the database
        carFacade.create(car);
    }

    public void setSelected(Car selected) {
        this.selected = selected;
    }

}