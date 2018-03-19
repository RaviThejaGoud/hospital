<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/json2.js">
</script>
<div id="schoolContent" class="grid_14 commonFormTabs">
	 <s:form action="ajaxAddHostelFee" id="addClassFee" method="post" name="addClassFee"
		theme="css_xhtml" namespace="/hostel">
		<s:hidden name="hiddenSelectedIds" value="%{className.id}" id="test" ></s:hidden>
		<s:hidden name="tempId1" value="%{anyId}" id="category"></s:hidden>
		<s:hidden name="tempString" cssClass='tempString' />
		<h1>
			Assign Fee To Class <s:property value="className.className" />
		</h1>
		<p>
			The fees, that will be assigned through this form, are applicable for all sections of the selected classes.
		</p>
		<fieldset>
			<div class="grid_13">
				<div class="grid_3">
						<label>Select Category:</label>
				</div>
				<div class="grid_10">
					<s:if
						test="%{hostelCategoriesList != null || !hostelCategoriesList.isEmpty}">
						<s:checkboxlist list="hostelCategoriesList" theme="ems"
							name="hostelTermchkBoxSelectedIds" listKey="id" listValue="categoryName"
							cssClass="check_all" id="hostelTermchkBoxSelectedIds"/>
					</s:if>
				</div>
			</div>
			<s:if
				test="%{classFeeTypeList != null || !classFeeTypeList.isEmpty && hostelTermsList!= null || !hostelTermsList.isEmpty}">
				<div class="grid_13 header">
					<div class="grid_4">
						<label class="labelRight">
							Fee Type
						</label>
					</div>
					<s:if test="%{hostelTermsList!= null || !hostelTermsList.isEmpty}">
						<s:iterator status="term" value="hostelTermsList">
							<div class="grid_2">
								<label>
									<s:property value="hostelTermName" />
								</label>
							</div>
						</s:iterator>
					</s:if>
				</div>
				<div id="classFeeTypesContent">
					<s:iterator value="classFeeTypeList">
						<div class="grid_13">
							<div class="grid_4">
								<span id='<s:property value='feeTypeId'/>' class='hostelFeeTypeId'></span>
								<input type="hidden" name="hostelFeeTypeId" value="<s:property value='feeTypeId'/>">
								<label class="labelRight">
									<s:property value="feeType" />:
								</label>
							</div>
							<s:iterator value="hostelTermsList">
								<div class="grid_2">
									<sj:textfield name="feeAmount" value="" id="%{id}~%{feeTypeId}"
										labelposition="top" cssClass="numeric textfield feeAmount"
										cssStyle="width:65px" maxlength="6"></sj:textfield>
								</div>
							</s:iterator>
						</div>
					</s:iterator>
				</div>
			</s:if>
			<div class="grid_12">
				<div class="grid_12">
					If the above fees are applicable/same for other classes, Please
					select applicable classes.
				</div>
				<div class="grid_13">
					<s:if
						test="%{tempActivePrekgClassList!= null || !tempActivePrekgClassList.isEmpty}">
						<s:checkboxlist list="tempActivePrekgClassList"  theme="ems"
							name="chkBoxSelectedIds" listKey="id" listValue="className"
							cssClass="check_all" id="chkBoxSelectedIds" />
					</s:if>
				</div>
			</div>
			<div class="grid_13 alpha">
				<s:url id="doCancelClassFee" action="ajaxDoHostelFeeCategory"
					includeParams="all" escapeAmp="false" namespace="/hostel">
					<s:param name="tempId1" value="%{anyId}"></s:param>
					</s:url>
				<sj:a href="%{doCancelClassFee}" cssClass="cancelButton" 
					indicator="indicator" targets="stepHostelClassFee">Cancel</sj:a>
				<sj:submit   targets="stepHostelClassFee" value="Submit" id="submitButton"
					indicator="indicator" cssClass="submit small" formIds="addClassFee"
					onClickTopics="hostelFeeValidation" validate="false"/>
				<img alt="Loading..."
					src="${pageContext.request.contextPath}/images/indicator.gif"
					id="indicator">
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(
		function() {
			$('.numeric').numeric();
			var classId = $('#test').val();
			var hostelCategoryId=$('#category').val();
			var feeURL = jQuery.url.getChatURL("/hostel/ajaxEditClassHostelFeeDetails.do?classId="+ classId+"&hostelCategoryId="+hostelCategoryId);
			//alert(feeURL);
			$.ajax( {
				url : feeURL,
				cache : false,
				dataType : 'json',
				success : function(response) {
					if (response.data) {
						var data = response.data;
						var hostelFeeTypeId;
						var hostelTermId;
						var feeAmount;
						var hostelFeeId;
						var studentPaymentId;
						var inputObj;
						if (data.length >= 1) {
							for ( var i = 0; i < data.length; i++) {
								hostelFeeTypeId = data[i].hostelFeeTypeId;
								hostelTermId = data[i].hostelTermId;
								feeAmount = data[i].amount;
								hostelFeeId = data[i].hostelFeeId;
								studentPaymentId = data[i].studentPaymentId;
								inputObj = $('input[id^=' + hostelTermId + '~'+ hostelFeeTypeId + ']');
								if (inputObj) {
									inputObj.val(feeAmount);
									inputObj.attr('id', hostelTermId + '~'+ hostelFeeTypeId + '~' + hostelFeeId);
									if(studentPaymentId !=0){
										inputObj.attr('disabled',true);
									}
								}
							}
						}
					}
				}
			});
			$.subscribe('hostelFeeValidation', function(event, data) {
				if ($('#addClassFee').valid()) {
					var fieldErrorString = '';
					var hostelFeeTypeId = '';
					var hostelTermId = '';
					var feeAmount = '';
					var jsonObj = [];
					var objIds;
					var allids;
					var hostelCategoryId=$('#category').val();
					$('input[name="feeAmount"]').each(function() {
						feeAmount = $(this).val();
						if (!isNonEmpty(feeAmount)) {
							feeAmount = "0";
						}
						objIds = $(this).attr('id');
						if (isNonEmpty(objIds)) {
							allids = objIds.split('~');
							if (allids.length > 2) {
								jsonObj.push( {
									"hostelFeeId" : allids[2],
									"hostelFeeTypeId" : allids[1],
									"hostelTermId" : allids[0],
									"feeAmount" : feeAmount
								});
							} else {
								jsonObj.push( {
									"hostelFeeId" : "0",
									"hostelFeeTypeId" : allids[1],
									"hostelTermId" : allids[0],
									"feeAmount" : feeAmount
								});
							}
						}
					});
					var classIds = [];
					classIds.push($('#test').val());
					$('input[name=chkBoxSelectedIds]').each(function(){
						if ($(this).is(':checked'))
						{
							classIds.push($(this).val());
						}
					});
					var categoryIds=[];
					$('input[name=hostelTermchkBoxSelectedIds]').each(function(){
						if ($(this).is(':checked'))
						{
							categoryIds.push($(this).val());
						}
					});
					var jsono={"chkBoxSelectedIds" :classIds, "hostelTermchkBoxSelectedIds" : categoryIds, "data" : jsonObj}
					if(jsonObj.length > 0 && isNonEmpty(hostelCategoryId))
					{
						$.ajax( {
							url : "ajaxAddHostelFee.do",
							cache : false,
							data : "tempString=" + JSON.stringify(jsono)+"&tempId1="+hostelCategoryId,
							success : function(response) {
								$('#stepHostelClassFee').html(response);
							}
						});
					}
				else
				{
					event.originalEvent.options.submit=false;
					alert('Something gone wrong! Unable to read the response. Please reload the screen and try or contact system administrator');
				} 
				
			}
		});
	});
</script>