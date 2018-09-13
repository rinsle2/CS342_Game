JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Direction.java \
	Place.java \
	Game.java \
	GameTester.java
default: classes 
	

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
