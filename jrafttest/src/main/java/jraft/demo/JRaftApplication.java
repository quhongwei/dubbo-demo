package jraft.demo;

import com.alipay.sofa.jraft.example.counter.CounterClient;
import com.alipay.sofa.jraft.example.counter.CounterServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * java -jar jrafttest-executable.jar server /tmp/server1 counter 127.0.0.1:8081 127.0.0.1:8081,127.0.0.1:8082
 *
 * java -jar jrafttest-executable.jar server /tmp/server2 counter 127.0.0.1:8082 127.0.0.1:8081,127.0.0.1:8082
 *
 * java -jar jrafttest-executable.jar client counter 11.124.179.77:9999,11.124.22.28:9999
 *
 * @author: hongwei.quhw
 * @date: 2020-11-04 11:43
 */
@SpringBootApplication
public class JRaftApplication {

    public static void main(String[] args) throws Exception{

        String[] params = new String[args.length - 1];
        System.arraycopy(args, 1, params, 0, args.length - 1);

        for (String arg : args) {
            System.out.println(arg);
        }

        if (args[0].equalsIgnoreCase("server")) {
            CounterServer.main(params);
        } else if (args[0].equalsIgnoreCase("client")){
            CounterClient.main(params);
        } else {
            throw new RuntimeException("参数不对");
        }

        SpringApplication.run(JRaftApplication.class, args);

        System.in.read();
    }
}
