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

@Service
@Transactional
@SuppressWarnings("unchecked")
public class YZ3Service extends YZ2Service {
	@Async
	public Future<Exception> calDsxMinJY() {
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
			DsxMinJyJQB unknownData = repositories.dsxMinJyJQBRepository.findByYearAndPhase(0, 0);
			if (unknownData == null) {
				unknownData = new DsxMinJyJQB();
				unknownData.setYear(0);
				unknownData.setPhase(0);
				unknownData.setDate("unknown");
				repositories.dsxMinJyJQBRepository.save(unknownData);
			}
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
						FxSw temp = result1.getContent().get(i);
						DsxJY jy = new DsxJY();
						jy.setDate(temp.getDate());
						jy.setYear(temp.getYear());
						jy.setPhase(temp.getPhase());
						DsxMinJyJQB jqb = repositories.dsxMinJyJQBRepository.findByYearAndPhase(temp.getYear(), temp.getPhase());
						if (jqb == null) {
							jqb = new DsxMinJyJQB();
							jqb.setYear(temp.getYear());
							jqb.setPhase(temp.getPhase());
							jqb.setDate(temp.getDate());
							KaiJiang kj = repositories.kaiJiangRepository.findByYearAndPhase(temp.getYear(), temp.getPhase());
							jqb.setSpecialNum(kj.getSpecialNum());
						}
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
							setFxSwMinData(jy, fxData, "Sx");
							setFxSwMinData(jy, fxData, "Sxzf");
							setFxSwMinData(jy, fxData, "Ds");
							setFxSwMinData(jy, fxData, "Dszf");
							setFxSwMinData(jy, fxData, "Sw");
							setFxSwMinData(jy, fxData, "Swzf");
							setFxSwMinData(jy, fxData, "Mw");
							setFxSwMinData(jy, fxData, "Mwzf");
							setFxSwMinData(jy, fxData, "Lh");
							setFxSwMinData(jy, fxData, "Lhzf");
							setFxSwMinData(jy, fxData, "Bs");
							setFxSwMinData(jy, fxData, "Bszf");
							setFxSwMinData(jy, fxData, "Zs");
							setFxSwMinData(jy, fxData, "Zszf");
							setFxSwMinData(jy, fxData, "Wx");
							setFxSwMinData(jy, fxData, "Wxzf");
							setFxSwMinData(jy, fxData, "Wxds");
							setFxSwMinData(jy, fxData, "Wxdszf");
							setFxSwMinData(jy, fxData, "Pd");
							setFxSwMinData(jy, fxData, "Pdzf");
							setFxSwMinData(jy, fxData, "Fd");
							setFxSwMinData(jy, fxData, "Fdzf");
							setFxSwMinData(jy, fxData, "Qq");
							setFxSwMinData(jy, fxData, "Qqzf");
							setFxSwMinData(jy, fxData, "Qiw");
							setFxSwMinData(jy, fxData, "Qiwzf");
							setFxSwMinData(jy, fxData, "Twelve");
							setFxSwMinData(jy, fxData, "Twelvezf");
							setFxSwMinData(jy, fxData, "Slq");
							setFxSwMinData(jy, fxData, "Slqzf");
							setFxSwMinData(jy, fxData, "Zx1");
							setFxSwMinData(jy, fxData, "Zx1zf");
							setFxSwMinData(jy, fxData, "Zx2");
							setFxSwMinData(jy, fxData, "Zx2zf");
							setFxSwMinData(jy, fxData, "Zx3");
							setFxSwMinData(jy, fxData, "Zx3zf");
							setFxSwMinData(jy, fxData, "Zx4");
							setFxSwMinData(jy, fxData, "Zx4zf");
							setFxSwMinData(jy, fxData, "Zx5");
							setFxSwMinData(jy, fxData, "Zx5zf");
							setFxSwMinData(jy, fxData, "Zx6");
							setFxSwMinData(jy, fxData, "Zx6zf");
							setFxSwMinData(jy, fxData, "Zx7");
							setFxSwMinData(jy, fxData, "Zx7zf");
							setFxSwMinData(jy, fxData, "Zx8");
							setFxSwMinData(jy, fxData, "Zx8zf");
							setFxSwMinData(jy, fxData, "Zx9");
							setFxSwMinData(jy, fxData, "Zx9zf");
							setFxSwMinData(jy, fxData, "Zx10");
							setFxSwMinData(jy, fxData, "Zx10zf");
							setFxSwMinData(jy, fxData, "Zx11");
							setFxSwMinData(jy, fxData, "Zx11zf");
							setFxSwMinData(jy, fxData, "Zx12");
							setFxSwMinData(jy, fxData, "Zx12zf");
							setFxSwMinData(jy, fxData, "Zx13");
							setFxSwMinData(jy, fxData, "Zx13zf");
							setFxSwMinData(jy, fxData, "Zx14");
							setFxSwMinData(jy, fxData, "Zx14zf");
							setFxSwMinData(jy, fxData, "Zx15");
							setFxSwMinData(jy, fxData, "Zx15zf");
							setFxSwMinData(jy, fxData, "Zx16");
							setFxSwMinData(jy, fxData, "Zx16zf");
							setFxSwMinData(jy, fxData, "Zx17");
							setFxSwMinData(jy, fxData, "Zx17zf");
							setFxSwMinData(jy, fxData, "Zx18");
							setFxSwMinData(jy, fxData, "Zx18zf");
						}
						if (lastJY != null) {
							calDsxJYForJQB(jqb, lastJY);
							calDsxJYForJQB(jqb, lastJY, true);
							calDsxJYForJQB(jqb, lastJY, false);
						}
						repositories.dsxMinJyJQBRepository.save(jqb);
						lastJY = jy;
					}
				}
				request = request.next();
			} while (result1 != null && result1.hasNext());
			if (lastJY != null) {
				unknownData.reset();
				calDsxJYForJQB(unknownData, lastJY);
				calDsxJYForJQB(unknownData, lastJY, true);
				calDsxJYForJQB(unknownData, lastJY, false);
				repositories.dsxMinJyJQBRepository.save(unknownData);
			}
			logger.info("End of calDsxMinRedCounts...");
		} catch (Exception e) {
			if (DataAccessException.class.isAssignableFrom(e.getClass())) {
				logger.error(e.getMessage(), e);
			}
			t = e;
		}
		return new AsyncResult<Exception>(t);
	}

	protected void setFxSwMinData(DsxJY jy, FxSw data, String column) throws Exception {
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
			if (redCountsForJY.compareTo(redCountsForFx) > 0) {
				toOverride = true;
			} else if (redCountsForJY.compareTo(redCountsForFx) == 0) {
				Integer yzForJY = (Integer) gmForYzForJY.invoke(jy);
				Integer yzForFx = (Integer) gmForYzForFx.invoke(data);
				if (yzForFx != null) {
					if (yzForJY == null) {
						toOverride = true;
					} else if (yzForJY.compareTo(yzForFx) > 0) {
						toOverride = true;
					}
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

	protected void calDsxJYForJQB(DsxMinJyJQB data, DsxJY lastJY) throws Exception {
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

	protected StringBuilder appendNums(StringBuilder sb, String numStr) {
		if (numStr != null) {
			sb.append(numStr).append(",");
		}
		return sb;
	}

	protected void calDsxJYForJQB(DsxMinJyJQB data, DsxJY lastJY, boolean reverse) throws Exception {
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
				m = ReflectionUtils.findMethod(DsxMinJyJQB.class, "getC" + sub.getCount());
			} else {
				m = ReflectionUtils.findMethod(DsxMinJyJQB.class, "getC11");
			}
			List<Integer> values = (List<Integer>) m.invoke(data);
			values.add(sub.getNum());
			Collections.sort(values);
		}
		data.assemble(qc, reverse);
	}
}
