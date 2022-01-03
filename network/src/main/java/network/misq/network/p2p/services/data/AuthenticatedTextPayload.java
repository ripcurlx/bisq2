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

package network.misq.network.p2p.services.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import network.misq.network.p2p.NetworkId;
import network.misq.network.p2p.services.data.storage.MetaData;
import network.misq.network.p2p.services.data.storage.auth.AuthenticatedPayload;

import java.util.concurrent.TimeUnit;

@EqualsAndHashCode
@ToString
@Getter
public class AuthenticatedTextPayload implements AuthenticatedPayload {
    private final String text;
    private final NetworkId networkId;
    final MetaData metaData;

    public AuthenticatedTextPayload(String text, NetworkId networkId) {
        this.text = text;
        this.networkId = networkId;
        // 463 is overhead of sig/pubkeys,...
        // 582 is pubkey+sig+hash
        metaData = new MetaData(TimeUnit.DAYS.toMillis(10), 251 + 463, getClass().getSimpleName());
    }

    @Override
    public MetaData getMetaData() {
        return metaData;
    }

    @Override
    public boolean isDataInvalid() {
        return false;
    }
}
