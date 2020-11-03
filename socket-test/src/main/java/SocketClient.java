/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */

import java.io.OutputStream;
import java.net.Socket;

/**
 * Copyright (C) 2013-2018 Ant Financial Services Group
 *
 * @author quhongwei
 * @version : SocketClient.java, v 0.1 2020年11月03日 10:06 quhongwei Exp $
 */
public class SocketClient {
    public static void main(String args[]) throws Exception {
        // 要连接的服务端IP地址和端口
        System.out.println(args[0]);
        String host = args[0];
        int port = 55533;
        // 与服务端建立连接
        while (true) {
        Socket socket = new Socket(host, port);
        // 建立连接后获得输出流

            OutputStream outputStream = socket.getOutputStream();


            String message = String.valueOf(System.currentTimeMillis());
            System.out.println(message);
            //首先需要计算得知消息的长度
            byte[] sendBytes = message.getBytes("UTF-8");
            //然后将消息的长度优先发送出去
            //outputStream.write(sendBytes.length >> 8);
            //outputStream.write(sendBytes.length);
            //然后将消息再次发送出去
            outputStream.write(sendBytes);
            outputStream.flush();

            outputStream.close();

            socket.close();
        }
    }


}