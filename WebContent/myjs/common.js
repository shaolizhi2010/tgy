jQuery.fn.setSelection = function(selectionStart, selectionEnd) {
    if(this.lengh == 0) return this;
    input = this[0];
 
    if (input.createTextRange) {
        var range = input.createTextRange();
        range.collapse(true);
        range.moveEnd('character', selectionEnd);
        range.moveStart('character', selectionStart);
        range.select();
    } else if (input.setSelectionRange) {
        input.focus();
        input.setSelectionRange(selectionStart, selectionEnd);
    }
 
    return this;
}
/*
jQuery.fn.enter = function(event) {
	keypress(function( event ) {
		  if ( event.which == 13 ) {
		     event.preventDefault();
		  }
	});
}
*/


$(window).scroll(function(){
	if($(document).scrollTop()>100){
		$('#go-top').css('display','block');	
	}
	else {
		$('#go-top').css('display','none');	
	}
});
$(document).ready(function() {
	$("img").lazyload();
});
