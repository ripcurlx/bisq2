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

package bisq.account.accounts;

import bisq.account.settlement.SettlementMethod;
import bisq.common.currency.TradeCurrency;
import bisq.common.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Slf4j
@ToString
@EqualsAndHashCode
public abstract class Account<T extends SettlementMethod> implements Serializable {
    protected final String id;
    protected final long creationDate;
    protected final String accountName;
    protected final AccountPayload payload;
    protected final T settlementMethod;
    protected final List<TradeCurrency> tradeCurrencies;

    public Account(String accountName,
                   T settlementMethod,
                   AccountPayload payload,
                   List<TradeCurrency> tradeCurrencies) {
        this(StringUtils.createUid(), new Date().getTime(), accountName, settlementMethod, payload, tradeCurrencies);
    }

    public Account(String id, long creationDate,
                   String accountName,
                   T settlementMethod,
                   AccountPayload payload,
                   List<TradeCurrency> tradeCurrencies) {
        this.id = id;
        this.creationDate = creationDate;
        this.accountName = accountName;
        this.payload = payload;
        this.settlementMethod = settlementMethod;
        this.tradeCurrencies = tradeCurrencies;
    }

    public Set<String> getTradeCurrencyCodes() {
        return tradeCurrencies.stream().map(TradeCurrency::getCode).collect(Collectors.toSet());
    }
}