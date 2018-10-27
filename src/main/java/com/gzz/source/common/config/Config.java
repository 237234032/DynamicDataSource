package com.gzz.source.common.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author gzz_gzz@163.com
 * @date 2018-03-08
 */
@Configuration
public class Config {

	@Bean(name = "ds1")
	@ConfigurationProperties(prefix = "spring.ds1")
	public DataSource dataSource1() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "ds2")
	@ConfigurationProperties(prefix = "spring.ds2")
	public DataSource dataSource2() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public DataSource getDataSource() {
		DynSource dds = new DynSource();
		dds.setDefaultTargetDataSource(dataSource1());
		Map<Object, Object> dsMap = new HashMap<>(5);
		dsMap.put("ds1", dataSource1());
		dsMap.put("ds2", dataSource2());
		dds.setTargetDataSources(dsMap);
		return dds;
	}

	@Bean
	public JdbcTemplate getT() {
		return new JdbcTemplate(getDataSource());
	}

	@Bean
	public NamedParameterJdbcTemplate getN() {
		return new NamedParameterJdbcTemplate(getDataSource());
	}

}
