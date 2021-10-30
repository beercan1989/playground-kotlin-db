# Playground Kotlin DB
Playing around with Kotlin and database stuff.

# The Tech
* Kotlin - https://kotlinlang.org
* JetBrains / Exposed - https://github.com/JetBrains/Exposed
* Gradle - https://gradle.org
* Multi-Project Build - https://docs.gradle.org/current/userguide/multi_project_builds.html

# Sources
* https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html#sharing_build_logic_between_subprojects

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