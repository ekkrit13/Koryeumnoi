<?php

	require_once('admin/config.php');

	if(!empty($_POST['book_id'])) {
		$book_id = mysqli_escape_string($mysqli,$_POST['book_id']);
	}
	if(!empty($_POST['member_id'])) {
		$member_id = mysqli_escape_string($mysqli,$_POST['member_id']);
	}

	$rent_date = date("Y-m-d");
	$deadline_date = date("Y-m-d", strtotime("+1 week"));;

	$sql = "INSERT INTO `rentlist` (book_id,member_id,rent_date,deadline_date) VALUES ('$book_id','$member_id','$rent_date','$deadline_date')";

	$result = mysqli_query($mysqli,$sql,MYSQLI_USE_RESULT);
	echo json_encode($result, JSON_UNESCAPED_UNICODE);
	
?>