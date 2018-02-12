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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;

import com.plusapp.realmrxjava2example.animation.AnimationActivity;
import com.plusapp.realmrxjava2example.gotchas.GotchasActivity;
import com.plusapp.realmrxjava2example.model.Person;
import com.plusapp.realmrxjava2example.retrofit.RetrofitExample;
import com.plusapp.realmrxjava2example.throttle.ThrottleSearchActivity;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity {

    private ViewGroup container;
    private final TreeMap<String, Class<? extends Activity>> buttons = new TreeMap<String, Class<? extends Activity>>() {{
        put("Animation", AnimationActivity.class);
        put("Throttle search", ThrottleSearchActivity.class);
        put("Network", RetrofitExample.class);
        put("Working with Realm", GotchasActivity.class);
    }};

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.list);
        setupButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //createTestData();
    }

    // Create test data
    private void createTestData() {
        final Random random = new Random(42);
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        for (Map.Entry<String, String> entry : testPersons.entrySet()) {
            Person person = realm.createObject(Person.class);
            person.setName(entry.getKey());
            person.setGithubUserName(entry.getValue());
            person.setAge(random.nextInt(100));

        }



        realm.commitTransaction();

        //Person is not part of the scema for this Realm
        //안드로이드 스튜디오의 instant run기능을 꺼야 해결된다고 하나 실제로는 안됨
        //File -> Settings -> Build Execution Deployment -> Instant Run

        //Person is not part of the scema for this Realm 오류 해결 안됨
        //File -> Other Settings -> Default Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors
        //enable시킴

        //Person is not part of the scema for this Realm 오류 원인과 해결방법을 모르겠음
//        final Random random = new Random(42);
//        Realm realm = Realm.getDefaultInstance();
//

//
//       realm.executeTransaction(r -> {
//            for (Map.Entry<String, String> entry : testPersons.entrySet()) {
//                Person p = realm.createObject(Person.class);
//                p.setName(entry.getKey());
//                p.setGithubUserName(entry.getValue());
//                p.setAge(random.nextInt(100));
//            }
//        });
//        realm.close();
    }

    private void setupButtons() {
        for (final Map.Entry<String, Class<? extends Activity>> entry : buttons.entrySet()) {
            Button button = new Button(this);
            button.setText(entry.getKey());
            button.setOnClickListener(view -> startActivity(entry.getValue()));
            container.addView(button);
        }
    }

    private void startActivity(Class<? extends Activity> activityClass) {
        startActivity(new Intent(this, activityClass));
    }
}
