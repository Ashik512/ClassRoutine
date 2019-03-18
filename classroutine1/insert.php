<?php 
 require_once 'connection.php';

 if($_SERVER['REQUEST_METHOD'] == 'GET')
 {
  $subject = $_GET['subject'];
  $teacher = $_GET['teacher'];
  $room_no = $_GET['room_no'];
  $start_time = $_GET['start_time'];
  $finish_time = $_GET['finish_time'];
  $day_select = $_GET['day_select'];
  
  
  $sql = "select * from routine where start_time like '$start_time' and day_select like '$day_select'";
  
  $result = mysqli_query($connection,$sql);
  
  if($start_time == $finish_time)
  {
	   echo json_encode(array('response' =>'time should not be same', 'message' => 'Already exists...'));
  }
  else if(mysqli_num_rows($result)>0)
  {
	   echo json_encode(array('response' =>'exists', 'message' => 'Already exists...'));
  }
  else
  {
 	$query = "INSERT INTO routine (subject, teacher, room_no, start_time,
	finish_time, day_select) VALUES ('$subject','$teacher','$room_no', '$start_time', 
	'$finish_time', '$day_select')";

 	$exeQuery = mysqli_query($connection, $query); 

 	echo ($exeQuery) ? json_encode(array('response' =>'ok', 'message' => 'Inserted Successfully...')) :  json_encode(array('response' =>2, 'message' => 'Insertion failed...'));
  }
 }
 else
 {
 	 echo json_encode(array('response' =>'failed', 'message' => 'request not valid...'));
 }

 ?>
