package tieto.as.pc.crmdemo.webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tieto.as.pc.crmdemo.domain.CrmDb;
import tieto.as.pc.crmdemo.domain.Customer;
import tieto.as.pc.crmdemo.util.CrmConsts;
import tieto.as.pc.crmdemo.webserver.response.CustomerFailedResponseImpl;
import tieto.as.pc.crmdemo.webserver.response.CustomerOkResponseImpl;
import tieto.as.pc.crmdemo.webserver.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


@CrossOrigin
@RestController
public class Server {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CrmDb crmDb;
    public static final String HTTPHEADER_STR = "HttpHeaders: {}";

    private final AtomicLong counter = new AtomicLong();


    /**
     * Instantiates a new Server.
     * Autowires the CRM db.
     *
     * @param crmDb the CRM db
     */
    @Autowired
    public Server(CrmDb crmDb) {
        this.crmDb = crmDb;
        logger.debug("******************************************************");
        logger.info("Starting application...");
    }

    public static class Info implements Response {
        private static final String INFO_MSG = "Try /customer/<number>";
        private final Map<String, Object> response = new HashMap<>();

        private Info() {
            response.put("ret", "ok");
            // Has to do like this or creates a Json conversion with extra layer.
            response.put("msg", INFO_MSG);
        }

        public static Response createInfoOkResponse() {
            return new Info();
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

    // Used by AWS ECS health check.
    public static class Health implements Response {
        private static final String HEALTH_MSG = "Health ok";
        private final Map<String, Object> response = new HashMap<>();

        private Health() {
            response.put("ret", "ok");
            // Has to do like this or creates a Json conversion with extra layer.
            response.put("msg", HEALTH_MSG);
        }

        public static Response createHealthOkResponse() {
            return new Health();
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

    /**
     * Gets info.
     *
     * @return the info.
     */
    @GetMapping(path = "/info")
    public ResponseEntity<Map> getInfo() {
        logger.debug(CrmConsts.LOG_ENTER);
        Response response = Info.createInfoOkResponse();
        logger.debug(CrmConsts.LOG_EXIT);
        HttpStatus httpStatus = response.isOk() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        ResponseEntity<Map> responseEntity = new ResponseEntity<>(response.getRestView(), httpStatus);
        return responseEntity;
    }

    /**
     * Gets health.
     *
     * @return the health.
     */
    @GetMapping(path = "/health")
    public ResponseEntity<Map> getHealth() {
        logger.debug(CrmConsts.LOG_ENTER);
        Response response = Health.createHealthOkResponse();
        logger.debug(CrmConsts.LOG_EXIT);
        HttpStatus httpStatus = response.isOk() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        ResponseEntity<Map> responseEntity = new ResponseEntity<>(response.getRestView(), httpStatus);
        return responseEntity;
    }

    /**
     * Gets customer.
     *
     * @return the customer (or error message)
     */
    @GetMapping(path = "/customer/{id}")
    public ResponseEntity<Map> getCustomer(@PathVariable("id") long id, @RequestHeader HttpHeaders headers) {
        logger.debug(CrmConsts.LOG_ENTER);
        Response response;
        logger.trace(HTTPHEADER_STR, headers);
        Customer customer = crmDb.getCustomerById(id);
        if (customer != null) {
            response = CustomerOkResponseImpl.createCustomerOkResponse(customer);
        } else {
            response = CustomerFailedResponseImpl.createCustomerFailedResponse(String.format("Could not find customer with id %d", id));
        }
        HttpStatus httpStatus = response.isOk() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        ResponseEntity<Map> responseEntity = new ResponseEntity<>(response.getRestView(), httpStatus);
        logger.debug(CrmConsts.LOG_EXIT);
        return responseEntity;
    }

}
