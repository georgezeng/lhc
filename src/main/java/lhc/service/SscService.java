package lhc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lhc.domain.SscKaiJiang;
import lhc.repository.jpa.api.SscKaiJiangRepository;

@Component
public class SscService {
	@Autowired
	private RestTemplate client;

	@Autowired
	private SscKaiJiangRepository repository;

	private String url = "https://fx.cp2y.com/xcqssc/data/issue_target!.jsp?ajax=true";

	@SuppressWarnings("unchecked")
	public void pullData() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		Integer page = 1;
		boolean hasNext = false;
		do {
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("lid", "10089");
			map.add("target", "10002");
			map.add("show", "300");
			map.add("week", "0");
			map.add("page", page.toString());
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
			String responseBody = client.postForObject(url, request, String.class);
			responseBody = responseBody.replace("(", "").replace(")", "").replaceAll("\\{([^:]+):", "{\"$1\":").replaceAll(",([^\\{^,^:]+):",
					",\"$1\":");
			Map<String, Object> result = new ObjectMapper().readValue(responseBody, new TypeReference<Map<String, Object>>() {
			});
			List<Map<String, Object>> datas = (List<Map<String, Object>>) result.get("data");
			for (int i = 0; i < datas.size(); i++) {
				Map<String, Object> data = datas.get(i);
				String phase = (String) data.get("iss");
				SscKaiJiang kj = repository.findByPhase(phase);
				if (kj == null) {
					kj = new SscKaiJiang();
					kj.setDate("20" + (String) data.get("it"));
					kj.setYear(Integer.valueOf(kj.getDate().replaceAll("-\\d+", "")));
					kj.setNums(((String) data.get("n")).replaceAll(" ", ","));
					kj.setPhase(phase);
					repository.save(kj);
				}
			}
			hasNext = page.compareTo((Integer) result.get("totalPage")) < 0;
			page++;
		} while (hasNext);
	}

}
