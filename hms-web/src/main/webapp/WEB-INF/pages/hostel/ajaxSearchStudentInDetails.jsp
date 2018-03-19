<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="searchDiv grid_10">
	<div class="hideSearchStudentBody">
		<s:form id="searchStudentByNumber" action="ajaxSearchStudentIn"
			theme="css_xhtml" namespace="/hostel">
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
<div id="searchStudentsList" class="grid_11"></div>
<script type="text/javascript">
$('#financeHome').addClass('current');
$(document).ready(function() {
	$('#Staffs').hide();
	$('.links').hide();
	$('#searchStudentsList').show();
	$(".hideSearchStudentBody").show();//hide the all of the element with class msg_body
	});
$(".links").click(function()//toggle the componenet with class msg_body
		{
			if ($(".hideSearchStudentBody").is(":hidden"))
				$("span.searchSpan").attr("style", "color:#000000");
			else
				$("span.searchSpan").attr("style", "color:#008EE8");

			$(".hideSearchStudentBody").slideToggle(600);
		});
</script>
<script Language="Javascript1.2" type="text/javascript">
$(document).ready(function() {
	changePageTitle("Student In&Out Register");
	$('.blockHeader h2').html('Manage Reports');
});

function clearThis(target) {
	$(target).val = "";
}
</script>