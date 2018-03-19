<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{anyTitle == 'category'}">
	<div class="grid_14">
		<s:url id="doAddNewCategoryTypeList"
			action="ajaxDoAddCategorySettings" includeParams="all"
			escapeAmp="false" namespace="/hostel">
			<s:param name="hostelCategory.id" value="0" />
		</s:url>
		<sj:a href="%{doAddNewCategoryTypeList}" indicator="indicator"
			targets="feeSettingContent" button="false" cssClass="linkRight">Create Category</sj:a>
		<h1>
			Current Hostel Categories
		</h1>
		<s:if
			test="%{hostelCategoriesList != null && !hostelCategoriesList.isEmpty()}">
			<s:if test="%{#session.newYear == #session.academicYear && hostelCategoriesList.size > 1}">
				<div class="noteFont grid_14">
					<div class="grid_1">
						<span class="noteMassage"> Note:</span>
					</div>
					<div class="grid_13">
						You can edit/update existing hostel category settings by clicking
						on edit pen icon in each row at right side.
					</div>
				</div>
			</s:if>
			<div class="grid_14" align="right" data-target="feeCategoryCont">
				<jsp:include
					page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
			</div>
			<div class="grid_14 th">
			<div class="grid_6">
				<div class="grid_2 categoryDiv sortHeader divArrow">
					Category
				</div>
				</div>
				<div class="grid_4">
					Edit
				</div>
				<div class="grid_4">
					Delete
				</div>
			</div>
			<div id="feeCategoryCont">
				<s:iterator value="hostelCategoriesList">
				 <div categoryName="<s:property value='categoryName' />" class="item">
					<div class="grid_14 row">
						<div class="grid_6">
							<s:property value="categoryName" />
						</div>
						<s:if test='%{categoryName != "General"}'> 
						<div class="grid_4">
							<s:url id="doViewCategoryList" action="ajaxDoAddCategorySettings"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="hostelCategory.id" value="%{id}" />
								<s:param name="academicYearId" value="%{academicYearId}" />
							</s:url>
							<sj:a href="%{doViewCategoryList}" indicator="indicator"
								targets="feeSettingContent" cssClass="normalEdit" title="Edit">
							</sj:a>
							&nbsp;
						</div>
						<div class="grid_4">
							<s:url id="removeCategory" action="ajaxDeleteCategory"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="hostelCategory.id" value="%{id}" />
							</s:url>
							<s:div cssStyle="margin-top:3px;" cssClass="close"
								id='%{removeCategory}' theme="simple" onclick="javascript:confirmDialogWithTarget(this,'feeSettingContent');"
								title="Delete this Category"></s:div>
						</div>
						</s:if>
					</div>
					</div>
				</s:iterator>
			</div>
		</s:if>
		<s:else>
			<div class="grid_14 th thb">
				You have not created hostel fee Categories. Creating Category is
				simple process and system would guide you.
			</div>
		</s:else>
	</div>
</s:if>
<s:if test='%{anyTitle == "particulars"}'>
	<div class="grid_14" id="stepHostel">
		<s:url id="doAddNewParticularsList"
			action="ajaxDoAddParticularsSettings" includeParams="all"
			escapeAmp="false" namespace="/hostel">
			<s:param name="hostelFeeType.id" value="0" />
		</s:url>
		<sj:a href="%{doAddNewParticularsList}" indicator="indicator"
			targets="feeSettingContent" button="false" cssClass="linkRight">Create Fee Particulars </sj:a>
		<h1>
			Current Fee Particulars Details
		</h1>
		<s:if
			test="%{hostelFeeTypeList!= null && !hostelFeeTypeList.isEmpty()}">
			<div class="noteFont grid_14">
				<div class="grid_1">
					<span class="noteMassage"> Note:</span>
				</div>
				<div class="grid_13">
					You can edit/update existing fee particulars settings by clicking
					on edit pen icon in each row at right side.
				</div>
			</div>
			<div class="grid_14" align="right" data-target="feeParticularsCont">
				<jsp:include
					page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
			</div>
			<div class="grid_14 th">
			<div class="grid_6">
				<div class="grid_6">
					<div class="grid_3 feeDiv sortHeader divArrow">
						Fee Particulars
					</div>
				</div>
				</div>
				<div class="grid_4">
					Edit
				</div>
				<div class="grid_4">
					Delete
				</div>
			</div>
			<div id="feeParticularsCont">
				<s:iterator value="hostelFeeTypeList">
				<div hostelFeeType="<s:property value='hostelFeeType' />" class="item">
					<div class="grid_14 row">
						<div class="grid_6">
							<s:property value="hostelFeeType" />
						</div>
						<s:if test='%{hostelFeeType != "Hostel Fee"}'> 
						<div class="grid_4">
							<s:url id="doViewParticularList"
								action="ajaxDoAddParticularsSettings" includeParams="all"
								escapeAmp="false" namespace="/hostel">
								<s:param name="hostelFeeType.id" value="%{id}" />
								<s:param name="academicYearId" value="%{academicYearId}" />
							</s:url>
							<sj:a href="%{doViewParticularList}" indicator="indicator"
								targets="feeSettingContent" cssClass="normalEdit" title="Edit">
							</sj:a>
							&nbsp;
						</div>
						<div class="grid_4">
							<s:url id="removeParticulars" action="ajaxDeleteFeeParticulars"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="hostelFeeType.id" value="%{id}" />
							</s:url>
							<s:div cssStyle="margin-top:3px;" cssClass="close"
								id='%{removeParticulars}' theme="simple" onclick="javascript:confirmDialogWithTarget(this,'feeSettingContent');"
								title="Delete this Fee Particular"></s:div>
						</div>
						</s:if>
					</div>
					</div>
				</s:iterator>
			</div>
		</s:if>
		<s:else>
			<div class="grid_14 th thb">
				You have not created fee particulars, Creating fee particulars is
				simple process and system would guide you.
			</div>
		</s:else>
	</div>
</s:if>
<s:elseif test='%{anyTitle == "terms"}'>
	<div class="grid_14" id="stepHostel">
		<s:url id="doAddNewTermsList" action="ajaxDoAddTermsSettings"
			includeParams="all" escapeAmp="false" namespace="/hostel">
			<s:param name="tempId" value="%{id}" />
		</s:url>
		<sj:a href="%{doAddNewTermsList}" indicator="indicator"
			targets="feeSettingContent" button="false" cssClass="linkRight">Create Fee Term</sj:a>
		<h1>
			Current Fee Terms
		</h1>
		<s:if test="%{hostelTermsList!= null && !hostelTermsList.isEmpty()}">
			<div class="noteFont grid_14">
				<div class="grid_1">
					<span class="noteMassage"> Note:</span>
				</div>
				<div class="grid_13">
					You can edit/update existing fee terms settings by clicking on edit
					pen icon in each row at right side.
				</div>
			</div>
			<div class="grid_14" align="right" data-target="feeTermsCont">
				<jsp:include
					page="/WEB-INF/pages/common/ajaxViewPaginationOptions.jsp"></jsp:include>
			</div>
			<div class="grid_14 th">
				<div class="grid_12">
					<div class="grid_3 termNameDiv sortHeader divArrow">
						Term Name
					</div>
					<div class="grid_2 startMonthDiv sortHeader divArrow">
						Start Month
					</div>
					<div class="grid_2 endMonthDiv sortHeader divArrow">
						End Month
					</div>
					<div class="grid_2 dueDateDiv sortHeader divArrow">
						Due Date
					</div>
					<div class="grid_3">
						<div class="grid_2 reminderDiv sortHeader divArrow">
							Reminder
							<!-- before Due date -->
						</div>
					</div>
				</div>
				<div class="grid_1">
					Edit
				</div>
				<div class="grid_1">
					Delete
				</div>
			</div>
			<div id="feeTermsCont">
				<s:iterator value="hostelTermsList">
				<div hostelTermName="<s:property value='hostelTermName' />" fromMonthName="<s:property value='fromMonthName' />" toMonthName="<s:property value='toMonthName' />" dueDateStr="<s:property value='dueDateStr' />"  noOfDays="<s:property value='noOfDays' />" class="item">
					<div class="grid_14 row">
						<div class="grid_12">
							<div class="grid_3">
								<s:property value="hostelTermName" />
							</div>
							<div class="grid_2">
								<s:property value="fromMonthName" />
							</div>
							<div class="grid_2">
								<s:property value="toMonthName" />
							</div>
							<div class="grid_2">
								<s:property value="dueDateStr" />
							</div>
							<div class="grid_3">
								<s:property value="noOfDays" />
							</div>
						</div>
						<div class="grid_1">
							<s:url id="doEditHostelTerms" action="ajaxEditManageHostelTerms"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="hostelTermId" value="%{id}" />
								<s:param name="academicYearId" value="%{academicYearId}" />
							</s:url>
							<sj:a href="%{doEditHostelTerms}" indicator="indicator"
								targets="feeSettingContent" cssClass="normalEdit" title="Edit">
							</sj:a>
							&nbsp;
						</div>
						<div class="grid_1">
							<s:url id="deleteFeeTerms" action="ajaxDeleteFeeTerms"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="hostelTerms.id" value="%{id}" />
							</s:url>
							<s:div cssStyle="margin-top:3px;" cssClass="close"
								id='%{deleteFeeTerms}' theme="simple" onclick="javascript:confirmDialogWithTarget(this,'feeSettingContent');"
								title="Delete this Fee term"></s:div>
						</div>
					</div>
					</div>
				</s:iterator>
			</div>
		</s:if>
		<s:else>
			<div class="grid_14 th thb">
				You have not created fee terms, Creating fee terms is simple process
				and system would guide you.
			</div>
		</s:else>
	</div>
</s:elseif>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator_dev.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
	$('#feeTermsCont').pagination();
	$('#feeParticularsCont').pagination();
	$('#feeCategoryCont').pagination();
	$.subscribe('feeFormValidation', function(event, data) {
		if ($('#addFee').valid()) {
			$('#stepHostelFees').show();
			return true;
		} else
			return false;
	});
	$.subscribe('doInitAddFee', function(event, data) {
		$('#stepHostelFees').show();
	});
	$.subscribe('doInitAddFee', function(event, data) {
		$('#stepHostelFees').show();
	});
});

var timeInverse=1;
$('div.categoryDiv').click(function () {
	//var subjectName= str.sort( case_insensitive_comp );
    $('div#feeCategoryCont>div.item').sortElements(function (a, b) {return ($(a).attr('categoryName').toLowerCase() > $(b).attr('categoryName').toLowerCase() ? 1 : -1) * timeInverse; });
    updateDirectionArrows($(this), timeInverse);
    $("#feeCategoryCont").pagination();
    timeInverse = timeInverse * -1;
    return false;
});
var feeDiv=1;
$('div.feeDiv').click(function () {
    $('div#feeParticularsCont>div.item').sortElements(function (a, b) {return ($(a).attr('hostelFeeType').toLowerCase() > $(b).attr('hostelFeeType').toLowerCase() ? 1 : -1) * feeDiv; });
    updateDirectionArrows($(this), feeDiv);
    $("#feeParticularsCont").pagination();
    feeDiv = feeDiv * -1;
    return false;
});
var termName=1;
$('div.termNameDiv').click(function () {
    $('div#feeTermsCont>div.item').sortElements(function (a, b) {return ($(a).attr('hostelTermName').toLowerCase() > $(b).attr('hostelTermName').toLowerCase() ? 1 : -1) * termName; });
    updateDirectionArrows($(this), termName);
    $("#feeTermsCont").pagination();
    termName = termName * -1;
    return false;
});
var startMonth=1;
$('div.startMonthDiv').click(function () {
    $('div#feeTermsCont>div.item').sortElements(function (a, b) {return ($(a).attr('fromMonthName').toLowerCase() > $(b).attr('fromMonthName').toLowerCase() ? 1 : -1) * startMonth; });
    updateDirectionArrows($(this), startMonth);
    $("#feeTermsCont").pagination();
    startMonth = startMonth * -1;
    return false;
});
var endMonth=1;
$('div.endMonthDiv').click(function () {
    $('div#feeTermsCont>div.item').sortElements(function (a, b) {return ($(a).attr('toMonthName').toLowerCase() > $(b).attr('toMonthName').toLowerCase() ? 1 : -1) * endMonth; });
    updateDirectionArrows($(this), endMonth);
    $("#feeTermsCont").pagination();
    endMonth = endMonth * -1;
    return false;
});


var reminder=1;
$('div.reminderDiv').click(function () {
    $('div#feeTermsCont>div.item').sortElements(function (a, b) {return (parseInt($(a).attr('noOfDays')) > parseInt($(b).attr('noOfDays')) ? 1 : -1) * reminder; });
    updateDirectionArrows($(this), reminder);
    $("#feeTermsCont").pagination();
    reminder = reminder * -1;
    return false;
});

var startDate=1;
$('div.dueDateDiv').click(function () {
    $('div#feeTermsCont>div.item').sortElements(function (a, b) {return ($(a).attr('dueDateStr') > $(b).attr('dueDateStr') ? 1 : -1) * startDate; });
    updateDirectionArrows($(this), startDate);
    $("#feeTermsCont").pagination();
    startDate = startDate * -1;
    //alert(timeInverse);
    return false;
});
</script>