//var flag = 0;
//
//function getInitAjax() {
//	//document.getElementById("demo").innerHTML = "Send ok";
//	if (flag == 0) {
//	    $.ajax({
//	        type:"GET",
//	        url: "getModelOptions",
//	        dataType: "json",
//	        success: function (data) {
//	    		//document.getElementById("demo").innerHTML = "AJAX YESSSS";
//	    		parseInit(data);
//	    	},
//	        	/*function(data) {
//	          document.getElementById("demo").innerHTML = "YESSSS";
//	          alert(data);
//	        },*/
//	        error: function(err) {
//	        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
//	        }
//    	});
//	    flag = 1;
//	}
//}
//
//function parseInit (data) {
//	//document.getElementById("demo").innerHTML = " parse YESSSS";
//	//var obj = JSON.parse(data);
//	//document.getElementById("demo2").innerHTML = data.nbDims[0];
//	var option;
//	var dbselect = document.getElementById('database');
//    var collselect = document.getElementById('collection');
//    var dimsselect = document.getElementById('nbdims');
//    dbselect.options.length = 0;
//    collselect.options.length = 0;
//    dimsselect.options.length = 0;
//    for (index = 0; index < data.database.length; ++index) {
//        option = data.database[index];
//        dbselect.options.add(new Option(option, option));
//    }
//    for (index = 0; index < data.collection.length; ++index) {
//        option = data.collection[index];
//        collselect.options.add(new Option(option, option));
//    }
//    for (index = 0; index < data.nbDims.length; ++index) {
//        option = data.nbDims[index];
//        dimsselect.options.add(new Option(option, option));
//    }
//}
//
//function setInitAjax() {
//	//document.getElementById("demo").innerHTML = $( "#database option:selected" ).text();
//    $.ajax({
//        type:"GET",
//        url: "setModelOptions",
//        data: {
//        		"database": $( "#database option:selected" ).text(),
//        		"nbDims": $( "#nbdims option:selected" ).text(),
//        		"collection": $( "#collection option:selected" ).text()
//        },
//        dataType: "json",
//        success: function (data) {
//    		//document.getElementById("demo").innerHTML = "AJAX SET YESSSS";
//    		//alert("The system is ready to run..");
//    		//self.close();
//        	$("#overlay").remove();
//    		/*var daddy = window.self;
//    		daddy.opener = window.self;
//    		daddy.close();*/
//    	},
//        error: function(err) {
//        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
//        }
//	});
//}

var options = []; var choices = new Object(); var totalDims;
/*$(document).ready(function() {
    getAjax();
});*/

function getAjaxInit() {
	    $.ajax({
	        type:"GET",
	        url: "getModelOptions",
	        dataType: "json",
	        success: function (data) {
	    		parseInit(data);
	    	},
	        error: function(err) {
	        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
	        }
    	});
}

function parseInit(data) {
	var i=0;
	$(document.body).append('<div id="overlay"> <div class="container-fluid" style="width:60%" id="container"> <style type="text/css"> div[id=container] { background: #f4f7f8 !important; }</style><p>&nbsp;</p> <p>&nbsp;</p> </div></div>');
	for (key in data) {
		var attrName = key;
		console.log(attrName);
		options[i] = attrName;
		var attrValue = data[key];
		console.log(attrValue);
		var str = "#field_"+i;
		choices[attrName]=attrValue[0];
		console.log(choices);
		if (attrValue.length == 1) {
			$("#container").append('<div class="row-md-1"><label>Choose '+attrName+':</label><input type="text" class="form-control" id="'+attrName+'" value="'+attrValue+'" placeholder="'+attrValue+'" onchange="setTextChoice($(this))"/></div> <p>&nbsp;</p>');
		} else {
			var sel = '<div class="row-md-1"><label for="exampleInputName2">Choose '+attrName+':</label><select id="'+attrName+'" class="form-control" onchange="setTextChoice($(this))">';
			for (j=0; j<attrValue.length; j++) {
				sel = sel + '<option value='+attrValue[j]+'>'+attrValue[j]+'</option>';
			}
			sel = sel + '</select></div><p>&nbsp;</p>';
			$("#container").append(sel);
		}
		i++;
	}
	$('#container').append('<button id="button1" type="button" class="btn btn-default" onclick="setAjaxInit()">Initiate model</button><p>&nbsp;</p><p>&nbsp;</p>');
	$('#container').append('<p>PS: For text fields, please make sure to insert a value.</p><p>&nbsp;</p><p>&nbsp;</p>');
}

function setTextChoice (elmnt) {
	var id = elmnt.attr('id');
	var val = elmnt.val();
	choices[id]=val;
	console.log(choices);
}

function setAjaxInit (){
	totalDims = choices["nbDims"][0];
	$.ajax({
        type:"GET",
        url: "setModelOptions",
        data: choices,
        dataType: "json",
        success: function (data) {
        	$("#overlay").remove();
        	setDimensionsOptions();
    	},
        error: function(err) {
        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
        }
	});    		
}

function setDimensionsOptions () {
	var sel = document.getElementById("x_select");
	sel.options.length = 0;
	for (index = 0; index < totalDims; index++) {
      //option index;
      sel.options.add(new Option(index, index));
    }
	sel = document.getElementById("y_select");
	sel.options.length = 0;
	for (index = 0; index < totalDims; index++) {
      //option = index;
      sel.options.add(new Option(index, index));
    }
}