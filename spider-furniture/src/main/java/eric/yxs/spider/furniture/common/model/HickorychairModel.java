package eric.yxs.spider.furniture.common.model;

import eric.yxs.spider.furniture.pipeline.HickoryModelPipeline;
import lombok.Getter;
import lombok.Setter;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.util.List;

/**
 * Created by young on 16/5/28.
 */
@Setter
@Getter
//@TargetUrl("http://www.hickorychair.com/Furniture/Dining-Room-and-Kitchen-Furniture/Suzanne-Kasler/i509945-Arden-Dining-Table-Top-and-Base.aspx")
//@HelpUrl("http://www.hickorychair.com/Furniture/Dining-Room-and-Kitchen-Furniture/Suzanne-Kasler/i509945-Arden-Dining-Table-Top-and-Base.aspx")
@TargetUrl("http://www.hickorychair.com/Furniture/*/*/*.aspx")
@HelpUrl("http://www.hickorychair.com/Furniture/*/*.aspx")
public class HickorychairModel {
    private String dirName = "product";
    @ExtractBy("//ul[@class='list-images']//li//img/@src")
    private List<String> uris;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),
                new HickoryModelPipeline(), HickorychairModel.class)
//                .setDownloader(new SeleniumDownloader("/soft/chrome/chromedriver"))
                .addUrl("http://www.hickorychair.com")
                .setScheduler(
                        new FileCacheQueueScheduler("/data/spider/cache/")
                )
//                .addUrl("http://www.hickorychair.com/Furniture/Dining-Room-and-Kitchen-Furniture/Suzanne-Kasler/i509945-Arden-Dining-Table-Top-and-Base.aspx")
                .thread(1).run();

    }
}
