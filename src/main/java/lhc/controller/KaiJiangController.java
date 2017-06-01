package lhc.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lhc.domain.KaiJiang;
import lhc.dto.BaseResult;
import lhc.dto.query.PageResult;
import lhc.dto.query.QueryInfo;
import lhc.repository.jpa.api.KaiJiangRepository;
import lhc.repository.jpa.api.QwYzRepository;
import lhc.repository.jpa.api.SxYzRepository;
import lhc.repository.jpa.api.SxZfYzRepository;
import lhc.repository.jpa.dao.KaiJiangDao;
import lhc.service.ParseService;

@RestController
@RequestMapping("/mvc/kj")
public class KaiJiangController {
	@Autowired
	private ParseService parseService;

	@Autowired
	private KaiJiangDao kaiJiangDao;

	@Autowired
	private KaiJiangRepository kaiJiangRepository;

	@Autowired
	private QwYzRepository qwYzRepository;

	@Autowired
	private SxYzRepository sxYzRepository;

	@Autowired
	private SxZfYzRepository sxZfYzRepository;

	@Autowired
	private YZController yZController;

	@RequestMapping("/sync/{year}")
	public BaseResult sync(@PathVariable int year) {
		delete(year);
		parseService.syncKaiJiang(year);
		yZController.calYZ();
		return BaseResult.EMPTY;
	}

	@RequestMapping("/delete/{year}")
	public BaseResult delete(@PathVariable int year) {
		kaiJiangRepository.deleteByYear(year);
		qwYzRepository.deleteAll();
		sxYzRepository.deleteAll();
		sxZfYzRepository.deleteAll();
		return BaseResult.EMPTY;
	}

	@RequestMapping("/list")
	public BaseResult list(@RequestBody QueryInfo<String> queryInfo) {
		return new BaseResult(kaiJiangDao.query(queryInfo));
	}

	@RequestMapping("/download")
	public String download(String searchKey, HttpServletResponse response) {
		QueryInfo<String> queryInfo = new QueryInfo<String>();
		queryInfo.setObject(searchKey);
		PageResult<KaiJiang> result = kaiJiangDao.query(queryInfo);
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			StringBuilder content = new StringBuilder();
			content.append("年份, 日期, 期数, 平码, 特码").append("\n");
			for (KaiJiang data : result.getList()) {
				content.append(data.getYear()).append(",");
				content.append(data.getDate()).append(",");
				content.append(data.getPhase()).append(",");
				content.append(data.getNum1Sx().getText() + "(" + data.getNum1() + ")").append(" ");
				content.append(data.getNum2Sx().getText() + "(" + data.getNum2() + ")").append(" ");
				content.append(data.getNum3Sx().getText() + "(" + data.getNum3() + ")").append(" ");
				content.append(data.getNum4Sx().getText() + "(" + data.getNum4() + ")").append(" ");
				content.append(data.getNum5Sx().getText() + "(" + data.getNum5() + ")").append(" ");
				content.append(data.getNum6Sx().getText() + "(" + data.getNum6() + ")").append(",");
				content.append(data.getSpecialSx().getText()).append("\n");
			}
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=kaijiang.csv");
			return content.toString();
		}
		return null;
	}

}
