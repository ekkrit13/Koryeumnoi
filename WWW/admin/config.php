<?php
session_start();

$host = "localhost";
$root = "root";
$passwords = "";
$dbname = "android";
$dir = "http://localhost/koryuemnoi/admin";
$dir_root = "http://localhost/koryuemnoi";

$mysqli = new mysqli($host,$root,$passwords,$dbname);
$mysqli -> set_charset('utf8');
if (mysqli_connect_errno()) {
	echo "Mysqli Connect was not estabished".mysqli_connect_errno();
}

?>