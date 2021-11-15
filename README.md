# Playground Kotlin DB
Playing around with Kotlin and database stuff.

# The Tech
* Java 11
* Kotlin 1.5.x - https://kotlinlang.org
* JetBrains / Exposed - https://github.com/JetBrains/Exposed
* Gradle - https://gradle.org
* Multi-Project Build - https://docs.gradle.org/current/userguide/multi_project_builds.html
* Config4k (Typesafe Config) - https://github.com/config4k/config4k

# Sources
* Database connection balancing and pooling - https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-j2ee-concepts-managing-load-balanced-connections.html
* Best practice on load balancing for a DB - https://pingcap.com/blog/best-practices-for-tidb-load-balancing
* Best practice for Java integration - https://docs.pingcap.com/tidb/stable/java-app-best-practices
* SQL Naming conventions:
  * https://launchbylunch.com/posts/2014/Feb/16/sql-naming-conventions/
  * https://www.sqlshack.com/learn-sql-naming-conventions/

# Build
```shell
# All
./gradlew build

# Each Module
./gradlew :common:build
./gradlew :importer:build
./gradlew :server:build
```

# Test
```shell
# All
./gradlew test

# Each Module
./gradlew :common:test
./gradlew :importer:test
./gradlew :server:test
```

# Run
```shell
# Each Module
./gradlew :importer:run
./gradlew :server:run
```