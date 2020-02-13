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
package io.leitstand.security.accesskeys.model;

import static java.util.Collections.unmodifiableSet;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import io.leitstand.commons.model.AbstractEntity;
import io.leitstand.commons.model.Query;
import io.leitstand.security.accesskeys.jpa.AccessKeyIdConverter;
import io.leitstand.security.accesskeys.jpa.AccessKeyNameConverter;
import io.leitstand.security.accesskeys.service.AccessKeyName;
import io.leitstand.security.auth.accesskey.AccessKeyId;

@Entity
@Table(schema="auth", name="accesskey")
@NamedQuery(name="AccessKey.findByAccessKeyId",
			query="SELECT k FROM AccessKey k WHERE k.uuid=:uuid")
@NamedQuery(name="AccessKey.findByAccessKeyName",
			query="SELECT k FROM AccessKey k WHERE k.name=:name")
@NamedQuery(name="AccessKey.findByNamePattern",
			query="SELECT k FROM AccessKey k WHERE CONCAT('',k.name) REGEXP :pattern ORDER BY k.name")
public class AccessKey extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	public static Query<AccessKey> findByAccessKeyId(AccessKeyId accessKeyId){
		return em -> em.createNamedQuery("AccessKey.findByAccessKeyId",AccessKey.class)
					   .setParameter("uuid",accessKeyId)
					   .getSingleResult();
	}
	
	public static Query<AccessKey> findByAccessKeyName(AccessKeyName accessKeyName) {
		return em -> em.createNamedQuery("AccessKey.findByAccessKeyName",AccessKey.class)
				   	   .setParameter("name",accessKeyName)
				   	   .getSingleResult();
	}
	
	public static Query<List<AccessKey>> findByNamePattern(String pattern){
		return em -> em.createNamedQuery("AccessKey.findByNamePattern",AccessKey.class)
					   .setParameter("pattern",pattern)
					   .getResultList();
	}
	
	@Convert(converter=AccessKeyIdConverter.class)
	private AccessKeyId uuid;
	@Convert(converter=AccessKeyNameConverter.class)
	private AccessKeyName name;
	private String description;
	
	@ElementCollection
	@CollectionTable(schema="auth", 
					 name="accesskey_scope", 
					 joinColumns=@JoinColumn(name="accesskey_id", referencedColumnName="id"))
	@Column(name="scope")
	private Set<String> scopes;

	protected AccessKey() {
		// JPA constructor
	}
	
	public AccessKey(AccessKeyId accessKeyId,
					 AccessKeyName accessKeyName) {
		this.uuid = accessKeyId;
		this.name = accessKeyName;
		this.scopes = new TreeSet<>();
	}
	

	public AccessKeyId getAccessKeyId() {
		return uuid;
	}
	
	public Set<String> getScopes() {
		return unmodifiableSet(scopes);
	}
	
	public void setScopes(Set<String> scopes) {
		this.scopes = new TreeSet<>(scopes);
	}

	public AccessKeyName getAccessKeyName() {
		return name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}


	
}
