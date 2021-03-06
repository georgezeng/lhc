package lhc.schedule;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lhc.controller.KaiJiangController;
import lhc.controller.YZController;

@Component
public class AutocalScheduler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private KaiJiangController controller;

	@Autowired
	private YZController yzController;

	@Scheduled(cron = "0 0 1 * * ?")
	public void run() {
		try {
			logger.info("Start doing auto sync and calculation schedule...");
			controller.sync(Calendar.getInstance().get(Calendar.YEAR), false);
			yzController.calYZ();
			logger.info("End of auto sync and calculation schedule...");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
