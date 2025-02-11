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

package bisq.desktop.primary.main.content.wallet.receive;

import bisq.desktop.common.view.View;
import bisq.i18n.Res;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class WalletReceiveView extends View<VBox, WalletReceiveModel, WalletReceiveController> {
    public WalletReceiveView(WalletReceiveModel model, WalletReceiveController controller) {
        super(new VBox(), model, controller);

        var generateNewAddressButton = new Button(Res.get("wallet.receive.generateNewAddress"));
        generateNewAddressButton.setOnAction(event -> controller.onGenerateNewAddress());

        ListView<String> listView = new ListView<>();
        listView.setCellFactory(param -> new WalletReceiveListCell());
        listView.setItems(model.getListItems());

        root.getChildren().addAll(generateNewAddressButton, listView);
    }
}
