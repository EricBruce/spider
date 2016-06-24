package eric.yxs.spider.furniture.common.model;

import lombok.Getter;
import lombok.Setter;
import us.codecraft.webmagic.Site;
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
//@TargetUrl("http://www.caracole.com/gallerydetails?*")
//@HelpUrl("http://www.caracole.com/gallerydetails?id=UPH-CHAWOO-54B&ref=gallery&ViewAll=True&type=cat&zid=LIVING%20-%20CHAIRS")
@TargetUrl("http://www.caracole.com/gallerydetails?*")
@HelpUrl("http://www.caracole.com/gallery?*")
public class CarracoleModel {
    private String dirName = "product";
    @ExtractBy("//div[@id='ContentPlaceHolder1_ctl01_ctl00_pnlAlt']//td/img/@src")
    private List<String> uris;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),
                new ConsolePageModelPipeline(), CarracoleModel.class)
//                .setDownloader(new SeleniumDownloader("/soft/chrome/chromedriver"))
                .addUrl("http://www.caracole.com/home")
//                .addUrl("http://www.caracole.com/gallerydetails?id=UPH-CHAWOO-54B&ref=gallery&ViewAll=True&type=cat&zid=LIVING%20-%20CHAIRS")
                .thread(1).run();

    }
}
