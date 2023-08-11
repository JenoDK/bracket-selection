Change the type in `build.gradle`  
for rider:
```
intellij {
	version = '2023.2'
	type = 'RD'
}
```

let gradle build run and find the jar file
```
unzip app.jar -d appFolder 
```
Remove all the directories except for `com/jetbrains/rider/ideaInterop` and create the `rider.jar` from it with
```
jar cvf rider.jar -C appFolder/ .
```
and replace the rider.jar in `libs`