<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{absentList != null && !absentList.isEmpty()}">
	<s:iterator value="absentList">
		<div class="display_box" align="left">
			<s:url id="editGroup" action="ajaxDoGetDisplayName"
				includeParams="all" escapeAmp="false">
				<s:param name="id" value="{accountId}" />
			</s:url>
			<sj:a href="%{editGroup}" targets="displayPersonName"
				indicator="indicator" button="false" buttonIcon="ui-icon-pencil">
				
					<s:property value="personFullName" />
			</sj:a>
		</div>
	</s:iterator>
</s:if>
<s:else>
 <script type="text/javascript">
		$("#displaySearchResult").text("Currently there are no users.");
		var height_diff = 20 + "px";
        document.getElementById("displaySearchResult").style.height = height_diff;
 </script>
</s:else>


<style type="text/css">
* {
	margin: 0px
}

#searchbox {
	width: 250px;
	border: solid 1px #000;
	padding: 3px;
}

#display {
	width: 250px;
	display: none;
	float: right;
	margin-right: 30px;
	border-left: solid 1px #dedede;
	border-right: solid 1px #dedede;
	border-bottom: solid 1px #dedede;
	overflow: hidden;
}

.display_box {
	padding: 4px;
	border-top: solid 1px #dedede;
	font-size: 12px;
	height: 30px;
	width: 310px;
}

.display_box:hover {
	background: #3b5998;
	color: #FFFFFF;
}

.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}

.paginator {
	text-align: center;
	color: #FFF;
}
</style>
