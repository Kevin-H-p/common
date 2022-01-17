package com.kevin.common.controller;

import com.kevin.common.util.QRCodeUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


/**
 * @author kevin
 * @date 2022-01-17 15:16
 */
@Api(tags = "工具")
@RestController
@RequestMapping("common")
public class CommonController {

    @RequestMapping(value = "generatorQR",method = RequestMethod.GET)
    public  void generatorQR(String content,String text, HttpServletResponse response) throws Exception{

        QRCodeUtil.encode(content,text, response.getOutputStream());

    }

    @RequestMapping(value = "generatorQRLogo",method = RequestMethod.GET)
    public  void generatorQRLogo(String content, String text,String icon, HttpServletResponse response) throws Exception{

        QRCodeUtil.encode(content,text,icon, response.getOutputStream());

    }
}
