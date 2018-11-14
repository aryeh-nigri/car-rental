<?php
try {
	require 'DB_Manager.php';
	
	$id = $_REQUEST["_id"];
	$isOpen = $_REQUEST["isOpen"];
	$carId = $_REQUEST["carId"];
	$startDate = $_REQUEST["startDate"];
	$endDate = $_REQUEST["endDate"];
	$startKilometers = $_REQUEST["startKilometers"];
	$endKilometers = $_REQUEST["endKilometers"];
	$didFilledTank = $_REQUEST["didFilledTank"];
	$littersFilled = $_REQUEST["littersFilled"];
	$clientId = $_REQUEST["clientId"];
	$finalAmountForBilling = $_REQUEST["finalAmountForBilling"];
	
	$sql = "INSERT INTO Reservation_Table(_id, isOpen, carId, startDate, endDate, startKilometers, endKilometers, didFilledTank, littersFilled, clientId, finalAmountForBilling) 
	VALUES ('$id', '$isOpen', '$carId', '$startDate', '$endDate', '$startKilometers', '$endKilometers', '$didFilledTank', '$littersFilled', '$clientId', '$finalAmountForBilling')";
	
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