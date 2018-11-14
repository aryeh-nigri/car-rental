<?php
try {
	require 'DB_Manager.php';

	$id = $_REQUEST["_id"];
	$branchId = $_REQUEST["branchId"];
	$modelId = $_REQUEST["modelId"];
	$kilometers = $_REQUEST["kilometers"];
	$modelPicture = $_REQUEST["modelPicture"];
	
	$sql = "INSERT INTO Car_Table( _id, branchId, modelId, kilometers, modelPicture) VALUES ('$id', '$branchId', '$modelId', '$kilometers', '$modelPicture')";
	
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