package com.techhive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.techhive.repository")
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    @Value("${spring.elasticsearch.url}")
    private String elasticsearchUrl;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
            .connectedTo(elasticsearchUrl)
            .build();
    }

}
