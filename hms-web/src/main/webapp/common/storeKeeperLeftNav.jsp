<li id="manageStoreInventory" class="start active">
	<a	href="javascript:;" id="SI"> 
		<i class="fa fa-book"></i> 
			<span	class="title">Store and Inventory</span> 
				<span class="selected"></span>
					<span class="arrow"></span>
	</a>
	<ul class="sub-menu">
		<li id="storeInfo" class="start active">
			<s:url	id="urlManageStore" action="ajaxDoManageStores" namespace="/store" />
				<sj:a id="urlManageStore" href="%{urlManageStore}"	targets="mainContentDiv" cssClass="ajaxify SI">
								Store Information
				</sj:a>
		</li>
		<li id="ItemTypes">
			<s:url id="urlManageItemTypes"	action="ajaxViewItemTypes" namespace="/store" /> 
				<sj:a id="urlManageItems" href="%{urlManageItemTypes}"	targets="mainContentDiv" cssClass="ajaxify IT">
								Item Types
				</sj:a>
		</li>
		<li id="Item">
			<s:url id="urlManageItems"	action="ajaxManageStoreItems" namespace="/store" /> 
				<sj:a	id="urlManagestoreItems" href="%{urlManageItems}" targets="mainContentDiv" cssClass="ajaxify IT">
								Add / Issue Items
				</sj:a>
		</li>
		<li id="Supplier">
			<s:url id="urlManageSuppliers"	action="ajaxManageSuppliers" namespace="/store" /> 
				<sj:a	id="urlManageSuppliersData" href="%{urlManageSuppliers}" targets="mainContentDiv" cssClass="ajaxify IT">
								Suppliers 
				</sj:a>
		</li>
		<li id="reports">
			<a href="javascript:;" class="ajaxify" id="SCS">
				<span class="title">Reports</span> 
					<span class="selected"></span>
						<span class="arrow " />
			</a>
			<ul class="sub-menu">
				<li id="storeWiseReport">
					<s:url id="urlStoreWiseReport"	action="ajaxGetStores" namespace="/store">
						<s:param name="reportType">Store Wise Details Report</s:param>
					</s:url>
					<sj:a id="urlStoreWiseReport" href="%{urlStoreWiseReport}"	targets="mainContentDiv" cssClass="ajaxify SI">
										Store Wise Details
					</sj:a>
				</li>
				<li id="itemIssueReport">
					<s:url id="urlItemIssueReport"	action="ajaxGetStores" namespace="/store">
						<s:param name="reportType">Issued Items Details Report</s:param>
					</s:url> 
					<sj:a id="urlItemIssueReport" href="%{urlItemIssueReport}" targets="mainContentDiv" cssClass="ajaxify SI">
										Issued Items Details
					</sj:a>
				</li>
			</ul></li>
	</ul>
</li>
<li>
	<a  href="${pageContext.request.contextPath}/staff/manageStaffLeaves.do?id=tManageLeaves"	id="tManageLeaves">
		<i class="fa fa-asterisk"></i> 
			<span	class="title">Leaves Management</span>
		    <span class="selected"></span>
	</a>
</li>