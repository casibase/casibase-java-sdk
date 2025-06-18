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

import java.util.List;
import java.util.Map;

public class Store {

    public String owner;
    public String name;
    public String createdTime;
    public String displayName;

    public String storageProvider;
    public String imageProvider;
    public String splitProvider;
    public String modelProvider;
    public String embeddingProvider;

    public List<String> modelProviders;
    public List<String> embeddingProviders;
    public Map<String, UsageInfo> modelUsageMap;
    public Map<String, UsageInfo> embeddingUsageMap;

    public int memoryLimit;
    public int frequency;
    public int limitMinutes;
    public int suggestionCount;
    public String welcome;
    public String prompt;
    public List<Prompt> prompts;
    public String themeColor;
    public String avatar;
    public String title;
    public boolean canSelectStore;

    public File fileTree;
    public Map<String, Properties> propertiesMap;

    public Store() {}

    public Store(String owner, String name, String createdTime, String displayName, String storageProvider,
                 String imageProvider, String splitProvider, String modelProvider, String embeddingProvider,
                 List<String> modelProviders, List<String> embeddingProviders, Map<String, UsageInfo> modelUsageMap,
                 Map<String, UsageInfo> embeddingUsageMap, int memoryLimit, int frequency, int limitMinutes,
                 int suggestionCount, String welcome, String prompt, List<Prompt> prompts, String themeColor,
                 String avatar, String title, boolean canSelectStore, File fileTree,
                 Map<String, Properties> propertiesMap) {
        this.owner = owner;
        this.name = name;
        this.createdTime = createdTime;
        this.displayName = displayName;
        this.storageProvider = storageProvider;
        this.imageProvider = imageProvider;
        this.splitProvider = splitProvider;
        this.modelProvider = modelProvider;
        this.embeddingProvider = embeddingProvider;
        this.modelProviders = modelProviders;
        this.embeddingProviders = embeddingProviders;
        this.modelUsageMap = modelUsageMap;
        this.embeddingUsageMap = embeddingUsageMap;
        this.memoryLimit = memoryLimit;
        this.frequency = frequency;
        this.limitMinutes = limitMinutes;
        this.suggestionCount = suggestionCount;
        this.welcome = welcome;
        this.prompt = prompt;
        this.prompts = prompts;
        this.themeColor = themeColor;
        this.avatar = avatar;
        this.title = title;
        this.canSelectStore = canSelectStore;
        this.fileTree = fileTree;
        this.propertiesMap = propertiesMap;
    }

    @Override
    public String toString() {
        return "Store{" +
                "owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", displayName='" + displayName + '\'' +
                ", storageProvider='" + storageProvider + '\'' +
                ", imageProvider='" + imageProvider + '\'' +
                ", splitProvider='" + splitProvider + '\'' +
                ", modelProvider='" + modelProvider + '\'' +
                ", embeddingProvider='" + embeddingProvider + '\'' +
                ", modelProviders=" + modelProviders +
                ", embeddingProviders=" + embeddingProviders +
                ", modelUsageMap=" + modelUsageMap +
                ", embeddingUsageMap=" + embeddingUsageMap +
                ", memoryLimit=" + memoryLimit +
                ", frequency=" + frequency +
                ", limitMinutes=" + limitMinutes +
                ", suggestionCount=" + suggestionCount +
                ", welcome='" + welcome + '\'' +
                ", prompt='" + prompt + '\'' +
                ", prompts=" + prompts +
                ", themeColor='" + themeColor + '\'' +
                ", avatar='" + avatar + '\'' +
                ", title='" + title + '\'' +
                ", canSelectStore=" + canSelectStore +
                ", fileTree=" + fileTree +
                ", propertiesMap=" + propertiesMap +
                '}';
    }
}
