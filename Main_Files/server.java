import java.net.*;
import java.io.*;
 
public class server {
    public static void main(String[] args) throws Exception {
         
        if (args.length != 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }
 
        int portNumber = Integer.parseInt(args[0]);
 
        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
         
            String inputLine, outputLine;
             
             //Initiate conversation with client
            serverProtocol ser = new serverProtocol();
            outputLine = ser.processInput(null);
          out.println(outputLine);
 
            while ((inputLine = in.readLine()) != null) {
            	//System.out.println("HII");
                outputLine = ser.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
