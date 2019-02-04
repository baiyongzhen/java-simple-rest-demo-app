package tieto.as.pc.crmdemo.domain;

import org.springframework.lang.Nullable;
import tieto.as.pc.crmdemo.util.CrmException;

import java.util.Map;

/**
 * The interface CRM db.
 */
public interface CrmDb {


    /**
     * Gets a shallow copy of customers.
     *
     * @return Customers
     */
    Map<String, Customer> getCustomers();

    /**
     * Checks if given email already exists in the customer db.
     *
     * @param givenEmail the given email
     * @return true if email already exists, false otherwise
     */
    boolean emailAlreadyExists(String givenEmail);

    /**
     * Adds new customer to the database.
     *
     * @param newEmail  Email
     * @param firstName First name
     * @param lastName  Last name
     * @return new Customer if ok, null if not ok (+ possible SSException)
     * @throws CrmException if failure
     */
    @Nullable
    Customer addCustomer(String newEmail, String firstName, String lastName);

    /**
     * Gets customer by id.
     *
     * @param id Customer id
     * @return Customer if found, null if not found (+ possible SSException)
     * @throws CrmException if failure
     */
    @Nullable
    Customer getCustomerById(long id);

    /**
     * Gets customer by email
     *
     * @param email Customer email
     * @return Customer if found, null if not found (+ possible SSException)
     * @throws CrmException if failure
     */
    @Nullable
    Customer getCustomerByEmail(String email);

}
