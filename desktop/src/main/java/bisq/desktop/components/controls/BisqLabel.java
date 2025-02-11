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

package bisq.desktop.components.controls;

import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.skin.LabelSkin;

import static bisq.desktop.common.utils.TooltipUtil.showTooltipIfTruncated;

public class BisqLabel extends Label {

    public BisqLabel() {
        super();
    }

    public BisqLabel(String text) {
        super(text);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new BisqLabelSkin(this);
    }

    public void setFixWidth(int value) {
        setMinWidth(value);
        setMaxWidth(value);
    }  public void setFixHeight(int value) {
        setMinHeight(value);
        setMaxHeight(value);
    }
    
    private static class BisqLabelSkin extends LabelSkin {

        public BisqLabelSkin(Label label) {
            super(label);
        }

        @Override
        protected void layoutChildren(double x, double y, double w, double h) {
            super.layoutChildren(x, y, w, h);
            showTooltipIfTruncated(this, getSkinnable());
        }
    }
}
