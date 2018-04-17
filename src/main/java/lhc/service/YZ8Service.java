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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import lhc.constants.Bs9qNums;
import lhc.constants.LhNums;
import lhc.constants.MwNums;
import lhc.constants.PdNums;
import lhc.constants.QiwNums;
import lhc.constants.SlqNums;
import lhc.constants.SwNums;
import lhc.constants.TwelveNums;
import lhc.constants.WxDsNums;
import lhc.constants.ZsNums;
import lhc.constants.Zx12Nums;
import lhc.domain.FxSw;
import lhc.domain.KaiJiang;
import lhc.domain.MyYz;
import lhc.domain.TmYz;
import lhc.domain.ZmkmYz;
import lhc.dto.TmYzInfo;
import lhc.dto.XbwJYSub;
import lhc.enums.SX;
import lhc.repository.jpa.BaseYzRepository;
import lhc.util.DateUtil;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class YZ8Service extends YZ7Service {
	@Async
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Future<Exception> calZmkm() {
		Exception t = null;
		try {
			calZmkmNums(repositories.myD1Repository, repositories.fxsw2Repository, repositories.fxsw3Repository);
			calZmkmYz();
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	private void calZmkmYz() throws Exception {
		Pageable request = new PageRequest(0, 200, new Sort(Direction.DESC, "date"));
		Page<ZmkmYz> result = null;
		ZmkmYz lastData = null;
		do {
			result = repositories.zmkmRepository.findAll(request);
			if (result != null && result.hasContent()) {
				for (ZmkmYz data : result.getContent()) {
					if (lastData != null) {
						for (int i = 1; i < 16; i++) {
							String numsStr = (String) ReflectionUtils.findMethod(data.getClass(), "getC" + i + "Nums").invoke(data);
							if (numsStr != null && !numsStr.isEmpty()) {
								String[] nums = numsStr.split(",\\s*");
								for (String num : nums) {
									if (num.equals(lastData.getSpecialNum() + "")) {
										ReflectionUtils.findMethod(data.getClass(), "setC" + i + "yz", Integer.class).invoke(data, 0);
										break;
									}
								}
							}
						}
						repositories.zmkmRepository.save(data);
					}
					lastData = data;
				}
			}
			request = request.next();
		} while (result != null && result.hasNext());

		request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
		result = null;
		lastData = null;
		do {
			result = repositories.zmkmRepository.findAll(request);
			if (result != null && result.hasContent()) {
				for (ZmkmYz data : result.getContent()) {
					if (lastData != null) {
						for (int i = 1; i < 16; i++) {
							Integer lastValue = (Integer) ReflectionUtils.findMethod(data.getClass(), "getC" + i + "yz").invoke(lastData);
							Integer value = (Integer) ReflectionUtils.findMethod(data.getClass(), "getC" + i + "yz").invoke(data);
							if (lastValue != null) {
								if (value == null || value > 0) {
									ReflectionUtils.findMethod(data.getClass(), "setC" + i + "yz", Integer.class).invoke(data, lastValue + 1);
								}
							}
						}
						repositories.zmkmRepository.save(data);
					}
					lastData = data;
				}
			}
			request = request.next();
		} while (result != null && result.hasNext());
	}

	private <A extends MyYz, E extends FxSw, I extends FxSw> void calZmkmNums(BaseYzRepository<A> abcdRepository, BaseYzRepository<E> efghRepository,
			BaseYzRepository<I> ijklRepository) throws Exception {
		Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
		Page<KaiJiang> result = null;
		do {
			result = repositories.kaiJiangRepository.findAll(request);
			if (result != null && result.hasContent()) {
				for (KaiJiang kj : result.getContent()) {
					MyYz abcdYz = abcdRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());

					Set<Integer> A = new HashSet<Integer>();
					SX sx = SX.textOf(abcdYz.getSxmy());
					SX bmnSX = DateUtil.getSxByYear(kj.getDate());
					if (sx != null) {
						List<Integer> sxNums = getSxNums(bmnSX, sx);
						addNumsToConditionList(A, sxNums);
					}
					if (abcdYz.getTwelvemy() != null) {
						addNumsToConditionList(A, TwelveNums.NUMS[Integer.valueOf(abcdYz.getTwelvemy().replace("位", "")) - 1]);
					}
					if (abcdYz.getSlqmy() != null) {
						addNumsToConditionList(A, SlqNums.NUMS[Integer.valueOf(abcdYz.getSlqmy().replace("位", "")) - 1]);
					}
					if (abcdYz.getZx12my() != null) {
						addNumsToConditionList(A, Zx12Nums.NUMS[Integer.valueOf(abcdYz.getZx12my().replace("位", "")) - 1]);
					}

					Set<Integer> B = new HashSet<Integer>();
					if (abcdYz.getLhmy() != null) {
						addNumsToConditionList(B, LhNums.NUMS[Integer.valueOf(abcdYz.getLhmy().replace("位", ""))]);
					}
					if (abcdYz.getMwmy() != null) {
						addNumsToConditionList(B, MwNums.NUMS[Integer.valueOf(abcdYz.getMwmy().replace("位", ""))]);
					}
					if (abcdYz.getFdmy() != null) {
						int pos = Integer.valueOf(abcdYz.getFdmy().replace("位", "").replace("W", "")) - 1;
						TmYz tmyz = repositories.tmyzRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						List<TmYzInfo> infos = getTMFDList(tmyz, true);
						int len = 4 * (pos + 1);
						if (pos == 11) {
							len = 49;
						}
						List<Integer> fdNums = new ArrayList<Integer>();
						for (int i = pos * 4; i < len; i++) {
							fdNums.add(infos.get(i).getNum());
						}
						addNumsToConditionList(B, fdNums);
					}
					if (abcdYz.getPdmy() != null) {
						addNumsToConditionList(B, PdNums.NUMS[Integer.valueOf(abcdYz.getPdmy().replace("位", "")) - 1]);
					}

					Set<Integer> C = new HashSet<Integer>();
					if (abcdYz.getZsmy() != null) {
						addNumsToConditionList(C, ZsNums.NUMS[Integer.valueOf(abcdYz.getZsmy().replace("位", "")) - 1]);
					}
					if (abcdYz.getWxdsmy() != null) {
						addNumsToConditionList(C, WxDsNums.NUMS_MAP.get(abcdYz.getWxdsmy()));
					}
					if (abcdYz.getBsmy() != null) {
						addNumsToConditionList(C, Bs9qNums.NUMS_MAP.get(abcdYz.getBsmy()));
					}

					Set<Integer> D = new HashSet<Integer>();
					if (abcdYz.getSwmy() != null) {
						addNumsToConditionList(D, SwNums.NUMS[Integer.valueOf(abcdYz.getSwmy().replace("位", ""))]);
					}
					if (abcdYz.getQiwmy() != null) {
						addNumsToConditionList(D, QiwNums.NUMS[Integer.valueOf(abcdYz.getQiwmy().replace("位", "")) - 1]);
					}

					FxSw efghYz = efghRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());

					Set<Integer> E = new HashSet<Integer>();
					sx = SX.textOf(efghYz.getSxDW());
					if (sx != null) {
						List<Integer> sxNums = getSxNums(bmnSX, sx);
						addNumsToConditionList(E, sxNums);
					}
					if (efghYz.getTwelveDW() != null) {
						addNumsToConditionList(E, TwelveNums.NUMS[Integer.valueOf(efghYz.getTwelveDW().replace("位", "")) - 1]);
					}
					if (efghYz.getSlqDW() != null) {
						addNumsToConditionList(E, SlqNums.NUMS[Integer.valueOf(efghYz.getSlqDW().replace("位", "")) - 1]);
					}
					if (efghYz.getZx12DW() != null) {
						addNumsToConditionList(E, Zx12Nums.NUMS[Integer.valueOf(efghYz.getZx12DW().replace("位", "")) - 1]);
					}

					Set<Integer> F = new HashSet<Integer>();
					if (efghYz.getLhDW() != null) {
						addNumsToConditionList(F, LhNums.NUMS[Integer.valueOf(efghYz.getLhDW().replace("位", ""))]);
					}
					if (efghYz.getMwDW() != null) {
						addNumsToConditionList(F, MwNums.NUMS[Integer.valueOf(efghYz.getMwDW().replace("位", ""))]);
					}
					if (efghYz.getFdDW() != null) {
						int pos = Integer.valueOf(efghYz.getFdDW().replace("位", "").replace("W", "")) - 1;
						TmYz tmyz = repositories.tmyzRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						List<TmYzInfo> infos = getTMFDList(tmyz, true);
						int len = 4 * (pos + 1);
						if (pos == 11) {
							len = 49;
						}
						List<Integer> fdNums = new ArrayList<Integer>();
						for (int i = pos * 4; i < len; i++) {
							fdNums.add(infos.get(i).getNum());
						}
						addNumsToConditionList(F, fdNums);
					}
					if (efghYz.getPdDW() != null) {
						addNumsToConditionList(F, PdNums.NUMS[Integer.valueOf(efghYz.getPdDW().replace("位", "")) - 1]);
					}

					Set<Integer> G = new HashSet<Integer>();
					if (efghYz.getZsDW() != null) {
						addNumsToConditionList(G, ZsNums.NUMS[Integer.valueOf(efghYz.getZsDW().replace("位", "")) - 1]);
					}
					if (efghYz.getWxdsDW() != null) {
						addNumsToConditionList(G, WxDsNums.NUMS_MAP.get(efghYz.getWxdsDW()));
					}
					if (efghYz.getBsDW() != null) {
						addNumsToConditionList(G, Bs9qNums.NUMS_MAP.get(efghYz.getBsDW()));
					}

					Set<Integer> H = new HashSet<Integer>();
					if (efghYz.getSwDW() != null) {
						addNumsToConditionList(H, SwNums.NUMS[Integer.valueOf(efghYz.getSwDW().replace("位", ""))]);
					}
					if (efghYz.getQiwDW() != null) {
						addNumsToConditionList(H, QiwNums.NUMS[Integer.valueOf(efghYz.getQiwDW().replace("位", "")) - 1]);
					}

					FxSw ijklYz = ijklRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());

					Set<Integer> I = new HashSet<Integer>();
					sx = SX.textOf(ijklYz.getSxDW());
					if (sx != null) {
						List<Integer> sxNums = getSxNums(bmnSX, sx);
						addNumsToConditionList(I, sxNums);
					}
					if (ijklYz.getTwelveDW() != null) {
						addNumsToConditionList(I, TwelveNums.NUMS[Integer.valueOf(ijklYz.getTwelveDW().replace("位", "")) - 1]);
					}
					if (ijklYz.getSlqDW() != null) {
						addNumsToConditionList(I, SlqNums.NUMS[Integer.valueOf(ijklYz.getSlqDW().replace("位", "")) - 1]);
					}
					if (ijklYz.getZx12DW() != null) {
						addNumsToConditionList(I, Zx12Nums.NUMS[Integer.valueOf(ijklYz.getZx12DW().replace("位", "")) - 1]);
					}

					Set<Integer> J = new HashSet<Integer>();
					if (ijklYz.getLhDW() != null) {
						addNumsToConditionList(J, LhNums.NUMS[Integer.valueOf(ijklYz.getLhDW().replace("位", ""))]);
					}
					if (ijklYz.getMwDW() != null) {
						addNumsToConditionList(J, MwNums.NUMS[Integer.valueOf(ijklYz.getMwDW().replace("位", ""))]);
					}
					if (ijklYz.getFdDW() != null) {
						int pos = Integer.valueOf(ijklYz.getFdDW().replace("位", "").replace("W", "")) - 1;
						TmYz tmyz = repositories.tmyzRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						List<TmYzInfo> infos = getTMFDList(tmyz, true);
						int len = 4 * (pos + 1);
						if (pos == 11) {
							len = 49;
						}
						List<Integer> fdNums = new ArrayList<Integer>();
						for (int i = pos * 4; i < len; i++) {
							fdNums.add(infos.get(i).getNum());
						}
						addNumsToConditionList(J, fdNums);
					}
					if (ijklYz.getPdDW() != null) {
						addNumsToConditionList(J, PdNums.NUMS[Integer.valueOf(ijklYz.getPdDW().replace("位", "")) - 1]);
					}

					Set<Integer> K = new HashSet<Integer>();
					if (ijklYz.getZsDW() != null) {
						addNumsToConditionList(K, ZsNums.NUMS[Integer.valueOf(ijklYz.getZsDW().replace("位", "")) - 1]);
					}
					if (ijklYz.getWxdsDW() != null) {
						addNumsToConditionList(K, WxDsNums.NUMS_MAP.get(ijklYz.getWxdsDW()));
					}
					if (ijklYz.getBsDW() != null) {
						addNumsToConditionList(K, Bs9qNums.NUMS_MAP.get(ijklYz.getBsDW()));
					}

					Set<Integer> L = new HashSet<Integer>();
					if (ijklYz.getSwDW() != null) {
						addNumsToConditionList(L, SwNums.NUMS[Integer.valueOf(ijklYz.getSwDW().replace("位", ""))]);
					}
					if (ijklYz.getQiwDW() != null) {
						addNumsToConditionList(L, QiwNums.NUMS[Integer.valueOf(ijklYz.getQiwDW().replace("位", "")) - 1]);
					}

					List<Integer> ABCDwithRP = new ArrayList<Integer>();
					ABCDwithRP.addAll(A);
					ABCDwithRP.addAll(B);
					ABCDwithRP.addAll(C);
					ABCDwithRP.addAll(D);
					List<Integer> ABCDwithoutRP = new ArrayList<Integer>();
					Set<Integer> Awithout = new HashSet<Integer>(A);
					ABCDwithoutRP.addAll(Awithout);
					Set<Integer> Bwithout = new HashSet<Integer>();
					for (Integer num : B) {
						if (!ABCDwithoutRP.contains(num)) {
							Bwithout.add(num);
							ABCDwithoutRP.add(num);
						}
					}
					Set<Integer> Cwithout = new HashSet<Integer>();
					for (Integer num : C) {
						if (!ABCDwithoutRP.contains(num)) {
							Cwithout.add(num);
							ABCDwithoutRP.add(num);
						}
					}
					Set<Integer> Dwithout = new HashSet<Integer>();
					for (Integer num : D) {
						if (!ABCDwithoutRP.contains(num)) {
							Dwithout.add(num);
							ABCDwithoutRP.add(num);
						}
					}
					List<Integer> all = new ArrayList<Integer>();
					Set<Integer> ABCD_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!ABCDwithoutRP.contains(i)) {
							ABCD_FZ.add(i);
						}
					}
					all.addAll(ABCD_FZ);

					List<Integer> EFGHwithRP = new ArrayList<Integer>();
					EFGHwithRP.addAll(E);
					EFGHwithRP.addAll(F);
					EFGHwithRP.addAll(G);
					EFGHwithRP.addAll(H);
					List<Integer> EFGHwithoutRP = new ArrayList<Integer>();
					Set<Integer> Ewithout = new HashSet<Integer>(A);
					EFGHwithoutRP.addAll(Ewithout);
					Set<Integer> Fwithout = new HashSet<Integer>();
					for (Integer num : F) {
						if (!EFGHwithoutRP.contains(num)) {
							Fwithout.add(num);
							EFGHwithoutRP.add(num);
						}
					}
					Set<Integer> Gwithout = new HashSet<Integer>();
					for (Integer num : G) {
						if (!EFGHwithoutRP.contains(num)) {
							Gwithout.add(num);
							EFGHwithoutRP.add(num);
						}
					}
					Set<Integer> Hwithout = new HashSet<Integer>();
					for (Integer num : H) {
						if (!EFGHwithoutRP.contains(num)) {
							Hwithout.add(num);
							EFGHwithoutRP.add(num);
						}
					}
					Set<Integer> EFGH_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!EFGHwithoutRP.contains(i)) {
							EFGH_FZ.add(i);
						}
					}
					all.addAll(EFGH_FZ);

					List<Integer> IJKLwithRP = new ArrayList<Integer>();
					IJKLwithRP.addAll(E);
					IJKLwithRP.addAll(F);
					IJKLwithRP.addAll(G);
					IJKLwithRP.addAll(H);
					List<Integer> IJKLwithoutRP = new ArrayList<Integer>();
					Set<Integer> Iwithout = new HashSet<Integer>(A);
					IJKLwithoutRP.addAll(Iwithout);
					Set<Integer> Jwithout = new HashSet<Integer>();
					for (Integer num : J) {
						if (!IJKLwithoutRP.contains(num)) {
							Jwithout.add(num);
							IJKLwithoutRP.add(num);
						}
					}
					Set<Integer> Kwithout = new HashSet<Integer>();
					for (Integer num : K) {
						if (!IJKLwithoutRP.contains(num)) {
							Kwithout.add(num);
							IJKLwithoutRP.add(num);
						}
					}
					Set<Integer> Lwithout = new HashSet<Integer>();
					for (Integer num : L) {
						if (!IJKLwithoutRP.contains(num)) {
							Lwithout.add(num);
							IJKLwithoutRP.add(num);
						}
					}
					Set<Integer> IJKL_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!IJKLwithoutRP.contains(i)) {
							IJKL_FZ.add(i);
						}
					}
					all.addAll(IJKL_FZ);

					Set<Integer> EIBCD = new HashSet<Integer>(Ewithout);
					EIBCD.addAll(Iwithout);
					EIBCD.addAll(B);
					EIBCD.addAll(C);
					EIBCD.addAll(D);
					Set<Integer> EIBCD_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!EIBCD.contains(i)) {
							EIBCD_FZ.add(i);
						}
					}
					all.addAll(EIBCD_FZ);

					Set<Integer> AFJCD = new HashSet<Integer>(A);
					AFJCD.addAll(Fwithout);
					AFJCD.addAll(Jwithout);
					AFJCD.addAll(C);
					AFJCD.addAll(D);
					Set<Integer> AFJCD_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!AFJCD.contains(i)) {
							AFJCD_FZ.add(i);
						}
					}
					all.addAll(AFJCD_FZ);

					Set<Integer> ABGKD = new HashSet<Integer>(A);
					ABGKD.addAll(B);
					ABGKD.addAll(Gwithout);
					ABGKD.addAll(Kwithout);
					ABGKD.addAll(D);
					Set<Integer> ABGKD_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!ABGKD.contains(i)) {
							ABGKD_FZ.add(i);
						}
					}
					all.addAll(ABGKD_FZ);

					Set<Integer> ABCHL = new HashSet<Integer>(A);
					ABCHL.addAll(B);
					ABCHL.addAll(C);
					ABCHL.addAll(Hwithout);
					ABCHL.addAll(Lwithout);
					Set<Integer> ABCHL_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!ABCHL.contains(i)) {
							ABCHL_FZ.add(i);
						}
					}
					all.addAll(ABCHL_FZ);

					Set<Integer> AIFGH = new HashSet<Integer>(Awithout);
					AIFGH.addAll(Iwithout);
					AIFGH.addAll(F);
					AIFGH.addAll(G);
					AIFGH.addAll(H);
					Set<Integer> AIFGH_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!AIFGH.contains(i)) {
							AIFGH_FZ.add(i);
						}
					}
					all.addAll(AIFGH_FZ);

					Set<Integer> EBJGH = new HashSet<Integer>(E);
					EBJGH.addAll(Bwithout);
					EBJGH.addAll(Jwithout);
					EBJGH.addAll(G);
					EBJGH.addAll(H);
					Set<Integer> EBJGH_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!EBJGH.contains(i)) {
							EBJGH_FZ.add(i);
						}
					}
					all.addAll(EBJGH_FZ);

					Set<Integer> EFCKH = new HashSet<Integer>(E);
					EFCKH.addAll(F);
					EFCKH.addAll(Cwithout);
					EFCKH.addAll(Kwithout);
					EFCKH.addAll(H);
					Set<Integer> EFCKH_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!EFCKH.contains(i)) {
							EFCKH_FZ.add(i);
						}
					}
					all.addAll(EFCKH_FZ);

					Set<Integer> EFGDL = new HashSet<Integer>(E);
					EFGDL.addAll(F);
					EFGDL.addAll(G);
					EFGDL.addAll(Dwithout);
					EFGDL.addAll(Lwithout);
					Set<Integer> EFGDL_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!EFGDL.contains(i)) {
							EFGDL_FZ.add(i);
						}
					}
					all.addAll(EFGDL_FZ);

					Set<Integer> AEJKL = new HashSet<Integer>(Awithout);
					AEJKL.addAll(Ewithout);
					AEJKL.addAll(J);
					AEJKL.addAll(K);
					AEJKL.addAll(L);
					Set<Integer> AEJKL_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!AEJKL.contains(i)) {
							AEJKL_FZ.add(i);
						}
					}
					all.addAll(AEJKL_FZ);

					Set<Integer> IBFKL = new HashSet<Integer>(I);
					IBFKL.addAll(Bwithout);
					IBFKL.addAll(Fwithout);
					IBFKL.addAll(K);
					IBFKL.addAll(L);
					Set<Integer> IBFKL_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!IBFKL.contains(i)) {
							IBFKL_FZ.add(i);
						}
					}
					all.addAll(IBFKL_FZ);

					Set<Integer> IJCGL = new HashSet<Integer>(I);
					IJCGL.addAll(J);
					IJCGL.addAll(Cwithout);
					IJCGL.addAll(Gwithout);
					IJCGL.addAll(L);
					Set<Integer> IJCGL_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!IJCGL.contains(i)) {
							IJCGL_FZ.add(i);
						}
					}
					all.addAll(IJCGL_FZ);

					Set<Integer> IJKDH = new HashSet<Integer>(I);
					IJKDH.addAll(J);
					IJKDH.addAll(K);
					IJKDH.addAll(Dwithout);
					IJKDH.addAll(Hwithout);
					Set<Integer> IJKDH_FZ = new HashSet<Integer>();
					for (int i = 1; i < 50; i++) {
						if (!IJKDH.contains(i)) {
							IJKDH_FZ.add(i);
						}
					}
					all.addAll(IJKDH_FZ);

					ZmkmYz data = repositories.zmkmRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
					if (data == null) {
						data = new ZmkmYz();
						data.setYear(kj.getYear());
						data.setPhase(kj.getPhase());
						data.setDate(kj.getDate());
						data.setSpecialNum(kj.getSpecialNum());
					}

					assembleZmkm(data, all);

					repositories.zmkmRepository.save(data);
				}
			}
			request = request.next();
		} while (result != null && result.hasNext());
	}

	protected void assembleZmkm(ZmkmYz data, Collection<Integer> arr) throws Exception {
		List<XbwJYSub> all = new ArrayList<XbwJYSub>();
		for (int num : arr) {
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
		for (XbwJYSub sub : all) {
			Method m = ReflectionUtils.findMethod(data.getClass(), "getC" + sub.getCount());
			List<Integer> values = (List<Integer>) m.invoke(data);
			values.add(sub.getNum());
			Collections.sort(values);
		}
		data.assemble();
	}
}
