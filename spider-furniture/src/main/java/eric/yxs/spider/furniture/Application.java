package eric.yxs.spider.furniture;

import eric.yxs.spider.furniture.common.Constant;
import eric.yxs.spider.furniture.common.model.CategoryModel;
import eric.yxs.spider.furniture.common.model.ChannelModel;
import eric.yxs.spider.furniture.common.model.EthanModel;
import eric.yxs.spider.furniture.pipeline.CategoryPageModelPipeline;
import eric.yxs.spider.furniture.pipeline.ChannelPageModelPipeline;
import eric.yxs.spider.furniture.pipeline.EthanPageModelPipeline;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

/**
 * Created by young on 16/5/28.
 */
public class Application {

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000)
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36"))
                .addPageModel(new ChannelPageModelPipeline(), ChannelModel.class)
                .addPageModel(new CategoryPageModelPipeline(), CategoryModel.class)
                .addPageModel(new EthanPageModelPipeline(), EthanModel.class)
                .addUrl(Constant.mkmjDomain)
                .addUrl(Constant.ethanAllenDomain)
                .setScheduler((new FileCacheQueueScheduler(Constant.schedulerCacheFile)))
                .thread(15)
                .run();
    }
}