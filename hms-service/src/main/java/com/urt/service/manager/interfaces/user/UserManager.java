package com.urt.service.manager.interfaces.user;

import java.util.List;

import com.hyniva.sms.ws.vo.ChangePasswordVO;
import com.hyniva.sms.ws.vo.CustomerVo;
import com.hyniva.sms.ws.vo.ForgotPasswordVO;
import com.hyniva.sms.ws.vo.SchoolAreaMainVO;
import com.hyniva.sms.ws.vo.UserTokenVO;
import com.hyniva.sms.ws.vo.UserVO;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.study.ViewStudentPersonAccountDetails;
import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.base.UniversalManager;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="UserManager.java.html"><i>View Source</i></a></p>
 */
public interface UserManager extends UniversalManager {
    
    ViewStudentPersonAccountDetails getViewStudentDetails(long accountId,String status);
    Customer saveCustomer(Customer customer);
    User saveUser(User user);
    User getAccountByCustIdAndStatus(String username,long custId,String status);
    User getAccountByCustId(String username,long custId);
    void removeRoleByUserIdAndRoleIdNotIn(String roleId,long userId);
    String getRolesByRoleIdAndUserId(long userId,long roleId);
    List<Object[]> searchParentMobileNumber(String mobileNumber, String parentEmail);
    UserVO getUserDetails(UserTokenVO userTokenVO);
    CustomerVo getCustomerDetails(long custId);
    boolean createCustomerSchoolAreaDetails(SchoolAreaMainVO schoolAreaMainVo);
    void removeSchoolAreas(long custId);
    boolean getUserForgotPasswordDetails(ForgotPasswordVO forgotPasswordVO);
    boolean changeUserPasswordDetails(ChangePasswordVO changePasswordVO);
    UserVO getStudentDetailsByAdmissionAndMobileNumbers(String admissionNumber, String mobileNumber, int randomNumber);
    UserVO getStaffDetailsByMobileNumber(String mobileNumber, int randomNumber);
}
