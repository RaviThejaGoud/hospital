<%@ include file="/common/taglibs.jsp"%>
<div id="popupBody">
<s:form action="addMenu" id="addMenu" method="post" theme="xhtml" validate="true">
		
		<sj:textfield name="menu.name" id="name"  label="Menu Name" cssClass="text small" maxlength="40" required="true"/>
		
		<sj:radio id="showonMenu" list="{'Yes', 'No'}" name="menu.showonMenu" label="Show On Menu" cssClass="text small"/>
	<s:hidden name="website.id" />
	<s:param name="id" value="{website.id}"/>
	
	<div class="type-button">
	            <!--<sj:submit   targets="formResult" value="AJAX Submit" indicator="indicator"/>
				--><s:url id="simpleecho" value="addMenu.do">
				<s:param name="id" value="{website.id}"/>
				</s:url>
	            <sj:submit   href="%{simpleecho}" targets="sideMenu" value="Submit" indicator="indicator"/>
	        </div>
	
	
	<!--<sj:submit   type="submit" cssClass="submit small" 
		    indicator="indicator" value="Submit"  src="<c:url value='/images/btns.gif' />" />
--></s:form>
<img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>

</div>

