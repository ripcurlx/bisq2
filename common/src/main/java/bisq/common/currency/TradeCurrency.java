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

package bisq.common.currency;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Getter
public abstract class TradeCurrency implements Comparable<TradeCurrency>, Serializable {
    protected final String code;
    @EqualsAndHashCode.Exclude
    protected final String name;

    public static boolean isFiat(String code) {
        return FiatCurrencyRepository.getCurrencyByCodeMap().containsKey(code);
    }

    public static boolean isBtc(String code) {
        return "BTC".equals(code);
    }

    /**
     * We only can check if the currency is not fiat and if the code matches the format, but we do not maintain a list
     * of crypto-currencies to be flexible with any newly added one.
     */
    public static boolean isMaybeCrypto(String code) {
        return !isFiat(code) && code.length() >= 3;
    }

    public TradeCurrency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getDisplayPrefix() {
        return "";
    }

    public String getNameAndCode() {
        return name + " (" + code + ")";
    }

    public String getCodeAndName() {
        return code + " (" + name + ")";
    }

    @Override
    public int compareTo(TradeCurrency other) {
        return this.name.compareTo(other.name);
    }

    public abstract boolean isFiat();
}
