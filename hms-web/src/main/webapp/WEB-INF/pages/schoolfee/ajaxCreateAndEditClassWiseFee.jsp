<%@ include file="/common/taglibs.jsp"%>
<div id="stepClassFee" class="tab-content">
	<s:if test="%{classList!= null || !classList.isEmpty}">
		<!--<span id="categoryId" class="<s:property value='anyId'/>"></span>-->
		<s:form id="addSchoolWideBooks" action="#" method="post"
			cssClass="form-horizontal" theme="simple">
			<div style="float:right">
				<div class="row">
					<div class="col-md-1">
						<s:url id="backClassFee" action="ajaxDoFeeCategory" includeParams="all" escapeAmp="false">
								<s:param name="anyId" value="%{anyTitle}"></s:param>
							</s:url>
							<sj:a href="%{backClassFee}" id="NormalFee" cssClass="btn default" cssStyle="float:right;margin-right:20px;" targets="feeSettingsContent">
							 Back</sj:a>
							
					</div>
				</div>
			</div>
			<br />
			<br />
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label class="control-label col-md-5"> Select Class : </label>
						<div class="col-md-6">
							<s:select list="classList" listKey="id"
								cssClass="required form-control input-mini"
								listValue="className" id="classNameId" name="classId"
								theme="simple" headerKey="null" headerValue="-Select Class-">
							</s:select>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5"> Select Setting Type
							: </label>
						<div class="col-md-7">
							<s:select list="objectList" listKey="id" listValue="settingName"
								id="settingFee" name="tempId" theme="simple"
								cssClass="required form-control input-mini" headerKey="null"
								headerValue="-Select Fee Setting-"
								onchange="javascript:getAjaxFeeSettingCateogy(this.value);" />
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-6"> Select Category : </label>
						<div class="col-md-6">
							<s:select list="tempList1" listKey="id" listValue="categoryName"
								id="category" name="anyId" theme="simple"
								cssClass="required form-control input-mini" headerKey="null"
								onchange="javascript:getAjaxFeeCateogy(this.value);"
								headerValue="-Select Category-" />
						</div>
					</div>
				</div>
			</div>
			<div></div>
		</s:form>
		<div id="classWiseFeeEdit"></div>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently there are no category.</div>
	</s:else>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if (getUrlVars()["target"] != "undefined") {
			if(getUrlVars()["target"] == "ADMS.ajaxify%20ADST" || getUrlVars()["target"] == "ADMS.ajaxify ADST") {
				$('a#NormalFee').hide();
				$('a#AdmissionClassFee').show();
			} else {
				$('a#NormalFee').show();
				$('a#AdmissionClassFee').hide();
			}
		} else {
			$('a#NormalFee').show();
			$('a#AdmissionClassFee').hide();
		}
		var categoryId = $('#category').val();
		if (isNonEmpty(categoryId)) {
			getAjaxFeeCateogy(categoryId);
		}
	});
	function getAjaxFeeSettingCateogy() {
		var categoryId = $("select#category option:eq(1)").attr("selected",
				"selected");
		if (isNonEmpty(categoryId.val())) {
			getAjaxFeeCateogy(categoryId.val());
		}
	}
	$('select[name="classId"]').change(
			function() {
				var classNameId = $(this).val();
				if (isNonEmpty(classNameId) && classNameId != 0) {
					var catgId = $("select#category option:eq(1)").attr(
							"selected", "selected");
					if (isNonEmpty(catgId.val())) {
						getAjaxFeeCateogy(catgId.val());
					}
				}
			});
	function getAjaxFeeCateogy(categoryId) {
		var tempId = null;
		var feeSettingId = $('#settingFee').val();
		var classNameId = $("#classNameId").val();
		if (isNonEmpty(classNameId) && isNonEmpty(categoryId)) {
			var pars = "classId=" + classNameId + "&tempId=" + feeSettingId + "&anyId=" + categoryId;
			$("#classWiseFeeEdit") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/schoolfee/ajaxDoSelectAddClassFee.do");
			$.ajax({
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#classWiseFeeEdit").html(html);
				}
			});
		}
	}
	function getUrlVars() {
		var vars = [], hash;
		var hashes = window.location.href.slice(window.location.href.indexOf('#') + 1).split('&');
		for (var i = 0; i < hashes.length; i++) {
			hash = hashes[i].split('=');
			vars.push(hash[0]);
			vars[hash[0]] = hash[1];
		}
		return vars;
	}
</script>