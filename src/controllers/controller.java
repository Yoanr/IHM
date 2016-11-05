//ordre d'exec des fonctions : 

Model m = new Model();
m.loadBd();
m.connectBDInterne();
// dans le listener oublie pas de recupere le nom du client
String tableaureservation = new String[][];
tableaureservation=m.getReservationByClient(CLIENTNAME);
// OCCUPE TOI DE LAFFICHER DANS LA VUE ;D

// ENSUITE IL CHOISIE EN COCHANT QUELLE RESERVATION CHOISIR ( recupere l'id reservation selectionner)

if(button=="valider"){
String responsemessage=validerReservation(IDRESERVATION)
//afficher responsemessage dans une bulle dans la vue
}

else {
	String tableauchambredispo = new String[][];
tableauchambredispo=getAvailableRooms(IDCHAMBRE_NON_DESIRER,RACCOURCI_CONTRAINTE);
//afficher dans la vue la liste des chambres disponibles
// ENSUIE IL CHOISIE EN COCHANT LA CHAMBRE (recupere idchambre choisie)
 String responsemessage=modifierReservation(IDRESERVATION,IDCHAMBRE_CHOISIE);
 //afficher responsemessage dans une bulle dans la vue
}

//avant que le progamme se ferme 
m.closeBd();