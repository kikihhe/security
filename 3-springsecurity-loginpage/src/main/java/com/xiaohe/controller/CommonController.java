package com.xiaohe.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

import com.aliyun.oss.OSSClient;

import com.xiaohe.config.AliyunConfig;
import com.xiaohe.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;


/**
 * 文件/图片 上传及显示
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private OSSClient ossClient;
    @Autowired
    private AliyunConfig aliyunConfig;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String USER_REGISTER_CODE = "user:register:code:";


    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 获取原始文件名
        String fileName = file.getOriginalFilename(); // abc.jpg
        // 获取后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")); // .jpg

        // 生成新的路径
        String s = username + "-" + UUID.randomUUID() + suffix; // 15823941994-asdfewr2345wfed2312.jpg

        // 上传至oss
        // 3. 上传至阿里OSS
        try {
            ossClient.putObject(aliyunConfig.getBucketName(), s, new ByteArrayInputStream(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException("上传失败!!");
        }

        return Result.success(s);
    }

    /**
     * 注册时获得验证码,四位数
     * @param request
     * @param response
     * @throws Exception
     */

    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String phone = request.getParameter("phone");

        response.setContentType("image/jpeg");
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 36, 4, 2);

        String code = captcha.getCode();
        // 将验证码存入redis,有效期为1分钟
        // key: 手机号
        // value: 验证码
        stringRedisTemplate.opsForValue().set(USER_REGISTER_CODE + phone, code);

        OutputStream out=response.getOutputStream();
        captcha.write(out);
        out.flush();
        out.close();
    }


}


