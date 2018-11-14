<?php
try {
	require 'DB_Manager.php';
	
	$id = $_REQUEST["_id"];
	$password = $_REQUEST["password"];
	
	$sql = "UPDATE Client_Table SET password = '$password' WHERE _id = '$id'";
	
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