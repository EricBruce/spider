package eric.yxs.spider.furniture;

import eric.yxs.spider.furniture.common.Constant;
import eric.yxs.spider.furniture.common.model.CategoryModel;
import eric.yxs.spider.furniture.common.model.ChannelModel;
import eric.yxs.spider.furniture.common.model.EthanModel;
import eric.yxs.spider.furniture.pipeline.CategoryPageModelPipeline;
import eric.yxs.spider.furniture.pipeline.ChannelPageModelPipeline;
import eric.yxs.spider.furniture.pipeline.EthanPageModelPipeline;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.scheduler.RedisScheduler;
import us.codecraft.webmagic.scheduler.component.BloomFilterDuplicateRemover;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by young on 16/5/28.
 */
@Slf4j
public class Application {

    public static void main(String[] args) {
        Spider spider = OOSpider.create(Site.me().setSleepTime(1000)
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36"))
                .addPageModel(new ChannelPageModelPipeline(), ChannelModel.class)
                .addPageModel(new CategoryPageModelPipeline(), CategoryModel.class)
                .addPageModel(new EthanPageModelPipeline(), EthanModel.class)
                .addUrl(Constant.mkmjDomain)
                .addUrl(Constant.ethanAllenDomain)
                .setScheduler(new FileCacheQueueScheduler(Constant.schedulerCacheFile)
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)) )
                .thread(15);
        try {
            SpiderMonitor.instance().register(spider);
            spider.start();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pr = new PrintWriter(sw);
            e.printStackTrace(pr);
            log.warn(sw.toString());
        }

    }
}