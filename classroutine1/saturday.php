<?php 
require_once 'connection.php'; 
$query = "SELECT * FROM routine where day_select like 'SATURDAY'";
$result = mysqli_query($connection,$query);
$array = array();
while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row; 
}
echo ($result) ? 
json_encode(array("kode" => 1, "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data fetching failed"));
?>