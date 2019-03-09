import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
import java.util.Vector;

public class RPCClient {

    private XmlRpcClient client;

    public RPCClient(XmlRpcClient client) {
        this.client = client;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Starting XML-RPC client...");
            int port = Integer.parseInt(args[0]);
            int leftTerm = Integer.parseInt(args[1]);
            int rightTerm = Integer.parseInt(args[2]);
            XmlRpcClient client = new XmlRpcClient("http://localhost:" + port);

            RPCClient myClient = new RPCClient(client);

            myClient.executeAsynchronously(leftTerm, rightTerm);
            myClient.executeSynchronously(leftTerm, rightTerm);

        } catch (Exception e) {
            System.out.println("Client XML-RPC Exception: " + e);
            e.printStackTrace();
        }
    }

    public void executeAsynchronously(int leftTerm, int rightTerm) {
        AsynchronousCallbackHandler asynchronousCallbackHandler = new AsynchronousCallbackHandler();

        Vector<Integer> parameters = new Vector<>();

        parameters.add(leftTerm);
        parameters.add(rightTerm);

        System.out.println("Calling asyc...");
        this.client.executeAsync("MyServer.echoAsynchronous", parameters, asynchronousCallbackHandler);
        System.out.println("Async called.");
    }

    public void executeSynchronously(int leftTerm, int rightTerm) throws XmlRpcException, IOException {
        AsynchronousCallbackHandler asynchronousCallbackHandler = new AsynchronousCallbackHandler();

        Vector<Integer> parameters = new Vector<>();

        parameters.add(leftTerm);
        parameters.add(rightTerm);

        System.out.println("Calling sync...");
        Object response = this.client.execute("MyServer.echo", parameters);
        System.out.println("Sync called.");

        if (response instanceof Integer) {
            Integer result = (Integer) response;
            System.out.println("The result of " + leftTerm + " + " + rightTerm + " = " + result);
        } else {
            System.out.println("Invalid response: " + response.toString());
        }
    }
}
