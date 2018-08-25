$(function() {
	$.ajax({
		method: "GET",
		url: "http://localhost:8080/sopramon-web/api/sopramons",
		
		success: function(sopramons) {
			for (let sopramon of sopramons) {
				appendSopramon(sopramon);
			}
		}
	});
	
	
	
	function appendSopramon(sopramon) {
		let myRow = $('<tr />');
		let myColumnId = $('<td />');
		let myColumnPrenom = $('<td />');
		let myColumnUsername = $('<td />');
		let myColumnExperience = $('<td />');
		

		myColumnId.html(sopramon.id);
		myColumnPrenom.html(sopramon.prenom);
		myColumnUsername.html(sopramon.username);
		myColumnExperience.html(sopramon.capacite.experience);

		myRow.append(myColumnId);
		myRow.append(myColumnPrenom);
		myRow.append(myColumnUsername);
		myRow.append(myColumnExperience);
		
		$('#sopramons tbody').append(myRow);
	}
});