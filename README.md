# Ralc

Ralc is a simple programming language that uses [reverse polish notation](https://en.wikipedia.org/wiki/Reverse_Polish_notation) for its syntax. This was a university assignment project.

Ralc programs can be executed in REPL (Read-eval-print loop) tool. Here are a few examples of simple ralc programs:

```
Welcome to ralc!
Type '.help' for more info or '.exit' to exit.

> HelloWorld echo
HelloWorld

> 1 2 swap odd echo
1

> 13 ! echo
1932053504
```

Type `.help` to display help for all available commands or `.help <command-pattern>` to display help for a specific command.
```
Welcome to ralc!
Type '.help' for more info or '.exit' to exit.

> .help ech
echo       prints out the element at the top of the stack (stack remains unchanged)
```

For a complete list of commands, see [`commands.md`](./commands.md).