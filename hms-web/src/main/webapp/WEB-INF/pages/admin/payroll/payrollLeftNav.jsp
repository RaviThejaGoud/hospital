<%@ include file="/common/taglibs.jsp"%>
<div class="wrapper container_18">
	<div class="wrapper">
		<div class="grid_18 block grid_18MarginLeft">
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						Payroll Process
					</h2>
				</div>
				<div class="block_content" id="sideMenu" style="padding: 0px;">
					<ul>
						<li style="line-height: 0px">
							&nbsp;
						</li>
						<li >
							 <s:url id="urlDoPayrollTypes" action="ajaxDoGetPayrollTypes" namespace="/admin" />
							<sj:a id="doPayrollTypes" href="%{urlDoPayrollTypes}"
								targets="staffPayroll" indicator="indicator">Payroll Types</sj:a>
						</li>
						<li >
							 <s:url id="urlDoPayrollSettings" action="ajaxDoPayrollSettingDetails" namespace="/admin" />
							<sj:a id="doPayrollSettings" href="%{urlDoPayrollSettings}"
								targets="staffPayroll" indicator="indicator">Payroll Settings</sj:a>
						</li>
						<li>
							 <s:url id="urlDoLoanDetails" action="ajaxDoGetLoanDetails" namespace="/admin" />
							<sj:a id="doLoanDetails" href="%{urlDoLoanDetails}"
								targets="staffPayroll" indicator="indicator">Loan Details</sj:a>
						</li>
						<li >
							 <s:url id="urlDoGeneratePayroll" action="ajaxDoGeneratePayroll" namespace="/admin"/>
							<sj:a id="doGeneratePayroll" href="%{urlDoGeneratePayroll}"
								targets="staffPayroll" indicator="indicator">Generate Payroll</sj:a>
						</li>
						
						<li style="line-height: 0px">
							&nbsp;
						</li>
					</ul>
				</div>
			</div>
			<div id="staffPayroll" class="grid_14 alpha">
				<jsp:include page="/WEB-INF/pages/admin/payroll/ajaxManagePayrollTypes.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>
<script Language="Javascript1.2" type="text/javascript">
function getUrlVars()
            {
                var vars = [], hash;
                var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
                for(var i = 0; i < hashes.length; i++)
                {
                    hash = hashes[i].split('=');
                    vars.push(hash[0]);
                    vars[hash[0]] = hash[1];
                }
                return vars;
            }
$(document).ready(
		function() {
			changePageTitle("Payroll Process");
			$('#payrollProcess').addClass('current');
			if(getUrlVars()["value"]=="false"){
				$('a#payrollProcess').click();
			}
 
		});
		
</script>