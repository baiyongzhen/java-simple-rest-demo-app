package tieto.as.pc.crmdemo.webserver.response;

import java.util.Map;

/**
 * The interface CRM response.
 */
public interface Response {

    /**
     * Gets the responce map that Spring converts to JSON.
     *
     * @return the response
     */
    Map<String, Object> getRestView();

    /**
     * Tells if the response is ok.
     *
     * @return true if ok, false otherwise
     */
    boolean isOk();
}
