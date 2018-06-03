# flyway-database-migration
flyway runner. Expects the location of the migration scripts as input

Build this project and with the help of generted jar, do migrations. Sample java command as below.

java -Ddb.connection.url="jdbc:mariadb://localhost:3306/" -Ddb.connection.userName="root" -Ddb.connection.password="yourPassword" -jar ./flyway-database-migration-1.0.jar --migrationScriptsLocation="filesystem:W:/path/database-migration-scripts/" > RunLog.log


# Input
* Migration scripts' location - Ex: "filesystem:C:/path/to/migration/scipts"
* database connection properties (would override connection details in applciation.yml)
