<?php
try {
	require 'DB_Manager.php';
	
	$id = $_REQUEST["_id"];
	$lastName = $_REQUEST["lastName"];
	$firstName = $_REQUEST["firstName"];
	$telephone = $_REQUEST["telephone"];
	$email = $_REQUEST["email"];
	$creditCard = $_REQUEST["creditCard"];
	
	$sql = "INSERT INTO Client_Table(_id, lastName, firstName, telephone, email, creditCard) VALUES ('$id', '$lastName', '$firstName', '$telephone', '$email', '$creditCard')";
	
	if ($conn->query($sql) === TRUE) {
		echo $id;
	}
	else {
		echo "Error: " . $sql . "\n" . $conn->error;
	}
}
catch(Exception $e) {
	echo "Exception Error See Log....";
	error_log($e->getMessage() , 0);
}

$conn->close();
?>