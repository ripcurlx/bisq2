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

package bisq.account.protocol;

// Versioning is handled by adding new entries. That way we could support multiple versions of the same protocol 
// if needed.
public enum SwapProtocolType implements ProtocolType {
    BTC_XMR_SWAP,
    LIQUID_SWAP,                // Atomic same chain (Liquid)
    BSQ_SWAP,                   // Atomic same chain (Bitcoin)
    LN_SWAP,
    MULTISIG,
    BSQ_BOND,
    REPUTATION
}
