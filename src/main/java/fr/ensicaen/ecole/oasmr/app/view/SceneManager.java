/*
 *  Copyright (c) 2019. CCC-Development-Team
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package fr.ensicaen.ecole.oasmr.app.view;

import fr.ensicaen.ecole.oasmr.app.Main;
import fr.ensicaen.ecole.oasmr.app.view.exception.ExceptionSceneAlrdeadyExists;
import fr.ensicaen.ecole.oasmr.app.view.exception.ExceptionSceneNotFound;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;

public class SceneManager {

    //TODO : Virer ce truc immonde :
    private String path = "/fr/ensicaen/ecole/oasmr/app/";
    private HashSet<View> views = new HashSet<>();
    private View activeView;
    private Stage primaryStage;
    private static SceneManager ourInstance = new SceneManager();

    private SceneManager() {
        primaryStage = new Stage();
        primaryStage.setTitle("OASMR");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("img/OASMR.png")));
    }

    public static SceneManager getInstance() {
        return ourInstance;
    }

    public void addScene(View view) throws IOException, ExceptionSceneAlrdeadyExists {
        if (views.add(view)) {
            view.onCreate();
        } else {
            throw new ExceptionSceneAlrdeadyExists();
        }
    }

    public View getView(Class<? extends View> klazz) throws ExceptionSceneNotFound {
        for (View v : views) {
            if (v.getClass().equals(klazz)) {
                return v;
            }
        }
        throw new ExceptionSceneNotFound();

    }

    public Scene getScene(Class<? extends View> klazz) throws ExceptionSceneNotFound {
        for (View v : views) {
            if (v.getClass().equals(klazz)) {
                return v.getScene();
            }
        }
        throw new ExceptionSceneNotFound();

    }

    public void setScenes(Class<? extends View> klazz) throws ExceptionSceneNotFound {
        if (activeView != null)
            activeView.onStop();
        View v = getView(klazz);
        v.onStart();
        activeView = v;
        primaryStage.setScene(v.getScene());
    }


    public void show() {
        primaryStage.show();
    }

}
