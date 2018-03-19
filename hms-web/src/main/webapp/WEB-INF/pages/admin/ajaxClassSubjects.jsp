<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxAddClassTimeTable" id="classTimeTable" method="POST"
	theme="css_xhtml" namespace="/admin">
	<s:hidden name="classId"></s:hidden>
	<table style="width: 100%;" border="1px;" cellspacing="0"
		class="striped" cellpadding="0" align="center">
		<caption>
			<s:property value="studyClass.classAndSection" />
			Timetable
		</caption>
		<tbody>
			<tr align="center" valign="middle">
				<td align="center" valign="middle">
					&nbsp;
				</td>
				<td align="center" valign="middle">
					P1
				</td>
				<td align="center" valign="middle">
					P2
				</td>
				<td align="center" valign="middle">
					P3
				</td>
				<td align="center" valign="middle">
					P4
				</td>
				<td align="center" valign="middle">
					P5
				</td>
				<td align="center" valign="middle">
					P6
				</td>
				<td align="center" valign="middle">
					P7
				</td>
				<td align="center" valign="middle">
					P8
				</td>
			</tr>
			<tr align="center" valign="middle" id="'Monday'" >
				<td align="center" valign="middle">
					Mon
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListMonday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Monday','1');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListMonday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Monday','2');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListMonday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Monday','3');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListMonday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Monday','4');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListMonday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Monday','5');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListMonday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Monday','6');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListMonday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Monday','7');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListMonday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Monday','8');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
			</tr>
			<tr align="center" valign="middle" id="'TuesDay'">
				<td align="center" valign="middle">
					Tue
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListTuesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'TuesDay','1');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListTuesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'TuesDay','2');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListTuesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'TuesDay','3');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListTuesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'TuesDay','4');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListTuesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'TuesDay','5');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListTuesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'TuesDay','6');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListTuesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'TuesDay','7');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListTuesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'TuesDay','8');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
			</tr>
			<tr align="center" valign="middle" id="'WednesDay'">
				<td align="center" valign="middle">
					Wed
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListWednesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'WednesDay','1');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListWednesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'WednesDay','2');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListWednesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'WednesDay','3');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListWednesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'WednesDay','4');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListWednesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'WednesDay','5');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListWednesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'WednesDay','6');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListWednesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'WednesDay','7');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListWednesDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'WednesDay','8');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
			</tr>
			<tr align="center" valign="middle" id="'ThursDay'">
				<td align="center" valign="middle">
					Thu
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListThursDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'ThursDay','1');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListThursDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'ThursDay','2');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListThursDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'ThursDay','3');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListThursDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'ThursDay','4');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListThursDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'ThursDay','5');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListThursDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'ThursDay','6');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListThursDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'ThursDay','7');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListThursDay" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'ThursDay','8');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
			</tr>
			<tr align="center" valign="middle" id="'Friday'" >
				<td align="center" valign="middle">
					Fri
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListFriday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Friday','1');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListFriday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Friday','2');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListFriday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Friday','3');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListFriday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Friday','4');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListFriday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Friday','5');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListFriday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Friday','6');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListFriday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Friday','7');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
				<td align="center" valign="middle">
					<s:select id="subjectsListFriday" list="studyClass.subjects" listKey="id"
						listValue="description" label="Select Resource"
						cssClass="required" required="true" labelSeparator="yes"
						labelposition="no" name="serviceId" headerKey="" onchange="javascript:addTimeTable(this.value,'Friday','8');"
						headerValue="- Select -" requiredposition="first" theme="simple">
					</s:select>
				</td>
			</tr>
		</tbody>
	</table>
	<sj:submit   id="submitButton" targets="classSubjects" value="submit"
		cssClass="submit small" formIds="classTimeTable" indicator="indicator"
		title="add Time Table" cssStyle="cursor:pointer" onClickTopics="formValidation" onSuccessTopics="adTimeTable" />
</s:form>
<script type="text/javascript">
	function addTimeTable(subject, day, period) {
		var divname = "subjectsList";
		var className='<s:property value="studyClass.className" />';
		var section='<s:property value="studyClass.section" />'
		var fullDivName = divname + day;
		$('#' + fullDivName).find('option:selected').each( function() {
			$.ajax( {
						type  : "POST",
						url   : jQuery.url.getChatURL("/admin/ajaxAddSubjectstoDay.do"),
						data  : "day="+day+"&subject="+subject+"&className="+className+"&periodName="+period+"&section="+section, 
						cache : true
					});
		});

	}
</script>
<script type="text/javascript">
$.subscribe('formValidation', function(event, data) {
         if ($('#classTimeTable').valid())
         {
         	 return true;
         }
          else
          	 return false;
   });
 </script> 