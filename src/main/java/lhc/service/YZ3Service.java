package lhc.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.google.common.base.Joiner;

import lhc.domain.DsxJyYz;
import lhc.domain.DsxMaxJyJDB;
import lhc.domain.DsxMaxJyJHB;
import lhc.domain.DsxMaxJyJQB;
import lhc.domain.DsxMinJyJDB;
import lhc.domain.DsxMinJyJHB;
import lhc.domain.DsxMinJyJQB;
import lhc.domain.FxSw;
import lhc.domain.FxSw1;
import lhc.domain.FxSw10;
import lhc.domain.FxSw11;
import lhc.domain.FxSw12;
import lhc.domain.FxSw2;
import lhc.domain.FxSw3;
import lhc.domain.FxSw4;
import lhc.domain.FxSw5;
import lhc.domain.FxSw6;
import lhc.domain.FxSw7;
import lhc.domain.FxSw8;
import lhc.domain.FxSw9;
import lhc.domain.FxSwA;
import lhc.domain.KaiJiang;
import lhc.dto.DsxJY;
import lhc.dto.XbwJYSub;
import lhc.repository.jpa.BaseYzRepository;
import lhc.util.CommonUtil;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class YZ3Service extends YZ2Service {
	@Async
	public Future<Exception> calDsxMinJY() {
		return calDsxJY(DsxMinJyJQB.class, DsxMinJyJDB.class, DsxMinJyJHB.class, repositories.dsxMinJyJQBRepository,
				repositories.dsxMinJyJDBRepository, repositories.dsxMinJyJHBRepository, false);
	}

	@Async
	public Future<Exception> calDsxMaxJY() {
		return calDsxJY(DsxMaxJyJQB.class, DsxMaxJyJDB.class, DsxMaxJyJHB.class, repositories.dsxMaxJyJQBRepository,
				repositories.dsxMaxJyJDBRepository, repositories.dsxMaxJyJHBRepository, true);
	}

	public <Q extends DsxJyYz, D extends DsxJyYz, H extends DsxJyYz> Future<Exception> calDsxJY(Class<Q> jqbClass,
			Class<D> jdbClass, Class<H> jhbClass, BaseYzRepository<Q> jqbRepository, BaseYzRepository<D> jdbRepository,
			BaseYzRepository<H> jhbRepository, boolean max) {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<FxSw1> result1 = null;
			Page<FxSw2> result2 = null;
			Page<FxSw3> result3 = null;
			Page<FxSw4> result4 = null;
			Page<FxSw5> result5 = null;
			Page<FxSw6> result6 = null;
			Page<FxSw7> result7 = null;
			Page<FxSw8> result8 = null;
			Page<FxSw9> result9 = null;
			Page<FxSw10> result10 = null;
			Page<FxSw11> result11 = null;
			Page<FxSw12> result12 = null;
			DsxJY lastJY = null;
			Q unknownDataForJQB = jqbRepository.findByYearAndPhase(0, 0);
			D unknownDataForJDB = jdbRepository.findByYearAndPhase(0, 0);
			H unknownDataForJHB = jhbRepository.findByYearAndPhase(0, 0);
			if (unknownDataForJQB == null) {
				unknownDataForJQB = jqbClass.newInstance();
				unknownDataForJQB.setYear(0);
				unknownDataForJQB.setPhase(0);
				unknownDataForJQB.setDate("unknown");
				jqbRepository.save(unknownDataForJQB);
			}
			if (unknownDataForJDB == null) {
				unknownDataForJDB = jdbClass.newInstance();
				unknownDataForJDB.setYear(0);
				unknownDataForJDB.setPhase(0);
				unknownDataForJDB.setDate("unknown");
				jdbRepository.save(unknownDataForJDB);
			}
			if (unknownDataForJHB == null) {
				unknownDataForJHB = jhbClass.newInstance();
				unknownDataForJHB.setYear(0);
				unknownDataForJHB.setPhase(0);
				unknownDataForJHB.setDate("unknown");
				jhbRepository.save(unknownDataForJHB);
			}
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			do {
				result1 = repositories.fxsw1Repository.findAll(request);
				result2 = repositories.fxsw2Repository.findAll(request);
				result3 = repositories.fxsw3Repository.findAll(request);
				result4 = repositories.fxsw4Repository.findAll(request);
				result5 = repositories.fxsw5Repository.findAll(request);
				result6 = repositories.fxsw6Repository.findAll(request);
				result7 = repositories.fxsw7Repository.findAll(request);
				result8 = repositories.fxsw8Repository.findAll(request);
				result9 = repositories.fxsw9Repository.findAll(request);
				result10 = repositories.fxsw10Repository.findAll(request);
				result11 = repositories.fxsw11Repository.findAll(request);
				result12 = repositories.fxsw12Repository.findAll(request);
				if (result1 != null && result1.hasContent()) {
					futures.add(repositories.yzService.doCalDsxJY(jqbClass, jdbClass, jhbClass, jqbRepository, jdbRepository,
							jhbRepository, max, result1, result2, result3, result4, result5, result6, result7, result8, result9,
							result10, result11, result12, lastJY));

					int i = result1.getContent().size() - 1;
					FxSw temp = result1.getContent().get(i);
					lastJY = new DsxJY();
					lastJY.setDate(temp.getDate());
					lastJY.setYear(temp.getYear());
					lastJY.setPhase(temp.getPhase());
					setFxSwDataForDsx(result1, result2, result3, result4, result5, result6, result7, result8, result9, result10,
							result11, result12, lastJY, i, max);
				}
				request = request.next();
			} while (result1 != null && result1.hasNext());
			if (lastJY != null) {
				unknownDataForJQB.reset();
				calDsxJYForJQB(unknownDataForJQB, lastJY);
				calDsxJYForJQB(unknownDataForJQB, lastJY, true);
				calDsxJYForJQB(unknownDataForJQB, lastJY, false);
				jqbRepository.save(unknownDataForJQB);
				unknownDataForJDB.reset();
				calDsxJYForJDB(unknownDataForJDB, lastJY);
				calDsxJYForJDB(unknownDataForJDB, lastJY, true);
				calDsxJYForJDB(unknownDataForJDB, lastJY, false);
				jdbRepository.save(unknownDataForJDB);
				unknownDataForJHB.reset();
				calDsxJYForJHB(unknownDataForJHB, lastJY);
				calDsxJYForJHB(unknownDataForJHB, lastJY, true);
				calDsxJYForJHB(unknownDataForJHB, lastJY, false);
				jhbRepository.save(unknownDataForJHB);
			}
			String str = max ? "Max" : "Min";
			CommonUtil.sleep(futures, 100);
			logger.info("End of calDsx" + str + "RedCounts...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public <Q extends DsxJyYz, D extends DsxJyYz, H extends DsxJyYz> Future<Exception> doCalDsxJY(Class<Q> jqbClass,
			Class<D> jdbClass, Class<H> jhbClass, BaseYzRepository<Q> jqbRepository, BaseYzRepository<D> jdbRepository,
			BaseYzRepository<H> jhbRepository, boolean max, Page<FxSw1> result1, Page<FxSw2> result2, Page<FxSw3> result3,
			Page<FxSw4> result4, Page<FxSw5> result5, Page<FxSw6> result6, Page<FxSw7> result7, Page<FxSw8> result8,
			Page<FxSw9> result9, Page<FxSw10> result10, Page<FxSw11> result11, Page<FxSw12> result12, DsxJY lastJY) {

		Exception t = null;
		try {
			if (result1 != null && result1.hasContent()) {
				for (int i = 0; i < result1.getContent().size(); i++) {
					FxSw temp = result1.getContent().get(i);
					DsxJY jy = new DsxJY();
					jy.setDate(temp.getDate());
					jy.setYear(temp.getYear());
					jy.setPhase(temp.getPhase());
					KaiJiang kj = repositories.kaiJiangRepository.findByYearAndPhase(temp.getYear(), temp.getPhase());
					Q jqb = jqbRepository.findByYearAndPhase(temp.getYear(), temp.getPhase());
					if (jqb == null) {
						jqb = jqbClass.newInstance();
						jqb.setYear(temp.getYear());
						jqb.setPhase(temp.getPhase());
						jqb.setDate(temp.getDate());
						jqb.setSpecialNum(kj.getSpecialNum());
					}
					D jdb = jdbRepository.findByYearAndPhase(temp.getYear(), temp.getPhase());
					if (jdb == null) {
						jdb = jdbClass.newInstance();
						jdb.setYear(temp.getYear());
						jdb.setPhase(temp.getPhase());
						jdb.setDate(temp.getDate());
						jdb.setSpecialNum(kj.getSpecialNum());
					}
					H jhb = jhbRepository.findByYearAndPhase(temp.getYear(), temp.getPhase());
					if (jhb == null) {
						jhb = jhbClass.newInstance();
						jhb.setYear(temp.getYear());
						jhb.setPhase(temp.getPhase());
						jhb.setDate(temp.getDate());
						jhb.setSpecialNum(kj.getSpecialNum());
					}
					setFxSwDataForDsx(result1, result2, result3, result4, result5, result6, result7, result8, result9, result10,
							result11, result12, jy, i, max);
					if (lastJY != null) {
						calDsxJYForJQB(jqb, lastJY);
						calDsxJYForJQB(jqb, lastJY, true);
						calDsxJYForJQB(jqb, lastJY, false);
						calDsxJYForJDB(jdb, lastJY);
						calDsxJYForJDB(jdb, lastJY, true);
						calDsxJYForJDB(jdb, lastJY, false);
						calDsxJYForJHB(jhb, lastJY);
						calDsxJYForJHB(jhb, lastJY, true);
						calDsxJYForJHB(jhb, lastJY, false);
					}
					jqbRepository.save(jqb);
					jdbRepository.save(jdb);
					jhbRepository.save(jhb);
					lastJY = jy;
				}
			}
			String str = max ? "Max" : "Min";
			logger.info("End of partition of Dsx" + str + "JY...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);

	}

	private void setFxSwDataForDsx(Page<FxSw1> result1, Page<FxSw2> result2, Page<FxSw3> result3, Page<FxSw4> result4,
			Page<FxSw5> result5, Page<FxSw6> result6, Page<FxSw7> result7, Page<FxSw8> result8, Page<FxSw9> result9,
			Page<FxSw10> result10, Page<FxSw11> result11, Page<FxSw12> result12, DsxJY jy, int i, boolean max)
			throws Exception {
		List<FxSw> fxDatas = new ArrayList<FxSw>();
		fxDatas.add(result1.getContent().get(i));
		fxDatas.add(result2.getContent().get(i));
		fxDatas.add(result3.getContent().get(i));
		fxDatas.add(result4.getContent().get(i));
		fxDatas.add(result5.getContent().get(i));
		fxDatas.add(result6.getContent().get(i));
		fxDatas.add(result7.getContent().get(i));
		fxDatas.add(result8.getContent().get(i));
		fxDatas.add(result9.getContent().get(i));
		fxDatas.add(result10.getContent().get(i));
		fxDatas.add(result11.getContent().get(i));
		fxDatas.add(result12.getContent().get(i));
		for (FxSw fxData : fxDatas) {
			setFxSwData(jy, fxData, "Sx", max);
			setFxSwData(jy, fxData, "Sxzf", max);
			setFxSwData(jy, fxData, "Ds", max);
			setFxSwData(jy, fxData, "Dszf", max);
			setFxSwData(jy, fxData, "Sw", max);
			setFxSwData(jy, fxData, "Swzf", max);
			setFxSwData(jy, fxData, "Mw", max);
			setFxSwData(jy, fxData, "Mwzf", max);
			setFxSwData(jy, fxData, "Lh", max);
			setFxSwData(jy, fxData, "Lhzf", max);
			setFxSwData(jy, fxData, "Bs", max);
			setFxSwData(jy, fxData, "Bszf", max);
			setFxSwData(jy, fxData, "Zs", max);
			setFxSwData(jy, fxData, "Zszf", max);
			setFxSwData(jy, fxData, "Wx", max);
			setFxSwData(jy, fxData, "Wxzf", max);
			setFxSwData(jy, fxData, "Wxds", max);
			setFxSwData(jy, fxData, "Wxdszf", max);
			setFxSwData(jy, fxData, "Pd", max);
			setFxSwData(jy, fxData, "Pdzf", max);
			setFxSwData(jy, fxData, "Fd", max);
			setFxSwData(jy, fxData, "Fdzf", max);
			setFxSwData(jy, fxData, "Qq", max);
			setFxSwData(jy, fxData, "Qqzf", max);
			setFxSwData(jy, fxData, "Qiw", max);
			setFxSwData(jy, fxData, "Qiwzf", max);
			setFxSwData(jy, fxData, "Twelve", max);
			setFxSwData(jy, fxData, "Twelvezf", max);
			setFxSwData(jy, fxData, "Slq", max);
			setFxSwData(jy, fxData, "Slqzf", max);
			setFxSwData(jy, fxData, "Zx1", max);
			setFxSwData(jy, fxData, "Zx1zf", max);
			setFxSwData(jy, fxData, "Zx2", max);
			setFxSwData(jy, fxData, "Zx2zf", max);
			setFxSwData(jy, fxData, "Zx3", max);
			setFxSwData(jy, fxData, "Zx3zf", max);
			setFxSwData(jy, fxData, "Zx4", max);
			setFxSwData(jy, fxData, "Zx4zf", max);
			setFxSwData(jy, fxData, "Zx5", max);
			setFxSwData(jy, fxData, "Zx5zf", max);
			setFxSwData(jy, fxData, "Zx6", max);
			setFxSwData(jy, fxData, "Zx6zf", max);
			setFxSwData(jy, fxData, "Zx7", max);
			setFxSwData(jy, fxData, "Zx7zf", max);
			setFxSwData(jy, fxData, "Zx8", max);
			setFxSwData(jy, fxData, "Zx8zf", max);
			setFxSwData(jy, fxData, "Zx9", max);
			setFxSwData(jy, fxData, "Zx9zf", max);
			setFxSwData(jy, fxData, "Zx10", max);
			setFxSwData(jy, fxData, "Zx10zf", max);
			setFxSwData(jy, fxData, "Zx11", max);
			setFxSwData(jy, fxData, "Zx11zf", max);
			setFxSwData(jy, fxData, "Zx12", max);
			setFxSwData(jy, fxData, "Zx12zf", max);
			setFxSwData(jy, fxData, "Zx13", max);
			setFxSwData(jy, fxData, "Zx13zf", max);
			setFxSwData(jy, fxData, "Zx14", max);
			setFxSwData(jy, fxData, "Zx14zf", max);
			setFxSwData(jy, fxData, "Zx15", max);
			setFxSwData(jy, fxData, "Zx15zf", max);
			setFxSwData(jy, fxData, "Zx16", max);
			setFxSwData(jy, fxData, "Zx16zf", max);
			setFxSwData(jy, fxData, "Zx17", max);
			setFxSwData(jy, fxData, "Zx17zf", max);
			setFxSwData(jy, fxData, "Zx18", max);
			setFxSwData(jy, fxData, "Zx18zf", max);
		}
	}

	protected void setFxSwData(DsxJY jy, FxSw data, String column, boolean max) throws Exception {
		Method gmForRedCountsForFx = ReflectionUtils.findMethod(FxSw.class, "getRedCountsFor" + column);
		Method gmForYzForFx = ReflectionUtils.findMethod(FxSw.class, "get" + column);
		Method gmForNumsForFx = ReflectionUtils.findMethod(FxSw.class, "get" + column + "Nums");

		Method gmForRedCountsForJY = ReflectionUtils.findMethod(DsxJY.class, "getRedCountsFor" + column);
		Method gmForYzForJY = ReflectionUtils.findMethod(DsxJY.class, "get" + column);
		Method smForRedCountsForJY = ReflectionUtils.findMethod(DsxJY.class, "setRedCountsFor" + column, Integer.class);
		Method smForYzForJY = ReflectionUtils.findMethod(DsxJY.class, "set" + column, Integer.class);
		Method smForNumsForJY = ReflectionUtils.findMethod(DsxJY.class, "set" + column + "Nums", String.class);

		boolean toOverride = false;
		Integer redCountsForJY = (Integer) gmForRedCountsForJY.invoke(jy);
		Integer redCountsForFx = (Integer) gmForRedCountsForFx.invoke(data);
		if (redCountsForJY == null) {
			toOverride = true;
		} else if (redCountsForFx != null) {
			if (redCountsForJY.compareTo(redCountsForFx) == 0) {
				Integer yzForJY = (Integer) gmForYzForJY.invoke(jy);
				Integer yzForFx = (Integer) gmForYzForFx.invoke(data);
				if (yzForFx != null) {
					if (yzForJY == null) {
						toOverride = true;
					} else if (yzForJY.compareTo(yzForFx) > 0) {
						toOverride = true;
					}
				}
			} else if (max) {
				if (redCountsForJY.compareTo(redCountsForFx) < 0) {
					toOverride = true;
				}
			} else {
				if (redCountsForJY.compareTo(redCountsForFx) > 0) {
					toOverride = true;
				}
			}
		}
		if (toOverride) {
			smForRedCountsForJY.invoke(jy, gmForRedCountsForFx.invoke(data));
			smForYzForJY.invoke(jy, gmForYzForFx.invoke(data));
			smForNumsForJY.invoke(jy, gmForNumsForFx.invoke(data));
		}
	}

	protected void addNumsToConditionList(Collection<Integer> conditionSet, String numsStr) {
		if (numsStr != null) {
			String[] nums = numsStr.split(",\\s*");
			if (nums != null && nums.length > 0) {
				for (String num : nums) {
					if (!num.isEmpty()) {
						conditionSet.add(Integer.valueOf(num));
					}
				}
			}
		}
	}

	protected void calDsxJYForJQB(DsxJyYz data, DsxJY lastJY) throws Exception {
		StringBuilder numsStr = new StringBuilder();
		appendNums(numsStr, lastJY.getSxNums());
		appendNums(numsStr, lastJY.getTwelveNums());
		appendNums(numsStr, lastJY.getSlqNums());
		appendNums(numsStr, lastJY.getFdNums());
		appendNums(numsStr, lastJY.getDsNums());
		appendNums(numsStr, lastJY.getLhNums());
		appendNums(numsStr, lastJY.getZx1Nums());
		appendNums(numsStr, lastJY.getZx3Nums());
		appendNums(numsStr, lastJY.getPdNums());
		appendNums(numsStr, lastJY.getWxdsNums());
		appendNums(numsStr, lastJY.getMwNums());
		appendNums(numsStr, lastJY.getZx2Nums());
		appendNums(numsStr, lastJY.getZx5Nums());
		appendNums(numsStr, lastJY.getZx11Nums());
		appendNums(numsStr, lastJY.getZx4Nums());
		appendNums(numsStr, lastJY.getZx13Nums());
		appendNums(numsStr, lastJY.getZx16Nums());
		appendNums(numsStr, lastJY.getZx12Nums());
		appendNums(numsStr, lastJY.getZx14Nums());
		appendNums(numsStr, lastJY.getZx15Nums());
		appendNums(numsStr, lastJY.getSxzfNums());
		appendNums(numsStr, lastJY.getTwelvezfNums());
		appendNums(numsStr, lastJY.getSlqzfNums());
		appendNums(numsStr, lastJY.getFdzfNums());
		appendNums(numsStr, lastJY.getDszfNums());
		appendNums(numsStr, lastJY.getLhzfNums());
		appendNums(numsStr, lastJY.getZx1zfNums());
		appendNums(numsStr, lastJY.getZx3zfNums());
		appendNums(numsStr, lastJY.getPdzfNums());
		appendNums(numsStr, lastJY.getWxdszfNums());
		appendNums(numsStr, lastJY.getMwzfNums());
		appendNums(numsStr, lastJY.getZx2zfNums());
		appendNums(numsStr, lastJY.getZx5zfNums());
		appendNums(numsStr, lastJY.getZx11zfNums());
		appendNums(numsStr, lastJY.getZx4zfNums());
		appendNums(numsStr, lastJY.getZx13zfNums());
		appendNums(numsStr, lastJY.getZx16zfNums());
		appendNums(numsStr, lastJY.getZx12zfNums());
		appendNums(numsStr, lastJY.getZx14zfNums());
		appendNums(numsStr, lastJY.getZx15zfNums());
		List<Integer> nums = new ArrayList<Integer>();
		addNumsToConditionList(nums, numsStr.toString());
		assembleDsxJY(data, new List[] { nums }, false, false);
	}

	protected void calDsxJYForJDB(DsxJyYz data, DsxJY lastJY) throws Exception {
		StringBuilder numsStr = new StringBuilder();
		appendNums(numsStr, lastJY.getSxNums());
		appendNums(numsStr, lastJY.getTwelveNums());
		appendNums(numsStr, lastJY.getSlqNums());
		appendNums(numsStr, lastJY.getFdNums());
		appendNums(numsStr, lastJY.getLhNums());
		appendNums(numsStr, lastJY.getZx3Nums());
		appendNums(numsStr, lastJY.getPdNums());
		appendNums(numsStr, lastJY.getWxdsNums());
		appendNums(numsStr, lastJY.getMwNums());
		appendNums(numsStr, lastJY.getZx2Nums());
		appendNums(numsStr, lastJY.getZx11Nums());
		appendNums(numsStr, lastJY.getZx4Nums());
		appendNums(numsStr, lastJY.getZx13Nums());
		appendNums(numsStr, lastJY.getZx16Nums());
		appendNums(numsStr, lastJY.getZx12Nums());
		appendNums(numsStr, lastJY.getZx15Nums());
		appendNums(numsStr, lastJY.getSxzfNums());
		appendNums(numsStr, lastJY.getTwelvezfNums());
		appendNums(numsStr, lastJY.getSlqzfNums());
		appendNums(numsStr, lastJY.getFdzfNums());
		appendNums(numsStr, lastJY.getLhzfNums());
		appendNums(numsStr, lastJY.getZx3zfNums());
		appendNums(numsStr, lastJY.getPdzfNums());
		appendNums(numsStr, lastJY.getWxdszfNums());
		appendNums(numsStr, lastJY.getMwzfNums());
		appendNums(numsStr, lastJY.getZx2zfNums());
		appendNums(numsStr, lastJY.getZx11zfNums());
		appendNums(numsStr, lastJY.getZx4zfNums());
		appendNums(numsStr, lastJY.getZx13zfNums());
		appendNums(numsStr, lastJY.getZx16zfNums());
		appendNums(numsStr, lastJY.getZx12zfNums());
		appendNums(numsStr, lastJY.getZx15zfNums());
		List<Integer> nums = new ArrayList<Integer>();
		addNumsToConditionList(nums, numsStr.toString());
		assembleDsxJY(data, new List[] { nums }, false, false);
	}

	protected void calDsxJYForJHB(DsxJyYz data, DsxJY lastJY) throws Exception {
		StringBuilder numsStr = new StringBuilder();
		appendNums(numsStr, lastJY.getSxNums());
		appendNums(numsStr, lastJY.getTwelveNums());
		appendNums(numsStr, lastJY.getSlqNums());
		appendNums(numsStr, lastJY.getLhNums());
		appendNums(numsStr, lastJY.getZx3Nums());
		appendNums(numsStr, lastJY.getPdNums());
		appendNums(numsStr, lastJY.getMwNums());
		appendNums(numsStr, lastJY.getZx2Nums());
		appendNums(numsStr, lastJY.getZx11Nums());
		appendNums(numsStr, lastJY.getZx13Nums());
		appendNums(numsStr, lastJY.getZx16Nums());
		appendNums(numsStr, lastJY.getZx12Nums());
		appendNums(numsStr, lastJY.getSxzfNums());
		appendNums(numsStr, lastJY.getTwelvezfNums());
		appendNums(numsStr, lastJY.getSlqzfNums());
		appendNums(numsStr, lastJY.getLhzfNums());
		appendNums(numsStr, lastJY.getZx3zfNums());
		appendNums(numsStr, lastJY.getPdzfNums());
		appendNums(numsStr, lastJY.getMwzfNums());
		appendNums(numsStr, lastJY.getZx2zfNums());
		appendNums(numsStr, lastJY.getZx11zfNums());
		appendNums(numsStr, lastJY.getZx13zfNums());
		appendNums(numsStr, lastJY.getZx16zfNums());
		appendNums(numsStr, lastJY.getZx12zfNums());
		List<Integer> nums = new ArrayList<Integer>();
		addNumsToConditionList(nums, numsStr.toString());
		assembleDsxJY(data, new List[] { nums }, false, false);
	}

	protected StringBuilder appendNums(StringBuilder sb, String numStr) {
		if (numStr != null) {
			sb.append(numStr).append(",");
		}
		return sb;
	}

	protected void calDsxJYForJQB(DsxJyYz data, DsxJY lastJY, boolean reverse) throws Exception {
		Set<Integer> A = new HashSet<Integer>();
		addNumsToConditionList(A, lastJY.getSxNums());
		addNumsToConditionList(A, lastJY.getTwelveNums());
		addNumsToConditionList(A, lastJY.getSlqNums());
		addNumsToConditionList(A, lastJY.getFdNums());
		addNumsToConditionList(A, lastJY.getDsNums());

		Set<Integer> B = new HashSet<Integer>();
		addNumsToConditionList(B, lastJY.getLhNums());
		addNumsToConditionList(B, lastJY.getZx1Nums());
		addNumsToConditionList(B, lastJY.getZx3Nums());
		addNumsToConditionList(B, lastJY.getPdNums());
		addNumsToConditionList(B, lastJY.getWxdsNums());

		Set<Integer> C = new HashSet<Integer>();
		addNumsToConditionList(C, lastJY.getMwNums());
		addNumsToConditionList(C, lastJY.getZx2Nums());
		addNumsToConditionList(C, lastJY.getZx5Nums());
		addNumsToConditionList(C, lastJY.getZx11Nums());
		addNumsToConditionList(C, lastJY.getZx4Nums());

		Set<Integer> D = new HashSet<Integer>();
		addNumsToConditionList(D, lastJY.getZx13Nums());
		addNumsToConditionList(D, lastJY.getZx16Nums());
		addNumsToConditionList(D, lastJY.getZx12Nums());
		addNumsToConditionList(D, lastJY.getZx14Nums());
		addNumsToConditionList(D, lastJY.getZx15Nums());

		Set<Integer> E = new HashSet<Integer>();
		addNumsToConditionList(E, lastJY.getSxzfNums());
		addNumsToConditionList(E, lastJY.getTwelvezfNums());
		addNumsToConditionList(E, lastJY.getSlqzfNums());
		addNumsToConditionList(E, lastJY.getFdzfNums());
		addNumsToConditionList(E, lastJY.getDszfNums());

		Set<Integer> F = new HashSet<Integer>();
		addNumsToConditionList(F, lastJY.getLhzfNums());
		addNumsToConditionList(F, lastJY.getZx1zfNums());
		addNumsToConditionList(F, lastJY.getZx3zfNums());
		addNumsToConditionList(F, lastJY.getPdzfNums());
		addNumsToConditionList(F, lastJY.getWxdszfNums());

		Set<Integer> G = new HashSet<Integer>();
		addNumsToConditionList(G, lastJY.getMwzfNums());
		addNumsToConditionList(G, lastJY.getZx2zfNums());
		addNumsToConditionList(G, lastJY.getZx5zfNums());
		addNumsToConditionList(G, lastJY.getZx11zfNums());
		addNumsToConditionList(G, lastJY.getZx4zfNums());

		Set<Integer> H = new HashSet<Integer>();
		addNumsToConditionList(H, lastJY.getZx13zfNums());
		addNumsToConditionList(H, lastJY.getZx16zfNums());
		addNumsToConditionList(H, lastJY.getZx12zfNums());
		addNumsToConditionList(H, lastJY.getZx14zfNums());
		addNumsToConditionList(H, lastJY.getZx15zfNums());

		data.clearAll();
		Set<Integer>[] nums = collectConditionList(A, B, C, D, E, F, G, H, reverse);
		assembleDsxJY(data, nums, true, reverse);
	}

	protected void calDsxJYForJDB(DsxJyYz data, DsxJY lastJY, boolean reverse) throws Exception {
		Set<Integer> A = new HashSet<Integer>();
		addNumsToConditionList(A, lastJY.getSxNums());
		addNumsToConditionList(A, lastJY.getTwelveNums());
		addNumsToConditionList(A, lastJY.getSlqNums());
		addNumsToConditionList(A, lastJY.getFdNums());

		Set<Integer> B = new HashSet<Integer>();
		addNumsToConditionList(B, lastJY.getLhNums());
		addNumsToConditionList(B, lastJY.getZx3Nums());
		addNumsToConditionList(B, lastJY.getPdNums());
		addNumsToConditionList(B, lastJY.getWxdsNums());

		Set<Integer> C = new HashSet<Integer>();
		addNumsToConditionList(C, lastJY.getMwNums());
		addNumsToConditionList(C, lastJY.getZx2Nums());
		addNumsToConditionList(C, lastJY.getZx11Nums());
		addNumsToConditionList(C, lastJY.getZx4Nums());

		Set<Integer> D = new HashSet<Integer>();
		addNumsToConditionList(D, lastJY.getZx13Nums());
		addNumsToConditionList(D, lastJY.getZx16Nums());
		addNumsToConditionList(D, lastJY.getZx12Nums());
		addNumsToConditionList(D, lastJY.getZx15Nums());

		Set<Integer> E = new HashSet<Integer>();
		addNumsToConditionList(E, lastJY.getSxzfNums());
		addNumsToConditionList(E, lastJY.getTwelvezfNums());
		addNumsToConditionList(E, lastJY.getSlqzfNums());
		addNumsToConditionList(E, lastJY.getFdzfNums());

		Set<Integer> F = new HashSet<Integer>();
		addNumsToConditionList(F, lastJY.getLhzfNums());
		addNumsToConditionList(F, lastJY.getZx3zfNums());
		addNumsToConditionList(F, lastJY.getPdzfNums());
		addNumsToConditionList(F, lastJY.getWxdszfNums());

		Set<Integer> G = new HashSet<Integer>();
		addNumsToConditionList(G, lastJY.getMwzfNums());
		addNumsToConditionList(G, lastJY.getZx2zfNums());
		addNumsToConditionList(G, lastJY.getZx11zfNums());
		addNumsToConditionList(G, lastJY.getZx4zfNums());

		Set<Integer> H = new HashSet<Integer>();
		addNumsToConditionList(H, lastJY.getZx13zfNums());
		addNumsToConditionList(H, lastJY.getZx16zfNums());
		addNumsToConditionList(H, lastJY.getZx12zfNums());
		addNumsToConditionList(H, lastJY.getZx15zfNums());

		data.clearAll();
		Set<Integer>[] nums = collectConditionList(A, B, C, D, E, F, G, H, reverse);
		assembleDsxJY(data, nums, true, reverse);
	}

	protected void calDsxJYForJHB(DsxJyYz data, DsxJY lastJY, boolean reverse) throws Exception {
		Set<Integer> A = new HashSet<Integer>();
		addNumsToConditionList(A, lastJY.getSxNums());
		addNumsToConditionList(A, lastJY.getTwelveNums());
		addNumsToConditionList(A, lastJY.getSlqNums());

		Set<Integer> B = new HashSet<Integer>();
		addNumsToConditionList(B, lastJY.getLhNums());
		addNumsToConditionList(B, lastJY.getZx3Nums());
		addNumsToConditionList(B, lastJY.getPdNums());

		Set<Integer> C = new HashSet<Integer>();
		addNumsToConditionList(C, lastJY.getMwNums());
		addNumsToConditionList(C, lastJY.getZx2Nums());
		addNumsToConditionList(C, lastJY.getZx11Nums());

		Set<Integer> D = new HashSet<Integer>();
		addNumsToConditionList(D, lastJY.getZx13Nums());
		addNumsToConditionList(D, lastJY.getZx16Nums());
		addNumsToConditionList(D, lastJY.getZx12Nums());

		Set<Integer> E = new HashSet<Integer>();
		addNumsToConditionList(E, lastJY.getSxzfNums());
		addNumsToConditionList(E, lastJY.getTwelvezfNums());
		addNumsToConditionList(E, lastJY.getSlqzfNums());

		Set<Integer> F = new HashSet<Integer>();
		addNumsToConditionList(F, lastJY.getLhzfNums());
		addNumsToConditionList(F, lastJY.getZx3zfNums());
		addNumsToConditionList(F, lastJY.getPdzfNums());

		Set<Integer> G = new HashSet<Integer>();
		addNumsToConditionList(G, lastJY.getMwzfNums());
		addNumsToConditionList(G, lastJY.getZx2zfNums());
		addNumsToConditionList(G, lastJY.getZx11zfNums());

		Set<Integer> H = new HashSet<Integer>();
		addNumsToConditionList(H, lastJY.getZx13zfNums());
		addNumsToConditionList(H, lastJY.getZx16zfNums());
		addNumsToConditionList(H, lastJY.getZx12zfNums());

		data.clearAll();
		Set<Integer>[] nums = collectConditionList(A, B, C, D, E, F, G, H, reverse);
		assembleDsxJY(data, nums, true, reverse);
	}

	protected void assembleDsxJY(DsxJyYz data, Collection<Integer>[] arr, boolean qc, boolean reverse) throws Exception {
		List<XbwJYSub> all = new ArrayList<XbwJYSub>();
		for (int i = 0; i < arr.length; i++) {
			for (int num : arr[i]) {
				boolean found = false;
				for (XbwJYSub sub : all) {
					if (num == sub.getNum()) {
						sub.setCount(sub.getCount() + 1);
						found = true;
						break;
					}
				}
				if (!found) {
					XbwJYSub sub = new XbwJYSub();
					sub.setNum(num);
					sub.setCount(1);
					all.add(sub);
				}
			}
		}
		for (XbwJYSub sub : all) {
			Method m = null;
			if (sub.getCount() < 11) {
				m = ReflectionUtils.findMethod(data.getClass(), "getC" + sub.getCount());
			} else {
				m = ReflectionUtils.findMethod(data.getClass(), "getC11");
			}
			List<Integer> values = (List<Integer>) m.invoke(data);
			values.add(sub.getNum());
			Collections.sort(values);
		}
		data.assemble(qc, reverse);
	}

	@Async
	public Future<Exception> calFxSwA() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			Page<FxSw1> result1 = null;
			Page<FxSw2> result2 = null;
			Page<FxSw3> result3 = null;
			Page<FxSw4> result4 = null;
			Page<FxSw5> result5 = null;
			Page<FxSw6> result6 = null;
			Page<FxSw7> result7 = null;
			Page<FxSw8> result8 = null;
			Page<FxSw9> result9 = null;
			Page<FxSw10> result10 = null;
			Page<FxSw11> result11 = null;
			Page<FxSw12> result12 = null;
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			do {
				result1 = repositories.fxsw1Repository.findAll(request);
				result2 = repositories.fxsw2Repository.findAll(request);
				result3 = repositories.fxsw3Repository.findAll(request);
				result4 = repositories.fxsw4Repository.findAll(request);
				result5 = repositories.fxsw5Repository.findAll(request);
				result6 = repositories.fxsw6Repository.findAll(request);
				result7 = repositories.fxsw7Repository.findAll(request);
				result8 = repositories.fxsw8Repository.findAll(request);
				result9 = repositories.fxsw9Repository.findAll(request);
				result10 = repositories.fxsw10Repository.findAll(request);
				result11 = repositories.fxsw11Repository.findAll(request);
				result12 = repositories.fxsw12Repository.findAll(request);
				if (result1 != null && result1.hasContent()) {
					for (int i = 0; i < result1.getContent().size(); i++) {
						List<FxSw> fxDatas = new ArrayList<FxSw>();
						fxDatas.add(result1.getContent().get(i));
						fxDatas.add(result2.getContent().get(i));
						fxDatas.add(result3.getContent().get(i));
						fxDatas.add(result4.getContent().get(i));
						fxDatas.add(result5.getContent().get(i));
						fxDatas.add(result6.getContent().get(i));
						fxDatas.add(result7.getContent().get(i));
						fxDatas.add(result8.getContent().get(i));
						fxDatas.add(result9.getContent().get(i));
						fxDatas.add(result10.getContent().get(i));
						fxDatas.add(result11.getContent().get(i));
						fxDatas.add(result12.getContent().get(i));
						futures.add(repositories.yzService.calFxSwAForSpecific(fxDatas));
					}
				}
				request = request.next();
			} while (result1 != null && result1.hasNext());
			CommonUtil.sleep(futures, 100);
			logger.info("End of calFxSwA...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	protected void calListForFxSwA(FxSw fxData, int pos, String field, Set<String> allNums, Set<String> allNonWQNums,
			Set<String> allYzNums, Set<String> allZfNums, Set<String> allYzNumsForJh147, Set<String> allYzNumsForJh258,
			Set<String> allYzNumsForJh369, Set<String> allZfNumsForJh147, Set<String> allZfNumsForJh258,
			Set<String> allZfNumsForJh369, Set<String> allNonWQNumsForJh1, Set<String> allNonWQNumsForJh2,
			Set<String> allNonWQNumsForJh3, Set<String> allNonWQNumsForJh4, Set<String> allNonWQNumsForJh5,
			Set<String> allNonWQNumsForJh6, Set<String> allNonWQNumsForJh7, Set<String> allNonWQNumsForJh8,
			Set<String> allNonWQNumsForJh9) throws Exception {
		List<String> nums = new ArrayList<String>();
		String numsStr = (String) ReflectionUtils.findMethod(FxSw.class, "get" + field + "Nums").invoke(fxData);
		if (numsStr != null) {
			nums = Arrays.asList(numsStr.split(",\\s*"));
		}
		if (pos < 12) {
			allNums.addAll(nums);
		}
		if (pos < 11) {
			if (allNonWQNums != null) {
				allNonWQNums.addAll(nums);
			}
			if (allYzNumsForJh147 != null) {
				allYzNumsForJh147.addAll(nums);
			}
			if (allZfNumsForJh147 != null) {
				allZfNumsForJh147.addAll(nums);
			}
		}
		if (pos < 10) {
			if (allYzNums != null) {
				allYzNums.addAll(nums);
			} else if (allZfNums != null) {
				allZfNums.addAll(nums);
			}
			if (allYzNumsForJh258 != null) {
				allYzNumsForJh258.addAll(nums);
			}
			if (allZfNumsForJh258 != null) {
				allZfNumsForJh258.addAll(nums);
			}
			if (allNonWQNumsForJh1 != null) {
				allNonWQNumsForJh1.addAll(nums);
			}
			if (allNonWQNumsForJh4 != null) {
				allNonWQNumsForJh4.addAll(nums);
			}
			if (allNonWQNumsForJh7 != null) {
				allNonWQNumsForJh7.addAll(nums);
			}
		}
		if (pos < 9) {
			if (allYzNumsForJh369 != null) {
				allYzNumsForJh369.addAll(nums);
			}
			if (allZfNumsForJh369 != null) {
				allZfNumsForJh369.addAll(nums);
			}
			if (allNonWQNumsForJh2 != null) {
				allNonWQNumsForJh2.addAll(nums);
			}
			if (allNonWQNumsForJh5 != null) {
				allNonWQNumsForJh5.addAll(nums);
			}
			if (allNonWQNumsForJh8 != null) {
				allNonWQNumsForJh8.addAll(nums);
			}
		}
		if (pos < 8) {
			if (allNonWQNumsForJh3 != null) {
				allNonWQNumsForJh3.addAll(nums);
			}
			if (allNonWQNumsForJh6 != null) {
				allNonWQNumsForJh6.addAll(nums);
			}
			if (allNonWQNumsForJh9 != null) {
				allNonWQNumsForJh9.addAll(nums);
			}
		}
	}

	@Async
	public Future<Exception> calFxSwAForSpecific(List<FxSw> fxDatas) {
		Exception t = null;
		try {
			FxSw swData = fxDatas.get(0);
			FxSwA data = repositories.fxSwARepository.findByYearAndPhase(swData.getYear(), swData.getPhase());
			if (data == null) {
				data = new FxSwA();
				data.setYear(swData.getYear());
				data.setPhase(swData.getPhase());
				data.setDate(swData.getDate());
			}
			KaiJiang kj = repositories.kaiJiangRepository.findByYearAndPhase(swData.getYear(), swData.getPhase());
			data.setSpecialNum(kj.getSpecialNum());

			Map<String, Integer> mapForAForJH = new HashMap<String, Integer>();
			Map<String, Integer> mapForAForJH1 = new HashMap<String, Integer>();
			Map<String, Integer> mapForAForJH2 = new HashMap<String, Integer>();
			Map<String, Integer> mapForAForJH3 = new HashMap<String, Integer>();
			Map<String, Integer> mapForAForJH4 = new HashMap<String, Integer>();
			Map<String, Integer> mapForAForJH5 = new HashMap<String, Integer>();
			Map<String, Integer> mapForAForJH6 = new HashMap<String, Integer>();
			Map<String, Integer> mapForAForJH7 = new HashMap<String, Integer>();
			Map<String, Integer> mapForAForJH8 = new HashMap<String, Integer>();
			Map<String, Integer> mapForAForJH9 = new HashMap<String, Integer>();
			Map<String, Integer> mapForAll = new HashMap<String, Integer>();
			Map<String, Integer> mapForAllYz = new HashMap<String, Integer>();
			Map<String, Integer> mapForAllZf = new HashMap<String, Integer>();
			Map<String, Integer> mapForAllNonWQ = new HashMap<String, Integer>();
			int pos = 1;
			for (FxSw fxData : fxDatas) {
				Set<String> allNums = new HashSet<String>();
				Set<String> allYzNums = new HashSet<String>();
				Set<String> allZfNums = new HashSet<String>();
				Set<String> allNonWQNums = new HashSet<String>();
				Set<String> allYzNumsForJh147 = new HashSet<String>();
				Set<String> allYzNumsForJh258 = new HashSet<String>();
				Set<String> allYzNumsForJh369 = new HashSet<String>();
				Set<String> allZfNumsForJh147 = new HashSet<String>();
				Set<String> allZfNumsForJh258 = new HashSet<String>();
				Set<String> allZfNumsForJh369 = new HashSet<String>();
				Set<String> allNonWQNumsForJh1 = new HashSet<String>();
				Set<String> allNonWQNumsForJh2 = new HashSet<String>();
				Set<String> allNonWQNumsForJh3 = new HashSet<String>();
				Set<String> allNonWQNumsForJh4 = new HashSet<String>();
				Set<String> allNonWQNumsForJh5 = new HashSet<String>();
				Set<String> allNonWQNumsForJh6 = new HashSet<String>();
				Set<String> allNonWQNumsForJh7 = new HashSet<String>();
				Set<String> allNonWQNumsForJh8 = new HashSet<String>();
				Set<String> allNonWQNumsForJh9 = new HashSet<String>();

				calListForFxSwA(fxData, pos, "Bs", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2, allNonWQNumsForJh3,
						allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7, allNonWQNumsForJh8,
						allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Bszf", allNums, allNonWQNums, null, null, null, null, allYzNumsForJh369,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Ds", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2, allNonWQNumsForJh3,
						allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7, allNonWQNumsForJh8,
						allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Dszf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Fd", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2, allNonWQNumsForJh3,
						allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7, allNonWQNumsForJh8,
						allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Fdzf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Lh", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2, allNonWQNumsForJh3,
						allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7, allNonWQNumsForJh8,
						allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Lhzf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Mw", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2, allNonWQNumsForJh3,
						allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7, allNonWQNumsForJh8,
						allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Mwzf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Pd", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2, allNonWQNumsForJh3,
						allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7, allNonWQNumsForJh8,
						allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Pdzf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Qiw", allNums, null, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, null, null, null, null, null, null, null, null, null);
				calListForFxSwA(fxData, pos, "Qiwzf", allNums, null, null, allZfNums, null, null, null, allZfNumsForJh147,
						allZfNumsForJh258, allZfNumsForJh369, null, null, null, null, null, null, null, null, null);
				calListForFxSwA(fxData, pos, "Qq", allNums, null, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, null, null, null, null, null, null, null, null, null);
				calListForFxSwA(fxData, pos, "Qqzf", allNums, null, null, allZfNums, null, null, null, allZfNumsForJh147,
						allZfNumsForJh258, allZfNumsForJh369, null, null, null, null, null, null, null, null, null);
				calListForFxSwA(fxData, pos, "Slq", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Slqzf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Sw", allNums, null, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, null, null, null, null, null, null, null, null, null);
				calListForFxSwA(fxData, pos, "Swzf", allNums, null, null, allZfNums, null, null, null, allZfNumsForJh147,
						allZfNumsForJh258, allZfNumsForJh369, null, null, null, null, null, null, null, null, null);
				calListForFxSwA(fxData, pos, "Sx", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2, allNonWQNumsForJh3,
						allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7, allNonWQNumsForJh8,
						allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Sxzf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Twelve", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Twelvezf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Wx", allNums, null, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, null, null, null, null, null, null, null, null, null);
				calListForFxSwA(fxData, pos, "Wxzf", allNums, null, null, allZfNums, null, null, null, allZfNumsForJh147,
						allZfNumsForJh258, allZfNumsForJh369, null, null, null, null, null, null, null, null, null);
				calListForFxSwA(fxData, pos, "Wxds", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Wxdszf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zs", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147, allYzNumsForJh258,
						allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2, allNonWQNumsForJh3,
						allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7, allNonWQNumsForJh8,
						allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zszf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx1", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx1zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx2", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx2zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx3", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx3zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx4", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx4zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx5", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx5zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx6", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx6zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx7", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx7zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx8", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx8zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx9", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, null, null, null, null, null, null, null, null,
						null);
				calListForFxSwA(fxData, pos, "Zx9zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, null, null, null, null, null, null, null, null,
						null);
				calListForFxSwA(fxData, pos, "Zx10", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, null, null, null, null, null, null, null, null,
						null);
				calListForFxSwA(fxData, pos, "Zx10zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, null, null, null, null, null, null, null, null,
						null);
				calListForFxSwA(fxData, pos, "Zx11", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx11zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx12", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx12zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx13", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx13zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx14", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx14zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx15", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx15zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx16", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx16zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx17", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx17zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx18", allNums, allNonWQNums, allYzNums, null, allYzNumsForJh147,
						allYzNumsForJh258, allYzNumsForJh369, null, null, null, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);
				calListForFxSwA(fxData, pos, "Zx18zf", allNums, allNonWQNums, null, allZfNums, null, null, null,
						allZfNumsForJh147, allZfNumsForJh258, allZfNumsForJh369, allNonWQNumsForJh1, allNonWQNumsForJh2,
						allNonWQNumsForJh3, allNonWQNumsForJh4, allNonWQNumsForJh5, allNonWQNumsForJh6, allNonWQNumsForJh7,
						allNonWQNumsForJh8, allNonWQNumsForJh9);

				if (pos < 12) {
					calAllFzForFxSwA(mapForAll, getFz(allNums));
				}

				Set<String> numSetForJh = new HashSet<String>();
				Set<String> numSetForJh1 = new HashSet<String>();
				Set<String> numSetForJh2 = new HashSet<String>();
				Set<String> numSetForJh3 = new HashSet<String>();
				Set<String> numSetForJh4 = new HashSet<String>();
				Set<String> numSetForJh5 = new HashSet<String>();
				Set<String> numSetForJh6 = new HashSet<String>();
				Set<String> numSetForJh7 = new HashSet<String>();
				Set<String> numSetForJh8 = new HashSet<String>();
				Set<String> numSetForJh9 = new HashSet<String>();
				if (pos < 11) {
					calAllFzForFxSwA(mapForAllNonWQ, getFz(allNonWQNums));
					numSetForJh.addAll(getFz(allNonWQNums));
					numSetForJh1.addAll(getFz(allYzNumsForJh147));
					numSetForJh1.addAll(getFz(allZfNumsForJh147));
					numSetForJh4.addAll(getFz(allYzNumsForJh147));
					numSetForJh4.addAll(getFz(allZfNumsForJh147));
					numSetForJh7.addAll(getFz(allYzNumsForJh147));
					numSetForJh7.addAll(getFz(allZfNumsForJh147));
				}

				if (pos < 10) {
					calAllFzForFxSwA(mapForAllYz, getFz(allYzNums));
					calAllFzForFxSwA(mapForAllZf, getFz(allZfNums));
					numSetForJh.addAll(getFz(allYzNums));
					numSetForJh.addAll(getFz(allZfNums));
					numSetForJh1.addAll(getFz(allNonWQNumsForJh1));
					numSetForJh4.addAll(getFz(allNonWQNumsForJh4));
					numSetForJh7.addAll(getFz(allNonWQNumsForJh7));
					numSetForJh2.addAll(getFz(allYzNumsForJh258));
					numSetForJh2.addAll(getFz(allZfNumsForJh258));
					numSetForJh5.addAll(getFz(allYzNumsForJh258));
					numSetForJh5.addAll(getFz(allZfNumsForJh258));
					numSetForJh8.addAll(getFz(allYzNumsForJh258));
					numSetForJh8.addAll(getFz(allZfNumsForJh258));
				}

				if (pos < 9) {
					numSetForJh2.addAll(getFz(allNonWQNumsForJh2));
					numSetForJh5.addAll(getFz(allNonWQNumsForJh5));
					numSetForJh8.addAll(getFz(allNonWQNumsForJh8));
					numSetForJh3.addAll(getFz(allYzNumsForJh369));
					numSetForJh3.addAll(getFz(allZfNumsForJh369));
					numSetForJh6.addAll(getFz(allYzNumsForJh369));
					numSetForJh6.addAll(getFz(allZfNumsForJh369));
					numSetForJh9.addAll(getFz(allYzNumsForJh369));
					numSetForJh9.addAll(getFz(allZfNumsForJh369));
				}

				if (pos < 8) {
					numSetForJh3.addAll(getFz(allNonWQNumsForJh3));
					numSetForJh6.addAll(getFz(allNonWQNumsForJh6));
					numSetForJh9.addAll(getFz(allNonWQNumsForJh9));
				}

				calAllFzForFxSwA(mapForAForJH, numSetForJh);
				calAllFzForFxSwA(mapForAForJH1, numSetForJh1); // yz 10, zf 10, nonWQ 9
				calAllFzForFxSwA(mapForAForJH2, numSetForJh2); // yz 10, zf 10, nonWQ 8
				calAllFzForFxSwA(mapForAForJH3, numSetForJh3); // yz 10, zf 10, nonWQ 7
				calAllFzForFxSwA(mapForAForJH4, numSetForJh4); // yz 9, zf 9, nonWQ 9
				calAllFzForFxSwA(mapForAForJH5, numSetForJh5); // yz 9, zf 9, nonWQ 8
				calAllFzForFxSwA(mapForAForJH6, numSetForJh6); // yz 9, zf 9, nonWQ 7
				calAllFzForFxSwA(mapForAForJH7, numSetForJh7); // yz 8, zf 8, nonWQ 9
				calAllFzForFxSwA(mapForAForJH8, numSetForJh8); // yz 8, zf 8, nonWQ 8
				calAllFzForFxSwA(mapForAForJH9, numSetForJh9); // yz 8, zf 8, nonWQ 7
				pos++;
			}

			FxSwA temp = getTempDataForFxSwA(mapForAll);
			data.setA1NumsForAll(temp.getA1NumsForAll());
			data.setA2NumsForAll(temp.getA2NumsForAll());
			data.setA3NumsForAll(temp.getA3NumsForAll());
			data.setA3pNumsForAll(temp.getA3pNumsForAll());
			data.setArNumsForAll(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForAll(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForAll(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAllYz);
			data.setA1NumsForAllYz(temp.getA1NumsForAll());
			data.setA2NumsForAllYz(temp.getA2NumsForAll());
			data.setA3NumsForAllYz(temp.getA3NumsForAll());
			data.setA3pNumsForAllYz(temp.getA3pNumsForAll());
			data.setArNumsForAllYz(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForAllYz(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForAllYz(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAllZf);
			data.setA1NumsForAllZf(temp.getA1NumsForAll());
			data.setA2NumsForAllZf(temp.getA2NumsForAll());
			data.setA3NumsForAllZf(temp.getA3NumsForAll());
			data.setA3pNumsForAllZf(temp.getA3pNumsForAll());
			data.setArNumsForAllZf(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForAllZf(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForAllZf(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAllNonWQ);
			data.setA1NumsForNonWQ(temp.getA1NumsForAll());
			data.setA2NumsForNonWQ(temp.getA2NumsForAll());
			data.setA3NumsForNonWQ(temp.getA3NumsForAll());
			data.setA3pNumsForNonWQ(temp.getA3pNumsForAll());
			data.setArNumsForNonWQ(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForNonWQ(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForNonWQ(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAForJH);
			data.setA1NumsForJh(temp.getA1NumsForAll());
			data.setA2NumsForJh(temp.getA2NumsForAll());
			data.setA3NumsForJh(temp.getA3NumsForAll());
			data.setA3pNumsForJh(temp.getA3pNumsForAll());
			data.setArNumsForJh(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForJh(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForJh(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAForJH1);
			data.setA1NumsForJh1(temp.getA1NumsForAll());
			data.setA2NumsForJh1(temp.getA2NumsForAll());
			data.setA3NumsForJh1(temp.getA3NumsForAll());
			data.setA3pNumsForJh1(temp.getA3pNumsForAll());
			data.setArNumsForJh1(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForJh1(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForJh1(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAForJH2);
			data.setA1NumsForJh2(temp.getA1NumsForAll());
			data.setA2NumsForJh2(temp.getA2NumsForAll());
			data.setA3NumsForJh2(temp.getA3NumsForAll());
			data.setA3pNumsForJh2(temp.getA3pNumsForAll());
			data.setArNumsForJh2(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForJh2(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForJh2(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAForJH3);
			data.setA1NumsForJh3(temp.getA1NumsForAll());
			data.setA2NumsForJh3(temp.getA2NumsForAll());
			data.setA3NumsForJh3(temp.getA3NumsForAll());
			data.setA3pNumsForJh3(temp.getA3pNumsForAll());
			data.setArNumsForJh3(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForJh3(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForJh3(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAForJH4);
			data.setA1NumsForJh4(temp.getA1NumsForAll());
			data.setA2NumsForJh4(temp.getA2NumsForAll());
			data.setA3NumsForJh4(temp.getA3NumsForAll());
			data.setA3pNumsForJh4(temp.getA3pNumsForAll());
			data.setArNumsForJh4(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForJh4(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForJh4(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAForJH5);
			data.setA1NumsForJh5(temp.getA1NumsForAll());
			data.setA2NumsForJh5(temp.getA2NumsForAll());
			data.setA3NumsForJh5(temp.getA3NumsForAll());
			data.setA3pNumsForJh5(temp.getA3pNumsForAll());
			data.setArNumsForJh5(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForJh5(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForJh5(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAForJH6);
			data.setA1NumsForJh6(temp.getA1NumsForAll());
			data.setA2NumsForJh6(temp.getA2NumsForAll());
			data.setA3NumsForJh6(temp.getA3NumsForAll());
			data.setA3pNumsForJh6(temp.getA3pNumsForAll());
			data.setArNumsForJh6(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForJh6(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForJh6(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAForJH7);
			data.setA1NumsForJh7(temp.getA1NumsForAll());
			data.setA2NumsForJh7(temp.getA2NumsForAll());
			data.setA3NumsForJh7(temp.getA3NumsForAll());
			data.setA3pNumsForJh7(temp.getA3pNumsForAll());
			data.setArNumsForJh7(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForJh7(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForJh7(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAForJH8);
			data.setA1NumsForJh8(temp.getA1NumsForAll());
			data.setA2NumsForJh8(temp.getA2NumsForAll());
			data.setA3NumsForJh8(temp.getA3NumsForAll());
			data.setA3pNumsForJh8(temp.getA3pNumsForAll());
			data.setArNumsForJh8(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForJh8(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForJh8(temp.getA1A2A3NumsForAll());

			temp = getTempDataForFxSwA(mapForAForJH9);
			data.setA1NumsForJh9(temp.getA1NumsForAll());
			data.setA2NumsForJh9(temp.getA2NumsForAll());
			data.setA3NumsForJh9(temp.getA3NumsForAll());
			data.setA3pNumsForJh9(temp.getA3pNumsForAll());
			data.setArNumsForJh9(temp.getArNumsForAll());
			data.setArA2A3A3PNumsForJh9(temp.getArA2A3A3PNumsForAll());
			data.setA1A2A3NumsForJh9(temp.getA1A2A3NumsForAll());

			repositories.fxSwARepository.save(data);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	protected Set<String> getFz(Collection<String> allNums) {
		Set<String> allFzNums = new HashSet<String>();
		for (int i = 1; i < 50; i++) {
			String num = String.valueOf(i);
			if (!allNums.contains(num)) {
				allFzNums.add(num);
			}
		}
		return allFzNums;
	}

	protected void calAllFzForFxSwA(Map<String, Integer> map, Collection<String> allNums) {
		for (String num : allNums) {
			Integer count = map.get(num);
			if (count == null) {
				map.put(num, 1);
			} else {
				map.put(num, count + 1);
			}
		}
	}

	protected FxSwA getTempDataForFxSwA(Map<String, Integer> map) {
		List<String> a1Nums = new ArrayList<String>();
		List<String> a2Nums = new ArrayList<String>();
		List<String> a3Nums = new ArrayList<String>();
		List<String> a3pNums = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			switch (entry.getValue()) {
			case 1:
				a1Nums.add(entry.getKey());
				break;
			case 2:
				a2Nums.add(entry.getKey());
				break;
			case 3:
				a3Nums.add(entry.getKey());
				break;
			default:
				a3pNums.add(entry.getKey());
				break;
			}
		}
		List<String> a1a2a3Nums = new ArrayList<String>(a1Nums);
		a1a2a3Nums.addAll(a2Nums);
		a1a2a3Nums.addAll(a3Nums);
		List<String> arNums = new ArrayList<String>(getFz(map.keySet()));
		Set<String> a2a3a3pNums = new HashSet<String>(a2Nums);
		a2a3a3pNums.addAll(a3Nums);
		a2a3a3pNums.addAll(a3pNums);
		List<String> arA2A3A3PNums = new ArrayList<String>(getFz(a2a3a3pNums));

		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
			}
		};

		Collections.sort(a1Nums, comparator);
		Collections.sort(a2Nums, comparator);
		Collections.sort(a3Nums, comparator);
		Collections.sort(a3pNums, comparator);
		Collections.sort(arNums, comparator);
		Collections.sort(arA2A3A3PNums, comparator);
		Collections.sort(a1a2a3Nums, comparator);

		FxSwA data = new FxSwA();
		data.setA1NumsForAll(Joiner.on(",").join(a1Nums));
		data.setA2NumsForAll(Joiner.on(",").join(a2Nums));
		data.setA3NumsForAll(Joiner.on(",").join(a3Nums));
		data.setA3pNumsForAll(Joiner.on(",").join(a3pNums));
		data.setArNumsForAll(Joiner.on(",").join(arNums));
		data.setArA2A3A3PNumsForAll(Joiner.on(",").join(arA2A3A3PNums));
		data.setA1A2A3NumsForAll(Joiner.on(",").join(a1a2a3Nums));
		return data;
	}

}
