<?php
require_once('connection.php');
if($_SERVER['REQUEST_METHOD']=='GET') {

   $id = $_GET['id'];
 	$query = "DELETE FROM routine WHERE id ='$id'";
 	$exeQuery = mysqli_query($connection, $query); 
 	echo ($exeQuery) ? json_encode(array('response' =>'ok', 'pesan' => 'Deleted Successfully...')) :  json_encode(array('kode' =>'error', 'pesan' => 'Deletion Failed...'));
 }
 
   mysqli_close($connection);
?>