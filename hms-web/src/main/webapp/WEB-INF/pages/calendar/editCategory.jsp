<%@ include file="/common/taglibs.jsp"%>
<td colspan="3">
	<s:form id="editCategory" action="ajaxEditCategory" method="post"
		theme="css_xhtml">
		<s:hidden name="anyId" />
		<sj:textfield name="category.name" id="categoryName"
			label="Category Name" cssClass="required text small" maxlength="40"></sj:textfield>
		<sj:textarea name="category.description" id="categoryDescription"
						label="Description" cssClass="text small" rows="5" cols="35"
						cssStyle="width: 85%;"></sj:textarea>

		<sj:submit   targets="categoryEvents" value="Submit"
			onClickTopics="categoryformValidation1" cssClass="submit small"
			indicator="indicator" formIds="editCategory" />

		<s:url id="doEditCategory" action="ajaxDoEditCategory"
			includeParams="all" escapeAmp="false">
			<s:param name="id" value="{category.id}" />
		</s:url>
		<sj:a href="%{doEditCategory}" onCompleteTopics="doInitEditCategory"
			cssClass="cancelButton" indicator="indicator"
			targets="editCategory%{category.id}" button="false"
			buttonIcon="ui-icon-plus">
			Cancel
		</sj:a>

	</s:form>
</td>
<script type="text/javascript">
	$(document).ready(function() {
		$.subscribe('categoryformValidation1', function(event, data) {
			if ($('#editCategory').valid())
				return true;
			else
				return false;
		});
	});
</script>