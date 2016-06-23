package eric.yxs.spider.furniture.common.model;

import eric.yxs.spider.furniture.pipeline.FnjiDetailPageModelPipeline;
import lombok.Getter;
import lombok.Setter;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
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
//@TargetUrl("http://www.fnji.com/furniture-detail*")
//@HelpUrl("http://www.fnji.com/furniture-detail?id=2735")
@TargetUrl("http://www.fnji.com/[\\w+]*-detail*")
@HelpUrl("http://www.fnji.com/.+")
public class FnjiDetailModel {
    @ExtractBy("//div[@class='category']//div[@class='text']/@class/text()")
    private String dirName;
    // TODO read attr left img-src
    @ExtractBy("//div[@class='product-left']//li/@img-src")
    private List<String> leftUrl;
    @ExtractBy("//div[@class='gallary-img']//li/@img-src")
    private List<String> footUrl;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),
                new FnjiDetailPageModelPipeline(), FnjiDetailModel.class)
                .setDownloader(new SeleniumDownloader("/soft/chrome/chromedriver"))
                .addUrl("http://www.fnji.com/furniture").thread(1).run();

    }
}
