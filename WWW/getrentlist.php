<?php

	require_once('admin/config.php');

	if(!empty($_POST['member_id'])) {
		$member_id = mysqli_escape_string($mysqli,$_POST['member_id']);
	}

	$sql = "SELECT * FROM `rentlist` JOIN member ON member.member_id = rentlist.member_id JOIN book ON book.book_id = rentlist.book_id JOIN type ON type.type_id = book.type WHERE rentlist.member_id = '$member_id' AND rentlist.status = '0' ORDER BY rentlist.rent_id DESC";

	$result = mysqli_query($mysqli,$sql,MYSQLI_USE_RESULT);

	$data = array();
	while ($row = mysqli_fetch_assoc($result)) {
     	$data[] = $row;
	}

	echo json_encode($data, JSON_UNESCAPED_UNICODE);
	
?>