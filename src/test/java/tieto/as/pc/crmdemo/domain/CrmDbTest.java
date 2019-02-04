package tieto.as.pc.crmdemo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tieto.as.pc.crmdemo.util.CrmConsts;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("CrmDb test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CrmDbImpl.class})
class CrmDbTest {
    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CrmDb crmDb;

    @DisplayName("Tests getting the customer by id")
    @Test
    void getCustomerByIdTest() {
        logger.debug(CrmConsts.LOG_ENTER);
        Customer customer = crmDb.getCustomerById(1L);
        assertEquals("Kari", customer.firstName);
        logger.debug(CrmConsts.LOG_EXIT);
    }

}
