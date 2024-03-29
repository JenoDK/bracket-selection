### Bracket Selection
IntelliJ IDEA plugin for selecting content between bracket pairs or quotes.<br/>
Bracket pairs can be "<>", "[]", "{}", "()"<br/>
https://plugins.jetbrains.com/plugin/11322-bracket-selection

These are the possibilities:
- Select everything from the first found opening brace to the matching closing brace (default mouse shortcut: <i>alt button1 doubleclick</i>, button1 is left click)<br/>
![everything_brackets](documentation/select_brackets_all.gif)
- Select everything between single or double quotes (default mouse shortcut: <i>alt button3 doubleclick</i>, button1 is right click)<br/>
![everything_quotes](documentation/select_quotes_all.gif)
- Select everything from the first found opening brace to current cursor (not bound by default)<br/>
- Select everything from the current cursor to to the first found closing brace (not bound by default)<br/>


Most languages should be supported, you can create an issue if one is not.

### Contribute
#### Setup
```
git clone <repo_url>
cd <repo_dir>
./gradlew idea
```

### Known issues
- Multi line strings in kotlin are not supported for quote selection