$(function() {
	$('form').bind('submit', function() {
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/sopramon-web/api/sopramons",
			
			data: {
				nom: $('input[name="nom"]').val(),
				prenom: $('input[name="prenom"]').val(),
				dateNaissance: $('input[name="dateNaissance"]').val(),
				username: $('input[name="username"]').val(),
				password: $('input[name="password"]').val()
			},
			
			success: function(user) {
				window.location.href = "index.html";
			}
		});
		
		return false;
	});
});