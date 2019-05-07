package com.tensor.nacos.nacosapi.service;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liaochuntao
 */
@Slf4j
@Service
public class NacosServiceImpl implements NacosService {

    @NacosInjected
    private NamingService namingService;

    @Override
    public Instance getInstance(String serviceName) {
        Instance instance;
        try {
            instance = namingService.selectOneHealthyInstance(serviceName);
            log.info("instance : {}", instance);
        } catch (NacosException e) {
            log.error("NacosService : {}", e.getErrMsg());
            throw new RuntimeException(e);
        }
        return instance;
    }
}
