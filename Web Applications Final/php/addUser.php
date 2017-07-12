

<?php

require 'DBConnect.php';


//if(isset($_POST['stats']){
	
	// SQL connection validation
	if ($mysqli->connect_errno) {
	  printf("Connect failed: %s\n", $mysqli->connect_error);
	  exit();
	}	
	
	// Defines variables 
	//$stats = $_POST['stats']; 
	$stats = "Dan,0,1"; 
	
	// Protects data from hacking
	$stats = htmlspecialchars($stats); 
	$stats = $mysqli->real_escape_string($stats); 

	
	$fullStats = explode(",", $stats); 
	$name = $fullStats[0]; 
	$won = $fullStats[1];
	$losses = $fullStats[2];
	
	echo $name.$won.$losses; 
	
/*	
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
}*/

?>