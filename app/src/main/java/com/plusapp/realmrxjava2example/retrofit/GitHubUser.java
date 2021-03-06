/*
 * Copyright 2015 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.plusapp.realmrxjava2example.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Model class for GitHub users: https://developer.github.com/v3/users/#get-a-single-user
 */
@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
class GitHubUser {
    public String name;
    public int public_repos;
    public int public_gists;
}
