package net.toracode.moviedb.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
public class User extends BaseEntity {
	@Column(nullable = false)
	private String name;
	@Email
	private String email;
	@Size(min = 6, max = 50)
	private String accountId;

	@OneToMany
	private List<UserCustomList> userCustomList;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<UserCustomList> getUserCustomList() {
		return userCustomList;
	}
	public void setUserCustomList(List<UserCustomList> userCustomList) {
		this.userCustomList = userCustomList;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", accountId='" + accountId + '\'' +
				", userCustomList=" + userCustomList +
				'}';
	}
}
