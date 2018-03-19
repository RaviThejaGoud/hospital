<%@ include file="/common/taglibs.jsp"%>
<div style="background-color:#9ACD32;" >
    <div  >
        <h3>
            Contact Your Administrator:
        </h3>
    </div>
   
    <div style="margin: 10px 0px 0px 16px">
        <table class="loginForm"  >
            <tr>
                <td style = "color: black;">
               <b> If you need an account or have not received your login please fill out the form below.
                 A system administrator will contact you as soon as possible.</b>
                <!--  <a href="#" onclick="javascript:openPersonDetailMsgDiv();">
                        click here</a> -->
                </td>
            </tr>
        </table>
       
    <div id="personDetailFieldErrors" style="width: 210px "></div>
     <font color="#000000">
    <s:form action="/eazyContactUs.do" onsubmit="javascript:return personDetailErrors();"
     theme="xhtml" >
    
                <sj:textfield name="contactUs.customerName"  id="customerName" required="true"
                    label="Name" requiredposition="first" 
                    cssClass="required text small" maxlength="20"></sj:textfield>
                <sj:textfield name="contactUs.customerEmail" id="customerEmail"
                    requiredposition="first" label="Email Id" labelposition="first"
                    cssClass="required email text small" required="true" maxlength="40"></sj:textfield>
                <sj:textfield name="contactUs.phoneNumber" id="phoneNumber"
                    required="true" label="Mobile Number" requiredposition="first"
                    cssClass="numeric text required small" maxlength="14"
                    onkeypress="return formatPhoneNumber(this,event);"></sj:textfield>
                <sj:textarea rows="3" cols="20" name="contactUs.comments" id="comments"
                    label="Description" requiredposition="first" cssStyle="width:130% ;vertical-align: top;"
                    cssClass="text small requi red"></sj:textarea>
                <sj:submit   cssClass="submit small" value="Submit"
                    indicator="indicator" targets="contactUsContent" />
                   
                <div id="buttons" style="margin: 0px 1000px 83px 0px;">
                    <a id="call_to_action1"
                        href="${pageContext.request.contextPath}/user/ajaxCancelContactUs.do">Cancel
                    </a>
                </div>
                
            </s:form>
            </font>
    </div>
   </div>
 <div class="footer-campus"></div>

<script type="text/javascript">
    $(document).ready(function() {
        $('#contactus').addClass('active');
    });
    $('.numeric').numeric();
    $('.alphabet').alpha();
    $.subscribe('eazyContactFormValidations', function(event, data) {
        if ($('#eazyContactForm').valid())
            return true;
        else
            return false;
    });
    function formatPhoneNumber(object) {
        var phoneString = object.value;
        if (phoneString.length == 1) {
            phoneString = "+91-" + phoneString;
        }
        object.value = phoneString;
    }
     function personDetailErrors()
        {
            var parameters ='';
            var fieldErrorString ='<font style=\"color:red\">';
            var customerName = document.getElementById('customerName').value;
            var customerEmail = document.getElementById('customerEmail').value;
            var phoneNumber = document.getElementById('phoneNumber').value;
            var comments = document.getElementById('comments').value;
            var customerNameTrim = $.trim(customerName);
            var customerEmailTrim = $.trim(customerEmail);
            var phoneNumberTrim = $.trim(phoneNumber);
            var commentsTrim = $.trim(comments);
            if(customerNameTrim == ''){
                document.getElementById('customerName').style.border='1px solid red';
            }
            else{
               document.getElementById('customerName').removeAttribute('style');
            }
            if(customerEmailTrim == ''){
                document.getElementById('customerEmail').style.border='1px solid red';
            }
            else{
               document.getElementById('customerEmail').removeAttribute('style');
            }
            if(phoneNumberTrim == ''){
                document.getElementById('phoneNumber').style.border='1px solid red';
            }
            else{
               document.getElementById('phoneNumber').removeAttribute('style');
            }
            if(commentsTrim == ''){
                document.getElementById('comments').style.border='1px solid red';
            }
            else{
               document.getElementById('comments').removeAttribute('style');
            }
           
           if(customerNameTrim == ''|| customerEmailTrim == ''|| phoneNumber == ''|| commentsTrim == ''){
               fieldErrorString = fieldErrorString + "!Please enter highlighted field values.<br/>";
            }
             fieldErrorString = fieldErrorString + '</font>';
              if(fieldErrorString!='' && fieldErrorString != '<font style="color:red"></font>') {
                document.getElementById('personDetailFieldErrors').innerHTML= fieldErrorString;
                return false;
            }
            else{
                return true;
            }
        }
</script>