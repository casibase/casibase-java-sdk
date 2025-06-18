/*
 * 	Copyright 2025 The Casibase Authors. All Rights Reserved.
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License");
 * 	you may not use this file except in compliance with the License.
 * 	You may obtain a copy of the License at
 *
 * 	     http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS,
 * 	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 	See the License for the specific language governing permissions and
 * 	limitations under the License.
 */

package org.casbin.casibase.entity;

public class Record {

    public int id;
    public String owner;
    public String name;
    public String createdTime;
    public String organization;
    public String clientIp;
    public String user;
    public String method;
    public String requestUri;
    public String action;
    public String language;
    public String object;
    public String response;
    public String provider;
    public String block;
    public String blockHash;
    public String transaction;
    public String userAgent;
    public boolean isTriggered;
    public boolean needCommit;
    
    public Record(){}

    public Record(String owner, String name, String createdTime, String organization, String clientIp,
                  String user, String method, String requestUri, String action, String object, String language, String provider,
                  String response, boolean isTriggered, String block, String blockHash, String transaction, String userAgent, boolean needCommit){
        this.owner = owner;
        this.name = name;
        this.createdTime = createdTime;
        this.organization = organization;
        this.clientIp = clientIp;
        this.user = user;
        this.method = method;
        this.requestUri = requestUri;
        this.action = action;
        this.object = object;
        this.language = language;
        this.provider = provider;
        this.response = response;
        this.isTriggered = isTriggered;
        this.block = block;
        this.blockHash = blockHash;
        this.transaction = transaction;
        this.userAgent = userAgent;
        this.needCommit = needCommit;
    }
}
