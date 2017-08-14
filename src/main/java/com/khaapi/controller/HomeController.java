package com.khaapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.khaapi.model.BuzzFeed;
import com.khaapi.model.person;
import com.khaapi.repo.ArticleRepo;
import com.khaapi.repo.BuzzFeedRepository;
import com.khaapi.repo.Peoplerepo;


@Controller
@EnableScheduling
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private BuzzFeedRepository buzzFeedRepository;
	@Autowired
	private Peoplerepo peoprepo;
	@Autowired
	private ArticleRepo articleRepo;
//	@Autowired
//	private person pr;
	@Value("${api.url.buzzfeed}")
	private String buzzFeedUrl;
	


	@RequestMapping(value = "/news-pull", method = RequestMethod.GET)@ResponseBody
	public BuzzFeed getNewsFromBuzzFeed() throws Exception {
		RestTemplate buzzFeedTemplate = new RestTemplate();
		BuzzFeed buzzFeed = buzzFeedTemplate.getForObject(buzzFeedUrl, BuzzFeed.class);
		buzzFeedRepository.save(buzzFeed);
		return buzzFeed;
	}

	@Scheduled(cron = "0 0 0/2 * * ?")
	public void pullNewsFeedFromBuzzFeed() throws Exception {
		BuzzFeed buzzFeed = getNewsFromBuzzFeed();
		logger.info("Completed fetching news from Buzz Feed at :" + new java.util.Date());
	}

	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String getBuzzFeeds(Model model) {
		 
		 model.addAttribute("Articles", articleRepo.findAll());
	return "news";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		 
		 model.addAttribute("message", "Welcome");
	return "home";
	}
	
	 @GetMapping("/enq")
	    public String perForm(Model model) {
		 model.addAttribute("pr", new person());
	         return "enq";
	    }

	    @PostMapping("/enq")
	    public String perSubmit(@ModelAttribute person pr) {
	    	peoprepo.save(pr);
	        return "enqresult";
	    }
	
	

}
