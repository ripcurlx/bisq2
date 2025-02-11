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

package bisq.network.p2p.node;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.Socket;
import java.util.function.BiConsumer;

@Slf4j
public class OutboundConnection extends Connection {

    @Getter
    private final Address address;

    OutboundConnection(Socket socket,
                       Address address,
                       Capability peersCapability,
                       Load peersLoad,
                       Metrics metrics,
                       Handler handler,
                       BiConsumer<Connection, Exception> errorHandler) {
        super(socket, peersCapability, peersLoad, metrics, handler, errorHandler);

        this.address = address;
        log.debug("Create outboundConnection to {}", address);
    }

    @Override
    public boolean isPeerAddressVerified() {
        return true;
    }
}
