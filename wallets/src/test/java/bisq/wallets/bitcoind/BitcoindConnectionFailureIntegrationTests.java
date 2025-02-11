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

package bisq.wallets.bitcoind;

import bisq.common.util.NetworkUtils;
import bisq.wallets.bitcoind.rpc.RpcClient;
import bisq.wallets.bitcoind.rpc.RpcConfig;
import bisq.wallets.exceptions.CannotConnectToWalletException;
import bisq.wallets.exceptions.InvalidRpcCredentialsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;

public class BitcoindConnectionFailureIntegrationTests {
    @Test
    void bitcoindNotRunningTest() throws MalformedURLException {
        int freePort = NetworkUtils.findFreeSystemPort();
        RpcConfig rpcConfig = BitcoindRegtestSetup.createRpcConfigForPort(freePort);

        var rpcClient = new RpcClient(rpcConfig);
        var minerChainBackend = new BitcoindChainBackend(rpcClient);

        CannotConnectToWalletException exception = Assertions
                .assertThrows(CannotConnectToWalletException.class, minerChainBackend::listWallets);

        Assertions.assertTrue(exception.getCause() instanceof ConnectException);
    }

    @Test
    void wrongRpcCredentialsTest() throws IOException {
        BitcoindProcess bitcoindProcess = BitcoindRegtestSetup.createAndStartBitcoind();

        RpcConfig wrongRpcConfig = new RpcConfig.Builder(bitcoindProcess.getRpcConfig())
                .password("WRONG_PASSWORD")
                .build();

        var rpcClient = new RpcClient(wrongRpcConfig);
        var minerChainBackend = new BitcoindChainBackend(rpcClient);

        Assertions.assertThrows(InvalidRpcCredentialsException.class, minerChainBackend::listWallets);

        bitcoindProcess.stopAndWaitUntilStopped();
    }
}
