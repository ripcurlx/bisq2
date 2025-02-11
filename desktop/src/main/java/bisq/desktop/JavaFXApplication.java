/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.desktop;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class JavaFXApplication extends Application {
    public record Data(Stage stage, Parameters parameters, HostServices hostServices) {
    }

    static final CompletableFuture<Data> onApplicationLaunched = new CompletableFuture<>();

    @Override
    public void start(Stage stage) {
        onApplicationLaunched.complete(new Data(stage, getParameters(), getHostServices()));
    }
}
