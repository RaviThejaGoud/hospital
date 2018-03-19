<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{tempString == 'categories'}">
	<s:if
		test="%{schoolCategoriesList != null && !schoolCategoriesList.isEmpty()}">
		<h4 class="bold pageTitle">
			Categories
		</h4>
		<s:if
			test='%{#session.previousYear == "N" && schoolCategoriesList.size > 1}'>
			<p>
			<span class="label label-danger"> NOTE : </span>&nbsp;&nbsp; 
				You can edit a category by clicking on edit button.</p>
		</s:if>
		<div class="spaceDiv"></div>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Category
					</th>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="schoolCategoriesList">
					<tr>
						<td>
							<s:property value="categoryName" />
						</td>
						<s:if test='%{categoryName != "General"}'>
							<td>
								<s:if test='%{#session.previousYear == "N"}'>
									<a data-toggle="modal" href="#categoryresponsive"
										class="btn btn-xs purple"
										onclick="javascript:PopupCategoryDetials(<s:property value="%{id}" />,<s:property value="%{academicYearId}" />);"><i
										class="fa fa-edit"></i>Edit </a>
								</s:if>
							</td>
							<td>
								<s:if test='%{#session.previousYear == "N"}'>
									<s:url id="removeSchoolCategory"
										action="ajaxRemoveSchoolCategory" includeParams="all"
										escapeAmp="false">
										<s:param name="schoolCategory.id" value="%{id}" />
									</s:url>
									<s:div cssClass="btn btn-xs red"
										onclick="javascript:confirmDialogWithTarget(this,'feeSettingsContent');"
										id='%{removeSchoolCategory}' title="Delete this category">
										<i class="fa fa-times"></i>Delete
								</s:div>
								</s:if>
							</td>
						</s:if><s:else><td>-</td><td>-</td></s:else>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Categories are not created.
		</div>
	</s:else>
</s:if>
<div id="deleteSettingParticular">
<s:if test="%{tempString == 'feeParticulars'}">
	<h4 class="bold pageTitle">
		 Fee Particulars
	</h4>
	<p>
	<span class="label label-danger"> NOTE : </span>&nbsp;&nbsp; You can
			edit fee particular by click edit pen icon in each row at right
			side.</p>
		<div class="spaceDiv"></div>&nbsp;
		<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Select Fee Setting Type :
				</label>
				<div class="col-md-6">
					<s:select list="objectList" listKey="id" listValue="settingName"
						id="settingFee" name="tempId" theme="simple" headerKey="null"
						cssClass="required form-control input-medium"
						onchange="javascript:getAjaxGetFeeSettingParticular(this.value);"
						headerValue="-Select Fee Setting Type-" />
				</div>
			</div>
		</div>
	</div>
	<div id="allFeeSettingParticular">
	</div>
</s:if>
</div>
<s:if test="%{tempString == 'feeTerms'}">
	<h4 class="bold pageTitle">
		 Fee Terms
	</h4>
	<p>
	<span class="label label-danger"> NOTE : </span>&nbsp;&nbsp; 
		You can edit terms by clicking on edit pen icon in each row.</p>
	<div class="spaceDiv"></div>&nbsp;
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Select Fee Setting Type :
				</label>
				<div class="col-md-6">
					<s:select list="objectList" listKey="id" listValue="settingName"
						id="settingFeeTerm" name="tempId" theme="simple" headerKey="null"
						cssClass="required form-control input-medium"
						onchange="javascript:getAjaxGetFeeSettingTerms(this.value);"
						headerValue="-Select Fee Setting Type-">
					</s:select>
				</div>
			</div>
		</div>
	</div>
	<div class="spaceDiv"></div>
	<div id="allFeeSettingTerms"></div>
</s:if>

<div id="categoryresponsive"></div>
<script type="text/javascript">
	TableAdvanced.init();
	UIExtendedModals.init();
	$(document).ready(function() {
		if ('<s:property value="tempString"/>' == "categories") {
			changePageTitle("Manage Categories");
		} else if ('<s:property value="tempString"/>' == "feeParticulars") {
			changePageTitle("Manage Fee Particulars");
		} else if ('<s:property value="tempString"/>' == "feeTerms") {
			changePageTitle("Manage Fee Terms");
		}
		var settingFeeId = $('#settingFee').val();
		if (isNonEmpty(settingFeeId)) {
			getAjaxGetFeeSettingParticular(settingFeeId);
		}
		var settingFeeTermId = $('#settingFeeTerm').val();
		if (isNonEmpty(settingFeeTermId)) {
			getAjaxGetFeeSettingTerms(settingFeeTermId);
		}
	});
	function getAjaxGetFeeSettingParticular(feeSettingId) {
		var tempString = "feeParticulars";
		if (isNonEmpty(feeSettingId)) {
			var pars = "tempId=" + feeSettingId + "&tempString=" + tempString;
			$("#allFeeSettingParticular")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url
					.getChatURL("/schoolfee/ajaxSchoolFeeSettingParticular.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#allFeeSettingParticular").html(html);
				}
			});
		}
	}
	function getAjaxGetFeeSettingTerms(settingFeeTermId) {
		var tempString = "feeTerms";
		if (isNonEmpty(settingFeeTermId)) {
			var pars = "tempId=" + settingFeeTermId + "&tempString=" + tempString;
			$("#allFeeSettingTerms")
					.html(
							'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url
					.getChatURL("/schoolfee/ajaxSchoolFeeSettingTerms.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#allFeeSettingTerms").html(html);
				}
			});
		}
	}
	function PopupCategoryDetials(id, academicYearId) {
		var pars = "schoolCategory.id=" + id + "&academicYearId=" + academicYearId;
		var url = jQuery.url.getChatURL("/schoolfee/ajaxDoCreateCategory.do");
		$('#categoryresponsive')
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#categoryresponsive").html(html);
			}
		});
	}
</script>
