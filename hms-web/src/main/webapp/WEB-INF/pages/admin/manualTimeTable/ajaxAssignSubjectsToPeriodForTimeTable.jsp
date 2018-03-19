<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/json2.js"></script>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{weekDayList != null && !weekDayList.isEmpty()}">
	<s:if test="%{timeTablePeriodList != null && !timeTablePeriodList.isEmpty()}">
		<s:if test="%{subjectsList != null && !subjectsList.isEmpty()}">
		<%-- <s:if test="%{timeTableDetailsCount > 0}">
			<div class="form-group">
            		<label class="control-label col-md-3"> 
                   	<span class="required">*</span>Do you want update timetable:
                   </label>
                   <div class="col-md-3" id="logoDiv" >
					<label class="radio">
                           <input type="checkbox" id="isCheck" class="make-switch" data-id="Y" data-value="N" data-on-color="success" data-on-text="Yes" data-off-text="No" data-off-color="warning">
               			<input type="hidden"  name="changeInTimeTable" value="N" id="changeInTimeTable">
                    </label>
                    </div>
        	</div>
        	<div class="row" id="selectDateDiv" style="display:none;">
        	<div class="span6">
				<div class="control-group">
					<label class="control-label">Select Date to update timetable:</label>
					<div class="controls">
						<div class="input-append">
							<input class="ui_date_picker_change_year_month m-wrap required" size="16" type="text" id="selectDate"
								name="startDate" onchange="getStudentAttendanceForStudyClass(this.value)" readonly="readonly" placeholder="MM/DD/YYYY"/>
							<span class="add-on"><i class="icon-calendar"></i> </span>
						 </div>
				    </div>
			  	</div>
			 </div>
			 </div>
			 <div id="attendance"></div> 
		</s:if> --%>
		<div class="table-scrollable">
			<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
				<thead>
					<tr>
						<th>
							<p style="text-align: center;">Day / Time</p>
						</th>
						<s:iterator value="timeTablePeriodList" status="stat">
							<th>
								<p style="text-align: center;"><s:property value='timeTablePeriodList[#stat.count-1][1]' /><br><s:property value='timeTablePeriodList[#stat.count-1][2]' />-<s:property value='timeTablePeriodList[#stat.count-1][3]' /></p>
							</th>
						</s:iterator>
					</tr>
				</thead>
				<tbody>
					<span id="toTalPeriodVal" hidden="true"><s:property value="%{timeTablePeriodList.size}"/></span>
					<span id="toTalAnotherPeriodListVal" hidden="true"><s:property value="%{objectList1.size}"/></span>
					<span id="toTalAnotherElectivePeriodListVal" hidden="true"><s:property value="%{objectList.size}"/></span>
					<% int i=1; %>
					<% int j=1; %>
					<s:iterator value="weekDayList" status="weekStat">
					<s:set name="weekId" value='%{id}' id="weekId"></s:set>
						<tr id="monthId<s:property value="id"/>" class="daySubjectPeriod">
							<td>
								<s:property value="%{dayName}" />
							</td>
							<s:iterator value="timeTablePeriodList" status="stat">
							<s:set name="periodId" value='%{timeTablePeriodList[#stat.count-1][0]}' id="periodId"></s:set>
								<td id="weekAndPeriodIds<s:property value='%{#weekId}'/><s:property value='%{#periodId}'/>" class="currentWeekAndPeriodId">
									<span id="eachPeriodVal" hidden="true"><s:property value="%{#stat.count}"/></span>
									<s:hidden id="weekId" value="%{#weekId}"></s:hidden>
									<s:hidden id="periodId" value="%{#periodId}"></s:hidden>
									<div id="labOrSubject">
										<s:select list="subjectsList" listKey="id" listValue="name" id="subjectId" headerKey="0" headerValue="- Select -" cssStyle="width:45%" disabled="false"
											name="%{#weekId}%{#periodId}" cssClass="form-control input-small" theme="simple" onchange="javascript:checkLabDetailsAndHide(this.value, '%{#weekId}%{#periodId}', '%{#weekId}','' );"/> 
										<div id="weekAndPeriodId<s:property value='%{#weekId}'/><s:property value='%{#periodId}'/>"></div>
									</div>
									<span id="eachTdVal" hidden="true"><%=i++%></span>
									<div id='displayAnotherLabGroup<%=i++%>'>
										<div id="displayAnotherLabDiv1" class="displayAnotherLabDivNewData" style="display: none;">
											<s:select list="objectList1" listKey="id" listValue="name" id="subjectId" headerKey="0" headerValue="- Select -" cssStyle="width:45%" disabled="false"
												name="anotherLab%{#weekId}%{#periodId}" cssClass="form-control input-small" theme="simple" onchange="javascript:checkLabDetailsAndHide(this.value, '%{#weekId}%{#periodId}', '%{#weekId}','C' );"/> 
											<div id="weekAndPeriodAnotherSubId<s:property value='%{#weekId}'/><s:property value='%{#periodId}'/>"></div>
										</div>
										<s:hidden name="anotherLab%{#weekId}%{#periodId}" id="weekPeriodsSubjectVal"></s:hidden>
									</div>
									<span id="eachTdElectiveVal" hidden="true"><%=j++%></span>
									<div id='displayElectiveGroup<%=j++%>'>
										<div id="displayAnotherElectiveDiv1" class="displayAnotherElectiveDivNewData" style="display: none;">
											<s:select list="objectList" listKey="id" listValue="name" id="subjectId" headerKey="0" headerValue="- Select -" cssStyle="width:45%" disabled="false"
												name="anotherElective%{#weekId}%{#periodId}" cssClass="form-control input-small" theme="simple" onchange="javascript:checkLabDetailsAndHide(this.value, '%{#weekId}%{#periodId}', '%{#weekId}','C' );"/> 
											<div id="weekAndPeriodAnotherSubId<s:property value='%{#weekId}'/><s:property value='%{#periodId}'/>"></div>
										</div>
										<s:hidden name="anotherElective%{#weekId}%{#periodId}" id="weekPeriodsSubjectVal"></s:hidden>
									</div> 
								</td>
							</s:iterator>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			</div>
			<div class="form-actions fluid" id="submitDiv">
				<div class="col-md-offset-2 col-md-6">
					<sj:submit cssClass="btn blue small" value="Submit" targets="subjectsListDiv" validate="true" resetForm="true"
						onBeforeTopics="addSubjectForPeriodValidation" formIds="addSubjectForPeriod" />
					<%-- <s:url id="cancelTimeTableButton" action="ajaxDoGetStudyClassList" namespace="/admin" includeParams="all" escapeAmp="false"></s:url>
					<sj:a href="%{cancelTimeTableButton}" cssClass="btn" indicator="indicator" targets="cancelTimeTableButtonDiv">Cancel</sj:a> --%>
				</div> 
			</div>
		</s:if>
		<s:else>
		<div class="alert alert-info">
			Subjects are not added for the selected course year.Please add subjects(Academics->Subjects).
		</div>
	</s:else>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Time-Table settings are not provided for the selected subject Please provide timetable settings.(settings->Timetable->Assign subjects to periods).
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		No Working days are selected, Please select working days in academic planner settings(Settings->Academic planner).
	</div>
</s:else>
<script type="text/javascript">
	$(document).ready(function() {
		//UIJQueryUI.init();
		//Metronic.init();
		FormAdvanced.init();
		//$('.numeric').numeric();
		var miniDate = '<s:property value="customerPreferencesObj.pastDateDaysCountInTimetableUpdate"/>';
		var maxDate ='<s:property value="customerPreferencesObj.futurDateDaysCountInTimetableUpdate"/>';
		var d = new Date();
		var month = d.getMonth()+1;
		var day = d.getDate();
		var todayDate = (month<10 ? '0' : '') + month + '/' +(day<10 ? '0' : '') + day +'/'+ d.getFullYear();
		$('#selectDate').datepicker("option", 'minDate',-miniDate);
		$('#selectDate').datepicker("option", 'maxDate',+maxDate);
		var timeTableDetailsCount = '<s:property value="timeTableDetailsCount" />';
		if(timeTableDetailsCount == 0){
			$("div#submitDiv").show();
		}else{
			var type = $("#changeInTimeTable").val();
			changeUploadBtn(type);
		}
		/* $("span.bootstrap-switch-handle-on,span.bootstrap-switch-handle-off").click(function(){
			if($(this).parents("div.bootstrap-switch").hasClass('bootstrap-switch-on')==true){
				$("div#logoDiv").find("div.bootstrap-switch").addClass("bootstrap-switch-on");
				$("div#logoDiv").find("div.bootstrap-switch").removeClass("bootstrap-switch-off");  
				$("input#changeInTimeTable").val("Y");
				changeUploadBtn($(this).parents("div.bootstrap-switch").find('input.make-switch').attr('data-id'));
			}
			else{
				$("div#logoDiv").find("div.bootstrap-switch").removeClass("bootstrap-switch-on");
				$("div#logoDiv").find("div.bootstrap-switch").addClass("bootstrap-switch-off"); 
				$("input#changeInTimeTable").val("N");
		   		changeUploadBtn($(this).parents("div.bootstrap-switch").find('input.make-switch').attr('data-value'));
			}
		});
		$("div#logoDiv").find('div.bootstrap-switch').click(function(){
			if($(this).hasClass('bootstrap-switch-on')==true){
				$("div#logoDiv").find("div.bootstrap-switch").addClass("bootstrap-switch-on");
				$("div#logoDiv").find("div.bootstrap-switch").removeClass("bootstrap-switch-off");  
				$("input#changeInTimeTable").val("Y");
				changeUploadBtn($(this).find('input.make-switch').attr('data-id'));
		    }else{
				$("div#logoDiv").find("div.bootstrap-switch").removeClass("bootstrap-switch-on");
				$("div#logoDiv").find("div.bootstrap-switch").addClass("bootstrap-switch-off"); 
				$("input#changeInTimeTable").val("N");
		   		changeUploadBtn($(this).find('input.make-switch').attr('data-value'));
			}
		}); */
		var startDateStr='<s:property value="startDate"/>';
		//var studyClassId1=$("#studyClassId").val();
		var studyClassId=$('select#studyClassId>option:selected').val();
		if(studyClassId>0 && timeTableDetailsCount>0){
			getSubjectPeriodDayWiseDetails(studyClassId);
		}
	});
	function getSubjectPeriodDayWiseDetails(studyClassId) {
		var pars = "studyClassId="+studyClassId;
		$.ajax( {
			url : jQuery.url.getChatURL("/timeTable/ajaxGetSubjectPeriodDayWiseDetails.do"),
			cache : false,
			data : pars,
			dataType : 'json',
			success : function(response) {
				if (isNonEmpty(response) && response.dayPeriodSubjectSettingsData) {
					var data = response.dayPeriodSubjectSettingsData;//alert('data:'+data);
					var subjectIds=[];
					if (data.length > 0) {
						for ( var i = 0; i < data.length; i++) {
							$('tr.daySubjectPeriod:visible').each(function(){
								$(this).find("td#weekAndPeriodIds"+ data[i].DAYID+ data[i].PERIODID).each(function(){//alert("td#weekAndPeriodIds"+ data[i].DAYID+ data[i].PERIODID);
									var subjectIds=data[i].INPUTVALUE.split(',');
									if(isNonEmpty(subjectIds)){
										var subjectLength=parseInt(subjectIds.length);
										if(subjectLength==1){
											//alert('test 1');
											$(this).find("div#labOrSubject").find('select#subjectId').val(subjectIds[0]);
										}else{
											//alert('test 2');
											var electiveSubOrNot=data[i].SUBJECTTYPE;
											$(this).find("div#labOrSubject").find('select#subjectId').val(subjectIds[0]);
											if("Y"==electiveSubOrNot){
												for(p=1;p<subjectLength;p++){
													$(this).find("div#displayAnotherElectiveDiv1").hide();
													preparingTheElectiveSubjects($(this));
													var val=(p+1);
													$(this).find("div#displayAnotherElectiveDiv"+val).find('select#subjectId').val(subjectIds[p]);
													if(p==1){
														$(this).find("div#weekAndPeriodId"+data[i].DAYID+ data[i].PERIODID).append("<span id='labSubjectCount' hidden='true'>"+(p-1)+"</span>").append("<span id='electiveSubCount' hidden='true'>"+p+"</span>");
													}
												}subjectLength=0;
											}else{
												for(g=1;g<subjectLength;g++){
													$(this).find("div#displayAnotherLabDiv1").hide();
													preparingTheLabSubjects($(this));
													var val=(g+1);
													$(this).find("div#displayAnotherLabDiv"+val).find('select#subjectId').val(subjectIds[g]);
													if(g==1){
														$(this).find("div#weekAndPeriodId"+data[i].DAYID+ data[i].PERIODID).append("<span id='labSubjectCount' hidden='true'>"+g+"</span>");
													}
												}subjectLength=0;
											}
										}
									}subjectIds="";
								});
							});
						}
					}
				}
			}
		});
	}
	function changeUploadBtn(uploadType) {
		if (uploadType == 'Y') {
			 $('div#submitDiv').show();
			 $('div#selectDateDiv').show();
		} else {
			 $('div#submitDiv').hide();
			 $('div#selectDateDiv').hide();
		}
	}
	function checkLabDetailsAndHide(subjectId,selectedTdVal,weekIdRow,currentVal){
		//var studyClassId = $('#studyClassId').val();
		var studyClassId=$('select#studyClassId>option:selected').val();
		//alert("studyClassId:"+studyClassId+" subjectId:"+subjectId);
		if(subjectId>0 && studyClassId>0){
			$("div#weekAndPeriodId").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "studySubjectId="+subjectId;
			url = jQuery.url.getChatURL("/timeTable/ajaxGetLabCountDetailsBySubjectId.do");
			$.ajax({
				url : url,
				cache : false,
				data : pars,
				success : function(response){
					if(isNonEmpty(currentVal)){
						$('div#weekAndPeriodAnotherSubId'+selectedTdVal).html(response);
					}else{
						$('div#weekAndPeriodId'+selectedTdVal).html(response);
					}
					var labSubCount=0;
					var anotherLabSubCount=0;
					var eachTdCount=0;
					var toTalPeriodVal=$('span#toTalPeriodVal').text(); 
					var anotherLabSubListCount=$('span#toTalAnotherPeriodListVal').text(); 
					var anotherElectiveSubListCount=$('span#toTalAnotherElectivePeriodListVal').text();
					//alert('anotherLabSubListCount'+anotherLabSubListCount);
					//alert('anotherElectiveSubListCount'+anotherElectiveSubListCount);
					var tdVal=1;
					var electiveSubCount=0;
					$('tr#monthId'+weekIdRow).each(function(){
							 $(this).find("td#weekAndPeriodIds"+selectedTdVal).each(function(){
								var inputNameVal=$(this).attr('id'); //alert("inputNameVal:-"+inputNameVal); Getting the td attr id value.
								if(isNonEmpty(inputNameVal)){
									var labSubjectCountDivId=inputNameVal.replace(/\D/g, '');  //inputNameVal.replace("s", "");
									if(labSubCount == 0){
										if(isEmptyVal($(this).find("div#weekAndPeriodId"+labSubjectCountDivId).find("span#labSubjectCount").text())){
											//labSubCount= 0;alert('if');
										}else{
											labSubCount=$(this).find("div#weekAndPeriodId"+labSubjectCountDivId).find("span#labSubjectCount").text();
											//alert('labSubCount'+labSubCount);
										}
									}
									if(isEmptyVal($(this).find("div#weekAndPeriodId"+labSubjectCountDivId).find("span#electiveSubCount").text())){
										electiveSubCount= 0;
									}else{
										electiveSubCount=$(this).find("div#weekAndPeriodId"+labSubjectCountDivId).find("span#electiveSubCount").text();
										//alert('electiveSubCount:'+electiveSubCount);
									}
									//alert("electiveSubCount:-"+electiveSubCount);
									/* var value= parseInt($(this).find("span#eachTdVal").text()) +1 ;
									var anotherLabSubjectCount = $(this).find("div#displayAnotherLabGroup"+value).find("div.displayAnotherLabDivNewData").length;
									if(isEmptyVal($(this).find("div#displayAnotherLabDiv"+anotherLabSubjectCount).find("div#weekAndPeriodAnotherSubId"+labSubjectCountDivId).find("#labSubjectCount").text())){
										anotherLabSubCount= 0;alert('if anotherLabSubCount:'+anotherLabSubCount);
									}else{
										anotherLabSubCount=$(this).find("div#displayAnotherLabDiv"+anotherLabSubjectCount).find("div#weekAndPeriodAnotherSubId"+labSubjectCountDivId).find("#labSubjectCount").text();
										alert('anotherLabSubCount else:'+anotherLabSubCount);
										if(anotherLabSubListCount == 1 && labSubCount ==0 && anotherLabSubCount >0){
											labSubCount=anotherLabSubCount;
											alert('in if labSubCount:'+labSubCount);
										}
									} */
							        //eachTdCount=$(this).find('span#eachPeriodVal').text();
							        //if((toTalPeriodVal != eachTdCount && labSubCount !=1) || electiveSubCount>0){
							        	//if((toTalPeriodVal != eachTdCount && labSubCount !=1) || electiveSubCount>0){ 
							    		//if(isNonEmpty(labSubCount)){
							    			//var equalPeriodCount= (toTalPeriodVal-eachTdCount)+1;
							    			//var maxNumberVal = Math.max(anotherLabSubCount, labSubCount);
								    		//if(equalPeriodCount >= maxNumberVal){
								    			if(labSubCount>0 || electiveSubCount>0){
								    				var isFlase=false;
								    				if(electiveSubCount>0){
								    					if(anotherElectiveSubListCount > 1){
								    						if(confirm('You have selected optional subject. Do you want to copy same period to another optional subject, if yes select OK.')){
									    						preparingTheElectiveSubjects($(this));
										    				}else{
										    					isFlase =true;
										    				}
								    					}else{
								    						isFlase =true;
								    					}
								    				}else if(labSubCount>0){
							    						if(anotherLabSubListCount > 1){
							    							if(confirm('You have selected language subject. Do you want to copy same period to another language subject, if yes select OK.')){
									    						preparingTheLabSubjects($(this));
										    				}else{
										    					isFlase =true;
										    				}
							    						}else{
							    							isFlase =true;
							    						}
								    				}
								    					/* if(anotherLabSubCount==0){
								    						if(anotherLabSubListCount > 1){
								    							if(confirm('You have selected language subject. Do you want to copy same period to another language subject, if yes select OK.')){
										    						preparingTheLabSubjects($(this));
											    				}else{
											    					isFlase =true;
											    				}
								    						}else{
								    							isFlase =true;
								    						}
								    					}else if (anotherLabSubCount > 0 && labSubCount>0) {
								    						if(anotherLabSubCount == labSubCount){
								    							if(anotherLabSubListCount > 1){
								    								if(confirm('You have selected language subject. Do you want to copy same period to another language subject, if yes select OK.')){
											    						preparingTheLabSubjects($(this));
												    				}else{
												    					isFlase =true;
												    				}
								    							}else{
								    								isFlase =true;
								    							}
								    						}else{
								    							alert("Both language subject periods are not matching, please select another subject.");
							        							$(this).find("div#displayAnotherLabDiv"+anotherLabSubjectCount).find('select#subjectId').val(0);
								    						}
														} */
								    				}
								    				/* else{
								    					if(anotherLabSubCount > 0 || labSubCount>0){
							        						if(anotherLabSubCount == labSubCount){
							        							isFlase =true;
							        						}else{
							        							alert("Both language subject periods are not matching, please select another subject.");
							        							$(this).find("div#displayAnotherLabDiv"+anotherLabSubjectCount).find('select#subjectId').val(0);
							        						}
														}else{
															 isFlase =true;
														}
								    				} */
								    				/* if(isFlase){
							        					var selectedInputVal=$(this).find('select#subjectId').val(); // Getting the select box input value
							        					var selectedAnotherInputVal= 0; //$(this).find('#displayAnotherLabDiv').find("select#subjectId").val();
							        						$(this).find("span#"+inputNameVal ).show();
															$(this).find("span#"+inputNameVal+" input").val(selectedInputVal);
											        		$(this).find('select#subjectId').attr('disabled','true'); 
											        		var currentTdVal=$(this);
											        		$(this).nextAll('td.currentWeekAndPeriodId').each(function(){
																if(tdVal < labSubCount){
																	var inputNameVal=$(this).attr('id'); 
																	$(this).find("span#"+inputNameVal ).show();
																	$(this).find('select#subjectId').val(selectedInputVal); 
												        			$(this).find('select#subjectId').attr('disabled','true');
												        			//$(this).find('div.displayAnotherLabDivNewData').remove();
												        			$(this).find('div#displayAnotherLabDiv1').nextAll('div').remove();
																	for(k=1; k<=anotherLabSubjectCount;k++){
																		var val=k;
													        			selectedAnotherInputVal=$(currentTdVal).find('#displayAnotherLabDiv'+k).find("select#subjectId").val();
													        			if(k<anotherLabSubjectCount){
													        				preparingTheLabSubjects($(this));
													        			}
													        			$(this).find("div#displayAnotherLabDiv"+val++).find('select#subjectId').val(selectedAnotherInputVal);
													        			selectedAnotherInputVal=0;
													        		}
																}
																tdVal++;
											        		});tdVal=0;selectedInputVal=0;anotherLabSubjectCount=0;
							        				} */
									        	//}
								    		//}
								    		 /*else{
								    			alert("You have selected language subject at Period "+eachTdCount+", but for selected language subject "+maxNumberVal+" period(s) required.");
									    		$(this).find("select#subjectId").val(0);
									    		$(this).find("div#displayAnotherLabDiv").find("span#"+inputNameVal+" input").val(0);
									    		$(this).find("div#displayAnotherLabDiv").hide();
								    		} */
								    		//equalPeriodCount=0;maxNumberVal=0;
										//}
							    	//}
							        	/* else{
							    		if(labSubCount != 0){
							    			alert("You have selected language subject at Period "+toTalPeriodVal+", it is not allowed to select language subject in the last hour.");
								    		$(this).find("select#subjectId").val(0);
								    		$(this).find("div#displayAnotherLabDiv").find("span#"+inputNameVal+" input").val(0);
							    		}
							    	} */
							    } labSubCount=0;
						  });
						labSubCount=0;
					}); 
				}
			});
		} 
	}
	function preparingTheLabSubjects(anchor){
		var value=parseInt($(anchor).find("span#eachTdVal").text()) +1 ;
		var anotherLabSubjectCount = $(anchor).find("div#displayAnotherLabGroup"+value).find("div.displayAnotherLabDivNewData").length;
		anotherLabSubjectCount++;
		if(anotherLabSubjectCount >= 2){
			var d=document.createElement("div");
			d.setAttribute("id", "displayAnotherLabDiv" + anotherLabSubjectCount);
			d.setAttribute("class", "displayAnotherLabDivNewData");
			$(d).html($(anchor).find("#displayAnotherLabDiv1").html());
			$(anchor).find("#displayAnotherLabGroup"+value).append(d);
		}else{
			$(anchor).find("#displayAnotherLabGroup"+value).find("div#displayAnotherLabDiv1").show();
		}
	}
	function preparingTheElectiveSubjects(anchor){
		var value=parseInt($(anchor).find("span#eachTdElectiveVal").text()) +1 ;
		var anotherElectiveSubjectCount = $(anchor).find("div#displayElectiveGroup"+value).find("div.displayAnotherElectiveDivNewData").length;
		anotherElectiveSubjectCount++;
		if(anotherElectiveSubjectCount >= 2){
			var d=document.createElement("div");
			d.setAttribute("id", "displayAnotherElectiveDiv" + anotherElectiveSubjectCount);
			d.setAttribute("class", "displayAnotherElectiveDivNewData");
			$(d).html($(anchor).find("#displayAnotherElectiveDiv1").html());
			$(anchor).find("#displayElectiveGroup"+value).append(d);
		}else{
			$(anchor).find("#displayElectiveGroup"+value).find("div#displayAnotherElectiveDiv1").show();
		}
	}
	function getStudentAttendanceForStudyClass(date) {
		var studyClassId = $('#studyClassId').val();
		if (isNonEmpty(studyClassId)) {
			var params = "studyClassId=" + studyClassId + "&startDate=" + date;
			$('#attendance').html('<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
				url : jQuery.url.getChatURL("/timeTable/ajaxGetStudyClassTakeAttendanceOrNot.do"),
				cache : true,
				data : params,
				success : function(response){
					$('#attendance').html(response);
					if($("#attendance").find("div.alert-info").hasClass("alert")){
						$("div#submitDiv").hide();
					}else{
						$("div#submitDiv").show();
					}
				}
			});
		}
	}
</script>