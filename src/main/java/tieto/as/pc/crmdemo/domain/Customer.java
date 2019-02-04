package tieto.as.pc.crmdemo.domain;


public class Customer {

    // We provide fields as public since there is no reason why they should
    // be private in a simple DTO class like this.
    public final long id;
    public final String email;
    public final String firstName;
    public final String lastName;

    /**
     * Instantiates a new Customer.
     *
     * @param id             the customer id
     * @param email          the email
     * @param firstName      the first name
     * @param lastName       the last name
     */
    public Customer(long id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

