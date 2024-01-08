run: build
	java BookingSystem

build:
	javac *.java

clean:
	rm -f *.class **/*.class
