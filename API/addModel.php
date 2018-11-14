<?php
try {
    require 'DB_Manager.php';
    
    $id = $_REQUEST["_id"];
    $companyName = $_REQUEST["companyName"];
    $modelName = $_REQUEST["modelName"];
    $tankVolume = $_REQUEST["tankVolume"];
    $gearbox = $_REQUEST["gearbox"];
    $seats = $_REQUEST["seats"];
	$pictureURL = $_REQUEST["pictureURL"];
	
    $sql = "INSERT INTO Model_Table(_id, companyName, modelName, tankVolume, gearbox, seats, pictureURL) VALUES ('$id', '$companyName', '$modelName', '$tankVolume', '$gearbox', '$seats', '$pictureURL')";
    
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