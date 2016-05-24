/**
 * 
 */


function sendAjax () {
	
	function sendAjax(element) {
		element.innerHTML = "Send ok";
	$.ajax({
	    type:"GET",
	    url: "getModelOptions",
	    dataType: "json",
	    success: function(data) {
	      element.innerHTML = "YESSSS";
	      alert(data);
	    },
	    error: function(data) {
	      alert(errorThrown);
	    }

	});}
}