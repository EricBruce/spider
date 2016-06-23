package eric.yxs.spider.furniture.pipeline;

import eric.yxs.spider.furniture.common.model.FashionFurnitureModel;
import eric.yxs.spider.furniture.common.util.GetImage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.core.util.UuidUtil;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by young on 16/5/28.
 */
@Slf4j
public class FashionFurnitureModelPipeline implements PageModelPipeline<FashionFurnitureModel> {
    public void process(FashionFurnitureModel model, Task task) {
        String baseDir = "/data/spider/furniture/fashionFurniture/" + model.getDirName() + "/";
        File file = new File(baseDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        List<String> toCrawlUrls = model.getUris();
        for (String uri : toCrawlUrls) {
            if (StringUtils.isEmpty(uri)) {
                continue;
            }
            String largeImgUrl = processImgUrl(uri);
            String fileName = baseDir + UuidUtil.getTimeBasedUuid().toString() + ".jpg";

            byte[] imgData = GetImage.getImageFromNetByUrl(largeImgUrl);
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

    private String processImgUrl(String sUrl) {
        String ss = sUrl.substring(sUrl.lastIndexOf("-")+1);
        if (ss.startsWith("s")) {
            ss = ss.replace("s", "b");
            String ss1 = sUrl.substring(0, sUrl.lastIndexOf("-") + 1);
            sUrl = ss1 + ss;
        }
        return sUrl;
    }

//    public static void main(String[] args) {
//        String s = "https://images.furnituredealer.net/img/products%2Fflexsteel%2Fcolor%2Flatitudes-hammond_1157-62p-s0.jpg";
//        String ss = s.substring(s.lastIndexOf("-")+1);
//        if (ss.startsWith("s")) {
//            ss = ss.replace("s", "b");
//            String ss1 = s.substring(0, s.lastIndexOf("-") + 1);
//            s = ss1 + ss;
//        }
//
//        System.out.println(s);
//    }
}
