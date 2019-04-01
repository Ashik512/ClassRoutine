<?php

require "connection.php";

$user_id = $_GET['user_id'];
$email=$_GET['email'];
$password=$_GET['password'];
$dept = $_GET['dept'];
$year = $_GET['year'];

$response=array();


$sql="select * from student where user_id like '$user_id'";

 $result = mysqli_query($connection,$sql);

if(mysqli_num_rows($result)>0){
  $status="exist";
}else{

  $sql_reg="insert into student (user_id,email,password,dept,year)
  values ('$user_id','$email','$password','$dept','$year')";

  if(mysqli_query($connection,$sql_reg)){

  	$response['user_id']=$user_id;
    $response['email']=$email;
    $response['dept']=$dept;
    $response['year']=$year;
    $response['user']="Student";
    $status="ok";
}
else {
      $status = "error";
  }
}

$response["response"]=$status;;
$output= json_encode($response);
echo $output;
mysqli_close($connection);

?>
