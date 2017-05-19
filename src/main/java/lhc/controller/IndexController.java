package lhc.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@Value("${system.title}")
	private String systemTitle;

	@RequestMapping("**/*.html")
	public String index(HttpServletRequest request, Model model) {
		model.addAttribute("title", systemTitle);
		model.addAttribute("date", DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA).format(new Date()));
		model.addAttribute("view", request.getRequestURI().replace(".html", ""));
		return "main";
	}

	@RequestMapping("/")
	public String index() {
		return "redirect:/kj/list.html";
	}
}
