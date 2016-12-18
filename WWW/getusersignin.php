<?php

	require_once('admin/config.php');

	if(!empty($_POST['login_username'])) {
		$login_username = mysqli_escape_string($mysqli,$_POST['login_username']);
	}

	if(!empty($_POST['login_password'])) {
		$login_password = mysqli_escape_string($mysqli,$_POST['login_password']);
	}

	$sql = "SELECT * FROM `member` WHERE student_id = '$login_username' AND password = '$login_password'";

	$result = mysqli_query($mysqli,$sql,MYSQLI_USE_RESULT);

	$row = mysqli_fetch_assoc($result);

	echo json_encode($row);
	
?>