package com.tensor.nacos.nacosapi.util;

import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUrlUtil {

    private static final String BASE_URL = "http://%s:%d%s";

    static public String url(String ip, int port, String path) {
        String url = String.format(BASE_URL, ip, port, path);
        log.info("url : {}", url);
        return url;
    }

    static public String url(Instance instance, String path) {
        return url(instance.getIp(), instance.getPort(), path);
    }

}
