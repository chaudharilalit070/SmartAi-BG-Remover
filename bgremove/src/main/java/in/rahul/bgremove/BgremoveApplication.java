package in.rahul.bgremove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BgremoveApplication {

	public static void main(String[] args) {
		SpringApplication.run(BgremoveApplication.class, args);
	}

}
//4:35 min
