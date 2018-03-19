<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<style type="text/css">
	@import url("${pageContext.request.contextPath}/styles/newCss/autocompleteStyles.css");
</style>
<div id="subjectsContentDiv">
	<s:if test='%{customer != null}'>
		<s:form name="myformIdCards" theme="simple" namespace="/reports"
			id="idCardGenForm" action="ajaxAllStudentIdCardGeneration"
			cssClass="form-horizontal" 
			onsubmit="javascript: return getClassIdsAndAdmissionNumber();">
			<input type="hidden" name="pdfId" value="pdf" />
			<input type="hidden" name="selectedId" id="classNameIds" />
			<input type="hidden" name="anyId" id="adminNumbers" />
			<input type="hidden" class="academicYearId" name="academicYearId">
			<div class="form-body">
				<!-- <span class="label label-danger"> NOTE : </span>&nbsp;You can select the id card type. -->
				<!-- <div>&nbsp;</div> -->
				<b><label class="control-label">
						<span class="required">*</span>Select ID Card Type :
					</label> </b>
				<br />
				<div class="row">
					<div class="col-md-6">
					<div class="radio-list">
					<label class="radio-inline">
						<input type="radio" id="verticalCard" value="V" class="radio"
							name="cardType">
						Vertical Card Type
						</label>
						</div>
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_4">
							<thead>
								<tr>
									<td>
										<div class="col-md-12">
											<div class="col-md-5" style="width: 134px;">
												<img src='<s:property  value="#session.custImage"/>' border="0" alt="logo" class="logoHeader" id="customerLogoDiv"/> 
											</div>
											<div class="col-md-7">
												<b style="color: MediumVioletRed;"><s:property
														value="customer.organization" /> </b>
												<br />
												School Address
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td class="col-md-6"
										style="border-bottom: none; text-align: center;">
										<img src="<c:url value='../images/nophoto.jpg'/>" />
									</td>
								</tr>
								<tr>
									<td align="center">
										<div class="col-md-5">
											<label>
												Name :
											</label>
											Jonnie Wheeler.C
										</div>
										<div class="col-md-5">
											<label>
												Class :
											</label>
											10th
										</div>
										<div class="col-md-5">
											<label>
												Section :
											</label>
											B
										</div>
										<div class="col-md-5">
											<label>
												Admission No :
											</label>
											89
										</div>
										<div class="col-md-5">
											<label>
												Blood Group :
											</label>
											B+
										</div>
										<div class="col-md-12">
											<div class="col-md-10">
												<label> Transport Mode : <span> Own / Private / School Transport </span> </label>
											</div>
										</div>
										<div class="col-md-5">
											&nbsp;
										</div>
										<div class="col-md-5">
											<div align="right">
												Principal
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td style="border-top: 1px solid #D6D6D6;">
										Student/Parent Address
									</td>
								</tr>
							</thead>
						</table>
					</div>
					<div class="col-md-6">
					<div class="radio-list">
					<label class="radio-inline">
						<input type="radio" id="horizentalCard" value="H" class="radio"
							name="cardType" checked>
						Horizontal Card Type
						</label>
						</div>
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_4">
							<thead>
								<tr>
									<td>
										<div class="col-md-12">
											<div class="col-md-5 left" style="width: 134px;">
												<img src='<s:property  value="#session.custImage"/>' border="0" alt="logo" class="logoHeader" id="customerLogoDiv"/> 
											</div>
											<b style="color: MediumVioletRed;"><s:property
													value="customer.organization" /> </b>
											<br />
											School Address
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div class="col-md-7 left" style="width: 150px;">
											<div>
												<img src="<c:url value='../images/nophoto.jpg'/>"
													height="150px" width="122px;"></img>
											</div>
											<div>
												&nbsp;
											</div>
											<div align="center">
												Principal
											</div>
										</div>
										<div class="col-md-5">
											<label>
												Name :
											</label>

											Jonnie Wheeler.C
										</div>
										<div class="col-md-5">

											<label>
												Class :
											</label>
											10th
										</div>
										<div class="col-md-5">
											<label>
												Section :
											</label>
											B
										</div>
										<div class="col-md-5">
											<label>
												Admission No :
											</label>
											89
										</div>
										<div class="col-md-5">
											<label>
												Blood Group :
											</label>
											B+
										</div>
										<div class="col-md-10" style="width: 200px;">
											<label>
												Transport Mode : <span>Own / Private / School Transport</span>
											</label>
										</div>
									</td>
								</tr>
								<tr>
									<td style="border-top: 1px solid #D6D6D6;">
										Student/Parent Address
									</td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div>
					<s:if test="%{academicYearList != null && !academicYearList.isEmpty()}">
					<div class="form-group">
						<label class="col-md-3 control-label">
							<span class="required">*</span>Select Academic Year :
						</label>
						<div class="col-md-9">
							<s:select id="academicYearId" list="academicYearList"
								cssClass="form-control input-medium" listKey="id"
								listValue="academicYear" name="tempId2"
								onchange="javascript:academicAdmissionIdCardDetails(this.value,'Y');" />
						</div>
					</div>
					<s:if test="%{classList != null && !classList.isEmpty()}">
						<div class="form-group">
							<label class="control-label col-md-3">
								Select :
							</label>
							<div class="col-md-9 radio-list">
							<label class="col-md-2 radio-inline">
								<input type="radio" value="classSectionWise"
									onclick="getSelectedValueForIdCard(this.value);"
									name="SelectType" checked>
								Class Wise
								</label>
								<label class="radio-inline">
								<input type="radio" value="admissionNumberWise"
									onclick="getSelectedValueForIdCard(this.value);"
									name="SelectType">
								Search By Admission Number Wise
								</label>
							</div>
						</div>
						<div id="classAndSection">
							<p>
							<span class="label label-danger">NOTE :</span>
								Please select class and id card type.
							</p>
							<div class="form-group">
							<div class="col-md-12">
								<div class="checkbox-list">
									<label class="checkbox-inline">
										<input type="checkbox" name="" value=""
											onClick="checkAllClasses()" class="checkbox allClasses">
										All Classes
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="conLable col-md-3 control-label">
								Class With Students :
							</label>
							<div class="col-md-12">
								<div class="checkbox-list">
									<s:checkboxlist name="chkBoxSelectedIds" list="classList"
										listKey="id" listValue="className" theme="ems" cssClass="small" />
								</div>
							</div>
						</div>
						</div>
					<div id="searchAdmissionNumber" style="display: none;">
						<div class="form-group">
								<label class="control-label col-md-3">
									<span class="required">*</span>Search Student Admission Number :
								</label>
								<div id="friends" class="col-md-6">
									<input id="search_Admmission" type="text" class="ui-autocomplete-input form-control"  style="border: 1px solid #FFFFFF;"/>
								</div> 
							</div>
							<div  class="col-md-3"></div><span class="help-block">(Enter at least 3 chars to get closer match.)</span>
					</div>
					<div id="generateIdCard">
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-12">
								<s:submit value="Generate ID Cards"
									cssClass="submitBt btn blue long" id="idCardGenForm"></s:submit>
							</div>
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="alert alert-info col-md-12">Currently there are no classes.
					</div>
				</s:else>
				</s:if>
				<s:else>
					<div class="alert alert-info">
							Currently there are no admission settings created to any academic
							year.
							<s:url id="admissionSettingsShortList" namespace="/admin"
								action="ajaxAdmissionSettingsHome" >
								<s:param name="description">createSettings</s:param>
							</s:url>
							<sj:a id="createShortAdmissionSettings" href="%{admissionSettingsShortList}"
								targets="mainContentDiv" data-toggle="tab"><b>Click here </b></sj:a> to add Admission Settings
						</div>
				</s:else>
				</div>
				</s:form>
			</s:if>
		</div>
<script type="text/javascript">
$(document).ready(function() {
 $("input:checkbox, input:radio").uniform();
$('input.academicYearId').val($('select#academicYearId option:selected').val());
var admissionNumber = "";
 changePageTitle("ID Cards Generation");	
	    $('#searchAdmissionNumber').hide();	    	    
		var selected = $('input[name=SelectType]:radio:checked').val();	
		      getSelectedValueForIdCard(selected);		 
		      $.destroyTopic('searchStudentAdmissionStuIdCardForm');
		  	$.subscribe('searchStudentAdmissionStuIdCardForm', function(event, data) {			  
				 admissionNumber = $('#admissionNumber').val();
				//alert('admissionNumber : '+admissionNumber)
				if (admissionNumber == null || admissionNumber == '' || admissionNumber == 'Student Admission Number') {
					alert("Please enter student admission number.");
					event.originalEvent.options.submit=false;
					
				}
				else if(admissionNumber.length < 3){
					alert("Please enter minimum 3 characters.");
					event.originalEvent.options.submit=false;
				}
				 else {					 
				 $("#adminNumber").val(admissionNumber);				 	   
				    $('#searchIdCardStudentsList').show();	
				    $("#generateIdCard").show();	
					return true;
				}
					
			});
	
	});
function getSelectedValueForIdCard(value) {			    
	if (isNonEmpty(value)) {				
		 if (value == 'classSectionWise' ) {
		 	checkAllClasses();			
			$('#searchAdmissionNumber').hide();			
			$('#classAndSection').show();
			$("#searchIdCardStudentsList").hide();
			$("#generateIdCard").show();
			$("#adminNumbers").val(null);
		} else {	
			$('input[name="chkBoxSelectedIds"]').removeAttr('checked');
			$('input.allClasses').removeAttr('checked');
			$('#classAndSection').hide();
			$('#searchAdmissionNumber').show();
			$("#searchIdCardStudentsList").hide();
			//$("#generateIdCard").hide();
			$("#classNameIds").val(null);
		}
	}
}		
function checkAllClasses() {
	if ($(".allClasses").is(':checked')) {
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
			$(".allClasses").parent('span').removeClass('checked');
			$(".allClasses").removeAttr("checked");
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		$(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
}); 
	function getClassIdsAndAdmissionNumber() {	  
	   var selected = $('input[name=SelectType]:radio:checked').val();
	  // alert('selected:'+selected)
	   if (selected == 'classSectionWise') {	       
			if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
				var classIds = $("input[name=chkBoxSelectedIds]:checked");		
				var selectedClassNames = [];
				var selectedClassIds = [];
				if (classIds.length > 0) {
					for ( var i = 0; i < classIds.length; i++) {
						if (isNonEmpty(classIds[i].value)) {
							selectedClassNames.push(classIds[i].value);
						}
					}
				}
				$("#classNameIds").val("(" + selectedClassNames + ")");
				$("#classNames").val(selectedClassNames);
				return true;
			} else {			
				alert("Please select at least one class.");
				return false;
			}
		}
	 else{	 	 
	     //alert($('div#emptyStudents').text());
	     if ($('div#emptyStudents').text()){
	     alert('Please find student');
	     return false;
	     }
	     if(isNonEmpty($('#adminNumbers').val()) && $('#adminNumbers').val()!="()"){
				return true;
			}else{
				alert("Please select at least one student.");
				return false;
			}
		}
	}	
function academicAdmissionIdCardDetails(academicYearId) {
$('input.academicYearId').val(academicYearId);
	var pars = "academicYearId=" + academicYearId;
	var url	 = jQuery.url.getChatURL("/admin/ajaxIDCardsGenerationForAdmittedStu.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#mainContentDiv").html(html);
			$("#mainContentDiv").show();
		}
	});
}


var nonIds = '0,';
$(".remove", document.getElementById("friends")).live("click", function () {
     //remove current friends  
     $(this).parent().remove();
     nonIds = nonIds.replace($(this).parent().attr('id') + ',', '').replace(/^\,/, "").replace(/,$/, "");
     //correct 'search_Admmission' field position  
     if ($("#friends div").length === 0) {
         $("#search_Admmission").css("top", 0);
     }
     var selectedStudAdmissionNumbers = [];
		$('.admissionIdcard').each(function() {
			stuAccounrId = $(this).attr('id');
			if (isNonEmpty(stuAccounrId)) {
				selectedStudAdmissionNumbers.push("'" + stuAccounrId + "'");
			}
		});
		//alert(selectedStudAdmissionNumbers);
	$("#adminNumbers").val("(" + selectedStudAdmissionNumbers + ")");
});
$("input#search_Admmission").autocomplete({
    minLength: 3,
    source: function (req, response1) {
        //pass request to server
        var searchword = $('#search_Admmission').val();
        var academicYearId=$('select#academicYearId option:selected').val();
       // alert("searchword=" + searchword + "&anyTitle=" + nonIds+"&academicYearId="+academicYearId);
        $('#search_Admmission').addClass('spinner');
        $.ajax({
            dataType: 'json',
            type: "POST",
            data: "searchword=" + searchword + "&anyTitle=" + nonIds+"&academicYearId="+academicYearId,
            url: "../reports/ajaxSearchStudentByAdmissionNumber.do",
            cache: false,
            success : function(response) {
			if (response) {
				var data = response.data;
				var accountId;
				var stuName;
				var admissionNumber;
				var rollNumber;
				var inputObj;
				if (data.length >= 1) {
					for ( var i = 0; i < data.length; i++) {
						accountId = data[i].accountId;
						stuName = data[i].stuName;
						admissionNumber = data[i].admissionNumber;
						rollNumber  = data[i].rollNumber;
						//alert("accountId="+accountId+"&stuName="+stuName+"&admissionNumber="+admissionNumber+"&rollNumber="+rollNumber);
					}
				}
				response1($.map(data, function (item) {
                        if (item.accountId != 0) {
	                          if(isNonEmpty(item.admissionNumber)){
								var l_LastName = '';
									if(isNonEmpty(item.admissionNumber ))
										l_LastName = item.admissionNumber ;
								return {
									label: extractLast(item.stuName + ' ' + admissionNumber + '(' + item.rollNumber + ')'),
									id: item.accountId+":"+admissionNumber,
									value: item.role
							}
							}else{
                          		  return {
	                                 label: extractLast(item.stuName + ' ' + '' + '(' + item.rollNumber + ')'),
	                                 id: item.accountId+":"+admissionNumber,
	                                 value: item.admissionNumber
	                             }
                            }
                             
                        } else {
                            return {
                                value: item.stuName,
                                id: item.accountId
                            }
                        }
                        
                    }))
			        }
               $("#displaySearchResult").data(data).show(); 
            }
        });
    },
    select: function (event, ui) {
   	 var admissionNum = ui.item.id.split(':');
        //create formatted friend  
        //alert(ui);
        if (admissionNum[0] == 0) {
            return false;
        } else {
            var valueName = ui.item.label;
          // alert("valueName"+ ui.item.value+"id="+ui.item.id+"friend="+friend);
            //alert(admissionNum);
            nonIds += admissionNum[0] + ',';
            var friend = valueName,
                span = $("<span class='admissionIdcard color-Type-" + ui.item.value + "'  id='" + admissionNum[0] + "'>").text(friend),
                a = $("<a>").addClass("remove").attr({
                    href: "javascript:",
                    title: "Remove " + friend
                }).text("x").appendTo(span);
            //add friend search_Admmission friend div  
            span.insertBefore("#search_Admmission");
			var selectedStudAdmissionNumbers = [];
				$('.admissionIdcard').each(function() {
					stuAccounrId = $(this).attr('id');
					if (isNonEmpty(stuAccounrId)) {
						selectedStudAdmissionNumbers.push("'" + stuAccounrId + "'");
					}
				});
				//alert(selectedStudAdmissionNumbers);
			$("#adminNumbers").val("(" + selectedStudAdmissionNumbers + ")");
            $("#search_Admmission").val("");
            $('#search_Admmission').removeClass('spinner');
            return false;
        }
    },
    //define select handler  
    change: function () {
        //prevent 'search_Admmission' field being updated and correct position  
        $("#search_Admmission").val("").css("top", 2);
    }
});
$("#friends").click(function () {
    //focus 'search_Admmission' field  
    $("#search_Admmission").focus();
});
function split(val) {
    return val.split(/,\s*/);
}
function extractLast(term) {
    return split(term).pop();
}

</script>
