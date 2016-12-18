<?php
require_once('./config.php');

$sql_status = TRUE;
$system_msg = '';

if(isset($_POST['student_id'])) { $student_id = $_POST['student_id']; } else { $student_id = ''; }
if(isset($_POST['password'])) { $password = $_POST['password']; } else { $password = ''; }

if(isset($_POST['submit'])) {


}
?>


<!DOCTYPE html>
<html>
<head>
	<title>รายการหนังสือ</title>
</head>
<body>
<?php include('./header.php'); ?>

<div style="width: 960px; margin: auto; padding-top: 48px;">
	<div>
	<h1 style="padding-top: 16px;">รายการหนังสือ</h1>

<?php
	$rows = $mysqli -> query("SELECT * FROM book JOIN type ON book.type = type.type_id ORDER BY book_id DESC");
	$num_rows = mysqli_num_rows($rows);

	foreach ($rows as $row) {
	$book_id = $row['book_id'];	
	$isbn = $row['isbn'];
	$title = $row['title'];
	$cover = $row['cover'];
	$name = $row['name'];

?>
	<div style="padding-top: 24px;">
	<div style="float:left; width: 140px;"><img src="<?php echo $dir_root;?>/cover/<?php echo $cover;?>" height="120"></div>
	<div style="float:left; width: 50px;"><span class="number2"><?php echo $book_id;?></p></span></div>
	<div style="float:left; width: 500px;"><p style="font-weight: bold; padding-bottom: 8px;"><?php echo $title;?></p><p>ISBN: <?php echo $isbn;?></p><p><?php echo $name;?></p></div>
	</div>
<?php

	}
?>

</div>

</body>
</html>