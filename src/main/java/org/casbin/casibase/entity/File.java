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

public class File {

    public String key;
    public String title;
    public long size;
    public String createdTime;
    public boolean isLeaf;
    public String url;
    public List<File> children;
    public Map<String, File> childrenMap;

    public File(){}

    public File(String key, String title, long size, String createdTime, boolean isLeaf, String url,
    List<File> children, Map<String, File> childrenMap){
        this.key = key;
        this.title = title;
        this.size = size;
        this.createdTime = createdTime;
        this.isLeaf = isLeaf;
        this.url = url;
        this.children = children;
        this.childrenMap = childrenMap;
    }

    @Override
    public String toString() {
        return "File{" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                ", createdTime='" + createdTime + '\'' +
                ", isLeaf=" + isLeaf +
                ", url='" + url + '\'' +
                ", children=" + children +
                ", childrenMap=" + childrenMap +
                '}';
    }
}
