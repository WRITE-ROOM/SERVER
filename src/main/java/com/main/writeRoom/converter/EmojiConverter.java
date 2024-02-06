package com.main.writeRoom.converter;

import com.main.writeRoom.domain.Emoji;
import com.main.writeRoom.domain.User.User;
import com.main.writeRoom.domain.mapping.EmojiClick;
import com.main.writeRoom.web.dto.emoji.EmojiResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class EmojiConverter {

    public static Emoji toEmoji(User user, Long emojiNum) {

        return Emoji.builder()
                .user(user)
                .emojiNum(emojiNum)
                .build();
    }

    public static EmojiResponseDTO.EmojiClickResult toEmojiClickResult(EmojiClick emojiClick) {

        return EmojiResponseDTO.EmojiClickResult.builder()
                .emojiClickId(emojiClick.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static EmojiResponseDTO.EmojiUpdateResult toEmojiUpdateResult(Emoji emoji) {
        return EmojiResponseDTO.EmojiUpdateResult.builder()
                .emojiId(emoji.getId())
                .emojiNum(emoji.getEmojiNum())
                .updatedAT(emoji.getUpdatedAt())
                .build();
    }

    public static EmojiResponseDTO.EmojiDeleteResult toEmojiDeleteResult(EmojiClick emojiClick) {

        return EmojiResponseDTO.EmojiDeleteResult.builder()
                .emojiClickId(emojiClick.getId())
                .build();
    }

}