<?php
require_once('./config.php');

if(isset($_SESSION['admin_id_s'])) {
	header('location: '.$dir.'/dashboard.php'); exit();
} else {
	header('location: '.$dir.'/login.php'); exit();
}
?>