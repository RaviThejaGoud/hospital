<%@ include file="/common/taglibs.jsp"%>
<s:form id="transportStudentGenerate" method="post" theme="simple" action="ajaxGenerateTransportRequestForm" onsubmit="return prepareNonSelectedStudIds();" cssClass="form-horizontal" namespace="/admin">
	<s:hidden name="classSectionId" id="classSectionId" value="%{studyClassId}"></s:hidden>
	<s:hidden name="studentNumber" id="studNumbers"></s:hidden>
	<s:if test="%{tempList != null && !tempList.isEmpty()}">
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-8">
				<ul>
					<li>
						Please check the students from the list for whom you want to
						generate transport request form.
					</li>
				</ul>
			</div>
			<div class="col-md-3" style="float: right;">
				<s:submit value="Generate Request Form" cssClass="submit btn purple"  />
			</div>
			<div class="spaceDiv"></div> 
		</div>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<s:if test="%{selectedId != null}">
						<th>
							Class Name
						</th>
					</s:if>
					<th>
						Admission No
					</th>
					<th>
						Student Name
					</th>
					<th>
						Boarding PointName
					</th>
					<th>
						Generate Transport Form
						<input type="checkbox" name="" value=""
							onClick="checkAllStudents()" class="checkbox allTStudents">
					</th>
				</tr>
			</thead>
			<tbody> 
				<s:iterator value="tempList" status="stat">
					 <tr class="transportStudentIds">
						 <s:if test="%{selectedId != null}">
							 <td>
								<s:property value="tempList[#stat.count-1][7]" />
							</td>
						</s:if>
						<td class="studentId" id="<s:property value='tempList[#stat.count-1][5]'/>">
							<s:if
								test='%{tempList[#stat.count-1][0] != "" && tempList[#stat.count-1][2] != null}'>
								<s:property value="tempList[#stat.count-1][2]" />
							</s:if>
						</td>
						<td>
							<s:property value="tempList[#stat.count-1][0]" />&nbsp;<s:property value="tempList[#stat.count-1][6]" />
						</td>
						<td>
							<s:property value="tempList[#stat.count-1][1]" />
						</td>
						<td>
							<input type="checkbox" id="trans_<s:property value='tempList[#stat.count-1][5]'/>"
										name="chkBoxSelectedIds"
										value="<s:property value='tempList[#stat.count-1][5]'/>" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
	<div class="alert alert-info">Currently there are no transport students.</div>
	</s:else>
	
</s:form>
<div id="responsive"></div>
<script type="text/javascript">
	$(document).ready(function(){
		 $("input:checkbox, input:radio").uniform();
	}) 
	function prepareNonSelectedStudIds(){
		var studentId = 0;
		var nonSelectedStudIds = '(';
		$('tr.transportStudentIds').each(
			function() {
				studentId = $(this).find("td.studentId").attr('id');
				if($('#trans_'+studentId).attr("checked")){
					if(isNonEmpty(studentId) && studentId >0){
							nonSelectedStudIds += (studentId+",");
					}
				}
		});
		nonSelectedStudIds += "0)";
		$('#studNumbers').val(nonSelectedStudIds);
		if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one student");
		return false;
	} 
	}
	
	var includeIds = '';
	function checkAllStudents() {
	if ($(".allTStudents").is(':checked')){
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
	 includeIds=$("input[name=chkBoxSelectedIds]:checked").map(function () {return this.value;}).get().join(",");
		if(includeIds.length>0){
			$('#studNumbers').val("("+includeIds+",0)");
		}else{
			$('#studNumbers').val("("+includeIds+"0)");
			alert("Please select at least one student.")
		}
	}
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			 $(".allTStudents").parent('span').removeClass("checked");
			$(".allTStudents").attr("checked", false);
		} else {
		 $(".allTStudents").parent('span').addClass("checked");
			$(".allTStudents").attr("checked", true);
		}
		includeIds = $("input[name=chkBoxSelectedIds]:checked").map(function() {
			return this.value;
		}).get().join(",");
		if (includeIds.length > 0) {
			$('#studNumbers').val("(" + includeIds + ",0)");
		} else {
			$('#studNumbers').val("(" + includeIds + "0)");
			alert("Please select at least one student.")
		}
	});
</script>