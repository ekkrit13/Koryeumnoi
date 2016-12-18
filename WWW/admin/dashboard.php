<?php
require_once('./config.php');

$sql_status = TRUE;
$system_msg = '';

if(isset($_POST['student_id'])) { $student_id = $_POST['student_id']; } else { $student_id = ''; }
if(isset($_POST['password'])) { $password = $_POST['password']; } else { $password = ''; }

if(isset($_POST['confirm'])) {

if(isset($_POST['rent_id'])) { $rent_id = $_POST['rent_id']; } else { $rent_id = ''; }

	$sendback_date = date("Y-m-d");
	$rows = $mysqli -> query("UPDATE rentlist SET status = '1',sendback_date = '$sendback_date' WHERE rent_id = '$rent_id'");
}
?>


<!DOCTYPE html>
<html>
<head>
	<title>บันทึกการยืม</title>
</head>
<body>
<?php include('./header.php'); ?>

<div style="width: 960px; margin: auto; padding-top: 48px;">

	<h1 style="padding-top: 16px;">บันทึกการยืม</h1>
	<div style="margin-top:24px; height: 40px; background: #f6f6f6; line-height: 40px;">
		<div style="float: left; width: 100px; padding-left: 16px;">ลำดับ</div>
		<div style="float: left; width: 100px;">ผู้ยืม</div>
		<div style="float: left; width: 240px;">หนังสือ</div>
		<div style="float: left; width: 200px;">กำหนดส่ง/วันที่ยืม</div>
		<div style="float: left; width: 100px;">ค่าปรับ</div>
		<div style="float: left;">สถานะ</div>
	</div>
<?php
	$rows = $mysqli -> query("SELECT * FROM book JOIN rentlist ON rentlist.book_id = book.book_id JOIN type ON type.type_id = book.type JOIN member ON member.member_id = rentlist.member_id JOIN fine ON fine.member_id = member.member_id ORDER BY rentlist.rent_id DESC");
	$num_rows = mysqli_num_rows($rows);

	foreach ($rows as $row) {
	$book_id = $row['book_id'];	
	$isbn = $row['isbn'];
	$title = $row['title'];
	$cover = $row['cover'];
	$name = $row['name'];
	$student_id = $row['student_id'];
	$rent_id = $row['rent_id'];
	$rent_date = $row['rent_date'];
	$deadline_date = $row['deadline_date'];
	$sendback_date = $row['sendback_date'];
	$status = $row['status'];
	$fine = $row['fine'];


?>
		<div style="margin-top: 16px; padding-bottom:16px; border-bottom: 1px #d5d5d5 solid;">
		<div style="float: left; width: 100px; padding-left: 16px;"><?php echo $rent_id; ?></div>
		<div style="float: left; width: 100px;"><?php echo $student_id; ?></div>
		<div style="float: left; width: 240px;"><p style="font-weight: bold; padding-bottom: 8px;"><?php echo $title; ?></p></div>
		<div style="float: left; width: 200px;"><p style="font-size: 10px;">กำหนดส่ง: <?php echo $deadline_date; ?><br>วันที่ยืม: <?php echo $rent_date; ?></p></div>
		<div style="float: left; width: 100px;"><?php echo $fine; ?> บาท</div>
		<div style="float: left; width: 100px;"><?php if($status==0) { echo'ยังไม่ส่งคืน'; } else { echo'ส่งคืนแล้ว<p style="font-size: 10px">'.$sendback_date.'</p>'; } ?></div>
		<div style="float: left;">
		<?php if($status==0) {
			?>
				<form action="" method="post">
				<input name="rent_id" type="hidden" value="<?php echo $rent_id; ?>">
				<button name="confirm" type="submit">ส่งแล้ว</button>
			</form>
			<?php
		} ?>
		</div>
		</div>
<?php

	}
?>
	
</div>

</body>
</html>