package com.tensquare.qa.client;

import com.tensquare.qa.client.impl.LabelClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yuming
 * @date 2019/2/17
 */
@FeignClient(value = "tensquare-base", fallback = LabelClientImpl.class)
public interface LabelClient {

    /**
     * 根据ID查询
     * 这里@PathVariable必须有value值
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/label/{id}", method = RequestMethod.GET)
    Result findById(@PathVariable(value = "id") String id);
}
