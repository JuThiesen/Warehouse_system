public class Algorithms3 {
	

	Neighborhood== L�sungsinstanzen f�r order
	state transition== tauschen von PSUs f�r andere L�sungsinstanz
	value function== jedes PSU hat value abh�ngig von Zahl der Items die order entsprechen
	              


	   temp       //STarttemp
	   tempcool   // coolingRate
	   tempterm  // termination temp --> Suche stoppt bei dieser temperatur
	   iterations  // iterations before temp decrease --> anzahl an vergleichen bevor temperatur gesenkt wird
	probability function//       probability of random choice



	    starte mit random L�sungsinstanz (){

	        for( jedes Item in order){

	            for (jedes PSU){

	                if( psu  enth�lt item){

	                    nimm in PSU in PSU-Liste und l�sche item aus order;

	                    wenn PSU mehrere Items enth�lt= PSU hat h�here value;
	                    PSU-Liste hat value der sdich aus values einzelner PSUs zsmsetzt;
	                    je weniger PSUs benutzt, je h�her value der PSU-Liste

	                }
	            }
	        }
	    }

	    probability function= exp(-((value nachabrl�sung)-(aktueller value PSU-Liste))/temperature

	    while( Temperatur > als termination temperature){

	        probability function= exp(-((value nachabrl�sung)-(aktueller value PSU-Liste))/temperature;

	        for (Anzahl iterations){

	         generiere random nummer zwischen 0 und 1;

	         betrachte Nachbarn der l�sungsinstanz ( der order erf�llt und eine getauschte PSU);

	         if(random number< probability fct){

	            if( Nachbarl�sung enth�lt PSU der mehr orderitems erf�llt als PSU in aktueller Lsg.){
	                l�sche PSUs aus PSU-Liste die durch neues PSU eretzt werden;
	                }

	             if (Nachbarl�sung enth�lt gleich viele PSUS oder mehr)   {
	                tausche nicht
	                }
	         }

	         if( random nummer > probability fct){

	            tausche wenn Nachbarl�sung gleiche Zahl oder mehr PSUS hat
	            und tausche nicht, auch wenn Nachbarl�sung besser
	         }
	        } <-- (alle itertaions durchgelaufen)
	    }

	    senke Temperatur um coolingrate (new Temperatur= temperatur*(1-coolingtemp);
	    }
	return PSU-liste;


}
