package lhc.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lhc.domain.KaiJiang;
import lhc.enums.SX;
import lhc.repository.jpa.api.KaiJiangRepository;

@Service
public class ParseService {

	@Autowired
	private KaiJiangRepository kaiJiangRepository;

	public void syncKaiJiang(int year) {
		try {
			String url = "http://138lh.com/kj/" + year + ".html";
			Document doc = Jsoup.connect(url).get();
			List<Element> trs = doc.getElementById("main").getElementsByTag("tr");
			List<KaiJiang> list = new ArrayList<KaiJiang>();
			for (int i = 1; i < trs.size(); i++) {
				List<Element> tds = trs.get(i).getElementsByTag("td");
				Element dateEl = tds.get(0);
				TextNode phaseEl = tds.get(1).textNodes().get(0);
				Element numsEl = tds.get(2).getElementsByClass("sortDown").first();
				List<Element> numEls = numsEl.getElementsByClass("hm");
				List<Element> sxEls = numsEl.getElementsByClass("sx");
				Element specialSxEl = tds.get(3).getElementsByClass("sx").first();
				Element specialNumEl = tds.get(3).getElementsByClass("hm").first();
				String date = dateEl.text().trim();
				KaiJiang data = kaiJiangRepository.findByDate(date);
				if (data == null) {
					data = new KaiJiang();
					data.setDate(date);
				}
				data.setYear(year);
				data.setPhase(Integer.valueOf(phaseEl.text().trim().replace("期", "")));
				data.setSpecialSx(SX.textOf(specialSxEl.text().trim()));
				data.setSpecialNum(Integer.valueOf(specialNumEl.text().trim()));
				for (int j = 0; j < 6; j++) {
					Method setNum = data.getClass().getDeclaredMethod("setNum" + (j + 1), int.class);
					Method setSx = data.getClass().getDeclaredMethod("setNum" + (j + 1) + "Sx", SX.class);
					setNum.invoke(data, Integer.valueOf(numEls.get(j).text()));
					setSx.invoke(data, SX.textOf(sxEls.get(j).text()));
				}
				list.add(data);
			}
			kaiJiangRepository.save(list);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
