package eric.yxs.spider.furniture.common.model;

import eric.yxs.spider.furniture.common.Constant;
import eric.yxs.spider.furniture.pipeline.BilliecountryDetailPageModelPipeline;
import eric.yxs.spider.furniture.pipeline.CategoryPageModelPipeline;
import eric.yxs.spider.furniture.pipeline.ChannelPageModelPipeline;
import eric.yxs.spider.furniture.pipeline.EthanPageModelPipeline;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.RedisScheduler;
import us.codecraft.webmagic.scheduler.component.BloomFilterDuplicateRemover;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by young on 16/5/28.
 */
@Setter
@Getter
//@TargetUrl("http://www.billiecountry.com/products_detail/&productId=1177.html")
//@HelpUrl("http://www.billiecountry.com/products_detail/&productId=1177.html")
@TargetUrl("http://www.billiecountry.com/products_detail/*")
@HelpUrl("http://www.billiecountry.com/products_list/*")
@Slf4j
public class BilliecountryDetailModel {
    private String dirName = "product";
    @ExtractBy("//div[@class='imgbox']//li//img/@src")
    private List<String> uris;

    public static void main(String[] args) {
//        OOSpider.create(Site.me().setSleepTime(1000),
//                new BilliecountryDetailPageModelPipeline(), BilliecountryDetailModel.class)
//                .setDownloader(new SeleniumDownloader("/soft/chrome/chromedriver"))
//                .addUrl("http://www.billiecountry.com/products_list/&pmcId=28.html")
//                .thread(1).run();
//                .addUrl("http://www.billiecountry.com/index.html").thread(1).run();
        Spider spider = OOSpider.create(Site.me().setSleepTime(1000)
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36"))
                .addPageModel(new BilliecountryDetailPageModelPipeline(), BilliecountryDetailModel.class)
                .addUrl("http://www.billiecountry.com/products_list/&pmcId=28.html")
                .setScheduler(
//                        new RedisScheduler("")
                        new FileCacheQueueScheduler("/data/spider/cache/")
                )
                .thread(1);
        spider = spider.setDownloader(new SeleniumDownloader("/soft/chrome/chromedriver"));
        try {
            SpiderMonitor.instance().register(spider);
            spider.start();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pr = new PrintWriter(sw);
            e.printStackTrace(pr);
            log.warn(sw.toString());
        }
    }
}
