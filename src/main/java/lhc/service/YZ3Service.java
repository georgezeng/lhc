package lhc.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
}
