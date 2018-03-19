<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<b style="margin-left: 10px;">Search Categories</b><br/>
<div class="grid_11" id="searchByName" style="margin-bottom:20px">
<s:form id="searchCategoryByName" 
			action="ajaxSearchByCategoryName" theme="css_xhtml">		
				<sj:textfield name="scrhTxt" id="categoryName" value="Enter Name"
					cssClass="text small required" required="true" 
					maxlength="60" cssStyle="width:170px; float:left; color:#CCCCCC; text-align:center"
					 onfocus="clearText(this)" onblur="clearText(this)"></sj:textfield>
				<sj:submit   targets="searchCategoriesList" value="Find Categories" 
					cssClass="submit long" indicator="indicator" cssStyle="margin-top: 1px; margin-left:20px;" 
					onClickTopics="searchByCategoryNameFormValidation" formIds="searchCategoryByName" resetForm="true"/>
</s:form>
</div>
    <div class="grid_11" id="searchCategoriesList">
		<jsp:include page="categoriesList.jsp" />
	</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script language="JavaScript" type="text/javascript">
	function clearText(field){
       if (field.defaultValue == field.value) field.value = '';
       else if (field.value == '') field.value = field.defaultValue;
    }
</script>

