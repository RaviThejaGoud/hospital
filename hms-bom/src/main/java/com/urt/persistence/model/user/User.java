package com.urt.persistence.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.churchgroup.common.constants.Constants;
import com.churchgroup.util.date.DateFunctions;
import com.churchgroup.util.object.ObjectFunctions;
import com.churchgroup.util.string.StringFunctions;
import com.hyniva.sms.ws.vo.UserVO;
import com.urt.persistence.model.base.PersistentObject;
import com.urt.persistence.model.common.Address;
import com.urt.persistence.model.common.PersonDocuments;
import com.urt.persistence.model.common.UserImage;
import com.urt.persistence.model.customer.Customer;
import com.urt.persistence.model.exam.KBank;
import com.urt.persistence.model.study.StaffCurricularActivities;
import com.urt.persistence.model.study.StaffHistory;
import com.urt.persistence.model.study.StudentCurricularActivities;
import com.urt.persistence.model.study.StudentSiblings;
import com.urt.persistence.util.excel.UserExcelRow;
import com.urt.util.excel.parser.ExcelField;
import com.urt.util.excel.parser.ExcelObject;
import com.urt.util.excel.parser.MappedExcelObject;
import com.urt.util.excel.parser.ParseType;

@Entity
@Table(name = "Account", uniqueConstraints= @UniqueConstraint(columnNames = {"username", "custId"}) )
@ExcelObject(parseType = ParseType.ROW, start = 3, end = 11)
public class User  extends PersistentObject implements UserDetails {
	
    private static final long serialVersionUID = 3832626162173359411L;

    protected String username;                    // required
    protected String confirmUserName;                    // required
    protected String password;                    // required
    protected String confirmPassword;
    protected String tempPassword;
    protected String passwordHint;
    protected Set<Role> roles;
    
    //protected Set<PartyAssociatedPerson> family;
    protected UserImage profileImage;
    protected UserImage profileStuImage;
    /**
	 * @param custId the custId to set
	 */	
    
    @MappedExcelObject
	protected Address primaryAddress;
    protected Address communicationAddress;
    protected Address tempararyAddress;
    protected long custId;
    @MappedExcelObject
    protected Person person;
    
    protected boolean enabled;
    protected boolean accountExpired;
    protected boolean accountLocked;
    protected boolean credentialsExpired ;
    protected boolean sharePrivacy;
    protected boolean isUserOnlineNow;
    //protected String parentId;
    protected Set<KBank> kBankFavouriteList;
    @ExcelField(position = 2)
    protected String admissionNumber; 
    protected Set<Customer> branches;
    protected int bioMetricId;    
	protected String staffNumber;
	protected List<StaffHistory> staffHistory;
	protected List<StudentSiblings> studentSiblings;
	protected List<StaffCurricularActivities> staffCurricularActivities;
	protected List<StudentCurricularActivities> studentCurricularActivities;
	private String barcode;
	
	protected String enrollmentCode;
	protected Long selectedStudentId;
	private boolean passwordStatus;
	private String secondaryUsername;
	private String OTP;
	private String otpStatus;
	private List<PersonDocuments> personDocuments;
	
	protected Set<User> parentChilds;
	
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="accountId")
	public List<PersonDocuments> getPersonDocuments() {
		if(ObjectFunctions.isNullOrEmpty(this.personDocuments))
		{
	    	 this.personDocuments=new ArrayList<PersonDocuments>();
	    }
		return personDocuments;
	}
	public void setPersonDocuments(List<PersonDocuments> personDocuments) {
		this.personDocuments = personDocuments;
	}
	@Column(name = "OTP", nullable = true, length = 6)
	public String getOTP() {
		return OTP;
	}
	public void setOTP(String oTP) {
		OTP = oTP;
	}
	@Column(name = "otpStatus",nullable=true, length = 1,columnDefinition="char(1) default 'N'")
	public String getOtpStatus() {
		return otpStatus;
	}
	public void setOtpStatus(String otpStatus) {
		this.otpStatus = otpStatus;
	}
	@Column(name = "secondaryUsername", nullable = false, length = 128)
	public String getSecondaryUsername() {
		return secondaryUsername;
	}
	public void setSecondaryUsername(String secondaryUsername) {
		this.secondaryUsername = secondaryUsername;
	}
	@Column(name = "passwordStatus", nullable = false, length = 1,columnDefinition="char(1) default 'Y'")
	@Type(type="yes_no")
	public boolean isPasswordStatus() {
		return passwordStatus;
	}
	public void setPasswordStatus(boolean passwordStatus) {
		this.passwordStatus = passwordStatus;
	}
	public String getEnrollmentCode() {
		return enrollmentCode;
	}
	public void setEnrollmentCode(String enrollmentCode) {
		this.enrollmentCode = enrollmentCode;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="accountId")
	public List<StudentCurricularActivities> getStudentCurricularActivities() {
		if(ObjectFunctions.isNullOrEmpty(this.studentCurricularActivities))
		{
	    	 this.studentCurricularActivities=new ArrayList<StudentCurricularActivities>();
	    }
		return studentCurricularActivities;
	}

	public void setStudentCurricularActivities(List<StudentCurricularActivities> studentCurricularActivities) {
		this.studentCurricularActivities = studentCurricularActivities;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="accountId") 
	public List<StaffCurricularActivities> getStaffCurricularActivities() {
		if(ObjectFunctions.isNullOrEmpty(this.staffCurricularActivities))
		{
	    	 this.staffCurricularActivities=new ArrayList<StaffCurricularActivities>();
	    }
		return staffCurricularActivities;
	}

	public void setStaffCurricularActivities(
			List<StaffCurricularActivities> staffCurricularActivities) {
		this.staffCurricularActivities = staffCurricularActivities;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="accountId") 
	public List<StudentSiblings> getStudentSiblings() {
		if(ObjectFunctions.isNullOrEmpty(this.studentSiblings))
		{
	    	 this.studentSiblings=new ArrayList<StudentSiblings>();
	    }
		return studentSiblings;
	}

	public void setStudentSiblings(List<StudentSiblings> studentSiblings) {
		this.studentSiblings = studentSiblings;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="accountId") 
	public List<StaffHistory> getStaffHistory() {
		if(ObjectFunctions.isNullOrEmpty(this.staffHistory))
		{
	    	 this.staffHistory=new ArrayList<StaffHistory>();
	    }
		return staffHistory;
	}

	public void setStaffHistory(List<StaffHistory> staffHistory) {
		this.staffHistory = staffHistory;
	}

	/**
	 * @return the staffNumber
	 */
	@Column(name = "staffNumber", nullable = true, length = 20)
	public String getStaffNumber() {
		return staffNumber;
	}

	/**
	 * @param staffNumber the staffNumber to set
	 */
	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	/**
	 * @return the branches
	 */
    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name = "UserBranches",
       joinColumns = { @JoinColumn(name = "userId") },
       inverseJoinColumns = { @JoinColumn(name = "custId") })
	public Set<Customer> getBranches() {
		return branches;
	}

	/**
	 * @param branches the branches to set
	 */
	public void setBranches(Set<Customer> branches) {
		this.branches = branches;
	}

	public User() {
    	this.enabled = Boolean.TRUE;
        this.accountExpired = Boolean.FALSE;
        this.accountLocked = Boolean.FALSE;
        this.credentialsExpired = Boolean.FALSE;
        this.isUserOnlineNow=Boolean.FALSE;
        this.lastAccessDate=DateFunctions.getTodayPlusNdays(0);
        this.passwordStatus = Boolean.TRUE;
    }

    public User(long id) {
        setId(id);
    }

    public User(String username) {
        this.username = username;
    }
    
    @Column(name = "admissionNumber", nullable = true, length = 128)
    public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	@Override
	@Column(name = "username", nullable = false, length = 128)
    public String getUsername() {
        return username;
    }

    @Override
	@Column(name = "password", nullable = false, length = 128)
    public String getPassword() {
        return password;
    }

    
    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Column(name = "passwordHint", nullable = true, length = 255)
    public String getPasswordHint() {
        return passwordHint;
    }
  
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "UserRoles",
       joinColumns = { @JoinColumn(name = "userId") },
       inverseJoinColumns = { @JoinColumn(name = "roleId") })

    public Set<Role> getRoles() {
        if(roles == null)
        {
            roles = new HashSet<Role>();
        }
        return roles;
    }

    /**
     * Adds a role for the user
     * @param role
     */
    public void addRole(Role role) {
        if(!hasRoleAlready(role))
            getRoles().add(role);
        
    }
    public void addNewRole(Role role) {
        getRoles().add(role);
    }
    
    /**
     * Adds a role for the user
     * @param role
     */
    public void removeRole(Role role) {
        getRoles().remove(role);
    }
    
    
    @Override
	@Transient
    public Collection<GrantedAuthority> getAuthorities() {
    	Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (Role role : getRoles())
    	{
    	  authorities.add(new GrantedAuthorityImpl(role.getAuthority()));
    	}
    	return authorities;

    }
    @Transient
    public String getUserRoleDescription()
    {
    	String roled="System";
    	for (Role role : getRoles())
       	{
       	  roled = role.getDescription();
       	}
    	return roled;
    }
    @Transient
    public boolean isAdminOrDelegate()
    {
        boolean administrator = false;
        Collection<GrantedAuthority> authorities = getAuthorities();
        for (GrantedAuthority authority : authorities)
    	{
            if (authority.getAuthority().equals(Constants.ADMIN_ROLE)
                    || authority.getAuthority().equals(Constants.DELEGATE_ROLE)) {
                administrator = true;
                break;
            }
        }  
        return administrator;
    }
    @Transient
    public boolean getIsAdminOrDelegate()
    {
       return isAdminOrDelegate();
    }
    @Transient
    public String getAdminOrDelegate()
    {
        if(isAdminOrDelegate())
            return "Y";
        else
            return "N";
    }
    
    @Transient
    public boolean hasRoleAlready(Role lRole)
    {
        boolean hasRole = false;
        Collection<GrantedAuthority> authorities = getAuthorities();
        for (GrantedAuthority authority : authorities)
    	{
            if (authority.getAuthority().equals(lRole.getAuthority()))
            {
            	hasRole = true;
                break;
            }
        }  
        return hasRole;
    }
    @Transient
    public Role getUserAdminRole()
    {
        Role role= new Role();
        Collection<GrantedAuthority> authorities = getAuthorities();
        for (GrantedAuthority authority : authorities)
    	{
            if (authority.getAuthority().equals(Constants.ADMIN_ROLE)
                    || authority.getAuthority().equals(Constants.DELEGATE_ROLE)) {
            	role = (Role)authority;
                break;
            }
        }  
        return role;
    }

    @Override
	@Column(name = "accountEnabled", nullable = true, length = 1, columnDefinition="char(1) default 'Y'")
    @Type(type="yes_no")
    
    public boolean isEnabled() {
        return enabled;
    }
    
    @Column(name = "accountExpired", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
    public boolean isAccountExpired() {
        return accountExpired;
    }
    
    /**
     * @see org.acegisecurity.userdetails.UserDetails#isAccountNonExpired()
     */
    @Override
	@Transient
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    @Column(name = "accountLocked", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
    public boolean isAccountLocked() {
        return accountLocked;
    }
    
    /**
     * @see org.acegisecurity.userdetails.UserDetails#isAccountNonLocked()
     */
    @Override
	@Transient
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    @Column(name = "credentialsExpired", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }
    
    /**
     * @see org.acegisecurity.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Override
	@Transient
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }
     
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
   
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }
    
    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }
    
    @Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final User user = (User) o;

        if (username != null ? !username.equals(user.getUsername()) : user.getUsername() != null) return false;

        return true;
    }

    @Override
	public int hashCode() {
        return (username != null ? username.hashCode() : 0);
    }

    

    /**
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", this.getId())
                .append("accountExpired", this.accountExpired)
                .append("lastAccessDate", super.lastAccessDate)
                .append("roles", this.roles)
                .append("accountNonExpired", this.isAccountNonExpired())
                .append("credentialsNonExpired", this.isCredentialsNonExpired())
                .append("enabled", this.enabled)
                .append("accountNonLocked",
                        this.isAccountNonLocked()).append("credentialsExpired",
                        this.credentialsExpired).append("passwordHint",
                        this.passwordHint)
                .append("authorities", this.getAuthorities()).append(
                        "username", this.username).append("password",
                        this.password).append("confirmPassword",
                        this.confirmPassword).append("accountLocked",
                        this.accountLocked).toString();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
        @Override
        public int compareTo(Object object) {
            
            User target = (User) object;
            if (target.getUsername() != null && this.getUsername() != null)
            {
                if(this.getUsername().equalsIgnoreCase(target.getUsername()))
                    return 0;
                else 
                   return this.getUsername().compareToIgnoreCase(target.getUsername());
                     
            }
            return 0;
        }
    

    /**
     * @return the confirmUserName
     */
        @Transient
    public String getConfirmUserName() {
        return confirmUserName;
    }

    /**
     * @param confirmUserName the confirmUserName to set
     */
    public void setConfirmUserName(String confirmUserName) {
        this.confirmUserName = confirmUserName;
    }
   
  

    /**
     * @return the tempPassword
     */
    @Transient
    public String getTempPassword() {
        return tempPassword;
    }

    /**
     * @param tempPassword the tempPassword to set
     */
    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }
    
    @Transient
    public String getLastAccessInfo()
    {
        StringBuffer userStatus = new StringBuffer();
        if(isUserOnlineNow())
            userStatus.append("User is online.");
        else if(!ObjectFunctions.isNullOrEmpty(getLastAccessDate()))
        {
            userStatus.append("Last logged on ");
            int days = DateFunctions.daysBetween(getLastAccessDate(), new Date());
            if(days > 0)
            {
                userStatus.append(days);
                userStatus.append(" day(s) ago");
            }
            else
            {
                long diff = DateFunctions.timeDifference(getLastAccessDate())/(1000 * 60);
                if(diff >= 60)
                {
                    userStatus.append(diff/60);
                    userStatus.append(" hour(s) ago");
                }
                else
                {
                    userStatus.append(diff);
                    userStatus.append(" mins ago");
                }
            }
        }
        return userStatus.toString();
            
    }
    @Transient
    public int getOffLineDays()
    {
        if(isUserOnlineNow())
            return 0;
        else if(!ObjectFunctions.isNullOrEmpty(getLastAccessDate()))
        {
            int days = DateFunctions.daysBetween(getLastAccessDate(), new Date());
            if(days > 0)
            {
                return days;
            }
            else
            {
                long diff = DateFunctions.timeDifference(getLastAccessDate())/(1000 * 60);
                return (int) (diff/(60*24));
            }
        }
        return 0;
    }
    
    @Transient
    public String getUserRole()
    {           
        Collection<GrantedAuthority> authorities = getAuthorities();
        for (GrantedAuthority authority : authorities)
    	{
            if (authority.getAuthority().equals(Constants.USER_ROLE)) {
                return Constants.USER_ROLE;
            }
            else if (authority.getAuthority().equals(Constants.ADMIN_ROLE)) {
                return Constants.ADMIN_ROLE;
            }
            else if (authority.getAuthority().equals(Constants.SCHOOL_TEACHER)) {
                return Constants.SCHOOL_TEACHER;
            }
            else if (authority.getAuthority().equals(Constants.SCHOOL_HOD)) {
                return Constants.SCHOOL_HOD;
            }
            else if (authority.getAuthority().equals(Constants.SCHOOL_PRINCIPAL)) {
                return Constants.SCHOOL_PRINCIPAL;
            }
            else if (authority.getAuthority().equals(Constants.SCHOOL_VICEPRINCIPAL)) {
                return Constants.SCHOOL_VICEPRINCIPAL;
            }
            else if (authority.getAuthority().equals(Constants.SCHOOL_ADMINOFFICER)) {
                return Constants.SCHOOL_ADMINOFFICER;
            } else if (authority.getAuthority().equals(Constants.SCHOOL_ADMIN_COORDINATOR)) {
                return Constants.SCHOOL_ADMIN_COORDINATOR;
            }
            else if(authority.getAuthority().equalsIgnoreCase(Constants.SCHOOL_ROLE_DIRECTOR)){
            	return Constants.SCHOOL_ROLE_DIRECTOR;
            }
            else{
                if (authority.getAuthority().equals(Constants.MASTER_ADMIN)) {
                    return Constants.MASTER_ADMIN;
                }
                else{
                    return null;
                }
                
            }
        }  
        
        return null;
    }
    
  
	/**
	 * @return the isUserOnlineNow
	 */
    @Column(name = "userOnlineNow", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isUserOnlineNow() {
		return isUserOnlineNow;
	}

	/**
	 * @param isUserOnlineNow the isUserOnlineNow to set
	 */
	public void setUserOnlineNow(boolean isUserOnlineNow) {
		this.isUserOnlineNow = isUserOnlineNow;
	}

	/**
	 * @return the family
	 */
	/*@Transient
	public Set<PartyAssociatedPerson> getFamily() {
		return family;
	}

	*//**
	 * @param family the family to set
	 *//*
	public void setFamily(Set<PartyAssociatedPerson> family) {
		this.family = family;
	}*/


  

	/**
	 * @return the profileImage
	 * @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
	 */
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="imageId", insertable=true, updatable=true) 
	public UserImage getProfileImage() {
		return profileImage;
	}

	/**
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(UserImage profileImage) {
		this.profileImage = profileImage;
	}

	/**
	 * @return the primaryAddress
	 */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="paddressId", insertable=true, updatable=true) 
	public Address getPrimaryAddress() {
		return primaryAddress;
	}

	/**
	 * @param primaryAddress the primaryAddress to set
	 */
	public void setPrimaryAddress(Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	/**
	 * @return the communicationAddress
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="caddressId", insertable=true, updatable=true) 
	public Address getCommunicationAddress() {
		return communicationAddress;
	}

	/**
	 * @param communicationAddress the communicationAddress to set
	 */
	public void setCommunicationAddress(Address communicationAddress) {
		this.communicationAddress = communicationAddress;
	}

	/**
	 * @return the tempararyAddress
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="taddressId", insertable=true, updatable=true) 
	public Address getTempararyAddress() {
		return tempararyAddress;
	}

	/**
	 * @param tempararyAddress the tempararyAddress to set
	 */
	public void setTempararyAddress(Address tempararyAddress) {
		this.tempararyAddress = tempararyAddress;
	}

	/**
	 * @return the person
	 */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="personId", insertable=true, updatable=true) 
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	@Transient
	public String getFullPersonName() {
		if(ObjectFunctions.isNullOrEmpty(this.person))
		{
			return "";
		}
		return this.person.getDisplayName();
	}
	
	/**
     * Add to the List of address for this person.  Do not add
     * nulls or existing (identical objects) to the list.  On a success-
     * ful add, return a true, otherwise return a false.
     * 
     * @pre anAddress != null
     * @pre anAddress does not already exist
     * 
     * @param anAddress
     * @return boolean
     */
    public boolean addCommunicationAddress(Address anAddress)
    {
        if(ObjectFunctions.isNullOrEmpty(getCommunicationAddress()))
        {
            setCommunicationAddress(anAddress);
        }
        else
        {
        	getCommunicationAddress().copyFrom(anAddress);
        }

        return true;
    }

	/**
	 * @return the sharePrivacy
	 */
    @Column(name = "sharePrivacy", nullable = true, length = 1,columnDefinition="char(1) default 'N'")
    @Type(type="yes_no")
	public boolean isSharePrivacy() {
		return sharePrivacy;
	}

	/**
	 * @param sharePrivacy the sharePrivacy to set
	 */
	public void setSharePrivacy(boolean sharePrivacy) {
		this.sharePrivacy = sharePrivacy;
	}
	
	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}
	
	public void setCustId(long custId) {
		this.custId = custId;
	}
	
	@Transient
	public boolean isSchoolAdmin(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_ADMIN) || authority.getAuthority().equals(Constants.SCHOOL_PRINCIPAL) || authority.getAuthority().equals(Constants.SCHOOL_ADMINOFFICER) || authority.getAuthority().equals(Constants.SCHOOL_VICEPRINCIPAL)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsSchoolAdmin(){
		if(isSchoolAdmin()){
			return "Y";
		}
		return "N";
    }
	
	/**
	 * @returns boolean
	 */
	
	@Transient
	public boolean isOnlySchoolAdmin(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_ADMIN) || authority.getAuthority().equals(Constants.SCHOOL_ADMINOFFICER)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
	public boolean isAdmin(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_ADMIN)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsOnlySchoolAdmin(){
		if(isOnlySchoolAdmin()){
			return "Y";
		}
		return "N";
    }
	
	
	/**
	 * @returns boolean
	 */
	@Transient
	public boolean isSchoolTeacher(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_TEACHER) || authority.getAuthority().equals(Constants.SCHOOL_HOD) || authority.getAuthority().equals(Constants.SCHOOL_ADMIN_COORDINATOR)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsSchoolTeacher(){
		if(isSchoolTeacher()){
			return "Y";
		}
		return "N";
    }
	
	@Transient
	public boolean isOnlySchoolTeacher(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_TEACHER)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsOnlySchoolTeacher(){
		if(isOnlySchoolTeacher()){
			return "Y";
		}
		return "N";
    }
	@Transient
	public boolean isOnlySchoolHod(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_HOD)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsOnlySchoolHod(){
		if(isOnlySchoolHod()){
			return "Y";
		}
		return "N";
    }
	@Transient
    public String getIsSchoolStudent(){
		if(isSchoolStudent()){
			return "Y";
		}
		return "N";
    }
	
	/**
	 * @returns boolean
	 */
	@Transient
	public boolean isSchoolStudent(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_STUDENT)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsSchoolTransport(){
		if(isSchoolTransport()){
			return "Y";
		}
		return "N";
    }
	
	/**
	 * @returns boolean
	 */
	@Transient
	public boolean isSchoolTransport(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_TRANSPORT)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
	public boolean isTransportFinance(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_TRANSPORTFINANCE)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
	    public String getIsTransportFinance(){
			if(isTransportFinance()){
				return "Y";
			}
			return "N";
	    }
	@Transient
    public String getIsSchoolFinance(){
		if(isSchoolFinance()){
			return "Y";
		}
		return "N";
    }
	@Transient
    public String getIsSchoolLibrarian(){
		if(isSchoolLibrarian()){
			return "Y";
		}
		return "N";
    }
	@Transient
    public String getIsMasterAdmin(){
		if(isMasterAdmin()){
			return "Y";
		}
		return "N";
    }
	/**
	 * @returns boolean
	 */
	@Transient
	public boolean isSchoolFinance(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_FINANCE)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsParent(){
            if(isParent()){
			return "Y";
		}
		return "N";
    }
	
	/**
	 * @returns boolean
	 */
	@Transient
	public boolean isParent(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_PARENT)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	
	
	
	/*public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}*/

	@Transient
	public boolean isHelper(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_HELPER)){	                 
		                return true;	               
		            }	
		        	authority=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsHelper(){
		if(isHelper()){
			return "Y";
		}
		return "N";
    }
	
	@Transient
	public boolean isDriver(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_DRIVER)){	                 
		                return true;	               
		            }	
		        	authority=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsDriver(){
		if(isDriver()){
			return "Y";
		}
		return "N";
    }
	
	@Transient
    public String getIsSchoolPrincipal(){
            if(isSchoolPrincipal()){
			return "Y";
		}
		return "N";
    }
	
	/**
	 * @returns boolean
	 */
	@Transient
	public boolean isSchoolPrincipal(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_PRINCIPAL) || authority.getAuthority().equals(Constants.SCHOOL_VICEPRINCIPAL)){                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
	public boolean isSchoolLibrarian(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_LIBRARIAN)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
	public boolean isHostelFinance(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_HOSTELFINANCE)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	
	@Transient
	    public String getIsHostelFinance(){
			if(isHostelFinance()){
				return "Y";
			}
			return "N";
	    }
	@Transient
	public boolean isSchoolHostel(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_HOSTEL)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
	    public String getIsSchoolHostel(){
			if(isSchoolHostel()){
				return "Y";
			}
			return "N";
	    }
	
	@Transient
	public boolean isMasterAdmin(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_MASTERADMIN)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	
	@Transient
	public boolean isSchoolClerk(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_CLERK)){	                 
		                return true;	               
		            }	
		        	authority=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsSchoolClerk(){
		if(isSchoolClerk()){
			return "Y";
		}
		return "N";
    }
	
	@Transient
	public boolean isSchoolSEO(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_SEO)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
	    public String getIsSEO(){
			if(isSchoolSEO()){
				return "Y";
			}
			return "N";
	    }
	
	@Transient
	public boolean isSchoolDEO(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_DEO)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
	    public String getIsDEO(){
			if(isSchoolDEO()){
				return "Y";
			}
			return "N";
	    }
	
	@Transient
	public boolean isSchoolCEO(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_CEO)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsCEO(){
		if(isSchoolCEO()){
			return "Y";
		}
		return "N";
    }
	@Transient
	public boolean isSecretary(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_SECRETARY)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsSecretary(){
		if(isSecretary()){
			return "Y";
		}
		return "N";
    }
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name = "kBankFavourite",
	   joinColumns = { @JoinColumn(name = "userId") },
	   inverseJoinColumns = { @JoinColumn(name = "kBankId") })
	public Set<KBank> getkBankFavouriteList() {
		return kBankFavouriteList;
	}
	public void setkBankFavouriteList(Set<KBank> kBankFavouriteList) {
		this.kBankFavouriteList = kBankFavouriteList;
	}
	public void addKBankFavourite(KBank kBank) {
		getkBankFavouriteList().add(kBank);
    }
	@Transient
	public String getPersonThumbnailImage() {
		if (ObjectFunctions.isNullOrEmpty(getProfileImage())) {
			return "/images/common/photo_notAvailable.jpg";
		} else {
			return getProfileImage().getThumbNailFile();
		}
	}
	public User copyBeans(User newUser, User oldUser,boolean isChangedAdmissionNo)
    {
		if(isChangedAdmissionNo)
		{
			oldUser.setAdmissionNumber(newUser.getAdmissionNumber());
			oldUser.setUsername(newUser.getUsername());
			oldUser.setPassword(newUser.getPassword());
		}
		return oldUser;
		
    }

	/**
	 * @return the bioMetricId
	 */
	@Column(name = "bioMetricId", nullable = true, length = 6, columnDefinition="int(6) default 0" )
	public int getBioMetricId() {
		return bioMetricId;
	}

	/**
	 * @param bioMetricId the bioMetricId to set
	 */
	public void setBioMetricId(int bioMetricId) {
		this.bioMetricId = bioMetricId;
	}
	public void copyFrom(User account) {
		this.accountExpired = account.isAccountExpired();
		this.accountLocked = account.isAccountLocked();
		this.credentialsExpired = account.isCredentialsExpired();
		this.enabled = account.isEnabled();
	}
	@Transient
	public String getUserRoleName(){
		if(ObjectFunctions.isNullOrEmpty(this.getRoles()))
			return null;
		else
			return this.getRoles().iterator().next().getName();
	}
	public void copyStaffExcelData(UserExcelRow userExcel){
		this.username = userExcel.getUsername();
		this.secondaryUsername = userExcel.getSecondaryUsername();
		this.password = userExcel.getPassword();
		this.staffNumber = userExcel.getStaffNumber();
		/*if(StringFunctions.isNotNullOrEmpty(userExcel.getBioMetricId()) && userExcel.getBioMetricId().matches("[0-9]+"))
			this.bioMetricId = Integer.valueOf(userExcel.getBioMetricId());
		else
			this.bioMetricId = 0;*/
	}
	
	/**
	 * @return the profileStuImage
	 * @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
	 */
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="studentImageId", insertable=true, updatable=true) 
	public UserImage getProfileStuImage() {
		return profileStuImage;
	}
	public void setProfileStuImage(UserImage profileStuImage) {
		this.profileStuImage = profileStuImage;
	}
	
	@Transient
	public boolean isSchoolAsstStaff(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_ASST_STAFF)){	                 
		                return true;	               
		            }	
		        	authority=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsSchoolAsstStaff(){
		if(isSchoolAsstStaff()){
			return "Y";
		}
		return "N";
    }
	
	@Transient
    public String getIsBEO(){
		if(isSchoolBEO()){
			return "Y";
		}
		return "N";
    }
	
	@Transient
	public boolean isSchoolBEO(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_BEO)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public long getRoleId() {
		long roleId = 0;
    	for (Role role : getRoles()) {
       	  roleId = role.getId();
       	}
    	return roleId;
    }
	@Transient
	public void addStaffHistory(StaffHistory staffHistory) {
		if(ObjectFunctions.isNullOrEmpty(staffHistory)){
			staffHistory=new StaffHistory();
		}
		getStaffHistory().add(staffHistory);
	}
	@Transient
	public void addStudentSiblings(StudentSiblings studentSiblings) {
		if(ObjectFunctions.isNullOrEmpty(studentSiblings)){
			studentSiblings=new StudentSiblings();
		}
		getStudentSiblings().add(studentSiblings);
	}
	@Transient
	public void addStaffCurricularActivities(StaffCurricularActivities staffCurricularActivities) {
		if(ObjectFunctions.isNullOrEmpty(staffCurricularActivities)){
			staffCurricularActivities=new StaffCurricularActivities();
		}
		getStaffCurricularActivities().add(staffCurricularActivities);
	}
	@Transient
	public void addStudentCurricularActivities(StudentCurricularActivities studentCurricularActivities) {
		if(ObjectFunctions.isNullOrEmpty(studentCurricularActivities)){
			studentCurricularActivities=new StudentCurricularActivities();
		}
		getStudentCurricularActivities().add(studentCurricularActivities);
	}
	
	@Transient
	public boolean isSecretaryPA(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_SECRETARY_PA)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsSecretaryPA(){
		if(isSecretaryPA()){
			return "Y";
		}
		return "N";
    }
	
	@Transient
	public boolean isSchoolManager(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_MANAGER)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsSchoolManager(){
		if(isSchoolManager()){
			return "Y";
		}
		return "N";
    }
	@Transient
	public boolean isChairMan(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_ROLE_CHAIR_MAN)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsChairMan(){
		if(isChairMan()){
			return "Y";
		}
		return "N";
    }
	@Transient
	public boolean isMessManager(){
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_ROLE_MESS_MANAGER)){	                 
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsMessManager(){
		if(isMessManager()){
			return "Y";
		}
		return "N";
    }
	
	public UserVO copyFromEntityToVo(User user)
	{
		UserVO userVo = new UserVO();
		userVo.id = user.id;
		userVo.username = user.username;
		userVo.password = user.password;
		userVo.passwordHint = user.passwordHint;
		//userVo.roles = userVoVo.roles;
		//userVo.family = userVoVo.family;
		//userVo.profileImage = userVoVo.profileImage;
		//userVo.profileStuImage = userVoVo.profileStuImage;
		//userVo.primaryAddress = userVoVo.primaryAddress;
		//userVo.communicationAddress = userVoVo.communicationAddress;
		//userVo.tempararyAddress = userVoVo.tempararyAddress;
		userVo.custId = user.custId;
		//userVo.person = userVoVo.person;
		//userVo.enabled = user.enabled;
		userVo.accountExpired = user.accountExpired;
		userVo.accountLocked = user.accountLocked;
		userVo.credentialsExpired = user.credentialsExpired;
		userVo.sharePrivacy = user.sharePrivacy;
		userVo.isUserOnlineNow = user.isUserOnlineNow;
		if(!ObjectFunctions.isNullOrEmpty(user.getStudentParent())) {
			userVo.parentId = user.getStudentParent().getId();
		}
		//userVo.kBankFavouriteList = userVoVo.kBankFavouriteList;
		userVo.admissionNumber = user.admissionNumber;
		//ser.branches = userVoVo.branches;
		userVo.bioMetricId = user.bioMetricId;
		userVo.staffNumber = user.staffNumber;
		//userVo.staffHistory = userVoVo.staffHistory;
		//userVo.studentSiblings = userVoVo.studentSiblings;
		//userVo.staffCurricularActivities = userVoVo.staffCurricularActivities;
		//userVo.studentCurricularActivities = userVoVo.studentCurricularActivities;
		userVo.barcode = user.barcode;
		userVo.enrollmentCode = user.enrollmentCode;
		//userVo.setVersion(user.getVersion());
		
		if(!ObjectFunctions.isNullOrEmpty(user.getPerson()))
        {
            Person person = user.getPerson();
            userVo.setPersonVo(person.copyFromEntityToVo(person));
        }
		
		if(!ObjectFunctions.isNullOrEmpty(user.getPrimaryAddress()))
        {
            Address primaryAdress = user.getPrimaryAddress();
            userVo.setPrimaryAddressVo(primaryAdress.copyFromEntityToVo(primaryAdress));
        }
		
		if(!ObjectFunctions.isNullOrEmpty(user.getRoles()))
        {
            Role role = new Role();
            userVo.setRolesVoSet(role.copyFromEntityToVo(user.getRoles()));
        }
		
		
		return userVo;
	}
	
	
	public User copyFromVoToEntity(User user, UserVO userVo)
	{
		if(user.getId() == 0)
			user.id = userVo.id;
		if(StringFunctions.isNullOrEmpty(user.username)) {
			user.username = userVo.username;
		}
		if(StringFunctions.isNullOrEmpty(user.password)) {
			user.password = userVo.password;
		}
		if(StringFunctions.isNullOrEmpty(user.secondaryUsername))
			user.secondaryUsername = userVo.secondaryUsername;
		//user.passwordHint = userVo.passwordHint;
		//user.setVersion(userVo.version);
		//user.roles = userVo.roles;
		//user.family = userVo.family;
		//user.profileImage = userVo.profileImage;
		//user.profileStuImage = userVo.profileStuImage;
		//user.primaryAddress = userVo.primaryAddress;
		//user.communicationAddress = userVo.communicationAddress;
		//user.tempararyAddress = userVo.tempararyAddress;
		if(user.getCustId() == 0) {
			user.custId = userVo.custId;
		}
		//user.person = userVo.person;
		//user.enabled = userVo.enabled;
		//user.accountExpired = userVo.accountExpired;
		//user.accountLocked = userVo.accountLocked;
		//user.credentialsExpired = userVo.credentialsExpired;
		//user.sharePrivacy = userVo.sharePrivacy;
		//user.isUserOnlineNow = userVo.isUserOnlineNow;
		//user.parentId = userVo.parentId;
		//user.kBankFavouriteList = userVo.kBankFavouriteList;
		user.admissionNumber = userVo.admissionNumber;
		//ser.branches = userVo.branches;
		user.bioMetricId = userVo.bioMetricId;
		user.staffNumber = userVo.staffNumber;
		//user.staffHistory = userVo.staffHistory;
		//user.studentSiblings = userVo.studentSiblings;
		//user.staffCurricularActivities = userVo.staffCurricularActivities;
		//user.studentCurricularActivities = userVo.studentCurricularActivities;
		user.barcode = userVo.barcode;
		user.enrollmentCode = userVo.enrollmentCode;
		
		return user;
	}
	
	public List<UserVO> copyFromEntityToUserVoList(List<User> usersList)
	{
		List<UserVO> userVoList = new ArrayList<UserVO>();
		if(!ObjectFunctions.isNullOrEmpty(usersList))
		{
			for(User account : usersList)
			{
				userVoList.add(copyFromEntityToVo(account));
				account = null;
			}
		}
		return userVoList;
		
		
	}
	@Transient
	public Long getSelectedStudentId() {
		return selectedStudentId;
	}
	public void setSelectedStudentId(Long selectedStudentId) {
		this.selectedStudentId = selectedStudentId;
	}
	
	
	@Transient
    public String getRoleName() {
		String roleName = null;
    	for (Role role : getRoles()) {
    		roleName = role.getName();
       	}
    	return roleName;
    }
	
	@Transient
	public void addPersonDocuments(PersonDocuments personDocuments) {
		if(ObjectFunctions.isNullOrEmpty(personDocuments)){
			personDocuments=new PersonDocuments();
		}
		getPersonDocuments().add(personDocuments);
	}
	
	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "studentparent",
       joinColumns = { @JoinColumn(name = "studentAccountId") },
       inverseJoinColumns = { @JoinColumn(name = "parentAccountId") })
	public Set<User> getParentChilds() {
		if(parentChilds == null)
        {
			parentChilds = new HashSet<User>();
        }
		return parentChilds;
	}
	public void setParentChilds(Set<User> parentChilds) {
		this.parentChilds = parentChilds;
	}
	 public void addParentChild(User user) {
		 getParentChilds().add(user);
	 }
	    
    public void removeParentChild(User user) {
    	getParentChilds().remove(user);
    }
	//For child to get parent obj
    @Transient
    public User getStudentParent() {
		if(!ObjectFunctions.isNullOrEmpty(this.getParentChilds()))
		return this.getParentChilds().iterator().next();
		else
			return null;
	}
    
	@Transient
	public boolean isAdminCoordinator(){
		
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_ADMIN_COORDINATOR)){	
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsAdminCoordinator(){
		if(isAdminCoordinator()){
			return "Y";
		}
		return "N";
    }
	
	@Transient
	public boolean isStoreKeeper(){
		
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_ROLE_STOREKEEPER)){	
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsStoreKeeper(){
		if(isStoreKeeper()){
			return "Y";
		}
		return "N";
    }
	
	@Transient
	public boolean isSchoolDirector(){
		
		try{
			if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
				Iterator authorityIterator=getAuthorities().iterator();
				for(Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();){
					GrantedAuthority authority=authoryObjectIterator.next();
					if(authority.getAuthority().equalsIgnoreCase(Constants.SCHOOL_ROLE_DIRECTOR)){
						return true;
					}
					authorityIterator=null;
				}
				authorityIterator=null;
			}
			return false;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	@Transient
	public String getIsSchoolDirector(){
		if(isSchoolDirector()){
			return "Y";
		}
		return "N";
	}
	
	@Transient
	public boolean isReceptionist(){
		
    	try{
	    	if(!ObjectFunctions.isNullOrEmpty(getAuthorities())){
		    	Iterator authorityIterator=getAuthorities().iterator();
		        for (Iterator<GrantedAuthority> authoryObjectIterator=authorityIterator; authoryObjectIterator.hasNext();) {
		        	GrantedAuthority authority=authoryObjectIterator.next();
		        	if (authority.getAuthority().equals(Constants.SCHOOL_ROLE_RECEPTIONIST)){	
		                return true;	               
		            }	
		        	authorityIterator=null;
		        }  
		        authorityIterator=null;
	    	}
	    	return false;
    	}
    	catch(Exception ex){
    		ex.printStackTrace();	    		
    	}
    	return false;
    }
	@Transient
    public String getIsReceptionist(){
		if(isReceptionist()){
			return "Y";
		}
		return "N";
    }
}

