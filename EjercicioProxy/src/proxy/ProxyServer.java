package proxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProxyServer {
    private static Logger log = Logger.getLogger("proxy.server");

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean wait = true;

        int port = 10000; // por defecto inicia en este puerto.
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
        }
        try {
            serverSocket = new ServerSocket(port);
            log.info("El Proxy inicio en el puerto: " + port);
        } catch (IOException e) {
            log.log(Level.SEVERE, "No se pudo lanzar en el puerto " + port);
            System.exit(-1);
        }

        while (wait) {
            new ProxyThread(serverSocket.accept()).start();
        }
        serverSocket.close();
    }
}