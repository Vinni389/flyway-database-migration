package com.flywaydatabasemigration;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlywayDatabaseMigrationApplication {

  public static void main(@NonNull final String[] args) {

    SpringApplication.run(FlywayDatabaseMigrationApplication.class, args);

  }
}
