<%@ include file="/common/taglibs.jsp"%>
	<label class="noteFont">Show records per page:</label>
	<s:select id="numberOfRows" list="#{'10':'10','15':'15','20':'20','30':'30','40':'40','50':'50'}" theme="simple"
	cssClass="textfield small" onchange="changeNumberOfRows(this)" cssStyle="width:50px;float: none;margin-bottom:5px;"/>
	
<script type="text/javascript">
	function changeNumberOfRows(element){
	var parent=element.parentNode;
		var target= parent.getAttribute('data-target')
		$('#'+target).attr('class',element.value);
	 	$("#"+target).pagination();
	/*
		var disptarget=element.parentNode.dataset.target;
		$('#'+disptarget).attr('class',element.value);
	 	$("#"+disptarget).pagination();*/
	}
</script>