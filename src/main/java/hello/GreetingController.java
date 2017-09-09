package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/crawl")
    public String crawling(@RequestParam(value="site", required=false, defaultValue="https://www.google.co.in/") String site, Model model) {
    	try {
			System.out.println("at crawling method....");
			String crawlStorageFolder = "/home/cshareef/crawler/Crawler1/src/main/resources/data";
			int numberOfCrawlers = 1;
			CrawlConfig config = new CrawlConfig();
			config.setCrawlStorageFolder(crawlStorageFolder);
			config.setMaxDepthOfCrawling(0);
			config.setMaxPagesToFetch(10);
			config.setPolitenessDelay(500);
			PageFetcher pageFetcher = new PageFetcher(config);
			RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
			RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
			CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
//			controller.addSeed("http://www.ics.uci.edu/~lopes/");
//			controller.addSeed("http://www.ics.uci.edu/~welling/");
//			controller.addSeed("http://www.ics.uci.edu/");
			controller.addSeed("http://economictimes.indiatimes.com/bharat-electronics-ltd/stocks/companyid-11945.cms");
			controller.addSeed("http://economictimes.indiatimes.com/oil-and-natural-gas-corporation-ltd/stocks/companyid-11599.cms");
			controller.start(MyCrawler.class, numberOfCrawlers);
    	} catch (Exception e) {
			System.out.println("Exception:::::");
			e.printStackTrace();
		}
		return "greeting";
    }
    
    
}