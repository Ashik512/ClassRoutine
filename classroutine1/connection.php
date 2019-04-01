<?php 
	include 'config.php';
	
	$connection = new Mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME) or die(Mysqli_errno());

 ?>