package lhc.controller;

import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
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
	private YZController yZController;

	@RequestMapping("/save")
	public BaseResult save(@RequestBody KaiJiang data) throws Exception {
		KaiJiang oldData = kaiJiangRepository.findByDate(data.getDate());
		if (oldData != null) {
			BeanUtils.copyProperties(data, oldData, "id");
		} else {
			oldData = data;
		}
		kaiJiangRepository.save(oldData);
		return new BaseResult();
	}

	@RequestMapping("/sync/{year}")
	public BaseResult sync(@PathVariable int year) throws Exception {
		return sync(year, true);
	}

	public BaseResult sync(int year, boolean sleep) throws Exception {
		delete(year);
		parseService.syncKaiJiang(year);
		yZController.calYZ(sleep);
		return BaseResult.EMPTY;
	}

	@RequestMapping("/delete/{year}")
	public BaseResult delete(@PathVariable int year) {
		kaiJiangRepository.deleteByYear(year);
		return BaseResult.EMPTY;
	}

	@RequestMapping("/list")
	public BaseResult list(@RequestBody QueryInfo<String> queryInfo) {
		return new BaseResult(kaiJiangDao.query(queryInfo));
	}

	@RequestMapping("/download")
	public String download(String searchKey, HttpServletResponse response) throws Exception {
		response.setContentType("text/csv;charset=gbk;");
		response.addHeader("Content-Disposition", "attachment;filename=kaijiang.csv");
		QueryInfo<String> queryInfo = new QueryInfo<String>();
		queryInfo.setObject(searchKey);
		PageResult<KaiJiang> result = kaiJiangDao.query(queryInfo);
		Collections.sort(result.getList(), new Comparator<KaiJiang>() {

			@Override
			public int compare(KaiJiang o1, KaiJiang o2) {
				Integer a = o1.getYear();
				Integer b = o2.getYear();
				if (a.equals(b)) {
					a = o1.getPhase();
					b = o2.getPhase();
				}
				return a.compareTo(b);
			}

		});
		if (result != null && result.getList() != null && !result.getList().isEmpty()) {
			Writer writer = response.getWriter();
			writer.append("日期, 年份, 期数, 平码(生肖), 平码(号码), 特码(生肖), 特码(号码)").append("\n");
			for (KaiJiang data : result.getList()) {
				writer.append(data.getDate()).append(",");
				writer.append(data.getYear() + "").append(",");
				writer.append(data.getPhase() + "").append(",");
				writer.append(data.getNum1Sx().getText()).append(" ");
				writer.append(data.getNum2Sx().getText()).append(" ");
				writer.append(data.getNum3Sx().getText()).append(" ");
				writer.append(data.getNum4Sx().getText()).append(" ");
				writer.append(data.getNum5Sx().getText()).append(" ");
				writer.append(data.getNum6Sx().getText()).append(", ");
				writer.append(data.getNum1() + "").append(" ");
				writer.append(data.getNum2() + "").append(" ");
				writer.append(data.getNum3() + "").append(" ");
				writer.append(data.getNum4() + "").append(" ");
				writer.append(data.getNum5() + "").append(" ");
				writer.append(data.getNum6() + "").append(", ");
				writer.append(data.getSpecialSx().getText()).append(", ");
				writer.append(data.getSpecialNum() + "").append("\n");
			}
		}
		return null;
	}

}
