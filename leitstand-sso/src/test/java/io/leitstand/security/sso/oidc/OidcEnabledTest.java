/*
 * Copyright 2020 RtBrick Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.leitstand.security.sso.oidc;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import io.leitstand.security.sso.oidc.OidcConfigProvider;

@RunWith(Parameterized.class)
public class OidcEnabledTest {

	private static final String AUTHORIZATION_ENDPOINT = "http://localhost/authorize";
	private static final String TOKEN_ENDPOINT = "http://localhost/token";
	private static final String USER_ENDPOINT = "http://localhost/userinfo";
	private static final String CLIENT_SECRET = "secret";
	private static final String CLIENT_ID = "id";
	
	@Parameters
	public static Collection<Object[]> getParameters(){
		return asList(new Object[][] {
						{AUTHORIZATION_ENDPOINT,TOKEN_ENDPOINT,USER_ENDPOINT,CLIENT_ID,CLIENT_SECRET,true},
						{AUTHORIZATION_ENDPOINT,TOKEN_ENDPOINT,USER_ENDPOINT,CLIENT_ID,null,false},
						{AUTHORIZATION_ENDPOINT,TOKEN_ENDPOINT,USER_ENDPOINT,CLIENT_ID,"",false},
						{AUTHORIZATION_ENDPOINT,TOKEN_ENDPOINT,USER_ENDPOINT,null,CLIENT_SECRET,false},
						{AUTHORIZATION_ENDPOINT,TOKEN_ENDPOINT,USER_ENDPOINT,"",CLIENT_SECRET,false},
						{AUTHORIZATION_ENDPOINT,TOKEN_ENDPOINT,null,CLIENT_ID,CLIENT_SECRET,false},
						{AUTHORIZATION_ENDPOINT,TOKEN_ENDPOINT,"",CLIENT_ID,CLIENT_SECRET,false},
						{AUTHORIZATION_ENDPOINT,null,USER_ENDPOINT,CLIENT_ID,CLIENT_SECRET,false},
						{AUTHORIZATION_ENDPOINT,"",USER_ENDPOINT,CLIENT_ID,CLIENT_SECRET,false},
						{null,TOKEN_ENDPOINT,USER_ENDPOINT,CLIENT_ID,CLIENT_SECRET,false},
						{"",TOKEN_ENDPOINT,USER_ENDPOINT,CLIENT_ID,CLIENT_SECRET,false}});
	}
	
	
	
	
	private String authorizationEndpoint;
	private String tokenEndpoint;
	private String userInfoEndpoint;
	private String clientId;
	private String clientSecret;
	private boolean openIdEnabledState;
	
	private OidcConfigProvider provider;
	
	public OidcEnabledTest(String authorizationEndpoint, String tokenEndpoint, String userInfoEndpoint, String clientId, String clientSecret, boolean state) {
		this.provider = new OidcConfigProvider();
		this.authorizationEndpoint = authorizationEndpoint;
		this.tokenEndpoint = tokenEndpoint;
		this.userInfoEndpoint = userInfoEndpoint;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.openIdEnabledState = state;
	}

	@Test
	public void correct_openid_enabled_state() {
		assertThat(openIdEnabledState, CoreMatchers.is(provider.isOpenIdEnabled(authorizationEndpoint, tokenEndpoint, userInfoEndpoint, clientId, clientSecret)));
	}
	
}
