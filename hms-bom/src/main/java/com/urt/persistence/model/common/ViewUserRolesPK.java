package com.urt.persistence.model.common;

import javax.persistence.Embeddable;

/**
 * ViewUserRolesPK entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ViewUserRolesPK implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String roleName;
	private String roleDescription;
	private String firstName;
	private String lastName;
	private Long custId;
	private Long accountId;
	private Long roleId;
	private boolean accountExpired;
	private String fullName;
	private String formatedFullName;

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @return the formatedFullName
	 */
	public String getFormatedFullName() {
		return formatedFullName;
	}

	/**
	 * @param formatedFullName the formatedFullName to set
	 */
	public void setFormatedFullName(String formatedFullName) {
		this.formatedFullName = formatedFullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/** default constructor */
	public ViewUserRolesPK() {
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof ViewUserRolesPK))
			return false;
		ViewUserRolesPK castOther = (ViewUserRolesPK) other;

		return this.accountId == castOther.accountId || (this
				.accountId != null
				&& castOther.accountId != null && this.accountId.equals(
				castOther.accountId))
				&& this.roleId == castOther.roleId || (this
						.roleId != null
						&& castOther.roleId != null && this
						.roleId.equals(
								castOther.roleId))
				&& this.custId == castOther.custId || (this
						.custId != null
						&& castOther.custId != null && this
						.custId.equals(
								castOther.custId))
				&& this.lastName == castOther.lastName || (this
						.lastName != null
						&& castOther.lastName != null && this
						.lastName.equals(castOther.lastName))
				&& this.firstName == castOther.firstName || (this
						.firstName != null
						&& castOther.firstName != null && this.firstName
						.equals(castOther.firstName))
				&& this.roleDescription == castOther.roleDescription || (this
						.roleDescription != null
						&& castOther.roleDescription != null && this
						.roleDescription.equals(castOther.roleDescription))
				&& this.getUsername() == castOther.getUsername() || this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (accountId == null ? 0 : this.accountId.hashCode());
		result = 37
				* result
				+ (roleId == null ? 0 : this.roleId
						.hashCode());
		result = 37
				* result
				+ (custId == null ? 0 : this.custId
						.hashCode());
		result = 37 * result
				+ (lastName == null ? 0 : this.lastName.hashCode());
		result = 37 * result
				+ (firstName == null ? 0 : this.firstName.hashCode());
		result = 37 * result
				+ (roleName == null ? 0 : this.roleName.hashCode());
		result = 37
				* result
				+ (roleDescription == null ? 0 : this.roleDescription
						.hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		return result;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleDescription
	 */
	public String getRoleDescription() {
		return roleDescription;
	}

	/**
	 * @param roleDescription
	 *            the roleDescription to set
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the custId
	 */
	public Long getCustId() {
		return custId;
	}

	/**
	 * @param custId
	 *            the custId to set
	 */
	public void setCustId(Long custId) {
		this.custId = custId;
	}

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the accountExpired
	 */
	public boolean isAccountExpired() {
		return accountExpired;
	}

	/**
	 * @param accountExpired the accountExpired to set
	 */
	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

}