package com.main.writeRoom.web.dto.challenge;

import com.main.writeRoom.domain.Note;
import com.main.writeRoom.domain.Room;
import com.main.writeRoom.domain.User.User;
import com.main.writeRoom.domain.mapping.ChallengeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ChallengeResponseDTO {

    //1. 챌린지 루틴 생성
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateChallengeRoutineResultDTO {
        Long challengeRoutineId;
        LocalDateTime createdAt;
    }

    //2. 챌린지 루틴 조회
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeRoutineDTO {

        String userName;     //회원명
        List<UserDTO> userList; //챌린지 참여자 목록
        LocalDate startDate; //시작 날짜
        LocalDate deadline;  //마감 날짜
        Integer targetCount; //목표 일수
        List<NoteDTO> noteList; //챌린지 기간동안 작성한 노트들의 인덱스와 날짜 목록
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDTO {
        Long userId;
        String userName;
        String profileImage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoteDTO {
        Long noteId;
        LocalDate date;
    }

    //3. 챌린지 루틴 조회 - 날짜, 200자 이상에 해당하는 노트 목록
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NoteListByDateDTO{
        Room room;  //룸의 노트 중 200자 이상인 노트를 조회
    }

    //5. 챌린지 루틴 포기
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GiveUpChallengeRoutineResultDTO {
        Long userId;             //포기한 회원
        Long challengeRoutineId; //포기한 챌린지 루틴
        ChallengeStatus status;  //포기 상태
        LocalDateTime createdAt; //포기 시간
    }


    //챌린지 목표량
    //챌린지 목표량 생성
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateChallengeGoalsResultDTO {
        Long challengeGoalsId;
        LocalDateTime createdAt;
    }

    //챌린지 목표량 조회
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeGoalsDTO {

        String userName;     //회원명
        Integer achieveCount; //기간 동안의 200자 이상인 노트의 수
        List<UserDTO> userList; //챌린지 참여자 목록
        LocalDate startDate; //시작 날짜
        LocalDate deadline;  //마감 날짜
        Integer targetCount; //목표 일수
    }

    //챌린지 목표량 포기
}
