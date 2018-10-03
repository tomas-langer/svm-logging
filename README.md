# Java util logging in Substrate VM

To reproduce the problem:

1. check ./etc/graal/env.sh and fix variables according to your environment
2. go to the root of the project (where pom.xml is located) and build the project
    ```bash
    ./build.sh
    ```
3. run each program (see MainInitLogging.java and StaticInitLogging.java in sources)
    ```bash
    java -jar target/init-main.jar
    java -jar target/init-static.jar
    ./init-main
    ./init-static
    ```
    
##What should happen
All of the programs should have exactly the same text in standard output and in log file "log-output.txt":
```text
Finest static
Fine static
Info static
Severe static
Info other
Severe other
```
   
##What actually happens 
The java programs have the output as expected, native-images have different output.

init-main
- configuration of handlers is working
- configuration of levels and formatters seems to be ignored

```text
INFO: Info static
Oct 03, 2018 3:54:04 PM io.helidon.reproducer.svm.TestClass
SEVERE: Severe static
Oct 03, 2018 3:54:04 PM io.helidon.other
INFO: Info other
Oct 03, 2018 3:54:04 PM io.helidon.other
SEVERE: Severe other
```

init-static
- configuration of handlers is working
- configuration of log levels is working
- configuration of formatters seems to be ignored

```text
Oct 03, 2018 4:00:29 PM io.helidon.reproducer.svm.TestClass
FINEST: Finest static
Oct 03, 2018 4:00:29 PM io.helidon.reproducer.svm.TestClass
FINE: Fine static
Oct 03, 2018 4:00:29 PM io.helidon.reproducer.svm.TestClass
INFO: Info static
Oct 03, 2018 4:00:29 PM io.helidon.reproducer.svm.TestClass
SEVERE: Severe static
Oct 03, 2018 4:00:29 PM io.helidon.other
INFO: Info other
Oct 03, 2018 4:00:29 PM io.helidon.other
SEVERE: Severe other
```

# Remedy (partial)
Modifying the class com.oracle.svm.core.jdkLoggerSubstitutions.java in substrate vm,
 I could fix the init-static, by commenting out:
 
- static block with "dummy initialization"
- Target_java_util_logging_LogManager substitution class