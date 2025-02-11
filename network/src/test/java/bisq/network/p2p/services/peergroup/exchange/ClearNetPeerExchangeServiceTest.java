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

package bisq.network.p2p.services.peergroup.exchange;

import bisq.network.p2p.node.transport.Transport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

@Slf4j
public class ClearNetPeerExchangeServiceTest extends BasePeerExchangeServiceTest {

    @Test
    void test_peerExchange() throws InterruptedException, ExecutionException {
        super.test_peerExchange(getConfig(Transport.Type.CLEAR));
    }

    @Override
    protected long getTimeout() {
        return 10;
    }
}