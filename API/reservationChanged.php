<?php
try {
	require 'DB_Manager.php';
	
	$affected_rows = $conn->affected_rows;
	
	echo $affected_rows;
	// >0 is ok
	// 0 is not changed
	// -1 is error
	
	/*
	if($affected_rows > 0){
		echo $affected_rows;
	}
	else{
		echo "0";
	}
	*/
	
}
catch(Exception $e) {
	echo "Exception Error See Log....";
	error_log($e->getMessage() , 0);
}

$conn->close();
?>