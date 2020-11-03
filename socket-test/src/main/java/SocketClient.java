import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    public static void main(String args[]) throws Exception {
        System.out.println(args[0]);
        String host = args[0];
        int port = 55533;
        while (true) {
        Socket socket = new Socket(host, port);
            OutputStream outputStream = socket.getOutputStream();


            String message = String.valueOf(System.currentTimeMillis());
            System.out.println(message);
            byte[] sendBytes = message.getBytes("UTF-8");
            outputStream.write(sendBytes);
            outputStream.flush();

            outputStream.close();

            socket.close();
        }
    }


}