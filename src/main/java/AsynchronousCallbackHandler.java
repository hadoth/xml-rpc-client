import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;

public class AsynchronousCallbackHandler implements AsyncCallback {
    public void handleResult(Object response, URL url, String method) {
        if (response instanceof Integer) {
            Integer result = (Integer) response;
            System.out.println("The result of is " + result);
        } else {
            System.out.println("Invalid response: " + response.toString());
        }
    }

    public void handleError(Exception exception, URL url, String method) {
        System.out.println("Async EXCEPTION: " + exception.getMessage());
    }
}
