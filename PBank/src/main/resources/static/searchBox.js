let count = 6;

var dropDown = document.getElementById("dropDown");

var searchTxt = document.getElementById("searchTxt");

function autoComplete(){ 
	console.log("ping");
    while(dropDown.firstChild){
        dropDown.removeChild(dropDown.firstChild);
    }

	fetch("/api/people/autoComplete?substr=" + searchTxt.value + "&count=" + count).then((response) => {
		return response.json();
	    
	})
	.then((json) => {	
	    for (let i = 0; i < Math.min(json.length, count); i++) {
	    	if(json[i] == searchTxt.value) continue;
	        var button = document.createElement("button");
	        var node = document.createTextNode(json[i]);
            button.appendChild(node);
	        button.onclick = function(){
	    	   searchTxt.value = this.innerHTML;
	    	   autoComplete();
	        }
	        dropDown.appendChild(button);
	    }
	});
}

$("#searchTxt").on("input", autoComplete);


document.getElementById("searchTxt").onfocus = function(){
    autoComplete();
}