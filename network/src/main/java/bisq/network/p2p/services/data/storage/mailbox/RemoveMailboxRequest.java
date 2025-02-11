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

package bisq.network.p2p.services.data.storage.mailbox;

import bisq.network.p2p.services.data.storage.MetaData;
import bisq.network.p2p.services.data.storage.auth.AuthenticatedData;
import bisq.network.p2p.services.data.storage.auth.RemoveAuthenticatedDataRequest;
import bisq.security.DigestUtil;
import bisq.security.SignatureUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Arrays;

@Slf4j
@EqualsAndHashCode(callSuper = true)
@Getter
public class RemoveMailboxRequest extends RemoveAuthenticatedDataRequest implements MailboxRequest {

    public static RemoveMailboxRequest from(MailboxPayload mailboxPayload, KeyPair receiverKeyPair)
            throws GeneralSecurityException {
        byte[] hash = DigestUtil.hash(mailboxPayload.serialize());
        byte[] signature = SignatureUtil.sign(hash, receiverKeyPair.getPrivate());
        return new RemoveMailboxRequest(mailboxPayload.getMetaData(), hash, receiverKeyPair.getPublic(),  signature);
    }

    // Receiver is owner for remove request
    public RemoveMailboxRequest(MetaData metaData,
                                byte[] hash,
                                PublicKey receiverPublicKey,
                                byte[] signature) {
        super(metaData,
                hash,
                receiverPublicKey.getEncoded(),
                receiverPublicKey,
                Integer.MAX_VALUE,  // Use max value for sequence number so that no other addData call is permitted.
                signature);
    }

    @Override
    public boolean isPublicKeyHashInvalid(AuthenticatedData entryFromMap) {
        try {
            MailboxData mailboxData = (MailboxData) entryFromMap;
            return !Arrays.equals(mailboxData.getHashOfReceiversPublicKey(), DigestUtil.hash(ownerPublicKeyBytes));
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public String toString() {
        return "RemoveMailboxDataRequest{} " + super.toString();
    }
}
