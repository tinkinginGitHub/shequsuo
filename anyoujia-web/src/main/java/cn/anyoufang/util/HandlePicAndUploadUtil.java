package cn.anyoufang.util;


import cn.anyoufang.utils.JsonUtils;
import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * 处理图片下载并上传到我们的图片服务器
 */
public class HandlePicAndUploadUtil {

    public static String uploadCapture(String result,String imgServerUrl) throws Exception {
        Map map = JsonUtils.jsonToMap(result);
        JSONObject jsonObject = (JSONObject) map.get("data");
        String picUrl = (String) jsonObject.get("picUrl");
        byte[] fileContent = downLoadFromUrl(picUrl);
        //2、创建一个FastDFS的客户端
        FastDFSClient fastDFSClient = FastDFSClient.getInstance("classpath:client.conf");
        String path = fastDFSClient.uploadFile(fileContent,"jpg");
        String url = imgServerUrl + path;
        jsonObject.put("picUrl",url);
        map.put("data",jsonObject);
        String data = JsonUtils.objectToJson(map);
        return data;
    }

    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @throws IOException
     */
    public static byte[]  downLoadFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        return getData;
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
