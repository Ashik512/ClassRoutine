<?php
require_once('connection.php');

if($_SERVER['REQUEST_METHOD']=='GET') {

   $day = $_GET['day_select'];
   $dept = $_GET['dept'];
   $year = $_GET['year'];

 	$query = "DELETE FROM routine WHERE day_select ='$day' and  (dept = '$dept' and year = '$year')";

 	$exeQuery = mysqli_query($connection, $query); 

 	echo ($exeQuery) ? json_encode(array('response' =>'ok', 'pesan' => 'Deleted Successfully...')) :  json_encode(array('kode' =>'error', 'pesan' => 'Deletion Failed...'));
 }
 
   mysqli_close($connection);
?>