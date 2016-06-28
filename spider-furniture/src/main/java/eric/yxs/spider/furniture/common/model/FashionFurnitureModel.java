package eric.yxs.spider.furniture.common.model;

import eric.yxs.spider.furniture.pipeline.BilliecountryDetailPageModelPipeline;
import eric.yxs.spider.furniture.pipeline.FashionFurnitureModelPipeline;
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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by young on 16/5/28.
 */
@Setter
@Getter
//@TargetUrl("http://www.fashionfurnitureco.com/item/latitudes-hammond-double-reclining-sofa-w-power/2072836135")
//@HelpUrl("http://www.fashionfurnitureco.com/item/latitudes-hammond-double-reclining-sofa-w-power/2072836135")
@TargetUrl("http://www.fashionfurnitureco.com/item/*")
@HelpUrl("http://www.fashionfurnitureco.com/browse/*")
@Slf4j
public class FashionFurnitureModel {
    private String dirName = "product";
    @ExtractBy("//div[@class='ImgNavContainer']//div[@class='items']//div[@class='ImgNav_Thumb']//img/@src")
    private List<String> uris;

    public static void main(String[] args) {
        Spider spider = OOSpider.create(Site.me().setSleepTime(1000)
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36"))
                .addPageModel(new FashionFurnitureModelPipeline(), FashionFurnitureModel.class)
                .addUrl("http://www.fashionfurnitureco.com/")
                .setScheduler(
                        new FileCacheQueueScheduler("/data/spider/cache/")
                )
                .thread(5);
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
