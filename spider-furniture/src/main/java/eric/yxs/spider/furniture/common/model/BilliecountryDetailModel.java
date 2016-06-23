package eric.yxs.spider.furniture.common.model;

import lombok.Getter;
import lombok.Setter;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

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
public class BilliecountryDetailModel {
    private String dirName = "product";
    @ExtractBy("//div[@class='imgbox']//li//img/@src")
    private List<String> uris;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),
                new ConsolePageModelPipeline(), BilliecountryDetailModel.class)
                .setDownloader(new SeleniumDownloader("/soft/chrome/chromedriver"))
                .addUrl("http://www.billiecountry.com/products_list/&pmcId=28.html")
                .thread(1).run();
//                .addUrl("http://www.billiecountry.com/index.html").thread(1).run();

    }
}
