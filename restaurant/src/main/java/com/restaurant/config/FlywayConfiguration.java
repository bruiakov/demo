package com.restaurant.config;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "app.profile", havingValue = "production", matchIfMissing = true)
public class FlywayConfiguration {

	public FlywayConfiguration(@Autowired DataSource dataSource) {
		Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
	}
}
