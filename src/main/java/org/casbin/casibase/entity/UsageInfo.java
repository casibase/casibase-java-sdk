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

import java.time.LocalDateTime;

public class UsageInfo {

    public String provider;
    public int tokenCount;
    public LocalDateTime startTime;

    public UsageInfo(){}

    public UsageInfo(String provider, int tokenCount, LocalDateTime startTime){
        this.provider = provider;
        this.tokenCount = tokenCount;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "UsageInfo{" +
                "provider='" + provider + '\'' +
                ", tokenCount=" + tokenCount +
                ", startTime=" + startTime +
                '}';
    }
}
