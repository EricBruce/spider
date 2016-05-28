package eric.yxs.spider.furniture.common.model;

import lombok.Getter;
import lombok.Setter;
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
}
