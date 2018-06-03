package com.flywaydatabasemigration;

import com.flywaydatabasemigration.callback.BeforeMigrateHook;
import com.flywaydatabasemigration.utils.ConnectionProperties;
import com.flywaydatabasemigration.utils.LogSchemaHistory;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ApplicationExecutor implements ApplicationRunner {

  private static final Logger logger = LoggerFactory.getLogger(ApplicationExecutor.class);

  @NonNull
  private final Flyway flyway;

  @NonNull
  private final ConnectionProperties connectionProperties;

  @NonNull
  private final String schemas;

  @NonNull
  private final BeforeMigrateHook beforeMigrateHook;

  @NonNull
  private final LogSchemaHistory logSchemaHistory;

  public ApplicationExecutor(@NonNull final Flyway flyway,
                             @NonNull final ConnectionProperties connectionProperties,
                             @NonNull final String schemas,
                             @NonNull final BeforeMigrateHook beforeMigrateHook,
                             @NonNull final LogSchemaHistory logSchemaHistory) {

    this.flyway = flyway;
    this.connectionProperties = connectionProperties;
    this.schemas = schemas;
    this.beforeMigrateHook = beforeMigrateHook;
    this.logSchemaHistory = logSchemaHistory;
  }

  public void execute(@NonNull final String migrationScriptsLocation) {

    try {
      setFlywayConfiguration(migrationScriptsLocation);

      flyway.migrate();
      logSchemaHistory.log();

    } catch (final FlywayException fe) {
      logger.error("Exception occurred - {}", fe.getStackTrace());

      logSchemaHistory.log();
      throw fe;
    }
  }

  private void setFlywayConfiguration(@NonNull final String migrationScriptsLocation) {

    // First thing to do - set connection & schema details
    flyway.setSchemas(schemas);
    flyway.setDataSource(connectionProperties.getUrl(),
                         connectionProperties.getUserName(),
                         connectionProperties.getPassword());

    logSchemaHistory.log();

    flyway.setIgnoreFutureMigrations(true);
    flyway.setLocations(migrationScriptsLocation);
    flyway.setCallbacks(beforeMigrateHook);

    logSchemaHistory.log();
  }

  @Override
  public void run(final ApplicationArguments args) throws Exception {

    final String migrationScriptsLocation = args.getOptionValues("migrationScriptsLocation").get(0);

    if (migrationScriptsLocation == null) {

      logger.error("specify --migrationScriptsLocation argument");
      throw new RuntimeException("specify --migrationScriptsLocation argument");
    }
    execute(migrationScriptsLocation);
  }
}