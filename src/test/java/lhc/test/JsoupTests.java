package lhc.test;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.junit.Test;

public class JsoupTests {
	@Test
	public void test() throws Exception {
		Document doc = Jsoup.connect("http://138lh.com/kj/2017.html").get();
		List<Element> trs = doc.getElementById("main").getElementsByTag("tr");
		for(int i = 1; i < trs.size(); i++) {
			List<Element> tds = trs.get(i).getElementsByTag("td");
			Element dateEl = tds.get(0);
			TextNode countEl = tds.get(1).textNodes().get(0);
			Element sxEl = tds.get(3).getElementsByClass("sx").get(0);
			System.out.println(dateEl.text().trim()+", " + countEl.text().trim() + ", " + sxEl.text().trim());
		}
	}
}
