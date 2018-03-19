<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> <s:property value="reportType"/>
					<span class="hidden-481"> </span>
				</div>
			</div>
			<div class="portlet-body" id="storeDetails">
				<div class="tab-content">
					<%@ include file="/common/messages.jsp"%>
					<s:form action="ajaxStoreReports" theme="simple" cssClass="form-horizontal" onsubmit="return generateStoreIds();"	id="storeReport" method="post" namespace="/store">
						<s:hidden name="anyId" cssClass="anyId" value=""></s:hidden>
						<s:hidden name="reportType" value="%{reportType}"></s:hidden>
					<div class="form-body">
						<s:if	test="%{(storeDataList != null && !storeDataList.isEmpty())}">
							<div class="form-group">
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<input type="checkbox" name="" value="" onClick="checkAllStores()"	class="checkbox allClasses">All Stores
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<div class="checkbox-list">
										<s:checkboxlist name="chkBoxSelectedIds"	list="storeDataList" listKey="id" listValue="storeName" theme="ems" cssClass="small" />
									</div>
								</div>
							</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-2 col-md-9">
								<s:submit type="submit" value="Generate Excel"  cssClass="submitBt btn blue" cssStyle="float:left;"	title="generate report" onclick="reportFormate()"/>
								<div id="classWiseStuDetailsPdf">
									<s:submit type="submit" value="Generate Pdf"	onclick="pdfReportType()" cssClass="submit btn blue"		title="generate report" cssStyle="float:left;margin-left:10px;"/>
								</div>
						</div>
					</div>							
					</s:if>
					<s:else>
								Currently No Stores are available.
					</s:else>

				</div>
			</s:form>
							
					</div>
				</div>
			</div>
		</div>
	</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script  type="text/javascript">
$(document).ready(function() {
	FormAdvanced.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();  
			
			$("input[name=chkBoxSelectedIds]").click(function() {
				if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
				   $(".allClasses").parent('span').removeClass("checked");
					$(".allClasses").attr("checked", false);
				} else {
				    $(".allClasses").parent('span').addClass("checked");
					$(".allClasses").attr("checked", true);
				}
			});
			
		});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
	function reportFormate() {
		$('.anyId').val('Excel');
	}
	function pdfReportType() {
		$('.anyId').val('PDF');
	}

	
	function checkAllStores() {
		if ($(".allClasses").is(':checked')){
		    $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
	function generateStoreIds() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var storeIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedStoreIds = '';
			if (storeIds.length > 0) {
				selectedStoreIds = '(';
				for ( var i = 0; i < storeIds.length; i++) {
					selectedStoreIds += storeIds[i].value + ', ';
				}
				selectedStoreIds += '0)';
			}
			$("#classNameIds").val(selectedStoreIds);
			
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Store");
			return false;
		} else {
			return false;
		}
	}
</script>