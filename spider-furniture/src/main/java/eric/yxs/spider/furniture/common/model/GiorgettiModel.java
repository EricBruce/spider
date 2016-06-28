package eric.yxs.spider.furniture.common.model;

import eric.yxs.spider.furniture.pipeline.GiorgettiPageModelPipeline;
import lombok.Getter;
import lombok.Setter;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by young on 16/5/28.
 */
@Setter
@Getter
//@TargetUrl("http://www.giorgetti.eu/prodotto.php*")
//@HelpUrl("http://www.giorgetti.eu/prodotto.php*")
@TargetUrl("http://www.giorgetti.eu/prodotto.php*")
@HelpUrl("http://www.giorgetti.eu/prodotti.php")
public class GiorgettiModel {
    private String dirName = "product";
    @ExtractBy("//div[@class='container']//div[@class='flexslider']//li//img/@src")
    private List<String> uris;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),
                new GiorgettiPageModelPipeline(), GiorgettiModel.class)
//                .setDownloader(new SeleniumDownloader("/soft/chrome/chromedriver"))
                .addUrl("http://www.giorgetti.eu")
                .setScheduler(
                        new FileCacheQueueScheduler("/data/spider/cache/")
                )
//                .addUrl("http://www.giorgetti.eu/prodotti.php")
                .thread(2).run();

    }
}
