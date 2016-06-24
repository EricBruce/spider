package eric.yxs.spider.furniture.common.model;

import eric.yxs.spider.furniture.pipeline.ChannelPageModelPipeline;
import lombok.Getter;
import lombok.Setter;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by young on 16/5/28.
 */
@Setter
@Getter
@TargetUrl("http://www.markorhome.com/channel/items/\\w+")
@HelpUrl("http://www.markorhome.com/channel/\\w+")
public class ChannelModel {
    @ExtractByUrl("http://www.markorhome.com/channel/(\\w+)/")
    private String dirName;
    @ExtractByUrl("http://www.markorhome.com/channel/items/(\\w+)")
    private String fileName;
    @ExtractBy("//div[@class='main-banner']/img/@src")
    private String uri;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),
                new ChannelPageModelPipeline(), ChannelModel.class)
//                .setDownloader(new SeleniumDownloader("/Users/yihua/Downloads/chromedriver"))
                .addUrl("http://www.markorhome.com/")
                .thread(5).run();

    }
}
