<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta name="Author"; content="Dan Anderson" />
<link rel="shortcut icon" href="../nashicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="../main.css" type="text/css"/>
<link rel="stylesheet" href="../menu.css" type="text/css"/>
<link rel="stylesheet" href="../Form.css" type="text/css"/>


<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../js/jsHelper.js" ></script>
<script type="text/javascript" src="../js/Form.js"></script>

<title>Form</title>
</head>

<body>
	
	<!-- HTML for the site header and menu  -->
	<h1 class="header"><a name="top">Nash Parent Council<img src="../nashcat.jpg" alt="Wildcat" class="floatright" width="70" height="70" /></a></h1>
	<div class="menu1">
	<div class="menu borderright"><a href="../main.html">Main</a>
			<ul class="dropdown">
			<li><a href="../main.html#mission">&nbsp;Mission Statement</a></li></ul></div>
	<div class="menu borderright"><a href="../newsletter.html">Newsletter</a></div>						
	<div class="menu borderright"><a href="../calendar.html">Calendar</a></div>
	<div class="menu borderright"><a href="Form.php">Contact Us</a>
			<ul class="dropdown">
			<li><a href="Form.php#contacts">&nbsp;Contacts</a></li>
			<li><a href="Form.php#form">&nbsp;Leave a Comment</a></li></ul></div>

	
	</div>
		
	<!-- Links Menu  -->
	<div><p class="menu2"><a href="https://www.facebook.com/nash.parentcouncil?fref=ts">
		<img src="../facebook.jpg" alt="Check out our Facebook page!" 
	width="20" height="20" title="Check out our Facebook page!"/></a>&nbsp;&nbsp;
	<a href="mailto:dan31877@bu.edu">
		<img src="../mail.jpg" alt="Send us an email" 
	width="25" height="20" title="Send us an email"/></a>&nbsp;&nbsp;
	<a href="http://www.weymouthschools.org/nash.aspx">
		<img src="../nash.jpg" alt="Official Nash School Page" 
	width="20" height="20" title="Official Nash School Page"/></a></p></div><br></br>
	
	<div class="links"><h2>Links:</h2>
	<p><a href="https://www.facebook.com/nash.parentcouncil?fref=ts">Facebook</a></p>
	<p><a href="http://www.weymouthschools.org/nash.aspx">Official Nash Page</a></p>
	</div>

	<br></br>
	
	<!-- Div for the remainder of the content  -->
	<div class="maincontent"><br></br><br></br>
	
	<!-- Contacts Table -->
	<h2><a name="contacts">Contacts: </a></h2>
	<div>
		<table border="0">
			<tr><td>Person 1<br />Address 1<br /></td><td>Person 2<br />Address 2<br /></td></tr>
			<tr><td>Person 3<br />Address 3<br /></td><td>Person 4<br />Address 4<br /></td></tr>
			<tr><td>Person 5<br />Address 5<br /></td><td>Person 6<br />Address 6<br /></td></tr>
		
		</table>
	</div><br></br><br></br>
	
	<!-- First Form, all fields are required. -->
	<a name="form"></a>
	<h2>Leave a comment for the Parent Council</h2> 
	<a href="#top">Back to top</a>
	
	<ul id="labelFormat">
		<li>Please fill in all the fields to insert a record.</li>
	</ul>
	 
	<form id="myForm" name="myForm" action="Form.php" method="post">
	 
	 <p><label id="fname">First Name: <input name="fname" type="text" size="25" /></label></p>
	 <p><label id="lname">Last Name: <input name="lname" type="text" size="25" /></label></p>
	 <p><label id="email">Email Address: <input name="email" type="text" size="25" /></label></p>


	 <p><label id="comments">Please leave a comment:<br></br>
	 	<textarea name="comments" rows="5" cols="35"></textarea>
	 </label></p>
	 
	 <input type="button" name="insert" value="Insert" onclick="validateForm();" />
	 <p id="hidden">Please fill in or correct the highlighted fields. </p><br></br>

</form>


<?php

/* PHP designed based on:	
	http://m.youtube.com/watch?feature=plpp&v=ZMLkF1vmZdE
	http://m.youtube.com/watch?feature=plpp&v=msCY6evKpNI
*/

require 'DBConnect.php';

// Calls the insert method if the insert button is pressed.
if(insertCheck()){
	insert($mysqli);
	echo "<script type=\"text/javascript\">redirect('#form');</script>";
}

function insert($mysqli){
	
	// SQL connection validation
	if ($mysqli->connect_errno) {
	  printf("Connect failed: %s\n", $mysqli->connect_error);
	  exit();
	}	
	
	// Defines variables 
	$fname = $_POST['fname']; 
	$lname = $_POST['lname']; 
	$email = $_POST['email']; 
	$comments = $_POST['comments'];
	
	// Protects data from hacking
	$fname = htmlspecialchars($fname); 
	$lname = htmlspecialchars($lname); 
	$email = htmlspecialchars($email); 
	$comments = htmlspecialchars($comments);
	
	$fname = $mysqli->real_escape_string($fname); 
	$lname = $mysqli->real_escape_string($lname); 
	$email = $mysqli->real_escape_string($email); 
	$comments = $mysqli->real_escape_string($comments);

	
 
	//echo "Form vars: $fname $lname $email $comments";
	
	// Runs insert query with all the fields. Second query delivers the ID. 
	$query="INSERT INTO `nash_database`.`nash_comments`". 
	"(`id`, `first_name`, `last_name`, `email`, `comments`) VALUES (NULL, '$fname', '$lname', '$email', '$comments');"; 
	$query2 = "SELECT `id` FROM `nash_database`.`nash_comments` WHERE `first_name` = '$fname' AND `last_name` = '$lname' AND `email` = '$email' AND `comments` = '$comments'";
	
	// Runs the first query, displays error or success message		
	if ($query_run = $mysqli->query($query)){ 
		echo "Row inserted successfully. ";
	} else{ 
		echo $mysqli->error;
	}
	
	// Runs the second query, displays error or success message with ID
	if ($result = $mysqli->query($query2)){ 
		$row = $result->fetch_assoc();
		$id = $row["id"];
		echo "Your ID is $id.";
	} else{ 
		echo $mysqli->error;
	}
				//} else { 
			//echo "Please fill in the required fields.";
			//}
			
	//}
	
	$mysqli->close();
}

// Checks if insert was pressed and if the fields are populated.
function insertCheck(){
	if (isset($_POST['fname']) && isset($_POST['lname']) && isset($_POST['email']) && isset($_POST['comments']) && 
		!empty($_POST['fname']) && !empty($_POST['lname']) && !empty($_POST['email']) && !empty($_POST['comments'])){
				return true;  
			}else{
				return false; 
			}	
			
}	

?>

</div>	 

	<div class="footer"><br></br><br></br><br></br><br></br>
	<p>This site is designed for the Nash Parent Council</p></div>


</body>

</html>
