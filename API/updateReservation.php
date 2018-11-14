<?php
try {
	require 'DB_Manager.php';
	
	$id = $_REQUEST["_id"];
	$isOpen = $_REQUEST["isOpen"];
	$endDate = $_REQUEST["endDate"];
	$endKilometers = $_REQUEST["endKilometers"];
	$didFilledTank = $_REQUEST["didFilledTank"];
	$littersFilled = $_REQUEST["littersFilled"];
	$finalAmountForBilling = $_REQUEST["finalAmountForBilling"];
	
	$sql = "UPDATE Reservation_Table SET isOpen = '$isOpen', endDate = '$endDate', endKilometers = '$endKilometers', didFilledTank = '$didFilledTank', littersFilled = '$littersFilled', finalAmountForBilling = '$finalAmountForBilling' WHERE _id = '$id'";
	
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