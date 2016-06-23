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
//@TargetUrl("http://www.fashionfurnitureco.com/item/latitudes-hammond-double-reclining-sofa-w-power/2072836135")
//@HelpUrl("http://www.fashionfurnitureco.com/item/latitudes-hammond-double-reclining-sofa-w-power/2072836135")
@TargetUrl("http://www.fashionfurnitureco.com/item/*")
@HelpUrl("http://www.fashionfurnitureco.com/browse/*")
public class FashionFurnitureModel {
    private String dirName = "product";
    @ExtractBy("//div[@class='ImgNavContainer']//div[@class='items']//div[@class='ImgNav_Thumb']//img/@src")
    private List<String> uris;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),
                new ConsolePageModelPipeline(), FashionFurnitureModel.class)
//                .setDownloader(new SeleniumDownloader("/soft/chrome/chromedriver"))
//                .addUrl("http://www.fashionfurnitureco.com/item/latitudes-hammond-double-reclining-sofa-w-power/2072836135")
                .addUrl("http://www.fashionfurnitureco.com/")
                .thread(1).run();
    }
}
