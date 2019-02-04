package tieto.as.pc.crmdemo.webserver.response;

import tieto.as.pc.crmdemo.domain.Customer;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Customer ok response.
 */
public class CustomerOkResponseImpl implements Response{
    private final Map<String, Object> response = new HashMap<>();


    private CustomerOkResponseImpl(Customer customer) {
        response.put("ret", "ok");
        // Has to do like this or creates a Json conversion with extra layer.
        response.put("customer", customer);
    }


    /**
     * Create customer ok response.
     *
     * @param customer the customer
     * @return the response
     */
    public static Response createCustomerOkResponse(Customer customer) {
        return new CustomerOkResponseImpl(customer);
    }


    @Override
    public Map<String, Object> getRestView() {
        return response;
    }


    @Override
    public boolean isOk() {
        return true;
    }
}
