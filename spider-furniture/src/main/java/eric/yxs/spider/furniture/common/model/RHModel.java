package eric.yxs.spider.furniture.common.model;

import eric.yxs.spider.furniture.pipeline.RHModelPipeline;
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
//@TargetUrl("https://www.restorationhardware.com/catalog/product/product.jsp?*")
//@HelpUrl("https://www.restorationhardware.com/catalog/product/product.jsp?productId=prod3240313&categoryId=cat1680014")
@TargetUrl("https://www.restorationhardware.com/catalog/product/product.jsp?*")
@HelpUrl("https://www.restorationhardware.com/catalog/category/products.jsp?*")
public class RHModel {
    private String dirName = "product";
    @ExtractBy("//div[@class='product-alt-images']//li//img/@src")
    private List<String> uris;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),
                new RHModelPipeline(), RHModel.class)
//                .setDownloader(new SeleniumDownloader("/soft/chrome/chromedriver"))
                .addUrl("https://www.restorationhardware.com")
                .setScheduler(
                        new FileCacheQueueScheduler("/data/spider/cache/")
                )
//                .addUrl("https://www.restorationhardware.com/catalog/product/product.jsp?productId=prod3240313&categoryId=cat1680014")
                .thread(2).run();

    }
}
