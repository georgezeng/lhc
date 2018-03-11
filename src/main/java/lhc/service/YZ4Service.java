package lhc.service;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.transaction.annotation.Transactional;

import lhc.domain.DmgJyYz;
import lhc.domain.DmgMaxJyJDB;
import lhc.domain.DmgMaxJyJHB;
import lhc.domain.DmgMaxJyJQB;
import lhc.domain.DmgMinJyJDB;
import lhc.domain.DmgMinJyJHB;
import lhc.domain.DmgMinJyJQB;
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
import lhc.repository.jpa.BaseYzRepository;
import lhc.util.CommonUtil;

@Transactional
@SuppressWarnings("unchecked")
public class YZ4Service extends YZ3Service {
	@Async
	public Future<Exception> calDmgMinJY() {
		return calDmgJY(DmgMinJyJQB.class, DmgMinJyJDB.class, DmgMinJyJHB.class, repositories.dmgMinJyJQBRepository,
				repositories.dmgMinJyJDBRepository, repositories.dmgMinJyJHBRepository, false);
	}

	@Async
	public Future<Exception> calDmgMaxJY() {
		return calDmgJY(DmgMaxJyJQB.class, DmgMaxJyJDB.class, DmgMaxJyJHB.class, repositories.dmgMaxJyJQBRepository,
				repositories.dmgMaxJyJDBRepository, repositories.dmgMaxJyJHBRepository, true);
	}

	public <Q extends DmgJyYz, D extends DmgJyYz, H extends DmgJyYz> Future<Exception> calDmgJY(Class<Q> jqbClass, Class<D> jdbClass,
			Class<H> jhbClass, BaseYzRepository<Q> jqbRepository, BaseYzRepository<D> jdbRepository, BaseYzRepository<H> jhbRepository, boolean max) {
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
			List<Future<DsxJY>> jys = new ArrayList<Future<DsxJY>>();
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
						jys.add(repositories.yzService.doCalDmgJY(result1, result2, result3, result4, result5, result6, result7, result8, result9,
								result10, result11, result12, i, max));
					}
				}
				request = request.next();
			} while (result1 != null && result1.hasNext());
			List<DsxJY> list = CommonUtil.getAsyncResults(jys, 100);
			List<Future<Exception>> futures = new ArrayList<Future<Exception>>();
			DsxJY lastJY = null;
			for (DsxJY jy : list) {
				if (lastJY != null) {
					futures.add(repositories.yzService.doCalDmgJY(jqbClass, jdbClass, jhbClass, jqbRepository, jdbRepository, jhbRepository, jy, lastJY));
				}
				lastJY = jy;
			}
			CommonUtil.sleep(futures, 100);
			if (lastJY != null) {
				calDmgJYForJQB(unknownDataForJQB, lastJY, true);
				calDmgJYForJQB(unknownDataForJQB, lastJY, false);
				jqbRepository.save(unknownDataForJQB);

				calDmgJYForJDB(unknownDataForJDB, lastJY, true);
				calDmgJYForJDB(unknownDataForJDB, lastJY, false);
				jdbRepository.save(unknownDataForJDB);

				calDmgJYForJHB(unknownDataForJHB, lastJY, true);
				calDmgJYForJHB(unknownDataForJHB, lastJY, false);
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
	public <JQ extends DmgJyYz, JD extends DmgJyYz, JH extends DmgJyYz> Future<Exception> doCalDmgJY(Class<JQ> jqbClass, Class<JD> jdbClass,
			Class<JH> jhbClass, BaseYzRepository<JQ> jqbRepository, BaseYzRepository<JD> jdbRepository, BaseYzRepository<JH> jhbRepository, DsxJY jy,
			DsxJY lastJY) {
		Exception t = null;
		try {
			KaiJiang kj = repositories.kaiJiangRepository.findByYearAndPhase(jy.getYear(), jy.getPhase());
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
				calDmgJYForJQB(jqb, lastJY, true);
				calDmgJYForJQB(jqb, lastJY, false);
				calDmgJYForJDB(jdb, lastJY, true);
				calDmgJYForJDB(jdb, lastJY, false);
				calDmgJYForJHB(jhb, lastJY, true);
				calDmgJYForJHB(jhb, lastJY, false);
			}
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
	public <Q extends DmgJyYz, D extends DmgJyYz, H extends DmgJyYz> Future<DsxJY> doCalDmgJY(Page<FxSw1> result1, Page<FxSw2> result2,
			Page<FxSw3> result3, Page<FxSw4> result4, Page<FxSw5> result5, Page<FxSw6> result6, Page<FxSw7> result7, Page<FxSw8> result8,
			Page<FxSw9> result9, Page<FxSw10> result10, Page<FxSw11> result11, Page<FxSw12> result12, int i, boolean max) throws Exception {
		FxSw temp = result1.getContent().get(i);
		DsxJY jy = new DsxJY();
		jy.setDate(temp.getDate());
		jy.setYear(temp.getYear());
		jy.setPhase(temp.getPhase());
		setFxSwDataForDsx(result1, result2, result3, result4, result5, result6, result7, result8, result9, result10, result11, result12, jy, i, max);
		return new AsyncResult<DsxJY>(jy);
	}

	protected void calDmgJYForJQB(DmgJyYz data, DsxJY lastJY, boolean reverse) throws Exception {
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
		Set<Integer>[] nums = collectConditionListForDmg(A, B, C, D, E, F, G, H, reverse);
		assembleDmgJY(data, nums, reverse);
	}

	protected void calDmgJYForJDB(DmgJyYz data, DsxJY lastJY, boolean reverse) throws Exception {
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
		Set<Integer>[] nums = collectConditionListForDmg(A, B, C, D, E, F, G, H, reverse);
		assembleDmgJY(data, nums, reverse);
	}

	protected void calDmgJYForJHB(DmgJyYz data, DsxJY lastJY, boolean reverse) throws Exception {
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
		Set<Integer>[] nums = collectConditionListForDmg(A, B, C, D, E, F, G, H, reverse);
		assembleDmgJY(data, nums, reverse);
	}

	protected Set<Integer>[] collectConditionListForDmg(Set<Integer> A, Set<Integer> B, Set<Integer> C, Set<Integer> D, Set<Integer> E,
			Set<Integer> F, Set<Integer> G, Set<Integer> H, boolean reverse) throws Exception {
		Set<Integer> AE = combineConditionList(A, E, null, null, reverse);
		Set<Integer> BF = combineConditionList(B, F, null, null, reverse);
		Set<Integer> CG = combineConditionList(C, G, null, null, reverse);
		Set<Integer> DH = combineConditionList(D, H, null, null, reverse);
		return new Set[] { AE, BF, CG, DH };
	}

	protected void assembleDmgJY(DmgJyYz data, Collection<Integer>[] arr, boolean reverse) throws Exception {
		data.setAe(new ArrayList<Integer>(arr[0]));
		data.setBf(new ArrayList<Integer>(arr[1]));
		data.setCg(new ArrayList<Integer>(arr[2]));
		data.setDh(new ArrayList<Integer>(arr[3]));
		data.assemble(reverse);
	}

}
