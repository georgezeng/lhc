package lhc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lhc.domain.KaiJiang;
import lhc.dto.BaseResult;
import lhc.dto.query.QueryInfo;
import lhc.repository.jpa.api.KaiJiangRepository;
import lhc.repository.jpa.dao.SxYzDao;
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
		return BaseResult.EMPTY;
	}

	@RequestMapping("/listSX")
	public BaseResult listSX(@RequestBody QueryInfo<Integer> queryInfo) {
		return new BaseResult(sxYzDao.query(queryInfo));
	}
}
