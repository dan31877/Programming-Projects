	
	
function redirect (name) { 
	window.location.href = name; 
}

/* Validates the form is filled our completely. */	
function validateForm()
{
	// Resets the form
	$("label").css({"color":"black", "font-weight":"normal"});
	$("#hidden").css("display", "none");
 
	// Define variables, run functions for each variable.	
	var alertMessage = "Validation on this form has failed."; 
	var w=document.forms["myForm"]["fname"].value;
	var isFirst = validate(w, "#fname");
	var x=document.forms["myForm"]["lname"].value;
	var isLast = validate(x, "#lname");
	var y=document.forms["myForm"]["email"].value;
	var isEmail = validateEmail(y);
	var z=document.forms["myForm"]["comments"].value;
	var isComments = validate(z, "#comments");
	
	
	// When validation fails, changes the color of the labels, shows a hidden <p>, and 
	// directs the page to the bottom of the form
	if( !isFirst || !isLast || !isEmail || !isComments ){
		alert(alertMessage);
		$("#hidden").css("display", "block");
		redirect("#form");

	// When validation succeeds, submits the form.
	}else {
		document.getElementById("myForm").submit(); 	
	}	
	
}	
	
/* Validates that other fields are filled out. */	
function validate(x, id) {
	if (x==null || x=="")
		{
		$(id).css({"color":"red", "font-weight":"bold"});
		return false;
		}
	else{ 
		return true;
	}	
}

/* Validates the email entry. */
function validateEmail(x) {
	var atpos=x.indexOf("@");
	var dotpos=x.lastIndexOf(".");
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
	{
		$("#email").css({"color":"red", "font-weight":"bold"});
		return false;
	}else{ 
		return true;
	}	

}

/* Ajax function to verify the ID */
function ajaxVerify(){

	var value = document.forms["ajaxform"]["id"].value;  
	//document.write(data);
	

   $.ajax({
      url: "../php/ajaxverify.php",
      type: "post",
      data: {id: value},
      success: function(result){
          //alert( result);
          $("#ajaxresult").html(result); 
          redirect('#ajaxresults');
          //document.getElementById("ajaxresult").innerHTML=result;
      },
      error:function(){
          alert("failure");
          $("#ajaxresult").html('There is error while submit');
          redirect('#ajaxresults');
      }   
   }); 
} 
