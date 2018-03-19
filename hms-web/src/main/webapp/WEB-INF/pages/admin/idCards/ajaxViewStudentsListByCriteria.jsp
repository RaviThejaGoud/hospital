<%@ include file="/common/taglibs.jsp"%>
<div class="links">
	<span class="searchSpan right"
		onmouseover="this.style.cursor='pointer';"><h4
			class="pageTitle bold">
			Search Student
		</h4>
	</span>
</div>
<p>
	<span class="label label-danger">NOTE :</span> For search results click
	on generate id cards button to get id card.
</p>
<div class="spaceDiv"></div>
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Roll Number
				</th>
				<th>
					Admission Number
				</th>
				<th>
					Student Name
				</th>
				<th>
					<div class="checkbox-list">
						<label class="checkbox-inline">
							Select All
							<input type="checkbox" onClick="checkAllStudents()"
								class="checkbox selectAll">
						</label>
					</div>
				</th>
			</tr>
		</thead>
		<tbody>
			<s:set var="classNameId" value=""></s:set>
			<s:iterator value="studentsList">
				<s:set var="studentDetailsId" value="studId"></s:set>
				<!--<div rollNumber="<s:property value='rollNumber' />"
						className="<s:property value='className' />"
						firstName="<s:property value='firstName' />" class="item">
						-->
				<s:if test="%{classId != #classNameId}">
					<tr>
						<td colspan="6">
							<center>
								<strong>Class Name & Section : <s:property
										value="className" />-<s:property value="section" /> </strong>
							</center>
						</td>
					</tr>
				</s:if>
				<tr>
					<td>
						<s:property value="rollNumber" />
					</td>
					<td>
						<s:property value="admissionNumber" />
					</td>
					<td>
						<s:property value="firstName" />
						<s:property value="lastName" />
					</td>
					<td>
						<div class="checkbox-list">
							<label class="checkbox-inline">
								<input type="checkbox" name="selectedStudentsIds"
									value="<s:property value="admissionNumber"/>"></input>
							</label>
						</div>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info" id="emptyStudents">
		Currently there are no students for this search.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
 	$("input:checkbox, input:radio:not('.toggle')").uniform(); 
	FormComponents.init();
	$('.links').show();
	$("#searchAdmissionNumber").hide();//hide the all of the element with class msg_body		
	});
$(".links").click(function()//toggle the componenet with class msg_body
		{
			if ($("#searchAdmissionNumber").is(":hidden"))
				$("span.searchSpan").attr("style", "color:#008EE8");
			else
				$("span.searchSpan").attr("style", "color:#008EE8");
			$("#searchStudentsList").hide();
			$("#generateId").hide();
			$("#searchAdmissionNumber").show();
		});
function checkAllStudents() {
	if ($(".selectAll").is(':checked')) {
		$("[name='selectedStudentsIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
			$(".selectAll").parent('span').removeClass('checked');
			$(".selectAll").removeAttr("checked");
		$("[name='selectedStudentsIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}

$("input[name=selectedStudentsIds]").click(function() {
	if ($("input[name=selectedStudentsIds]:unchecked").length > 0) {
		$(".selectAll").parent('span').removeClass("checked");
		$(".selectAll").attr("checked", false);
	} else {
		$(".selectAll").parent('span').addClass("checked");
		$(".selectAll").attr("checked", true);
	}
});
</script>
