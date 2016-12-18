<?php
require_once('./config.php');

if(isset($_SESSION['admin_id_s'])) {
	header('location: '.$dir.'/dashboard.php'); exit();
}

$sql_status = TRUE;
$system_msg = '';

if(isset($_POST['student_id'])) { $student_id = $_POST['student_id']; } else { $student_id = ''; }
if(isset($_POST['password'])) { $password = $_POST['password']; } else { $password = ''; }

if(isset($_POST['submit'])) {

	if($student_id!='' AND $password!='') {

	$rows = $mysqli -> query("SELECT * FROM member WHERE role = 'admin'");
	$num_rows = mysqli_num_rows($rows);

	foreach ($rows as $row) {
	$member_student_id = $row['student_id'];
	$member_password = $row['password'];
	}

	if($member_student_id!=$student_id OR $member_password!=$password) {
		$sql_status = FALSE;
		$system_msg = '<p class="error">ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง</p>';
	} else {
		$_SESSION['admin_id_s'] = uniqid();
		header('location: '.$dir.'/dashboard.php'); exit();
	}
	} else {
		$system_msg = '<p class="error">กรุณากรอกข้อมูลให้ครบถ้วน</p>';
	}

}
?>


<!DOCTYPE html>
<html>
<head>
	<title>เข้าสู่ระบบหลังบ้าน</title>
</head>
<body>
<?php include('./header.php'); ?>

<form action="" method="post" style="margin-top: 100px;">
<div style="width: 960px; margin: auto;">
	<h1 style="text-align: center;">เข้าสู่ระบบหลังบ้าน</h1>
	<?php echo $system_msg; ?>
	<p style="padding-top: 48px;"><input type="text" name="student_id" placeholder="กรอกชื่อผู้ใช้" class="input1"></p>
	<p style="padding-top: 8px;"><input type="password" name="password" placeholder="กรอกรหัสผ่าน" class="input1"></p>
	<p style="padding-top: 24px;"><button name="submit" type="submit">เข้าสู่ระบบ</button></p>
</div>
</form>

</body>
</html>