package com.ireslab.emc.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.ireslab.emc.properties.EmcProperties;

/**
 * @author iRESlab
 *
 */
@Primary
@Service(value = "clientDetailsService")
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	public EmcProperties emcProperties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.ClientDetailsService#
	 * loadClientByClientId(java.lang.String)
	 */
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		

		BaseClientDetails clientDetails = new BaseClientDetails(emcProperties.clientId, emcProperties.resourceIds,
				emcProperties.scopes, emcProperties.grantTypes, emcProperties.authorities);
		clientDetails.setClientSecret(emcProperties.clientSecret);
		
		
		return clientDetails;
	}
}
