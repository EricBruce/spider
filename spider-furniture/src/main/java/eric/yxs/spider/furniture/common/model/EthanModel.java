package eric.yxs.spider.furniture.common.model;

import lombok.Getter;
import lombok.Setter;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/**
 * Created by young on 16/5/28.
 */
@Setter
@Getter
@TargetUrl("http://www.ethanallen.com/en_US/shop[\\w+|-]*/*")
@HelpUrl("http://www.ethanallen.com/en_US/shop[\\w+|-]*")
public class EthanModel {
    @ExtractBy("//ol[@class='breadcrumb']/li[3]/a/text()")
    private String dirName;
    @ExtractBy("//div[@id='thumbnails']//a/@href")
    private List<String> urls;

}
