/**
 * 
 */
package com.ireslab.emc.repository;

import org.springframework.data.repository.CrudRepository;

import com.ireslab.emc.entity.AdminAccount;



/**
 * @author Sachin
 *
 */
public interface AdminAccountRepository extends CrudRepository<AdminAccount, Integer> {

	public AdminAccount findByAdminName(String adminName);
	
	public AdminAccount findByEmailAddress(String emailAddress);
	
	public AdminAccount findByResetToken(String resetToken);
}
