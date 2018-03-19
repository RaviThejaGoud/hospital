<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				var errMsg = $('.block .message');
				if (errMsg) {
					$('.block .message').hide().append(
							'<span class="close1" title="Click to Close"></span>')
							.fadeIn('slow');
					$('.block .message .close1').hover( function() {
						$(this).addClass('hover');
					}, function() {
						$(this).removeClass('hover');
					});

					$('.block .message .close1').click( function() {
						$(this).parent().fadeOut('slow', function() {
							$(this).remove();
						});
					});
				}
	});
</script>

<s:if test="hasActionMessages()">
	<s:iterator value="actionMessages">
		<div class="block"
			style="margin-bottom: 0px; margin-top: -5px; padding-bottom: 0px">
			<div style="display: block;width:500px;" class="message success">
				<s:property />
			</div>
		</div>
	</s:iterator>
</s:if>


<s:if test="hasActionErrors()">
	<s:iterator value="actionErrors">
		<div class="block"
			style="margin-bottom: 0px; margin-top: -15px; padding-bottom: 0px;">
			<div style="display: block" class="message errormsg">
			<img width="28" height="24"   src="../images/newImages/error_icon.png">
				<s:property />
			</div>
		</div>
	</s:iterator>
</s:if>

<div class="grid_15">
	<div style="float: left">
		<div class="grid_6">
			Personal Details
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					First Name:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.firstName" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					Last Name:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.lastName" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					Customer Short Name:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.customerShortName" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					Street:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.address.streetName" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					City:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.address.city" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					State:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.address.state" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					Pincode:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.address.postalCode" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					Modify Invoice Password:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.modifyInvoicePassword" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					Delete Invoice Password:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.deleteInvoicePassword" />
			</div>
		</div>
	</div>
	<div>
		<div class="grid_6">
			Organization Address
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					Organization Name:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.organization" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					Organization Type:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="organizationType" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					AddressLine1:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="address.addressLine1" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					Street:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="address.streetName" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					City:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="address.city" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					State:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="stateName" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					Pincode:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="address.postalCode" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					Phone Number:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.contactNumber" />
			</div>
		</div>
		<div class="grid_6">
			<div class="grid_3">
				<label class="labelRight">
					MobileNumber:
				</label>
			</div>
			<div class="grid_3">
				<s:property value="customer.mobileNumber" />
			</div>
		</div>
	</div>
</div>