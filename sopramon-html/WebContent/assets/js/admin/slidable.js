var slidesCount = $('.slidable .slide').length;
var activeSlideIndex = 0;


function slideContent(slideIndex) {
	let myDirection = (slideIndex < activeSlideIndex) ? 100 : -100;
	
	$('.slidable .slide').css('transform', 'translateX(' + myDirection + 'vw)');
	$('.slidable .slide:nth-child(' + (slideIndex + 1) + ')').css('transform', 'translateX(0vw)');
	
	activeSlideIndex = slideIndex;
}