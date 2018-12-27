package com.ireslab.emc.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ireslab.emc.entity.AdminAccount;
import com.ireslab.emc.model.ClientProfile;
import com.ireslab.emc.repository.AdminAccountRepository;
import com.ireslab.emc.service.ClientUserInfoService;

/**
 * @author iRESlab
 *
 */
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	public ClientUserInfoService clientInfoService;
	
	@Autowired
	public AdminAccountRepository adminAccountRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		

		LOG.info("Checking account details for username - " + userName );

		ClientProfile account = clientInfoService.getClientByUserName(userName);
		if(account.getUserName() == null) {
			
			AdminAccount adminAccount = adminAccountRepo.findByAdminName(userName);
			account = new ClientProfile();
			account.setPassword(adminAccount.getPassword());
			
		}/*else {
			account = clientInfoService.getClientByUserName(userName);
		}
		
		if (account == null) {
			LOG.error("Account not exists for username - " + userName);
			throw new UsernameNotFoundException("User not found.");
		}*/

		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		/*UserDetails UserDetails = new UserCredential(userName, account.getPassword(), authorities);*/
		UserDetails UserDetails = new User(userName, account.getPassword(), authorities);
		
		
		return UserDetails;
	}
}
