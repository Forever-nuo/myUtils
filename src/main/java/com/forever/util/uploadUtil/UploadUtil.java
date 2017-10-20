package com.forever.util.uploadUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Forever丶诺 on 2017-10-19.
 */
public class UploadUtil {


    public static void upload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //1.1 解决上传文件中 中文乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        /*
         * 上传3部曲
         */
        //2.1 创建工厂
        DiskFileItemFactory dfif = new DiskFileItemFactory();

        //创建解析器
        ServletFileUpload upload = new ServletFileUpload(dfif);

        //解析请求
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }


    }
}
