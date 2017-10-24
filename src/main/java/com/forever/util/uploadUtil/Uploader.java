package com.forever.util.uploadUtil;

import com.forever.util.CommonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Forever丶诺 on 2017-10-19.
 */
public class Uploader {

    private HttpServletRequest request;

    //用来封装 非文件的请求参数
    private Map fieldMap = new HashMap();

    private String saveDir; //保存目录

    private String saveFileDir; //保存的文件目录

    private List<UploadFile> fileList = new ArrayList<UploadFile>(); //上传文件的list

    /**
     * @param request:
     * @Description: 构造函数必须传HttpServletRequest对象
     * @Author: Forever丶诺
     * @Date: 23:12 2017-10-22
     */
    public Uploader(HttpServletRequest request, String saveDir) {
        this.saveDir = saveDir;
        this.request = request;
    }

    public void upload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (ServletFileUpload.isMultipartContent(request)) {
            //1.1 解决上传文件中 中文乱码问题
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");


            //2.1 创建工厂
            DiskFileItemFactory itemFactory = new DiskFileItemFactory();
            //2.2创建解析器
            ServletFileUpload upload = new ServletFileUpload(itemFactory);


            try {
                // 解析参数的编码
                final String FIELD_VALUE_ENCODE = "utf-8";


                //2.3解析请求 获得请求多部件对象
                List<FileItem> fileItemList = upload.parseRequest(request);


                //获得保存路径的绝对路径
                String realSaveDir = request.getServletContext().getRealPath(saveDir);
                File dateFileDir = getDateFileDir(realSaveDir);


                for (FileItem fileItem : fileItemList) {
                    //判断是普通项 还是文件项
                    if (fileItem.isFormField()) {
                        fieldMap.put(fileItem.getFieldName(), fileItem.getString(FIELD_VALUE_ENCODE));
                    } else {

                        String originName = fileItem.getName();
                        //获取唯一文件 = uuId + 文件名
                        String uuName = CommonUtils.uuid() + "_" + originName;


                        //将数据写进文件中
                        fileItem.write(new File(dateFileDir, uuName));

                        //保存的路径
                        String savePath = saveFileDir + "/" + uuName;


                        //设置上传文件的返回数据
                        UploadFile uploadFile = new UploadFile();
                        uploadFile.setFileName(originName);
                        uploadFile.setPath(savePath);
                        uploadFile.setType(getFileExt(originName));
                        uploadFile.setSize(fileItem.getSize());
                        fileList.add(uploadFile);
                    }
                }

            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 获得日期格式的文件目录
     *
     * @param realSaveDir
     * @return
     */
    private File getDateFileDir(String realSaveDir) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        saveFileDir = realSaveDir + "/" + dateFormat.format(new Date());
        File fileDir = new File(saveFileDir);
        fileDir.mkdirs();
        return fileDir;
    }

    /**
     * 获得hashCode格式的文件目录
     *
     * @param realSaveDir
     * @return
     */
    private File getHashCodeFileDir(String realSaveDir, String fileName) {
        String hexHashCode = Integer.toHexString(fileName.hashCode());
        saveFileDir = realSaveDir + "/" + hexHashCode.charAt(0) + "/" + hexHashCode.charAt(1);
        File fileDir = new File(saveFileDir);
        fileDir.mkdirs();
        return fileDir;
    }


    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


}
