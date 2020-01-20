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
import {Resource} from '/ui/js/client.js';

//TODO JSDoc

export class Records extends Resource{
	constructor(cfg) {
		super();
		this._cfg = cfg;
	}
		
	load(params) {
		return this.json("/api/v1/login/records?user_id={{&user_id}}&from={{&from}}&to={{&to}}&remote_ip={{&remote_ip}}&limit={{&limit}}",
						 this._cfg,
						 params)
				   .GET();
	}	
}

export class Record extends Resource {
	
	constructor(cfg) {
		super();
		this._cfg = cfg;
	}
	
	load(params) {
		return this.json("/api/v1/login/records/{{&local_ip}}/{{&id}}",
				  	     this._cfg,
				  	     params)
				   .GET();
	}

}
