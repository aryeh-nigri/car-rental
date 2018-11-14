<?php
try {
    require 'DB_Manager.php';
    
    $id = $_REQUEST["_id"];
    $city = $_REQUEST["city"];
    $street = $_REQUEST["street"];
    $number = $_REQUEST["number"];
    $parkingSpace = $_REQUEST["parkingSpace"];
	
    $sql = "INSERT INTO Branch_Table(_id, city, street, number, parkingSpace) VALUES ('$id', '$city', '$street', '$number', '$parkingSpace')";
    
    if ($conn->query($sql) === TRUE) {
        echo $id;
    } else {
        echo "Error: " . $sql . "\n" . $conn->error;
    }
    
}
catch (Exception $e) {
	echo "Exception Error See Log....";
    error_log($e->getMessage(), 0);
}
$conn->close();
?>