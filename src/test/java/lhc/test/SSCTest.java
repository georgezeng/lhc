package lhc.test;

import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SSCTest {
	@Test
	public void test() throws Exception {
		RestTemplate client = new RestTemplate();
		String url = "https://fx.cp2y.com/xcqssc/data/issue_target!.jsp?ajax=true";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("lid", "10089");
		map.add("target", "10002");
		map.add("show", "10");
		map.add("page", "1");
		map.add("week", "0");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		String responseBody = client.postForObject(url, request, String.class);
		responseBody = responseBody.replace("(", "").replace(")", "").replaceAll("\\{([^:]+):", "{\"$1\":").replaceAll(",([^\\{^,^:]+):", ",\"$1\":");
		
		Map<String, Object> result = new ObjectMapper().readValue(responseBody, new TypeReference<Map<String, Object>>(){});
		System.out.println(responseBody);
	}
}
