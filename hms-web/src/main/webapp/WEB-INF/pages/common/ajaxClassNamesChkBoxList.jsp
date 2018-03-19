<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{(classList != null && !classList.isEmpty()) || (classNameList != null && !classNameList.isEmpty())}">
	<s:if test="%{classList != null && !classList.isEmpty()}">
		<div class="form-group">
			<div class="col-md-12">
				<div class="checkbox-list">
					<label class="checkbox-inline">
							<input type="checkbox" name="" value=""
								onClick="checkAllClasses()" class="checkbox allClasses">
						All Classes
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="conLable col-md-3 control-label">
				<span class="required">*</span>Classes With Students :
			</label>
			<div class="col-md-12">
				<div class="checkbox-list">
					<s:checkboxlist name="chkBoxSelectedIds" list="classList"
						listKey="id" listValue="className" theme="ems" cssClass="small" />
				</div>
			</div>
		</div>
	</s:if>
	<s:if test='%{classNameList != null && !classNameList.isEmpty()}'>
		<div class="form-group">
			<label class="conLable col-md-3 control-label">
				<span class="required">*</span>Classes With Out Students :
			</label>
			<div class="col-md-12">
				<div class="checkbox-list">
					<s:checkboxlist name="chkBoxNotStudents" list="classNameList"
						listKey="id" listValue="className" theme="ems" cssClass="small"
						disabled="true" />
				</div>
			</div>
		</div>
	</s:if>
</s:if>
<s:else>
	<div class="alert alert-info">
		Classes are not available.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(
	function() {
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
</script>