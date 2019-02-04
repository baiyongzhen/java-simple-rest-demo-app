package tieto.as.pc.crmdemo.webserver;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tieto.as.pc.crmdemo.domain.CrmDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tieto.as.pc.crmdemo.domain.Customer;
import tieto.as.pc.crmdemo.webserver.response.CustomerFailedResponseImpl;
import tieto.as.pc.crmdemo.webserver.response.CustomerOkResponseImpl;
import tieto.as.pc.crmdemo.util.CrmConsts;
import tieto.as.pc.crmdemo.webserver.response.Response;


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

    static class Info {
        private static final String INFO_MSG = "Try /customer/<number>";

        /**
         * Gets info as json. Used in testing.
         *
         * @return the info
         */
        public String getInfo() {
            return INFO_MSG;
        }
    }


    /**
     * Gets info.
     *
     * @return the info.
     */
    @GetMapping(path = "/info")
    public Info getInfo() {
        logger.debug(CrmConsts.LOG_ENTER);
        Info info = new Info();
        logger.debug(CrmConsts.LOG_EXIT);
        return info;
    }

    /**
     * Gets product groups.
     *
     * @return the product groups
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
