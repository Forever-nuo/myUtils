package com.forever.util.uploadUtil;

import lombok.Data;

/**
 * Created by Forever丶诺 on 2017-10-23.
 */
@Data
public class UploadFile {
    private String fileName;//文件名
    private String path;//文件存储地址
    private String type;//文件类型
    private long size;
}
