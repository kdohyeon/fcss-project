package fast.campus.netplix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class NetplixBatchApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(NetplixBatchApplication.class);
        SpringApplication.exit(run);
    }
}
