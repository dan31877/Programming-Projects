<?php

	/* PHP designed based on:	
	http://m.youtube.com/watch?feature=plpp&v=ZMLkF1vmZdE
	http://m.youtube.com/watch?feature=plpp&v=msCY6evKpNI
	*/
	require 'DBConnect.php';

	// SQL connection validation
	if ($mysqli->connect_errno) {
	  printf("Connect failed: %s\n", $mysqli->connect_error);
	  exit();
	}
	
	
// Verifies the ID field is posted
if (isset($_POST['id'])){ 

	// Defines variables 
	$id = $_POST['id']; 
	
	// Protects data from hacking
	$id = $mysqli->real_escape_string($id);
	//echo "ID: $id ";
	 
	// Makes sure the id isn't mepty 
	if(!empty($id)){ 
	
		// Query selects all the id's	
		$query="SELECT `id` FROM `nash_database`.`nash_comments`"; 
		$verified = false;
			
		// Query runs and compares the id to entries in the database	
		if ($query_run = $mysqli->query($query)){
				
			if ($query_run->num_rows == 0){
				echo "No rows found.";
			}else{
					
				while( !$verified && $row = $query_run->fetch_assoc()){
					$idDB = $row["id"];	
					
					if( $idDB == $id){
						$verified = true; 
					}
								
				}
				
				// Prints message based on whether id is found
				if($verified){ 
					echo "ID $id is in the database."; 
				} else { 
					echo "ID $id is not in the database."; 
				}
		  	} 
		  } else{ 
				echo $mysqli->error;
			}	
			 	
	$mysqli->close();
	}
	
	// Prints if nothing is entered
	else { 
		echo "No ID was entered."; 
	}
}	
	
?> 
