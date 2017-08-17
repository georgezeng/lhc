package lhc.schedule;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lhc.controller.KaiJiangController;

@Component
public class AutocalScheduler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private KaiJiangController controller;

	@Transactional
	@Scheduled(cron = "0 0 0/6 * * ?")
	public void run() {
		try {
			logger.info("Start doing auto sync and calculation schedule...");
			controller.sync(Calendar.getInstance().get(Calendar.YEAR));
			logger.info("End of auto sync and calculation schedule...");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
