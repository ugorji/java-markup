# java-markup

This repository contains the `java-markup` library.

It defines a **format**, **EBNF** parser and library for 
reading and writing the custom and extensive wiki format.

It also provides a bunch of **macros** that can be used within the 
simple format.

Finally, it provides **indexing** support, so a lucene index can easily
tokenize and index these documents, do search results, determine hits
and ranking, etc.

## Dependencies

This repository is part of a multi-project `gradle` build.

It has the following dependencies:

- [java-common](https://github.com/ugorji/java-common)

Before building:

- clone the dependencies into adjacent folders directly under same parent folder
- download [`settings.gradle`](https://gist.githubusercontent.com/ugorji/2a338462e63680d117016793989847fa/raw/settings.gradle) into the parent folder

## Building

```sh
gradle clean
gradle build
```

