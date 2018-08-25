$(function() {
	var currentItem = null;
	
	$.ajax({
		method: "GET",
		url: "http://localhost:8080/sopramon-web/api/items",
		
		success: function(items) {
			for (let item of items) {
				appendItem(item);
			}
		}
	});
	
	
	$('a[href="#add-item"]').bind('click', function() {
		slideContent(1);
		return false;
	});
	
	
	
	$('form').bind('submit', function() {
		let myAjaxObject = {
			method: "POST",
			url: "http://localhost:8080/sopramon-web/api/items",
			contentType: 'application/json',
			
			data: JSON.stringify({
				nom: $('input[name="nom"]').val(),
				prix: $('input[name="prix"]').val(),
				capacite: {
					attaque: $('input[name="attaque"]').val(),
					defense: $('input[name="defense"]').val(),
					esquive: $('input[name="esquive"]').val(),
					vitesse: $('input[name="vitesse"]').val(),
					pointsDeVie: $('input[name="pointsDeVie"]').val()
				}
			}),
			
			success: function(item) {
				appendItem(item, currentItem);
				
				$('input[name="nom"]').val("");
				$('input[name="prix"]').val("");
				$('input[name="attaque"]').val("0");
				$('input[name="defense"]').val("0");
				$('input[name="esquive"]').val("0");
				$('input[name="vitesse"]').val("0");
				$('input[name="pointsDeVie"]').val("0");
				
				currentItem = null;
				slideContent(0);
			}
		};
		
		
		if (currentItem !== null) {
			myAjaxObject.method = "PUT";
			myAjaxObject.url += "/" + currentItem.id;
		}
		
		
		$.ajax(myAjaxObject);
		return false;
	});
	
	
	
	function appendItem(item, replacementItem) {
		let myRow = $('<tr />');
		let myColumnId = $('<td />');
		let myColumnNom = $('<td />');
		let myColumnPrix = $('<td />');
		let myColumnCapacites = $('<td />');
		
		let myColumnActions = $('<td />');
		let myButtonEdit = $('<button class="btn btn-warning" />');
		let myButtonDelete = $('<button class="btn btn-danger" />');
		
		
		/* INFOS */
		myRow.attr('data-item-id', item.id);
		myColumnId.html(item.id);
		myColumnNom.html(item.nom);
		myColumnPrix.html(item.prix);
		myColumnCapacites.html(item.capacite.attaque + ", " + item.capacite.defense + ", " + item.capacite.esquive + ", " + item.capacite.vitesse + ", " + item.capacite.pointsDeVie);
		
		myRow.append(myColumnId);
		myRow.append(myColumnNom);
		myRow.append(myColumnPrix);
		myRow.append(myColumnCapacites);
		
		
		/* ACTIONS */
		myButtonEdit.html("Editer");
		myButtonDelete.html("Supprimer");
		
		myColumnActions.append(myButtonEdit);
		myColumnActions.append(myButtonDelete);
		
		myRow.append(myColumnActions);

		myButtonEdit.bind('click', item, editItem);
		myButtonDelete.bind('click', item, deleteItem);
		
		
		/* AJOUT DE LA LIGNE */
		if (replacementItem == null) {
			$('#items tbody').append(myRow);
		}
		
		else { //On cherche l'item à remplacer
			let myReplacementRow = $('#items tbody tr[data-item-id=' + replacementItem.id + ']');
			myRow.insertBefore(myReplacementRow);
			myReplacementRow.remove();
		}
	}
	
	
	
	function editItem(event) {
		let myRow = $(this).closest('tr');
		let item = event.data;

		$('input[name="nom"]').val(item.nom);
		$('input[name="prix"]').val(item.prix);
		$('input[name="attaque"]').val(item.capacite.attaque);
		$('input[name="defense"]').val(item.capacite.defense);
		$('input[name="esquive"]').val(item.capacite.esquive);
		$('input[name="vitesse"]').val(item.capacite.vitesse);
		$('input[name="pointsDeVie"]').val(item.capacite.pointsDeVie);
		
		currentItem = item;
		slideContent(1);
	}
	
	
	
	function deleteItem(event) {
		let myRow = $(this).closest('tr');
		let item = event.data;
		
		$.ajax({
			method: "DELETE",
			url: "http://localhost:8080/sopramon-web/api/items/" + item.id,
			
			success: function() {
				myRow.remove();
			}
		});
	}
});