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

package bisq.wallets.bitcoind.responses;

import bisq.wallets.model.Utxo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ListUnspentResponseEntry {
    private String txid;
    private int vout;
    private String address;
    private String label;
    private String scriptPubKey;
    private double amount;
    private int confirmations;
    private String redeemScript;
    private String witnessScript;
    private boolean spendable;
    private boolean solvable;
    private boolean reused;
    private String desc;
    private boolean safe;

    public Utxo toUtxo() {
        return new Utxo.Builder()
                .txId(txid)
                .address(address)
                .amount(amount)
                .confirmations(confirmations)
                .reused(reused)
                .build();
    }
}
