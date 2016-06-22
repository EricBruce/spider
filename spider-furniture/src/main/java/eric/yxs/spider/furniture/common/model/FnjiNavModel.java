package eric.yxs.spider.furniture.common.model;

import lombok.Getter;
import lombok.Setter;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/**
 * Created by young on 16/5/28.
 */
@Setter
@Getter
@HelpUrl("http://www.fnji.com/furniture")
@TargetUrl("http://www.fnji.com/furniture")
public class FnjiNavModel {
    @ExtractByUrl("http://www.fnji.com/(\\w+)")
    private String dirName;
    @ExtractBy("//div[@class='focusArea']/@class")
    private String urls;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),
                new ConsolePageModelPipeline(), FnjiNavModel.class)
//                .setDownloader(new SeleniumDownloader("/Users/yihua/Downloads/chromedriver"))
                .addUrl("http://www.fnji.com/furniture").thread(5).run();

    }
}
