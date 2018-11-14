<?php
try {
	require 'DB_Manager.php';
	
	$id = $_REQUEST["_id"];
	$newKilometers = $_REQUEST["newKilometers"];
	
	$sql = "UPDATE Car_Table SET kilometers = '$newKilometers' WHERE _id = '$id'";
	
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