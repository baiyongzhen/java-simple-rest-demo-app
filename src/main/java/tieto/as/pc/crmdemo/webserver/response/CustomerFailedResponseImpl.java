package tieto.as.pc.crmdemo.webserver.response;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Customer failed response.
 */
public class CustomerFailedResponseImpl implements  Response {
    private final Map<String, Object> response = new HashMap<>();

    private CustomerFailedResponseImpl(String msg) {
        response.put("ret", "failed");
        response.put("msg", msg);
    }


    /**
     * Create customer failed response.
     *
     * @param msg Error message
     * @return the response
     */
    public static Response createCustomerFailedResponse(String msg) {
        return new CustomerFailedResponseImpl(msg);
    }


    @Override
    public Map<String, Object> getRestView() {
        return response;
    }


    @Override
    public boolean isOk() {
        return false;
    }
}
