package top.harvie.ProjectTeam.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
@Api(value="FileController",description = "测试文件上传与下载的接口")
public class FileController {

    @Value("${file.path}")
    private String filePath;

    private static Logger log = LogManager.getLogger(FileController.class);

    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ApiOperation(value="/uploadFile接口",notes="上传文件")
    public Map uploadFile(
            @ApiParam(required = true,name="file",value = "文件") @RequestParam(value = "file",required = true) MultipartFile file
    ){
        Map<String,Object> map=new HashMap<>();
        log.info("进入uploadFile接口");
        log.info("filePath:"+this.filePath);
        if (file.isEmpty()) {
            map.put("state：","文件为空");
            return map;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = this.filePath;
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        log.info("文件修改名为：" + fileName);
        File dest = new File(filePath + fileName);
        log.info("文件存储目录为：" + dest.getPath());

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            log.info("创建文件目录" );
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            map.put("state：","上传成功");
            map.put("file：",dest.toString());
            return map;
        } catch (IllegalStateException e) {
            map.put("上传失败：",e.getMessage());
            return map;
        } catch (IOException e) {
            map.put("上传失败：",e.getMessage());
            return map;
        }

    }


//    @RequestMapping(value = "/downloadFile",method = RequestMethod.POST)
//    @ApiOperation(value="/downloadFile接口",notes="下载文件")
//    public Map downloadFile(
//            @ApiParam(required = true,name="fileName",value = "文件名") @RequestParam(value = "filename") String filename,
//            HttpServletResponse response
//    ){
//        Map<String,Object> map=new HashMap<>();
//        log.info("进入downloadFile接口");
//        if (filename==null) {
//            map.put("state：","文件名为空");
//            return map;
//        }
//        // 文件所在的路径
//        String filePath = this.filePath;
//        log.info("文件所在的路径：" + filePath);
//        // 获取文件
//        File file = new File(filePath + filename);
//        log.info("文件总路径" + file.getPath());
//        // 检测是否存在文件
//        if (file.exists()) {
//            log.info("文件存在" );
//            byte[] buffer = new byte[1024];
//            FileInputStream fis = null;
//            BufferedInputStream bis = null;
//            try {
//                fis = new FileInputStream(file);
//                bis = new BufferedInputStream(fis);
//                OutputStream os = response.getOutputStream();
//                int i = bis.read(buffer);
//                while (i != -1) {
//                    os.write(buffer, 0, i);
//                    i = bis.read(buffer);
//                }
//            } catch (IllegalStateException e) {
//                map.put("下载失败：",e.getMessage());
//                return map;
//            } catch (IOException e) {
//                map.put("下载失败：",e.getMessage());
//                return map;
//            }finally {
//                if (bis != null) {
//                    try {
//                        bis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (fis != null) {
//                    try {
//                        fis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }else{
//            map.put("下载失败：","文件不存在");
//            return map;
//        }
//        map.put("下载成功：","已下载");
//        return map;
//    }
}
