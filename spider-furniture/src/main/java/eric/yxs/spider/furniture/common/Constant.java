package eric.yxs.spider.furniture.common;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

/**
 * Created by young on 16/5/28.
 */
@Slf4j
public class Constant {


    private static Properties properties = new Properties();

    static {

        File file = new File("config.properties");
        if (!file.exists()) {
            log.warn("配置文件config.properties不存在");
            System.exit(404);
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
            fis.close();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pr = new PrintWriter(sw);
            e.printStackTrace(pr);
            log.warn(sw.toString());
        }
    }

    public final static String urlPrefix_mkmj = "http://www.markorhome.com";
    public final static String urlPrefix_fnji = "http://www.fnji.com/temp/upload/";
    public final static String basicPath = "" + properties.get("spider.data.base.path");
    public final static String schedulerCacheFile = "" + properties.get("scheduler.cache.file.path");

    /* 美克美家 */
    public final static String mkmjDomain = "" + properties.get("spider.domain.mkmj");
    /* ethanAllen */
    public final static String ethanAllenDomain = "" + properties.get("spider.domain.ethanAllen");
    public final static String domain_fnji = "" + properties.get("spider.domain.fnji");

}
