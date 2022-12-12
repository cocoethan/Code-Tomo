README:
Note: As JavaFX is no longer included in Java SDK Releases the project relies on JavaFX Dependencies.
Instructions to execute jar file -
1. Download JavaFX on your system, link: https://openjfx.io/
2. Download tomo.jar, note: Double-clicking will not work for execution
3. Open system terminal
4. Change current directory to directory housing tomo.jar
5. Find path for javafx-sdk-19/lib
6. Run the following command: java --module-path "JAVAFX-PATH-HERE" --add-modules javafx.controls,javafx.fxml -jar tomo.jar
7. Enjoy!
Instructions to run source code in IDE -
1. Download JavaFX on your system
2. Include JavaFX SDK Library, JRE System Library (above Java-SE 12), and JavaFX lib folder jars in project's MODULEPATH
Issues and Resolutions for jar file execution -
Note: When running in a supported IDE, older Java versions are supported.
Note: Current jar file was compiled with JDK19 and will need JDK19 to run.
Error: java.lang.UnsupportedClassVersionError
Solution: Update to current Java JDK version 19 
