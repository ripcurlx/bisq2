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

package bisq.network.p2p.node.transport;

import bisq.network.p2p.node.Address;
import com.runjva.sourceforge.jsocks.protocol.Socks5Proxy;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface Transport {
    enum Type implements Serializable {
        TOR,
        I2P,
        CLEAR;

        public static Type from(Address address) {
            if (address.isClearNetAddress()) {
                return Type.CLEAR;
            } else if (address.isTorAddress()) {
                return Type.TOR;
            } else if (address.isI2pAddress()) {
                return Type.I2P;
            } else {
                throw new IllegalArgumentException("Could not resolve transportType from address " + address);
            }
        }
    }

    record Config(String baseDir) {
    }

    record ServerSocketResult(String nodeId, ServerSocket serverSocket, Address address) {
    }

    Boolean initialize();

    ServerSocketResult getServerSocket(int port, String nodeId);

    Socket getSocket(Address address) throws IOException;

    default Optional<Socks5Proxy> getSocksProxy() throws IOException {
        return Optional.empty();
    }

    Optional<Address> getServerAddress(String serverId);

    CompletableFuture<Void> shutdown();
}
