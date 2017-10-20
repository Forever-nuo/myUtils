package com.forever.util.downloadUtil;


import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Author: Forever丶诺
 * @Description: web项目的下载
 * @Date: 22:39 2017-10-18
 */
public class DownloadUtil {


    /**
     * @param request
     * @param response
     * @param downloadPath
     * @param downloadFileName
     * @throws IOException
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, String downloadPath, String downloadFileName) throws IOException {

        ServletContext servletContext = request.getServletContext();

        //读取要下载的文件
        String filePath = servletContext.getRealPath(downloadPath);
        FileInputStream fis = new FileInputStream(filePath);

        //下载名称 设置编码
        downloadFileName = fileNameEncoding(downloadFileName, request);

        //设置两个响应体
        response.setHeader("Content-Type", servletContext.getMimeType(filePath));
        response.setHeader("Content-Disposition", "attachment;filename=" + downloadFileName);

        //将读取流的数据 转到输出流中
        IOUtils.copy(fis, response.getOutputStream());
        fis.close();
    }


    // 用来对下载的文件名称进行编码的！
    public static String fileNameEncoding(String filename, HttpServletRequest request) throws IOException {
        String agent = request.getHeader("User-Agent"); //获取浏览器
        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?"
                    + base64Encoder.encode(filename.getBytes("utf-8"))
                    + "?=";
        } else if (agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

}
