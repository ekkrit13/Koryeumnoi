<?php

	require_once('admin/config.php');

	if(!empty($_POST['member_id'])) {
		$member_id = mysqli_escape_string($mysqli,$_POST['member_id']);
	}

	$sql = "SELECT * FROM `member` WHERE member_id = '$member_id'";

	$result = mysqli_query($mysqli,$sql,MYSQLI_USE_RESULT);
	$row = mysqli_fetch_assoc($result);

	echo json_encode($row, JSON_UNESCAPED_UNICODE);
	
?>