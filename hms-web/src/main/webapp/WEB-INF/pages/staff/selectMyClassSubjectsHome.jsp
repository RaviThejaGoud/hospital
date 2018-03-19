<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="block grid_12 omega">
	<div class="block_head">
		<h2>	My Students</h2>
		<div id="topMenu">
			<ul>
				<li style="background-image: none;padding-left: 0px;">
					<s:if test="%{selectboxMap != null && !selectboxMap.isEmpty()}">
						<label style="text-align: center;float:left;">Class:</label>	
							<s:select  list="selectboxMap" id="className" cssStyle="width:135px;margin:5px 5px 5px 5px;"
								cssClass="required" required="true" name="studyClass.id" 
								headerKey="" headerValue="- Select -"  theme="simple" 
								onchange="javascript:getAjaxDoGetStudent(this.value);"
								requiredposition="first">
							</s:select>	
					</s:if>
				</li>
			</ul>
			<!--<ul>
			
			<li>
					 Here is the list key studyClassId is studyClassId 
					<div class="tableactions" style="padding-bottom: 0px;">
						asffadsf<s:select list="selectboxMap"id="className"  label="Select Class"
							cssClass="required" required="true" name="studyClass.id"
							headerKey="" headerValue="- Select -"  theme="simple"
							onchange="javascript:getAjaxDoGetSubjects(this.value);"
							requiredposition="first">
						</s:select>
					</div>
			</li>
			--><!--<li>
				<s:url id="urlMyStudentsLink" action="ajaxDoGetMyClass" />
				<sj:a href="%{urlMyStudentsLink}"
					targets="myStudentList" indicator="indicator">Other Students</sj:a>
			</li>
				--><!--<li>
			    <s:url id="urlDownloadSheet" action="ajaxDoDownloadMarkSheet" />
				<sj:a href="%{urlDownloadSheet}" targets="marksTemplate"
					indicator="indicator">Download </sj:a>
					
				  |
				     
				<s:url id="urlUploadSheet" action="ajaxDoUploadMarkSheet" />
				<sj:a href="%{urlUploadSheet}" targets="marksTemplate"
					indicator="indicator">Upload </sj:a>
					 Marks Sheet
				</li>
				--><!--<li>
					<s:url id="urlUploadSheet" action="ajaxDoUploadMarkSheet" />
					<sj:a href="%{urlUploadSheet}" targets="marksTemplate"
						indicator="indicator">Download Marks Sheet</sj:a>
				</li>
			</ul>-->
		</div>
	</div>
	<div class="block_content" id="myStudentList">
		<jsp:include page="/WEB-INF/pages/staff/viewMyStudentsLists.jsp" />
	</div>
</div>

<script type="text/javascript">
changePageTitle('My Students List');
function getAjaxDoGetStudent(studyClassId) {
		var pars = "studyClassId=" + studyClassId;
		$("#myStudentList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/staff/ajaxDoGetMyStudents.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#myStudentList").html(html);
			}
		});
	}

</script>