package com.kevin.common.interceptor;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author kevin
 * @date 2021-12-20 17:30
 */
@Slf4j
@RestControllerAdvice
public class GlobalAdvice {

    @ExceptionHandler(Exception.class)
    public R<String> exception(Exception e) {
        log.error(e.getMessage(), e);
        return R.failed("请求异常：" + e.getMessage());
    }
}
