$(function() {
	$('form').bind('submit', function() {
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/sopramon-web/api/auth",
			
			data: {
				username: $('input[name="username"]').val(),
				password: $('input[name="password"]').val()
			},
			
			success: function(user) {
				//ADMIN
				if (user.dateNaissance === undefined) {
					window.location.href = "admin";
				}
				
				else {
					window.location.href = "world.html";
				}
			}
		});
		
		return false;
	});
});