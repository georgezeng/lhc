package lhc.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.google.common.base.Joiner;

import lhc.constants.Bs9qNums;
import lhc.constants.DsNums;
import lhc.constants.LhNums;
import lhc.constants.MwNums;
import lhc.constants.PdNums;
import lhc.constants.QiwNums;
import lhc.constants.QqNums;
import lhc.constants.SlqNums;
import lhc.constants.SwNums;
import lhc.constants.TwelveNums;
import lhc.constants.WxDsNums;
import lhc.constants.WxNums;
import lhc.constants.ZsNums;
import lhc.constants.Zx10Nums;
import lhc.constants.Zx11Nums;
import lhc.constants.Zx12Nums;
import lhc.constants.Zx13Nums;
import lhc.constants.Zx14Nums;
import lhc.constants.Zx15Nums;
import lhc.constants.Zx16Nums;
import lhc.constants.Zx17Nums;
import lhc.constants.Zx18Nums;
import lhc.constants.Zx1Nums;
import lhc.constants.Zx2Nums;
import lhc.constants.Zx3Nums;
import lhc.constants.Zx4Nums;
import lhc.constants.Zx5Nums;
import lhc.constants.Zx6Nums;
import lhc.constants.Zx7Nums;
import lhc.constants.Zx8Nums;
import lhc.constants.Zx9Nums;
import lhc.domain.Avg;
import lhc.domain.Bs9qYz;
import lhc.domain.DsYz;
import lhc.domain.KaiJiang;
import lhc.domain.LhYz;
import lhc.domain.MwYz;
import lhc.domain.My1Yz;
import lhc.domain.My200Yz;
import lhc.domain.My2Yz;
import lhc.domain.PdYz;
import lhc.domain.QiwYz;
import lhc.domain.QqYz;
import lhc.domain.SlqYz;
import lhc.domain.SwYz;
import lhc.domain.SxYz;
import lhc.domain.Tm12FdYz;
import lhc.domain.TmYz;
import lhc.domain.TwelveYz;
import lhc.domain.WxYz;
import lhc.domain.WxdsYz;
import lhc.domain.ZsYz;
import lhc.domain.Zx10Yz;
import lhc.domain.Zx11Yz;
import lhc.domain.Zx12Yz;
import lhc.domain.Zx13Yz;
import lhc.domain.Zx14Yz;
import lhc.domain.Zx15Yz;
import lhc.domain.Zx16Yz;
import lhc.domain.Zx17Yz;
import lhc.domain.Zx18Yz;
import lhc.domain.Zx1Yz;
import lhc.domain.Zx2Yz;
import lhc.domain.Zx3Yz;
import lhc.domain.Zx4Yz;
import lhc.domain.Zx5Yz;
import lhc.domain.Zx6Yz;
import lhc.domain.Zx7Yz;
import lhc.domain.Zx8Yz;
import lhc.domain.Zx9Yz;
import lhc.dto.TmYzInfo;
import lhc.enums.SX;
import lhc.repository.jpa.BaseYzDao;
import lhc.repository.jpa.BaseYzRepository;
import lhc.util.CommonUtil;
import lhc.util.DateUtil;

@Service
@Transactional
public class YZ6Service extends YZ5Service {
	@Async
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Future<Exception> calMy1() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			List<Future<Object>> futures = new ArrayList<Future<Object>>();
			Future<Object> lastYzFuture = null;
			do {
				result = repositories.kaiJiangRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (KaiJiang kj : result.getContent()) {
						lastYzFuture = repositories.yzService.doCalMy1InSync(kj, lastYzFuture);
						futures.add(lastYzFuture);
					}
				}
				request = request.next();
			} while (result != null && result.hasNext());
			CommonUtil.getAsyncResultsWithException(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Future<Exception> calMy2() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			List<Future<Object>> futures = new ArrayList<Future<Object>>();
			Future<Object> lastYzFuture = null;
			count = 0;
			int i = 0;
			do {
				result = repositories.kaiJiangRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (KaiJiang kj : result.getContent()) {
							while(count < i) {
								Thread.sleep(10);
							}
							lastYzFuture = repositories.yzService.doCalMy2InSync(kj, lastYzFuture, i);
							i++;
						futures.add(lastYzFuture);
					}
				}
				request = request.next();
			} while (result != null && result.hasNext());
			CommonUtil.getAsyncResultsWithException(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Future<Exception> calMy200() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<KaiJiang> result = null;
			List<Future<Object>> futures = new ArrayList<Future<Object>>();
			Future<Object> lastYzFuture = null;
			do {
				result = repositories.kaiJiangRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (KaiJiang kj : result.getContent()) {
						lastYzFuture = repositories.yzService.doCalMy200InSync(kj, lastYzFuture);
						futures.add(lastYzFuture);
					}
				}
				request = request.next();
			} while (result != null && result.hasNext());
			CommonUtil.getAsyncResultsWithException(futures, 100);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public Future<Object> doCalMy1InSync(KaiJiang kj, Future<Object> lastYzFuture) {
		My1Yz temp = repositories.my1Repository.findByYearAndPhase(kj.getYear(), kj.getPhase());
		if (temp == null) {
			temp = new My1Yz();
			temp.setDate(kj.getDate());
			temp.setYear(kj.getYear());
			temp.setPhase(kj.getPhase());
		}
		final My1Yz yz = temp;
		try {
			calSxMy(yz, lastYzFuture);
			calFdMy(yz, lastYzFuture, "Ds", DsYz.class, repositories.dsyzRepository, repositories.dsYzDao, DsNums.FDS, DsNums.NUMS,
					new String[] { "ds0Odd", "ds0Even", "ds1Odd", "ds1Even", "ds2Odd", "ds2Even", "ds3Odd", "ds3Even", "ds4Odd", "ds4Even" },
					new String[] { "0单", "0双", "1单", "1双", "2单", "2双", "3单", "3双", "4单", "4双" });
			calFdMy(yz, lastYzFuture, "Sw", SwYz.class, repositories.swyzRepository, repositories.swYzDao, SwNums.FDS, SwNums.NUMS,
					new String[] { "w0", "w1", "w2", "w3", "w4" }, new String[] { "位0", "位1", "位2", "位3", "位4" });
			calFdMy(yz, lastYzFuture, "Mw", MwYz.class, repositories.mwyzRepository, repositories.mwYzDao, MwNums.FDS, MwNums.NUMS,
					new String[] { "w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9" },
					new String[] { "位0", "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" });
			calFdMy(yz, lastYzFuture, "Lh", LhYz.class, repositories.lhyzRepository, repositories.lhYzDao, LhNums.FDS, LhNums.NUMS,
					new String[] { "w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9" },
					new String[] { "位0", "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" });
			calFdMy(yz, lastYzFuture, "Bs", Bs9qYz.class, repositories.bs9qyzRepository, repositories.bs9qYzDao, Bs9qNums.FDS, Bs9qNums.NUMS,
					new String[] { "red1", "red2", "red3", "blue1", "blue2", "blue3", "green1", "green2", "green3" },
					new String[] { "红1", "红2", "红3", "蓝1", "蓝2", "蓝3", "绿1", "绿2", "绿3" });
			calFdMy(yz, lastYzFuture, "Zs", ZsYz.class, repositories.zsyzRepository, repositories.zsYzDao, ZsNums.FDS, ZsNums.NUMS,
					new String[] { "fd1", "fd2", "fd3", "fd4", "fd5", "fd6", "fd7", "fd8", "fd9" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" });
			calFdMy(yz, lastYzFuture, "Wx", WxYz.class, repositories.wxyzRepository, repositories.wxYzDao, WxNums.FDS, WxNums.NUMS,
					new String[] { "jin", "mu", "shui", "huo", "tu" }, new String[] { "金", "木", "水", "火", "土" });
			calFdMy(yz, lastYzFuture, "Wxds", WxdsYz.class, repositories.wxdsyzRepository, repositories.wxdsYzDao, WxDsNums.FDS, WxDsNums.NUMS,
					new String[] { "jin_odd", "jin_even", "mu_odd", "mu_even", "shui_odd", "shui_even", "huo_odd", "huo_even", "tu_odd", "tu_even" },
					new String[] { "金单", "金双", "木单", "木双", "水单", "水双", "火单", "火双", "土单", "土双" });
			calFdMy(yz, lastYzFuture, "Pd", PdYz.class, repositories.pdyzRepository, repositories.pdYzDao, PdNums.FDS, PdNums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Qq", QqYz.class, repositories.qqyzRepository, repositories.qqYzDao, QqNums.FDS, QqNums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" }, new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" });
			calFdMy(yz, lastYzFuture, "Qiw", QiwYz.class, repositories.qiwYzRepository, repositories.qiwYzDao, QiwNums.FDS, QiwNums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" }, new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" });
			calFdMy(yz, lastYzFuture, "Twelve", TwelveYz.class, repositories.twelveyzRepository, repositories.twelveYzDao, TwelveNums.FDS,
					TwelveNums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Slq", SlqYz.class, repositories.slqyzRepository, repositories.slqYzDao, SlqNums.FDS, SlqNums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" });
			calFdMy(yz, lastYzFuture, "Zx1", Zx1Yz.class, repositories.zx1yzRepository, repositories.zx1yzDao, Zx1Nums.FDS, Zx1Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx2", Zx2Yz.class, repositories.zx2yzRepository, repositories.zx2yzDao, Zx2Nums.FDS, Zx2Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx3", Zx3Yz.class, repositories.zx3yzRepository, repositories.zx3yzDao, Zx3Nums.FDS, Zx3Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx4", Zx4Yz.class, repositories.zx4yzRepository, repositories.zx4yzDao, Zx4Nums.FDS, Zx4Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx5", Zx5Yz.class, repositories.zx5yzRepository, repositories.zx5yzDao, Zx5Nums.FDS, Zx5Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx6", Zx6Yz.class, repositories.zx6yzRepository, repositories.zx6yzDao, Zx6Nums.FDS, Zx6Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx7", Zx7Yz.class, repositories.zx7yzRepository, repositories.zx7yzDao, Zx7Nums.FDS, Zx7Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10" });
			calFdMy(yz, lastYzFuture, "Zx8", Zx8Yz.class, repositories.zx8yzRepository, repositories.zx8yzDao, Zx8Nums.FDS, Zx8Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10" });
			calFdMy(yz, lastYzFuture, "Zx9", Zx9Yz.class, repositories.zx9yzRepository, repositories.zx9yzDao, Zx9Nums.FDS, Zx9Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5" }, new String[] { "位1", "位2", "位3", "位4", "位5" });
			calFdMy(yz, lastYzFuture, "Zx10", Zx10Yz.class, repositories.zx10yzRepository, repositories.zx10yzDao, Zx10Nums.FDS, Zx10Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" }, new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" });
			calFdMy(yz, lastYzFuture, "Zx11", Zx11Yz.class, repositories.zx11yzRepository, repositories.zx11yzDao, Zx11Nums.FDS, Zx11Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx12", Zx12Yz.class, repositories.zx12yzRepository, repositories.zx12yzDao, Zx12Nums.FDS, Zx12Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx13", Zx13Yz.class, repositories.zx13yzRepository, repositories.zx13yzDao, Zx13Nums.FDS, Zx13Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx14", Zx14Yz.class, repositories.zx14yzRepository, repositories.zx14yzDao, Zx14Nums.FDS, Zx14Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx15", Zx15Yz.class, repositories.zx15yzRepository, repositories.zx15yzDao, Zx15Nums.FDS, Zx15Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx16", Zx16Yz.class, repositories.zx16yzRepository, repositories.zx16yzDao, Zx16Nums.FDS, Zx16Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy(yz, lastYzFuture, "Zx17", Zx17Yz.class, repositories.zx17yzRepository, repositories.zx17yzDao, Zx17Nums.FDS, Zx17Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" });
			calFdMy(yz, lastYzFuture, "Zx18", Zx18Yz.class, repositories.zx18yzRepository, repositories.zx18yzDao, Zx18Nums.FDS, Zx18Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" });
			calFdMy(yz, lastYzFuture);
			repositories.my1Repository.save(yz);
		} catch (Exception e) {
			return new AsyncResult<Object>(e);
		}
		return new AsyncResult<Object>(yz);
	}
	
	
	private volatile int count;

	@Async
	public Future<Object> doCalMy2InSync(KaiJiang kj, Future<Object> lastYzFuture, int i) {
		My2Yz temp = repositories.my2Repository.findByYearAndPhase(kj.getYear(), kj.getPhase());
		if (temp == null) {
			temp = new My2Yz();
			temp.setDate(kj.getDate());
			temp.setYear(kj.getYear());
			temp.setPhase(kj.getPhase());
		}
		final My2Yz yz = temp;
		try {
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			synchronized (this) {
				futures.add(repositories.yzService.calSxMy2(yz, lastYzFuture));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Ds", DsYz.class, repositories.dsyzRepository, repositories.dsYzDao, DsNums.FDS, DsNums.NUMS,
						new String[] { "ds0Odd", "ds0Even", "ds1Odd", "ds1Even", "ds2Odd", "ds2Even", "ds3Odd", "ds3Even", "ds4Odd", "ds4Even" },
						new String[] { "0单", "0双", "1单", "1双", "2单", "2双", "3单", "3双", "4单", "4双" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Sw", SwYz.class, repositories.swyzRepository, repositories.swYzDao, SwNums.FDS, SwNums.NUMS,
						new String[] { "w0", "w1", "w2", "w3", "w4" }, new String[] { "位0", "位1", "位2", "位3", "位4" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Mw", MwYz.class, repositories.mwyzRepository, repositories.mwYzDao, MwNums.FDS, MwNums.NUMS,
						new String[] { "w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9" },
						new String[] { "位0", "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Lh", LhYz.class, repositories.lhyzRepository, repositories.lhYzDao, LhNums.FDS, LhNums.NUMS,
						new String[] { "w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9" },
						new String[] { "位0", "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Bs", Bs9qYz.class, repositories.bs9qyzRepository, repositories.bs9qYzDao, Bs9qNums.FDS,
						Bs9qNums.NUMS, new String[] { "red1", "red2", "red3", "blue1", "blue2", "blue3", "green1", "green2", "green3" },
						new String[] { "红1", "红2", "红3", "蓝1", "蓝2", "蓝3", "绿1", "绿2", "绿3" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zs", ZsYz.class, repositories.zsyzRepository, repositories.zsYzDao, ZsNums.FDS, ZsNums.NUMS,
						new String[] { "fd1", "fd2", "fd3", "fd4", "fd5", "fd6", "fd7", "fd8", "fd9" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Wx", WxYz.class, repositories.wxyzRepository, repositories.wxYzDao, WxNums.FDS, WxNums.NUMS,
						new String[] { "jin", "mu", "shui", "huo", "tu" }, new String[] { "金", "木", "水", "火", "土" }));
				futures.add(
						repositories.yzService.calFdMy2(yz, lastYzFuture, "Wxds", WxdsYz.class, repositories.wxdsyzRepository, repositories.wxdsYzDao, WxDsNums.FDS,
								WxDsNums.NUMS, new String[] { "jin_odd", "jin_even", "mu_odd", "mu_even", "shui_odd", "shui_even", "huo_odd",
										"huo_even", "tu_odd", "tu_even" },
								new String[] { "金单", "金双", "木单", "木双", "水单", "水双", "火单", "火双", "土单", "土双" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Pd", PdYz.class, repositories.pdyzRepository, repositories.pdYzDao, PdNums.FDS, PdNums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Qq", QqYz.class, repositories.qqyzRepository, repositories.qqYzDao, QqNums.FDS, QqNums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" }, new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Qiw", QiwYz.class, repositories.qiwYzRepository, repositories.qiwYzDao, QiwNums.FDS,
						QiwNums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Twelve", TwelveYz.class, repositories.twelveyzRepository, repositories.twelveYzDao,
						TwelveNums.FDS, TwelveNums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Slq", SlqYz.class, repositories.slqyzRepository, repositories.slqYzDao, SlqNums.FDS,
						SlqNums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx1", Zx1Yz.class, repositories.zx1yzRepository, repositories.zx1yzDao, Zx1Nums.FDS,
						Zx1Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx2", Zx2Yz.class, repositories.zx2yzRepository, repositories.zx2yzDao, Zx2Nums.FDS,
						Zx2Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx3", Zx3Yz.class, repositories.zx3yzRepository, repositories.zx3yzDao, Zx3Nums.FDS,
						Zx3Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx4", Zx4Yz.class, repositories.zx4yzRepository, repositories.zx4yzDao, Zx4Nums.FDS,
						Zx4Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx5", Zx5Yz.class, repositories.zx5yzRepository, repositories.zx5yzDao, Zx5Nums.FDS,
						Zx5Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx6", Zx6Yz.class, repositories.zx6yzRepository, repositories.zx6yzDao, Zx6Nums.FDS,
						Zx6Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx7", Zx7Yz.class, repositories.zx7yzRepository, repositories.zx7yzDao, Zx7Nums.FDS,
						Zx7Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx8", Zx8Yz.class, repositories.zx8yzRepository, repositories.zx8yzDao, Zx8Nums.FDS,
						Zx8Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx9", Zx9Yz.class, repositories.zx9yzRepository, repositories.zx9yzDao, Zx9Nums.FDS,
						Zx9Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5" }, new String[] { "位1", "位2", "位3", "位4", "位5" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx10", Zx10Yz.class, repositories.zx10yzRepository, repositories.zx10yzDao, Zx10Nums.FDS,
						Zx10Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx11", Zx11Yz.class, repositories.zx11yzRepository, repositories.zx11yzDao, Zx11Nums.FDS,
						Zx11Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx12", Zx12Yz.class, repositories.zx12yzRepository, repositories.zx12yzDao, Zx12Nums.FDS,
						Zx12Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx13", Zx13Yz.class, repositories.zx13yzRepository, repositories.zx13yzDao, Zx13Nums.FDS,
						Zx13Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx14", Zx14Yz.class, repositories.zx14yzRepository, repositories.zx14yzDao, Zx14Nums.FDS,
						Zx14Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx15", Zx15Yz.class, repositories.zx15yzRepository, repositories.zx15yzDao, Zx15Nums.FDS,
						Zx15Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx16", Zx16Yz.class, repositories.zx16yzRepository, repositories.zx16yzDao, Zx16Nums.FDS,
						Zx16Nums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx17", Zx17Yz.class, repositories.zx17yzRepository, repositories.zx17yzDao, Zx17Nums.FDS,
						Zx17Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture, "Zx18", Zx18Yz.class, repositories.zx18yzRepository, repositories.zx18yzDao, Zx18Nums.FDS,
						Zx18Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" }));
				futures.add(repositories.yzService.calFdMy2(yz, lastYzFuture));
				count++;
			}
			CommonUtil.sleep(futures, 10);
			repositories.my2Repository.save(yz);
		} catch (Exception e) {
			return new AsyncResult<Object>(e);
		}
		return new AsyncResult<Object>(yz);
	}

	@Async
	public Future<Object> doCalMy200InSync(KaiJiang kj, Future<Object> lastYzFuture) {
		My200Yz temp = repositories.my200Repository.findByYearAndPhase(kj.getYear(), kj.getPhase());
		if (temp == null) {
			temp = new My200Yz();
			temp.setDate(kj.getDate());
			temp.setYear(kj.getYear());
			temp.setPhase(kj.getPhase());
		}
		final My200Yz yz = temp;
		try {
			calSxMy200(yz, lastYzFuture);
			calFdMy200(yz, lastYzFuture, "Ds", DsYz.class, repositories.dsyzRepository, repositories.dsYzDao, DsNums.FDS, DsNums.NUMS,
					new String[] { "ds0Odd", "ds0Even", "ds1Odd", "ds1Even", "ds2Odd", "ds2Even", "ds3Odd", "ds3Even", "ds4Odd", "ds4Even" },
					new String[] { "0单", "0双", "1单", "1双", "2单", "2双", "3单", "3双", "4单", "4双" });
			calFdMy200(yz, lastYzFuture, "Sw", SwYz.class, repositories.swyzRepository, repositories.swYzDao, SwNums.FDS, SwNums.NUMS,
					new String[] { "w0", "w1", "w2", "w3", "w4" }, new String[] { "位0", "位1", "位2", "位3", "位4" });
			calFdMy200(yz, lastYzFuture, "Mw", MwYz.class, repositories.mwyzRepository, repositories.mwYzDao, MwNums.FDS, MwNums.NUMS,
					new String[] { "w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9" },
					new String[] { "位0", "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" });
			calFdMy200(yz, lastYzFuture, "Lh", LhYz.class, repositories.lhyzRepository, repositories.lhYzDao, LhNums.FDS, LhNums.NUMS,
					new String[] { "w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9" },
					new String[] { "位0", "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" });
			calFdMy200(yz, lastYzFuture, "Bs", Bs9qYz.class, repositories.bs9qyzRepository, repositories.bs9qYzDao, Bs9qNums.FDS, Bs9qNums.NUMS,
					new String[] { "red1", "red2", "red3", "blue1", "blue2", "blue3", "green1", "green2", "green3" },
					new String[] { "红1", "红2", "红3", "蓝1", "蓝2", "蓝3", "绿1", "绿2", "绿3" });
			calFdMy200(yz, lastYzFuture, "Zs", ZsYz.class, repositories.zsyzRepository, repositories.zsYzDao, ZsNums.FDS, ZsNums.NUMS,
					new String[] { "fd1", "fd2", "fd3", "fd4", "fd5", "fd6", "fd7", "fd8", "fd9" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" });
			calFdMy200(yz, lastYzFuture, "Wx", WxYz.class, repositories.wxyzRepository, repositories.wxYzDao, WxNums.FDS, WxNums.NUMS,
					new String[] { "jin", "mu", "shui", "huo", "tu" }, new String[] { "金", "木", "水", "火", "土" });
			calFdMy200(yz, lastYzFuture, "Wxds", WxdsYz.class, repositories.wxdsyzRepository, repositories.wxdsYzDao, WxDsNums.FDS, WxDsNums.NUMS,
					new String[] { "jin_odd", "jin_even", "mu_odd", "mu_even", "shui_odd", "shui_even", "huo_odd", "huo_even", "tu_odd", "tu_even" },
					new String[] { "金单", "金双", "木单", "木双", "水单", "水双", "火单", "火双", "土单", "土双" });
			calFdMy200(yz, lastYzFuture, "Pd", PdYz.class, repositories.pdyzRepository, repositories.pdYzDao, PdNums.FDS, PdNums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Qq", QqYz.class, repositories.qqyzRepository, repositories.qqYzDao, QqNums.FDS, QqNums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" }, new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" });
			calFdMy200(yz, lastYzFuture, "Qiw", QiwYz.class, repositories.qiwYzRepository, repositories.qiwYzDao, QiwNums.FDS, QiwNums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" }, new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" });
			calFdMy200(yz, lastYzFuture, "Twelve", TwelveYz.class, repositories.twelveyzRepository, repositories.twelveYzDao, TwelveNums.FDS,
					TwelveNums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Slq", SlqYz.class, repositories.slqyzRepository, repositories.slqYzDao, SlqNums.FDS, SlqNums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" });
			calFdMy200(yz, lastYzFuture, "Zx1", Zx1Yz.class, repositories.zx1yzRepository, repositories.zx1yzDao, Zx1Nums.FDS, Zx1Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx2", Zx2Yz.class, repositories.zx2yzRepository, repositories.zx2yzDao, Zx2Nums.FDS, Zx2Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx3", Zx3Yz.class, repositories.zx3yzRepository, repositories.zx3yzDao, Zx3Nums.FDS, Zx3Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx4", Zx4Yz.class, repositories.zx4yzRepository, repositories.zx4yzDao, Zx4Nums.FDS, Zx4Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx5", Zx5Yz.class, repositories.zx5yzRepository, repositories.zx5yzDao, Zx5Nums.FDS, Zx5Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx6", Zx6Yz.class, repositories.zx6yzRepository, repositories.zx6yzDao, Zx6Nums.FDS, Zx6Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx7", Zx7Yz.class, repositories.zx7yzRepository, repositories.zx7yzDao, Zx7Nums.FDS, Zx7Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10" });
			calFdMy200(yz, lastYzFuture, "Zx8", Zx8Yz.class, repositories.zx8yzRepository, repositories.zx8yzDao, Zx8Nums.FDS, Zx8Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10" });
			calFdMy200(yz, lastYzFuture, "Zx9", Zx9Yz.class, repositories.zx9yzRepository, repositories.zx9yzDao, Zx9Nums.FDS, Zx9Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5" }, new String[] { "位1", "位2", "位3", "位4", "位5" });
			calFdMy200(yz, lastYzFuture, "Zx10", Zx10Yz.class, repositories.zx10yzRepository, repositories.zx10yzDao, Zx10Nums.FDS, Zx10Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" }, new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" });
			calFdMy200(yz, lastYzFuture, "Zx11", Zx11Yz.class, repositories.zx11yzRepository, repositories.zx11yzDao, Zx11Nums.FDS, Zx11Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx12", Zx12Yz.class, repositories.zx12yzRepository, repositories.zx12yzDao, Zx12Nums.FDS, Zx12Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx13", Zx13Yz.class, repositories.zx13yzRepository, repositories.zx13yzDao, Zx13Nums.FDS, Zx13Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx14", Zx14Yz.class, repositories.zx14yzRepository, repositories.zx14yzDao, Zx14Nums.FDS, Zx14Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx15", Zx15Yz.class, repositories.zx15yzRepository, repositories.zx15yzDao, Zx15Nums.FDS, Zx15Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx16", Zx16Yz.class, repositories.zx16yzRepository, repositories.zx16yzDao, Zx16Nums.FDS, Zx16Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			calFdMy200(yz, lastYzFuture, "Zx17", Zx17Yz.class, repositories.zx17yzRepository, repositories.zx17yzDao, Zx17Nums.FDS, Zx17Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" });
			calFdMy200(yz, lastYzFuture, "Zx18", Zx18Yz.class, repositories.zx18yzRepository, repositories.zx18yzDao, Zx18Nums.FDS, Zx18Nums.NUMS,
					new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
					new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" });
			calFdMy200(yz, lastYzFuture);
			repositories.my200Repository.save(yz);
		} catch (Exception e) {
			return new AsyncResult<Object>(e);
		}
		return new AsyncResult<Object>(yz);
	}

	@Async
	public Future<Object> doCalMy1InAsync(KaiJiang kj, Future<Object> lastYzFuture) {
		My1Yz temp = repositories.my1Repository.findByYearAndPhase(kj.getYear(), kj.getPhase());
		if (temp == null) {
			temp = new My1Yz();
			temp.setDate(kj.getDate());
			temp.setYear(kj.getYear());
			temp.setPhase(kj.getPhase());
		}
		final My1Yz yz = temp;
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		try {
			executor.initialize();
			executor.execute(() -> {
				calSxMy(yz, lastYzFuture);
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Ds", DsYz.class, repositories.dsyzRepository, repositories.dsYzDao, DsNums.FDS, DsNums.NUMS,
						new String[] { "ds0Odd", "ds0Even", "ds1Odd", "ds1Even", "ds2Odd", "ds2Even", "ds3Odd", "ds3Even", "ds4Odd", "ds4Even" },
						new String[] { "0单", "0双", "1单", "1双", "2单", "2双", "3单", "3双", "4单", "4双" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Sw", SwYz.class, repositories.swyzRepository, repositories.swYzDao, SwNums.FDS, SwNums.NUMS,
						new String[] { "w0", "w1", "w2", "w3", "w4" }, new String[] { "位0", "位1", "位2", "位3", "位4" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Mw", MwYz.class, repositories.mwyzRepository, repositories.mwYzDao, MwNums.FDS, MwNums.NUMS,
						new String[] { "w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9" },
						new String[] { "位0", "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Lh", LhYz.class, repositories.lhyzRepository, repositories.lhYzDao, LhNums.FDS, LhNums.NUMS,
						new String[] { "w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9" },
						new String[] { "位0", "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Bs", Bs9qYz.class, repositories.bs9qyzRepository, repositories.bs9qYzDao, Bs9qNums.FDS, Bs9qNums.NUMS,
						new String[] { "red1", "red2", "red3", "blue1", "blue2", "blue3", "green1", "green2", "green3" },
						new String[] { "红1", "红2", "红3", "蓝1", "蓝2", "蓝3", "绿1", "绿2", "绿3" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zs", ZsYz.class, repositories.zsyzRepository, repositories.zsYzDao, ZsNums.FDS, ZsNums.NUMS,
						new String[] { "fd1", "fd2", "fd3", "fd4", "fd5", "fd6", "fd7", "fd8", "fd9" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Wx", WxYz.class, repositories.wxyzRepository, repositories.wxYzDao, WxNums.FDS, WxNums.NUMS,
						new String[] { "jin", "mu", "shui", "huo", "tu" }, new String[] { "金", "木", "水", "火", "土" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Wxds", WxdsYz.class, repositories.wxdsyzRepository, repositories.wxdsYzDao, WxDsNums.FDS, WxDsNums.NUMS,
						new String[] { "jin_odd", "jin_even", "mu_odd", "mu_even", "shui_odd", "shui_even", "huo_odd", "huo_even", "tu_odd",
								"tu_even" },
						new String[] { "金单", "金双", "木单", "木双", "水单", "水双", "火单", "火双", "土单", "土双" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Pd", PdYz.class, repositories.pdyzRepository, repositories.pdYzDao, PdNums.FDS, PdNums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Qq", QqYz.class, repositories.qqyzRepository, repositories.qqYzDao, QqNums.FDS, QqNums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" }, new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Qiw", QiwYz.class, repositories.qiwYzRepository, repositories.qiwYzDao, QiwNums.FDS, QiwNums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" }, new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Twelve", TwelveYz.class, repositories.twelveyzRepository, repositories.twelveYzDao, TwelveNums.FDS,
						TwelveNums.NUMS, new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Slq", SlqYz.class, repositories.slqyzRepository, repositories.slqYzDao, SlqNums.FDS, SlqNums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx1", Zx1Yz.class, repositories.zx1yzRepository, repositories.zx1yzDao, Zx1Nums.FDS, Zx1Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx2", Zx2Yz.class, repositories.zx2yzRepository, repositories.zx2yzDao, Zx2Nums.FDS, Zx2Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx3", Zx3Yz.class, repositories.zx3yzRepository, repositories.zx3yzDao, Zx3Nums.FDS, Zx3Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx4", Zx4Yz.class, repositories.zx4yzRepository, repositories.zx4yzDao, Zx4Nums.FDS, Zx4Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx5", Zx5Yz.class, repositories.zx5yzRepository, repositories.zx5yzDao, Zx5Nums.FDS, Zx5Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx6", Zx6Yz.class, repositories.zx6yzRepository, repositories.zx6yzDao, Zx6Nums.FDS, Zx6Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx7", Zx7Yz.class, repositories.zx7yzRepository, repositories.zx7yzDao, Zx7Nums.FDS, Zx7Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx8", Zx8Yz.class, repositories.zx8yzRepository, repositories.zx8yzDao, Zx8Nums.FDS, Zx8Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx9", Zx9Yz.class, repositories.zx9yzRepository, repositories.zx9yzDao, Zx9Nums.FDS, Zx9Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5" }, new String[] { "位1", "位2", "位3", "位4", "位5" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx10", Zx10Yz.class, repositories.zx10yzRepository, repositories.zx10yzDao, Zx10Nums.FDS, Zx10Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7" }, new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx11", Zx11Yz.class, repositories.zx11yzRepository, repositories.zx11yzDao, Zx11Nums.FDS, Zx11Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx12", Zx12Yz.class, repositories.zx12yzRepository, repositories.zx12yzDao, Zx12Nums.FDS, Zx12Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx13", Zx13Yz.class, repositories.zx13yzRepository, repositories.zx13yzDao, Zx13Nums.FDS, Zx13Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx14", Zx14Yz.class, repositories.zx14yzRepository, repositories.zx14yzDao, Zx14Nums.FDS, Zx14Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx15", Zx15Yz.class, repositories.zx15yzRepository, repositories.zx15yzDao, Zx15Nums.FDS, Zx15Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx16", Zx16Yz.class, repositories.zx16yzRepository, repositories.zx16yzDao, Zx16Nums.FDS, Zx16Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx17", Zx17Yz.class, repositories.zx17yzRepository, repositories.zx17yzDao, Zx17Nums.FDS, Zx17Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture, "Zx18", Zx18Yz.class, repositories.zx18yzRepository, repositories.zx18yzDao, Zx18Nums.FDS, Zx18Nums.NUMS,
						new String[] { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16" },
						new String[] { "位1", "位2", "位3", "位4", "位5", "位6", "位7", "位8", "位9", "位10", "位11", "位12", "位13", "位14", "位15", "位16" });
			});
			executor.execute(() -> {
				calFdMy(yz, lastYzFuture);
			});
			while (executor.getActiveCount() > 0) {
				Thread.sleep(100);
			}
			repositories.my1Repository.save(yz);
		} catch (Exception e) {
			return new AsyncResult<Object>(e);
		} finally {
			executor.shutdown();
		}
		return new AsyncResult<Object>(yz);
	}

	public void calSxMy(My1Yz yz, Future<Object> lastYzFuture) {
		try {
			SxYz sxyz = repositories.sxyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
			if (sxyz == null) {
				return;
			}
			SX sx = sxyz.getCurrentSx();
			if (sx != null) {
				SxYz lastYz = repositories.sxYzDao.getLastYzWithSamePos(sxyz, sx.getColumn());
				if (lastYz != null) {
					SxYz nextYz = repositories.sxyzRepository.findOne(lastYz.getId() + 1);
					if (nextYz != null && nextYz.getCurrentSx() != null) {
						yz.setSxmy(nextYz.getCurrentSx().getText());
						yz.setSxNums(Joiner.on(",").join(getSxNums(DateUtil.getSxByYear((Date) null), nextYz.getCurrentSx())));
						Integer currentYz = null;

						lastYz = repositories.sxyzRepository.findOne(sxyz.getId() - 1);
						if (lastYz == null || lastYz.getCurrentSx() == null) {
							return;
						}
						String sxs = lastYz.getCurrentSx().name() + sxyz.getCurrentSx().name();
						lastYz = repositories.sxYzDao.getLastYzWithSamePos(lastYz, lastYz.getCurrentSx().getColumn());
						if (lastYz == null || lastYz.getCurrentSx() == null) {
							return;
						}
						nextYz = repositories.sxyzRepository.findOne(lastYz.getId() + 1);
						if (nextYz == null || nextYz.getCurrentSx() == null) {
							return;
						}
						String compareSxs = lastYz.getCurrentSx().name() + nextYz.getCurrentSx().name();
						if (sxs.equals(compareSxs)) {
							currentYz = 0;
						}
						if (currentYz == null && lastYzFuture != null) {
							while (true) {
								if (lastYzFuture.isDone()) {
									if (lastYzFuture.get() != null && lastYzFuture.get() instanceof My1Yz) {
										My1Yz temp = (My1Yz) lastYzFuture.get();
										if (lastYzFuture.get() != null && temp.getSxyz() != null) {
											currentYz = temp.getSxyz() + 1;
										}
									}
									break;
								} else {
									Thread.sleep(100);
								}
							}
						}
						yz.setSxyz(currentYz);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Async("subExecutor")
	public Future<Exception> calSxMy2(My2Yz yz, Future<Object> lastYzFuture) {
		Exception t = null;
		Future<Exception> result = new AsyncResult<Exception>(null);
		try {
			SxYz sxyz = repositories.sxyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
			if (sxyz == null) {
				return result;
			}
			SX sx = sxyz.getCurrentSx();
			if (sx != null) {
				SxYz lastYz = repositories.sxyzRepository.findOne(sxyz.getId() - 1);
				if (lastYz == null) {
					return result;
				}
				List<SxYz> compareList = repositories.sxYzDao.getLastYzListWithSamePos(lastYz, lastYz.getCurrentSx().getColumn());
				if (compareList == null || compareList.isEmpty()) {
					return result;
				}
				for (SxYz data : compareList) {
					lastYz = repositories.sxyzRepository.findOne(data.getId() + 1);
					if (lastYz == null) {
						continue;
					}
					if (lastYz.getCurrentSx() != null && lastYz.getCurrentSx().equals(sxyz.getCurrentSx())) {
						break;
					}
					lastYz = null;
				}
				if (lastYz != null) {
					SxYz nextYz = repositories.sxyzRepository.findOne(lastYz.getId() + 1);
					if (nextYz != null && nextYz.getCurrentSx() != null) {
						yz.setSxmy(nextYz.getCurrentSx().getText());
						yz.setSxNums(Joiner.on(",").join(getSxNums(DateUtil.getSxByYear((Date) null), nextYz.getCurrentSx())));

						Integer currentYz = null;
						String sxs = lastYz.getCurrentSx().name() + sxyz.getCurrentSx().name();
						SxYz lastYz1 = lastYz;
						lastYz = repositories.sxyzRepository.findOne(sxyz.getId() - 2);
						if (lastYz == null || lastYz.getCurrentSx() == null) {
							return result;
						}
						sxs = lastYz.getCurrentSx().name() + sxs;

						compareList = repositories.sxYzDao.getLastYzListWithSamePos(lastYz, lastYz.getCurrentSx().getColumn());
						if (compareList == null || compareList.isEmpty()) {
							return result;
						}
						String compareSxs = null;
						for (SxYz data : compareList) {
							if (data.getCurrentSx() == null) {
								continue;
							}
							compareSxs = data.getCurrentSx().name();
							SxYz temp = repositories.sxyzRepository.findOne(data.getId() + 1);
							if (temp == null || temp.getCurrentSx() == null) {
								continue;
							}
							if (temp.getCurrentSx().equals(lastYz1.getCurrentSx())) {
								compareSxs += temp.getCurrentSx().name();
								nextYz = repositories.sxyzRepository.findOne(temp.getId() + 1);
								if (nextYz == null || nextYz.getCurrentSx() == null) {
									continue;
								}
								compareSxs += nextYz.getCurrentSx().name();
								break;
							}
						}
						if (sxs.equals(compareSxs)) {
							currentYz = 0;
						}

						if (currentYz == null && lastYzFuture != null) {
							while (true) {
								if (lastYzFuture.isDone()) {
									if (lastYzFuture.get() != null && lastYzFuture.get() instanceof My2Yz) {
										My2Yz temp = (My2Yz) lastYzFuture.get();
										if (lastYzFuture.get() != null && temp.getSxyz() != null) {
											currentYz = temp.getSxyz() + 1;
										}
									}
									break;
								} else {
									Thread.sleep(100);
								}
							}
						}
						yz.setSxyz(currentYz);
					}
				}
			}
		} catch (Exception e) {
			// logger.error(e.getMessage(), e);
			// throw new RuntimeException(e.getMessage(), e);
			t = e;
			return new AsyncResult<Exception>(t);
		}
		return result;
	}

	public void calSxMy200(My200Yz yz, Future<Object> lastYzFuture) {
		try {
			SxYz sxyz = repositories.sxyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
			if (sxyz == null) {
				return;
			}
			SX sx = sxyz.getCurrentSx();
			if (sx != null) {
				SxYz lastYz = repositories.sxYzDao.getLastYzIn(sxyz.getId(), 200);
				if (lastYz != null) {
					SxYz nextYz = repositories.sxyzRepository.findOne(lastYz.getId() + 1);
					if (nextYz != null && nextYz.getCurrentSx() != null) {
						yz.setSxmy(nextYz.getCurrentSx().getText());
						yz.setSxNums(Joiner.on(",").join(getSxNums(DateUtil.getSxByYear((Date) null), nextYz.getCurrentSx())));
						Integer currentYz = null;

						String sxs = sxyz.getCurrentSx().name();
						String compareSxs = lastYz.getCurrentSx().name();
						if (sxs.equals(compareSxs)) {
							currentYz = 0;
						}
						if (currentYz == null && lastYzFuture != null) {
							while (true) {
								if (lastYzFuture.isDone()) {
									if (lastYzFuture.get() != null && lastYzFuture.get() instanceof My200Yz) {
										My200Yz temp = (My200Yz) lastYzFuture.get();
										if (lastYzFuture.get() != null && temp.getSxyz() != null) {
											currentYz = temp.getSxyz() + 1;
										}
									}
									break;
								} else {
									Thread.sleep(100);
								}
							}
						}
						yz.setSxyz(currentYz);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public void calFdMy(My1Yz yz, Future<Object> lastYzFuture) {
		try {
			Tm12FdYz tm12yz = repositories.tm12fdyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
			if (tm12yz == null) {
				return;
			}
			int pos0 = 0;
			String[] fds = { "W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10", "W11", "W12" };
			String[] columns = { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" };
			for (String fd : fds) {
				Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
				Integer value = (Integer) gm.invoke(tm12yz);
				if (value != null && 0 == value) {
					break;
				}
				pos0++;
			}
			if (pos0 < fds.length) {
				Tm12FdYz lastYz = repositories.tm12fdYzDao.getLastYzWithSamePos(tm12yz, columns[pos0]);
				if (lastYz != null) {
					Tm12FdYz nextYz = repositories.tm12fdyzRepository.findOne(lastYz.getId() + 1);
					if (nextYz != null) {
						int pos1 = 0;
						for (String fd : fds) {
							Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
							Integer value = (Integer) gm.invoke(nextYz);
							if (value != null && 0 == value) {
								break;
							}
							pos1++;
						}
						if (nextYz != null && pos1 < fds.length) {
							yz.setFdmy("位" + (pos1 + 1));
							TmYz tmyz = repositories.tmyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
							List<TmYzInfo> infos = getTMFDList(tmyz, true);
							int len = 4 * (pos1 + 1);
							if (pos1 == 11) {
								len = 49;
							}
							List<Integer> nums = new ArrayList<Integer>();
							for (int i = pos1 * 4; i < len; i++) {
								nums.add(infos.get(i).getNum());
							}
							yz.setFdNums(Joiner.on(",").join(nums));
							Integer currentYz = null;

							lastYz = repositories.tm12fdyzRepository.findOne(tm12yz.getId() - 1);
							if (lastYz == null) {
								return;
							}
							pos1 = 0;
							for (String fd : fds) {
								Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
								Integer value = (Integer) gm.invoke(lastYz);
								if (value != null && 0 == value) {
									break;
								}
								pos1++;
							}
							String sxs = "" + pos1 + pos0;
							lastYz = repositories.tm12fdYzDao.getLastYzWithSamePos(lastYz, columns[pos1]);
							if (lastYz == null) {
								return;
							}
							nextYz = repositories.tm12fdyzRepository.findOne(lastYz.getId() + 1);
							if (nextYz == null) {
								return;
							}
							pos1 = 0;
							for (String fd : fds) {
								Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
								Integer value = (Integer) gm.invoke(lastYz);
								if (value != null && 0 == value) {
									break;
								}
								pos1++;
							}
							pos0 = 0;
							for (String fd : fds) {
								Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
								Integer value = (Integer) gm.invoke(nextYz);
								if (value != null && 0 == value) {
									break;
								}
								pos0++;
							}
							String compareSxs = "" + pos1 + pos0;
							if (sxs.equals(compareSxs)) {
								currentYz = 0;
							}
							if (currentYz == null && lastYzFuture != null) {
								while (true) {
									if (lastYzFuture.isDone()) {
										if (lastYzFuture.get() != null && lastYzFuture.get() instanceof My1Yz) {
											My1Yz temp = (My1Yz) lastYzFuture.get();
											if (lastYzFuture.get() != null && temp.getSxyz() != null) {
												currentYz = temp.getSxyz() + 1;
											}
										}
										break;
									} else {
										Thread.sleep(100);
									}
								}
							}
							yz.setFdyz(currentYz);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Async("subExecutor")
	public Future<Exception> calFdMy2(My2Yz yz, Future<Object> lastYzFuture) {
		Exception t = null;
		Future<Exception> result = new AsyncResult<Exception>(null);

		try {
			Tm12FdYz tm12yz = repositories.tm12fdyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
			if (tm12yz == null) {
				return result;
			}
			int pos0 = 0;
			String[] fds = { "W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10", "W11", "W12" };
			String[] columns = { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12" };
			for (String fd : fds) {
				Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
				Integer value = (Integer) gm.invoke(tm12yz);
				if (value != null && 0 == value) {
					break;
				}
				pos0++;
			}
			if (pos0 < fds.length) {
				Tm12FdYz lastYz = repositories.tm12fdyzRepository.findOne(tm12yz.getId() - 1);
				if (lastYz == null) {
					return result;
				}
				int pos1 = 0;
				for (String fd : fds) {
					Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
					Integer value = (Integer) gm.invoke(lastYz);
					if (value != null && 0 == value) {
						break;
					}
					pos1++;
				}
				List<Tm12FdYz> compareList = repositories.tm12fdYzDao.getLastYzListWithSamePos(lastYz, columns[pos1]);
				if (compareList == null || compareList.isEmpty()) {
					return result;
				}
				for (Tm12FdYz data : compareList) {
					lastYz = repositories.tm12fdyzRepository.findOne(data.getId() + 1);
					if (lastYz == null) {
						continue;
					}
					int tempPos = 0;
					for (String fd : fds) {
						Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
						Integer value = (Integer) gm.invoke(lastYz);
						if (value != null && 0 == value) {
							break;
						}
						tempPos++;
					}
					if (tempPos == pos0) {
						break;
					}
					lastYz = null;
				}
				if (lastYz != null) {
					Tm12FdYz nextYz = repositories.tm12fdyzRepository.findOne(lastYz.getId() + 1);
					if (nextYz != null) {
						int pos2 = 0;
						for (String fd : fds) {
							Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
							Integer value = (Integer) gm.invoke(nextYz);
							if (value != null && 0 == value) {
								break;
							}
							pos2++;
						}
						if (nextYz != null && pos2 < fds.length) {
							yz.setFdmy("位" + (pos2 + 1));
							TmYz tmyz = repositories.tmyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
							List<TmYzInfo> infos = getTMFDList(tmyz, true);
							int len = 4 * (pos2 + 1);
							if (pos2 == 11) {
								len = 49;
							}
							List<Integer> nums = new ArrayList<Integer>();
							for (int i = pos2 * 4; i < len; i++) {
								nums.add(infos.get(i).getNum());
							}
							yz.setFdNums(Joiner.on(",").join(nums));
							Integer currentYz = null;

							String sxs = "" + pos1 + pos0;
							lastYz = repositories.tm12fdyzRepository.findOne(tm12yz.getId() - 2);
							if (lastYz == null) {
								return result;
							}
							int pos3 = 0;
							for (String fd : fds) {
								Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
								Integer value = (Integer) gm.invoke(lastYz);
								if (value != null && 0 == value) {
									break;
								}
								pos3++;
							}
							if (pos3 == fds.length) {
								return result;
							}
							sxs = pos3 + sxs;

							compareList = repositories.tm12fdYzDao.getLastYzListWithSamePos(lastYz, columns[pos3]);
							if (compareList == null || compareList.isEmpty()) {
								return result;
							}
							String compareSxs = null;
							for (Tm12FdYz data : compareList) {
								int pos = 0;
								for (String fd : fds) {
									Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
									Integer value = (Integer) gm.invoke(data);
									if (value != null && 0 == value) {
										break;
									}
									pos++;
								}
								if (pos == fds.length) {
									continue;
								}
								compareSxs = "" + pos;
								Tm12FdYz temp = repositories.tm12fdyzRepository.findOne(data.getId() + 1);
								if (temp == null) {
									continue;
								}
								pos = 0;
								for (String fd : fds) {
									Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
									Integer value = (Integer) gm.invoke(temp);
									if (value != null && 0 == value) {
										break;
									}
									pos++;
								}
								if (pos == fds.length) {
									continue;
								}
								if (pos == pos1) {
									compareSxs += pos;
									nextYz = repositories.tm12fdyzRepository.findOne(temp.getId() + 1);
									if (nextYz == null) {
										continue;
									}
									pos = 0;
									for (String fd : fds) {
										Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
										Integer value = (Integer) gm.invoke(nextYz);
										if (value != null && 0 == value) {
											break;
										}
										pos++;
									}
									if (pos == fds.length) {
										continue;
									}
									compareSxs += pos;
									break;
								}
							}
							if (sxs.equals(compareSxs)) {
								currentYz = 0;
							}

							if (currentYz == null && lastYzFuture != null) {
								while (true) {
									if (lastYzFuture.isDone()) {
										if (lastYzFuture.get() != null && lastYzFuture.get() instanceof My2Yz) {
											My2Yz temp = (My2Yz) lastYzFuture.get();
											if (lastYzFuture.get() != null && temp.getSxyz() != null) {
												currentYz = temp.getSxyz() + 1;
											}
										}
										break;
									} else {
										Thread.sleep(100);
									}
								}
							}
							yz.setFdyz(currentYz);
						}
					}
				}
			}
		} catch (Exception e) {
			// logger.error(e.getMessage(), e);
			// throw new RuntimeException(e.getMessage(), e);
			t = e;
			return new AsyncResult<Exception>(t);
		}
		return result;
	}

	public void calFdMy200(My200Yz yz, Future<Object> lastYzFuture) {
		try {
			Tm12FdYz tm12yz = repositories.tm12fdyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
			if (tm12yz == null) {
				return;
			}
			int pos0 = 0;
			String[] fds = { "W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10", "W11", "W12" };
			for (String fd : fds) {
				Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
				Integer value = (Integer) gm.invoke(tm12yz);
				if (value != null && 0 == value) {
					break;
				}
				pos0++;
			}
			if (pos0 < fds.length) {
				Tm12FdYz lastYz = repositories.tm12fdYzDao.getLastYzIn(tm12yz.getId(), 200);
				if (lastYz != null) {
					int pos1 = 0;
					for (String fd : fds) {
						Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
						Integer value = (Integer) gm.invoke(lastYz);
						if (value != null && 0 == value) {
							break;
						}
						pos1++;
					}
					Tm12FdYz nextYz = repositories.tm12fdyzRepository.findOne(lastYz.getId() + 1);
					if (nextYz != null) {
						if (nextYz != null && pos1 < fds.length) {
							yz.setFdmy("位" + (pos1 + 1));
							TmYz tmyz = repositories.tmyzRepository.findByYearAndPhase(yz.getYear(), yz.getPhase());
							List<TmYzInfo> infos = getTMFDList(tmyz, true);
							int len = 4 * (pos1 + 1);
							if (pos1 == 11) {
								len = 49;
							}
							List<Integer> nums = new ArrayList<Integer>();
							for (int i = pos1 * 4; i < len; i++) {
								nums.add(infos.get(i).getNum());
							}
							yz.setFdNums(Joiner.on(",").join(nums));
							Integer currentYz = null;

							String sxs = "" + pos0;
							String compareSxs = "" + pos1;
							if (sxs.equals(compareSxs)) {
								currentYz = 0;
							}
							if (currentYz == null && lastYzFuture != null) {
								while (true) {
									if (lastYzFuture.isDone()) {
										if (lastYzFuture.get() != null && lastYzFuture.get() instanceof My200Yz) {
											My200Yz temp = (My200Yz) lastYzFuture.get();
											if (lastYzFuture.get() != null && temp.getSxyz() != null) {
												currentYz = temp.getSxyz() + 1;
											}
										}
										break;
									} else {
										Thread.sleep(100);
									}
								}
							}
							yz.setFdyz(currentYz);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public <T extends Avg> void calFdMy(My1Yz myYz, Future<Object> lastYzFuture, String column, Class<T> clazz, BaseYzRepository<T> repository,
			BaseYzDao<T> dao, String[] fds, List<Integer>[] nums, String[] columns, String[] texts) {
		try {
			T yz = repository.findByYearAndPhase(myYz.getYear(), myYz.getPhase());
			if (yz == null) {
				return;
			}
			int pos0 = 0;
			for (String fd : fds) {
				Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
				Integer value = (Integer) gm.invoke(yz);
				if (value != null && 0 == value) {
					break;
				}
				pos0++;
			}
			if (pos0 < fds.length) {
				T lastYz = dao.getLastYzWithSamePos(yz, columns[pos0]);
				if (lastYz != null) {
					T nextYz = repository.findOne(lastYz.getId() + 1);
					int pos1 = 0;
					for (String fd : fds) {
						Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
						Integer value = (Integer) gm.invoke(nextYz);
						if (value != null && 0 == value) {
							break;
						}
						pos1++;
					}
					if (nextYz != null && pos1 < fds.length) {
						Method mySm = ReflectionUtils.findMethod(My1Yz.class, "set" + column + "my", String.class);
						mySm.invoke(myYz, texts[pos1]);
						Method numsSm = ReflectionUtils.findMethod(My1Yz.class, "set" + column + "Nums", String.class);
						numsSm.invoke(myYz, Joiner.on(",").join(nums[pos1]));
						Integer currentYz = null;

						lastYz = repository.findOne(yz.getId() - 1);
						if (lastYz == null) {
							return;
						}
						pos1 = 0;
						for (String fd : fds) {
							Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
							Integer value = (Integer) gm.invoke(lastYz);
							if (value != null && 0 == value) {
								break;
							}
							pos1++;
						}
						String sxs = "" + pos1 + pos0;
						lastYz = dao.getLastYzWithSamePos(lastYz, columns[pos1]);
						if (lastYz == null) {
							return;
						}
						nextYz = repository.findOne(lastYz.getId() + 1);
						if (nextYz == null) {
							return;
						}
						pos1 = 0;
						for (String fd : fds) {
							Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
							Integer value = (Integer) gm.invoke(lastYz);
							if (value != null && 0 == value) {
								break;
							}
							pos1++;
						}
						pos0 = 0;
						for (String fd : fds) {
							Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
							Integer value = (Integer) gm.invoke(nextYz);
							if (value != null && 0 == value) {
								break;
							}
							pos0++;
						}
						String compareSxs = "" + pos1 + pos0;
						if (sxs.equals(compareSxs)) {
							currentYz = 0;
						}
						if (currentYz == null && lastYzFuture != null) {
							while (true) {
								if (lastYzFuture.isDone()) {
									if (lastYzFuture.get() != null && lastYzFuture.get() instanceof My1Yz) {
										Method gm = ReflectionUtils.findMethod(My1Yz.class, "get" + column + "yz");
										Integer value = (Integer) gm.invoke(lastYzFuture.get());
										if (value != null) {
											currentYz = value + 1;
										}
									}
									break;
								} else {
									Thread.sleep(100);
								}
							}
						}
						Method sm = ReflectionUtils.findMethod(My1Yz.class, "set" + column + "yz", Integer.class);
						sm.invoke(myYz, currentYz);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Async("subExecutor")
	public <T extends Avg> Future<Exception> calFdMy2(My2Yz myYz, Future<Object> lastYzFuture, String column, Class<T> clazz,
			BaseYzRepository<T> repository, BaseYzDao<T> dao, String[] fds, List<Integer>[] nums, String[] columns, String[] texts) {
		Exception t = null;
		Future<Exception> result = new AsyncResult<Exception>(null);
		try {
			T yz = repository.findByYearAndPhase(myYz.getYear(), myYz.getPhase());
			if (yz == null) {
				return result;
			}
			int pos0 = 0;
			for (String fd : fds) {
				Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
				Integer value = (Integer) gm.invoke(yz);
				if (value != null && 0 == value) {
					break;
				}
				pos0++;
			}
			if (pos0 < fds.length) {
				T lastYz = repository.findOne(yz.getId() - 1);
				if (lastYz == null) {
					return result;
				}
				int pos1 = 0;
				for (String fd : fds) {
					Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
					Integer value = (Integer) gm.invoke(lastYz);
					if (value != null && 0 == value) {
						break;
					}
					pos1++;
				}
				List<T> compareList = dao.getLastYzListWithSamePos(lastYz, columns[pos1]);
				if (compareList == null || compareList.isEmpty()) {
					return result;
				}
				for (T data : compareList) {
					lastYz = repository.findOne(data.getId() + 1);
					if (lastYz == null) {
						continue;
					}
					int tempPos = 0;
					for (String fd : fds) {
						Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
						Integer value = (Integer) gm.invoke(lastYz);
						if (value != null && 0 == value) {
							break;
						}
						tempPos++;
					}
					if (tempPos == pos0) {
						break;
					}
					lastYz = null;
				}
				if (lastYz != null) {
					T nextYz = repository.findOne(lastYz.getId() + 1);
					int pos2 = 0;
					for (String fd : fds) {
						Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
						Integer value = (Integer) gm.invoke(nextYz);
						if (value != null && 0 == value) {
							break;
						}
						pos2++;
					}
					if (nextYz != null && pos2 < fds.length) {
						Method mySm = ReflectionUtils.findMethod(My2Yz.class, "set" + column + "my", String.class);
						mySm.invoke(myYz, texts[pos1]);
						Method numsSm = ReflectionUtils.findMethod(My2Yz.class, "set" + column + "Nums", String.class);
						numsSm.invoke(myYz, Joiner.on(",").join(nums[pos2]));
						Integer currentYz = null;

						lastYz = repository.findOne(yz.getId() - 1);
						if (lastYz == null) {
							return result;
						}
						pos1 = 0;
						for (String fd : fds) {
							Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
							Integer value = (Integer) gm.invoke(lastYz);
							if (value != null && 0 == value) {
								break;
							}
							pos1++;
						}

						String sxs = "" + pos1 + pos0;
						lastYz = repository.findOne(yz.getId() - 2);
						if (lastYz == null) {
							return result;
						}
						int pos3 = 0;
						for (String fd : fds) {
							Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
							Integer value = (Integer) gm.invoke(lastYz);
							if (value != null && 0 == value) {
								break;
							}
							pos3++;
						}
						if (pos3 == fds.length) {
							return result;
						}
						sxs = pos3 + sxs;

						compareList = dao.getLastYzListWithSamePos(lastYz, columns[pos3]);
						if (compareList == null || compareList.isEmpty()) {
							return result;
						}
						String compareSxs = null;
						for (T data : compareList) {
							int pos = 0;
							for (String fd : fds) {
								Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
								Integer value = (Integer) gm.invoke(data);
								if (value != null && 0 == value) {
									break;
								}
								pos++;
							}
							if (pos == fds.length) {
								continue;
							}
							compareSxs = "" + pos;
							T temp = repository.findOne(data.getId() + 1);
							if (temp == null) {
								continue;
							}
							pos = 0;
							for (String fd : fds) {
								Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
								Integer value = (Integer) gm.invoke(temp);
								if (value != null && 0 == value) {
									break;
								}
								pos++;
							}
							if (pos == fds.length) {
								continue;
							}
							if (pos == pos1) {
								compareSxs += pos;
								nextYz = repository.findOne(temp.getId() + 1);
								if (nextYz == null) {
									continue;
								}
								pos = 0;
								for (String fd : fds) {
									Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
									Integer value = (Integer) gm.invoke(nextYz);
									if (value != null && 0 == value) {
										break;
									}
									pos++;
								}
								if (pos == fds.length) {
									continue;
								}
								compareSxs += pos;
								break;
							}
						}
						if (sxs.equals(compareSxs)) {
							currentYz = 0;
						}

						if (currentYz == null && lastYzFuture != null) {
							while (true) {
								if (lastYzFuture.isDone()) {
									if (lastYzFuture.get() != null && lastYzFuture.get() instanceof My2Yz) {
										Method gm = ReflectionUtils.findMethod(My2Yz.class, "get" + column + "yz");
										Integer value = (Integer) gm.invoke(lastYzFuture.get());
										if (value != null) {
											currentYz = value + 1;
										}
									}
									break;
								} else {
									Thread.sleep(100);
								}
							}
						}
						Method sm = ReflectionUtils.findMethod(My2Yz.class, "set" + column + "yz", Integer.class);
						sm.invoke(myYz, currentYz);
					}
				}
			}
		} catch (Exception e) {
			// logger.error(e.getMessage(), e);
			// throw new RuntimeException(e.getMessage(), e);
			t = e;
			return new AsyncResult<Exception>(t);
		}
		return result;
	}

	public <T extends Avg> void calFdMy200(My200Yz myYz, Future<Object> lastYzFuture, String column, Class<T> clazz, BaseYzRepository<T> repository,
			BaseYzDao<T> dao, String[] fds, List<Integer>[] nums, String[] columns, String[] texts) {
		try {
			T yz = repository.findByYearAndPhase(myYz.getYear(), myYz.getPhase());
			if (yz == null) {
				return;
			}
			int pos0 = 0;
			for (String fd : fds) {
				Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
				Integer value = (Integer) gm.invoke(yz);
				if (value != null && 0 == value) {
					break;
				}
				pos0++;
			}
			if (pos0 < fds.length) {
				T lastYz = dao.getLastYzIn(yz.getId(), 200);
				if (lastYz != null) {
					int pos1 = 0;
					for (String fd : fds) {
						Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
						Integer value = (Integer) gm.invoke(lastYz);
						if (value != null && 0 == value) {
							break;
						}
						pos1++;
					}
					T nextYz = repository.findOne(lastYz.getId() + 1);
					if (nextYz != null && pos1 < fds.length) {
						Method mySm = ReflectionUtils.findMethod(My200Yz.class, "set" + column + "my", String.class);
						mySm.invoke(myYz, texts[pos1]);
						Method numsSm = ReflectionUtils.findMethod(My200Yz.class, "set" + column + "Nums", String.class);
						numsSm.invoke(myYz, Joiner.on(",").join(nums[pos1]));
						Integer currentYz = null;

						String sxs = "" + pos0;
						String compareSxs = "" + pos1;
						if (sxs.equals(compareSxs)) {
							currentYz = 0;
						}
						if (currentYz == null && lastYzFuture != null) {
							while (true) {
								if (lastYzFuture.isDone()) {
									if (lastYzFuture.get() != null && lastYzFuture.get() instanceof My200Yz) {
										Method gm = ReflectionUtils.findMethod(My200Yz.class, "get" + column + "yz");
										Integer value = (Integer) gm.invoke(lastYzFuture.get());
										if (value != null) {
											currentYz = value + 1;
										}
									}
									break;
								} else {
									Thread.sleep(100);
								}
							}
						}
						Method sm = ReflectionUtils.findMethod(My200Yz.class, "set" + column + "yz", Integer.class);
						sm.invoke(myYz, currentYz);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
