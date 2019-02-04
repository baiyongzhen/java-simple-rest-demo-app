package tieto.as.pc.crmdemo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tieto.as.pc.crmdemo.util.CrmConsts;
import tieto.as.pc.crmdemo.util.CrmErrorCode;
import tieto.as.pc.crmdemo.util.CrmException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Domain class.
 */
@Service
public class CrmDbImpl implements CrmDb {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * The Synchronized CRM db. Synchronized since Spring Services
     * are singletons by default (and hence not multi-thread safe)
     * and class member variables need to be synchoronized
     * to be thread safe.
     */
    private final Map<String, Customer> syncCrmDb =
            Collections.synchronizedMap(new HashMap<>());
    /**
     * The Counter. Access synchronized on method level.
     */
    private long counter = 3;


    /**
     * Instantiates a new Customers. Simulates CRM database.
     */
    public CrmDbImpl() {
        Customer Customer1 = new Customer(1, "kari.karttinen@foo.com",
                "Kari", "Karttinen");
        Customer Customer2 = new Customer(2, "timo.tillinen@foo.com",
                "Timo", "Tillinen");
        Customer Customer3 = new Customer(3, "erkka.erkkila@foo.com",
                "Erkka", "Erkkila");
        syncCrmDb.put(Long.toString(Customer1.id), Customer1);
        syncCrmDb.put(Long.toString(Customer2.id), Customer2);
        syncCrmDb.put(Long.toString(Customer3.id), Customer3);
    }

    /**
     * Get next counter.
     *
     * @return the long
     */
    private synchronized long counter() {
        counter++;
        return counter;
    }


    @Override
    public Map<String, Customer> getCustomers() {
        return new HashMap<>(syncCrmDb);
    }

    @Override
    public boolean emailAlreadyExists(String givenEmail) {
        logger.debug(CrmConsts.LOG_ENTER);
        Collection<Customer> Customers = syncCrmDb.values();
        List<Customer> filteredCustomers = Customers.stream().filter(thisCustomer ->
                (thisCustomer.email.equals(givenEmail))).collect(Collectors.toList());
        boolean ret = (!filteredCustomers.isEmpty());
        logger.debug("{}, ret: {}", CrmConsts.LOG_EXIT, ret);
        return ret;
    }

    @Override
    public Customer addCustomer(String newEmail, String firstName, String lastName) {
        logger.debug(CrmConsts.LOG_ENTER);
        Customer Customer;
        if (!emailAlreadyExists(newEmail)) {
            long id = counter();
            Customer = new Customer(id, newEmail, firstName, lastName);
            syncCrmDb.put(Long.toString(id), Customer);
        } else {
            throw new CrmException("Email already exists: " + newEmail, CrmErrorCode.EMAIL_ALREADY_EXISTS);
        }
        logger.debug(CrmConsts.LOG_EXIT);
        return Customer;
    }

    @Override
    public Customer getCustomerById(long id) {
        logger.debug(CrmConsts.LOG_ENTER);
        Collection<Customer> Customers = syncCrmDb.values();
        List<Customer> filteredCustomers = Customers.stream().filter(thisCustomer ->
                (thisCustomer.id == id)).collect(Collectors.toList());

        Customer ret = (!filteredCustomers.isEmpty()) ? filteredCustomers.get(0) : null;
        logger.debug(CrmConsts.LOG_EXIT);
        return ret;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        logger.debug(CrmConsts.LOG_ENTER);
        Collection<Customer> Customers = syncCrmDb.values();
        List<Customer> filteredCustomers = Customers.stream().filter(thisCustomer ->
                (thisCustomer.email.equals(email))).collect(Collectors.toList());

        Customer ret = (!filteredCustomers.isEmpty()) ? filteredCustomers.get(0) : null;
        logger.debug(CrmConsts.LOG_EXIT);
        return ret;
    }
}
