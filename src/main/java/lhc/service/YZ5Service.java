package lhc.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import lhc.domain.DsyJyYz;
import lhc.domain.DsyMaxJyDB;
import lhc.domain.DsyMaxJyJDB;
import lhc.domain.DsyMaxJyJHB;
import lhc.domain.DsyMaxJyJQB;
import lhc.domain.DsyMaxJySJB;
import lhc.domain.DsyMinJyDB;
import lhc.domain.DsyMinJyJDB;
import lhc.domain.DsyMinJyJHB;
import lhc.domain.DsyMinJyJQB;
import lhc.domain.DsyMinJySJB;
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
import lhc.domain.LhYz;
import lhc.domain.MwYz;
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
import lhc.dto.DsxJY;
import lhc.dto.DsyJY;
import lhc.dto.TmYzInfo;
import lhc.dto.XbwJYSub;
import lhc.enums.SX;
import lhc.repository.jpa.BaseYzDao;
import lhc.repository.jpa.BaseYzRepository;
import lhc.util.CommonUtil;
import lhc.util.DateUtil;

//@Service
@SuppressWarnings("unchecked")
public class YZ5Service extends YZ4Service {
	@Async
	public Future<Exception> calDsyMinJY() {
		return calDsyJY(DsyMinJySJB.class, DsyMinJyDB.class, DsyMinJyJQB.class, DsyMinJyJDB.class, DsyMinJyJHB.class,
				repositories.dsyMinJySJBRepository, repositories.dsyMinJyDBRepository, repositories.dsyMinJyJQBRepository,
				repositories.dsyMinJyJDBRepository, repositories.dsyMinJyJHBRepository, false);
	}

	@Async
	public Future<Exception> calDsyMaxJY() {
		return calDsyJY(DsyMaxJySJB.class, DsyMaxJyDB.class, DsyMaxJyJQB.class, DsyMaxJyJDB.class, DsyMaxJyJHB.class,
				repositories.dsyMaxJySJBRepository, repositories.dsyMaxJyDBRepository, repositories.dsyMaxJyJQBRepository,
				repositories.dsyMaxJyJDBRepository, repositories.dsyMaxJyJHBRepository, true);
	}

	public <SJ extends DsyJyYz, D extends DsyJyYz, JQ extends DsyJyYz, JD extends DsyJyYz, JH extends DsyJyYz> Future<Exception> calDsyJY(
			Class<SJ> sjbClass, Class<D> dbClass, Class<JQ> jqbClass, Class<JD> jdbClass, Class<JH> jhbClass, BaseYzRepository<SJ> sjbRepository,
			BaseYzRepository<D> dbRepository, BaseYzRepository<JQ> jqbRepository, BaseYzRepository<JD> jdbRepository,
			BaseYzRepository<JH> jhbRepository, boolean max) {
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
			SJ unknownDataForSJB = sjbRepository.findByYearAndPhase(0, 0);
			D unknownDataForDB = dbRepository.findByYearAndPhase(0, 0);
			JQ unknownDataForJQB = jqbRepository.findByYearAndPhase(0, 0);
			JD unknownDataForJDB = jdbRepository.findByYearAndPhase(0, 0);
			JH unknownDataForJHB = jhbRepository.findByYearAndPhase(0, 0);
			if (unknownDataForSJB == null) {
				unknownDataForSJB = sjbClass.newInstance();
				unknownDataForSJB.setYear(0);
				unknownDataForSJB.setPhase(0);
				unknownDataForSJB.setDate("unknown");
				sjbRepository.save(unknownDataForSJB);
			}
			if (unknownDataForDB == null) {
				unknownDataForDB = dbClass.newInstance();
				unknownDataForDB.setYear(0);
				unknownDataForDB.setPhase(0);
				unknownDataForDB.setDate("unknown");
				dbRepository.save(unknownDataForDB);
			}
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
			List<Future<DsyJY>> jys = new ArrayList<Future<DsyJY>>();
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
						jys.add(repositories.yzService.doCalDsyJY(result1, result2, result3, result4, result5, result6, result7, result8, result9,
								result10, result11, result12, i, max));
					}
				}
				request = request.next();
			} while (result1 != null && result1.hasNext());
			List<DsyJY> list = CommonUtil.getAsyncResults(jys, 100);
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			DsyJY lastJY = null;
			for (DsyJY jy : list) {
				if (lastJY != null) {
					futures.add(repositories.yzService.doCalDsyJY(sjbClass, dbClass, jqbClass, jdbClass, jhbClass, sjbRepository, dbRepository,
							jqbRepository, jdbRepository, jhbRepository, jy, lastJY));
				}
				lastJY = jy;
			}
			CommonUtil.sleep(futures, 100);
			if (lastJY != null) {
				calDsyJYForSJB(unknownDataForSJB, lastJY, true);
				calDsyJYForSJB(unknownDataForSJB, lastJY, false);
				sjbRepository.save(unknownDataForSJB);

				calDsyJYForDB(unknownDataForDB, lastJY, true);
				calDsyJYForDB(unknownDataForDB, lastJY, false);
				dbRepository.save(unknownDataForDB);

				calDsyJYForJQB(unknownDataForJQB, lastJY, true);
				calDsyJYForJQB(unknownDataForJQB, lastJY, false);
				jqbRepository.save(unknownDataForJQB);

				calDsyJYForJDB(unknownDataForJDB, lastJY, true);
				calDsyJYForJDB(unknownDataForJDB, lastJY, false);
				jdbRepository.save(unknownDataForJDB);

				calDsyJYForJHB(unknownDataForJHB, lastJY, true);
				calDsyJYForJHB(unknownDataForJHB, lastJY, false);
				jhbRepository.save(unknownDataForJHB);
			}

		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public <SJ extends DsyJyYz, D extends DsyJyYz, JQ extends DsyJyYz, JD extends DsyJyYz, JH extends DsyJyYz> Future<Exception> doCalDsyJY(
			Class<SJ> sjbClass, Class<D> dbClass, Class<JQ> jqbClass, Class<JD> jdbClass, Class<JH> jhbClass, BaseYzRepository<SJ> sjbRepository,
			BaseYzRepository<D> dbRepository, BaseYzRepository<JQ> jqbRepository, BaseYzRepository<JD> jdbRepository,
			BaseYzRepository<JH> jhbRepository, DsyJY jy, DsyJY lastJY) {
		Exception t = null;
		try {
			KaiJiang kj = repositories.kaiJiangRepository.findByYearAndPhase(jy.getYear(), jy.getPhase());
			SJ sjb = sjbRepository.findByYearAndPhase(jy.getYear(), jy.getPhase());
			if (sjb == null) {
				sjb = sjbClass.newInstance();
				sjb.setYear(jy.getYear());
				sjb.setPhase(jy.getPhase());
				sjb.setDate(jy.getDate());
				sjb.setSpecialNum(kj.getSpecialNum());
			}
			D db = dbRepository.findByYearAndPhase(jy.getYear(), jy.getPhase());
			if (db == null) {
				db = dbClass.newInstance();
				db.setYear(jy.getYear());
				db.setPhase(jy.getPhase());
				db.setDate(jy.getDate());
				db.setSpecialNum(kj.getSpecialNum());
			}
			JQ jqb = jqbRepository.findByYearAndPhase(jy.getYear(), jy.getPhase());
			if (jqb == null) {
				jqb = jqbClass.newInstance();
				jqb.setYear(jy.getYear());
				jqb.setPhase(jy.getPhase());
				jqb.setDate(jy.getDate());
				jqb.setSpecialNum(kj.getSpecialNum());
			}
			JD jdb = jdbRepository.findByYearAndPhase(jy.getYear(), jy.getPhase());
			if (jdb == null) {
				jdb = jdbClass.newInstance();
				jdb.setYear(jy.getYear());
				jdb.setPhase(jy.getPhase());
				jdb.setDate(jy.getDate());
				jdb.setSpecialNum(kj.getSpecialNum());
			}
			JH jhb = jhbRepository.findByYearAndPhase(jy.getYear(), jy.getPhase());
			if (jhb == null) {
				jhb = jhbClass.newInstance();
				jhb.setYear(jy.getYear());
				jhb.setPhase(jy.getPhase());
				jhb.setDate(jy.getDate());
				jhb.setSpecialNum(kj.getSpecialNum());
			}
			if (lastJY != null) {
				calDsyJYForSJB(sjb, lastJY, true);
				calDsyJYForSJB(sjb, lastJY, false);
				calDsyJYForDB(db, lastJY, true);
				calDsyJYForDB(db, lastJY, false);
				calDsyJYForJQB(jqb, lastJY, true);
				calDsyJYForJQB(jqb, lastJY, false);
				calDsyJYForJDB(jdb, lastJY, true);
				calDsyJYForJDB(jdb, lastJY, false);
				calDsyJYForJHB(jhb, lastJY, true);
				calDsyJYForJHB(jhb, lastJY, false);
			}
			sjbRepository.save(sjb);
			dbRepository.save(db);
			jqbRepository.save(jqb);
			jdbRepository.save(jdb);
			jhbRepository.save(jhb);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	@Async
	public <Q extends DsyJyYz, D extends DsyJyYz, H extends DsyJyYz> Future<DsyJY> doCalDsyJY(Page<FxSw1> result1, Page<FxSw2> result2,
			Page<FxSw3> result3, Page<FxSw4> result4, Page<FxSw5> result5, Page<FxSw6> result6, Page<FxSw7> result7, Page<FxSw8> result8,
			Page<FxSw9> result9, Page<FxSw10> result10, Page<FxSw11> result11, Page<FxSw12> result12, int i, boolean max) throws Exception {
		FxSw temp = result1.getContent().get(i);
		DsyJY jy = new DsyJY();
		jy.setDate(temp.getDate());
		jy.setYear(temp.getYear());
		jy.setPhase(temp.getPhase());
		setFxSwDataForDsy(result1, result2, result3, result4, result5, result6, result7, result8, result9, result10, result11, result12, jy, i, max);
		return new AsyncResult<DsyJY>(jy);
	}

	protected void setFxSwDataForDsy(Page<FxSw1> result1, Page<FxSw2> result2, Page<FxSw3> result3, Page<FxSw4> result4, Page<FxSw5> result5,
			Page<FxSw6> result6, Page<FxSw7> result7, Page<FxSw8> result8, Page<FxSw9> result9, Page<FxSw10> result10, Page<FxSw11> result11,
			Page<FxSw12> result12, DsyJY jy, int i, boolean max) throws Exception {
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

		String[] columns = { "Sx", "Sxzf", "Ds", "Dszf", "Sw", "Swzf", "Mw", "Mwzf", "Lh", "Lhzf", "Bs", "Bszf", "Zs", "Zszf", "Wx", "Wxzf", "Wxds",
				"Wxdszf", "Pd", "Pdzf", "Fd", "Fdzf", "Qq", "Qqzf", "Qiw", "Qiwzf", "Twelve", "Twelvezf", "Slq", "Slqzf", "Zx1", "Zx1zf", "Zx2",
				"Zx2zf", "Zx3", "Zx3zf", "Zx4", "Zx4zf", "Zx5", "Zx5zf", "Zx6", "Zx6zf", "Zx7", "Zx7zf", "Zx8", "Zx8zf", "Zx9", "Zx9zf", "Zx10",
				"Zx10zf", "Zx11", "Zx11zf", "Zx12", "Zx12zf", "Zx13", "Zx13zf", "Zx14", "Zx14zf", "Zx15", "Zx15zf", "Zx16", "Zx16zf", "Zx17",
				"Zx17zf", "Zx18", "Zx18zf" };

		for (String column : columns) {
			for (FxSw fxData : fxDatas) {
				setFxSwDataForABCD(jy, fxData, column, max);
			}
		}

		compareDsyDataForABCD(jy, "Sx", max);
		compareDsyDataForABCD(jy, "Ds", max);
		compareDsyDataForABCD(jy, "Sw", max);
		compareDsyDataForABCD(jy, "Mw", max);
		compareDsyDataForABCD(jy, "Lh", max);
		compareDsyDataForABCD(jy, "Bs", max);
		compareDsyDataForABCD(jy, "Zs", max);
		compareDsyDataForABCD(jy, "Wx", max);
		compareDsyDataForABCD(jy, "Wxds", max);
		compareDsyDataForABCD(jy, "Pd", max);
		compareDsyDataForABCD(jy, "Fd", max);
		compareDsyDataForABCD(jy, "Qq", max);
		compareDsyDataForABCD(jy, "Qiw", max);
		compareDsyDataForABCD(jy, "Twelve", max);
		compareDsyDataForABCD(jy, "Slq", max);
		compareDsyDataForABCD(jy, "Zx1", max);
		compareDsyDataForABCD(jy, "Zx2", max);
		compareDsyDataForABCD(jy, "Zx3", max);
		compareDsyDataForABCD(jy, "Zx4", max);
		compareDsyDataForABCD(jy, "Zx5", max);
		compareDsyDataForABCD(jy, "Zx6", max);
		compareDsyDataForABCD(jy, "Zx7", max);
		compareDsyDataForABCD(jy, "Zx8", max);
		compareDsyDataForABCD(jy, "Zx9", max);
		compareDsyDataForABCD(jy, "Zx10", max);
		compareDsyDataForABCD(jy, "Zx11", max);
		compareDsyDataForABCD(jy, "Zx12", max);
		compareDsyDataForABCD(jy, "Zx13", max);
		compareDsyDataForABCD(jy, "Zx14", max);
		compareDsyDataForABCD(jy, "Zx15", max);
		compareDsyDataForABCD(jy, "Zx16", max);
		compareDsyDataForABCD(jy, "Zx17", max);
		compareDsyDataForABCD(jy, "Zx18", max);

		setSxDataForEFGH(jy);
		setFdDataForEFGH(jy);
		setFxSwDataForEFGH(jy, "Ds", DsYz.class, repositories.dsyzRepository, repositories.dsYzDao, DsNums.FDS, DsNums.NUMS);
		setFxSwDataForEFGH(jy, "Sw", SwYz.class, repositories.swyzRepository, repositories.swYzDao, SwNums.FDS, SwNums.NUMS);
		setFxSwDataForEFGH(jy, "Mw", MwYz.class, repositories.mwyzRepository, repositories.mwYzDao, MwNums.FDS, MwNums.NUMS);
		setFxSwDataForEFGH(jy, "Lh", LhYz.class, repositories.lhyzRepository, repositories.lhYzDao, LhNums.FDS, LhNums.NUMS);
		setFxSwDataForEFGH(jy, "Bs", Bs9qYz.class, repositories.bs9qyzRepository, repositories.bs9qYzDao, Bs9qNums.FDS, Bs9qNums.NUMS);
		setFxSwDataForEFGH(jy, "Zs", ZsYz.class, repositories.zsyzRepository, repositories.zsYzDao, ZsNums.FDS, ZsNums.NUMS);
		setFxSwDataForEFGH(jy, "Wx", WxYz.class, repositories.wxyzRepository, repositories.wxYzDao, WxNums.FDS, WxNums.NUMS);
		setFxSwDataForEFGH(jy, "Wxds", WxdsYz.class, repositories.wxdsyzRepository, repositories.wxdsYzDao, WxDsNums.FDS, WxDsNums.NUMS);
		setFxSwDataForEFGH(jy, "Pd", PdYz.class, repositories.pdyzRepository, repositories.pdYzDao, PdNums.FDS, PdNums.NUMS);
		setFxSwDataForEFGH(jy, "Qq", QqYz.class, repositories.qqyzRepository, repositories.qqYzDao, QqNums.FDS, QqNums.NUMS);
		setFxSwDataForEFGH(jy, "Qiw", QiwYz.class, repositories.qiwYzRepository, repositories.qiwYzDao, QiwNums.FDS, QiwNums.NUMS);
		setFxSwDataForEFGH(jy, "Twelve", TwelveYz.class, repositories.twelveyzRepository, repositories.twelveYzDao, TwelveNums.FDS, TwelveNums.NUMS);
		setFxSwDataForEFGH(jy, "Slq", SlqYz.class, repositories.slqyzRepository, repositories.slqYzDao, SlqNums.FDS, SlqNums.NUMS);
		setFxSwDataForEFGH(jy, "Zx1", Zx1Yz.class, repositories.zx1yzRepository, repositories.zx1yzDao, Zx1Nums.FDS, Zx1Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx2", Zx2Yz.class, repositories.zx2yzRepository, repositories.zx2yzDao, Zx2Nums.FDS, Zx2Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx3", Zx3Yz.class, repositories.zx3yzRepository, repositories.zx3yzDao, Zx3Nums.FDS, Zx3Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx4", Zx4Yz.class, repositories.zx4yzRepository, repositories.zx4yzDao, Zx4Nums.FDS, Zx4Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx5", Zx5Yz.class, repositories.zx5yzRepository, repositories.zx5yzDao, Zx5Nums.FDS, Zx5Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx6", Zx6Yz.class, repositories.zx6yzRepository, repositories.zx6yzDao, Zx6Nums.FDS, Zx6Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx7", Zx7Yz.class, repositories.zx7yzRepository, repositories.zx7yzDao, Zx7Nums.FDS, Zx7Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx8", Zx8Yz.class, repositories.zx8yzRepository, repositories.zx8yzDao, Zx8Nums.FDS, Zx8Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx9", Zx9Yz.class, repositories.zx9yzRepository, repositories.zx9yzDao, Zx9Nums.FDS, Zx9Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx10", Zx10Yz.class, repositories.zx10yzRepository, repositories.zx10yzDao, Zx10Nums.FDS, Zx10Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx11", Zx11Yz.class, repositories.zx11yzRepository, repositories.zx11yzDao, Zx11Nums.FDS, Zx11Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx12", Zx12Yz.class, repositories.zx12yzRepository, repositories.zx12yzDao, Zx12Nums.FDS, Zx12Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx13", Zx13Yz.class, repositories.zx13yzRepository, repositories.zx13yzDao, Zx13Nums.FDS, Zx13Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx14", Zx14Yz.class, repositories.zx14yzRepository, repositories.zx14yzDao, Zx14Nums.FDS, Zx14Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx15", Zx15Yz.class, repositories.zx15yzRepository, repositories.zx15yzDao, Zx15Nums.FDS, Zx15Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx16", Zx16Yz.class, repositories.zx16yzRepository, repositories.zx16yzDao, Zx16Nums.FDS, Zx16Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx17", Zx17Yz.class, repositories.zx17yzRepository, repositories.zx17yzDao, Zx17Nums.FDS, Zx17Nums.NUMS);
		setFxSwDataForEFGH(jy, "Zx18", Zx18Yz.class, repositories.zx18yzRepository, repositories.zx18yzDao, Zx18Nums.FDS, Zx18Nums.NUMS);
	}

	protected void setSxDataForEFGH(DsyJY jy) throws Exception {
		SxYz current = repositories.sxyzRepository.findByYearAndPhase(jy.getYear(), jy.getPhase());
		List<YzSortInfo> infos = new ArrayList<YzSortInfo>();
		int infoPos = 0;
		int posTrend = 1;
		int pos = 0;
		for (SX sx : SX.seq()) {
			Method gm = ReflectionUtils.findMethod(SxYz.class, "get" + sx.name());
			YzSortInfo info = new YzSortInfo();
			info.setYzData(sx);
			info.setYz((Integer) gm.invoke(current));
			infos.add(info);
			if (info.getYz() != null && info.getYz() == current.getLastYz()) {
				infoPos = pos;
			}
			pos++;
		}
		Collections.sort(infos, new YzSortComparator());
		SX sx = null;
		if (current.getLastYz() != null) {
			YzSortInfo d1 = infos.get(infos.size() - 1);
			YzSortInfo s1 = infos.get(0);
			if (s1.getYz() != null) {
				if (s1.getYz().equals(current.getLastYz())) {
					if (current.getTotalAvg() != null) {
						if (current.getTotal() != null) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								sx = (SX) d1.getYzData();
							} else {
								YzSortInfo d2 = infos.get(infos.size() - 2);
								sx = (SX) d2.getYzData();
							}
						} else {
							sx = (SX) d1.getYzData();
						}
					} else {
						YzSortInfo d2 = infos.get(infos.size() - 2);
						sx = (SX) d2.getYzData();
					}
				}
			}

			if (sx == null) {
				if (d1.getYz() != null) {
					if (d1.getYz().equals(current.getLastYz())) {
						if (current.getTotalAvg() != null) {
							if (current.getTotal() != null) {
								if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
									sx = (SX) s1.getYzData();
								} else {
									YzSortInfo s2 = infos.get(1);
									sx = (SX) s2.getYzData();
								}
							} else {
								sx = (SX) d1.getYzData();
							}
						} else {
							YzSortInfo s2 = infos.get(1);
							sx = (SX) s2.getYzData();
						}
					}
				}
			}
		}

		if (current.getTotalAvg() != null) {
			if (current.getTotal() != null) {
				if (sx == null) {
					List<SxYz> datas = repositories.sxYzDao.queryUpTo(jy.getYear(), jy.getPhase());
					String trend = "";
					Integer lastYz = null;
					for (SxYz sxyz : datas) {
						Integer yz = sxyz.getLastYz();
						if (lastYz != null) {
							if (yz != null) {
								if (yz.compareTo(lastYz) > 0) {
									trend += "U";
								} else if (yz.compareTo(lastYz) == 0) {
									trend += "P";
								} else {
									trend += "D";
								}
							} else {
								break;
							}
						}
						lastYz = yz;
					}
					if (trend.length() == 2) {
						if ("UU".equals(trend) || "UP".equals(trend) || "PU".equals(trend) || "PP".equals(trend)) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = 1;
							} else {
								posTrend = -1;
							}
						} else if ("DD".equals(trend) || "DP".equals(trend) || "PD".equals(trend)) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = -1;
							} else {
								posTrend = 1;
							}
						} else if ("UD".equals(trend)) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = 1;
							} else {
								posTrend = -1;
							}
						} else {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = -1;
							} else {
								posTrend = 1;
							}
						}
						int currentPos = infoPos + posTrend;
						if (currentPos >= 0 && currentPos < infos.size()) {
							YzSortInfo swInfo = infos.get(currentPos);
							sx = (SX) swInfo.getYzData();
						}
					}
				}
			}
		}

		if (sx != null) {
			SX bmnSX = DateUtil.getSxByYear(jy.getDate());
			List<Integer> nums = getSxNums(bmnSX, sx);
			String[] compareNums = jy.getSxNums() != null ? jy.getSxNums().split(",\\s*") : null;
			if (compareNums != null && compareNums.length > 0) {
				List<String> compareNumList = Arrays.asList(compareNums);
				for (Integer num : nums) {
					if (compareNumList.contains(num.toString())) {
						int currentPos = infoPos + posTrend * (-1);
						if (currentPos >= 0 && currentPos < infos.size()) {
							YzSortInfo swInfo = infos.get(currentPos);
							sx = (SX) swInfo.getYzData();
							nums = getSxNums(bmnSX, sx);
						}
						break;
					}
				}
			}
			String numsStr = Joiner.on(",").join(nums);
			jy.setSxzfNums(numsStr);
		}

	}

	protected <T extends Avg> void setFxSwDataForEFGH(DsyJY jy, String column, Class<T> clazz, BaseYzRepository<T> repository, BaseYzDao<T> dao,
			String[] fds, List<Integer>[] nums) throws Exception {
		T current = repository.findByYearAndPhase(jy.getYear(), jy.getPhase());
		List<YzSortInfo> infos = new ArrayList<YzSortInfo>();
		int infoPos = 0;
		int posTrend = 1;
		int pos = 0;
		for (String fd : fds) {
			Method gm = ReflectionUtils.findMethod(clazz, "get" + fd);
			YzSortInfo info = new YzSortInfo();
			info.setYzData(pos);
			info.setYz((Integer) gm.invoke(current));
			infos.add(info);
			if (info.getYz() != null && info.getYz() == current.getLastYz()) {
				infoPos = pos;
			}
			pos++;
		}
		Collections.sort(infos, new YzSortComparator());
		Integer thePos = null;
		if (current.getLastYz() != null) {
			YzSortInfo d1 = infos.get(infos.size() - 1);
			YzSortInfo s1 = infos.get(0);
			if (s1.getYz() != null) {
				if (s1.getYz().equals(current.getLastYz())) {
					if (current.getTotalAvg() != null) {
						if (current.getTotal() != null) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								thePos = (Integer) d1.getYzData();
							} else {
								YzSortInfo d2 = infos.get(infos.size() - 2);
								thePos = (Integer) d2.getYzData();
							}
						} else {
							thePos = (Integer) d1.getYzData();
						}
					} else {
						YzSortInfo d2 = infos.get(infos.size() - 2);
						thePos = (Integer) d2.getYzData();
					}
				}
			}

			if (thePos == null) {
				if (d1.getYz() != null) {
					if (d1.getYz().equals(current.getLastYz())) {
						if (current.getTotalAvg() != null) {
							if (current.getTotal() != null) {
								if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
									thePos = (Integer) s1.getYzData();
								} else {
									YzSortInfo s2 = infos.get(1);
									thePos = (Integer) s2.getYzData();
								}
							} else {
								thePos = (Integer) d1.getYzData();
							}
						} else {
							YzSortInfo s2 = infos.get(1);
							thePos = (Integer) s2.getYzData();
						}
					}
				}
			}
		}

		if (current.getTotalAvg() != null) {
			if (current.getTotal() != null) {
				if (thePos == null) {
					List<T> datas = dao.queryUpTo(jy.getYear(), jy.getPhase());
					String trend = "";
					Integer lastYz = null;
					for (T fdYz : datas) {
						Integer yz = fdYz.getLastYz();
						if (lastYz != null) {
							if (yz != null) {
								if (yz.compareTo(lastYz) > 0) {
									trend += "U";
								} else if (yz.compareTo(lastYz) == 0) {
									trend += "P";
								} else {
									trend += "D";
								}
							} else {
								break;
							}
						}
						lastYz = yz;
					}
					if (trend.length() == 2) {
						if ("UU".equals(trend) || "UP".equals(trend) || "PU".equals(trend) || "PP".equals(trend)) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = 1;
							} else {
								posTrend = -1;
							}
						} else if ("DD".equals(trend) || "DP".equals(trend) || "PD".equals(trend)) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = -1;
							} else {
								posTrend = 1;
							}
						} else if ("UD".equals(trend)) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = 1;
							} else {
								posTrend = -1;
							}
						} else {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = -1;
							} else {
								posTrend = 1;
							}
						}
						int currentPos = infoPos + posTrend;
						if (currentPos >= 0 && currentPos < infos.size()) {
							YzSortInfo swInfo = infos.get(currentPos);
							thePos = (Integer) swInfo.getYzData();
						}
					}
				}
			}
		}

		if (thePos != null) {
			List<Integer> theNums = nums[thePos];
			Method gm = ReflectionUtils.findMethod(jy.getClass(), "get" + column + "Nums");
			String tempNums = (String) gm.invoke(jy);
			String[] compareNums = tempNums != null ? tempNums.split(",\\s*") : null;
			if (compareNums != null && compareNums.length > 0) {
				List<String> compareNumList = Arrays.asList(compareNums);
				for (Integer num : theNums) {
					if (compareNumList.contains(num.toString())) {
						int currentPos = infoPos + posTrend * (-1);
						if (currentPos >= 0 && currentPos < infos.size()) {
							YzSortInfo swInfo = infos.get(currentPos);
							thePos = (Integer) swInfo.getYzData();
							theNums = nums[thePos];
						}
						break;
					}
				}
			}
			String numsStr = Joiner.on(",").join(theNums);
			Method sm = ReflectionUtils.findMethod(jy.getClass(), "set" + column + "zfNums", String.class);
			sm.invoke(jy, numsStr);
		}

	}

	protected void setFdDataForEFGH(DsyJY jy) throws Exception {
		TmYz tmData = repositories.tmyzRepository.findByYearAndPhase(jy.getYear(), jy.getPhase());
		List<TmYzInfo> currentTmInfos = getTMFDList(tmData, false);
		List<List<Integer>> numsArr = new ArrayList<List<Integer>>();
		int length = 12;
		int range = 4;
		for (int i = 1; i <= length; i++) {
			List<Integer> subNums = new ArrayList<Integer>();
			int num = 0;
			int pos = 1;
			for (TmYzInfo info : currentTmInfos) {
				if (pos % range == 0) {
					num = pos / range;
				} else {
					num = pos / range + 1;
				}
				if (num > length) {
					num = length;
				}
				if (num == i) {
					subNums.add(info.getNum());
				}
				pos++;
			}
			numsArr.add(subNums);
		}
		List<Integer>[] nums = numsArr.toArray(new List[] {});

		Tm12FdYz current = repositories.tm12fdyzRepository.findByYearAndPhase(jy.getYear(), jy.getPhase());
		List<YzSortInfo> infos = new ArrayList<YzSortInfo>();
		int infoPos = 0;
		int posTrend = 1;
		int pos = 0;
		for (String fd : TwelveNums.FDS) {
			Method gm = ReflectionUtils.findMethod(Tm12FdYz.class, "get" + fd);
			YzSortInfo info = new YzSortInfo();
			info.setYzData(pos);
			info.setYz((Integer) gm.invoke(current));
			infos.add(info);
			if (info.getYz() != null && info.getYz() == current.getLastYz()) {
				infoPos = pos;
			}
			pos++;
		}
		Collections.sort(infos, new YzSortComparator());
		Integer thePos = null;
		if (current.getLastYz() != null) {
			YzSortInfo d1 = infos.get(infos.size() - 1);
			YzSortInfo s1 = infos.get(0);
			if (s1.getYz() != null) {
				if (s1.getYz().equals(current.getLastYz())) {
					if (current.getTotalAvg() != null) {
						if (current.getTotal() != null) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								thePos = (Integer) d1.getYzData();
							} else {
								YzSortInfo d2 = infos.get(infos.size() - 2);
								thePos = (Integer) d2.getYzData();
							}
						} else {
							thePos = (Integer) d1.getYzData();
						}
					} else {
						YzSortInfo d2 = infos.get(infos.size() - 2);
						thePos = (Integer) d2.getYzData();
					}
				}
			}

			if (thePos == null) {
				if (d1.getYz() != null) {
					if (d1.getYz().equals(current.getLastYz())) {
						if (current.getTotalAvg() != null) {
							if (current.getTotal() != null) {
								if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
									thePos = (Integer) s1.getYzData();
								} else {
									YzSortInfo s2 = infos.get(1);
									thePos = (Integer) s2.getYzData();
								}
							} else {
								thePos = (Integer) d1.getYzData();
							}
						} else {
							YzSortInfo s2 = infos.get(1);
							thePos = (Integer) s2.getYzData();
						}
					}
				}
			}
		}

		if (current.getTotalAvg() != null) {
			if (current.getTotal() != null) {
				if (thePos == null) {
					List<Tm12FdYz> datas = repositories.tm12fdYzDao.queryUpTo(jy.getYear(), jy.getPhase());
					String trend = "";
					Integer lastYz = null;
					for (Tm12FdYz fdYz : datas) {
						Integer yz = fdYz.getLastYz();
						if (lastYz != null) {
							if (yz != null) {
								if (yz.compareTo(lastYz) > 0) {
									trend += "U";
								} else if (yz.compareTo(lastYz) == 0) {
									trend += "P";
								} else {
									trend += "D";
								}
							} else {
								break;
							}
						}
						lastYz = yz;
					}
					if (trend.length() == 2) {
						if ("UU".equals(trend) || "UP".equals(trend) || "PU".equals(trend) || "PP".equals(trend)) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = 1;
							} else {
								posTrend = -1;
							}
						} else if ("DD".equals(trend) || "DP".equals(trend) || "PD".equals(trend)) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = -1;
							} else {
								posTrend = 1;
							}
						} else if ("UD".equals(trend)) {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = 1;
							} else {
								posTrend = -1;
							}
						} else {
							if (current.getTotalAvg().compareTo(new BigDecimal(current.getTotal())) > 0) {
								posTrend = -1;
							} else {
								posTrend = 1;
							}
						}
						int currentPos = infoPos + posTrend;
						if (currentPos >= 0 && currentPos < infos.size()) {
							YzSortInfo swInfo = infos.get(currentPos);
							thePos = (Integer) swInfo.getYzData();
						}
					}
				}
			}
		}

		if (thePos != null) {
			List<Integer> theNums = nums[thePos];
			Method gm = ReflectionUtils.findMethod(jy.getClass(), "getFdNums");
			String tempNums = (String) gm.invoke(jy);
			String[] compareNums = tempNums != null ? tempNums.split(",\\s*") : null;
			if (compareNums != null && compareNums.length > 0) {
				List<String> compareNumList = Arrays.asList(compareNums);
				for (Integer num : theNums) {
					if (compareNumList.contains(num.toString())) {
						int currentPos = infoPos + posTrend * (-1);
						if (currentPos >= 0 && currentPos < infos.size()) {
							YzSortInfo swInfo = infos.get(currentPos);
							thePos = (Integer) swInfo.getYzData();
							theNums = nums[thePos];
						}
						break;
					}
				}
			}
			String numsStr = Joiner.on(",").join(theNums);
			Method sm = ReflectionUtils.findMethod(jy.getClass(), "setFdzfNums", String.class);
			sm.invoke(jy, numsStr);
		}
	}

	protected void setFxSwDataForABCD(DsyJY jy, FxSw data, String column, boolean max) throws Exception {
		Method gmForRedCountsForFx = ReflectionUtils.findMethod(FxSw.class, "getRedCountsFor" + column);
		Method gmForYzForFx = ReflectionUtils.findMethod(FxSw.class, "get" + column);
		Method gmForNumsForFx = ReflectionUtils.findMethod(FxSw.class, "get" + column + "Nums");

		Method gmForRedCountsForJY = ReflectionUtils.findMethod(DsyJY.class, "getRedCountsFor" + column);
		Method gmForYzForJY = ReflectionUtils.findMethod(DsyJY.class, "get" + column);
		Method smForRedCountsForJY = ReflectionUtils.findMethod(DsyJY.class, "setRedCountsFor" + column, Integer.class);
		Method smForYzForJY = ReflectionUtils.findMethod(DsyJY.class, "set" + column, Integer.class);
		Method smForNumsForJY = ReflectionUtils.findMethod(DsyJY.class, "set" + column + "Nums", String.class);

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

	protected void compareDsyDataForABCD(DsyJY jy, String column, boolean max) throws Exception {
		Method smForRedCountsForJY = ReflectionUtils.findMethod(DsxJY.class, "setRedCountsFor" + column, Integer.class);
		Method smForYzForJY = ReflectionUtils.findMethod(DsxJY.class, "set" + column, Integer.class);
		Method smForNumsForJY = ReflectionUtils.findMethod(DsxJY.class, "set" + column + "Nums", String.class);

		Method gmForYzRedCounts = ReflectionUtils.findMethod(FxSw.class, "getRedCountsFor" + column);
		Method gmForZfRedCounts = ReflectionUtils.findMethod(FxSw.class, "getRedCountsFor" + column + "zf");
		Method gmForYz = ReflectionUtils.findMethod(FxSw.class, "get" + column);
		Method gmForZfYz = ReflectionUtils.findMethod(FxSw.class, "get" + column + "zf");
		Method gmForYzNums = ReflectionUtils.findMethod(FxSw.class, "get" + column + "Nums");
		Method gmForZfNums = ReflectionUtils.findMethod(FxSw.class, "get" + column + "zfNums");

		Integer redCounts = null;
		Integer yz = null;
		Integer yzRedCounts = (Integer) gmForYzRedCounts.invoke(jy);
		Integer zfRedCounts = (Integer) gmForZfRedCounts.invoke(jy);
		String nums = null;
		if (yzRedCounts.compareTo(zfRedCounts) == 0) {
			Method gmForYzSw = ReflectionUtils.findMethod(FxSw.class, "get" + column);
			Method gmForZfSw = ReflectionUtils.findMethod(FxSw.class, "get" + column + "zf");
			Integer yzForSw = (Integer) gmForYzSw.invoke(jy);
			Integer zfyzForSw = (Integer) gmForZfSw.invoke(jy);
			if (yzForSw != null && zfyzForSw != null) {
				if (yzForSw.equals(zfyzForSw)) {
					redCounts = yzRedCounts;
					yz = (Integer) gmForYz.invoke(jy);
					nums = (String) gmForYzNums.invoke(jy);
				} else if (yzForSw.compareTo(zfRedCounts) > 0) {
					if (!max) {
						redCounts = zfRedCounts;
						yz = (Integer) gmForZfYz.invoke(jy);
						nums = (String) gmForZfNums.invoke(jy);
					} else {
						redCounts = yzRedCounts;
						yz = (Integer) gmForYz.invoke(jy);
						nums = (String) gmForYzNums.invoke(jy);
					}
				} else {
					if (!max) {
						redCounts = yzRedCounts;
						yz = (Integer) gmForYz.invoke(jy);
						nums = (String) gmForYzNums.invoke(jy);
					} else {
						redCounts = zfRedCounts;
						yz = (Integer) gmForZfYz.invoke(jy);
						nums = (String) gmForZfNums.invoke(jy);
					}
				}
			} else if (yzForSw != null && zfyzForSw == null) {
				redCounts = yzRedCounts;
				yz = (Integer) gmForYz.invoke(jy);
				nums = (String) gmForYzNums.invoke(jy);
			} else if (zfyzForSw != null && yzForSw == null) {
				redCounts = zfRedCounts;
				yz = (Integer) gmForZfYz.invoke(jy);
				nums = (String) gmForZfNums.invoke(jy);
			}
		} else if (yzRedCounts.compareTo(zfRedCounts) > 0) {
			if (!max) {
				redCounts = zfRedCounts;
				yz = (Integer) gmForZfYz.invoke(jy);
				nums = (String) gmForZfNums.invoke(jy);
			} else {
				redCounts = yzRedCounts;
				yz = (Integer) gmForYz.invoke(jy);
				nums = (String) gmForYzNums.invoke(jy);
			}
		} else if (yzRedCounts.compareTo(zfRedCounts) < 0) {
			if (!max) {
				redCounts = yzRedCounts;
				yz = (Integer) gmForYz.invoke(jy);
				nums = (String) gmForYzNums.invoke(jy);
			} else {
				redCounts = zfRedCounts;
				yz = (Integer) gmForZfYz.invoke(jy);
				nums = (String) gmForZfNums.invoke(jy);
			}
		}

		smForRedCountsForJY.invoke(jy, redCounts);
		smForYzForJY.invoke(jy, yz);
		smForNumsForJY.invoke(jy, nums);
	}

	protected void calDsyJYForJQB(DsyJyYz data, DsyJY lastJY, boolean reverse) throws Exception {
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
		assembleDsyJY(data, nums, reverse);
	}

	protected void calDsyJYForJDB(DsyJyYz data, DsyJY lastJY, boolean reverse) throws Exception {
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
		assembleDsyJY(data, nums, reverse);
	}

	protected void calDsyJYForJHB(DsyJyYz data, DsyJY lastJY, boolean reverse) throws Exception {
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
		assembleDsyJY(data, nums, reverse);
	}

	protected void calDsyJYForSJB(DsyJyYz data, DsyJY lastJY, boolean reverse) throws Exception {
		Set<Integer> A = new HashSet<Integer>();
		addNumsToConditionList(A, lastJY.getSxNums());
		addNumsToConditionList(A, lastJY.getTwelveNums());

		Set<Integer> B = new HashSet<Integer>();
		addNumsToConditionList(B, lastJY.getZx3Nums());
		addNumsToConditionList(B, lastJY.getPdNums());

		Set<Integer> C = new HashSet<Integer>();
		addNumsToConditionList(C, lastJY.getZx2Nums());
		addNumsToConditionList(C, lastJY.getZx11Nums());

		Set<Integer> D = new HashSet<Integer>();
		addNumsToConditionList(D, lastJY.getZx16Nums());
		addNumsToConditionList(D, lastJY.getZx12Nums());

		Set<Integer> E = new HashSet<Integer>();
		addNumsToConditionList(E, lastJY.getSxzfNums());
		addNumsToConditionList(E, lastJY.getTwelvezfNums());

		Set<Integer> F = new HashSet<Integer>();
		addNumsToConditionList(F, lastJY.getPdzfNums());

		Set<Integer> G = new HashSet<Integer>();
		addNumsToConditionList(G, lastJY.getZx11zfNums());

		Set<Integer> H = new HashSet<Integer>();
		addNumsToConditionList(H, lastJY.getZx16zfNums());

		data.clearAll();
		Set<Integer>[] nums = collectConditionList(A, B, C, D, E, F, G, H, reverse);
		assembleDsyJY(data, nums, reverse);
	}

	protected void calDsyJYForDB(DsyJyYz data, DsyJY lastJY, boolean reverse) throws Exception {
		Set<Integer> A = new HashSet<Integer>();
		addNumsToConditionList(A, lastJY.getTwelveNums());

		Set<Integer> B = new HashSet<Integer>();
		addNumsToConditionList(B, lastJY.getPdNums());

		Set<Integer> C = new HashSet<Integer>();
		addNumsToConditionList(C, lastJY.getZx11Nums());

		Set<Integer> D = new HashSet<Integer>();
		addNumsToConditionList(D, lastJY.getZx16Nums());

		Set<Integer> E = new HashSet<Integer>();
		addNumsToConditionList(E, lastJY.getTwelvezfNums());

		Set<Integer> F = new HashSet<Integer>();
		addNumsToConditionList(F, lastJY.getPdzfNums());

		Set<Integer> G = new HashSet<Integer>();
		addNumsToConditionList(G, lastJY.getZx11zfNums());

		Set<Integer> H = new HashSet<Integer>();
		addNumsToConditionList(H, lastJY.getZx16zfNums());

		data.clearAll();
		Set<Integer>[] nums = collectConditionList(A, B, C, D, E, F, G, H, reverse);
		assembleDsyJY(data, nums, reverse);
	}

	protected void assembleDsyJY(DsyJyYz data, Collection<Integer>[] arr, boolean reverse) throws Exception {
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
		Set<Integer> total = new HashSet<Integer>();
		for (XbwJYSub sub : all) {
			Method m = null;
			if (sub.getCount() < 11) {
				m = ReflectionUtils.findMethod(data.getClass(), "getC" + sub.getCount());
				List<Integer> values = (List<Integer>) m.invoke(data);
				values.add(sub.getNum());
				total.add(sub.getNum());
			}
		}
		List<Integer> c11 = data.getC11();
		for (int i = 1; i < 50; i++) {
			if (!total.contains(i)) {
				c11.add(i);
			}
		}
		Collections.sort(data.getC1());
		Collections.sort(data.getC2());
		Collections.sort(data.getC3());
		Collections.sort(data.getC4());
		Collections.sort(data.getC5());
		Collections.sort(data.getC6());
		Collections.sort(data.getC7());
		Collections.sort(data.getC8());
		Collections.sort(data.getC9());
		Collections.sort(data.getC10());
		Collections.sort(data.getC11());
		data.assemble(reverse);
	}

}

class YzSortInfo {
	private Object yzData;
	private Integer yz;

	public Object getYzData() {
		return yzData;
	}

	public void setYzData(Object yzData) {
		this.yzData = yzData;
	}

	public Integer getYz() {
		return yz;
	}

	public void setYz(Integer yz) {
		this.yz = yz;
	}
}

class YzSortComparator implements Comparator<YzSortInfo> {

	@Override
	public int compare(YzSortInfo o1, YzSortInfo o2) {
		if (o1.getYz() != null && o2.getYz() != null) {
			return o1.getYz().compareTo(o2.getYz());
		} else if (o1.getYz() == null) {
			return -1;
		} else if (o2.getYz() == null) {
			return 1;
		} else {
			return 0;
		}
	}

}
