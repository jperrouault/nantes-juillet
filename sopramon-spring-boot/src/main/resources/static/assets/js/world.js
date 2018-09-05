$(function() {
	$('.monster').bind('click', function() {
		let that = $(this);
		$('.monster').not($(this)).css('opacity', 0);
		$(this).addClass('open');
		
		setTimeout(function() {
			window.location.href = that.attr('data-href');
		}, 500);
	});
});