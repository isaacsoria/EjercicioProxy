package proxy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProxyThread extends Thread {
    //Logger para mensajes.
    private static Logger log = Logger.getLogger("proxy.thread");

    private Socket socket = null;

    //Limite
    private static final int size = 32768;

    public ProxyThread(Socket socket) {
        super("ProxyThread");
        this.socket = socket;
    }

    public void run() {
        try {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            int count = 0;
            String urlToCall = "";
            while ((inputLine = input.readLine()) != null) {
                try {
                    // Separate the entire request by blank space
                    StringTokenizer tokenString = new StringTokenizer(inputLine);
                    tokenString.nextToken();
                } catch (Exception e) {
                    break;
                }
                // Mostrar URL
                if (count == 0) {
                    log.info("------------ \n URL: " + inputLine);
                    //Limpiar URL
                    String[] aux = inputLine.split(" ");
                    urlToCall = aux[1];
                }
                count++;
            }
            BufferedReader bufferReader = null;
            try {
                URL url = new URL(urlToCall);
                URLConnection urlConnection = url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(false);
                //Obtener respuesta de la pagina
                InputStream inputStream = null;
                try {
                    inputStream = urlConnection.getInputStream();
                    bufferReader = new BufferedReader(new InputStreamReader(inputStream));
                } catch (IOException ioe) {
                }
                //Del CLiente
                byte receivedData[] = new byte[size];
                int index = inputStream.read(receivedData, 0, size);

                while (index != -1) {
                    output.write(receivedData, 0, index);
                    index = inputStream.read(receivedData, 0, size);
                    log.info(String.format("I got: %s", new String(receivedData, "UTF-8")));
                }
                output.flush();
            } catch (Exception e) {
                log.log(Level.SEVERE, "Encountered exception: " + e);
                output.writeBytes("");
            }
            // CErrar buffers e inputs y ouputs
            if (bufferReader != null) {
                bufferReader.close();
            }
            if (output != null) {
                output.close();
            }
            if (input != null) {
                input.close();
            }
            if (socket != null) {
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}