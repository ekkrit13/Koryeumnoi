<?php

	require_once('admin/config.php');

	if(!empty($_POST['isbn_number'])) {
		$isbn_number = mysqli_escape_string($mysqli,$_POST['isbn_number']);
	}

	$sql = "SELECT * FROM `book` JOIN type ON type.type_id = book.type WHERE isbn = '$isbn_number'";

	$result = mysqli_query($mysqli,$sql,MYSQLI_USE_RESULT);
	$row = mysqli_fetch_assoc($result);
	echo json_encode($row, JSON_UNESCAPED_UNICODE);
	
?>