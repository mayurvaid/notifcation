package com.example.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(
		  basePackages = "com.example.notification.repositories")
public class CassandraConfig extends AbstractCassandraConfiguration {
	@Value("${cassandra.keyspacename:notification}")
	private String keyspaceName;
	
	@Value("${cassandra.host:127.0.0.1}")
	private String cassandraHost;
	
    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }
 
    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = 
          new CassandraClusterFactoryBean();
        cluster.setContactPoints(cassandraHost);
        cluster.setPort(9042);
        return cluster;
    }
 
  
}
