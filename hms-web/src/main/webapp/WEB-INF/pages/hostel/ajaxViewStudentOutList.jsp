<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14">
	<s:url id="searchStudentByNumber" action="ajaxViewStudentInAndOut"
		includeParams="all" escapeAmp="false" namespace="/hostel">
	</s:url>
	<sj:a href="%{searchStudentByNumber}" indicator="indicator"
		targets="stepHostelInOutRegister" button="false" cssClass="linkRight">Add Student</sj:a>
	<h1>
		Current Student-Out Details
	</h1>
	<%--<div class="searchDiv grid_10">
	<div class="hideSearchStudentBody">
		<s:form id="searchStudentByNumber" action="ajaxSearchStudentInOut"
			theme="css_xhtml">
			 
			<fieldset>
				<h1 id="Students">
					Search Student
				</h1>
				<p>
					Key at least 3 chars and hit submit to get closer match.
				</p>
				<div class="grid_8">
					&nbsp;
				</div>
				<div class="grid_8">
					<div class="grid_5">
						<sj:textfield name="anyTitle" id="anyword"
							value="First or Last Name" onfocus="clearThis(this)"
							targets="text" cssClass="textfield large required defaultValue"
							required="true"></sj:textfield>
					</div>
					<div class="grid_3">
						<sj:submit   targets="searchStudentsList" value="Find Student"
							cssClass="submit long" indicator="indicator"
							cssStyle="float:none;" onClickTopics="searchStudentHostelForm"
							formIds="searchStudentByNumber" />
					</div>
				</div>
			</fieldset>
		</s:form>
	</div>
</div>
--%>
	<div id="searchStudentsList" class="grid_11"></div>
	<s:if test="%{studentOutList!= null && !studentOutList.isEmpty()}">
		<div class="grid_14" align="right" data-target="studentContent">
			<jsp:include
				page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
		</div>
		<div class="grid_14 th">
			<div class="grid_12">
				<div class="grid_3 sNameDiv sortHeader divArrow">
					Student Name
				</div>
				<div class="grid_2">
					Class Name
				</div>
				<div class="grid_2 outDateDiv sortHeader divArrow">
					Out Date
				</div>
				<div class="grid_2 inDateDiv sortHeader divArrow">
					In Date
				</div>
				<div class="grid_3">
					Out Time/In Time
				</div>
			</div>
			<div class="grid_2">
				Visitor Name
			</div>
		</div>
		<div id="studentContent">
			<s:iterator value="studentOutList">
			<div firstName="<s:property value='firstName' />" outDateStr="<s:property value='outDateStr' />" expectedInDateStr="<s:property value='expectedInDateStr' />"  class="item">
				<div class="grid_14 row">
					<div class="grid_12">
						<div class="grid_3">
							<s:property value="firstName" />
							&nbsp;
							<s:property value="lastName" />
						</div>
						<div class="grid_2">
							<s:property value="className" />
							&nbsp;
							<s:property value="section" />
						</div>
						<div class="grid_2">
							<s:property value="outDateStr" />
						</div>
						<div class="grid_2">
							<s:property value="expectedInDateStr" />
						</div>
						<div class="grid_3">
							<s:property value="outTime" />
							/
							<s:property value="exceptedInTime" />
						</div>
					</div>
					<div class="grid_2">
						<s:property value="visitorName" />
					</div>
				</div>
				</div>
			</s:iterator>
		</div>
	</s:if>
	<s:else>
		<div class="grid_14 th thb">
			Currently there is no outed permission students.
		</div>
	</s:else>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	$('#studentContent').pagination();
	$('#searchStudentsList').show();
});


var name=1;
$('div.sNameDiv').click(function () {
     $('div#studentContent>div.item').sortElements(function (a, b) {return ($(a).attr('firstName').toLowerCase() > $(b).attr('firstName').toLowerCase() ? 1 : -1) * name;});
    updateDirectionArrows($(this), name);
    $("#studentContent").pagination();
    name= name* -1;
    return false;
});
var outDate=1;
$('div.outDateDiv').click(function () {
 	$('div#studentContent>div.item').sortElements(function (a, b) {return ($(a).attr('outDateStr') > $(b).attr('outDateStr') ? 1 : -1) * outDate; });
	updateDirectionArrows($(this), outDate);
    $("#studentContent").pagination();
    outDate = outDate * -1;
    return false;
});

var inDate=1;
$('div.inDateDiv').click(function () {
	$('div#studentContent>div.item').sortElements(function (a, b) {return ($(a).attr('expectedInDateStr') > $(b).attr('expectedInDateStr') ? 1 : -1) * inDate; });
	updateDirectionArrows($(this), inDate);
    $("#completedSchedulesCont").pagination();
    inDate = inDate * -1;
    return false;
});
 
</script>
<style type="text/css">
.grid_1, .grid_2, .grid_3, .grid_4, .grid_5, .grid_6, .grid_7, .grid_8, .grid_9, .grid_10, .grid_11, .grid_12, .grid_13, .grid_14, .grid_15, .grid_16, grid_17, grid_18 {
    margin-right: 9px;
}
</style>