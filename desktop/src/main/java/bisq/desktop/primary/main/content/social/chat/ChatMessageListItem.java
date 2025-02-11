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

package bisq.desktop.primary.main.content.social.chat;

import bisq.presentation.formatters.TimeFormatter;
import bisq.social.chat.ChatMessage;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Getter
@EqualsAndHashCode
class ChatMessageListItem implements Comparable<ChatMessageListItem> {
    private final ChatMessage chatMessage;
    private final String message;
    private final String senderUserName;
    private final String date;

    public ChatMessageListItem(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
        message = chatMessage.getText();
        senderUserName = chatMessage.getSenderUserName();
       
        date = TimeFormatter.formatTime(new Date(chatMessage.getDate()));
    }

    @Override
    public int compareTo(ChatMessageListItem o) {
        return new Date(chatMessage.getDate()).compareTo(new Date(o.getChatMessage().getDate()));
    }
}