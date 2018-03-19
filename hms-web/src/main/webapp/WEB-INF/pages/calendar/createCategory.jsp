<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.autoCheck.js"></script>

        <s:form id="createCategory" action="ajaxCreateCategory" theme="css_xhtml">
					
					<sj:textfield name="categoryName" id="categoryName" 
						required="true" label="Category Name" labelposition="top"
						cssClass="required text small" maxlength="40"></sj:textfield>
					<br/>
					<sj:textarea name="description" id="categoryDescription"
						label="Description" cssClass="text small" rows="5" cols="35"
						cssStyle="width: 85%;"></sj:textarea>
				
					<sj:submit   cssClass="submit small" value="Submit"
						indicator="indicator" button="true" targets="categoryEvents"
						buttonIcon="ui-icon-plus" onClickTopics="categoryFormValidation" formIds="createCategory"/>
					
					<s:url id="doCancelCategory" action="ajaxDoCancelCategory" includeParams="all" ></s:url>
					<sj:a href="%{doCancelCategory}"  cssClass="cancelButton"
		                indicator="indicator" targets="categoryEvents">Cancel</sj:a>
				</s:form>
<script language="JavaScript" type="text/javascript">
$.subscribe('categoryFormValidation', function(event, data) {
         if ($('#createCategory').valid())
         	 return true;
          else
          	 return false;
   });
</script>