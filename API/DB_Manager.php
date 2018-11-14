<?php

header('Content-Type: text/html; charset=utf-8');

//TODO check this information properly
$servername = "localhost";   //   because the php file is in the same server as the database
$username = "manager";
$password = "Android5778";
$dbname = "RentalCar_DB";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Set utf8 to handle hebrew
//mysql_query("SET NAMES 'utf8'", $conn);

// Check connection
if ($conn->connect_error) {
	die("Connection failed: " . $conn->connect_error);
}
?>