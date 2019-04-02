### Bracket Selection
IntelliJ IDEA plugin for selecting content between brackets.<br/>
https://plugins.jetbrains.com/plugin/11322-bracket-selection

There are three possibilities:
- Select everything from the first found opening brace to the matching closing brace (default mouse shortcut: <i>alt button1 doubleclick</i> or <i>hold alt -> double left click anywhere in between brackets</i> in human terms)<br/>
![everything](documentation/select_bracket_all.gif)
- Select everything from the first found opening brace to current cursor (not bound by default)<br/>
![opening](documentation/select_bracket_left.gif)
- Select everything from the current cursor to to the first found closing brace (not bound by default)<br/>
![closing](documentation/select_bracket_right.gif)

### Contribute
#### Setup
```
git clone <repo_url>
cd <repo_dir>
./gradlew idea
```
