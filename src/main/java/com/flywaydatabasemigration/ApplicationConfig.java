package com.flywaydatabasemigration;

import com.flywaydatabasemigration.callback.BeforeMigrateHook;
import com.flywaydatabasemigration.utils.ConnectionProperties;
import com.flywaydatabasemigration.utils.LogSchemaHistory;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @NonNull
  @Bean
  public Flyway flyway() {

    return new Flyway();
  }

  @ConfigurationProperties(prefix = "db.connection",
                           ignoreUnknownFields = false)
  @Bean
  @NonNull
  public ConnectionProperties connectionUtil() {

    return new ConnectionProperties();
  }

  @NonNull
  @Bean
  public ApplicationExecutor applicationExecutor(@NonNull final Flyway flyway,
                                                 @NonNull final ConnectionProperties connectionProperties,
                                                 @Value("${db.schemas}") @NonNull final String schemas,
                                                 @NonNull final BeforeMigrateHook beforeMigrateHook,
                                                 @NonNull final LogSchemaHistory logSchemaHistory) {

    return new ApplicationExecutor(flyway, connectionProperties, schemas, beforeMigrateHook, logSchemaHistory);
  }

  @NonNull
  @Bean
  public BeforeMigrateHook beforeRun(@NonNull final Flyway flyway) {

    return new BeforeMigrateHook(flyway);
  }

  @NonNull
  @Bean
  public LogSchemaHistory logInfo(@NonNull final Flyway flyway) {

    return new LogSchemaHistory(flyway);
  }
}
