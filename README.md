# Dairy Maker

A cattle farm management system that tracks cows in a hierarchical parent-child tree structure.

## Features

- Register cows with unique IDs and nicknames
- Track parent-child relationships (births)
- Remove cows while re-parenting their children to the grandparent
- Choosable registry strategy: linked list (no collections) or HashMap (O(1) lookups)

## Compile

```bash
javac -d out *.java
```

## Run

### Java 7+

```bash
java -cp out Main
```

### Java 6 (no main method)

Java 6 allows running a class without `main()` using only a static block.
If you have Java 6 installed:

```bash
java -cp out Main6
```

## Run Tests

```bash
java -cp out CattleFarmTest
```

## Compile and Run (one-liner)

```bash
javac -d out *.java && java -cp out Main
```

## Compile and Test (one-liner)

```bash
javac -d out *.java && java -cp out CattleFarmTest
```

## Java EE (Servlet - no main method)

Deploy to any servlet container (Tomcat, Jetty, WildFly, etc).

### Requirements

- Java EE / Jakarta EE servlet container
- `javax.servlet-api` on classpath for compilation

### Build WAR

The servlet API jar is included in `lib/javax.servlet-api-4.0.1.jar`.

```bash
# Compile
mkdir -p build/WEB-INF/classes
javac -cp lib/javax.servlet-api-4.0.1.jar -d build/WEB-INF/classes \
    CattleFarm.java CowRecord.java CowChain.java ChainLink.java \
    CowRegistry.java ChainRegistry.java HashMapRegistry.java \
    MainServlet.java

# Copy web.xml
cp WEB-INF/web.xml build/WEB-INF/

# Create WAR
cd build && jar -cvf ../dairy-maker.war . && cd ..
```

### Deploy

1. Copy `dairy-maker.war` to Tomcat's `webapps/` directory
2. Start Tomcat
3. Access: `http://localhost:8080/dairy-maker/farm`
