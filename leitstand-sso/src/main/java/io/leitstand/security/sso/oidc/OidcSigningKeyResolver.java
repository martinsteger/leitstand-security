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

import java.security.Key;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolverAdapter;

 class OidcSigningKeyResolver extends SigningKeyResolverAdapter{

	private Map<String,Key> keys;
	
	OidcSigningKeyResolver(Map<String,Key> keys){
		this.keys = keys;
	}
	
	@Override
	public Key resolveSigningKey(JwsHeader header, Claims claims) {
		String kid = header.getKeyId();
		return keys.get(kid);		
	}


}
