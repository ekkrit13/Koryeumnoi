<style type="text/css">
* { margin:0; padding:0; font-size: 16px; }
div:after { clear:both; display:block; content:''; }
.input1 {
	width: 100%;
	border: 1px #ccc solid;
	border-radius: 8px;
	height: 48px;
	line-height: 48px;
	padding: 16px;
	box-sizing: border-box;
}
.error {
	text-align: center;
	color: #ff0000;
	padding: 24px;
	box-sizing: border-box;
}
.number {
	font-family: Arial;
	font-weight: bold;
	font-size: 48px;
}
.number2 {
	font-size: 36px;
	font-family: Arial;
	font-weight: bold;
}
label {
	font-weight: bold;
	margin: 16px 0;
	display: block;
}
button {
	border: 0 none;
	width: 100%;
	text-align: center;
	background: #1baf96;
	color: #fff;
	border-radius: 8px;
	height: 48px;
	padding: 16px;
	box-sizing: border-box;
	cursor: pointer;
}
h1 {
	font-size: 48px;
}
a {
	text-decoration: none;
	padding: 0 16px;
}
a:hover {
	opacity: 0.8;
}
</style>

<div style="background: #000; height: 64px; line-height: 64px; color: #fff;">
<div style="width: 960px; margin: auto;">
<span style="float: left;">KorYuemNoi ระบบจัดการหลังบ้านแอปพลิเคชัน</span>
<span style="float: right;">
<?php 
if(isset($_SESSION['admin_id_s'])) {
	echo '<a href="'.$dir.'/list.php" style="color: #fff;">รายการหนังสือ</a><a href="'.$dir.'/add.php" style="color: #fff;">เพิ่มหนังสือใหม่</a><a href="'.$dir.'/dashboard.php" style="color: #fff;">บันทึกการยืม</a><a href="'.$dir.'/logout.php" style="color: #fff;">ออกจากระบบ</a>';
} else {
	echo '<a href="'.$dir.'/login.php" style="color: #fff;">เข้าสู่ระบบ</a>';
}
?>
</span>
</div>
</div>