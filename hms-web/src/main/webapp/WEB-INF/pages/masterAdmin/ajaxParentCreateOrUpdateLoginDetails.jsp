<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<s:if test='%{plTitle == "Barcode"}'>
						Generate Barcode
					</s:if>
					<s:else>
						 Create/Update Parent Login Credentials
					</s:else>
				</div>
			</div>
			<div class="portlet-body">
				<%@ include file="/common/messages.jsp"%>
				<div><img   src="../img/bg/bigWaiting.gif" alt="Loading..." title="Loading..." id="myDefaultIndicator" style="display:none;background-repeat: no-repeat;position: fixed;z-index: 1000;top: 50%;left: 50%;text-align:center;" /></div>
			</div>
		</div>
	</div>
</div>