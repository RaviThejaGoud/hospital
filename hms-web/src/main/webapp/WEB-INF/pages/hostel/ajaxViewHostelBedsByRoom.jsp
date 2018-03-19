<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>  
<div id="commonTabContent">
<div id="commonTabWrapper">
	<div class="grid_11">
		<div class="grid_10" id="bedsContent">
		<s:if test="%{objectList!= null && !objectList.isEmpty()}">
			<div class="grid_10" align="right" data-target="bedContent">
				<jsp:include
					page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
			</div>
			<div class="grid_11 th">
				<div class="grid_8">
					<div class="grid_4 badNameDiv sortHeader divArrow">
						Bed Name
					</div>
				</div>
				<div class="grid_2">
					<div class="grid_1">
						Edit
					</div>
					<div class="grid_1">
						Delete
					</div>
				</div>
			</div>
			<div id="bedContent">
				<s:iterator value="objectList">
				<div bedName="<s:property value='bedName' />" class="item">
					<div class="grid_11 row">
						<div class="grid_8">
							<div class="grid_4">
								<s:property value="bedName" />
								<s:property value="bedLevel" />
							</div>
						</div>
						<div class="grid_2">
							<div class="grid_1">
								<s:url id="doViewBedList" action="ajaxDoAddBedSettings"
									includeParams="all" escapeAmp="false" namespace="/hostel">
									<s:param name="anyId" value="%{id}" />
								</s:url>
								<sj:a href="%{doViewBedList}" indicator="indicator"  onCompleteTopics="doInitAddStudent1"
										 onBeforeTopics="cleanOpenDivs"    targets="bed%{id}" cssClass="normalEdit"
									title="Edit">
								</sj:a>
								&nbsp;
							</div>
							<div class="grid_1">
								<s:url id="removeBed" action="ajaxDeleteBed" includeParams="all" escapeAmp="false" namespace="/hostel">
									<s:param name="anyId" value="id"></s:param>
								</s:url>
								<s:div cssStyle="margin-top:3px;" cssClass="close"
									id='%{removeBed}' theme="simple"
									onclick="javascript:confirmDialogWithTarget(this,'hostelSettingContent');"
									title="Delete this Bed"></s:div>
							</div>
						</div>
								<div id="bed<s:property value='id' />" style="display: none;" ></div>
					</div>
					</div>
				</s:iterator>
			</div>
		</s:if>
		<s:else>
			<div class="grid_14 th thb">
				You have not created beds, Creating beds is simple process and
				system would guide you.
			</div>
		</s:else>
		</div>
	</div> 
	</div>
	</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script type="text/javascript">
$(document).ready(function() {  
	changePageTitle('Rooms Information');
	$.subscribe('doInitAddStudent1', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide(); 
		}
	});  
		$.subscribe('addBed', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide(); 
		}
	}); 
});

	$.subscribe('cleanOpenDivs', function(event, data) {
			$("div.loading").each(function(i, row) {
				$(row).find('div').remove();
				$(row).hide();
			});
		});
	function closeDiv(){
			//alert('lasya'); 
			$('#commonTabWrapper').hide();
							
		} 
</script> 