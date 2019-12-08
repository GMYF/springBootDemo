package com.light.springboot.util.io;

import com.light.springboot.util.date.DateUtil;

import java.io.*;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/11/29 18:47
 */
public class IoUtils {
    private InputStreamReader iom;

    /**
     * 将文件输出到目录
     *
     * @param in
     */
    public static void readFile(InputStream in, String fileName, String fileType) {
        File file = null;
        File finalFile = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        byte[] bytes = new byte[1024];
        int byteRead = 0;
        try {
            file = new File(DateUtil.getTime());
            if (!file.exists()) {
                file.mkdir();
            }
            finalFile = new File(file.getAbsolutePath(), fileName + "." + fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            bos = new BufferedOutputStream(new FileOutputStream(finalFile));
            bis = new BufferedInputStream(in);
            while ((byteRead = bis.read(bytes)) != -1) {
                bos.write(byteRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }   finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
