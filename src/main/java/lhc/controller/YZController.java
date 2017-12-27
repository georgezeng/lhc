package lhc.controller;

import java.io.Writer;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lhc.constants.SwNums;
import lhc.constants.WxNums;
import lhc.domain.Avg;
import lhc.domain.BaseDsYz;
import lhc.domain.BaseYz;
import lhc.domain.Bs9qLrYz;
import lhc.domain.Bs9qYz;
import lhc.domain.Bs9qZfYz;
import lhc.domain.BsYz;
import lhc.domain.BsZfYz;
import lhc.domain.DsLrYz;
import lhc.domain.DsYz;
import lhc.domain.DsZfYz;
import lhc.domain.FxSw1;
import lhc.domain.FxSw2;
import lhc.domain.FxSw3;
import lhc.domain.HmDsYz;
import lhc.domain.KaiJiang;
import lhc.domain.LhDsYz;
import lhc.domain.LhLrYz;
import lhc.domain.LhYz;
import lhc.domain.LhZfYz;
import lhc.domain.MwCsYz;
import lhc.domain.MwLrYz;
import lhc.domain.MwYz;
import lhc.domain.MwZfYz;
import lhc.domain.PdLrYz;
import lhc.domain.PdYz;
import lhc.domain.PdZfYz;
import lhc.domain.PtYz;
import lhc.domain.QiwYz;
import lhc.domain.QiwZfYz;
import lhc.domain.QqYz;
import lhc.domain.QqZfYz;
import lhc.domain.QwYz;
import lhc.domain.SlqLrYz;
import lhc.domain.SlqYz;
import lhc.domain.SlqZfYz;
import lhc.domain.SqYz;
import lhc.domain.SwYz;
import lhc.domain.SwZfYz;
import lhc.domain.SxCsYz;
import lhc.domain.SxDsYz;
import lhc.domain.SxLrYz;
import lhc.domain.SxYz;
import lhc.domain.SxZfYz;
import lhc.domain.SxZfYz2;
import lhc.domain.Tm12FdLrYz;
import lhc.domain.Tm12FdYz;
import lhc.domain.Tm12FdZfYz;
import lhc.domain.TmFdYz;
import lhc.domain.TmYz;
import lhc.domain.TwelveLrYz;
import lhc.domain.TwelveYz;
import lhc.domain.TwelveZfYz;
import lhc.domain.WxYz;
import lhc.domain.WxZfYz;
import lhc.domain.WxdsLrYz;
import lhc.domain.WxdsYz;
import lhc.domain.WxdsZfYz;
import lhc.domain.ZsLrYz;
import lhc.domain.ZsYz;
import lhc.domain.ZsZfYz;
import lhc.domain.Zx10Yz;
import lhc.domain.Zx10ZfYz;
import lhc.domain.Zx11Yz;
import lhc.domain.Zx11ZfYz;
import lhc.domain.Zx12Yz;
import lhc.domain.Zx12ZfYz;
import lhc.domain.Zx13Yz;
import lhc.domain.Zx13ZfYz;
import lhc.domain.Zx14Yz;
import lhc.domain.Zx14ZfYz;
import lhc.domain.Zx15Yz;
import lhc.domain.Zx15ZfYz;
import lhc.domain.Zx16Yz;
import lhc.domain.Zx16ZfYz;
import lhc.domain.Zx17Yz;
import lhc.domain.Zx17ZfYz;
import lhc.domain.Zx18Yz;
import lhc.domain.Zx18ZfYz;
import lhc.domain.Zx1Yz;
import lhc.domain.Zx1ZfYz;
import lhc.domain.Zx2Yz;
import lhc.domain.Zx2ZfYz;
import lhc.domain.Zx3Yz;
import lhc.domain.Zx3ZfYz;
import lhc.domain.Zx4Yz;
import lhc.domain.Zx4ZfYz;
import lhc.domain.Zx5Yz;
import lhc.domain.Zx5ZfYz;
import lhc.domain.Zx6Yz;
import lhc.domain.Zx6ZfYz;
import lhc.domain.Zx7Yz;
import lhc.domain.Zx7ZfYz;
import lhc.domain.Zx8Yz;
import lhc.domain.Zx8ZfYz;
import lhc.domain.Zx9Yz;
import lhc.domain.Zx9ZfYz;
import lhc.dto.BaseResult;
import lhc.dto.D1Yz;
import lhc.dto.DownloadDTO;
import lhc.dto.DownloadPrepareTZ;
import lhc.dto.J0Yz;
import lhc.dto.PmDTO;
import lhc.dto.PmNum;
import lhc.dto.SpecialNum;
import lhc.dto.TmYzInfo;
import lhc.dto.XbwJY;
import lhc.dto.XbwJYCondition;
import lhc.dto.XbwJYResult;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.enums.SX;
import lhc.repository.jpa.BaseYzDao;
import lhc.repository.jpa.Repositories;
import lhc.service.ParallelYzServiceWrapper;
import lhc.util.DateUtil;

@RestController
@RequestMapping("/mvc/yz")
@SuppressWarnings("unchecked")
public class YZController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected Repositories repositories;

	@Autowired
	protected ParallelYzServiceWrapper parallelYzServiceWrapper;

	@RequestMapping("/years")
	public BaseResult years() {
		List<KaiJiang> list = repositories.kaiJiangRepository.findGroupByYear();
		List<Integer> years = new ArrayList<Integer>();
		if (list != null && !list.isEmpty()) {
			for (KaiJiang data : list) {
				years.add(data.getYear());
			}
		}
		return new BaseResult(years);
	}

	@RequestMapping("/phases/{year}")
	public BaseResult phases(@PathVariable int year) {
		List<KaiJiang> list = repositories.kaiJiangRepository.findByYearOrderByPhaseDesc(year);
		List<Integer> phases = new ArrayList<Integer>();
		if (list != null && !list.isEmpty()) {
			for (KaiJiang data : list) {
				phases.add(data.getPhase());
			}
		}
		return new BaseResult(phases);
	}

	public void calSX() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calSX());
		futures.add(repositories.yzService.calSXDSYZ());
		futures.add(repositories.yzService.calSQYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calSXCSYZ());
		futures.add(parallelYzServiceWrapper.calAvg(repositories.sxyzRepository, "calSxyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.sxzfyz2Repository, "calSxzfAvg"));
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calSXLRYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.sxlryzRepository, "calSxlrAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calSX stage...");
	}

	public void calTM() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calHMQWYZ());
		futures.add(repositories.yzService.calTMYZ());
		futures.add(repositories.yzService.calHMDSYZ());
		futures.add(repositories.yzService.calPTYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calTM12FDYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.tm12fdyzRepository, "calTm12fdyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.tm12fdzfyzRepository, "calTm12fdzfAvg"));
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calTM12FDLRYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.tm12fdlryzRepository, "calTm12fdlrAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calTM stage...");
	}

	public void calSW() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calSWYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.swyzRepository, "calSwyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.swzfyzRepository, "calSwzfAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calSW stage...");
	}

	public void calMW() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calMWYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.mwyzRepository, "calMwyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.mwzfyzRepository, "calMwzfAvg"));
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calMWLRYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.mwlryzRepository, "calMwlrAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calMW stage...");
	}

	public void calLH() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calLHYZ());
		futures.add(repositories.yzService.calLHDSYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.lhyzRepository, "calLhyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.lhzfyzRepository, "calLhzfAvg"));
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calLHLRYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.lhlryzRepository, "calLhlrAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calLH stage...");
	}

	public void calQQ() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calQQYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.qqyzRepository, "calQqyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.qqzfyzRepository, "calQqzfAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calQQ stage...");
	}

	public void calQiw() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calQIWYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.qiwYzRepository, "calQiwyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.qiwzfYzRepository, "calQiwzfAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calQiw stage...");
	}

	public void calBS() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calBSYZ());
		futures.add(repositories.yzService.calBS9QYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.bsyzRepository, "calBsyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.bszfyzRepository, "calBszfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.bs9qyzRepository, "calBs9qyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.bs9qzfyzRepository, "calBs9qzfAvg"));
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calBS9QLRYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.bs9qlryzRepository, "calBs9qlrAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calBS stage...");
	}

	public void calDS() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calDSYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.dsyzRepository, "calDsyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.dszfyzRepository, "calDszfAvg"));
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calDSLRYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.dslrYzRepository, "calDslrAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calDS stage...");
	}

	public void calZS() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calZSYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zsyzRepository, "calZsyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zszfyzRepository, "calZszfAvg"));
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calZSLRYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zslryzRepository, "calZslrAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calZS stage...");
	}

	public void calWX() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calWXYZ());
		futures.add(repositories.yzService.calWXDSYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.wxyzRepository, "calWxyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.wxzfyzRepository, "calWxzfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.wxdsyzRepository, "calWxdsyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.wxdszfyzRepository, "calWxdszfAvg"));
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calWXDSLRYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.wxdslryzRepository, "calWxdslrAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calWX stage...");
	}

	public void calTwelve() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calTwelveYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.twelveyzRepository, "calTwelveyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.twelvezfyzRepository, "calTwelvezfAvg"));
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calTwelveLRYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.twelvelryzRepository, "calTwelvelrAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calTwelve stage...");
	}

	public void calSLQ() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calSLQYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.slqyzRepository, "calSlqyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.slqzfyzRepository, "calSlqzfAvg"));
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calSLQLRYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.slqlryzRepository, "calSlqlrAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calSLQ stage...");
	}

	public void calPD() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calPDYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.pdyzRepository, "calPdyzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.pdzfyzRepository, "calPdzfAvg"));
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(repositories.yzService.calPDLRYZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.pdlryzRepository, "calPdlrAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calPD stage...");
	}

	public void calZX() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(repositories.yzService.calZX1YZ());
		futures.add(repositories.yzService.calZX2YZ());
		futures.add(repositories.yzService.calZX3YZ());
		futures.add(repositories.yzService.calZX4YZ());
		futures.add(repositories.yzService.calZX5YZ());
		futures.add(repositories.yzService.calZX6YZ());
		futures.add(repositories.yzService.calZX7YZ());
		futures.add(repositories.yzService.calZX8YZ());
		futures.add(repositories.yzService.calZX9YZ());
		futures.add(repositories.yzService.calZX10YZ());
		futures.add(repositories.yzService.calZX11YZ());
		futures.add(repositories.yzService.calZX12YZ());
		futures.add(repositories.yzService.calZX13YZ());
		futures.add(repositories.yzService.calZX14YZ());
		futures.add(repositories.yzService.calZX15YZ());
		futures.add(repositories.yzService.calZX16YZ());
		futures.add(repositories.yzService.calZX17YZ());
		futures.add(repositories.yzService.calZX18YZ());
		repositories.yzService.sleep(futures, 100);
		futures.clear();
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx1yzRepository, "calZx1yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx1zfyzRepository, "calZx1zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx2yzRepository, "calZx2yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx2zfyzRepository, "calZx2zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx3yzRepository, "calZx3yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx3zfyzRepository, "calZx3zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx4yzRepository, "calZx4yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx4zfyzRepository, "calZx4zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx5yzRepository, "calZx5yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx5zfyzRepository, "calZx5zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx6yzRepository, "calZx6yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx6zfyzRepository, "calZx6zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx7yzRepository, "calZx7yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx7zfyzRepository, "calZx7zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx8yzRepository, "calZx8yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx8zfyzRepository, "calZx8zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx9yzRepository, "calZx9yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx9zfyzRepository, "calZx9zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx10yzRepository, "calZx10yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx10zfyzRepository, "calZx10zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx11yzRepository, "calZx11yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx11zfyzRepository, "calZx11zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx12yzRepository, "calZx12yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx12zfyzRepository, "calZx12zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx13yzRepository, "calZx13yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx13zfyzRepository, "calZx13zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx14yzRepository, "calZx14yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx14zfyzRepository, "calZx14zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx15yzRepository, "calZx15yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx15zfyzRepository, "calZx15zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx16yzRepository, "calZx16yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx16zfyzRepository, "calZx16zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx17yzRepository, "calZx17yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx17zfyzRepository, "calZx17zfAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx18yzRepository, "calZx18yzAvg"));
		futures.add(parallelYzServiceWrapper.calAvg(repositories.zx18zfyzRepository, "calZx18zfAvg"));
		repositories.yzService.sleep(futures, 100);
		logger.info("End of calZX stage...");
	}

	public void calFXSW() throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		repositories.yzService.clearFxSwData();
		futures.add(repositories.yzService.calFxSw1());
		futures.add(repositories.yzService.calFxSw2());
		repositories.yzService.sleep(futures, 100);
		repositories.yzService.saveFxSwData();
		repositories.yzService.clearFxSwData();
		futures.clear();
		futures.add(repositories.yzService.calFxSw3());
		repositories.yzService.sleep(futures, 100);
		repositories.yzService.saveFxSwData();
		repositories.yzService.clearFxSwData();
		logger.info("End of calFXSW stage...");
	}

	@RequestMapping("/calYZ")
	public BaseResult calYZ() throws Exception {
		calBS();
		calDS();
		calLH();
		calMW();
		calPD();
		calQiw();
		calQQ();
		calSLQ();
		calSX();
		calSW();
		calTM();
		calTwelve();
		calWX();
		calZS();
		calZX();
		calFXSW();
		logger.info("Done calYZ...");
		return BaseResult.EMPTY;
	}

	@RequestMapping("/listSX")
	public BaseResult listSX(@RequestBody QueryInfo<SxYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<SxYz> result = repositories.sxYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				SxYz last = new SxYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listSXLRYZ")
	public BaseResult listSXLRYZ(@RequestBody QueryInfo<SxLrYz> queryInfo) throws Exception {
		PageResult<SxLrYz> result = repositories.sxlrYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listTwelveLRYZ")
	public BaseResult listTwelveLRYZ(@RequestBody QueryInfo<TwelveLrYz> queryInfo) throws Exception {
		PageResult<TwelveLrYz> result = repositories.twelvelrYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listSLQLRYZ")
	public BaseResult listSLQLRYZ(@RequestBody QueryInfo<SlqLrYz> queryInfo) throws Exception {
		PageResult<SlqLrYz> result = repositories.slqlrYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listPDLRYZ")
	public BaseResult listPDLRYZ(@RequestBody QueryInfo<PdLrYz> queryInfo) throws Exception {
		PageResult<PdLrYz> result = repositories.pdlrYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listTM12FDLRYZ")
	public BaseResult listTM12FDLRYZ(@RequestBody QueryInfo<Tm12FdLrYz> queryInfo) throws Exception {
		PageResult<Tm12FdLrYz> result = repositories.tm12fdlrYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listMWLRYZ")
	public BaseResult listMWLRYZ(@RequestBody QueryInfo<MwLrYz> queryInfo) throws Exception {
		PageResult<MwLrYz> result = repositories.mwlrYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listDSLRYZ")
	public BaseResult listDSLRYZ(@RequestBody QueryInfo<DsLrYz> queryInfo) throws Exception {
		PageResult<DsLrYz> result = repositories.dslrYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listLHLRYZ")
	public BaseResult listLHLRYZ(@RequestBody QueryInfo<LhLrYz> queryInfo) throws Exception {
		PageResult<LhLrYz> result = repositories.lhlrYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listBS9QLRYZ")
	public BaseResult listBS9QLRYZ(@RequestBody QueryInfo<Bs9qLrYz> queryInfo) throws Exception {
		PageResult<Bs9qLrYz> result = repositories.bs9qlrYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listWXDSLRYZ")
	public BaseResult listWXDSLRYZ(@RequestBody QueryInfo<WxdsLrYz> queryInfo) throws Exception {
		PageResult<WxdsLrYz> result = repositories.wxdslrYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZSLRYZ")
	public BaseResult listZSLRYZ(@RequestBody QueryInfo<ZsLrYz> queryInfo) throws Exception {
		PageResult<ZsLrYz> result = repositories.zslrYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listSWYZ")
	public BaseResult listSWYZ(@RequestBody QueryInfo<SwYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<SwYz> result = repositories.swYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				SwYz last = new SwYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listSWZF")
	public BaseResult listSWZF(@RequestBody QueryInfo<SwZfYz> queryInfo) throws Exception {
		PageResult<SwZfYz> result = repositories.swZfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX1YZ")
	public BaseResult listZX1YZ(@RequestBody QueryInfo<Zx1Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx1Yz> result = repositories.zx1yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx1Yz last = new Zx1Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX1ZF")
	public BaseResult listZX1ZF(@RequestBody QueryInfo<Zx1ZfYz> queryInfo) throws Exception {
		PageResult<Zx1ZfYz> result = repositories.zx1zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX2ZF")
	public BaseResult listZX2ZF(@RequestBody QueryInfo<Zx2ZfYz> queryInfo) throws Exception {
		PageResult<Zx2ZfYz> result = repositories.zx2zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX3ZF")
	public BaseResult listZX3ZF(@RequestBody QueryInfo<Zx3ZfYz> queryInfo) throws Exception {
		PageResult<Zx3ZfYz> result = repositories.zx3zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX4ZF")
	public BaseResult listZX4ZF(@RequestBody QueryInfo<Zx4ZfYz> queryInfo) throws Exception {
		PageResult<Zx4ZfYz> result = repositories.zx4zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX5ZF")
	public BaseResult listZX5ZF(@RequestBody QueryInfo<Zx5ZfYz> queryInfo) throws Exception {
		PageResult<Zx5ZfYz> result = repositories.zx5zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX6ZF")
	public BaseResult listZX6ZF(@RequestBody QueryInfo<Zx6ZfYz> queryInfo) throws Exception {
		PageResult<Zx6ZfYz> result = repositories.zx6zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX7ZF")
	public BaseResult listZX7ZF(@RequestBody QueryInfo<Zx7ZfYz> queryInfo) throws Exception {
		PageResult<Zx7ZfYz> result = repositories.zx7zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX8ZF")
	public BaseResult listZX8ZF(@RequestBody QueryInfo<Zx8ZfYz> queryInfo) throws Exception {
		PageResult<Zx8ZfYz> result = repositories.zx8zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX9ZF")
	public BaseResult listZX9ZF(@RequestBody QueryInfo<Zx9ZfYz> queryInfo) throws Exception {
		PageResult<Zx9ZfYz> result = repositories.zx9zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX10ZF")
	public BaseResult listZX10ZF(@RequestBody QueryInfo<Zx10ZfYz> queryInfo) throws Exception {
		PageResult<Zx10ZfYz> result = repositories.zx10zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX11ZF")
	public BaseResult listZX11ZF(@RequestBody QueryInfo<Zx11ZfYz> queryInfo) throws Exception {
		PageResult<Zx11ZfYz> result = repositories.zx11zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX12ZF")
	public BaseResult listZX12ZF(@RequestBody QueryInfo<Zx12ZfYz> queryInfo) throws Exception {
		PageResult<Zx12ZfYz> result = repositories.zx12zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX13ZF")
	public BaseResult listZX13ZF(@RequestBody QueryInfo<Zx13ZfYz> queryInfo) throws Exception {
		PageResult<Zx13ZfYz> result = repositories.zx13zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX14ZF")
	public BaseResult listZX14ZF(@RequestBody QueryInfo<Zx14ZfYz> queryInfo) throws Exception {
		PageResult<Zx14ZfYz> result = repositories.zx14zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX15ZF")
	public BaseResult listZX15ZF(@RequestBody QueryInfo<Zx15ZfYz> queryInfo) throws Exception {
		PageResult<Zx15ZfYz> result = repositories.zx15zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX16ZF")
	public BaseResult listZX16ZF(@RequestBody QueryInfo<Zx16ZfYz> queryInfo) throws Exception {
		PageResult<Zx16ZfYz> result = repositories.zx16zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX17ZF")
	public BaseResult listZX17ZF(@RequestBody QueryInfo<Zx17ZfYz> queryInfo) throws Exception {
		PageResult<Zx17ZfYz> result = repositories.zx17zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX18ZF")
	public BaseResult listZX18ZF(@RequestBody QueryInfo<Zx18ZfYz> queryInfo) throws Exception {
		PageResult<Zx18ZfYz> result = repositories.zx18zfyzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZX2YZ")
	public BaseResult listZX2YZ(@RequestBody QueryInfo<Zx2Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx2Yz> result = repositories.zx2yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx2Yz last = new Zx2Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX3YZ")
	public BaseResult listZX3YZ(@RequestBody QueryInfo<Zx3Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx3Yz> result = repositories.zx3yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx3Yz last = new Zx3Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX4YZ")
	public BaseResult listZX4YZ(@RequestBody QueryInfo<Zx4Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx4Yz> result = repositories.zx4yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx4Yz last = new Zx4Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX5YZ")
	public BaseResult listZX5YZ(@RequestBody QueryInfo<Zx5Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx5Yz> result = repositories.zx5yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx5Yz last = new Zx5Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX6YZ")
	public BaseResult listZX6YZ(@RequestBody QueryInfo<Zx6Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx6Yz> result = repositories.zx6yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx6Yz last = new Zx6Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX7YZ")
	public BaseResult listZX7YZ(@RequestBody QueryInfo<Zx7Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx7Yz> result = repositories.zx7yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx7Yz last = new Zx7Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX8YZ")
	public BaseResult listZX8YZ(@RequestBody QueryInfo<Zx8Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx8Yz> result = repositories.zx8yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx8Yz last = new Zx8Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX9YZ")
	public BaseResult listZX9YZ(@RequestBody QueryInfo<Zx9Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx9Yz> result = repositories.zx9yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx9Yz last = new Zx9Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX10YZ")
	public BaseResult listZX10YZ(@RequestBody QueryInfo<Zx10Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx10Yz> result = repositories.zx10yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx10Yz last = new Zx10Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX11YZ")
	public BaseResult listZX11YZ(@RequestBody QueryInfo<Zx11Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx11Yz> result = repositories.zx11yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx11Yz last = new Zx11Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX12YZ")
	public BaseResult listZX12YZ(@RequestBody QueryInfo<Zx12Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx12Yz> result = repositories.zx12yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx12Yz last = new Zx12Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX13YZ")
	public BaseResult listZX13YZ(@RequestBody QueryInfo<Zx13Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx13Yz> result = repositories.zx13yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx13Yz last = new Zx13Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX14YZ")
	public BaseResult listZX14YZ(@RequestBody QueryInfo<Zx14Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx14Yz> result = repositories.zx14yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx14Yz last = new Zx14Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX15YZ")
	public BaseResult listZX15YZ(@RequestBody QueryInfo<Zx15Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx15Yz> result = repositories.zx15yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx15Yz last = new Zx15Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX16YZ")
	public BaseResult listZX16YZ(@RequestBody QueryInfo<Zx16Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx16Yz> result = repositories.zx16yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx16Yz last = new Zx16Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX17YZ")
	public BaseResult listZX17YZ(@RequestBody QueryInfo<Zx17Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx17Yz> result = repositories.zx17yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx17Yz last = new Zx17Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZX18YZ")
	public BaseResult listZX18YZ(@RequestBody QueryInfo<Zx18Yz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Zx18Yz> result = repositories.zx18yzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Zx18Yz last = new Zx18Yz();
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listMWYZ")
	public BaseResult listMWYZ(@RequestBody QueryInfo<MwYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<MwYz> result = repositories.mwYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				MwYz last = new MwYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listMWZF")
	public BaseResult listMWZF(@RequestBody QueryInfo<MwZfYz> queryInfo) throws Exception {
		PageResult<MwZfYz> result = repositories.mwZfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listLHYZ")
	public BaseResult listLHYZ(@RequestBody QueryInfo<LhYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<LhYz> result = repositories.lhYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				LhYz last = new LhYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listBS9QYZ")
	public BaseResult listBS9QYZ(@RequestBody QueryInfo<Bs9qYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<Bs9qYz> result = repositories.bs9qYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Bs9qYz last = new Bs9qYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listWXDSYZ")
	public BaseResult listWXDSYZ(@RequestBody QueryInfo<WxdsYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<WxdsYz> result = repositories.wxdsYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				WxdsYz last = new WxdsYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listLHZF")
	public BaseResult listLHZF(@RequestBody QueryInfo<LhZfYz> queryInfo) throws Exception {
		PageResult<LhZfYz> result = repositories.lhZfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listBS9QZF")
	public BaseResult listBS9QZF(@RequestBody QueryInfo<Bs9qZfYz> queryInfo) throws Exception {
		PageResult<Bs9qZfYz> result = repositories.bs9qZfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listWXDSZF")
	public BaseResult listWXDSZF(@RequestBody QueryInfo<WxdsZfYz> queryInfo) throws Exception {
		PageResult<WxdsZfYz> result = repositories.wxdsZfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listQQYZ")
	public BaseResult listQQYZ(@RequestBody QueryInfo<QqYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<QqYz> result = repositories.qqYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				QqYz last = new QqYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listQQZF")
	public BaseResult listQQZF(@RequestBody QueryInfo<QqZfYz> queryInfo) throws Exception {
		PageResult<QqZfYz> result = repositories.qqZfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listQIWYZ")
	public BaseResult listQIWYZ(@RequestBody QueryInfo<QiwYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<QiwYz> result = repositories.qiwYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				QiwYz last = new QiwYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listQIWZF")
	public BaseResult listQIWZF(@RequestBody QueryInfo<QiwZfYz> queryInfo) throws Exception {
		PageResult<QiwZfYz> result = repositories.qiwzfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listSQYZ")
	public BaseResult listSQYZ(@RequestBody QueryInfo<SqYz> queryInfo) throws Exception {
		PageResult<SqYz> result = repositories.sqYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			SqYz last = new SqYz();
			last.setTotal(result.getList().size());
			result.getList().add(last);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listBSYZ")
	public BaseResult listBSYZ(@RequestBody QueryInfo<BsYz> queryInfo, String mode) throws Exception {
		PageResult<BsYz> result = repositories.bsYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				BsYz last = new BsYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listBSZF")
	public BaseResult listBSZF(@RequestBody QueryInfo<BsZfYz> queryInfo) throws Exception {
		PageResult<BsZfYz> result = repositories.bszfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listWXYZ")
	public BaseResult listWXYZ(@RequestBody QueryInfo<WxYz> queryInfo, String mode) throws Exception {
		PageResult<WxYz> result = repositories.wxYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				WxYz last = new WxYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listWXZF")
	public BaseResult listWXZF(@RequestBody QueryInfo<WxZfYz> queryInfo) throws Exception {
		PageResult<WxZfYz> result = repositories.wxzfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listZSYZ")
	public BaseResult listZSYZ(@RequestBody QueryInfo<ZsYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<ZsYz> result = repositories.zsYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				ZsYz last = new ZsYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listZSZF")
	public BaseResult listZSZF(@RequestBody QueryInfo<ZsZfYz> queryInfo) throws Exception {
		PageResult<ZsZfYz> result = repositories.zszfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listDSYZ")
	public BaseResult listDSYZ(@RequestBody QueryInfo<DsYz> queryInfo) throws Exception {
		return new BaseResult(repositories.dsYzDao.query(queryInfo));
	}

	@RequestMapping("/listDSZF")
	public BaseResult listDSZF(@RequestBody QueryInfo<DsZfYz> queryInfo) throws Exception {
		PageResult<DsZfYz> result = repositories.dszfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listTwelveYZ")
	public BaseResult listTwelveYZ(@RequestBody QueryInfo<TwelveYz> queryInfo, String mode) throws Exception {
		PageResult<TwelveYz> result = repositories.twelveYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				TwelveYz last = new TwelveYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listTwelveZF")
	public BaseResult listTwelveZF(@RequestBody QueryInfo<TwelveZfYz> queryInfo) throws Exception {
		PageResult<TwelveZfYz> result = repositories.twelvezfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listSLQYZ")
	public BaseResult listSLQYZ(@RequestBody QueryInfo<SlqYz> queryInfo, String mode) throws Exception {
		PageResult<SlqYz> result = repositories.slqYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				SlqYz last = new SlqYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listPDYZ")
	public BaseResult listPDYZ(@RequestBody QueryInfo<PdYz> queryInfo, String mode) throws Exception {
		PageResult<PdYz> result = repositories.pdYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				PdYz last = new PdYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listTM12FDYZ")
	public BaseResult listTM12FDYZ(@RequestBody QueryInfo<Tm12FdYz> queryInfo, String mode) throws Exception {
		PageResult<Tm12FdYz> result = repositories.tm12fdYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			Tm12FdYz queryObj = queryInfo.getObject();
			TmYz tmResult = repositories.tmyzRepository.findByYearAndPhase(queryObj.getYear(), queryObj.getPhase());
			if (tmResult != null) {
				List<TmYzInfo> fdList = repositories.yzService.getTMFDList(tmResult, false);
				if (result != null && result.getTotal() > 0) {
					Tm12FdYz data = new Tm12FdYz();
					data.setList(fdList);
					result.getList().add(data);
				}
			}
		} else if ("2".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				Tm12FdYz last = new Tm12FdYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listTM12FDLastPhaseList")
	public BaseResult listTM12FDLastPhaseList() throws Exception {
		List<Integer> years = (List<Integer>) years().getData();
		int year = years.get(0);
		List<Integer> phases = (List<Integer>) phases(year).getData();
		TmYz tmResult = repositories.tmyzRepository.findByYearAndPhase(year, phases.get(0));
		return new BaseResult(repositories.yzService.getTMFDList(tmResult, false));
	}

	@RequestMapping("/listSLQZF")
	public BaseResult listSLQZF(@RequestBody QueryInfo<SlqZfYz> queryInfo) throws Exception {
		PageResult<SlqZfYz> result = repositories.slqzfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listPDZF")
	public BaseResult listPDZF(@RequestBody QueryInfo<PdZfYz> queryInfo) throws Exception {
		PageResult<PdZfYz> result = repositories.pdzfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listTM12FDZF")
	public BaseResult listTM12FDZF(@RequestBody QueryInfo<Tm12FdZfYz> queryInfo) throws Exception {
		PageResult<Tm12FdZfYz> result = repositories.tm12fdzfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listLHDSYZ")
	public BaseResult listLHDSYZ(@RequestBody QueryInfo<LhDsYz> queryInfo) throws Exception {
		PageResult<LhDsYz> result = repositories.lhdsYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			LhDsYz last = new LhDsYz();
			last.setTotal(result.getList().size());
			result.getList().add(last);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listSXDSYZ")
	public BaseResult listSXDSYZ(@RequestBody QueryInfo<SxDsYz> queryInfo) throws Exception {
		PageResult<SxDsYz> result = repositories.sxdsYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			SxDsYz last = new SxDsYz();
			last.setTotal(result.getList().size());
			result.getList().add(last);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listHMDSYZ")
	public BaseResult listHMDSYZ(@RequestBody QueryInfo<HmDsYz> queryInfo) throws Exception {
		PageResult<HmDsYz> result = repositories.hmdsYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			HmDsYz last = new HmDsYz();
			last.setTotal(result.getList().size());
			result.getList().add(last);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listSXZH")
	public BaseResult listSXZH(@RequestBody QueryInfo<SxYz> queryInfo) throws Exception {
		queryInfo.setToReverse(false);
		PageResult<SxYz> result = repositories.sxYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			SxYz last = result.getList().get(0);
			SxYz next = null;
			SxYz totalLine = new SxYz();
			for (int i = 0; i < result.getList().size(); i++) {
				SxYz data = result.getList().get(i);
				data.setTopSx(last.getCurrentSx());
				if (data.getCurrentSx().equals(last.getCurrentSx())) {
					if (i > 0) {
						next = result.getList().get(i - 1);
						next.setLastSx(data.getCurrentSx());
						Method sm = ReflectionUtils.findMethod(SxYz.class, "set" + next.getCurrentSx().name(), Integer.class);
						Method gm = ReflectionUtils.findMethod(SxYz.class, "get" + next.getCurrentSx().name());
						Integer count = (Integer) gm.invoke(totalLine);
						if (count == null) {
							count = 0;
						}
						count++;
						sm.invoke(totalLine, count);
					}
				}
			}
			Collections.reverse(result.getList());
			result.getList().add(totalLine);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listSXZF")
	public BaseResult listSXZF(@RequestBody QueryInfo<SxZfYz> queryInfo) throws Exception {
		PageResult<SxZfYz> result = repositories.sxZfYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			SxZfYz last = new SxZfYz();
			last.setTotal(result.getList().size());
			result.getList().add(last);
		}

		return new BaseResult(result);
	}

	@RequestMapping("/listSXZF2")
	public BaseResult listSXZF2(@RequestBody QueryInfo<SxZfYz2> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<SxZfYz2> result = repositories.sxZfYz2Dao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				SxZfYz2 last = new SxZfYz2();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listSXZFLevel2")
	public BaseResult listSXZFLevel2(@RequestBody QueryInfo<SxZfYz> queryInfo) throws Exception {
		PageResult<SxZfYz> result = repositories.sxZfYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			SxZfYz data = null;
			SxZfYz last = null;
			SxZfYz lastData = null;
			List<SxZfYz> list = new ArrayList<SxZfYz>();
			for (SxZfYz current : result.getList()) {
				data = new SxZfYz();
				data.setDate(current.getDate());
				data.setYear(current.getYear());
				data.setPhase(current.getPhase());
				data.setId(current.getId());

				int currentValue = 0;
				if (last != null) {
					for (int i = 0; i < 12; i++) {
						Method gm = ReflectionUtils.findMethod(SxZfYz.class, "getZf" + i);
						Integer value = (Integer) gm.invoke(lastData);
						if (value == null) {
							value = 0;
						}
						Method sm = ReflectionUtils.findMethod(SxZfYz.class, "setZf" + i, Integer.class);
						sm.invoke(data, value);
					}
					if (current.getCurrentPos() != null && last.getCurrentPos() != null) {
						BigDecimal pos = new BigDecimal(current.getCurrentPos()).subtract(new BigDecimal(last.getCurrentPos()))
								.abs();
						Method gm = ReflectionUtils.findMethod(SxZfYz.class, "getZf" + pos.intValue());
						Integer value = (Integer) gm.invoke(data);
						if (value == null) {
							value = 0;
						}
						currentValue = value + 1;
						Method sm = ReflectionUtils.findMethod(SxZfYz.class, "setZf" + pos.intValue(), Integer.class);
						sm.invoke(data, currentValue);
						data.setCurrentPos(pos.intValue());
					}
				}
				data.setDelta(currentValue);

				if (lastData != null) {
					int total = 0;
					for (int i = 0; i < 12; i++) {
						Method gm = ReflectionUtils.findMethod(SxZfYz.class, "getZf" + i);
						Integer value = (Integer) gm.invoke(lastData);
						if (value != null) {
							total += value;
						}
					}
					data.setTotal(total);
					data.setAvg(new BigDecimal(total / 12d));
					if (currentValue > 1) {
						data.setLastCountYz(currentValue - 2);
					}
				}

				last = current;
				lastData = data;
				list.add(data);
			}

			data = new SxZfYz();
			data.setTotal(list.size());
			list.add(data);
			result.setList(list);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/countSXZF")
	public BaseResult countSXZF(@RequestBody QueryInfo<SxZfYz> queryInfo) throws Exception {
		PageResult<SxZfYz> result = repositories.sxZfYzDao.query(queryInfo);
		List<SxZfYz> list = new ArrayList<SxZfYz>();
		if (result != null && result.getTotal() > 0) {
			SxZfYz lastZF = new SxZfYz();
			for (int i = 0; i < 12; i++) {
				Method m = ReflectionUtils.findMethod(SxZfYz.class, "setZf" + i, Integer.class);
				m.invoke(lastZF, 0);
			}
			for (SxZfYz data : result.getList()) {
				SxZfYz dto = new SxZfYz();
				for (int i = 0; i < 12; i++) {
					Method sm = ReflectionUtils.findMethod(SxZfYz.class, "setZf" + i, Integer.class);
					Method gm = ReflectionUtils.findMethod(SxZfYz.class, "getZf" + i);
					sm.invoke(dto, (Integer) gm.invoke(lastZF));
				}
				dto.setDate(data.getDate());
				dto.setYear(data.getYear());
				dto.setPhase(data.getPhase());
				dto.setId(data.getId());
				int currentValue = 0;
				for (int i = 0; i < 12; i++) {
					Method gm = ReflectionUtils.findMethod(SxZfYz.class, "getZf" + i);
					Integer zf = (Integer) gm.invoke(data);
					if (zf != null && zf == 0) {
						Method sm = ReflectionUtils.findMethod(SxZfYz.class, "setZf" + i, Integer.class);
						currentValue = 1 + (Integer) gm.invoke(dto);
						sm.invoke(dto, currentValue);
						break;
					}
				}
				dto.setDelta(currentValue);

				int total = 0;
				for (int i = 0; i < 12; i++) {
					Method gm = ReflectionUtils.findMethod(SxZfYz.class, "getZf" + i);
					Integer value = (Integer) gm.invoke(lastZF);
					if (value != null) {
						total += value;
					}
				}
				dto.setTotal(total);
				dto.setAvg(new BigDecimal(total / 12d));
				if (currentValue > 1) {
					dto.setLastCountYz(currentValue - 2);
				}

				list.add(dto);
				lastZF = dto;
			}

			lastZF = new SxZfYz();
			lastZF.setTotal(result.getList().size());
			list.add(lastZF);

		}
		result.setList(list);
		return new BaseResult(result);
	}

	@RequestMapping("/listSXJTCSYZ")
	public BaseResult listSXJTCSYZ(@RequestBody QueryInfo<SxCsYz> queryInfo) throws Exception {
		PageResult<SxCsYz> result = repositories.sxcsYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			result.getList().add(new SxCsYz());
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listSXCSYZ")
	public BaseResult listSXCSYZ(@RequestBody QueryInfo<SxYz> queryInfo) throws Exception {
		PageResult<SxYz> sxResult = repositories.sxYzDao.query(queryInfo);
		Map<String, Object> map = repositories.yzService.calSXCSYZ(queryInfo.getPageInfo(), sxResult, null, null);
		PageResult<SxCsYz> result = (PageResult<SxCsYz>) map.get("result");
		SX[] sxlist = SX.seq();
		if (result != null && result.getTotal() > 0) {
			List<Future<Exception>> fList = new ArrayList<Future<Exception>>();
			for (int i = result.getList().size() - 1; i > -1; i--) {
				SxCsYz data = result.getList().get(i);
				SX maxSX = null;
				SX minSX = null;
				Integer max = 0;
				Integer min = Integer.MAX_VALUE;

				SxYz sxyz = repositories.sxyzRepository.findByYearAndPhase(data.getYear(), data.getPhase());
				for (SX sx : sxlist) {
					Method m = ReflectionUtils.findMethod(SxCsYz.class, "get" + sx.name());
					Integer value = (Integer) m.invoke(data);
					if (value < min) {
						min = value;
						minSX = sx;
					}
					if (value > max) {
						max = value;
						maxSX = sx;
					}
				}
				if (minSX != null) {
					Method m = ReflectionUtils.findMethod(SxYz.class, "get" + minSX.name());
					data.setMinYz((Integer) m.invoke(sxyz));
				}
				if (maxSX != null) {
					Method m = ReflectionUtils.findMethod(SxYz.class, "get" + maxSX.name());
					data.setMaxYz((Integer) m.invoke(sxyz));
				}
				data.setMin(min);

				QueryInfo<SxYz> subQueryInfo = new QueryInfo<SxYz>();
				SxYz condition = new SxYz();
				condition.setYear(data.getYear());
				condition.setPhase(data.getPhase());
				subQueryInfo.setObject(condition);
				subQueryInfo.setToReverse(queryInfo.isToReverse());
				subQueryInfo.setPageInfo(queryInfo.getPageInfo());
				fList.add(repositories.yzService.calSXCSYZForMax(subQueryInfo, data));
			}
			result.getList().add(new SxCsYz());
			repositories.yzService.sleep(fList, 500);
		}

		return new BaseResult(result);
	}

	@RequestMapping("/listMWCSYZ")
	public BaseResult listMWCSYZ(@RequestBody QueryInfo<MwYz> queryInfo) throws Exception {
		PageResult<MwYz> sxResult = repositories.mwYzDao.query(queryInfo);
		Map<String, Object> map = repositories.yzService.calMWCSYZ(queryInfo.getPageInfo(), sxResult, null, null);
		PageResult<MwCsYz> result = (PageResult<MwCsYz>) map.get("result");
		if (result != null && result.getTotal() > 0) {
			result.getList().add(new MwCsYz());
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listAllJ0")
	public BaseResult listAllJ0(@RequestBody QueryInfo<J0Yz> queryInfo) throws Exception {
		PageResult<J0Yz> result = repositories.yzService.getJ0List(queryInfo);
		if (result != null && result.getTotal() > 0) {
			result.getList().add(new J0Yz());
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listAllD1")
	public BaseResult listAllD1(@RequestBody QueryInfo<SxYz> queryInfo, String mode) throws Exception {
		PageResult<D1Yz> result = repositories.yzService.getD1List(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				result.getList().add(new D1Yz());
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/countSXJZ")
	public BaseResult countSXJZ(@RequestBody QueryInfo<SxYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.sxYzDao, SxYz.class);
	}

	@RequestMapping("/countDSJZ")
	public BaseResult countDSJZ(@RequestBody QueryInfo<DsYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.dsYzDao, DsYz.class);
	}

	@RequestMapping("/countSWJZ")
	public BaseResult countSWJZ(@RequestBody QueryInfo<SwYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.swYzDao, SwYz.class);
	}

	@RequestMapping("/countMWJZ")
	public BaseResult countMWJZ(@RequestBody QueryInfo<MwYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.mwYzDao, MwYz.class);
	}

	@RequestMapping("/countLHJZ")
	public BaseResult countLHJZ(@RequestBody QueryInfo<LhYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.lhYzDao, LhYz.class);
	}

	@RequestMapping("/countBS9QJZ")
	public BaseResult countBS9QJZ(@RequestBody QueryInfo<Bs9qYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.bs9qYzDao, Bs9qYz.class);
	}

	@RequestMapping("/countWXDSJZ")
	public BaseResult countWXDSJZ(@RequestBody QueryInfo<WxdsYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.wxdsYzDao, WxdsYz.class);
	}

	@RequestMapping("/countBSJZ")
	public BaseResult countBSJZ(@RequestBody QueryInfo<BsYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.bsYzDao, BsYz.class);
	}

	@RequestMapping("/countZSJZ")
	public BaseResult countZSJZ(@RequestBody QueryInfo<ZsYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.zsYzDao, ZsYz.class);
	}

	@RequestMapping("/countWXJZ")
	public BaseResult countWXJZ(@RequestBody QueryInfo<WxYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.wxYzDao, WxYz.class);
	}

	@RequestMapping("/countQQJZ")
	public BaseResult countQQJZ(@RequestBody QueryInfo<QqYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.qqYzDao, QqYz.class);
	}

	@RequestMapping("/countQIWJZ")
	public BaseResult countQIWJZ(@RequestBody QueryInfo<QiwYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.qiwYzDao, QiwYz.class);
	}

	@RequestMapping("/countTwelveJZ")
	public BaseResult countTwelveJZ(@RequestBody QueryInfo<TwelveYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.twelveYzDao, TwelveYz.class);
	}

	@RequestMapping("/countSLQJZ")
	public BaseResult countSLQJZ(@RequestBody QueryInfo<SlqYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.slqYzDao, SlqYz.class);
	}

	@RequestMapping("/countPDJZ")
	public BaseResult countPDJZ(@RequestBody QueryInfo<PdYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.pdYzDao, PdYz.class);
	}

	@RequestMapping("/countTM12FDJZ")
	public BaseResult countTM12FDJZ(@RequestBody QueryInfo<Tm12FdYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.tm12fdYzDao, Tm12FdYz.class);
	}

	@RequestMapping("/countSQJZ")
	public BaseResult countSQJZ(@RequestBody QueryInfo<SqYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.sqYzDao, SqYz.class);
	}

	@RequestMapping("/countTMJZ")
	public BaseResult countTMJZ(@RequestBody QueryInfo<TmYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.tmYzDao, TmYz.class);
	}

	@RequestMapping("/countSXZFJZ")
	public BaseResult countSXZFJZ(@RequestBody QueryInfo<SxZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.sxZfYzDao, SxZfYz.class);
	}

	@RequestMapping("/countSXZF2JZ")
	public BaseResult countSXZF2JZ(@RequestBody QueryInfo<SxZfYz2> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.sxZfYz2Dao, SxZfYz2.class);
	}

	@RequestMapping("/countDSZFJZ")
	public BaseResult countDSZFJZ(@RequestBody QueryInfo<DsZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.dszfYzDao, DsZfYz.class);
	}

	@RequestMapping("/countSWZFJZ")
	public BaseResult countSWZFJZ(@RequestBody QueryInfo<SwZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.swZfYzDao, SwZfYz.class);
	}

	@RequestMapping("/countMWZFJZ")
	public BaseResult countMWZFJZ(@RequestBody QueryInfo<MwZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.mwZfYzDao, MwZfYz.class);
	}

	@RequestMapping("/countLHZFJZ")
	public BaseResult countLHZFJZ(@RequestBody QueryInfo<LhZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.lhZfYzDao, LhZfYz.class);
	}

	@RequestMapping("/countBS9QZFJZ")
	public BaseResult countBS9QZFJZ(@RequestBody QueryInfo<Bs9qZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.bs9qZfYzDao, Bs9qZfYz.class);
	}

	@RequestMapping("/countWXDSZFJZ")
	public BaseResult countWXDSZFJZ(@RequestBody QueryInfo<WxdsZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.wxdsZfYzDao, WxdsZfYz.class);
	}

	@RequestMapping("/countBSZFJZ")
	public BaseResult countBSZFJZ(@RequestBody QueryInfo<BsZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.bszfYzDao, BsZfYz.class);
	}

	@RequestMapping("/countZSZFJZ")
	public BaseResult countZSZFJZ(@RequestBody QueryInfo<ZsZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.zszfYzDao, ZsZfYz.class);
	}

	@RequestMapping("/countWXZFJZ")
	public BaseResult countWXZFJZ(@RequestBody QueryInfo<WxZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.wxzfYzDao, WxZfYz.class);
	}

	@RequestMapping("/countQQZFJZ")
	public BaseResult countQQZFJZ(@RequestBody QueryInfo<QqZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.qqZfYzDao, QqZfYz.class);
	}

	@RequestMapping("/countQIWZFJZ")
	public BaseResult countQIWZFJZ(@RequestBody QueryInfo<QiwZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.qiwzfYzDao, QiwZfYz.class);
	}

	@RequestMapping("/countTwelveZFJZ")
	public BaseResult countTwelveZFJZ(@RequestBody QueryInfo<TwelveZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.twelvezfYzDao, TwelveZfYz.class);
	}

	@RequestMapping("/countSLQZFJZ")
	public BaseResult countSLQZFJZ(@RequestBody QueryInfo<SlqZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.slqzfYzDao, SlqZfYz.class);
	}

	@RequestMapping("/countPDZFJZ")
	public BaseResult countPDZFJZ(@RequestBody QueryInfo<PdZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.pdzfYzDao, PdZfYz.class);
	}

	@RequestMapping("/countTM12FDZFJZ")
	public BaseResult countTM12FDZFJZ(@RequestBody QueryInfo<Tm12FdZfYz> queryInfo) throws Exception {
		return countJZ(queryInfo, repositories.tm12fdzfYzDao, Tm12FdZfYz.class);
	}

	private <T extends BaseYz> BaseResult countJZ(QueryInfo<T> queryInfo, BaseYzDao<T> dao, Class<T> clazz)
			throws Exception {
		PageResult<T> result = dao.query(queryInfo);
		return countJZ(result, clazz);
	}

	private <T> BaseResult countJZ(PageResult<T> result, Class<T> clazz) throws Exception {
		if (result != null && result.getTotal() > 0) {
			T totalLine = clazz.newInstance();
			Method m1 = ReflectionUtils.findMethod(clazz, "getLastYz");
			Method m2 = ReflectionUtils.findMethod(clazz, "getLastYzList");
			for (T data : result.getList()) {
				Integer lastYz = (Integer) m1.invoke(data);
				if (lastYz != null && lastYz > -1) {
					int[] lastYzList = (int[]) m2.invoke(totalLine);
					if (lastYz < 31) {
						lastYzList[lastYz]++;
					} else if (lastYz > 30 && lastYz < 41) {
						lastYzList[31]++;
					} else if (lastYz > 40 && lastYz < 51) {
						lastYzList[32]++;
					} else {
						lastYzList[33]++;
					}
				}
			}
			result.getList().add(totalLine);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/countSXZF10Loop")
	public BaseResult countSXZF10Loop(@RequestBody QueryInfo<SxZfYz> queryInfo) throws Exception {
		PageResult<SxZfYz> result = repositories.sxZfYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			for (int i = result.getList().size() - 1; i >= 0; i--) {
				SxZfYz data = result.getList().get(i);
				int k = i - 10;
				for (int j = 0; j < 11; j++) {
					if (k > -1 && k < result.getList().size()) {
						data.getPosList()[j] = result.getList().get(k).getCurrentPos();
					}
					k++;
				}
				if (i < result.getList().size() - 2) {
					data.getPosList()[11] = result.getList().get(i + 1).getCurrentPos();
				}
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/countSX10Loop")
	public BaseResult countSX10Loop(@RequestBody QueryInfo<SxYz> queryInfo) throws Exception {
		PageResult<SxYz> result = repositories.sxYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			for (int i = result.getList().size() - 1; i >= 0; i--) {
				SxYz data = result.getList().get(i);
				int k = i - 12;
				for (int j = 0; j < 13; j++) {
					if (k > -1 && k < result.getList().size()) {
						data.getLastYzList()[j] = result.getList().get(k).getLastYz();
					}
					k++;
				}
				if (i < result.getList().size() - 1) {
					data.getLastYzList()[13] = result.getList().get(i + 1).getLastYz();
				}
			}
		}
		return new BaseResult(result);
	}

	private BaseResult count10Loop(QueryInfo<KaiJiang> queryInfo, Class<?> clazz) throws Exception {
		PageResult<KaiJiang> result = repositories.kaiJiangDao.queryForPM(queryInfo);
		if (result != null && result.getTotal() > 0) {
			String[] fds = (String[]) ReflectionUtils.findField(clazz, "FDS").get(null);
			for (int i = result.getList().size() - 1; i >= 0; i--) {
				KaiJiang data = result.getList().get(i);
				int k = i - 10;
				for (int j = 0; j < 11; j++) {
					if (k > -1 && k < result.getList().size()) {
						int num = result.getList().get(k).getSpecialNum();
						for (String fd : fds) {
							List<Integer> nums = (List<Integer>) ReflectionUtils.findField(clazz, fd.toUpperCase()).get(null);
							if (nums.contains(num)) {
								data.getLastYzList()[j] = fd;
								break;
							}
						}
					}
					k++;
				}
				if (i < result.getList().size() - 1) {
					int num = result.getList().get(k).getSpecialNum();
					for (String fd : fds) {
						List<Integer> nums = (List<Integer>) ReflectionUtils.findField(clazz, fd.toUpperCase()).get(null);
						if (nums.contains(num)) {
							data.getLastYzList()[11] = fd;
							break;
						}
					}
				}
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/countSW10Loop")
	public BaseResult countSW10Loop(@RequestBody QueryInfo<KaiJiang> queryInfo) throws Exception {
		return count10Loop(queryInfo, SwNums.class);
	}

	@RequestMapping("/countWX10Loop")
	public BaseResult countWX10Loop(@RequestBody QueryInfo<KaiJiang> queryInfo) throws Exception {
		return count10Loop(queryInfo, WxNums.class);
	}

	@RequestMapping("/listPM")
	public BaseResult listPM(@RequestBody QueryInfo<KaiJiang> queryInfo) throws Exception {
		return new BaseResult(getPMList(queryInfo, true));
	}

	private PageResult<PmDTO> getPMList(QueryInfo<KaiJiang> queryInfo, boolean withTotal) throws Exception {
		PageResult<KaiJiang> pResult = repositories.kaiJiangDao.queryForPM(queryInfo);
		PageResult<PmDTO> result = new PageResult<PmDTO>();
		result.setPage(pResult.getPage());
		result.setTotal(pResult.getTotal());
		result.setTotalPage(pResult.getTotalPage());
		if (pResult != null && pResult.getList() != null && !pResult.getList().isEmpty()) {
			List<PmDTO> list = new ArrayList<PmDTO>();
			Map<String, PmDTO> map = new HashMap<String, PmDTO>();
			PmDTO totalDTO = new PmDTO();
			for (int i = pResult.getList().size() - 1; i >= 0; i--) {
				KaiJiang specialData = pResult.getList().get(i);
				for (int j = i; j >= 0; j--) {
					KaiJiang pmData = pResult.getList().get(j);
					PmDTO dto = map.get(pmData.getDate());
					if (dto == null) {
						dto = new PmDTO();
						dto.setDate(pmData.getDate());
						dto.setYear(pmData.getYear());
						dto.setPhase(pmData.getPhase());
						SpecialNum specialNum = new SpecialNum();
						specialNum.setNum(pmData.getSpecialNum());
						dto.setSpecialNum(specialNum);
						map.put(pmData.getDate(), dto);
					}
					boolean in = false;
					boolean in2 = false;
					for (int k = 1; k < 7; k++) {
						Method dtoSm = ReflectionUtils.findMethod(PmDTO.class, "setNum" + k, PmNum.class);
						Method gm = ReflectionUtils.findMethod(KaiJiang.class, "getNum" + k);
						Integer num = (Integer) gm.invoke(pmData);
						if (!in) {
							in = num == specialData.getSpecialNum();
							if (in) {
								in2 = in;
							}
						}
						Method dtoGm = ReflectionUtils.findMethod(PmDTO.class, "getNum" + k);
						PmNum pmNum = (PmNum) dtoGm.invoke(dto);
						if (pmNum == null) {
							pmNum = new PmNum(num, in);
						}
						if (in) {
							pmNum.setMatchedForSpecialNum(true);
							Method tpGm = ReflectionUtils.findMethod(PmDTO.class, "getTp" + k);
							Method tpSm = ReflectionUtils.findMethod(PmDTO.class, "setTp" + k, BigDecimal.class);
							BigDecimal total = (BigDecimal) tpGm.invoke(totalDTO);
							tpSm.invoke(totalDTO, total.add(new BigDecimal(1)));
							in = false;
						}
						dtoSm.invoke(dto, pmNum);
					}
					in = j < i && pmData.getSpecialNum() == specialData.getSpecialNum();
					if (in) {
						Method tpGm = ReflectionUtils.findMethod(PmDTO.class, "getTpS");
						Method tpSm = ReflectionUtils.findMethod(PmDTO.class, "setTpS", BigDecimal.class);
						BigDecimal total = (BigDecimal) tpGm.invoke(totalDTO);
						tpSm.invoke(totalDTO, total.add(new BigDecimal(1)));
						PmDTO dto2 = map.get(pmData.getDate());
						dto2.getSpecialNum().setMatchedForSpecialNum(true);
						in2 = true;
					}
					dto = map.get(specialData.getDate());
					if (in2) {
						dto.getSpecialNum().setDelta(i - j);
						dto.setLastYz(dto.getSpecialNum().getDelta());
						break;
					} else {
						dto.setLastYz(-1);
					}
				}
				list.add(map.get(specialData.getDate()));
			}
			Collections.reverse(list);
			if (withTotal) {
				for (int k = 1; k < 7; k++) {
					Method tpGm = ReflectionUtils.findMethod(PmDTO.class, "getTp" + k);
					Method tpSm = ReflectionUtils.findMethod(PmDTO.class, "setTp" + k, BigDecimal.class);
					BigDecimal total = (BigDecimal) tpGm.invoke(totalDTO);
					tpSm.invoke(totalDTO, total.divide(new BigDecimal(result.getPage().getPageSize()), 4, RoundingMode.HALF_UP)
							.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP));
				}
				list.add(totalDTO);
			}
			result.setList(list);
		}
		return result;
	}

	@RequestMapping("/countPMJZ")
	public BaseResult countPMJZ(@RequestBody QueryInfo<KaiJiang> queryInfo) throws Exception {
		return countJZ(getPMList(queryInfo, false), PmDTO.class);
	}

	@RequestMapping("/listQWYZ")
	public BaseResult listQWYZ(@RequestBody QueryInfo<QwYz> queryInfo) throws Exception {
		PageResult<QwYz> result = repositories.qwYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			QwYz totalLine = new QwYz();
			for (int i = result.getList().size() - 1; i >= 0; i--) {
				QwYz data = result.getList().get(i);
				for (int j = 0; j < 10; j++) {
					Method gm = ReflectionUtils.findMethod(QwYz.class, "getW" + j);
					Integer value = (Integer) gm.invoke(data);
					if (value != null && value == 0) {
						Method sm = ReflectionUtils.findMethod(QwYz.class, "setW" + j, Integer.class);
						Integer count = (Integer) gm.invoke(totalLine);
						if (count == null) {
							count = 0;
						}
						sm.invoke(totalLine, count + 1);
					}

					int k = i;
					int maxTimes = 0;
					value = null;
					boolean forback = false;
					do {
						QwYz countMaxTimesData = result.getList().get(k);
						value = (Integer) gm.invoke(countMaxTimesData);
						forback = value != null && value == 0;
						if (forback) {
							k--;
							maxTimes++;
						}
					} while (forback && k >= 0);
					if (maxTimes > data.getMaxTimes()) {
						data.setMaxTimes(maxTimes);
					}
				}
			}
			result.getList().add(totalLine);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listQWZH")
	public BaseResult listQWZH(@RequestBody QueryInfo<QwYz> queryInfo) throws Exception {
		PageResult<QwYz> pResult = repositories.qwYzDao.query(queryInfo);
		if (pResult != null && pResult.getList() != null && !pResult.getList().isEmpty()) {
			List<QwYz> list = new ArrayList<QwYz>();
			int[][] pairs = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 0 }, { 2, 3 }, { 3, 0 }, { 3, 4 }, { 4, 0 },
					{ 4, 5 }, { 5, 0 }, { 5, 6 }, { 6, 0 }, { 6, 7 }, { 7, 0 }, { 7, 8 }, { 8, 0 }, { 8, 9 }, { 9, 0 },
					{ 9, 10 } };
			QwYz totalLine = new QwYz();
			for (int k = 0; k < pairs.length; k++) {
				int[] pair = pairs[k];
				QwYz data = new QwYz();
				data.setYear(1);
				for (int j = 0; j < 10; j++) {
					for (int i = pResult.getList().size() - 1; i > 0; i--) {
						QwYz current = pResult.getList().get(i);
						QwYz last = pResult.getList().get(i - 1);
						Method gm = ReflectionUtils.findMethod(QwYz.class, "getW" + j);
						Integer[] currentPair = { (Integer) gm.invoke(last), (Integer) gm.invoke(current) };
						if (currentPair[0] != null && currentPair[1] != null && currentPair[0] == pair[0]
								&& currentPair[1] == pair[1]) {
							Method sm = ReflectionUtils.findMethod(QwYz.class, "setW" + j, Integer.class);
							Integer value = (Integer) gm.invoke(data);
							if (value == null) {
								value = 0;
							}
							totalLine.setPhase(totalLine.getPhase() + 1);
							Integer wTotal = (Integer) gm.invoke(totalLine);
							if (wTotal == null) {
								wTotal = 0;
							}
							sm.invoke(totalLine, wTotal + 1);
							sm.invoke(data, value + 1);
						}
					}
				}
				list.add(data);
			}
			list.add(totalLine);
			pResult.setList(list);
		}
		return new BaseResult(pResult);
	}

	@RequestMapping("/listTMYZ")
	public BaseResult listTMYZ(@RequestBody QueryInfo<TmYz> queryInfo) throws Exception {
		PageResult<TmYz> result = repositories.tmYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			result.getList().add(new TmYz());
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listPTYZ")
	public BaseResult listPTYZ(@RequestBody QueryInfo<PtYz> queryInfo) throws Exception {
		return new BaseResult(repositories.ptYzDao.query(queryInfo));
	}

	@RequestMapping("/listLastCJ")
	public BaseResult listLastCJ() throws Exception {
		Integer year = ((List<Integer>) years().getData()).get(0);
		Integer phase = ((List<Integer>) phases(year).getData()).get(0);
		QueryInfo<PtYz> queryInfo = new QueryInfo<PtYz>();
		PtYz object = new PtYz();
		object.setYear(year);
		object.setPhase(phase);
		queryInfo.setObject(object);
		PageInfo page = new PageInfo();
		page.setPageNo(1);
		page.setPageSize(1);
		page.setToSort(false);
		queryInfo.setPageInfo(page);
		return listCJYZ(queryInfo);
	}

	@RequestMapping("/listCJYZ")
	public BaseResult listCJYZ(@RequestBody QueryInfo<PtYz> queryInfo) throws Exception {
		PageResult<PtYz> pResult = repositories.ptYzDao.query(queryInfo);
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		if (pResult != null && pResult.getTotal() > 0) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (PtYz yz : pResult.getList()) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("year", yz.getYear());
				data.put("phase", yz.getPhase());
				for (int i = 0; i < 7; i++) {
					data.put("jg" + i, new ArrayList<Integer>());
				}
				List<Integer> hms = new ArrayList<Integer>();
				for (int i = 1; i < 50; i++) {
					Method m = ReflectionUtils.findMethod(PtYz.class, "getHm" + i);
					Integer value = (Integer) m.invoke(yz);
					if (value == 0) {
						hms.add(value);
					}
				}
				for (int i = 1; i < 50; i++) {
					Method m = ReflectionUtils.findMethod(PtYz.class, "getHm" + i);
					Integer value = (Integer) m.invoke(yz);
					switch (value) {
					case 0: {
						putJg(data, 0, i);
						if (i + 1 < 50 && !hms.contains(i + 1)) {
							putAdd1orMins1(data, "add1", i + 1);
						}
						if (i - 1 > 0 && !hms.contains(i - 1)) {
							putAdd1orMins1(data, "min1", i - 1);
						}
					}
						break;
					case 1:
						putJg(data, 1, i);
						break;
					case 2:
						putJg(data, 2, i);
						break;
					case 3:
						putJg(data, 3, i);
						break;
					case 4:
						putJg(data, 4, i);
						break;
					case 5:
						putJg(data, 5, i);
						break;
					case 6:
						putJg(data, 6, i);
						break;
					default:
						putJg(data, 7, i);
						break;
					}
				}
				list.add(data);
			}
			Collections.reverse(list);

			Map<String, Object> lastData = null;
			for (Map<String, Object> data : list) {
				if (lastData != null) {
					List<Integer> comparor = (List<Integer>) lastData.get("jg0");
					String[] arr = new String[] { "min1", "add1", "jg0", "jg1", "jg2", "jg3", "jg4", "jg5", "jg6", "jg6Plus" };
					for (String key : arr) {
						List<Integer> comparee = (List<Integer>) data.get(key);
						List<Integer> darks = new ArrayList<Integer>();
						for (Integer num : comparee) {
							if (comparor.contains(num)) {
								darks.add(num);
							}
						}
						data.put("s" + key, darks);
					}
				}
				lastData = data;
			}
			Collections.reverse(list);
			result.setList(list);
		}
		return new BaseResult(result);
	}

	private void putJg(Map<String, Object> data, int index, int num) {
		String str = "jg" + (index < 7 ? index : "6Plus");
		List<Integer> hms = (List<Integer>) data.get(str);
		if (hms == null) {
			hms = new ArrayList<Integer>();
			data.put(str, hms);
		}
		hms.add(num);
	}

	private void putAdd1orMins1(Map<String, Object> data, String field, int num) {
		List<Integer> hms = (List<Integer>) data.get(field);
		if (hms == null) {
			hms = new ArrayList<Integer>();
			data.put(field, hms);
		}
		hms.add(num);
	}

	@RequestMapping("/listTMFDYZ")
	public BaseResult listTMFDYZ(@RequestBody QueryInfo<TmFdYz> queryInfo, String mode) throws Exception {
		PageResult<TmFdYz> result = repositories.tmfdYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			TmFdYz queryObj = queryInfo.getObject();
			TmYz tmResult = repositories.tmyzRepository.findByYearAndPhase(queryObj.getYear(), queryObj.getPhase());
			if (tmResult != null) {
				List<TmYzInfo> fdList = repositories.yzService.getTMFDList(tmResult, false);
				if (result != null && result.getTotal() > 0) {
					TmFdYz data = new TmFdYz();
					data.setList(fdList);
					result.getList().add(data);
				}
			}
		}
		return new BaseResult(result);
	}

	private <T extends Avg> String downloadYZ(String filename, Class<T> clazz, DownloadDTO dto,
			HttpServletResponse response, BaseYzDao<T> dao) throws Exception {
		return downloadYZ(filename, clazz, dto, response, dao, null, null);
	}

	private <T extends Avg> String downloadYZ(String filename, Class<T> clazz, DownloadDTO dto,
			HttpServletResponse response, BaseYzDao<T> dao, String extraFieldTxt, String extraField) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=" + filename + ".csv");
		QueryInfo<T> queryInfo = new QueryInfo<T>();
		T queryObj = clazz.newInstance();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<T> result = dao.query(queryInfo);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 差值, 遗值(上), 和, ");
			if (extraFieldTxt != null) {
				writer.append(extraFieldTxt + ", ");
			}

			writer.append("倒1, 倒2, 倒3, 倒4, 倒5, " + "间0, 间1, 间2, 间3, 间4, 间5, 间6, 间7, 间8, 间9, 间10, "
					+ "间11, 间12, 间13, 间14, 间15, 间16, 间17, 间18, 间19").append("\n");
			for (T data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getDelta() + "").append(", ");
				writer.append(data.getLastYz() + "").append(",");
				writer.append(data.getTotal() + "").append(", ");
				if (extraField != null) {
					writer.append(ReflectionUtils.findMethod(clazz, "get" + extraField).invoke(data).toString()).append(", ");
				}
				for (int i = 0; i < 5; i++) {
					Method m = ReflectionUtils.findMethod(Avg.class, "getTop" + i);
					Integer value = (Integer) m.invoke(data);
					if (value != null) {
						writer.append(value.toString()).append(", ");
					} else {
						writer.append("").append(", ");
					}
				}
				for (int i = 0; i < 20; i++) {
					Method m = ReflectionUtils.findMethod(Avg.class, "getMin" + i);
					Integer value = (Integer) m.invoke(data);
					if (value != null) {
						writer.append(value.toString());
					} else {
						writer.append("");
					}
					if (i < 19) {
						writer.append(", ");
					} else {
						writer.append("\n");
					}
				}
			}

		}
		return null;
	}

	@RequestMapping("/downloadAllTop0YZ")
	public String downloadAllTop0YZ(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=allj0.csv");
		Writer writer = response.getWriter();
		writer.append("年份").append(", ");
		writer.append("期数").append(", ");
		writer.append("生肖").append(", ");
		writer.append("生肖振幅").append(", ");
		writer.append("单双").append(", ");
		writer.append("单双振幅").append(", ");
		writer.append("首位").append(", ");
		writer.append("首位振幅").append(", ");
		writer.append("末位").append(", ");
		writer.append("末位振幅").append(", ");
		writer.append("合数").append(", ");
		writer.append("合数振幅").append(", ");
		writer.append("波色").append(", ");
		writer.append("波色振幅").append(", ");
		writer.append("质数").append(", ");
		writer.append("质数振幅").append(", ");
		writer.append("五行").append(", ");
		writer.append("五行振幅").append(", ");
		writer.append("五行单双").append(", ");
		writer.append("五行单双振幅").append(", ");
		writer.append("配对").append(", ");
		writer.append("配对振幅").append(", ");
		writer.append("分段").append(", ");
		writer.append("分段振幅").append(", ");
		writer.append("七区").append(", ");
		writer.append("七区振幅").append(", ");
		writer.append("七位").append(", ");
		writer.append("七位振幅").append(", ");
		writer.append("十二区").append(", ");
		writer.append("十二区振幅").append(", ");
		writer.append("十六区").append(", ");
		writer.append("十六区振幅").append("\n");
		QueryInfo<J0Yz> queryInfo = new QueryInfo<J0Yz>();
		J0Yz condition = new J0Yz();
		condition.setYear(Integer.valueOf(request.getParameter("year")));
		condition.setPhase(Integer.valueOf(request.getParameter("phase")));
		queryInfo.setObject(condition);
		queryInfo.setPageInfo(new PageInfo(1, Integer.valueOf(request.getParameter("size"))));
		PageResult<J0Yz> result = repositories.yzService.getJ0List(queryInfo);
		if (result != null && result.getTotal() > 0) {
			String[] fds = new String[] { "Sx", "Sxzf", "Ds", "Dszf", "Sw", "Swzf", "Mw", "Mwzf", "Lh", "Lhzf", "Bs", "Bszf",
					"Zs", "Zszf", "Wx", "Wxzf", "Wxds", "Wxdszf", "Pd", "Pdzf", "Fd", "Fdzf", "Qq", "Qqzf", "Qiw", "Qiwzf",
					"Twelve", "Twelvezf", "Slq", "Slqzf" };
			for (J0Yz yz : result.getList()) {
				writer.append(yz.getYear() + "").append(", ");
				writer.append(yz.getPhase() + "").append(", ");
				int i = 0;
				for (String fd : fds) {
					Method m = ReflectionUtils.findMethod(J0Yz.class, "get" + fd + "Nums");
					List<Integer> nums = (List<Integer>) m.invoke(yz);
					writer.append("[");
					if (nums != null && !nums.isEmpty()) {
						int j = 0;
						for (Integer num : nums) {
							writer.append(num + "");
							if (j < nums.size() - 1) {
								writer.append(" ");
							}
						}
						j++;
					}
					if (i < fds.length - 1) {
						writer.append("], ");
					} else {
						writer.append("\n");
					}
					i++;
				}
			}
		}
		return null;
	}

	@RequestMapping("/downloadFXSW")
	public String downloadFXSW(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=fxsw" + request.getParameter("pos") + ".csv");
		Writer writer = response.getWriter();
		writer.append("年份").append(", ");
		writer.append("期数").append(", ");
		writer.append("计数(0/1)").append(", ");
		writer.append("计数").append(", ");
		writer.append("连续计数(0/1)").append(", ");
		writer.append("反转个数").append("\n");
		String[] years = request.getParameter("years").split(",");
		String[] phases = request.getParameter("phases").split(",");
		String[] counts01 = request.getParameter("counts01").split(",");
		String[] countsTotals = request.getParameter("countsTotals").split(",");
		String[] continuosCounts = request.getParameter("continuosCounts").split(",");
		String[] fzNums = request.getParameter("fzNums").split(",");
		for (int i = 0; i < years.length; i++) {
			writer.append(years[i]).append(", ");
			writer.append(phases[i]).append(", ");
			writer.append(counts01[i]).append(", ");
			writer.append(countsTotals[i]).append(", ");
			writer.append(continuosCounts[i]).append(", ");
			writer.append(fzNums[i]).append("\n");
		}
		return null;
	}

	@RequestMapping("/downloadAllD1YZ")
	public String downloadAllD1YZ(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=alld1.csv");
		Writer writer = response.getWriter();
		writer.append("年份").append(", ");
		writer.append("期数").append(", ");
		writer.append("生肖").append(", ");
		writer.append("生肖振幅").append(", ");
		writer.append("单双").append(", ");
		writer.append("单双振幅").append(", ");
		writer.append("首位").append(", ");
		writer.append("首位振幅").append(", ");
		writer.append("末位").append(", ");
		writer.append("末位振幅").append(", ");
		writer.append("合数").append(", ");
		writer.append("合数振幅").append(", ");
		writer.append("波色").append(", ");
		writer.append("波色振幅").append(", ");
		writer.append("质数").append(", ");
		writer.append("质数振幅").append(", ");
		writer.append("五行").append(", ");
		writer.append("五行振幅").append(", ");
		writer.append("五行单双").append(", ");
		writer.append("五行单双振幅").append(", ");
		writer.append("配对").append(", ");
		writer.append("配对振幅").append(", ");
		writer.append("分段").append(", ");
		writer.append("分段振幅").append(", ");
		writer.append("七区").append(", ");
		writer.append("七区振幅").append(", ");
		writer.append("七位").append(", ");
		writer.append("七位振幅").append(", ");
		writer.append("十二区").append(", ");
		writer.append("十二区振幅").append(", ");
		writer.append("十六区").append(", ");
		writer.append("十六区振幅").append("\n");
		QueryInfo<SxYz> queryInfo = new QueryInfo<SxYz>();
		SxYz condition = new SxYz();
		condition.setYear(Integer.valueOf(request.getParameter("year")));
		condition.setPhase(Integer.valueOf(request.getParameter("phase")));
		queryInfo.setObject(condition);
		queryInfo.setPageInfo(new PageInfo(1, Integer.valueOf(request.getParameter("size"))));
		PageResult<D1Yz> result = repositories.yzService.getD1List(queryInfo);
		if (result != null && result.getTotal() > 0) {
			String[] fds = new String[] { "Sx", "Ds", "Sw", "Mw", "Lh", "Bs", "Zs", "Wx", "Wxds", "Pd", "Fd", "Qq", "Qiw",
					"Twelve", "Slq" };
			for (D1Yz yz : result.getList()) {
				writer.append(yz.getYear() + "").append(", ");
				writer.append(yz.getPhase() + "").append(", ");
				int i = 0;
				for (String fd : fds) {
					Method m = ReflectionUtils.findMethod(D1Yz.class, "get" + fd + "d1");
					writer.append(m.invoke(yz).toString()).append(", ");
					m = ReflectionUtils.findMethod(D1Yz.class, "get" + fd + "zfd1");
					writer.append(m.invoke(yz).toString());
					if (i < fds.length - 1) {
						writer.append(", ");
					} else {
						writer.append("\n");
					}
					i++;
				}
			}
		}
		return null;
	}

	@RequestMapping("/downloadAllZFParameters")
	public String downloadAllZFParameters(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=allZfParameters.csv");
		Writer writer = response.getWriter();
		writer.append("年份").append(", ");
		writer.append("期数").append(", ");
		writer.append("特码").append(", ");
		writer.append("生肖振幅-遗值和").append(", ");
		writer.append("生肖振幅-倒1").append(", ");
		writer.append("单双振幅-遗值和").append(", ");
		writer.append("单双振幅-倒1").append(", ");
		writer.append("首位振幅-遗值和").append(", ");
		writer.append("首位振幅-倒1").append(", ");
		writer.append("末位振幅-遗值和").append(", ");
		writer.append("末位振幅-倒1").append(", ");
		writer.append("合数振幅-遗值和").append(", ");
		writer.append("合数振幅-倒1").append(", ");
		writer.append("波色振幅-遗值和").append(", ");
		writer.append("波色振幅-倒3").append(", ");
		writer.append("波色振幅-倒4").append(", ");
		writer.append("波色振幅-倒5").append(", ");
		writer.append("质数振幅-遗值和").append(", ");
		writer.append("质数振幅-倒1").append(", ");
		writer.append("五行振幅-遗值和").append(", ");
		writer.append("五行振幅-倒3").append(", ");
		writer.append("五行振幅-倒4").append(", ");
		writer.append("五行单双振幅-遗值和").append(", ");
		writer.append("五行单双振幅-倒1").append(", ");
		writer.append("配对振幅-遗值和").append(", ");
		writer.append("配对振幅-倒1").append(", ");
		writer.append("分段振幅-遗值和").append(", ");
		writer.append("分段振幅-倒1").append(", ");
		writer.append("七区振幅-遗值和").append(", ");
		writer.append("七区振幅-倒1").append(", ");
		writer.append("七位振幅-遗值和").append(", ");
		writer.append("七位振幅-倒1").append(", ");
		writer.append("十二区振幅-遗值和").append(", ");
		writer.append("十二区振幅-倒1").append(", ");
		writer.append("十六区振幅-遗值和").append(", ");
		writer.append("十六区振幅-倒1").append("\n");
		QueryInfo<SxYz> queryInfo = new QueryInfo<SxYz>();
		SxYz condition = new SxYz();
		condition.setYear(Integer.valueOf(request.getParameter("year")));
		condition.setPhase(Integer.valueOf(request.getParameter("phase")));
		queryInfo.setObject(condition);
		queryInfo.setPageInfo(new PageInfo(1, Integer.valueOf(request.getParameter("size"))));
		PageResult<D1Yz> result = repositories.yzService.getD1List(queryInfo);
		if (result != null && result.getTotal() > 0) {
			for (D1Yz yz : result.getList()) {
				writer.append(yz.getYear() + "").append(", ");
				writer.append(yz.getPhase() + "").append(", ");
				writer.append(yz.getSpecialNum() + "").append(", ");
				writer.append(yz.getSxzfTotal() + "").append(", ");
				writer.append(yz.getSxzfd1() + "").append(", ");
				writer.append(yz.getDszfTotal() + "").append(", ");
				writer.append(yz.getDszfd1() + "").append(", ");
				writer.append(yz.getSwzfTotal() + "").append(", ");
				writer.append(yz.getSwzfd1() + "").append(", ");
				writer.append(yz.getMwzfTotal() + "").append(", ");
				writer.append(yz.getMwzfd1() + "").append(", ");
				writer.append(yz.getLhzfTotal() + "").append(", ");
				writer.append(yz.getLhzfd1() + "").append(", ");
				writer.append(yz.getBszfTotal() + "").append(", ");
				writer.append(yz.getBszfd3() + "").append(", ");
				writer.append(yz.getBszfd4() + "").append(", ");
				writer.append(yz.getBszfd5() + "").append(", ");
				writer.append(yz.getZszfTotal() + "").append(", ");
				writer.append(yz.getZszfd1() + "").append(", ");
				writer.append(yz.getWxzfTotal() + "").append(", ");
				writer.append(yz.getWxzfd3() + "").append(", ");
				writer.append(yz.getWxzfd4() + "").append(", ");
				writer.append(yz.getWxdszfTotal() + "").append(", ");
				writer.append(yz.getWxdszfd1() + "").append(", ");
				writer.append(yz.getPdzfTotal() + "").append(", ");
				writer.append(yz.getPdzfd1() + "").append(", ");
				writer.append(yz.getFdzfTotal() + "").append(", ");
				writer.append(yz.getFdzfd1() + "").append(", ");
				writer.append(yz.getQqzfTotal() + "").append(", ");
				writer.append(yz.getQqzfd1() + "").append(", ");
				writer.append(yz.getQiwzfTotal() + "").append(", ");
				writer.append(yz.getQiwzfd1() + "").append(", ");
				writer.append(yz.getTwelvezfTotal() + "").append(", ");
				writer.append(yz.getTwelvezfd1() + "").append(", ");
				writer.append(yz.getSlqzfTotal() + "").append(", ");
				writer.append(yz.getSlqzfd1() + "").append("\n");
			}
		}
		return null;
	}

	@RequestMapping("/downloadAllYZParameters")
	public String downloadAllYZParameters(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=allYZParameters.csv");
		Writer writer = response.getWriter();
		writer.append("年份").append(", ");
		writer.append("期数").append(", ");
		writer.append("特码").append(", ");
		writer.append("生肖-遗值和").append(", ");
		writer.append("生肖-倒1").append(", ");
		writer.append("生肖冷热-遗值和").append(", ");
		writer.append("生肖冷热-区位").append(", ");
		writer.append("生肖冷热-倒1").append(", ");
		writer.append("单双-遗值和").append(", ");
		writer.append("单双-倒1").append(", ");
		writer.append("首位-遗值和").append(", ");
		writer.append("首位-区位").append(", ");
		writer.append("首位-倒1").append(", ");
		writer.append("末位-遗值和").append(", ");
		writer.append("末位-倒1").append(", ");
		writer.append("合数-遗值和").append(", ");
		writer.append("合数-倒1").append(", ");
		writer.append("波色-遗值和").append(", ");
		writer.append("波色-倒3").append(", ");
		writer.append("波色-倒4").append(", ");
		writer.append("波色-倒5").append(", ");
		writer.append("质数-遗值和").append(", ");
		writer.append("质数-倒1").append(", ");
		writer.append("五行-遗值和").append(", ");
		writer.append("五行-倒3").append(", ");
		writer.append("五行-倒4").append(", ");
		writer.append("五行单双-遗值和").append(", ");
		writer.append("五行单双-倒1").append(", ");
		writer.append("配对-遗值和").append(", ");
		writer.append("配对-倒1").append(", ");
		writer.append("分段-遗值和").append(", ");
		writer.append("分段-倒1").append(", ");
		writer.append("七区-遗值和").append(", ");
		writer.append("七区-区位").append(", ");
		writer.append("七区-倒1").append(", ");
		writer.append("七位-遗值和").append(", ");
		writer.append("七位-区位").append(", ");
		writer.append("七位-倒1").append(", ");
		writer.append("十二区-遗值和").append(", ");
		writer.append("十二区-区位").append(", ");
		writer.append("十二区-倒1").append(", ");
		writer.append("十六区-遗值和").append(", ");
		writer.append("十六区-倒1").append("\n");
		QueryInfo<SxYz> queryInfo = new QueryInfo<SxYz>();
		SxYz condition = new SxYz();
		condition.setYear(Integer.valueOf(request.getParameter("year")));
		condition.setPhase(Integer.valueOf(request.getParameter("phase")));
		queryInfo.setObject(condition);
		queryInfo.setPageInfo(new PageInfo(1, Integer.valueOf(request.getParameter("size"))));
		PageResult<D1Yz> result = repositories.yzService.getD1List(queryInfo);
		if (result != null && result.getTotal() > 0) {
			for (D1Yz yz : result.getList()) {
				writer.append(yz.getYear() + "").append(", ");
				writer.append(yz.getPhase() + "").append(", ");
				writer.append(yz.getSpecialNum() + "").append(", ");
				writer.append(yz.getSxTotal() + "").append(", ");
				writer.append(yz.getSxd1() + "").append(", ");
				writer.append(yz.getSxlrTotal() + "").append(", ");
				writer.append(yz.getSxlrPos() + "").append(", ");
				writer.append(yz.getSxlrd1() + "").append(", ");
				writer.append(yz.getDsTotal() + "").append(", ");
				writer.append(yz.getDsd1() + "").append(", ");
				writer.append(yz.getSwTotal() + "").append(", ");
				writer.append(yz.getSwPos() + "").append(", ");
				writer.append(yz.getSwd1() + "").append(", ");
				writer.append(yz.getMwTotal() + "").append(", ");
				writer.append(yz.getMwd1() + "").append(", ");
				writer.append(yz.getLhTotal() + "").append(", ");
				writer.append(yz.getLhd1() + "").append(", ");
				writer.append(yz.getBsTotal() + "").append(", ");
				writer.append(yz.getBsd3() + "").append(", ");
				writer.append(yz.getBsd4() + "").append(", ");
				writer.append(yz.getBsd5() + "").append(", ");
				writer.append(yz.getZsTotal() + "").append(", ");
				writer.append(yz.getZsd1() + "").append(", ");
				writer.append(yz.getWxTotal() + "").append(", ");
				writer.append(yz.getWxd3() + "").append(", ");
				writer.append(yz.getWxd4() + "").append(", ");
				writer.append(yz.getWxdsTotal() + "").append(", ");
				writer.append(yz.getWxdsd1() + "").append(", ");
				writer.append(yz.getPdTotal() + "").append(", ");
				writer.append(yz.getPdd1() + "").append(", ");
				writer.append(yz.getFdTotal() + "").append(", ");
				writer.append(yz.getFdd1() + "").append(", ");
				writer.append(yz.getQqTotal() + "").append(", ");
				writer.append(yz.getQqPos() + "").append(", ");
				writer.append(yz.getQqd1() + "").append(", ");
				writer.append(yz.getQiwTotal() + "").append(", ");
				writer.append(yz.getQiwPos() + "").append(", ");
				writer.append(yz.getQiwd1() + "").append(", ");
				writer.append(yz.getTwelveTotal() + "").append(", ");
				writer.append(yz.getTwelvePos() + "").append(", ");
				writer.append(yz.getTwelved1() + "").append(", ");
				writer.append(yz.getSlqTotal() + "").append(", ");
				writer.append(yz.getSlqd1() + "").append("\n");
			}
		}
		return null;
	}

	@RequestMapping("/downloadTZXBW")
	public String downloadTZXBW(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=tzxbw.csv");
		Writer writer = response.getWriter();
		writer.append("组合").append(", ");
		writer.append("反转号码").append("\n");
		Integer totalResult = Integer.valueOf(request.getParameter("totalResult"));
		String[] fds = new String[] { "ABCD", "EFGH", "EBCD", "AFCD", "ABGD", "ABCH", "AFGH", "EBGH", "EFCH", "EFGD" };
		if ("3".equals(request.getParameter("compositeSize"))) {
			fds = new String[] { "ABCD", "EFGH", "IJKL", "EBCD", "AFCD", "ABGD", "ABCH", "EJKL", "IFKL", "IJGL", "IJKH",
					"AFGH", "EBGH", "EFCH", "EFGD", "AJKL", "IBKL", "IJCL", "IJKD", "IBCD", "AJCD", "ABKD", "ABCL", "IFGH",
					"EJGH", "EFKH", "EFGL" };
		}
		for (int i = 0; i < totalResult; i++) {
			for (int j = 0; j < fds.length; j++) {
				writer.append(request.getParameter("fd_" + fds[j] + "_name_" + i)).append(", ");
				writer.append(request.getParameter("fd_" + fds[j] + "_value_" + i)).append("\n");
			}
			writer.append("合计").append(", ");
			writer.append(request.getParameter("fdlist_" + i)).append("\n");
		}
		writer.append("总计").append(", ");
		writer.append(request.getParameter("totalList")).append("\n");
		return null;
	}

	@RequestMapping("/downloadTZSZS")
	public String downloadTZSZS(Map<String, Object> dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=tzszs.csv");
		Writer writer = response.getWriter();
		Integer total = (Integer) dto.get("total");
		for (int i = 0; i < total; i++) {
			writer.append(dto.get("hms" + i).toString()).append("\n");
		}
		return null;
	}

	@RequestMapping("/downloadTZDC")
	public String downloadTZDC(DownloadPrepareTZ dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=tzdc.csv");
		Writer writer = response.getWriter();
		for (int i = 1; i < 12; i++) {
			// Method m1 = ReflectionUtils.findMethod(DownloadPrepareTZ.class,
			// "getCategories" + i);
			Method m2 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getGsPlusHms" + i);
			Method m3 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getGsMinusHms" + i);
			Method m4 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getGsPlusNonHms" + i);
			Method m5 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getGsMinusNonHms" + i);
			// writer.append((String) m1.invoke(dto)).append("\n");
			writer.append("公式+").append("\n");
			writer.append((String) m2.invoke(dto)).append("\n");
			writer.append("公式-").append("\n");
			writer.append((String) m3.invoke(dto)).append("\n");
			writer.append("反转号码").append("\n");
			writer.append("公式+").append("\n");
			writer.append((String) m4.invoke(dto)).append("\n");
			writer.append("公式-").append("\n");
			writer.append((String) m5.invoke(dto)).append("\n");
			writer.append("\n");
		}
		writer.append("汇总号码").append("\n");
		writer.append(dto.getAllHms()).append("\n");
		writer.append("汇总反转号码").append("\n");
		writer.append(dto.getAllNonHms()).append("\n");
		return null;
	}

	@RequestMapping("/downloadSXYZ")
	public String downloadSXYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("sxyz", SxYz.class, dto, response, repositories.sxYzDao);
	}

	@RequestMapping("/downloadDSYZ")
	public String downloadDSYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("dsyz", DsYz.class, dto, response, repositories.dsYzDao);
	}

	@RequestMapping("/downloadBSYZ")
	public String downloadBSYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("bsyz", BsYz.class, dto, response, repositories.bsYzDao);
	}

	@RequestMapping("/downloadZSYZ")
	public String downloadZSYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zsyz", ZsYz.class, dto, response, repositories.zsYzDao);
	}

	@RequestMapping("/downloadSWYZ")
	public String downloadSWYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("swyz", SwYz.class, dto, response, repositories.swYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadMWYZ")
	public String downloadMWYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("mwyz", MwYz.class, dto, response, repositories.mwYzDao);
	}

	@RequestMapping("/downloadLHYZ")
	public String downloadLHYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("lhyz", LhYz.class, dto, response, repositories.lhYzDao);
	}

	@RequestMapping("/downloadBS9QYZ")
	public String downloadBS9QYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("bs9qyz", Bs9qYz.class, dto, response, repositories.bs9qYzDao);
	}

	@RequestMapping("/downloadWXDSYZ")
	public String downloadWXDSYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("wxdsyz", WxdsYz.class, dto, response, repositories.wxdsYzDao);
	}

	@RequestMapping("/downloadWXYZ")
	public String downloadWXYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("wxyz", WxYz.class, dto, response, repositories.wxYzDao);
	}

	@RequestMapping("/downloadSLQYZ")
	public String downloadSLQYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("slqyz", SlqYz.class, dto, response, repositories.slqYzDao);
	}

	@RequestMapping("/downloadPDYZ")
	public String downloadPDYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("pdyz", PdYz.class, dto, response, repositories.pdYzDao);
	}

	@RequestMapping("/downloadTM12FDYZ")
	public String downloadTM12FDYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("tm12fdyz", Tm12FdYz.class, dto, response, repositories.tm12fdYzDao);
	}

	@RequestMapping("/downloadSXLRYZ")
	public String downloadSXLRYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("sxlryz", SxLrYz.class, dto, response, repositories.sxlrYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadMWLRYZ")
	public String downloadMWLRYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("mwlryz", MwLrYz.class, dto, response, repositories.mwlrYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadDSLRYZ")
	public String downloadDSLRYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("dslryz", DsLrYz.class, dto, response, repositories.dslrYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadLHLRYZ")
	public String downloadLHLRYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("lhlryz", LhLrYz.class, dto, response, repositories.lhlrYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadBS9QLRYZ")
	public String downloadBS9QLRYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("bs9qlryz", Bs9qLrYz.class, dto, response, repositories.bs9qlrYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadWXDSLRYZ")
	public String downloadWXDSLRYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("wxdslryz", WxdsLrYz.class, dto, response, repositories.wxdslrYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadZSLRYZ")
	public String downloadZSLRYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zslryz", ZsLrYz.class, dto, response, repositories.zslrYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadTwelveLRYZ")
	public String downloadTwelveLRYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("twelvelryz", TwelveLrYz.class, dto, response, repositories.twelvelrYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadSLQLRYZ")
	public String downloadSLQLRYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("slqlryz", SlqLrYz.class, dto, response, repositories.slqlrYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadPDLRYZ")
	public String downloadPDLRYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("pdlryz", PdLrYz.class, dto, response, repositories.pdlrYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadTM12FDLRYZ")
	public String downloadTM12FDLRYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("tm12fdlryz", Tm12FdLrYz.class, dto, response, repositories.tm12fdlrYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadQQYZ")
	public String downloadQQYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("qqyz", QqYz.class, dto, response, repositories.qqYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadQIWYZ")
	public String downloadQIWYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("qiwyz", QiwYz.class, dto, response, repositories.qiwYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadTwelveYZ")
	public String downloadTwelveYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("twelveyz", TwelveYz.class, dto, response, repositories.twelveYzDao, "位置", "Pos");
	}

	@RequestMapping("/downloadZX1YZ")
	public String downloadZX1YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx1yz", Zx1Yz.class, dto, response, repositories.zx1yzDao);
	}

	@RequestMapping("/downloadZX1ZF")
	public String downloadZX1ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx1zf", Zx1ZfYz.class, dto, response, repositories.zx1zfyzDao);
	}

	@RequestMapping("/downloadZX2YZ")
	public String downloadZX2YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx2yz", Zx2Yz.class, dto, response, repositories.zx2yzDao);
	}

	@RequestMapping("/downloadZX2ZF")
	public String downloadZX2ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx2zf", Zx2ZfYz.class, dto, response, repositories.zx2zfyzDao);
	}

	@RequestMapping("/downloadZX3YZ")
	public String downloadZX3YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx3yz", Zx3Yz.class, dto, response, repositories.zx3yzDao);
	}

	@RequestMapping("/downloadZX3ZF")
	public String downloadZX3ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx3zf", Zx3ZfYz.class, dto, response, repositories.zx3zfyzDao);
	}

	@RequestMapping("/downloadZX4YZ")
	public String downloadZX4YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx4yz", Zx4Yz.class, dto, response, repositories.zx4yzDao);
	}

	@RequestMapping("/downloadZX4ZF")
	public String downloadZX4ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx4zf", Zx4ZfYz.class, dto, response, repositories.zx4zfyzDao);
	}

	@RequestMapping("/downloadZX5YZ")
	public String downloadZX5YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx5yz", Zx5Yz.class, dto, response, repositories.zx5yzDao);
	}

	@RequestMapping("/downloadZX5ZF")
	public String downloadZX5ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx5zf", Zx5ZfYz.class, dto, response, repositories.zx5zfyzDao);
	}

	@RequestMapping("/downloadZX6YZ")
	public String downloadZX6YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx6yz", Zx6Yz.class, dto, response, repositories.zx6yzDao);
	}

	@RequestMapping("/downloadZX6ZF")
	public String downloadZX6ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx6zf", Zx6ZfYz.class, dto, response, repositories.zx6zfyzDao);
	}

	@RequestMapping("/downloadZX7YZ")
	public String downloadZX7YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx7yz", Zx7Yz.class, dto, response, repositories.zx7yzDao);
	}

	@RequestMapping("/downloadZX7ZF")
	public String downloadZX7ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx7zf", Zx7ZfYz.class, dto, response, repositories.zx7zfyzDao);
	}

	@RequestMapping("/downloadZX8YZ")
	public String downloadZX8YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx8yz", Zx8Yz.class, dto, response, repositories.zx8yzDao);
	}

	@RequestMapping("/downloadZX8ZF")
	public String downloadZX8ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx8zf", Zx8ZfYz.class, dto, response, repositories.zx8zfyzDao);
	}

	@RequestMapping("/downloadZX9YZ")
	public String downloadZX9YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx9yz", Zx9Yz.class, dto, response, repositories.zx9yzDao);
	}

	@RequestMapping("/downloadZX9ZF")
	public String downloadZX9ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx9zf", Zx9ZfYz.class, dto, response, repositories.zx9zfyzDao);
	}

	@RequestMapping("/downloadZX10YZ")
	public String downloadZX10YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx10yz", Zx10Yz.class, dto, response, repositories.zx10yzDao);
	}

	@RequestMapping("/downloadZX10ZF")
	public String downloadZX10ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx10zf", Zx10ZfYz.class, dto, response, repositories.zx10zfyzDao);
	}

	@RequestMapping("/downloadZX11YZ")
	public String downloadZX11YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx11yz", Zx11Yz.class, dto, response, repositories.zx11yzDao);
	}

	@RequestMapping("/downloadZX11ZF")
	public String downloadZX11ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx11zf", Zx11ZfYz.class, dto, response, repositories.zx11zfyzDao);
	}

	@RequestMapping("/downloadZX12YZ")
	public String downloadZX12YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx12yz", Zx12Yz.class, dto, response, repositories.zx12yzDao);
	}

	@RequestMapping("/downloadZX12ZF")
	public String downloadZX12ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx12zf", Zx12ZfYz.class, dto, response, repositories.zx12zfyzDao);
	}

	@RequestMapping("/downloadZX13YZ")
	public String downloadZX13YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx13yz", Zx13Yz.class, dto, response, repositories.zx13yzDao);
	}

	@RequestMapping("/downloadZX13ZF")
	public String downloadZX13ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx13zf", Zx13ZfYz.class, dto, response, repositories.zx13zfyzDao);
	}

	@RequestMapping("/downloadZX14YZ")
	public String downloadZX14YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx14yz", Zx14Yz.class, dto, response, repositories.zx14yzDao);
	}

	@RequestMapping("/downloadZX14ZF")
	public String downloadZX14ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx14zf", Zx14ZfYz.class, dto, response, repositories.zx14zfyzDao);
	}

	@RequestMapping("/downloadZX15YZ")
	public String downloadZX15YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx15yz", Zx15Yz.class, dto, response, repositories.zx15yzDao);
	}

	@RequestMapping("/downloadZX15ZF")
	public String downloadZX15ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx15zf", Zx15ZfYz.class, dto, response, repositories.zx15zfyzDao);
	}

	@RequestMapping("/downloadZX16YZ")
	public String downloadZX16YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx16yz", Zx16Yz.class, dto, response, repositories.zx16yzDao);
	}

	@RequestMapping("/downloadZX16ZF")
	public String downloadZX16ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx16zf", Zx16ZfYz.class, dto, response, repositories.zx16zfyzDao);
	}

	@RequestMapping("/downloadZX17YZ")
	public String downloadZX17YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx17yz", Zx17Yz.class, dto, response, repositories.zx17yzDao);
	}

	@RequestMapping("/downloadZX17ZF")
	public String downloadZX17ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx17zf", Zx17ZfYz.class, dto, response, repositories.zx17zfyzDao);
	}

	@RequestMapping("/downloadZX18YZ")
	public String downloadZX18YZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx18yz", Zx18Yz.class, dto, response, repositories.zx18yzDao);
	}

	@RequestMapping("/downloadZX18ZF")
	public String downloadZX18ZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zx18zf", Zx18ZfYz.class, dto, response, repositories.zx18zfyzDao);
	}

	@RequestMapping("/downloadTwelveZF")
	public String downloadTwelveZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("twelvezf", TwelveZfYz.class, dto, response, repositories.twelvezfYzDao);
	}

	@RequestMapping("/downloadBS9QZF")
	public String downloadBS9QZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("bs9qzf", Bs9qZfYz.class, dto, response, repositories.bs9qZfYzDao);
	}

	@RequestMapping("/downloadZSZF")
	public String downloadZSZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zszf", ZsZfYz.class, dto, response, repositories.zszfYzDao);
	}

	@RequestMapping("/downloadLHZF")
	public String downloadLHZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("lhzf", LhZfYz.class, dto, response, repositories.lhZfYzDao);
	}

	@RequestMapping("/downloadSLQZF")
	public String downloadSLQZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("slqzf", SlqZfYz.class, dto, response, repositories.slqzfYzDao);
	}

	@RequestMapping("/downloadPDZF")
	public String downloadPDZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("pdzf", PdZfYz.class, dto, response, repositories.pdzfYzDao);
	}

	@RequestMapping("/downloadTM12FDZF")
	public String downloadTM12FDZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("tm12fdzf", Tm12FdZfYz.class, dto, response, repositories.tm12fdzfYzDao);
	}

	@RequestMapping("/downloadDSZF")
	public String downloadDSZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("dszf", DsZfYz.class, dto, response, repositories.dszfYzDao);
	}

	@RequestMapping("/downloadMWZF")
	public String downloadMWZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("mwzf", MwZfYz.class, dto, response, repositories.mwZfYzDao);
	}

	@RequestMapping("/downloadQQZF")
	public String downloadQQZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("qqzf", QqZfYz.class, dto, response, repositories.qqZfYzDao);
	}

	@RequestMapping("/downloadQIWZF")
	public String downloadQIWZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("qiwzf", QiwZfYz.class, dto, response, repositories.qiwzfYzDao);
	}

	@RequestMapping("/downloadSWZF")
	public String downloadSWZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("swzf", SwZfYz.class, dto, response, repositories.swZfYzDao);
	}

	@RequestMapping("/downloadWXZF")
	public String downloadWXZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("wxzf", WxZfYz.class, dto, response, repositories.wxzfYzDao);
	}

	@RequestMapping("/downloadWXDSZF")
	public String downloadWXDSZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("wxdszf", WxdsZfYz.class, dto, response, repositories.wxdsZfYzDao);
	}

	@RequestMapping("/downloadSXZF")
	public String downloadSXZF(DownloadDTO dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=sxzf.csv");
		QueryInfo<SxZfYz> queryInfo = new QueryInfo<SxZfYz>();
		SxZfYz queryObj = new SxZfYz();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<SxZfYz> result = repositories.sxZfYzDao.query(queryInfo);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 和, 差值, 遗值").append("\n");
			for (SxZfYz data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getTotal() + "").append(", ");
				writer.append(data.getDelta() + "").append(", ");
				writer.append(data.getLastYz() + "").append("\n");
			}
		}
		return null;
	}

	@RequestMapping("/downloadHMPMYZ")
	public String downloadHMPMYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=hmpm.csv");
		QueryInfo<KaiJiang> queryInfo = new QueryInfo<KaiJiang>();
		KaiJiang queryObj = new KaiJiang();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<PmDTO> result = getPMList(queryInfo, false);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 特码, 平码1, 平码2, 平码3, 平码4, 平码5, 平码6, 期差").append("\n");
			for (PmDTO data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getSpecialNum().getNum() + "").append(", ");
				writer.append(data.getNum1().getNum() + "").append(", ");
				writer.append(data.getNum2().getNum() + "").append(", ");
				writer.append(data.getNum3().getNum() + "").append(", ");
				writer.append(data.getNum4().getNum() + "").append(", ");
				writer.append(data.getNum5().getNum() + "").append(", ");
				writer.append(data.getNum6().getNum() + "").append(", ");
				writer.append((data.getSpecialNum().getDelta() != null ? data.getSpecialNum().getDelta() : "") + "")
						.append("\n");
			}
		}
		return null;
	}

	@RequestMapping("/downloadZFCS")
	public String downloadZFCS(DownloadDTO dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=sxzfcs.csv");
		QueryInfo<SxZfYz> queryInfo = new QueryInfo<SxZfYz>();
		SxZfYz queryObj = new SxZfYz();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<SxZfYz> result = (PageResult<SxZfYz>) countSXZF(queryInfo).getData();
		result.getList().remove(result.getList().size() - 1);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 当期次数").append("\n");
			for (SxZfYz data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getDelta() + "").append("\n");
			}
		}
		return null;
	}

	@RequestMapping("/downloadZFCSLevel2")
	public String downloadZFCSLevel2(DownloadDTO dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=sxzfcs_level2.csv");
		QueryInfo<SxZfYz> queryInfo = new QueryInfo<SxZfYz>();
		SxZfYz queryObj = new SxZfYz();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<SxZfYz> result = (PageResult<SxZfYz>) listSXZFLevel2(queryInfo).getData();
		result.getList().remove(result.getList().size() - 1);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 当期次数").append("\n");
			for (SxZfYz data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getDelta() + "").append("\n");
			}
		}
		return null;
	}

	@RequestMapping("/downloadQWYZ")
	public String downloadQWYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=qwyz.csv");
		QueryInfo<QwYz> queryInfo = new QueryInfo<QwYz>();
		QwYz queryObj = new QwYz();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<QwYz> result = (PageResult<QwYz>) listQWYZ(queryInfo).getData();
		result.getList().remove(result.getList().size() - 1);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 和, 最大遗值, 最大连续期数").append("\n");
			for (QwYz data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getTotal() + "").append(", ");
				writer.append(data.getCurrentYz() + "").append(", ");
				writer.append(data.getMaxTimes() + "").append("\n");
			}
		}
		return null;
	}

	@RequestMapping("/downloadSXDSYZ")
	public String downloadSXDSYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadDSYZ(dto, SxDsYz.class, repositories.sxdsYzDao, "sxdsyz", response);
	}

	@RequestMapping("/downloadHMDSYZ")
	public String downloadHMDSYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadDSYZ(dto, HmDsYz.class, repositories.hmdsYzDao, "hmdsyz", response);
	}

	@RequestMapping("/downloadLHDSYZ")
	public String downloadLHDSYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadDSYZ(dto, LhDsYz.class, repositories.lhdsYzDao, "lhdsyz", response);
	}

	private <T extends BaseDsYz> String downloadDSYZ(DownloadDTO dto, Class<T> clazz, BaseYzDao<T> dao, String filename,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=" + filename + ".csv");
		QueryInfo<T> queryInfo = new QueryInfo<T>();
		T queryObj = clazz.newInstance();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<T> result = dao.query(queryInfo);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 遗值（大小）, 遗值（单双）, 遗值（大小单双）").append("\n");
			for (T data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getLastDxYz() + "").append(", ");
				writer.append(data.getLastDsYz() + "").append(", ");
				writer.append(data.getLastDxDsYz() + "").append(", ");
			}
		}
		return null;
	}

	@RequestMapping("/downloadSQYZ")
	public String downloadSQYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=sqyz.csv");
		QueryInfo<SqYz> queryInfo = new QueryInfo<SqYz>();
		SqYz queryObj = new SqYz();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<SqYz> result = repositories.sqYzDao.query(queryInfo);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 和, 差值, 遗值").append("\n");
			for (SqYz data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getTotal() + "").append(", ");
				writer.append(data.getDelta() + "").append(", ");
				writer.append(data.getLastYz() + "").append("\n");
			}
		}
		return null;
	}

	@RequestMapping("/downloadTMYZ")
	public String downloadTMYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=tmyz.csv");
		QueryInfo<TmYz> queryInfo = new QueryInfo<TmYz>();
		TmYz queryObj = new TmYz();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<TmYz> result = repositories.tmYzDao.query(queryInfo);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 遗值（上期）, ");
			for (int i = 1; i < 50; i++) {
				writer.append("号码" + i).append(", ");
			}
			writer.append("\n");
			for (TmYz data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getLastYz() + "").append(", ");
				for (int i = 1; i < 50; i++) {
					Method m = ReflectionUtils.findMethod(TmYz.class, "getHm" + i);
					Integer value = (Integer) m.invoke(data);
					if (value != null) {
						writer.append(value.toString()).append(", ");
					} else {
						writer.append("").append(", ");
					}
				}
				writer.append("\n");
			}
		}
		return null;
	}

	@RequestMapping("/downloadTMYZ2")
	public String downloadTMYZ2(DownloadDTO dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=tmyz2.csv");
		QueryInfo<TmYz> queryInfo = new QueryInfo<TmYz>();
		TmYz queryObj = new TmYz();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<TmYz> result = repositories.tmYzDao.query(queryInfo);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 遗值（上期）, ");
			for (int i = 1; i < 50; i++) {
				writer.append("号码" + i).append(", ");
			}
			writer.append("反转").append("\n");
			for (TmYz data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getLastYz() + "").append(", ");
				List<Integer> arr = new ArrayList<Integer>();
				for (int i = 1; i < 50; i++) {
					Method m = ReflectionUtils.findMethod(TmYz.class, "getHm" + i);
					Integer value = (Integer) m.invoke(data);
					if (value != null && value > 0 && value < 50) {
						arr.add(value);
						writer.append(value.toString()).append(", ");
					} else {
						writer.append("").append(", ");
					}
				}
				String reversed = "";
				for (int i = 1; i < 50; i++) {
					if (!arr.contains(i)) {
						reversed += i + " ";
					}
				}
				writer.append(reversed);
				writer.append("\n");
			}
		}
		return null;
	}

	@RequestMapping("/downloadPTYZ")
	public String downloadPTYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=ptyz.csv");
		QueryInfo<PtYz> queryInfo = new QueryInfo<PtYz>();
		PtYz queryObj = new PtYz();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<PtYz> result = repositories.ptYzDao.query(queryInfo);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, ");
			for (int i = 1; i < 50; i++) {
				writer.append("号码" + i).append(", ");
			}
			writer.append("\n");
			for (PtYz data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				for (int i = 1; i < 50; i++) {
					Method m = ReflectionUtils.findMethod(PtYz.class, "getHm" + i);
					Integer value = (Integer) m.invoke(data);
					if (value != null) {
						writer.append(value.toString()).append(", ");
					} else {
						writer.append("").append(", ");
					}
				}
				writer.append("\n");
			}
		}
		return null;
	}

	@RequestMapping("/listXBWJY")
	public BaseResult listXBWJY(@RequestBody QueryInfo<XbwJYCondition> queryInfos) throws Exception {
		Map<String, XbwJYResult> map = new HashMap<String, XbwJYResult>();
		List<XbwJYResult> list = new ArrayList<XbwJYResult>();
		for (Integer type : queryInfos.getObject().getTypes()) {
			QueryInfo<XbwJYCondition> queryInfo = new QueryInfo<XbwJYCondition>();
			XbwJYCondition condition = new XbwJYCondition();
			condition.setYear(queryInfos.getObject().getYear());
			condition.setPhase(queryInfos.getObject().getPhase());
			condition.setType(type);
			queryInfo.setObject(condition);
			queryInfo.setPageInfo(queryInfos.getPageInfo());
			PageResult<XbwJY> result = repositories.yzService.calXBWJY(queryInfo);
			for (XbwJY data : result.getList()) {
				String key = data.getYear() + "_" + data.getPhase();
				XbwJYResult dataSet = map.get(key);
				if (dataSet == null) {
					dataSet = new XbwJYResult();
					dataSet.setYear(data.getYear());
					dataSet.setPhase(data.getPhase());
					dataSet.setSpecialNum(data.getSpecialNum());
					map.put(key, dataSet);
					list.add(dataSet);
				}
				dataSet.addData(data);
			}
		}
		if (queryInfos.getObject().getTypes().size() == 1) {
			list.add(new XbwJYResult());
		}
		return new BaseResult(new PageResult<>(list, list.size(), queryInfos.getPageInfo()));
	}

	@RequestMapping("/getSxListInCurrentYear")
	public BaseResult getSxListInCurrentYear() {
		SX bmnSx = DateUtil.getSxByYear(Calendar.getInstance().get(Calendar.YEAR));
		List<SX> list = new ArrayList<SX>();
		for (int i = bmnSx.getPos() + 12; i > bmnSx.getPos(); i--) {
			int pos = i;
			if (i > 12) {
				pos = i - 12;
			}
			list.add(SX.posOf(pos));
		}
		return new BaseResult(list);
	}

	@RequestMapping("/listXBWJY2")
	public BaseResult listXBWJY2(@RequestBody XbwJYCondition queryInfo) throws Exception {
		return new BaseResult(repositories.yzService.getXbwJY2(queryInfo));
	}

	@RequestMapping("/listFXSW1")
	public BaseResult listFXSW1(@RequestBody QueryInfo<FxSw1> queryInfo) throws Exception {
		PageResult<FxSw1> result = repositories.fxsw1Dao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			result.getList().add(new FxSw1());
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listFXSW2")
	public BaseResult listFXSW2(@RequestBody QueryInfo<FxSw2> queryInfo) throws Exception {
		PageResult<FxSw2> result = repositories.fxsw2Dao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			result.getList().add(new FxSw2());
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listFXSW3")
	public BaseResult listFXSW3(@RequestBody QueryInfo<FxSw3> queryInfo) throws Exception {
		PageResult<FxSw3> result = repositories.fxsw3Dao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			result.getList().add(new FxSw3());
		}
		return new BaseResult(result);
	}

}
