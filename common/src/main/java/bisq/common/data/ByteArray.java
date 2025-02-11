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

package bisq.common.data;

import bisq.common.encoding.Hex;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;

/**
 * We do not use a record here because a byte array would be compared for identity equality instead of content
 * equality if a record is used. We could override the equals methods but this would violate the semantics of a record.
 * We prefer to keep is as a normal class instead.
 * See: https://stackoverflow.com/questions/61261226/java-14-records-and-arrays
 */
@SuppressWarnings("ClassCanBeRecord")
@Getter
public class ByteArray implements Serializable {
    private final byte[] bytes;

    public ByteArray(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteArray byteArray = (ByteArray) o;
        return Arrays.equals(bytes, byteArray.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public String toString() {
        return Hex.encode(bytes);
    }
}
