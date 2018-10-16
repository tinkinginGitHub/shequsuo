package cn.anyoufang.util;


import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * Project Name:anyoufang
 * File Name:FastDFSClient
 * Package Name:cn.anyoufang.utils
 * Date:2018/4/21 14:17
 * Author:
 * Description:
 */

public class FastDFSClient {


    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;
    public static FastDFSClient instance = null;


   public static FastDFSClient getInstance(String conf) throws Exception{
       if(instance == null) {
           synchronized (FastDFSClient.class) {
               if (instance == null) {
                   instance = new FastDFSClient(conf);
                   return instance;
               }

           }
       }
       return instance;
   }

    private FastDFSClient(String conf) throws Exception {
        if (conf.contains("classpath:")) {
            conf = conf
                    .replace("classpath:",
                        this.getClass().getResource("/").getPath())
                    .replaceAll("%20", " ");
        }
        ClientGlobal.init(conf);
        trackerClient = new TrackerClient();
        trackerServer = trackerClient.getConnection();
        storageServer = null;
        storageClient = new StorageClient1(trackerServer, storageServer);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileName 文件全路径
     * @param extName 文件扩展名，不包含（.）
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
        String result = storageClient.upload_file1(fileName, extName, metas);
        return result;
    }

    public String uploadFile(String fileName) throws Exception {
        return uploadFile(fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     * @param fileContent 文件的内容，字节数组
     * @param extName 文件扩展名
     * @param metas 文件扩展信息
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        String result = storageClient.upload_file1(fileContent, extName, metas);
        return result;
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }

    public int deletFile(String fileId) throws IOException, MyException {
        return  storageClient.delete_file1(fileId);
    }


}



