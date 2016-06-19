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
@TargetUrl("http://www.fnji.com/furniture-detail*")
@HelpUrl("http://www.fnji.com/furniture-detail?id=2735")
//@TargetUrl("http://www.fnji.com/[\\w+]*-detail*")
//@HelpUrl("http://www.fnji.com/\\w+")
public class FnjiDetailModel {
    @ExtractBy("//div[@class='col-s-1 small-img product-left']/@class")
    private String dirName;
//    @ExtractBy("//div[@class='category']//div[@class='focusItem']/@style/background-image")
    private List<String> urls;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),
                new ConsolePageModelPipeline(), FnjiDetailModel.class)
                .setDownloader(new SeleniumDownloader("/soft/chrome/chromedriver"))
                .addUrl("http://www.fnji.com/furniture-detail?id=2735").thread(1).run();

    }
}
