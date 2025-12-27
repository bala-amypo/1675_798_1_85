@SpringBootApplication
@ServletComponentScan
public class DemoApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(DemoApplication.class, args);
        System.out.println("Real Estate Rating Engine started successfully on port: " +
                context.getEnvironment().getProperty("server.port", "8080"));
    }
}
