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
@Setter @Getter
@TargetUrl("http://www.markorhome.com/catalogue/*")
@HelpUrl("http://www.markorhome.com/search/*")
public class CategoryModel {
//    @ExtractByUrl("http://www.markorhome.com/search/(\\w+-\\w+)/*")
    private String dirName="search";
    @ExtractBy("//h1[@class='main-title']/text()")
    private String fileName;
    @ExtractBy("//ul[@class='thumbnails']/li/a/@href")
    private List<String> uris;

}
