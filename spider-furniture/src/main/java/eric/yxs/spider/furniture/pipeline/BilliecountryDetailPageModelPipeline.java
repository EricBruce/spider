package eric.yxs.spider.furniture.pipeline;

import eric.yxs.spider.furniture.common.model.BilliecountryDetailModel;
import eric.yxs.spider.furniture.common.model.FnjiDetailModel;
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
public class BilliecountryDetailPageModelPipeline implements PageModelPipeline<BilliecountryDetailModel> {
    public void process(BilliecountryDetailModel detailModel, Task task) {
        String baseDir = "/data/spider/furniture/billieCountry/" + detailModel.getDirName() + "/";
        File file = new File(baseDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        List<String> toCrawlUrls = detailModel.getUris();
        for (String uri : toCrawlUrls) {
            if (StringUtils.isEmpty(uri)) {
                continue;
            }
            StringBuffer imgUrl = new StringBuffer("http://www.billiecountry.com")
                    .append(uri);
            String fileName = baseDir + UuidUtil.getTimeBasedUuid().toString() + ".jpg";

            byte[] imgData = GetImage.getImageFromNetByUrl(imgUrl.toString());
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
