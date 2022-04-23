package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Driver;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// @Stateless annotation implies that the conversational state with the client shall NOT be maintained.
@Stateless
public class DriverFacade extends AbstractFacade<Driverr> {
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
    public DriverFacade() {
        super(Driver.class);
    }

    /*
     *********************
     *   Other Methods   *
     *********************
     */

    // Returns the object reference of the Driver object whose primary key is id
    public Driver getDriver(int id) {
        // The find method is inherited from the parent AbstractFacade class
        return entityManager.find(Driver.class, id);
    }

    // Returns the object reference of the Driver object whose driver name is drivername
    public Driver findByDrivername(String name) {
        if (entityManager.createQuery("SELECT c FROM Driver c WHERE c.name = :name")
                .setParameter("name", name)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (Driver) (entityManager.createQuery("SELECT c FROM Driver c WHERE c.name = :name")
                    .setParameter("name", name)
                    .getSingleResult());
        }
    }
    // Returns the object reference of the Driver object whose driver license number is license_number
    public Driver findByLicenseNumber(String license_number) {
        if (entityManager.createQuery("SELECT c FROM Driver c WHERE c.license_number = :license_number")
                .setParameter("license_number", license_number)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (Driver) (entityManager.createQuery("SELECT c FROM Driver c WHERE c.license_number = :license_number")
                    .setParameter("license_number", license_number)
                    .getSingleResult());
        }
    }
}
