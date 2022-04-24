package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Car;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// @Stateless annotation implies that the conversational state with the client shall NOT be maintained.
@Stateless
public class CarFacade extends AbstractFacade<Car> {
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
    public CarFacade() {
        super(Car.class);
    }

    /*
     *********************
     *   Other Methods   *
     *********************
     */

    // Returns the object reference of the Car object whose primary key is id
    public Car getCar(int id) {
        // The find method is inherited from the parent AbstractFacade class
        return entityManager.find(Car.class, id);
    }

    /*
    public Car findByCustomerID(String customer_id) {
        if (entityManager.createQuery("SELECT c FROM Car c WHERE c.customer_id = :customer_id)
                .setParameter("customer_id", customer_id)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (Car) (entityManager.createQuery("SELECT c FROM Car c WHERE c.customer_id = :customer_id")
                    .setParameter("customer_id", customer_id)
                    .getSingleResult());
        }
    }*/
    // Returns the object reference of the Car object whose car plate number is plate_number
    public Car findByPlateNumber(String plate_number) {
        if (entityManager.createQuery("SELECT c FROM Car c WHERE c.plate_number = :plate_number")
                .setParameter("plate_number", plate_number)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (Car) (entityManager.createQuery("SELECT c FROM Car c WHERE c.plate_number = :plate_number")
                    .setParameter("plate_number", plate_number)
                    .getSingleResult());
        }
    }
}