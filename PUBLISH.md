# Publishing new version

- Update version & add changelog in `build.gradle`
- Check for newer versions at [build range numbers](https://plugins.jetbrains.com/docs/intellij/build-number-ranges.html#intellij-platform-based-products-of-recent-ide-versions) and update the 
  ```
  intellij {
      version = '2021.2'
  }
  ```
  and
  ```
  patchPluginXml {
      sinceBuild = '201'
      untilBuild = '212.*'
      ...
  }
  ```
- Run 
  ```
  ./gradlew runPluginVerifier
  ./gradlew publishPlugin
  ```