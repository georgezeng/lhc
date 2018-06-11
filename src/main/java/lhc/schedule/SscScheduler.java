package lhc.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lhc.service.SscService;

@Component
public class SscScheduler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SscService service;

	@Scheduled(cron = "0 0 1 * * ?")
//	@PostConstruct
	public void run() {
		try {
			service.pullData();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
