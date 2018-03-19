<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	function noBack() {
		window.history.forward()
	}
	noBack();
	window.onload = noBack;
	window.onpageshow = function(evt) {
		if (evt.persisted)
			noBack()
	}
	window.onunload = function() {
		void (0)
	}
</script>
<div id="article-top" class="grid_24">
</div>
<div id="content-wrapper" class="demo grid_24 omega">	
	<div class="grid_12" style="margin-top:50px;">
		 <table>
				<tr class="rowgap">
					<td colspan="2">
						<div>
							<font
								style="color: #4089D8; font-size: 15; font-weight: bold">IMPORTANT NOTES:</font><b> The following Information was also emailed to</b>
							<s:property value="customer.custEmail" />
						</div>
					</td>
				</tr>
				<tr>
					<td style="border-bottom: 1px dotted #CCCCCC; width: 500px;"
						colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>				
				
				<tr>
					<td colspan="2" style="background-color: #4089D8">
						<font color="#ffffff" style="font-size: 20px;">
							&nbsp;&nbsp;Administrative Access:</font>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2">
						To Access your administrative controls,You can now log in at <a href="<s:property value="hostUrl"/>" target="_new">apps.uroomtech.com</a>
						<br>
						and log into your new account using the following :
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<font style="color: #4089D8; font-size: 15">E-mail
							Address :</font>
						<s:property value="customer.custEmail" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<font style="color: #4089D8; font-size: 15">Password :</font>
						<s:property value="customer.password" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>				
			</table>
	</div>
	
</div>