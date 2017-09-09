package hello;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import hello.dao.PersonDao;
import hello.dao.StockDao;
import hello.model.Person;
import hello.model.Stock;

public class MyCrawler extends WebCrawler{
//	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
//            + "|png|mp3|mp3|zip|gz))$");
	
	private static int pageNo=1;
	@Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
//		System.out.println("url:::::"+url);
//		System.out.println("referringPage:::::"+referringPage);
//        String href = url.getURL().toLowerCase();
//        System.out.println("href:::"+href);
//        return !FILTERS.matcher(href).matches()
//               && href.startsWith("http://www.zyme.com/");
		return true;
    }
	
	@Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL===================" + url);
        
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            SavetoFile(text, html);
            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());
        }
   }

	private void SavetoFile(String text, String html) {
		try {
			File txtfile = new File("/home/cshareef/crawler/Crawler1/testFile"+pageNo+".txt");
			File htmlfile = new File("/home/cshareef/crawler/Crawler1/testFile"+pageNo+".html");

			// Create the file
			if (txtfile.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}
			
			// Create the file
			if (htmlfile.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}

			// Write Content
			FileWriter writer = new FileWriter(txtfile);
			writer.write(text);
			writer.close();

			// Write Content
			FileWriter writer1 = new FileWriter(htmlfile);
			writer1.write(html);
			writer1.close();
			int index1 = html.indexOf("nseTradeprice\">")+15;
			int index2 = html.indexOf("</div>", index1);
			String nsePrice = html.substring(index1, index2);
			index1 = html.indexOf("bseTradeprice\">")+15;
			index2 = html.indexOf("</div>", index1);
			String bsePrice = html.substring(index1, index2);
			System.out.println("=========================");
			System.out.println("nsePrice::::"+nsePrice);
			System.out.println("bsePrice::::"+bsePrice);
			System.out.println("=========================");
			pageNo++;
			
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
			
			StockDao stockDAO = context.getBean(StockDao.class);
			
			Stock stock = new Stock();
			stock.setName("stk1");
			stock.setBseprice(bsePrice);
			stock.setNseprice(nsePrice);
			
			stockDAO.save(stock);
			
			System.out.println("stock::"+stock);
			
			List<Stock> list = stockDAO.list();
			
			for(Stock s : list){
				System.out.println("stock List::"+s);
			}
			//close resources
			context.close();	
			
		} catch (Exception e) {

		}
	}
	
}
