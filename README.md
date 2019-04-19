# java-markup

This repository contains the `java-markup` library.

It defines a **format**, **EBNF** parser and library for 
reading and writing the custom and extensive wiki format.

It also provides a bunch of **macros** that can be used within the 
simple format.

Finally, it provides **indexing** support, so a lucene index can easily
tokenize and index these documents, do search results, determine hits
and ranking, etc.

It is used by my `java-markup`, `java-facade` and `java-web` packages, 
and whatever other modules depend on them.

## Dependencies

This is typically used within a multi-project `gradle` build.

It is used by my collab-wiki and collab-forum projects 
as the easy format for writing content.

It depends on themy java-common `http://github.com/ugorji/java-common` library.

## Building

This is typically used within a multi-project `gradle` build.
However, this package doesn't depend on any others.

```sh
gradle clean build
```

