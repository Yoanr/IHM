JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

ROOT_VIEWS_PATH=views/
ACCUEIL_VIEWS_PATH=views/accueil/
ACCUEIL_CTRLS_PATH=controllers/
ACCUEIL_MODELS_PATH=models/

ACCUEIL_ARCHIVE = Accueil.jar
NETTOYAGE_ARCHIVE = Nettoyage.jar

NETTOYAGE_VIEW_PATH=views/nettoyage/
NETTOYAGE_CTRLS_PATH=controllers/

ACCUEIL_CLASSES = \
	$(ACCUEIL_MODELS_PATH)AccueilModel.java \
	$(ACCUEIL_VIEWS_PATH)JCheckBoxTable.java \
	$(ACCUEIL_CTRLS_PATH)Controller.java \
	$(ACCUEIL_VIEWS_PATH)JReservation.java \
	$(ACCUEIL_VIEWS_PATH)SearchView.java \
	$(ROOT_VIEWS_PATH)AccueilView.java \
	AccueilMain.java

NETTOYAGE_CLASSES = \
	$(ACCUEIL_MODELS_PATH)NettoyageModel.java \
	$(ACCUEIL_CTRLS_PATH)ControllerNettoyage.java \
	$(ACCUEIL_CTRLS_PATH)Stockage.java \
	$(ACCUEIL_CTRLS_PATH)ControllerNettoyage2.java \
	$(ROOT_VIEWS_PATH)NettoyageView.java \
	MainNettoyage.java

default: all

accueil: $(ACCUEIL_CLASSES:.java=.class) make-accueil-jar clean-accueil
nettoyage: $(NETTOYAGE_CLASSES:.java=.class) make-nettoyage-jar clean-nettoyage

all: accueil nettoyage

clean-accueil:
	$(RM) $(ACCUEIL_MODELS_PATH)*.class
	$(RM) $(ROOT_VIEWS_PATH)*.class
	$(RM) $(ACCUEIL_CTRLS_PATH)*.class
	$(RM) $(ACCUEIL_VIEWS_PATH)*.class
	$(RM) *.class

clean-nettoyage:
	$(RM) $(NETTOYAGE_VIEW_PATH)*.class
	$(RM) $(NETTOYAGE_CTRLS_PATH)*.class
	$(RM) $(ACCUEIL_MODELS_PATH)*.class
	$(RM) *.class
	$(RM) $(ROOT_VIEWS_PATH)*.class

clean: clean-accueil clean-nettoyage

run-accueil:
	java -cp "mariadb-client.jar:." MainTest

make-accueil-jar:
	@echo "Java archive creation ..."
	jar cvmf ACCUEIL.MF $(ACCUEIL_ARCHIVE) $(ACCUEIL_VIEWS_PATH)*.class $(ROOT_VIEWS_PATH)AccueilView.class $(ACCUEIL_CTRLS_PATH)Controller.class $(ACCUEIL_MODELS_PATH)AccueilModel.class AccueilMain.class
	@echo "Archive creation is a success, if you want to use the selector mode, use as argument tester, -tester or --tester by executing the archive"

make-nettoyage-jar:
	@echo "Java archive creation ..."
	jar cvmf NETTOYAGE.MF $(NETTOYAGE_ARCHIVE) $(NETTOYAGE_VIEW_PATH)*.class $(ROOT_VIEWS_PATH)NettoyageView.class $(ACCUEIL_CTRLS_PATH)ControllerNettoyage*.class $(ACCUEIL_CTRLS_PATH)Stockage.class $(ACCUEIL_MODELS_PATH)NettoyageModel.class MainNettoyage.class
	@echo "Archive creation is a success, if you want to use the selector mode, use as argument tester, -tester or --tester by executing the archive"
