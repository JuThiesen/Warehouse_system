public class Algorithms3 {
	

	Neighborhood== Lösungsinstanzen für order
	state transition== tauschen von PSUs für andere Lösungsinstanz
	value function== jedes PSU hat value abhängig von Zahl der Items die order entsprechen
	              


	   temp       //STarttemp
	   tempcool   // coolingRate
	   tempterm  // termination temp --> Suche stoppt bei dieser temperatur
	   iterations  // iterations before temp decrease --> anzahl an vergleichen bevor temperatur gesenkt wird
	probability function//       probability of random choice



	    starte mit random Lösungsinstanz (){

	        for( jedes Item in order){

	            for (jedes PSU){

	                if( psu  enthält item){

	                    nimm in PSU in PSU-Liste und lösche item aus order;

	                    wenn PSU mehrere Items enthält= PSU hat höhere value;
	                    PSU-Liste hat value der sdich aus values einzelner PSUs zsmsetzt;
	                    je weniger PSUs benutzt, je höher value der PSU-Liste

	                }
	            }
	        }
	    }

	    probability function= exp(-((value nachabrlösung)-(aktueller value PSU-Liste))/temperature

	    while( Temperatur > als termination temperature){

	        probability function= exp(-((value nachabrlösung)-(aktueller value PSU-Liste))/temperature;

	        for (Anzahl iterations){

	         generiere random nummer zwischen 0 und 1;

	         betrachte Nachbarn der lösungsinstanz ( der order erfüllt und eine getauschte PSU);

	         if(random number< probability fct){

	            if( Nachbarlösung enthält PSU der mehr orderitems erfüllt als PSU in aktueller Lsg.){
	                lösche PSUs aus PSU-Liste die durch neues PSU eretzt werden;
	                }

	             if (Nachbarlösung enthält gleich viele PSUS oder mehr)   {
	                tausche nicht
	                }
	         }

	         if( random nummer > probability fct){

	            tausche wenn Nachbarlösung gleiche Zahl oder mehr PSUS hat
	            und tausche nicht, auch wenn Nachbarlösung besser
	         }
	        } <-- (alle itertaions durchgelaufen)
	    }

	    senke Temperatur um coolingrate (new Temperatur= temperatur*(1-coolingtemp);
	    }
	return PSU-liste;


}
