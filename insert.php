<?php

require "init.php";

$subject=$_GET['subject'];
$teacher=$_GET['teacher'];
$room_no=$_GET['room_no'];
$start_time=$_GET['start_time'];
$finish_time=$_GET['finish_time'];
$day_select=$_GET['day_select'];

$response=array();


$sql_reg="insert into routine (subject,teacher,room_no,start_time,finish_time,day_select)
values ('$subject','$teacher','$room_no','$start_time','$finish_time','$day_select');";


  if(mysqli_query($connection,$sql_reg)){
    $status="ok";
   }
  else{
      $status = "error";
  }



$response["response"]=$status;;
$output= json_encode($response);
echo $output;
mysqli_close($connection);

?>
