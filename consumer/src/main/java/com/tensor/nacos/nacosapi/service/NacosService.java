package com.tensor.nacos.nacosapi.service;

import com.alibaba.nacos.api.naming.pojo.Instance;

/**
 * @author liaochuntao
 */
public interface NacosService {

    /**
     *
     * @param serviceName
     * @return
     */
    Instance getInstance(String serviceName);

}
