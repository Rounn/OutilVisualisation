/**
 * 
 * 
 */

var trace; var xTab = []; var yTab = []; var textTab = []; var textTabWithRef = []; var choiceX = "0"; var choiceY="1"; var id;
var flagRef = 0;
var jsonPoints;

function play(){
	flagRef = 0;
	textTabWithRef = [];
	var totalSteps = parseInt(document.getElementById("total_steps_input").value)/parseInt(document.getElementById("nbr_steps_input").value);
	document.getElementById("totalsteps_dash").innerHTML ="Total = " + document.getElementById("total_steps_input").value;
	//document.getElementById("test2").innerHTML =document.getElementById("nbr_steps_input").value;
	//document.getElementById("test3").innerHTML =totalSteps;
	$.ajax({
        type:"GET",
        url: "setnbrs",
        dataType: "json",
        data: {"nbrSteps":parseInt(document.getElementById("nbr_steps_input").value)},
        success: function (data) {
    		document.getElementById("steps_dash").innerHTML = "Steps = " + data.nbrSteps ;
    	},
        error: function(err) {
        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
        }
	});
	
	for (var i =0; i<totalSteps; i++) {
		//var timeoutID;
		/*$.ajax({
	        type:"GET",
	        url: "next",
	        dataType: "json",
	        success: function (data) {
	    		document.getElementById("title").innerHTML = "Step N# " + data.id + " Dashboard";
	    		document.getElementById("line_dash").innerHTML = "Line: " + parseFloat(data.line.line).toFixed(4) ;
    			document.getElementById("dec_dash").innerHTML = "Decreasing Line Factor: " + parseFloat(data.line.decreasingFactor).toFixed(4) ;
    			document.getElementById("loss_dash").innerHTML = "Loss : " + parseFloat(data.losses.loss).toFixed(4) ;
    			document.getElementById("global_dash").innerHTML = "Global Loss  " + parseFloat(data.losses.globalLoss).toFixed(4) ;
    			drawGraph(data.points);
    			//pointsObj = data.points;
	    	},
	        error: function(err) {
	      		console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
	        }
    	}).delay(500);*/
    	
  		nextAjax();
	}
}

function nextAjax() {
	//sleep();
	$.ajax({
        type:"GET",
        url: "next",
        dataType: "json",
        success: function (data) {
        	id = parseInt(data.id);
    		document.getElementById("title").innerHTML = "Step N# " + data.id + " Dashboard";
    		document.getElementById("line_dash").innerHTML = "Line: " + parseFloat(data.line.line).toFixed(4) ;
			document.getElementById("dec_dash").innerHTML = "Decreasing Line Factor: " + parseFloat(data.line.decreasingFactor).toFixed(4) ;
			document.getElementById("loss_dash").innerHTML = "Loss : " + parseFloat(data.losses.loss).toFixed(4) ;
			document.getElementById("global_dash").innerHTML = "Global Loss  " + parseFloat(data.losses.globalLoss).toFixed(4) ;
			
			jsonPoints = data.points;
			drawGraph(data.points);
			
			//pointsObj = data.points;
    	},
        error: function(err) {
      		console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
        }
	});
}
	
	
function drawGraph(pointsObj) {
	xTab = pointsObj[choiceX];
	yTab = pointsObj[choiceY];
	textTab = pointsObj.name;
	console.log("xTab : " + xTab);
	console.log("yTab : " + yTab);
	console.log("text : " + textTab);
	
	trace = {
		x: xTab,
        y: yTab,
        mode: 'markers',
        type: 'scatter',
        text: textTab,
        marker: { size: 4 }	
	};
	
	var data = [ trace ];
	
	var layout = {
        title:'GraphPlot'
    };
    
    Plotly.newPlot('myDiv', data, layout);
    
    myDiv.on('plotly_click', function(data){
	var pts = '';
	for(var i=0; i < data.points.length; i++){
		
		pts = pointsObj.name[xTab.indexOf(data.points[i].x)] + '\n x = '+data.points[i].x +'\ny = '+
		data.points[i].y.toPrecision(4) + '\n\n';
		applyAttrandSims2(pointsObj.name[xTab.indexOf(data.points[i].x)]) 
		
	}
	alert('Closest point clicked:\n\n'+pts);
	
	
	
	});
}

function refSelection() {
	if (flagRef == 0){
		var option;
		var sel = document.getElementById("ref_select");
		sel.options.length = 0;
		for (index = 0; index < textTab.length; ++index) {
            option = textTab[index];
            sel.options.add(new Option(option, option));
        }
		flagRef = 1;
	}
}


function applyAttrandSims2(int) {
	textTabWithRef = [];
	$.ajax({
	        type:"GET",
	        url: "getSimilarities",
	        dataType: "json",
	        data: {"simsReferrer":int},
	        success: function (data) {
	    		document.getElementById("simRef_dash").innerHTML = int ;
			simsObj = data.Similarities;	
			for (var i=0; i < textTab.length; i++){
				textTabWithRef[i] = textTab[i];
			}
			for (var obj in simsObj) {
				for (var i=0; i < textTab.length; i++){
					if (textTab[i]==obj){
						textTabWithRef[i] = textTab[i] + "  Similarity: " + simsObj[obj];
					}
				}
			}
	    	},
	        error: function(err) {
	        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
	        }
   	});
   	textTabWithRef2 = [];
   	tabSim = [];
   	$.ajax({
	        type:"GET",
	        url: "getAttractivities",
	        dataType: "json",
	        data: {"attrReferrer":int},
	        success: function (data) {
	    		document.getElementById("attrRef_dash").innerHTML = int ;
			attrObj = data.Attractivities;	
			for (var i=0; i < textTab.length; i++){
				textTabWithRef2[i] = textTab[i];
			}
			for (var obj in attrObj) {
				for (var i=0; i < textTab.length; i++){
					if (textTab[i]==obj){
						textTabWithRef2[i] = textTab[i] + "  Attractivity: " + attrObj[obj];
					}
					tabSim[i]=attrObj[i+1];
				}
			}
			
			//console.log("text : " + textTabWithRef);
			console.log(tabSim);
			if (tabSim === undefined || tabSim.length == 0){
				console.log('testttt')
				var c= 'blue';
			}else{
				var c = changeColor(tabSim);
			}
			drawWith2RefC(textTabWithRef,textTabWithRef2,c);
	    	},
	        error: function(err) {
	        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
	        }
		
   	});

}

function drawWith2RefC(tab,tab2,col){
	console.log(tab);
	console.log(tab2);
	console.log(col);
	
	text3 = new Array(tab.length);
	for ( i =0 ;i<tab2.length;i++){
	  if(tab[i]!=undefined){
	     if(tab2[i]!=undefined){
	      text3[i] = tab[i] +tab2[i];
	     }else{
	      text3[i] = tab[i];
	     }
	  }else{
	    if(tab2[i]!=undefined){
	      text3[i] = tab2[i];
	    }
		  }
	}
	console.log(text3) 
	
	
	traceRef = {
			x: xTab,
           y: yTab,
           mode: 'markers',
           type: 'scatter',
           text: text3,
           marker: { size: 4
           }	
		};
		
		var data = [ traceRef ];
		
		var layout = {
           title:'GraphPlot'
       };

       Plotly.newPlot('myDiv', data, layout);
       myDiv.on('plotly_click', function(data){
	var pts = '';
	for(var i=0; i < data.points.length; i++){
		
		pts = text3[xTab.indexOf(data.points[i].x)].charAt(0) + '\n x = '+data.points[i].x +'\ny = '+
		data.points[i].y.toPrecision(4) + '\n\n';
		applyAttrandSims2(text3[xTab.indexOf(data.points[i].x)].charAt(0)) ;
		
	};
	
	alert('Closest point clicked:\n\n'+pts);
	
				
	});
	console.log(col) 
	var update = {
		marker: { 
			color: col,
			size: 12
		}
	}
	Plotly.restyle(myDiv, update);
}

function changeColor(tab){
	col = [];
	for (var i = 0 ; i<tab.length ; i++ ){
		if(tab[i] == undefined){
			col[i] = 'blue'
		}else {
			if(tab[i] < 0){
				col[i] = 'red';
			}else{
				col[i] = 'green';
			} 
		}
	}
	console.log(tab);
	console.log(col);
	return col;
}



function drawWithRef(tab){
	console.log("text : " + tab);
	
		traceRef = {
			x: xTab,
           y: yTab,
           mode: 'markers',
           type: 'scatter',
           text: tab,
           marker: { size: 4 }	
		};
		
		var data = [ traceRef ];
		
		var layout = {
           title:'GraphPlot'
       };

       Plotly.newPlot('myDiv', data, layout);
       myDiv.on('plotly_click', function(data){
	var pts = '';
	for(var i=0; i < data.points.length; i++){
		
		pts = pointsObj.name[xTab.indexOf(data.points[i].x)].charAt(0) + '\n x = '+data.points[i].x +'\ny = '+
		data.points[i].y.toPrecision(4) + '\n\n';
		applyAttrandSims2(pointsObj.name[xTab.indexOf(data.points[i].x)].charAt(0)) 
		
	}
	alert('Closest point clicked:\n\n'+pts);			
	});
}

function applyLine() {
	console.log("Testttttt Lineeeeeee");
	console.log(parseFloat(document.getElementById("line_input").value));
	$.ajax({
        type:"GET",
        url: "setLine",
        dataType: "json",
        data: {"line":parseFloat(document.getElementById("line_input").value)},
        success: function (data) {
    		document.getElementById("line_dash").innerHTML = "Line :" + data.line;
    	},
        error: function(err) {
        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
        }
	});
}

function applyFactor() {
	$.ajax({
        type:"GET",
        url: "setDec",
        dataType: "json",
        data: {"decFactor":parseFloat(document.getElementById("factor_input").value)},
        success: function (data) {
    		document.getElementById("dec_dash").innerHTML = "Line decreasing factor " + data.decreasingFactor;
    	},
        error: function(err) {
        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
        }
	});
}

function changeX (elmnt) {
	choiceX = elmnt.val();
	drawGraph(jsonPoints);
}

function changeY (elmnt) {
	choiceY = elmnt.val();
	drawGraph(jsonPoints);
}

function previous () {
	var steps = parseInt(document.getElementById("nbr_steps_input").value);
	var path = id - steps;
	$.ajax({
        type:"GET",
        url: "load",
        dataType: "json",
        data: {"name":path.toString()},
        success: function (data) {
        	id = parseInt(data.id);
    		document.getElementById("title").innerHTML = "Step N# " + data.id + " Dashboard";
    		document.getElementById("line_dash").innerHTML = "Line: " + parseFloat(data.line.line).toFixed(4) ;
			document.getElementById("dec_dash").innerHTML = "Decreasing Line Factor: " + parseFloat(data.line.decreasingFactor).toFixed(4) ;
			document.getElementById("loss_dash").innerHTML = "Loss : " + parseFloat(data.losses.loss).toFixed(4) ;
			document.getElementById("global_dash").innerHTML = "Global Loss  " + parseFloat(data.losses.globalLoss).toFixed(4) ;
			
			jsonPoints = data.points;
			drawGraph(data.points);
    		
    	},
        error: function(err) {
        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
        }
	});
}

function next () {
	var steps = parseInt(document.getElementById("nbr_steps_input").value);
	var path = id + steps;
	$.ajax({
        type:"GET",
        url: "load",
        dataType: "json",
        data: {"name":path.toString()},
        success: function (data) {
        	id = parseInt(data.id);
    		document.getElementById("title").innerHTML = "Step N# " + data.id + " Dashboard";
    		document.getElementById("line_dash").innerHTML = "Line: " + parseFloat(data.line.line).toFixed(4) ;
			document.getElementById("dec_dash").innerHTML = "Decreasing Line Factor: " + parseFloat(data.line.decreasingFactor).toFixed(4) ;
			document.getElementById("loss_dash").innerHTML = "Loss : " + parseFloat(data.losses.loss).toFixed(4) ;
			document.getElementById("global_dash").innerHTML = "Global Loss  " + parseFloat(data.losses.globalLoss).toFixed(4) ;
			
			jsonPoints = data.points;
			drawGraph(data.points);
    		
    	},
        error: function(err) {
        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
        }
	});
}

function load () {
	var steps = parseInt(document.getElementById("nbr_steps_input").value);
	var path = steps;
	$.ajax({
        type:"GET",
        url: "load",
        dataType: "json",
        data: {"name":path.toString()},
        success: function (data) {
        	id = parseInt(data.id);
    		document.getElementById("title").innerHTML = "Step N# " + data.id + " Dashboard";
    		document.getElementById("line_dash").innerHTML = "Line: " + parseFloat(data.line.line).toFixed(4) ;
			document.getElementById("dec_dash").innerHTML = "Decreasing Line Factor: " + parseFloat(data.line.decreasingFactor).toFixed(4) ;
			document.getElementById("loss_dash").innerHTML = "Loss : " + parseFloat(data.losses.loss).toFixed(4) ;
			document.getElementById("global_dash").innerHTML = "Global Loss  " + parseFloat(data.losses.globalLoss).toFixed(4) ;
			
			jsonPoints = data.points;
			drawGraph(data.points);
    		
    	},
        error: function(err) {
        	console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
        }
	});
}

