package com.tensor.api.org.config.solr;

import lombok.extern.slf4j.Slf4j;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;

@Slf4j
@org.springframework.context.annotation.Configuration

public class SolrConfigure {
    @Bean(value = "SolrConnection")
    public HttpSolrClient SolrConnection() throws Exception {
        String solrUrl = "http://47.106.181.240:8983/solr/ik";
        HttpSolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        return  solrClient;
    }
}
