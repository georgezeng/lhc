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
import lhc.repository.jpa.dao.KaiJiangDao;
import lhc.service.ParseService;

@RestController
@RequestMapping("/mvc/kj")
public class KaiJiangController {
	@Autowired
	private ParseService parseService;

	@Autowired
	private KaiJiangDao kaiJiangDao;

	@RequestMapping("/sync/{year}")
	public BaseResult sync(@PathVariable int year) {
		parseService.syncKaiJiang(year);
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
				content.append(data.getNum1Sx().getText()).append(",");
				content.append(data.getSpecialSx().getText()).append("\n");
			}
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=kaijiang.csv");
			return content.toString();
		}
		return null;
	}

}
