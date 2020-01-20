package io.leitstand.security.sso.oidc;

import static io.leitstand.commons.model.BuilderUtil.assertNotInvalidated;
import static io.leitstand.commons.model.StringUtil.isNonEmptyString;
import static java.util.Collections.emptyMap;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.security.enterprise.credential.Password;

import io.leitstand.commons.model.StringUtil;
import io.leitstand.commons.model.ValueObject;
import io.leitstand.security.auth.UserName;

public class OidcConfig extends ValueObject{

	public static Builder newOpenIdConfig() {
		return new Builder();
	}

	public static class Builder {
		
		private OidcConfig config = new OidcConfig();
		
		
		public Builder withAuthorizationEndpoint(URI endpoint) {
			assertNotInvalidated(getClass(),config);
			config.authorizationEndpoint = endpoint;
			return this;
		}
		
		public Builder withUserInfoEndpoint(URI endpoint) {
			assertNotInvalidated(getClass(), config);
			config.userInfoEndpoint = endpoint;
			return this;
		}
		
		public Builder withTokenEndpoint(URI endpoint) {
			assertNotInvalidated(getClass(), config);
			config.tokenEndpoint = endpoint;
			return this;
		}
		
		public Builder withConnectTimeout(long timeout) {
			assertNotInvalidated(getClass(), config);
			config.connectTimeout = timeout;
			return this;
		}

		public Builder withReadTimeout(long timeout) {
			assertNotInvalidated(getClass(), config);
			config.readTimeout = timeout;
			return this;
		}

		public Builder withRolesClaim(String rolesClaim) {
			assertNotInvalidated(getClass(), config);
			config.rolesClaim = rolesClaim;
			return this;
		}
		
		public Builder withRoleMapping(Map<String,String> roleMapping) {
			assertNotInvalidated(getClass(), config);
			config.roleMapping = new HashMap<>(roleMapping);
			return this;
		}
		
		public Builder withClientId(UserName clientId) {
			assertNotInvalidated(getClass(), config);
			config.clientId = clientId;
			return this;
		}
		
		public Builder withClientSecret(Password clientSecret) {
			assertNotInvalidated(getClass(), config);
			config.clientSecret = clientSecret;
			return this;
		}
		
		public OidcConfig build() {
			try {
				assertNotInvalidated(getClass(), config);
				return config;
			} finally {
				this.config = null;
			}
		}
	
	}
	
	private URI authorizationEndpoint;
	private URI userInfoEndpoint;
	private URI tokenEndpoint;
	private String rolesClaim;
	private Map<String,String> roleMapping = emptyMap();
	private UserName clientId;
	private Password clientSecret;
	private long connectTimeout;
	private long readTimeout;
	
	public URI getAuthorizationEndpoint() {
		return authorizationEndpoint;
	}
	
	public URI getUserInfoEndpoint() {
		return userInfoEndpoint;
	}
	
	public URI getTokenEndpoint() {
		return tokenEndpoint;
	}
	
	public UserName getClientId() {
		return clientId;
	}
	
	public Password getClientSecret() {
		return clientSecret;
	}
	
	public long getConnectTimeout() {
		return connectTimeout;
	}
	
	public long getReadTimeout() {
		return readTimeout;
	}
	
	public String getRolesClaim() {
		return rolesClaim;
	}
	
	public String mapRole(String role) {
		return roleMapping.get(role);
	}

	public boolean isCustomRolesClaimEnabled() {
		return isNonEmptyString(rolesClaim);
	}
	
	
	
}