package lhc.controller;

import java.io.Writer;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

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

import lhc.domain.Avg;
import lhc.domain.BaseYz;
import lhc.domain.Bs9qLrYz;
import lhc.domain.Bs9qYz;
import lhc.domain.Bs9qZfYz;
import lhc.domain.BsYz;
import lhc.domain.BsZfYz;
import lhc.domain.DsYz;
import lhc.domain.DsZfYz;
import lhc.domain.KaiJiang;
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
import lhc.dto.BaseResult;
import lhc.dto.DownloadDTO;
import lhc.dto.DownloadPrepareTZ;
import lhc.dto.PmDTO;
import lhc.dto.PmNum;
import lhc.dto.SpecialNum;
import lhc.dto.TmYzInfo;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.repository.jpa.BaseYzDao;
import lhc.repository.jpa.Repositories;
import lhc.service.ParallelYzServiceWrapper;
import lhc.service.YZService;

@RestController
@RequestMapping("/mvc/yz")
@SuppressWarnings("unchecked")
public class YZController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private YZService yzService;

	@Autowired
	private Repositories repositories;

	@Autowired
	private ParallelYzServiceWrapper parallelYzService;

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

	@RequestMapping("/calYZ")
	public BaseResult calYZ() throws Exception {
		calYZ(true);
		return BaseResult.EMPTY;
	}

	public void calYZ(boolean sleep) throws Exception {
		List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
		futures.add(yzService.calSX());
		futures.add(yzService.calHMQWYZ());
		futures.add(yzService.calSWYZ());
		futures.add(yzService.calMWYZ());
		futures.add(yzService.calLHYZ());
		futures.add(yzService.calQQYZ());
		futures.add(yzService.calBSYZ());
		futures.add(yzService.calSQYZ());
		futures.add(yzService.calDSYZ());
		futures.add(yzService.calTMYZ());
		futures.add(yzService.calZSYZ());
		futures.add(yzService.calWXYZ());
		futures.add(yzService.calPTYZ());
		futures.add(yzService.calTwelveYZ());
		futures.add(yzService.calSLQYZ());
		futures.add(yzService.calPDYZ());
		futures.add(yzService.calBS9QYZ());
		futures.add(yzService.calWXDSYZ());
		if (sleep) {
			sleep(futures, 1000);
		} else {
			sleep(futures, 0);
		}
		logger.info("End of calYZ stage1...");

		futures.clear();
		futures.add(yzService.calSXDSYZ());
		futures.add(yzService.calSXCSYZ());
		futures.add(yzService.calTM12FDYZ());
		futures.add(parallelYzService.calAvg(repositories.sxyzRepository));
		futures.add(parallelYzService.calAvg(repositories.sxzfyz2Repository));
		futures.add(parallelYzService.calAvg(repositories.bsyzRepository));
		futures.add(parallelYzService.calAvg(repositories.bszfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.swyzRepository));
		futures.add(parallelYzService.calAvg(repositories.swzfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.zsyzRepository));
		futures.add(parallelYzService.calAvg(repositories.zszfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.dsyzRepository));
		futures.add(parallelYzService.calAvg(repositories.dszfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.mwyzRepository));
		futures.add(parallelYzService.calAvg(repositories.mwzfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.lhyzRepository));
		futures.add(parallelYzService.calAvg(repositories.lhzfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.wxyzRepository));
		futures.add(parallelYzService.calAvg(repositories.wxzfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.qqyzRepository));
		futures.add(parallelYzService.calAvg(repositories.qqzfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.twelveyzRepository));
		futures.add(parallelYzService.calAvg(repositories.twelvezfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.slqyzRepository));
		futures.add(parallelYzService.calAvg(repositories.slqzfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.pdyzRepository));
		futures.add(parallelYzService.calAvg(repositories.pdzfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.bs9qyzRepository));
		futures.add(parallelYzService.calAvg(repositories.bs9qzfyzRepository));
		futures.add(parallelYzService.calAvg(repositories.wxdsyzRepository));
		futures.add(parallelYzService.calAvg(repositories.wxdszfyzRepository));
		if (sleep) {
			sleep(futures, 1000);
		} else {
			sleep(futures, 0);
		}
		logger.info("End of calYZ stage2...");

		futures.clear();
		futures.add(yzService.calSXLRYZ());
		futures.add(yzService.calMWLRYZ());
		futures.add(yzService.calLHLRYZ());
		futures.add(yzService.calTwelveLRYZ());
		futures.add(yzService.calSLQLRYZ());
		futures.add(yzService.calPDLRYZ());
		futures.add(yzService.calZSLRYZ());
		futures.add(yzService.calBS9QLRYZ());
		futures.add(yzService.calWXDSLRYZ());
		futures.add(parallelYzService.calAvg(repositories.tm12fdyzRepository));
		futures.add(parallelYzService.calAvg(repositories.tm12fdzfyzRepository));
		if (sleep) {
			sleep(futures, 1000);
		} else {
			sleep(futures, 0);
		}
		logger.info("End of calYZ stage3...");

		futures.clear();
		futures.add(yzService.calTM12FDLRYZ());
		futures.add(parallelYzService.calAvg(repositories.sxlryzRepository));
		futures.add(parallelYzService.calAvg(repositories.mwlryzRepository));
		futures.add(parallelYzService.calAvg(repositories.twelvelryzRepository));
		futures.add(parallelYzService.calAvg(repositories.slqlryzRepository));
		futures.add(parallelYzService.calAvg(repositories.pdlryzRepository));
		futures.add(parallelYzService.calAvg(repositories.lhlryzRepository));
		futures.add(parallelYzService.calAvg(repositories.zslryzRepository));
		futures.add(parallelYzService.calAvg(repositories.bs9qlryzRepository));
		futures.add(parallelYzService.calAvg(repositories.wxdslryzRepository));
		if (sleep) {
			sleep(futures, 1000);
		} else {
			sleep(futures, 0);
		}
		logger.info("End of calYZ stage4...");

		futures.clear();
		futures.add(parallelYzService.calAvg(repositories.tm12fdlryzRepository));
		if (sleep) {
			sleep(futures, 1000);
		} else {
			sleep(futures, 0);
		}
		logger.info("Done calYZ...");
	}

	private void sleep(List<Future<Exception>> futures, long time) throws Exception {
		while (true) {
			int count = 0;
			for (Future<Exception> f : futures) {
				if (f.isDone()) {
					if (f.get() != null) {
						throw f.get();
					}
					count++;
				}
			}
			if (count == futures.size()) {
				break;
			}
			if (time > 0) {
				Thread.sleep(time);
			}
		}
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
				List<TmYzInfo> fdList = yzService.getTMFDList(tmResult, false);
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
		return new BaseResult(yzService.getTMFDList(tmResult, false));
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
		Map<String, Object> map = yzService.calSXCSYZ(queryInfo.getPageInfo(), sxResult, null, null);
		PageResult<SxCsYz> result = (PageResult<SxCsYz>) map.get("result");
		if (result != null && result.getTotal() > 0) {
			result.getList().add(new SxCsYz());
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listMWCSYZ")
	public BaseResult listMWCSYZ(@RequestBody QueryInfo<MwYz> queryInfo) throws Exception {
		PageResult<MwYz> sxResult = repositories.mwYzDao.query(queryInfo);
		Map<String, Object> map = yzService.calMWCSYZ(queryInfo.getPageInfo(), sxResult, null, null);
		PageResult<MwCsYz> result = (PageResult<MwCsYz>) map.get("result");
		if (result != null && result.getTotal() > 0) {
			result.getList().add(new MwCsYz());
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
				int k = i - 10;
				for (int j = 0; j < 11; j++) {
					if (k > -1 && k < result.getList().size()) {
						data.getLastYzList()[j] = result.getList().get(k).getLastYz();
					}
					k++;
				}
				if (i < result.getList().size() - 2) {
					data.getLastYzList()[11] = result.getList().get(i + 1).getLastYz();
				}
			}
		}
		return new BaseResult(result);
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

	@RequestMapping("/listTMFDYZ")
	public BaseResult listTMFDYZ(@RequestBody QueryInfo<TmFdYz> queryInfo, String mode) throws Exception {
		PageResult<TmFdYz> result = repositories.tmfdYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			TmFdYz queryObj = queryInfo.getObject();
			TmYz tmResult = repositories.tmyzRepository.findByYearAndPhase(queryObj.getYear(), queryObj.getPhase());
			if (tmResult != null) {
				List<TmYzInfo> fdList = yzService.getTMFDList(tmResult, false);
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

	@RequestMapping("/downloadTZDBW")
	public String downloadTZDBW(DownloadPrepareTZ dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=tzdbw.csv");
		Writer writer = response.getWriter();
		for (int i = 1; i < 37; i++) {
			Method m1 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getCategories" + i);
			Method m2 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getHms" + i);
			Method m3 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getNonHms" + i);
			writer.append((String) m1.invoke(dto)).append("\n");
			writer.append((String) m2.invoke(dto)).append("\n");
			writer.append("反转号码").append("\n");
			writer.append((String) m3.invoke(dto)).append("\n");
			writer.append("\n");
		}
		writer.append("汇总号码").append("\n");
		writer.append(dto.getAllHms()).append("\n");
		writer.append("汇总反转号码").append("\n");
		writer.append(dto.getAllNonHms()).append("\n");
		return null;
	}

	@RequestMapping("/downloadTZDC")
	public String downloadTZDC(DownloadPrepareTZ dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=tzdc.csv");
		Writer writer = response.getWriter();
		for (int i = 1; i < 12; i++) {
			Method m1 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getCategories" + i);
			Method m2 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getGsPlusHms" + i);
			Method m3 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getGsMinusHms" + i);
			Method m4 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getGsPlusNonHms" + i);
			Method m5 = ReflectionUtils.findMethod(DownloadPrepareTZ.class, "getGsMinusNonHms" + i);
			writer.append((String) m1.invoke(dto)).append("\n");
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

	@RequestMapping("/downloadTwelveYZ")
	public String downloadTwelveYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("twelveyz", TwelveYz.class, dto, response, repositories.twelveYzDao, "位置", "Pos");
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
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=dsyz.csv");
		QueryInfo<SxDsYz> queryInfo = new QueryInfo<SxDsYz>();
		SxDsYz queryObj = new SxDsYz();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<SxDsYz> result = repositories.sxdsYzDao.query(queryInfo);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 遗值（生肖,大小）, 遗值（生肖,单双）, 遗值（号码,大小）, 遗值（号码,单双）").append("\n");
			for (SxDsYz data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getLastSxDxYz() + "").append(", ");
				writer.append(data.getLastSxDsYz() + "").append(", ");
				writer.append(data.getLastHmDxYz() + "").append(", ");
				writer.append(data.getLastHmDsYz() + "").append("\n");
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
}
