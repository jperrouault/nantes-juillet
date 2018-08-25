$(function() {
	$.ajax({
		method: "GET",
		url: "http://localhost:8080/sopramon-web/api/combats",
		
		success: function(combats) {
			for (let combat of combats) {
				appendCombat(combat);
			}
		}
	});
	
	
	$('a[href="#to-combats"]').bind('click', function() {
		slideContent(0);
		return false;
	});
	
	
	
	function appendCombat(combat) {
		let myRow = $('<tr />');
		let myColumnDate = $('<td />');
		let myColumnArene = $('<td />');
		let myColumnType = $('<td />');
		let myColumnCombattant1 = $('<td />');
		let myColumnCombattant2 = $('<td />');
		let myColumnCoups = $('<td />');
		
		
		/* INFOS */
		myColumnDate.html(combat.date);
		myColumnArene.html(combat.arene);
		myColumnType.html(combat.type);
		myColumnCombattant1.html(combat.combattant1.nom);
		myColumnCombattant2.html(combat.combattant2.nom);
		myColumnCoups.html(combat.coups.length);
		
		myRow.append(myColumnDate);
		myRow.append(myColumnArene);
		myRow.append(myColumnType);
		myRow.append(myColumnCombattant1);
		myRow.append(myColumnCombattant2);
		myRow.append(myColumnCoups);
		
		
		/* CLIQUE SUR LIGNE */
		myRow.bind('click', combat, detailsCoups);
		
		
		/* AJOUT DE LA LIGNE */
		$('#combats tbody').append(myRow);
	}
	
	
	function detailsCoups(event) {
		let myCombat = event.data;
		
		//On nettoie la liste des coups avant
		$('#coups tbody tr').remove();
		
		for (let coup of myCombat.coups) {
			appendCoup(coup);
		}
		
		
		slideContent(1);
	}
	
	
	
	function appendCoup(coup) {
		let myRow = $('<tr />');
		let myColumnDate = $('<td />');
		let myColumnAttaquant = $('<td />');
		let myColumnVictime = $('<td />');
		let myColumnDegats = $('<td />');
		
		
		/* INFOS */
		myColumnDate.html(coup.date);
		myColumnAttaquant.html(coup.attaquant.nom);
		myColumnVictime.html(coup.victime.nom);
		myColumnDegats.html(coup.degats);
		
		myRow.append(myColumnDate);
		myRow.append(myColumnAttaquant);
		myRow.append(myColumnVictime);
		myRow.append(myColumnDegats);
		
		
		/* AJOUT DE LA LIGNE */
		$('#coups tbody').append(myRow);
	}
});