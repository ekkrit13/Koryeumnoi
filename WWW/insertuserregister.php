<?php

	require_once('admin/config.php');

	if(!empty($_POST['register_username'])) {
		$register_username = mysqli_escape_string($mysqli,$_POST['register_username']);
	}
	if(!empty($_POST['register_password'])) {
		$register_password = mysqli_escape_string($mysqli,$_POST['register_password']);
	}

	$sql = "INSERT INTO member (student_id,password,role,created_at) VALUES ('$register_username','$register_password','member',now())";

	$result = mysqli_query($mysqli,$sql,MYSQLI_USE_RESULT);
	echo json_encode($result, JSON_UNESCAPED_UNICODE);
	
?>