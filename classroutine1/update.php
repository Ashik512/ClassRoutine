<?php
require_once('connection.php');
if($_SERVER['REQUEST_METHOD']=='GET') {

   $response = array();
   
   $id = $_GET['id'];
   $subject = $_GET['subject'];
   $teacher = $_GET['teacher'];
   $room_no = $_GET['room_no'];
   $start_time = $_GET['start_time'];
   $finish_time = $_GET['finish_time'];
   $day_select = $_GET['day_select'];
   
   
   $sql = "UPDATE routine SET subject = '$subject', teacher = '$teacher',room_no = '$room_no',
           start_time = '$start_time', finish_time = '$finish_time', 
		   day_select = '$day_select', WHERE id = '$id'";
		   
   if(mysqli_query($connection,$sql)){
     $response["value"] = "ok";
     $response["message"] = "Update Success...";
     echo json_encode($response);
   } else { 
       $response["value"] = "error";
       $response["message"] = "Update failed...";
       echo json_encode($response); 
   }
   
   mysqli_close($connection);
}