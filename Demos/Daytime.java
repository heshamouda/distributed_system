package Demos;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Daytime {

    public static void main(String[] args) throws UnknownHostException, IOException {

        try (Socket s = new Socket("time-a.nist.gov", 13)) {
            InputStream in = s.getInputStream();
            int ch = in.read();
            while (ch != -1) {
                System.out.print((char) ch);
                ch = in.read();
            }
        }

    }
}
