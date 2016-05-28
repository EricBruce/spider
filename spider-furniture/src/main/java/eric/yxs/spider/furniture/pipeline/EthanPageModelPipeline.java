package eric.yxs.spider.furniture.pipeline;

import eric.yxs.spider.furniture.common.Constant;
import eric.yxs.spider.furniture.common.model.ChannelModel;
import eric.yxs.spider.furniture.common.model.EthanModel;
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
public class EthanPageModelPipeline implements PageModelPipeline<EthanModel> {

    public void process(EthanModel ethanModel, Task task) {
        String baseDir = Constant.basicPath + "ethan/" + ethanModel.getDirName() + "/";
        File file = new File(baseDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        for (String url : ethanModel.getUrls()) {
            String fileName = baseDir + url.substring(url.lastIndexOf("/"));
            byte[] imgData = GetImage.getImageFromNetByUrl(url);
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
}
