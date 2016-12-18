<?php
require_once('./config.php');

$sql_status = TRUE;
$system_msg = '';

if(isset($_POST['isbn'])) { $isbn = $_POST['isbn']; } else { $isbn = ''; }
if(isset($_POST['title'])) { $title = $_POST['title']; } else { $title = ''; }
if(isset($_POST['type'])) { $type = $_POST['type']; } else { $type = ''; }
if(isset($_POST['detail'])) { $detail = $_POST['detail']; } else { $detail = ''; }
if(isset($_FILES['file'])) {
	$uniqid = uniqid();
	$file = $_FILES["file"]["tmp_name"];
	$filepath = pathinfo($_FILES["file"]["name"]);
	$filesize = $_FILES["file"]["size"];
	$filetype = $filepath["extension"];
	$filenewname = $uniqid.".".$filetype;
} else { 
	$file = '';
}

if(isset($_POST['submit'])) {

	if($file=='') {
		$system_msg = '<p class="error">กรุณาเลือกไฟล์ปกหนังสือ</p>';
		$sql_status = FALSE;
	}

	if($isbn!='' AND $title!='' AND $type!='' AND $detail!='') {

	if($filetype!="gif" AND $filetype!="jpg" AND $filetype!="jpeg" AND $filetype!="png") {
		$system_msg = '<p class="error">กรุณาเลือกไฟล์ GIF/JPG/PNG เท่านั้น</p>';
		$sql_status = FALSE;
	}
	if($filesize>5000000) {
		$system_msg = '<p class="error">ไฟล์ภาพมีขนาดใหญ่เกิน 5 MB</p>';
		$sql_status = FALSE;
	}

	if($sql_status==TRUE) {

	$isbn = htmlspecialchars($isbn);
	$title = htmlspecialchars($title);
	$detail = htmlspecialchars($detail);

	$rows = $mysqli -> query("INSERT INTO book (isbn,title,type,detail,cover,created_at) VALUES ('$isbn','$title','$type','$detail','$filenewname',now())");
		if($rows == TRUE) {
			move_uploaded_file($file,"../cover/".$filenewname);
			header('location: '.$dir.'/list.php'); exit();
		}
		else {
			$system_msg = '<p class="error">ไม่สามารถบันทึกข้อมูลได้</p>';
		}
	}
	} else {
		$system_msg = '<p class="error">กรุณากรอกข้อมูลให้ครบถ้วน</p>';
	}

}
?>


<!DOCTYPE html>
<html>
<head>
	<title>เพิ่มหนังสือใหม่</title>
</head>
<body>
<?php include('./header.php'); ?>

<form action="" method="post" enctype="multipart/form-data">
<div style="width: 960px; margin: auto; padding-top: 48px; padding-bottom: 48px;">
	<h1 style="padding-top: 16px;">เพิ่มหนังสือใหม่</h1>
	<?php echo $system_msg; ?>
	<div style="margin-top:24px; background: #f6f6f6; padding: 24px 48px; box-sizing: border-box;">
	<p style="padding-top: 8px;"><label>ภาพปกหนังสือ</label><input type="file" name="file" accept="image/jpeg,image/gif,image/png"></p>
	<p style="padding-top: 8px;"><label>ISBN</label><input type="text" name="isbn" placeholder="กรอกรหัส ISBN" class="input1"></p>
	<p style="padding-top: 8px;"><label>ชื่อหนังสือ</label><input type="text" name="title" placeholder="กรอกชื่อหนังสือ" class="input1"></p>
	<p style="padding-top: 8px;"><label>ประเภทหนังสือ</label>
	<select name="type" class="input1" style="height: auto;">
	<?php
	$rows = $mysqli -> query("SELECT * FROM type ORDER BY type_id ASC");
	$num_rows = mysqli_num_rows($rows);

	foreach ($rows as $row) {
	$type_id = $row['type_id'];
	$name = $row['name'];

	echo'<option value="'.$type_id.'">'.$name.'</option>';
	}
	?>
	</select>
	</p>
	<p style="padding-top: 8px;"><label>รายละเอียดหนังสือ</label><textarea type="text" name="detail" placeholder="กรอกรายละเอียดหนังสือ" class="input1" rows="4" style="height: auto;"></textarea></p>
	<p style="padding-top: 24px;"><button name="submit" type="submit">เพิ่มหนังสือ</button></p>
	</div>
</div>
</form>

</body>
</html>