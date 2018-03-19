<?php include('Crypto.php')?>

<?php 

/// This file path in hynica.com swebsite"  home/hyniva13/public_html/ccavrequesthandler.php

$working_key = "63E8BC3765E40B888895B8DD2A23EF75";

foreach ($_GET as $key => $value)
{
	if("eschool" == $value)
	{
		$merchant_data .="redirect_url=https://eazyschool.in/admin/verifyBuySMSPayment.do&cancel_url=https://eazyschool.in/admin/verifyBuySMSPayment.do";
	}
	else
		$merchant_data.=$key.'='.$value.'&';
}
//if("eschool" == $_GET['source'])
	
	//$merchant_data .="currency=INR&redirect_url=https://eazyschool.in/admin/ajaxDoBuySms.do&cancel_url=https://eazyschool.in/admin/ajaxDoBuySms.do";
		
$merchant_data .="currency=INR";

$encrypted_data=encrypt($merchant_data,$working_key); // Method for encrypting the data.

log_message("merchant_data:" . $merchant_data);
//echo $merchant_data;


function log_message($msg)
{

	$handle = fopen(dirname(__FILE__). "/debug.log", "a+");
	$reffer = "";
	//if(isset($_SERVER['HTTP_REFERER']))$reffer = $_SERVER['HTTP_REFERER'];
	fwrite($handle, date("Y-m-d H:i:s") . "," . $reffer . "," . $msg . "\r\n");
	fclose($handle);
}


?>
<form method="post" name="redirect" action="https://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction"> 
<?php
//echo "encrypted_data:" . $encrypted_data;
echo "<input type=hidden name=encRequest value=$encrypted_data>";
echo "<input type=hidden name=access_code value='AVLB06CI27AD69BLDA'/>";
?>
</form>

<script language='javascript'>   document.redirect.submit();  </script>