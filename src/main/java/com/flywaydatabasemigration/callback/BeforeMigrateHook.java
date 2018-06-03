package com.flywaydatabasemigration.callback;

import java.sql.Connection;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.callback.BaseFlywayCallback;

public class BeforeMigrateHook extends BaseFlywayCallback {

  @NonNull
  private final Flyway flyway;

  public BeforeMigrateHook(@NonNull final Flyway flyway) {

    this.flyway = flyway;
  }

  @Override
  public void beforeMigrate(final Connection connection) {

    flyway.repair();
  }
}
