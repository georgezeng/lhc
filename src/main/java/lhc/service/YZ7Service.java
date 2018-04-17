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
import org.springframework.util.StringUtils;

import com.google.common.base.Joiner;

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
import lhc.domain.BaseYz;
import lhc.domain.CyhtYz;
import lhc.domain.DsxMnYz;
import lhc.domain.KaiJiang;
import lhc.domain.My100MnYz;
import lhc.domain.My100XcMnYz;
import lhc.domain.MyMnYz;
import lhc.domain.MyXcMnYz;
import lhc.domain.MyYz;
import lhc.domain.ScydYz;
import lhc.domain.SwMnYz;
import lhc.domain.SwXcMnYz;
import lhc.domain.TmYz;
import lhc.dto.TmYzInfo;
import lhc.dto.XbwJYSub;
import lhc.enums.SX;
import lhc.repository.jpa.BaseYzRepository;
import lhc.util.DateUtil;

@Transactional
@SuppressWarnings("unchecked")
public class YZ7Service extends YZ6Service {
	@Async
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Future<Exception> calMyMn() {
		Exception t = null;
		try {
			calDsxMnForMy(MyMnYz.class, repositories.myMnRepository, repositories.myD1Repository, repositories.my1Repository);
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
	public Future<Exception> calMyXcMn() {
		Exception t = null;
		try {
			calDsxXcMnForMy(MyXcMnYz.class, repositories.myxcMnRepository, repositories.myD1Repository, repositories.my1Repository);
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
	public Future<Exception> calMy100Mn() {
		Exception t = null;
		try {
			calDsxMnForMy(My100MnYz.class, repositories.my100MnRepository, repositories.my100Repository, repositories.my200Repository);
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
	public Future<Exception> calMy100XcMn() {
		Exception t = null;
		try {
			calDsxXcMnForMy(My100XcMnYz.class, repositories.my100xcMnRepository, repositories.my100Repository, repositories.my200Repository);
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
	public Future<Exception> calSwMn() {
		Exception t = null;
		try {
			calDsxMn(SwMnYz.class, repositories.swMnRepository, repositories.fxsw2Repository, repositories.fxsw3Repository);
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
	public Future<Exception> calSwXcMn() {
		Exception t = null;
		try {
			calDsxXcMn(SwXcMnYz.class, repositories.swxcMnRepository, repositories.fxsw2Repository, repositories.fxsw3Repository);
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	private <T extends DsxMnYz, A extends MyYz, E extends MyYz> void calDsxMnForMy(Class<T> clazz, BaseYzRepository<T> repository,
			BaseYzRepository<A> abcdRepository, BaseYzRepository<E> efghRepository) throws Exception {
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
						int pos = Integer.valueOf(abcdYz.getFdmy().replace("位", "")) - 1;
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

					MyYz efghYz = efghRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());

					Set<Integer> E = new HashSet<Integer>();
					sx = SX.textOf(efghYz.getSxmy());
					if (sx != null) {
						List<Integer> sxNums = getSxNums(bmnSX, sx);
						addNumsToConditionList(E, sxNums);
					}
					if (efghYz.getTwelvemy() != null) {
						addNumsToConditionList(E, TwelveNums.NUMS[Integer.valueOf(efghYz.getTwelvemy().replace("位", "")) - 1]);
					}
					if (efghYz.getSlqmy() != null) {
						addNumsToConditionList(E, SlqNums.NUMS[Integer.valueOf(efghYz.getSlqmy().replace("位", "")) - 1]);
					}
					if (efghYz.getZx12my() != null) {
						addNumsToConditionList(E, Zx12Nums.NUMS[Integer.valueOf(efghYz.getZx12my().replace("位", "")) - 1]);
					}

					Set<Integer> F = new HashSet<Integer>();
					if (efghYz.getLhmy() != null) {
						addNumsToConditionList(F, LhNums.NUMS[Integer.valueOf(efghYz.getLhmy().replace("位", ""))]);
					}
					if (efghYz.getMwmy() != null) {
						addNumsToConditionList(F, MwNums.NUMS[Integer.valueOf(efghYz.getMwmy().replace("位", ""))]);
					}
					if (efghYz.getFdmy() != null) {
						int pos = Integer.valueOf(efghYz.getFdmy().replace("位", "")) - 1;
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
					if (efghYz.getPdmy() != null) {
						addNumsToConditionList(F, PdNums.NUMS[Integer.valueOf(efghYz.getPdmy().replace("位", "")) - 1]);
					}

					Set<Integer> G = new HashSet<Integer>();
					if (efghYz.getZsmy() != null) {
						addNumsToConditionList(G, ZsNums.NUMS[Integer.valueOf(efghYz.getZsmy().replace("位", "")) - 1]);
					}
					if (efghYz.getWxdsmy() != null) {
						addNumsToConditionList(G, WxDsNums.NUMS_MAP.get(efghYz.getWxdsmy()));
					}
					if (efghYz.getBsmy() != null) {
						addNumsToConditionList(G, Bs9qNums.NUMS_MAP.get(efghYz.getBsmy()));
					}

					Set<Integer> H = new HashSet<Integer>();
					if (efghYz.getSwmy() != null) {
						addNumsToConditionList(H, SwNums.NUMS[Integer.valueOf(efghYz.getSwmy().replace("位", ""))]);
					}
					if (efghYz.getQiwmy() != null) {
						addNumsToConditionList(H, QiwNums.NUMS[Integer.valueOf(efghYz.getQiwmy().replace("位", "")) - 1]);
					}

					Set<Integer>[] nums = collectConditionList(A, B, C, D, E, F, G, H, true);
					T data = repository.findByYearAndPhase(kj.getYear(), kj.getPhase());
					if (data == null) {
						data = clazz.newInstance();
						data.setYear(kj.getYear());
						data.setPhase(kj.getPhase());
						data.setDate(kj.getDate());
						data.setSpecialNum(kj.getSpecialNum());
					}
					assembleDsxMn(data, nums);
					repository.save(data);
				}
			}
			request = request.next();
		} while (result != null && result.hasNext());
	}

	private <T extends DsxMnYz, A extends MyYz, E extends MyYz> void calDsxXcMnForMy(Class<T> clazz, BaseYzRepository<T> repository,
			BaseYzRepository<A> abcdRepository, BaseYzRepository<E> efghRepository) throws Exception {
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
						int pos = Integer.valueOf(abcdYz.getFdmy().replace("位", "")) - 1;
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

					MyYz efghYz = efghRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());

					Set<Integer> E = new HashSet<Integer>();
					sx = SX.textOf(efghYz.getSxmy());
					if (sx != null) {
						List<Integer> sxNums = getSxNums(bmnSX, sx);
						addNumsToConditionList(E, sxNums);
					}
					if (efghYz.getTwelvemy() != null) {
						addNumsToConditionList(E, TwelveNums.NUMS[Integer.valueOf(efghYz.getTwelvemy().replace("位", "")) - 1]);
					}
					if (efghYz.getSlqmy() != null) {
						addNumsToConditionList(E, SlqNums.NUMS[Integer.valueOf(efghYz.getSlqmy().replace("位", "")) - 1]);
					}
					if (efghYz.getZx12my() != null) {
						addNumsToConditionList(E, Zx12Nums.NUMS[Integer.valueOf(efghYz.getZx12my().replace("位", "")) - 1]);
					}

					Set<Integer> F = new HashSet<Integer>();
					if (efghYz.getLhmy() != null) {
						addNumsToConditionList(F, LhNums.NUMS[Integer.valueOf(efghYz.getLhmy().replace("位", ""))]);
					}
					if (efghYz.getMwmy() != null) {
						addNumsToConditionList(F, MwNums.NUMS[Integer.valueOf(efghYz.getMwmy().replace("位", ""))]);
					}
					if (efghYz.getFdmy() != null) {
						int pos = Integer.valueOf(efghYz.getFdmy().replace("位", "")) - 1;
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
					if (efghYz.getPdmy() != null) {
						addNumsToConditionList(F, PdNums.NUMS[Integer.valueOf(efghYz.getPdmy().replace("位", "")) - 1]);
					}

					Set<Integer> G = new HashSet<Integer>();
					if (efghYz.getZsmy() != null) {
						addNumsToConditionList(G, ZsNums.NUMS[Integer.valueOf(efghYz.getZsmy().replace("位", "")) - 1]);
					}
					if (efghYz.getWxdsmy() != null) {
						addNumsToConditionList(G, WxDsNums.NUMS_MAP.get(efghYz.getWxdsmy()));
					}
					if (efghYz.getBsmy() != null) {
						addNumsToConditionList(G, Bs9qNums.NUMS_MAP.get(efghYz.getBsmy()));
					}

					Set<Integer> H = new HashSet<Integer>();
					if (efghYz.getSwmy() != null) {
						addNumsToConditionList(H, SwNums.NUMS[Integer.valueOf(efghYz.getSwmy().replace("位", ""))]);
					}
					if (efghYz.getQiwmy() != null) {
						addNumsToConditionList(H, QiwNums.NUMS[Integer.valueOf(efghYz.getQiwmy().replace("位", "")) - 1]);
					}

					Set<Integer>[] nums = new Set[] { A, B, C, D, E, F, G, H };
					T data = repository.findByYearAndPhase(kj.getYear(), kj.getPhase());
					if (data == null) {
						data = clazz.newInstance();
						data.setYear(kj.getYear());
						data.setPhase(kj.getPhase());
						data.setDate(kj.getDate());
						data.setSpecialNum(kj.getSpecialNum());
					}
					assembleDsxMn(data, nums);
					repository.save(data);
				}
			}
			request = request.next();
		} while (result != null && result.hasNext());
	}

	private <T extends DsxMnYz> void calDsxMn(Class<T> clazz, BaseYzRepository<T> repository, BaseYzRepository<? extends BaseYz> abcdRepository,
			BaseYzRepository<?> efghRepository) throws Exception {
		Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
		Page<KaiJiang> result = null;
		do {
			result = repositories.kaiJiangRepository.findAll(request);
			if (result != null && result.hasContent()) {
				for (KaiJiang kj : result.getContent()) {
					BaseYz abcdYz = abcdRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());

					Set<Integer> A = new HashSet<Integer>();
					addNumsToConditionList(A, getNums(abcdYz, "Sx"));
					addNumsToConditionList(A, getNums(abcdYz, "Twelve"));
					addNumsToConditionList(A, getNums(abcdYz, "Slq"));
					addNumsToConditionList(A, getNums(abcdYz, "Zx12"));

					Set<Integer> B = new HashSet<Integer>();
					addNumsToConditionList(B, getNums(abcdYz, "Lh"));
					addNumsToConditionList(B, getNums(abcdYz, "Mw"));
					addNumsToConditionList(B, getNums(abcdYz, "Fd"));
					addNumsToConditionList(B, getNums(abcdYz, "Pd"));

					Set<Integer> C = new HashSet<Integer>();
					addNumsToConditionList(C, getNums(abcdYz, "Zs"));
					addNumsToConditionList(C, getNums(abcdYz, "Wxds"));
					addNumsToConditionList(C, getNums(abcdYz, "Bs"));

					Set<Integer> D = new HashSet<Integer>();
					addNumsToConditionList(D, getNums(abcdYz, "Sw"));
					addNumsToConditionList(D, getNums(abcdYz, "Qiw"));

					BaseYz efghYz = efghRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());

					Set<Integer> E = new HashSet<Integer>();
					addNumsToConditionList(E, getNums(efghYz, "Sx"));
					addNumsToConditionList(E, getNums(efghYz, "Twelve"));
					addNumsToConditionList(E, getNums(efghYz, "Slq"));
					addNumsToConditionList(E, getNums(efghYz, "Zx12"));

					Set<Integer> F = new HashSet<Integer>();
					addNumsToConditionList(F, getNums(efghYz, "Lh"));
					addNumsToConditionList(F, getNums(efghYz, "Mw"));
					addNumsToConditionList(F, getNums(efghYz, "Fd"));
					addNumsToConditionList(F, getNums(efghYz, "Pd"));

					Set<Integer> G = new HashSet<Integer>();
					addNumsToConditionList(G, getNums(efghYz, "Zs"));
					addNumsToConditionList(G, getNums(efghYz, "Wxds"));
					addNumsToConditionList(G, getNums(efghYz, "Bs"));

					Set<Integer> H = new HashSet<Integer>();
					addNumsToConditionList(H, getNums(efghYz, "Sw"));
					addNumsToConditionList(H, getNums(efghYz, "Qiw"));

					Set<Integer>[] nums = collectConditionList(A, B, C, D, E, F, G, H, true);
					T data = repository.findByYearAndPhase(kj.getYear(), kj.getPhase());
					if (data == null) {
						data = clazz.newInstance();
						data.setYear(kj.getYear());
						data.setPhase(kj.getPhase());
						data.setDate(kj.getDate());
						data.setSpecialNum(kj.getSpecialNum());
					}
					assembleDsxMn(data, nums);
					repository.save(data);
				}
			}
			request = request.next();
		} while (result != null && result.hasNext());
	}

	private <T extends DsxMnYz> void calDsxXcMn(Class<T> clazz, BaseYzRepository<T> repository, BaseYzRepository<? extends BaseYz> abcdRepository,
			BaseYzRepository<?> efghRepository) throws Exception {
		Pageable request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
		Page<KaiJiang> result = null;
		do {
			result = repositories.kaiJiangRepository.findAll(request);
			if (result != null && result.hasContent()) {
				for (KaiJiang kj : result.getContent()) {
					BaseYz abcdYz = abcdRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());

					Set<Integer> A = new HashSet<Integer>();
					addNumsToConditionList(A, getNums(abcdYz, "Sx"));
					addNumsToConditionList(A, getNums(abcdYz, "Twelve"));
					addNumsToConditionList(A, getNums(abcdYz, "Slq"));
					addNumsToConditionList(A, getNums(abcdYz, "Zx12"));

					Set<Integer> B = new HashSet<Integer>();
					addNumsToConditionList(B, getNums(abcdYz, "Lh"));
					addNumsToConditionList(B, getNums(abcdYz, "Mw"));
					addNumsToConditionList(B, getNums(abcdYz, "Fd"));
					addNumsToConditionList(B, getNums(abcdYz, "Pd"));

					Set<Integer> C = new HashSet<Integer>();
					addNumsToConditionList(C, getNums(abcdYz, "Zs"));
					addNumsToConditionList(C, getNums(abcdYz, "Wxds"));
					addNumsToConditionList(C, getNums(abcdYz, "Bs"));

					Set<Integer> D = new HashSet<Integer>();
					addNumsToConditionList(D, getNums(abcdYz, "Sw"));
					addNumsToConditionList(D, getNums(abcdYz, "Qiw"));

					BaseYz efghYz = efghRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());

					Set<Integer> E = new HashSet<Integer>();
					addNumsToConditionList(E, getNums(efghYz, "Sx"));
					addNumsToConditionList(E, getNums(efghYz, "Twelve"));
					addNumsToConditionList(E, getNums(efghYz, "Slq"));
					addNumsToConditionList(E, getNums(efghYz, "Zx12"));

					Set<Integer> F = new HashSet<Integer>();
					addNumsToConditionList(F, getNums(efghYz, "Lh"));
					addNumsToConditionList(F, getNums(efghYz, "Mw"));
					addNumsToConditionList(F, getNums(efghYz, "Fd"));
					addNumsToConditionList(F, getNums(efghYz, "Pd"));

					Set<Integer> G = new HashSet<Integer>();
					addNumsToConditionList(G, getNums(efghYz, "Zs"));
					addNumsToConditionList(G, getNums(efghYz, "Wxds"));
					addNumsToConditionList(G, getNums(efghYz, "Bs"));

					Set<Integer> H = new HashSet<Integer>();
					addNumsToConditionList(H, getNums(efghYz, "Sw"));
					addNumsToConditionList(H, getNums(efghYz, "Qiw"));

					Set<Integer>[] nums = new Set[] { A, B, C, D, E, F, G, H };
					T data = repository.findByYearAndPhase(kj.getYear(), kj.getPhase());
					if (data == null) {
						data = clazz.newInstance();
						data.setYear(kj.getYear());
						data.setPhase(kj.getPhase());
						data.setDate(kj.getDate());
						data.setSpecialNum(kj.getSpecialNum());
					}
					assembleDsxMn(data, nums);
					repository.save(data);
				}
			}
			request = request.next();
		} while (result != null && result.hasNext());
	}

	protected List<Integer> getNums(BaseYz abcdYz, String field) throws Exception {
		String numsStr = (String) ReflectionUtils.findMethod(abcdYz.getClass(), "get" + field + "Nums").invoke(abcdYz);
		return getNums(numsStr);
	}

	protected List<Integer> getNums(String numsStr) throws Exception {
		List<Integer> nums = new ArrayList<Integer>();
		if (!StringUtils.isEmpty(numsStr)) {
			for (String numStr : numsStr.split(",\\s*")) {
				nums.add(Integer.valueOf(numStr));
			}
		}
		return nums;
	}

	protected void assembleDsxMn(DsxMnYz data, Collection<Integer>[] arr) throws Exception {
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
			if (sub.getCount() > 10) {
				continue;
			}
			Method m = ReflectionUtils.findMethod(data.getClass(), "getC" + sub.getCount());
			List<Integer> values = (List<Integer>) m.invoke(data);
			values.add(sub.getNum());
			Collections.sort(values);
		}
		data.assemble();
	}

	@Async
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Future<Exception> calCyht() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.DESC, "date"));
			Page<KaiJiang> result = null;
			KaiJiang lastKj = null;
			do {
				result = repositories.kaiJiangRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (KaiJiang kj : result.getContent()) {
						CyhtYz data = repositories.cyhtRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						if (data == null) {
							data = new CyhtYz();
							data.setDate(kj.getDate());
							data.setYear(kj.getYear());
							data.setPhase(kj.getPhase());
							data.setSpecialNum(kj.getSpecialNum());
						}
						MyMnYz my = repositories.myMnRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						Set<Integer> myNums = new HashSet<Integer>();
						myNums.addAll(getNums(my.getC5Nums()));
						myNums.addAll(getNums(my.getC10Nums()));
						data.setMyNums(Joiner.on(",").join(myNums));

						My100MnYz my100 = repositories.my100MnRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						Set<Integer> my100Nums = new HashSet<Integer>();
						my100Nums.addAll(getNums(my100.getC5Nums()));
						my100Nums.addAll(getNums(my100.getC10Nums()));
						data.setMy100Nums(Joiner.on(",").join(my100Nums));

						SwMnYz sw = repositories.swMnRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						Set<Integer> swNums = new HashSet<Integer>();
						swNums.addAll(getNums(sw.getC5Nums()));
						swNums.addAll(getNums(sw.getC10Nums()));
						data.setSwNums(Joiner.on(",").join(swNums));

						Set<Integer> v1Nums = new HashSet<Integer>();
						v1Nums.addAll(myNums);
						v1Nums.addAll(my100Nums);
						data.setV1Nums(Joiner.on(",").join(v1Nums));

						Set<Integer> v2Nums = new HashSet<Integer>();
						v2Nums.addAll(myNums);
						v2Nums.addAll(swNums);
						data.setV2Nums(Joiner.on(",").join(v2Nums));

						Set<Integer> v3Nums = new HashSet<Integer>();
						v3Nums.addAll(my100Nums);
						v3Nums.addAll(swNums);
						data.setV3Nums(Joiner.on(",").join(v3Nums));

						if (lastKj != null) {
							if (myNums.contains(lastKj.getSpecialNum())) {
								data.setMyYz(0);
							}

							if (my100Nums.contains(lastKj.getSpecialNum())) {
								data.setMy100Yz(0);
							}

							if (swNums.contains(lastKj.getSpecialNum())) {
								data.setSwYz(0);
							}

							if (v1Nums.contains(lastKj.getSpecialNum())) {
								data.setV1Yz(0);
							}

							if (v2Nums.contains(lastKj.getSpecialNum())) {
								data.setV2Yz(0);
							}

							if (v3Nums.contains(lastKj.getSpecialNum())) {
								data.setV3Yz(0);
							}
						}
						repositories.cyhtRepository.save(data);
						lastKj = kj;
					}
				}
				request = request.next();
			} while (result != null && result.hasNext());

			request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			result = null;
			CyhtYz lastData = null;
			do {
				result = repositories.kaiJiangRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (KaiJiang kj : result.getContent()) {
						CyhtYz data = repositories.cyhtRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						if (lastData != null) {
							if (data.getMyYz() == null && lastData.getMyYz() != null) {
								data.setMyYz(lastData.getMyYz() + 1);
							}
							if (data.getMy100Yz() == null && lastData.getMy100Yz() != null) {
								data.setMy100Yz(lastData.getMy100Yz() + 1);
							}
							if (data.getSwYz() == null && lastData.getSwYz() != null) {
								data.setSwYz(lastData.getSwYz() + 1);
							}
							if (data.getV1Yz() == null && lastData.getV1Yz() != null) {
								data.setV1Yz(lastData.getV1Yz() + 1);
							}
							if (data.getV2Yz() == null && lastData.getV2Yz() != null) {
								data.setV2Yz(lastData.getV2Yz() + 1);
							}
							if (data.getV3Yz() == null && lastData.getV3Yz() != null) {
								data.setV3Yz(lastData.getV3Yz() + 1);
							}
						}
						repositories.cyhtRepository.save(data);
						lastData = data;
					}
				}
				request = request.next();
			} while (result != null && result.hasNext());
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
	public Future<Exception> calScyd() {
		Exception t = null;
		try {
			Pageable request = new PageRequest(0, 200, new Sort(Direction.DESC, "date"));
			Page<KaiJiang> result = null;
			KaiJiang lastKj = null;
			do {
				result = repositories.kaiJiangRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (KaiJiang kj : result.getContent()) {
						ScydYz data = repositories.scydRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						if (data == null) {
							data = new ScydYz();
							data.setDate(kj.getDate());
							data.setYear(kj.getYear());
							data.setPhase(kj.getPhase());
							data.setSpecialNum(kj.getSpecialNum());
						}
						MyXcMnYz my = repositories.myxcMnRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						Set<Integer> myNums = new HashSet<Integer>();
						myNums.addAll(getNums(my.getC2Nums()));
						myNums.addAll(getNums(my.getC3Nums()));
						myNums.addAll(getNums(my.getC4Nums()));
						data.setMyNums(Joiner.on(",").join(myNums));

						My100XcMnYz my100 = repositories.my100xcMnRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						Set<Integer> my100Nums = new HashSet<Integer>();
						my100Nums.addAll(getNums(my100.getC2Nums()));
						my100Nums.addAll(getNums(my100.getC3Nums()));
						my100Nums.addAll(getNums(my100.getC4Nums()));
						data.setMy100Nums(Joiner.on(",").join(my100Nums));

						SwXcMnYz sw = repositories.swxcMnRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						Set<Integer> swNums = new HashSet<Integer>();
						swNums.addAll(getNums(sw.getC2Nums()));
						swNums.addAll(getNums(sw.getC3Nums()));
						swNums.addAll(getNums(sw.getC4Nums()));
						data.setSwNums(Joiner.on(",").join(swNums));

						Set<Integer> v1Nums = new HashSet<Integer>();
						v1Nums.addAll(myNums);
						v1Nums.addAll(my100Nums);
						data.setV1Nums(Joiner.on(",").join(v1Nums));

						Set<Integer> v2Nums = new HashSet<Integer>();
						v2Nums.addAll(myNums);
						v2Nums.addAll(swNums);
						data.setV2Nums(Joiner.on(",").join(v2Nums));

						Set<Integer> v3Nums = new HashSet<Integer>();
						v3Nums.addAll(my100Nums);
						v3Nums.addAll(swNums);
						data.setV3Nums(Joiner.on(",").join(v3Nums));

						if (lastKj != null) {
							if (myNums.contains(lastKj.getSpecialNum())) {
								data.setMyYz(0);
							}

							if (my100Nums.contains(lastKj.getSpecialNum())) {
								data.setMy100Yz(0);
							}

							if (swNums.contains(lastKj.getSpecialNum())) {
								data.setSwYz(0);
							}

							if (v1Nums.contains(lastKj.getSpecialNum())) {
								data.setV1Yz(0);
							}

							if (v2Nums.contains(lastKj.getSpecialNum())) {
								data.setV2Yz(0);
							}

							if (v3Nums.contains(lastKj.getSpecialNum())) {
								data.setV3Yz(0);
							}
						}
						repositories.scydRepository.save(data);
						lastKj = kj;
					}
				}
				request = request.next();
			} while (result != null && result.hasNext());

			request = new PageRequest(0, 200, new Sort(Direction.ASC, "date"));
			result = null;
			ScydYz lastData = null;
			do {
				result = repositories.kaiJiangRepository.findAll(request);
				if (result != null && result.hasContent()) {
					for (KaiJiang kj : result.getContent()) {
						ScydYz data = repositories.scydRepository.findByYearAndPhase(kj.getYear(), kj.getPhase());
						if (lastData != null) {
							if (data.getMyYz() == null && lastData.getMyYz() != null) {
								data.setMyYz(lastData.getMyYz() + 1);
							}
							if (data.getMy100Yz() == null && lastData.getMy100Yz() != null) {
								data.setMy100Yz(lastData.getMy100Yz() + 1);
							}
							if (data.getSwYz() == null && lastData.getSwYz() != null) {
								data.setSwYz(lastData.getSwYz() + 1);
							}
							if (data.getV1Yz() == null && lastData.getV1Yz() != null) {
								data.setV1Yz(lastData.getV1Yz() + 1);
							}
							if (data.getV2Yz() == null && lastData.getV2Yz() != null) {
								data.setV2Yz(lastData.getV2Yz() + 1);
							}
							if (data.getV3Yz() == null && lastData.getV3Yz() != null) {
								data.setV3Yz(lastData.getV3Yz() + 1);
							}
						}
						repositories.scydRepository.save(data);
						lastData = data;
					}
				}
				request = request.next();
			} while (result != null && result.hasNext());
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

}
