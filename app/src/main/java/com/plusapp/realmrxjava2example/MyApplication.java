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

package com.plusapp.realmrxjava2example;

import android.app.Application;

import com.plusapp.realmrxjava2example.model.Person;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApplication extends Application {

    private static final TreeMap<String, String> testPersons = new TreeMap<>();
    static {
        testPersons.put("Chris", null);
        testPersons.put("Christian", "cmelchior");
        testPersons.put("Christoffer", null);
        testPersons.put("Emanuele", "emanuelez");
        testPersons.put("Dan", null);
        testPersons.put("Donn", "donnfelker");
        testPersons.put("Nabil", "nhachicha");
        testPersons.put("Ron", null);
        testPersons.put("Leonardo", "dalinaum");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(configuration);


        //원래 코드
//        Realm.init(this);
//        RealmConfiguration config = new RealmConfiguration.Builder().build();
//        Realm.deleteRealm(config);
//        Realm.setDefaultConfiguration(config);

        createTestData();
    }

    // Create test data
    private void createTestData() {
        final Random random = new Random(42);
        Realm realm = Realm.getDefaultInstance();

        //Person is not part of the scema for this Realm
        //안드로이드 스튜디오의 instant run기능을 꺼야 해결된다고 하나 실제로는 안됨
        //File -> Settings -> Build Execution Deployment -> Instant Run

        //Person is not part of the scema for this Realm 오류 해결!
        //File -> Other Settings -> Default Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors
        //enable시킴

        realm.executeTransaction(r -> {
            for (Map.Entry<String, String> entry : testPersons.entrySet()) {
                Person p = r.createObject(Person.class);
                p.setName(entry.getKey());
                p.setGithubUserName(entry.getValue());
                p.setAge(random.nextInt(100));
            }
        });
        realm.close();
    }
}
