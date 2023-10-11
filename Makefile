run: build
	java Main

build:
	javac *.java

clean:
	rm -f *.class **/*.class
