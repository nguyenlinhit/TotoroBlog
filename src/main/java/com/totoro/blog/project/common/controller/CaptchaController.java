package com.totoro.blog.project.common.controller;

import com.totoro.blog.common.contant.Constants;
import com.totoro.blog.common.utils.IdUtils;
import com.totoro.blog.common.utils.VerifyCodeUtils;
import com.totoro.blog.common.utils.sign.Base64;
import com.totoro.blog.framework.redis.RedisCacheService;
import com.totoro.blog.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @className: CaptchaController
 * @description: Generate Captcha
 * @author: LinhNguyen
 * @date: 3/1/2020
 */
@Slf4j
@RestController
public class CaptchaController {
    private final RedisCacheService redisCacheService;

    public CaptchaController(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    @GetMapping("/captchaImg")
    public AjaxResult getCode(HttpServletResponse response) throws IOException {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        redisCacheService.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        int w = 208;
        int h = 80;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("uuid", uuid);
            ajax.put("img", Base64.encode(stream.toByteArray()));
            ajax.put("code", verifyCode);
            return ajax;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return AjaxResult.error(e.getMessage());
        } finally {
            stream.close();
        }
    }
}
