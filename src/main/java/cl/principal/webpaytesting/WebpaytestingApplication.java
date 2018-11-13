package cl.principal.webpaytesting;


import cl.principal.webpaytesting.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class WebpaytestingApplication {

	private static final Logger log = LoggerFactory.getLogger(WebpaytestingApplication.class);

	public static void main(String[] args) throws UnknownHostException {

		SpringApplication app = new SpringApplication(WebpaytestingApplication.class);
		Environment env = app.run(args).getEnvironment();
		//SpringApplication.run(WebpaytestingApplication.class, args);
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\t{}://localhost:{}\n\t" +
						"External: \t{}://{}:{}\n\t" +
						"Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol,
				env.getProperty("server.port"),
				protocol,
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"),
				env.getActiveProfiles());
	}
}
