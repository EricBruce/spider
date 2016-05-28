package eric.yxs.spider.furniture.pipeline;

import eric.yxs.spider.furniture.common.Constant;
import eric.yxs.spider.furniture.common.model.ChannelModel;
import eric.yxs.spider.furniture.common.util.GetImage;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by young on 16/5/28.
 */
@Slf4j
public class ChannelPageModelPipeline implements PageModelPipeline<ChannelModel> {

    public void process(ChannelModel channelModel, Task task) {
        String imgUrl = Constant.urlPrefix + channelModel.getUri();
        String fName = imgUrl.substring(imgUrl.lastIndexOf("/"));
        String dir = Constant.basicPath + "/mkmj/" + channelModel.getDirName();
        File file = new File(dir);
        if (file.exists()) {
            file.mkdirs();
        }
        String fileName = dir + fName;
        byte[] imgData = GetImage.getImageFromNetByUrl(imgUrl);
        try {
            GetImage.writeImageToDisk(imgData, fileName);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pr = new PrintWriter(sw);
            e.printStackTrace(pr);
            log.warn(sw.toString());
        }
    }
}
