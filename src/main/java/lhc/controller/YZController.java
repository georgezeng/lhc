package lhc.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lhc.domain.KaiJiang;
import lhc.domain.SxYz;
import lhc.domain.SxZfYz;
import lhc.dto.BaseResult;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.repository.jpa.api.KaiJiangRepository;
import lhc.repository.jpa.dao.SxYzDao;
import lhc.repository.jpa.dao.SxZfYzDao;
import lhc.service.YZService;

@RestController
@RequestMapping("/mvc/yz")
public class YZController {
	@Autowired
	private YZService yzService;

	@Autowired
	private KaiJiangRepository KaiJiangRepository;

	@Autowired
	private SxYzDao sxYzDao;

	@Autowired
	private SxZfYzDao sxZfYzDao;

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

	@RequestMapping("/calSX")
	public BaseResult calSX() {
		yzService.calSX();
		yzService.calSXZF();
		return BaseResult.EMPTY;
	}

	@RequestMapping("/listSX")
	public BaseResult listSX(@RequestBody QueryInfo<SxYz> queryInfo) {
		return new BaseResult(sxYzDao.query(queryInfo));
	}

	@RequestMapping("/listSXZF")
	public BaseResult listSXZF(@RequestBody QueryInfo<SxZfYz> queryInfo) {
		return new BaseResult(sxZfYzDao.query(queryInfo));
	}

	@RequestMapping("/countSXZF")
	public BaseResult countSXZF(@RequestBody QueryInfo<SxZfYz> queryInfo) throws Exception {
		PageResult<SxZfYz> result = sxZfYzDao.query(queryInfo);
		List<SxZfYz> list = new ArrayList<SxZfYz>();
		if (result != null && result.getTotal() > 0) {
			Collections.reverse(result.getList());
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
					sm.invoke(dto, 0 + (Integer) gm.invoke(lastZF));
				}
				dto.setYear(data.getYear());
				dto.setPhase(data.getPhase());
				for (int i = 0; i < 12; i++) {
					Method gm = SxZfYz.class.getDeclaredMethod("getZf" + i);
					Integer zf = (Integer) gm.invoke(data);
					if (zf != null && zf == 0) {
						Method sm = SxZfYz.class.getDeclaredMethod("setZf" + i, Integer.class);
						sm.invoke(dto, 1 + (Integer) gm.invoke(dto));
						break;
					}
				}
				list.add(dto);
				lastZF = dto;
			}
		}
		result.setList(list);
		return new BaseResult(result);
	}
}
