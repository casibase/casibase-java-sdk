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

public class Prompt {

    public String title;
    public String text;
    public String image;

    public Prompt(){}

    public Prompt(String title, String text, String image){
        this.title = title;
        this.text = text;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Prompt{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
