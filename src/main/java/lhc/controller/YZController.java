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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lhc.domain.Avg;
import lhc.domain.BsYz;
import lhc.domain.DsYz;
import lhc.domain.DsZfYz;
import lhc.domain.KaiJiang;
import lhc.domain.LhYz;
import lhc.domain.LhZfYz;
import lhc.domain.MwYz;
import lhc.domain.MwZfYz;
import lhc.domain.QqYz;
import lhc.domain.QwYz;
import lhc.domain.SqYz;
import lhc.domain.SwYz;
import lhc.domain.SwZfYz;
import lhc.domain.SxDsYz;
import lhc.domain.SxYz;
import lhc.domain.SxZfYz;
import lhc.domain.SxZfYz2;
import lhc.domain.TmFdYz;
import lhc.domain.TmYz;
import lhc.domain.ZsYz;
import lhc.domain.ZsZfYz;
import lhc.dto.BaseResult;
import lhc.dto.DownloadDTO;
import lhc.dto.PmDTO;
import lhc.dto.PmNum;
import lhc.dto.SpecialNum;
import lhc.dto.TmYzInfo;
import lhc.dto.query.PageInfo;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.enums.SX;
import lhc.repository.jpa.BaseYzDao;
import lhc.repository.jpa.api.BsYzRepository;
import lhc.repository.jpa.api.DsYzRepository;
import lhc.repository.jpa.api.DsZfYzRepository;
import lhc.repository.jpa.api.KaiJiangRepository;
import lhc.repository.jpa.api.LhYzRepository;
import lhc.repository.jpa.api.LhZfYzRepository;
import lhc.repository.jpa.api.MwYzRepository;
import lhc.repository.jpa.api.MwZfYzRepository;
import lhc.repository.jpa.api.SwYzRepository;
import lhc.repository.jpa.api.SwZfYzRepository;
import lhc.repository.jpa.api.SxYzRepository;
import lhc.repository.jpa.api.SxZfYz2Repository;
import lhc.repository.jpa.api.TmYzRepository;
import lhc.repository.jpa.api.ZsYzRepository;
import lhc.repository.jpa.api.ZsZfYzRepository;
import lhc.repository.jpa.dao.BsYzDao;
import lhc.repository.jpa.dao.DsYzDao;
import lhc.repository.jpa.dao.DsZfYzDao;
import lhc.repository.jpa.dao.KaiJiangDao;
import lhc.repository.jpa.dao.LhYzDao;
import lhc.repository.jpa.dao.LhZfYzDao;
import lhc.repository.jpa.dao.MwYzDao;
import lhc.repository.jpa.dao.MwZfYzDao;
import lhc.repository.jpa.dao.QqYzDao;
import lhc.repository.jpa.dao.QwYzDao;
import lhc.repository.jpa.dao.SqYzDao;
import lhc.repository.jpa.dao.SwYzDao;
import lhc.repository.jpa.dao.SwZfYzDao;
import lhc.repository.jpa.dao.SxDsYzDao;
import lhc.repository.jpa.dao.SxYzDao;
import lhc.repository.jpa.dao.SxZfYz2Dao;
import lhc.repository.jpa.dao.SxZfYzDao;
import lhc.repository.jpa.dao.TmFdYzDao;
import lhc.repository.jpa.dao.TmYzDao;
import lhc.repository.jpa.dao.ZsYzDao;
import lhc.repository.jpa.dao.ZsZfYzDao;
import lhc.service.ParallelYzServiceWrapper;
import lhc.service.YZService;

@RestController
@RequestMapping("/mvc/yz")
@SuppressWarnings("unchecked")
public class YZController {
	@Autowired
	private YZService yzService;

	@Autowired
	private KaiJiangRepository KaiJiangRepository;

	@Autowired
	private SxYzDao sxYzDao;

	@Autowired
	private SxZfYzDao sxZfYzDao;

	@Autowired
	private SxZfYz2Dao sxZfYz2Dao;

	@Autowired
	private SwZfYzDao swZfYzDao;

	@Autowired
	private MwZfYzDao mwZfYzDao;

	@Autowired
	private LhZfYzDao lhZfYzDao;

	@Autowired
	private KaiJiangDao kaiJiangDao;

	@Autowired
	private QwYzDao qwYzDao;

	@Autowired
	private SwYzDao swYzDao;

	@Autowired
	private MwYzDao mwYzDao;

	@Autowired
	private LhYzDao lhYzDao;

	@Autowired
	private QqYzDao qqYzDao;

	@Autowired
	private BsYzDao bsYzDao;

	@Autowired
	private ZsYzDao zsYzDao;

	@Autowired
	private ZsZfYzDao zszfYzDao;

	@Autowired
	private DsYzDao dsYzDao;

	@Autowired
	private SqYzDao sqYzDao;

	@Autowired
	private SxDsYzDao sxdsYzDao;

	@Autowired
	private TmYzDao tmYzDao;

	@Autowired
	private TmFdYzDao tmfdYzDao;

	@Autowired
	private DsZfYzDao dszfYzDao;

	@Autowired
	private TmYzRepository tmYzRepository;

	@Autowired
	private SxYzRepository sxYzRepository;

	@Autowired
	private SxZfYz2Repository sxzfYz2Repository;

	@Autowired
	private BsYzRepository bsYzRepository;

	@Autowired
	private ZsYzRepository zsYzRepository;

	@Autowired
	private ZsZfYzRepository zszfYzRepository;

	@Autowired
	private DsYzRepository dsYzRepository;

	@Autowired
	private SwYzRepository swYzRepository;

	@Autowired
	private SwZfYzRepository swzfYzRepository;

	@Autowired
	private DsZfYzRepository dszfYzRepository;

	@Autowired
	private MwYzRepository mwYzRepository;

	@Autowired
	private MwZfYzRepository mwzfYzRepository;

	@Autowired
	private LhYzRepository lhYzRepository;

	@Autowired
	private LhZfYzRepository lhzfYzRepository;

	@Autowired
	private ParallelYzServiceWrapper parallelYzService;

	@RequestMapping("/years")
	public BaseResult years() {
		List<KaiJiang> list = KaiJiangRepository.findGroupByYear();
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
		List<KaiJiang> list = KaiJiangRepository.findByYearOrderByPhaseDesc(year);
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
		futures.add(yzService.calSXDSYZ());
		futures.add(yzService.calTMYZ());
		futures.add(yzService.calZSYZ());
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
			Thread.sleep(1000);
		}
		futures.clear();
		futures.add(parallelYzService.calAvg(sxYzRepository));
		futures.add(parallelYzService.calAvg(sxzfYz2Repository));
		futures.add(parallelYzService.calAvg(bsYzRepository));
		futures.add(parallelYzService.calAvg(swYzRepository));
		futures.add(parallelYzService.calAvg(swzfYzRepository));
		futures.add(parallelYzService.calAvg(zsYzRepository));
		futures.add(parallelYzService.calAvg(zszfYzRepository));
		futures.add(parallelYzService.calAvg(dsYzRepository));
		futures.add(parallelYzService.calAvg(dszfYzRepository));
		futures.add(parallelYzService.calAvg(mwYzRepository));
		futures.add(parallelYzService.calAvg(mwzfYzRepository));
		futures.add(parallelYzService.calAvg(lhYzRepository));
		futures.add(parallelYzService.calAvg(lhzfYzRepository));
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
			Thread.sleep(1000);
		}
		return BaseResult.EMPTY;
	}

	@RequestMapping("/listSX")
	public BaseResult listSX(@RequestBody QueryInfo<SxYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<SxYz> result = sxYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				SxYz last = new SxYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listSWYZ")
	public BaseResult listSWYZ(@RequestBody QueryInfo<SwYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<SwYz> result = swYzDao.query(queryInfo);
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
		PageResult<SwZfYz> result = swZfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listMWYZ")
	public BaseResult listMWYZ(@RequestBody QueryInfo<MwYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<MwYz> result = mwYzDao.query(queryInfo);
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
		PageResult<MwZfYz> result = mwZfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listLHYZ")
	public BaseResult listLHYZ(@RequestBody QueryInfo<LhYz> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<LhYz> result = lhYzDao.query(queryInfo);
		if ("1".equals(mode)) {
			if (result != null && result.getTotal() > 0) {
				LhYz last = new LhYz();
				last.setTotal(result.getList().size());
				result.getList().add(last);
			}
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listLHZF")
	public BaseResult listLHZF(@RequestBody QueryInfo<LhZfYz> queryInfo) throws Exception {
		PageResult<LhZfYz> result = lhZfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listQQYZ")
	public BaseResult listQQYZ(@RequestBody QueryInfo<QqYz> queryInfo) throws Exception {
		PageResult<QqYz> result = qqYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			QqYz last = new QqYz();
			last.setTotal(result.getList().size());
			result.getList().add(last);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listSQYZ")
	public BaseResult listSQYZ(@RequestBody QueryInfo<SqYz> queryInfo) throws Exception {
		PageResult<SqYz> result = sqYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			SqYz last = new SqYz();
			last.setTotal(result.getList().size());
			result.getList().add(last);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listBSYZ")
	public BaseResult listBSYZ(@RequestBody QueryInfo<BsYz> queryInfo) throws Exception {
		return new BaseResult(bsYzDao.query(queryInfo));
	}

	@RequestMapping("/listZSYZ")
	public BaseResult listZSYZ(@RequestBody QueryInfo<ZsYz> queryInfo) throws Exception {
		return new BaseResult(zsYzDao.query(queryInfo));
	}

	@RequestMapping("/listZSZF")
	public BaseResult listZSZF(@RequestBody QueryInfo<ZsZfYz> queryInfo) throws Exception {
		PageResult<ZsZfYz> result = zszfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listDSYZ")
	public BaseResult listDSYZ(@RequestBody QueryInfo<DsYz> queryInfo) throws Exception {
		return new BaseResult(dsYzDao.query(queryInfo));
	}

	@RequestMapping("/listDSZF")
	public BaseResult listDSZF(@RequestBody QueryInfo<DsZfYz> queryInfo) throws Exception {
		PageResult<DsZfYz> result = dszfYzDao.query(queryInfo);
		return new BaseResult(result);
	}

	@RequestMapping("/listSXDSYZ")
	public BaseResult listSXDSYZ(@RequestBody QueryInfo<SxDsYz> queryInfo) throws Exception {
		PageResult<SxDsYz> result = sxdsYzDao.query(queryInfo);
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
		PageResult<SxYz> result = sxYzDao.query(queryInfo);
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
						Method sm = SxYz.class.getDeclaredMethod("set" + next.getCurrentSx().name(), Integer.class);
						Method gm = SxYz.class.getDeclaredMethod("get" + next.getCurrentSx().name());
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
		PageResult<SxZfYz> result = sxZfYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			SxZfYz last = new SxZfYz();
			last.setTotal(result.getList().size());
			result.getList().add(last);
		}

		return new BaseResult(result);
	}

	@RequestMapping("/listSXZF2")
	public BaseResult listSXZF2(@RequestBody QueryInfo<SxZfYz2> queryInfo, @RequestParam String mode) throws Exception {
		PageResult<SxZfYz2> result = sxZfYz2Dao.query(queryInfo);
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
		PageResult<SxZfYz> result = sxZfYzDao.query(queryInfo);
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
						Method gm = SxZfYz.class.getDeclaredMethod("getZf" + i);
						Integer value = (Integer) gm.invoke(lastData);
						if (value == null) {
							value = 0;
						}
						Method sm = SxZfYz.class.getDeclaredMethod("setZf" + i, Integer.class);
						sm.invoke(data, value);
					}
					if (current.getCurrentPos() != null && last.getCurrentPos() != null) {
						BigDecimal pos = new BigDecimal(current.getCurrentPos()).subtract(new BigDecimal(last.getCurrentPos()))
								.abs();
						Method gm = SxZfYz.class.getDeclaredMethod("getZf" + pos.intValue());
						Integer value = (Integer) gm.invoke(data);
						if (value == null) {
							value = 0;
						}
						currentValue = value + 1;
						Method sm = SxZfYz.class.getDeclaredMethod("setZf" + pos.intValue(), Integer.class);
						sm.invoke(data, currentValue);
						data.setCurrentPos(pos.intValue());
					}
				}
				data.setDelta(currentValue);

				if (lastData != null) {
					int total = 0;
					for (int i = 0; i < 12; i++) {
						Method gm = SxZfYz.class.getDeclaredMethod("getZf" + i);
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
		PageResult<SxZfYz> result = sxZfYzDao.query(queryInfo);
		List<SxZfYz> list = new ArrayList<SxZfYz>();
		if (result != null && result.getTotal() > 0) {
			SxZfYz lastZF = new SxZfYz();
			for (int i = 0; i < 12; i++) {
				Method m = SxZfYz.class.getDeclaredMethod("setZf" + i, Integer.class);
				m.invoke(lastZF, 0);
			}
			for (SxZfYz data : result.getList()) {
				SxZfYz dto = new SxZfYz();
				for (int i = 0; i < 12; i++) {
					Method sm = SxZfYz.class.getDeclaredMethod("setZf" + i, Integer.class);
					Method gm = SxZfYz.class.getDeclaredMethod("getZf" + i);
					sm.invoke(dto, (Integer) gm.invoke(lastZF));
				}
				dto.setDate(data.getDate());
				dto.setYear(data.getYear());
				dto.setPhase(data.getPhase());
				dto.setId(data.getId());
				int currentValue = 0;
				for (int i = 0; i < 12; i++) {
					Method gm = SxZfYz.class.getDeclaredMethod("getZf" + i);
					Integer zf = (Integer) gm.invoke(data);
					if (zf != null && zf == 0) {
						Method sm = SxZfYz.class.getDeclaredMethod("setZf" + i, Integer.class);
						currentValue = 1 + (Integer) gm.invoke(dto);
						sm.invoke(dto, currentValue);
						break;
					}
				}
				dto.setDelta(currentValue);

				int total = 0;
				for (int i = 0; i < 12; i++) {
					Method gm = SxZfYz.class.getDeclaredMethod("getZf" + i);
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

	@RequestMapping("/countSXYZ")
	public BaseResult countSXYZ(@RequestBody QueryInfo<SxYz> queryInfo) throws Exception {
		PageResult<SxYz> result = sxYzDao.query(queryInfo);
		List<SxYz> list = new ArrayList<SxYz>();
		if (result != null && result.getTotal() > 0) {
			SxYz lastYZ = new SxYz();
			for (SX sx : SX.seq()) {
				Method m = SxYz.class.getDeclaredMethod("set" + sx.name(), Integer.class);
				m.invoke(lastYZ, 0);
			}
			for (SxYz data : result.getList()) {
				SxYz dto = new SxYz();
				for (SX sx : SX.seq()) {
					Method sm = SxYz.class.getDeclaredMethod("set" + sx.name(), Integer.class);
					Method gm = SxYz.class.getDeclaredMethod("get" + sx.name());
					sm.invoke(dto, (Integer) gm.invoke(lastYZ));
				}
				dto.setYear(data.getYear());
				dto.setPhase(data.getPhase());
				dto.setId(data.getId());
				int currentValue = 0;
				for (SX sx : SX.seq()) {
					Method gm = SxYz.class.getDeclaredMethod("get" + sx.name());
					Integer value = (Integer) gm.invoke(data);
					if (value != null && value == 0) {
						Method sm = SxYz.class.getDeclaredMethod("set" + sx.name(), Integer.class);
						currentValue = 1 + (Integer) gm.invoke(dto);
						sm.invoke(dto, currentValue);
						break;
					}
				}
				dto.setDelta(currentValue);

				int total = 0;
				for (SX sx : SX.seq()) {
					Method gm = SxYz.class.getDeclaredMethod("get" + sx.name());
					Integer value = (Integer) gm.invoke(dto);
					if (value != null) {
						total += value;
					}
				}
				dto.setTotal(total);
				dto.setAvg(new BigDecimal(1d * total / SX.values().length));
				if (currentValue > 1) {
					dto.setLastCountYz(currentValue - 2);
				}

				list.add(dto);
				lastYZ = dto;
			}

			lastYZ = new SxYz();
			lastYZ.setTotal(result.getList().size());
			list.add(lastYZ);
		}
		result.setList(list);
		return new BaseResult(result);
	}

	@RequestMapping("/countSXJZ")
	public BaseResult countSXJZ(@RequestBody QueryInfo<SxYz> queryInfo) throws Exception {
		PageResult<SxYz> result = sxYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			SxYz totalLine = new SxYz();
			for (SxYz data : result.getList()) {
				Integer lastYz = data.getLastYz();
				if (lastYz != null) {
					if (lastYz < 21) {
						totalLine.getLastYzList()[lastYz]++;
					} else if (lastYz > 20 && lastYz < 31) {
						totalLine.getLastYzList()[21]++;
					} else if (lastYz > 30 && lastYz < 41) {
						totalLine.getLastYzList()[22]++;
					} else if (lastYz > 40 && lastYz < 51) {
						totalLine.getLastYzList()[23]++;
					} else {
						totalLine.getLastYzList()[24]++;
					}
				}
			}
			result.getList().add(totalLine);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/countSXZFJZ")
	public BaseResult countSXZFJZ(@RequestBody QueryInfo<SxZfYz> queryInfo) throws Exception {
		PageResult<SxZfYz> result = sxZfYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			SxZfYz totalLine = new SxZfYz();
			for (SxZfYz data : result.getList()) {
				Integer lastYz = data.getLastYz();
				if (lastYz != null) {
					if (lastYz < 21) {
						totalLine.getLastYzList()[lastYz]++;
					} else if (lastYz > 20 && lastYz < 31) {
						totalLine.getLastYzList()[21]++;
					} else if (lastYz > 30 && lastYz < 41) {
						totalLine.getLastYzList()[22]++;
					} else if (lastYz > 40 && lastYz < 51) {
						totalLine.getLastYzList()[23]++;
					} else {
						totalLine.getLastYzList()[24]++;
					}
				}
			}
			result.getList().add(totalLine);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/countSXZF10Loop")
	public BaseResult countSXZF10Loop(@RequestBody QueryInfo<SxZfYz> queryInfo) throws Exception {
		PageResult<SxZfYz> result = sxZfYzDao.query(queryInfo);
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
		PageResult<SxYz> result = sxYzDao.query(queryInfo);
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

	@RequestMapping("/pmList")
	public BaseResult pmList(@RequestBody QueryInfo<KaiJiang> queryInfo) throws Exception {
		PageResult<KaiJiang> pResult = kaiJiangDao.queryForPM(queryInfo);
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
						Method dtoSm = PmDTO.class.getDeclaredMethod("setNum" + k, PmNum.class);
						Method gm = KaiJiang.class.getDeclaredMethod("getNum" + k);
						Integer num = (Integer) gm.invoke(pmData);
						if (!in) {
							in = num == specialData.getSpecialNum();
							if (in) {
								in2 = in;
							}
						}
						Method dtoGm = PmDTO.class.getDeclaredMethod("getNum" + k);
						PmNum pmNum = (PmNum) dtoGm.invoke(dto);
						if (pmNum == null) {
							pmNum = new PmNum(num, in);
						}
						if (in) {
							pmNum.setMatchedForSpecialNum(true);
							Method tpGm = PmDTO.class.getDeclaredMethod("getTp" + k);
							Method tpSm = PmDTO.class.getDeclaredMethod("setTp" + k, BigDecimal.class);
							BigDecimal total = (BigDecimal) tpGm.invoke(totalDTO);
							tpSm.invoke(totalDTO, total.add(new BigDecimal(1)));
							in = false;
						}
						dtoSm.invoke(dto, pmNum);
					}
					if (in2) {
						dto = map.get(specialData.getDate());
						dto.getSpecialNum().setDelta(i - j);
						break;
					}
				}
				list.add(map.get(specialData.getDate()));
			}
			Collections.reverse(list);
			for (int k = 1; k < 7; k++) {
				Method tpGm = PmDTO.class.getDeclaredMethod("getTp" + k);
				Method tpSm = PmDTO.class.getDeclaredMethod("setTp" + k, BigDecimal.class);
				BigDecimal total = (BigDecimal) tpGm.invoke(totalDTO);
				tpSm.invoke(totalDTO, total.divide(new BigDecimal(result.getPage().getPageSize()), 4, RoundingMode.HALF_UP)
						.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP));
			}
			list.add(totalDTO);
			result.setList(list);
		}
		return new BaseResult(result);
	}

	@RequestMapping("/listQWYZ")
	public BaseResult listQWYZ(@RequestBody QueryInfo<QwYz> queryInfo) throws Exception {
		PageResult<QwYz> result = qwYzDao.query(queryInfo);
		if (result != null && result.getTotal() > 0) {
			QwYz totalLine = new QwYz();
			for (int i = result.getList().size() - 1; i >= 0; i--) {
				QwYz data = result.getList().get(i);
				for (int j = 0; j < 10; j++) {
					Method gm = QwYz.class.getDeclaredMethod("getW" + j);
					Integer value = (Integer) gm.invoke(data);
					if (value != null && value == 0) {
						Method sm = QwYz.class.getDeclaredMethod("setW" + j, Integer.class);
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
		PageResult<QwYz> pResult = qwYzDao.query(queryInfo);
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
						Method gm = QwYz.class.getDeclaredMethod("getW" + j);
						Integer[] currentPair = { (Integer) gm.invoke(last), (Integer) gm.invoke(current) };
						if (currentPair[0] != null && currentPair[1] != null && currentPair[0] == pair[0]
								&& currentPair[1] == pair[1]) {
							Method sm = QwYz.class.getDeclaredMethod("setW" + j, Integer.class);
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
		return new BaseResult(tmYzDao.query(queryInfo));
	}

	@RequestMapping("/listTMFDYZ")
	public BaseResult listTMFDYZ(@RequestBody QueryInfo<TmFdYz> queryInfo) throws Exception {
		PageResult<TmFdYz> result = tmfdYzDao.query(queryInfo);
		TmFdYz queryObj = queryInfo.getObject();
		TmYz tmResult = tmYzRepository.findByYearAndPhase(queryObj.getYear(), queryObj.getPhase());
		if (tmResult != null) {
			List<TmYzInfo> fdList = yzService.getTMFDList(tmResult);
			if (result != null && result.getTotal() > 0) {
				TmFdYz data = new TmFdYz();
				data.setList(fdList);
				result.getList().add(data);
			}
		}
		return new BaseResult(result);
	}

	private <T extends Avg> String downloadYZ(String filename, Class<T> clazz, DownloadDTO dto,
			HttpServletResponse response, BaseYzDao<T> dao) throws Exception {
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
			writer
					.append("日期, 年份, 期数, 差值, 遗值(上), 和, " + "倒1, 倒2, 倒3, 倒4, 倒5, "
							+ "间0, 间1, 间2, 间3, 间4, 间5, 间6, 间7, 间8, 间9, 间10, " + "间11, 间12, 间13, 间14, 间15, 间16, 间17, 间18, 间19")
					.append("\n");
			for (T data : result.getList()) {
				writer.append(data.getDate()).append(", ");
				writer.append(data.getYear() + "").append(", ");
				writer.append(data.getPhase() + "").append(", ");
				writer.append(data.getDelta() + "").append(", ");
				writer.append(data.getLastYz() + "").append(",");
				writer.append(data.getTotal() + "").append(", ");
				for (int i = 0; i < 5; i++) {
					Method m = Avg.class.getDeclaredMethod("getTop" + i);
					Integer value = (Integer) m.invoke(data);
					if (value != null) {
						writer.append(value.toString()).append(", ");
					} else {
						writer.append("").append(", ");
					}
				}
				for (int i = 0; i < 20; i++) {
					Method m = Avg.class.getDeclaredMethod("getMin" + i);
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

	@RequestMapping("/downloadSXYZ")
	public String downloadSXYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("sxyz", SxYz.class, dto, response, sxYzDao);
	}

	@RequestMapping("/downloadDSYZ")
	public String downloadDSYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("dsyz", DsYz.class, dto, response, dsYzDao);
	}

	@RequestMapping("/downloadBSYZ")
	public String downloadBSYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("bsyz", BsYz.class, dto, response, bsYzDao);
	}

	@RequestMapping("/downloadZSYZ")
	public String downloadZSYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("zsyz", ZsYz.class, dto, response, zsYzDao);
	}

	@RequestMapping("/downloadSWYZ")
	public String downloadSWYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("swyz", SwYz.class, dto, response, swYzDao);
	}

	@RequestMapping("/downloadMWYZ")
	public String downloadMWYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("mwyz", MwYz.class, dto, response, mwYzDao);
	}

	@RequestMapping("/downloadLHYZ")
	public String downloadLHYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		return downloadYZ("lhyz", LhYz.class, dto, response, lhYzDao);
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
		PageResult<SxZfYz> result = sxZfYzDao.query(queryInfo);
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
		PageResult<SxDsYz> result = sxdsYzDao.query(queryInfo);
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

	@RequestMapping("/downloadQQYZ")
	public String downloadQQYZ(DownloadDTO dto, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=qqyz.csv");
		QueryInfo<QqYz> queryInfo = new QueryInfo<QqYz>();
		QqYz queryObj = new QqYz();
		queryObj.setYear(dto.getYear());
		queryObj.setPhase(dto.getPhase());
		queryInfo.setObject(queryObj);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(1);
		pageInfo.setPageSize(dto.getSize());
		queryInfo.setPageInfo(pageInfo);
		PageResult<QqYz> result = qqYzDao.query(queryInfo);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 和, 差值, 遗值").append("\n");
			for (QqYz data : result.getList()) {
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
		PageResult<SqYz> result = sqYzDao.query(queryInfo);
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
		PageResult<TmYz> result = tmYzDao.query(queryInfo);
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
					Method m = TmYz.class.getDeclaredMethod("getHm" + i);
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
