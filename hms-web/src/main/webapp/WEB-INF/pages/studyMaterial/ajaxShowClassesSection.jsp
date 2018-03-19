<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="form-group">
		<label class="col-md-2 control-label">
			 All Sections :
		</label>
		<div class="col-md-2">
			<div class="checkbox-list">
				<label class="col-md-2 control-label">
						<input type="checkbox" name="" value=""
							onClick="checkAllClasses()" class="checkbox allClasses">
				</label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-2 control-label">
			Sections :
		</label>
		<div class="col-md-9">
			<div class="checkbox-list">
			<s:checkboxlist name="chkBoxSelectedIds" list="objectList" 
					listKey="id" listValue="section" theme="ems" />
				
			</div>
			
		</div>
		<div id="studySubjectsDiv"></div>
	</div>
	<div class="col-md-12 spaceDiv"></div>
</s:if>
<s:else>
<div class="col-md-12 spaceDiv">
	<div class="alert alert-info">
		There are no sections,you can continue further process.
	</div></div>
</s:else>
<script language="JavaScript" type="text/javascript">
$(document).ready(
	function() {
		$("input:checkbox, input:radio").uniform();
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