import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import utils.IdWorker;
@EnableTransactionManagement
@SpringBootApplication
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class,args);

    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

}
