JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

ROOT_VIEWS_PATH=views/
ACCUEIL_VIEWS_PATH=views/accueil/
ACCUEIL_CTRLS_PATH=controllers/
ACCUEIL_MODELS_PATH=models/

NETTOYAGE_VIEW_PATH=views/nettoyage/
NETTOYAGE_CTRLS_PATH=controllers/nettoyage/

ACCUEIL_CLASSES = \
	$(ACCUEIL_MODELS_PATH)Model.java \
	$(ACCUEIL_CTRLS_PATH)controller.java \
	$(ACCUEIL_VIEWS_PATH)SearchView.java \
	$(ROOT_VIEWS_PATH)AccueilView.java \
	MainTest.java

NETTOYAGE_CLASSES = \

default: all

accueil: $(ACCUEIL_CLASSES:.java=.class)
nettoyage: $(NETTOYAGE_CLASSES:.java=.class)

all: accueil nettoyage

clean-accueil:
	$(RM) $(ROOT_VIEWS_PATH)*.class
	$(RM) $(ACCUEIL_CTRLS_PATH)*.class
	$(RM) $(ACCUEIL_VIEWS_PATH)*.class
	$(RM) *.class

clean-nettoyage:
	$(RM) $(NETTOYAGE_VIEW_PATH)*.class
	$(RM) $(NETTOYAGE_CTRLS_PATH)*.class

clean: clean-accueil clean-nettoyage