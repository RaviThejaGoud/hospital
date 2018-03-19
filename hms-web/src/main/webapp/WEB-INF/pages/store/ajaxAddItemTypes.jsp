<%@ include file="/common/taglibs.jsp"%>
	
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:form action="ajaxSaveItemTypes" theme="simple" id="addNewItemType"
	method="post" cssClass="form-horizontal" namespace="/store">
		
		<div class="form-body">
		
			<h4 class="bold pageTitle">
				Add Item Type
			</h4>	
			<br>	
		
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<label class="control-label col-md-5">
							<span class="required">*</span>Type Name :
						</label>
						<div class="col-md-4">
							<sj:textfield name="itemTypesListVO.itemTypesList[0].typeName" 
								cssClass="required form-control input-medium" maxlength="40" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>Measurement Type:
						</label>
						<div class="col-md-6">					
								<s:select  cssClass="required form-control input-small"
								headerKey="" headerValue="- Select -"
								name="itemTypesListVO.itemTypesList[0].measurementType" 
								list="#{'Kgs':'Kgs','Units':'Units','Liter':'Liter'}"></s:select>					 
							
						</div>
					</div>
				</div>
			
				<div class="col-md-1">
				<button type="button" data-dismiss="modal" style="cursor: pointer; width: 78px;" class="normalAdd btn btn-xs green"
				 id="addButton"> <i class="fa fa-plus"></i>ADD </button>
				 
				</div>
							
			</div>
			<div id="TextBoxesGroup">			 
			</div>
			<br>
			
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit cssClass="submitBt btn blue" validate="true" value="submit"  id="clickSubmit"
							indicator="indicator" targets="mainContentDiv" formIds="addNewItemType" />							
							<s:url id="doViewItemTypeData" action="ajaxViewItemTypes"
								includeParams="all" escapeAmp="false" namespace="/store">
							</s:url>							
							<sj:a href="%{doViewItemTypeData}" cssClass="btn default"
								indicator="indicator" targets="mainContentDiv">Cancel</sj:a>								
						 						
					</div>
				</div>
			</div>
		</div>
	</s:form>
	
<script type="text/javascript">


$(document).ready(function(){
	var counter = 1;    
    $("#addButton").click(function () {    
     var label = '<label class="control-label col-md-5">' +
				'<span class="required">*</span>Type Name' +':' +
				'</label> ';
	 var startDivTag = '<div class="col-md-4"><div class="form-group">';
	 var startDiv1Tag = '<div class="col-md-4">';
	 var measurementLabel = '<label class="control-label col-md-6"><span class="required">*</span>'+'Measurement Type:</label>';
	 var selectBox = '';
	 var startDivSelectTag = '<div class="col-md-6"><div class="form-group">';
	 var startDiv1SelectTag = '<div class="col-md-6">';
	 //var texttag =  '<sj'+':'+'textfield name="itemTypesListVO.itemTypesList['+counter+'].typeName" cssClass="required form-control input-medium" maxlength="30"/>'+'</div></div></div>';
	 
	if(counter>50){
            alert("Max you Can Add 50 Item Types.");
            return false;
	}
	
	//var newbreak = $(document.createElement('br'));
	var newTextBoxDiv = $(document.createElement('div'))
	     .attr('id', 'Typename' + counter);
	     
	     var divid = 'Typename' + counter;
	     
	  newTextBoxDiv.attr('class','row');
	     
	  newTextBoxDiv.after().html(startDivTag + label + startDiv1Tag +	
	      '<input type="text" name="itemTypesListVO.itemTypesList['+counter+'].typeName" style="width:240px" cssClass="required form-control input-small" maxlength="40" required="required"></div></div></div>' + 
	      startDivSelectTag + measurementLabel + startDiv1SelectTag +
	      '<select style= "width:120px;"  headerKey="" headerValue="- Select -"  name="itemTypesListVO.itemTypesList['+counter+'].measurementType" required="required"><option value="">- Select -</option><option value="Kgs">Kgs</option><option value="Units">Unit</option></select>' + '</div></div></div>' +
	      '<div class="col-md-1"><button type="button" data-dismiss="modal" class="btn btn-xs red normalDelete" style="cursor: pointer;position: absolute;width:60px;" id="removeButton" onclick="remove(\''+divid+'\')"> REMOVE </button></div>' );
	          
	newTextBoxDiv.appendTo('#TextBoxesGroup');
	
	//newbreak.appendTo("#TextBoxesGroup");
	
	counter++;
     });
     
  
  });
  
  function remove(divid){  
  	$('#'+divid).remove();
  }   
</script>