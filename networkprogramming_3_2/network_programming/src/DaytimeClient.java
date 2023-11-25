import java.io.*;
import java.net.*;

public class DaytimeClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // localhost로 변경
        int port = 3747; // TimeServer의 포트 번호

        Socket socket = null;
        try {
            socket = new Socket(hostname, port);
            socket.setSoTimeout(15000);

            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");

            for (int c = reader.read(); c != -1; c = reader.read()) {
                time.append((char) c);
            }
            System.out.println("Server time: " + time);
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    // ignore
                }
            }
        }
    }
}
