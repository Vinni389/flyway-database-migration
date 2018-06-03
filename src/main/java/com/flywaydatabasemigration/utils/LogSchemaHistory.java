package com.flywaydatabasemigration.utils;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationInfoService;
import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.internal.info.MigrationInfoDumper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogSchemaHistory {

  private static final Logger logger = LoggerFactory.getLogger(LogSchemaHistory.class);

  @NonNull
  private final Flyway flyway;

  public LogSchemaHistory(final @NonNull Flyway flyway) {

    this.flyway = flyway;
  }

  public void log() {

    final MigrationInfoService info = flyway.info();
    final MigrationInfo current = info.current();
    final MigrationVersion currentSchemaVersion = (current == null) ? MigrationVersion.EMPTY : current.getVersion();

    logger.info("Schema version: {}", currentSchemaVersion);
    logger.info(MigrationInfoDumper.dumpToAsciiTable(info.all()));
  }
}
