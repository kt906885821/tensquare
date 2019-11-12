package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.LabelClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author yuming
 * @date 2019/2/18
 */
@Component
public class LabelClientImpl implements LabelClient {
    @Override
    public Result findById(String id) {
        System.out.println("Hystrix熔断器启动了");
        return new Result(false, StatusCode.REMOTE_ERROR, "远程调用失败");
    }
}
