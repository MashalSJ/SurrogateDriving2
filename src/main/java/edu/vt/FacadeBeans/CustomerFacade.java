/*
 * Created by Yongjae Lim on 2022.4.23
 * Copyright © 2022 Yongjae Lim. All rights reserved.
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// @Stateless annotation implies that the conversational state with the client shall NOT be maintained.
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {
    /*
    ---------------------------------------------------------------------------------------------
    The EntityManager is an API that enables database CRUD (Create Read Update Delete) operations
    and complex database searches. An EntityManager instance is created to manage entities
    that are defined by a persistence unit. The @PersistenceContext annotation below associates
    the entityManager instance with the persistence unitName identified below.
    ---------------------------------------------------------------------------------------------
     */
    @PersistenceContext(unitName = "SurrogateDriving-PU")
    private EntityManager entityManager;

    // Obtain the object reference of the EntityManager instance in charge of
    // managing the entities in the persistence context identified above.
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /* 
    This constructor method invokes its parent AbstractFacade's constructor method,
    which in turn initializes its entity class type T and entityClass instance variable.
     */
    public CustomerFacade() {
        super(Customer.class);
    }

    /*
     *********************
     *   Other Methods   *
     *********************
     */

    // Returns the object reference of the Customer object whose primary key is id
    public Customer getCustomer(int id) {
        // The find method is inherited from the parent AbstractFacade class
        return entityManager.find(Customer.class, id);
    }

    // Returns the object reference of the Customer object whose customer name is customername
    public Customer findByCustomername(String name) {
        if (entityManager.createQuery("SELECT c FROM Customer c WHERE c.name = :name")
                .setParameter("name", name)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (Customer) (entityManager.createQuery("SELECT c FROM Customer c WHERE c.name = :name")
                    .setParameter("name", name)
                    .getSingleResult());
        }
    }
}