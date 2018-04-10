package lhc.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableScheduling
// @EnableRedisHttpSession
// @EnableRedisRepositories(basePackages = { "lhc.repository.redis.api" })
@EnableJpaRepositories(basePackages = { "lhc.repository.jpa.api" })
public class AppConfig extends AsyncConfigurerSupport {
	@Value("${executor.poolSize}")
	private int poolSize;

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(poolSize);
		executor.setMaxPoolSize(poolSize);
		executor.setQueueCapacity(Integer.MAX_VALUE);
		executor.setThreadNamePrefix("Async-Executor-");
		executor.initialize();
		return executor;
	}
	
	@Bean
	public Executor subExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(50);
		executor.setMaxPoolSize(50);
		executor.setQueueCapacity(Integer.MAX_VALUE);
		executor.setThreadNamePrefix("Async-SubExecutor-");
		executor.initialize();
		return executor;
	}
	
	/**
	 * fix for redis cloud service to ignore the keyspace nitification configs
	 * 
	 * @return
	 */
	// @Bean
	// public static ConfigureRedisAction configureRedisAction() {
	// return ConfigureRedisAction.NO_OP;
	// }

	// @Value("${spring.session.redis.namespace}")
	// private String sessionKeyNamespace;

	// @Autowired
	// private RedisOperationsSessionRepository sessionReporitory;
	//
	// @PostConstruct
	// public void init() {
	// sessionReporitory.setRedisKeyNamespace(sessionKeyNamespace);
	// }
}
