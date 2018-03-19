<%@ include file="/common/taglibs.jsp"%>
<div id="studAttCont">
	<s:if
		test="%{(studyClassList != null && !studyClassList.isEmpty()) || (tempList2 != null && !tempList2.isEmpty())}">
		<s:form action="ajaxGenerateTimeTableByClasses"
			cssClass="form-horizontal" theme="simple"
			onsubmit="return generateClassIds();" id="generateTTForm"
			method="post" namespace="/reports">
			<s:hidden id="classNameIds" name="selectedId" />
			<s:hidden id="classNames" name="tempString" />
			<div class="form-body">
			<div class="form-group">
				<div class="col-md-12">
					<div class="checkbox-list">
						<label class="checkbox-inline">
								<input type="checkbox" name="" value="" onClick="checkAllClasses()"
									class="checkbox allClasses">
							All Classes
						</label>
					</div>
				</div>
				</div>
			<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
				<div class="form-group">
					<label class="conLable col-md-3 control-label">
						<span class="required">*</span> Classes With Students :
					</label>
					<div class="col-md-12">
						<div class="checkbox-list">
							<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
								listKey="id" listValue="classAndSection" theme="ems"
								cssClass="small" />
						</div>
					</div>
				</div>
			</s:if>
			<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
				<div class="form-group">
					<label class="conLable col-md-3 control-label">
						Classes With Out Students :
					</label>
					<div class="col-md-12">
						<div class="checkbox-list">
							<s:checkboxlist name="chkBoxNotStudents" list="tempList2"
								listKey="id" listValue="classAndSection" theme="ems"
								cssClass="small" disabled="true" />
						</div>
					</div>
				</div>
			</s:if>
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-3 col-md-9">
						<s:submit type="submit small" value="Download Timetable"
							onclick="reportType()" cssClass="submitBt btn blue long"
							title="generate report" />
					</div>
				</div>
			</div>
			</div>
		</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Classes are not available.
		</div>
	</s:else>
</div>
<script type="text/javascript">
	$(document).ready(function() {
			 $("input:checkbox, input:radio").uniform();
				$('span.hidden-title').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
			+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
			
			
	});
	$("input[name=chkBoxSelectedIds]").click(function() {
			if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
				$(".allClasses").attr("checked", false);
				$(".allClasses").parent('span').removeClass("checked");
			} else {
			    $(".allClasses").parent('span').addClass("checked");
				$(".allClasses").attr("checked", true);
			}
		});
	function checkAllClasses() {
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
	function generateClassIds() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedClassIds = '';
			var selectedClassNames = '';
			if (classIds.length > 0) {
				selectedClassIds = '(';
				$(classIds).each(function(){
					selectedClassIds += $(this).parents('label').text() + ', ';
					if(isNonEmpty($(this).parents('label').text()))
					    selectedClassNames +=  $(this).parents('label').text().replace(/^\s+|\s+$/g, ' ');
				})
				selectedClassIds += '0)';
			}
			$("#classNames").val(selectedClassNames);
			$("#classNameIds").val(selectedClassIds);
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one Class");
			return false;
		} else {
			return false;
		}
	}
</script>
