<?php
require_once('connection.php');
if($_SERVER['REQUEST_METHOD']=='GET') {

   $response = array();

   $id = $_GET['id'];
 
   $sql = "DELETE FROM routine WHERE id = '$id'";
   
   if(mysqli_query($connection,$sql)){
     $response["value"] = "ok";
     $response["message"] = "Deleted Successfully...";
     echo json_encode($response);
   } else { 
       $response["value"] = "error";
       $response["message"] = "Delete Failed...";
       echo json_encode($response); 
   }
 
   mysqli_close($connection);
}