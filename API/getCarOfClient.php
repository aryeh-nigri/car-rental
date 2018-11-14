<?php
try {
	require 'DB_Manager.php';
	
	$clientId = $_REQUEST["clientId"];
	
	$sql = "SELECT * FROM Car_Table WHERE _id IN (SELECT carId FROM Reservation_Table WHERE clientId = '$clientId')";
	
	$result = $conn->query($sql);
	$data = array();
	
	if($result->num_rows == 1) {
		// output data of each row
		while($row = $result->fetch_assoc()) {
			array_push($data, $row);
		}
		
		echo json_encode(array('car' => $data));
	}
	else {
		echo "0 results";
	}
}
catch(Exception $e) {
	echo "Exception Error See Log....";
	error_log($e->getMessage() , 0);
}

$conn->close();
?>