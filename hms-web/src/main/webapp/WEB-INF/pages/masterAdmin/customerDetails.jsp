<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US">
	<head profile="http://gmpg.org/xfn/11">
		<title>Media Fusion Home | Media Fusion</title>
	</head>
	
<script type="text/javascript">
function createInstanceFieldErrors(){
			
             var parameters ='';
             var fieldErrorString ='';
             var orgName = document.getElementById("orgName").value;
             var preferedUrl = document.getElementById("prferedUrl").value;
             var emailId = document.getElementById("emailId").value;
             var password = document.getElementById("password").value;
          
             
             var orgNameTrim=$.trim(orgName);
             var preferedUrlTrim=$.trim(preferedUrl);
             var emailIdTrim=$.trim(emailId);
             var passwordTrim=$.trim(password);
             
              
             if(orgNameTrim == ''){
                fieldErrorString = fieldErrorString + "<font style=\"color:red; margin:0px 0px 0px 20px;\">Please enter Organization Name.<br/></font>";
             }
            
            if(preferedUrlTrim == ''){
                fieldErrorString = fieldErrorString + "<font style=\"color:red; margin:0px 0px 0px 20px;\">Please enter Prefered URL.<br/></font>";
             }
             
             if(emailIdTrim == ''){
                fieldErrorString = fieldErrorString + "<font style=\"color:red; margin:0px 0px 0px 20px;\">Please enter Email Id.<br/></font>";
             }
            
            if(passwordTrim == ''){
                fieldErrorString = fieldErrorString + "<font style=\"color:red; margin:0px 0px 0px 20px;\">Please enter Password.<br/></font>";
             }
            if (fieldErrorString!='') {
                document.getElementById('createFreeInstanceFieldErrors').innerHTML= fieldErrorString;
                document.getElementById('createFreeInstanceFieldErrors').style.display = "block";
                return false;
                }
                else{
             return true;
            }   
        }

function editInstanceFieldErrors(){
			alert('edit instance');
             var parameters ='';
             var fieldErrorString ='';
             var orgName = document.getElementById("orgName").value;
             var preferedUrl = document.getElementById("prferedUrl").value;
             var emailId = document.getElementById("emailId").value;
             var password = document.getElementById("password").value;
          
             
             var orgNameTrim=$.trim(orgName);
             var preferedUrlTrim=$.trim(preferedUrl);
             var emailIdTrim=$.trim(emailId);
             var passwordTrim=$.trim(password);
             
              
             if(orgNameTrim == ''){
                fieldErrorString = fieldErrorString + "<font style=\"color:red; margin:0px 0px 0px 20px;\">Please enter Organization Name.<br/></font>";
             }
            
            if(preferedUrlTrim == ''){
                fieldErrorString = fieldErrorString + "<font style=\"color:red; margin:0px 0px 0px 20px;\">Please enter Prefered URL.<br/></font>";
             }
             
             if(emailIdTrim == ''){
                fieldErrorString = fieldErrorString + "<font style=\"color:red; margin:0px 0px 0px 20px;\">Please enter Email Id.<br/></font>";
             }
            
            if(passwordTrim == ''){
                fieldErrorString = fieldErrorString + "<font style=\"color:red; margin:0px 0px 0px 20px;\">Please enter Password.<br/></font>";
             }
            if (fieldErrorString!='') {
                document.getElementById('editInstanceFieldErrors').innerHTML= fieldErrorString;
                document.getElementById('editInstanceFieldErrors').style.display = "block";
                return false;
                }
                else{
             return true;
            }   
        }

	function closeDiv() {
		$('#resultDiv').hide();
	}
	function getCustomersCount() {
		$('#resultDiv').show();
	}
</script>	
	
	<body id="page-6">
		<div id="wrapper" class="container_24">
			<div id="header" class="grid_24">
				<div class="grid_8 suffix_10">
					<a href="http://www.startthefusion.com/" class="sprite logo-link">Media
						Fusion &#8211; Video Audio and Podcasting</a>
				</div>

				<div class="grid_5 alpha omega">
					<a href="<c:url value='/logout.jsp'/>" class="sprite logout">Log
						Out</a>
				</div>
			</div>
			<div id="navigation" class="grid_24">
				<ul>
					<li class="current">
						<a href="http://www.startthefusion.com/">StartTheFusion.com</a>
					</li>
					<li class="page_item page-item-2 current_page_item">
						<a href="masterAdminHome.do" title="Customer Details">Master Admin</a>
					</li>
					<li class="current">
						<a href="ikonCustDetails.do" title="Ikon Customer Details">ICON Customers Details</a>
					</li>
				</ul>
			</div>
			<div id="article-top" class="grid_24">
			</div>
			<div class="container_24 grid_24">
				<div class="mainContainer">
					<div class="headerBottom">
						<div class="headerText">
							<img align="absmiddle" src="../images/media/leader.png"
								style="margin: 2px" />
							Customer's Details
							<a id="createButton" rel="ibox"
								href="<c:url value='/popup/popupCreateInstance.do'/>"> <img
									src="../images/media/create_instance.jpg"
									style="float: right; margin-top: 10px" /> </a>
						</div>

						<div style="float: right">
							<font style="color: gray"><b>(Y=Yearly,M=Monthly,F=Free)</b>
							</font>
						</div>
					</div>
					<div class="grid_24 " id="cssBlue" style="margin-top: 10px;">
						<div  class="grid_18">
							<a href="#" onmouseover="javascript:getCustomersCount();"  onmouseout="javascript:closeDiv();" id="customerCount">Total Customers Count :: </a>  <s:property value="customersList.size"/>
						</div>
						<div class="grid_3">
							<b> Export Options: 
						</div>
						<div class="grid_1">
							<a
								href="<c:url value='/admin/generateCustomerDetailsExcel.do'/>"
								style="float: right;">Excel</a>
						</div>
					</div>
					<div class="grid_6" id="resultDiv" style="display: none;">
						<div class="grid_6">
							<font style="color: gray;"><b>Others  :: <s:property value="objectList.size"/> </b></font>
						</div>
						<div class="grid_6">
							<font style="color: gray;"><b>FaceBook ::  <s:property value="faceBookList.size"/> </b></font>
						</div>
						<div class="grid_6">
							<font style="color: gray;"><b>GoogleSearch ::  <s:property value="googleSearchList.size"/></b></font>
						</div>
						<div class="grid_6">
							<font style="color: gray;"><b>Church Crunch :: <s:property value="ccList.size"/></b></font>
						</div>
						<div class="grid_6">
							<font style="color: gray;"><b>Sermon Speach ::  <s:property value="ssList.size"/></b></font>
						</div>
						<div class="grid_6">
							<font style="color: gray;"><b>Michel Hyatt ::  <s:property value="mhList.size"/></b></font>
						</div>
					</div>
					<div class="grid_24 " id="cssBlue" style="margin-top: 10px;">
						<div class="grid_4">
							<b>Organization Name</b>
						</div>
						<div class="grid_5">
							<b>Email-Id</b>
						</div>
						<div class="grid_3">
							<b>Contact Number</b>
						</div>
						<div class="grid_2">
							<b>Type</b>
						</div>
						<div class="grid_2">
							<b>Status</b>
						</div>
						<div class="grid_4">
							<b>Renewal Date</b>
						</div>
						<div class="grid_1">
							<b>Edit</b>
						</div>
						<div class="grid_1" style="float: right">
							<b>Delete</b>
						</div>
					</div>

					<div class="grid_24" style="margin-top: 5px;">
						<s:if test="%{customersList!=null}">
							<s:iterator value="customersList">
								<div id="cssBlue" style="margin-bottom: 5px">
									<div class="grid_4" style="margin-top: 10px">
										<a
											href="<c:url value='/popup/popupViewCustomerDetails.do'/>?cid=<s:property value="id"/>"
											rel="ibox"> <s:property value="customerName" />&nbsp;</a>
									</div>
									<div class="grid_5" style="margin-top: 10px">
										<s:property value="custEmail" />
										&nbsp;
									</div>
									<div class="grid_3" style="margin-top: 10px">
										<s:property value="customerPhone.formattedPhoneNumber" />
										&nbsp;
									</div>
									<div class="grid_2" style="margin-top: 10px;">
										<s:property value="subscriptionType" />
										&nbsp;
									</div>
									<div class="grid_2" style="margin-top: 10px">
										<s:property value="status" />
										&nbsp;
									</div>
									<div class="grid_4" style="margin-top: 10px">
										<s:property value="formattedCreatedDate" />
										&nbsp;
									</div>
									<div class="grid_1" style="margin-top: 10px;">
										<a
											href="<c:url value='/popup/popupEditCustomerDetails.do'/>?cid=<s:property value="id"/>"
											rel="ibox" title=" "><img
												src="../images/media/application_edit.png" width="18"
												height="18" align="absmiddle" border="none"> </a>
									</div>
									<div class="grid_1" style="margin-top: 10px; float: right">
										<a
											href="<c:url value='/popup/popupdeleteCustomer.do'/>?cid=<s:property value="id"/>"
											rel="ibox" title=" "><img
												src="../images/media/delete1.png" width="18" height="18"
												align="absmiddle" border="none"> </a>
									</div>
									<div class="grid_24">
										&nbsp;
									</div>
								</div>
							</s:iterator>
						</s:if>
					</div>
				</div>
				<div class="grid_24 ">
					&nbsp;
				</div>
			</div>
		</div>