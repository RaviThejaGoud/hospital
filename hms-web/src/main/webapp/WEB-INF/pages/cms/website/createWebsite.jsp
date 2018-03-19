<%@ include file="/common/taglibs.jsp"%>
	
	<head>
    <sj:head jqueryui="true"/>
    <title>EMS | Dashboard</title>
  </head>
	
	<body />
			<div class="wrapper container_16">
				<!-- wrapper begins -->
			<div class="block grid_4" id="blockgrid">
				<jsp:include page="/WEB-INF/pages/cms/website/websiteLeftNav.jsp" />
			</div>

				<div class="block grid_12">

					<div class="block_head">
						<h2>
							Content
						</h2>

						<ul>
							<li>
								<a href="#">View Templates</a>
							</li>
							<li>
								<a href="#">Create user</a>
							</li>
						</ul>
					</div>
					<!-- .block_head ends -->

					<div class="block_content">
					<div id="result" class="result ui-widget-content ui-corner-all">Submit form bellow.</div>
					
					<ul id="formerrors" class="errorMessage"></ul>
					<s:form action="createWebsite" id="createWebsite" method="post" theme="css_xhtml" validate="true">
						<sj:textfield name="website.title" id="title"  label="Website Title" cssClass="text small" maxlength="40" required="true"/>
						
						<sj:textfield name="website.websiteUrl" id="websiteUrl"  label="Website Url" cssClass="text small" maxlength="50" required="true"/>
					
						<sj:textfield name="website.aliases" id="aliases"  label="Website Aliases" cssClass="text small" maxlength="50"/>
						
						<sj:datepicker name="website.startDate" id="websitestartDate"  label="Website StartDate" cssClass="text small" maxlength="50" showButtonPanel="true"/>
						
						<sj:datepicker name="website.expDate" id="websiteexpDate"  label="Website ExpDate" cssClass="text small" maxlength="50" showButtonPanel="true"/>
					
                    <sj:submit   type="submit" cssClass="submit small" validate="true" validateFunction="customeValidation"
		    		onBeforeTopics="removeErrors" onSuccessTopics="removeErrors" value="Submit"  src="<c:url value='/images/btns.gif' />" />
				</s:form>
				<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>

				</div>
				</div>
				<!-- .leftcol ends -->
			</div>
			
			<script type="text/javascript">
			
			$(document).ready( function() {
	$.subscribe('removeErrors', function(event,data) {
		$('.errorLabel').html('').removeClass('errorLabel');
		$('#formerrors').html('');
	});
});	

function customeValidation(form, errors) {
	
	//List for errors
	var list = $('#formerrors');
	alert(list);
	
	//Handle non field errors 
	if (errors.errors) {
		$.each(errors.errors, function(index, value) { 
			list.append('<li>'+value+'</li>\n');
		});
	}
	
	//Handle field errors 
	if (errors.fieldErrors) {
		$.each(errors.fieldErrors, function(index, value) { 
			var elem = $('#'+index+'Error');
			if(elem)
			{
				elem.html(value[0]);
				elem.addClass('errorLabel');
			}
		});
	}
}
			</script>
