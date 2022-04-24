/*
 * Created by Yongjae Lim on 2022.4.23
 * Copyright © 2022 Yongjae Lim. All rights reserved.
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Transaction;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// @Stateless annotation implies that the conversational state with the client shall NOT be maintained.
@Stateless
public class TransactionFacade extends AbstractFacade<Transaction> {
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
    public TransactionFacade() {
        super(Transaction.class);
    }
    

    /*
     *********************
     *   Other Methods   *
     *********************
     */


    public List<Transaction> driverIdQuery(Integer driver_id) {
        return getEntityManager().createQuery(
                        "SELECT c FROM Transaction c WHERE c.driver_id = :driver_id")
                .setParameter("driver_id", driver_id)
                .getResultList();
    }

    public List<Transaction> customerIdQuery(Integer customer_id) {
        return getEntityManager().createQuery(
                        "SELECT c FROM Transaction c WHERE c.customer_id = :customer_id")
                .setParameter("customer_id", customer_id)
                .getResultList();
    }

    public List<Transaction> jobsQuery() {
        return getEntityManager().createQuery(
                        "SELECT u FROM Transaction u WHERE u.end_time IS NULL")
                .getResultList();
    }

    public double averagePrice() {
        Object result = (getEntityManager().createQuery(
                        "SELECT AVG(price) as sumPrices FROM Transaction")
                .getResultList()).get(0);
        System.out.println(result);
        return (Double) result;
    }
    public List<Transaction> findByEndTimeNull() {
        // Place the % wildcard before and after the search string to search for it anywhere in the Country name
        //searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery(
                        "SELECT c FROM Transaction c WHERE c.end_time IS NULL")
                .getResultList();
    }

    // Returns the object reference of the Transaction object whose primary key is id
    public Transaction getTransaction(int id) {
        // The find method is inherited from the parent AbstractFacade class
        return entityManager.find(Transaction.class, id);
    }

    // Returns the object reference of the Transaction object whose transaction name is transactionname
    public Transaction findByStartLocation(String startLocation) {
        if (entityManager.createQuery("SELECT c FROM Transaction c WHERE c.start_location = :startLocation")
                .setParameter("startLocation", startLocation)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (Transaction) (entityManager.createQuery("SELECT c FROM Transaction c WHERE c.start_location = :startLocation")
                    .setParameter("startLocation", startLocation)
                    .getSingleResult());
        }
    }
}