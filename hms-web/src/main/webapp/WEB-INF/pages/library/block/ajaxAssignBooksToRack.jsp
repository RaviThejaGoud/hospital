<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="block_head">
	<h2>
		Assign Books To Rack
	</h2>
</div>
<div class="block_content" id="studentLibraryContent">
		<table class="striped">
			<tr>
				<td>
					<label style="width: 220px;">Book Name</label>
				</td>
				<td>
				   <label style="width: 220px;">Author</label>
				</td>
			    <td>
				  <label style="width: 220px;">Publisher</label>
			   </td>
			    <td>
				  <label style="width: 220px;">No.Of Books</label>
			   </td>
			</tr>
			</table>
		<div  style="width: 601px">
		<table class="striped">
		 <c:forEach var="element" items="${objectList}">
		 <tr>
			     <td><input type="checkbox" value="${element[5]}" id="${element[0]}" name="chkSelectedCheckIds" checked="checked" class="${element[5]}"/><label style="width: 220px;color: blue;" id="${element[5]}" class="subjectNameIds">${element[1]}</label></td>
			     <td><label style="width: 220px;color: blue;" id="${element[5]}">${element[2]}</label></td>
			     <td><label style="width: 220px;color: blue;" id="${element[5]}">${element[3]}</label></td>
			     <td><label style="width: 220px;color: blue;" id="${element[5]}">${element[4]}</label></td>    
		 </tr>
		 </c:forEach>
		 </table>
			<div>
				<a  href="#" class="cancelButton"  id="assignBooksToRack">Assign Books</a>
			</div>
		</div>
</div>

<script type="text/javascript">
changePageTitle("Assign Books To Rack");
$('a#assignBooksToRack').click(function(){
	var strValue=[];
	if($('input[name="chkSelectedCheckIds"]:checked').length == 0){
	 alert('Please select at least one book.(OR) Press escape to not select');
	   return false;
	}
	else{
	var subjectId=0;
	var checkedBooksCount=0;
	$('input[name="chkSelectedCheckIds"]:checked').each(function(){
	   strValue.push($(this).attr('id')+'_'+$(this).val()+'_'+$(this).parents('tr').children('td:last').children('label').html());
	   checkedBooksCount +=Number($(this).parents('tr').children('td:last').children('label').html());
	   subjectId=$(this).attr('id');
	 });
	 document.getElementById('subjectCheckBoxVal'+subjectId).innerHTML=strValue;
	 document.getElementById('totalBooks'+subjectId).innerHTML=checkedBooksCount;
	 return true;
	}
});
</script>
