<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<style type="text/css">
	@import url("${pageContext.request.contextPath}/styles/newCss/autocompleteStyles.css");
</style>
<s:if test='%{customer != null}'>
	<s:form name="myformIdCards" theme="simple" namespace="/reports"
		cssClass="form-horizontal" id="idCardGenForm"
		action="ajaxAllStaffIdCardGeneration" 
		onsubmit="javascript: return getClassIdsAndAdmissionNumber();">
		<input type="hidden" name="pdfId" value="pdf" />
		<input type="hidden" name="selectedId" id="roleIds" />
		<input type="hidden" name="anyId" id="staffAccountIds" />
		<div class="form-body">
			<b><label>
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
								<td align="center">
									<div class="col-md-12">
										<div class="col-md-5" style="width: 134px;">
											<img src='<s:property  value="#session.custImage"/>' border="0" alt="logo" class="logoHeader" id="customerLogoDiv"/> 
										</div>

										<div class="col-md-6">
											<b style="color: MediumVioletRed;"><s:property
													value="customer.organization" /> </b>
											<br />
											School Address
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td class="col-md-3"
									style="border-bottom: none; text-align: center;">
									<img src="<c:url value='../images/nophoto.jpg'/>" />
								</td>
							</tr>
							<tr>
								<td align="center">
									<div class="col-md-5">
										<label>
											Staff Name :
										</label>

										Jonnie.C
									</div>
									<div class="col-md-5">

										<label>
											Desg :
										</label>
										Teacher
									</div>
									<div class="col-md-5">
										<label>
											Blood Group :
										</label>
										B+
									</div>
									<div class="col-md-4">
										&nbsp;
									</div>
									<div class="col-md-4">
										<div align="right">
											Principal
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td style="border-top: 1px solid #D6D6D6;">
									Staff Address :
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
									<div class="col-md-4" style="width: 173px">
										<b style="color: MediumVioletRed;"><s:property
												value="customer.organization" /> </b>
										<br />
										School Address
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="col-md-2 left" style="width: 98px;">
										<div>
											<img src="<c:url value='../images/nophoto.jpg'/>"
												height="90px;" width="80px;"></img>
										</div>
										<div align="center">
											Principal
										</div>
									</div>
									<div class="col-md-5">
										<label>
											Staff Name :
										</label>

										Wheeler.C
									</div>
									<div class="col-md-5">

										<label>
											Desg :
										</label>

										Teacher
									</div>
									<div class="col-md-5">

										<label>
											Blood Group :
										</label>
										B+
									</div>
								</td>
							</tr>
							<tr>
								<td style="border-top: 1px solid #D6D6D6;">
									Staff Address :
								</td>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<s:if test="%{objectList != null && !objectList.isEmpty()}">
				<div class="form-group">
					<label class="col-md-1 control-label">
						<strong>Select : </strong>
					</label>
					<div class="radio-list">
						<label class="radio-inline">
						<input type="radio"
								name="SelectType" value="roleWise"
								onclick="getSelectedValue(this.value);" checked="checked">
							All
						</label>
						<label class="radio-inline">
							<input type="radio"  name="SelectType" value="individuals"
								onclick="getSelectedValue(this.value);" >
								Individuals
						</label>
					</div>
				</div>
				<div id="roleDescription">
				<p>
					<span class="label label-danger">NOTE :</span>&nbsp;&nbsp;
						Please select role and id card type.</p>
					<div class="form-body">
					<div class="form-group">
						<div class="col-md-12">
							<div class="checkbox-list">
								<label class="checkbox-inline">
									<input type="checkbox" name="" value=""
									onClick="allRoleNames()" class="checkbox userRoleNames">
								 All Role Names
								</label>
							</div>
							<s:checkboxlist name="chkBoxSelectedIds" list="objectList"
								listKey="roleId" listValue="roleDescription" theme="ems"
								cssClass="small" />
						</div>
					</div>
				</div>
				<div id="searchStudentsList"></div>
			</div>
			<div id="searchStaffName" style="display: none;">
				<div class="form-group">
						<label class="control-label col-md-3">
							<span class="required">*</span>Search Staff :
						</label>
						<div id="friends" class="col-md-6">
							<input id="search_role" type="text" class="ui-autocomplete-input form-control"  style="border: 1px solid #FFFFFF;"/>
						</div> 
					</div>
					<div  class="col-md-3"></div><span class="help-block">(Enter at least 3 chars to get closer match.)</span>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-12">
					<s:submit value="Generate ID Cards"
						cssClass="submitBt btn blue long" id="idCardGenForm"></s:submit>
				</div>
			</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">Currently there are no roles.
				</div>
			</s:else>
		</div>
	</s:form>
</s:if>
<script type="text/javascript">
	$(document).ready(
		function() {
		 $("input:checkbox, input:radio").uniform();
			var staffName = "";
			var str = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
			if(isNonEmpty(str)){
				$('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
						+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			}else{
				$('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim())
			}
			/* $('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim()) */
			var title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
			changePageTitle(title);
			$('#searchStaffName').hide();
			var selected = $('input[name=SelectType]:radio:checked').val();
			getSelectedValue(selected);
			$.subscribe('searchStaffForm', function(event, data) {
				staffName = $('#staffName').val();
				//alert('admissionNumber : '+admissionNumber)
					if (staffName == null || staffName == ''
							|| staffName == 'Staff staffName') {
						alert("Please enter Staff Name.");
						return false;

					} else if (staffName.length < 3) {
						alert("Please enter minimum 3 characters.");
						$('#staffName').val('staffName');
						return false;
					} else {
						$("#staffAccountIds").val(staffName);
						$('#searchStudentsList').show();
						$("#generateId").show();
						return true;
					}

				});

		});
	function getSelectedValue(value) {
		if (isNonEmpty(value)) {
			allRoleNames();
			if (value == 'roleWise') {
				$('#searchStaffName').hide();
				$('#roleDescription').show();
				$("#staffAccountIds").val(null);
				$(".staffIdcards").remove();
				searchStaff();
			} else {
				$('input[name="chkBoxSelectedIds"]').removeAttr('checked');
				$('input.userRoleNames').removeAttr('checked');
				$('#roleDescription').hide();
				$('#searchStaffName').show();
				$("#roleIds").val(null);
			}
		}
	}
	function allRoleNames() {
		if ($(".userRoleNames").is(':checked')){
		    $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
			$(".userRoleNames").parent('span').removeClass('checked');
			$(".userRoleNames").removeAttr("checked");
		 $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		   $(".userRoleNames").parent('span').removeClass("checked");
		  $(".userRoleNames").attr("checked", false);
		} else {
		    $(".userRoleNames").parent('span').addClass("checked");
			$(".userRoleNames").attr("checked", true);
		}
	});
	function getClassIdsAndAdmissionNumber() {
		var selected = $('input[name=SelectType]:radio:checked').val();
		if (selected == 'roleWise') {
			if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
				var rollIds = $("input[name=chkBoxSelectedIds]:checked");
				var selectedRoleNames = [];
				var selectedRoleIds = [];
				if (rollIds.length > 0) {
				$(rollIds).each(function(){
						if (isNonEmpty($(this).val())
								&& isNonEmpty($(this).parents('label').text())) {
							selectedRoleIds.push($(this).val());
							selectedRoleNames.push($(this).parents('label').text().trim())
						}
					});
				}
				$("#roleIds").val("(" + selectedRoleIds + ")");
				$("#roleNames").val(selectedRoleNames);
				return true;
			} else {
				alert("Please select at least one Role.");
				return false;
			}
		} else {
			if(isNonEmpty($('#staffAccountIds').val()) && $('#staffAccountIds').val()!="()"){
				return true;
			}else{
				alert('Please select at least one staff.');
				return false;
			}
		}
	}
	function searchStaff(){
		var nonIds = '0,';
		$(".remove", document.getElementById("friends")).live("click", function () {
		     $(this).parent().remove();
		     nonIds = nonIds.replace($(this).parent().attr('id') + ',', '').replace(/^\,/, "").replace(/,$/, "");
		     if ($("#friends div").length == 0) {
		         $("#search_role").css("top", 0);
		     }
		     var selectedStaffAccountIds = [];
				$('.staffIdcards').each(function() {
					staffAccounrId = $(this).attr('id');
					if (isNonEmpty(staffAccounrId)) {
						selectedStaffAccountIds.push(staffAccounrId);
					}
				});
			$("#staffAccountIds").val("(" + selectedStaffAccountIds + ")");
			nonIds=selectedStaffAccountIds+",";
			if(nonIds==","){
				nonIds=selectedStaffAccountIds+"0,";
			}
		});
		$("input#search_role").autocomplete({
		     minLength: 3,
		     source: function (req, response1) {
		         //pass request to server
		         var searchword = $('#search_role').val();
		         $('#search_role').addClass('spinner');
		         $.ajax({
		             dataType: 'json',
		             type: "POST",
		             data: "searchword=" + searchword + "&anyTitle=" + nonIds,
		             url: "../reports/ajaxSearchStaffName.do",
		             cache: false,
		             success : function(response) {
					if (response) {
						var data = response.data;
						var accountId;
						var staffNames;
						if (data.length >= 1) {
							for ( var i = 0; i < data.length; i++) {
								accountId = data[i].accountId;
								staffNames = data[i].staffNames;
							}
						}
							response1($.map(data, function (item) {
								if (item.accountId != 0) {
			                          if(isNonEmpty(item.staffNames)){
										var l_LastName = '';
											if(isNonEmpty(item.staffNames))
												l_LastName = item.staffNames ;
										return {
											label: extractLast(item.staffNames),
											id: item.accountId+":"+accountId,
											value: item.role
									}
									}else{
		                           		  return {
		 	                                 label: extractLast(item.staffNames + ' ' + '' + '(' + item.role + ')'),
			                                 id: item.accountId+":"+accountId,
			                                 value: item.accountId
			                             }
		                             }
		                              
		                         } else {
		                             return {
		                                 value: item.staffNames,
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
			    	  if (admissionNum[1] == 0) {
			             return false;
			         } else {
			             var valueName = ui.item.label;
			             nonIds += admissionNum[0] + ',';
			             var friend = valueName,
			             span = $("<span class='staffIdcards color-Type-" + ui.item.value + "'  id='" + admissionNum[0] + "'>").text(friend),
			                 a = $("<a>").addClass("remove").attr({
			                     href: "javascript:",
			                     title: "Remove " + friend
			                 }).text("x").appendTo(span);
			             span.insertBefore("#search_role");
			 			var selectedStaffAccountIds = [];
			 				$('.staffIdcards').each(function() {
			 					staffAccounrId = $(this).attr('id');
			 					if (isNonEmpty(staffAccounrId)) {
			 						selectedStaffAccountIds.push("'" + staffAccounrId + "'");
			 					}
			 				});
			 			$("#staffAccountIds").val("(" + selectedStaffAccountIds + ")");
			             $("#search_role").val("");
			             $('#search_role').removeClass('spinner');
			             return false;
			         }
			     },
			     change: function () {
			         $("#search_role").val("").css("top", 2);
			     }
			    
			 });
			  $("#friends").click(function () {
				     $("#search_role").focus();
				 });
				 function split(val) {
				     return val.split(/,\s*/);
				 }
				 function extractLast(term) {
				     return split(term).pop();
				 }
	}
	//$(".go-top").click();
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
	
	</script>
