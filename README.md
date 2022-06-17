## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

The command compile the file is:
javac -d bin -cp lib/* src/*.java

The command run the code is:
java -cp "lib/* ; bin" App

## Dependency Management

This project depends on the jackson library, version 2.13, which is used to parse json files
