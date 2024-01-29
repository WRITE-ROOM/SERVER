package com.main.writeRoom.service.ChallengeService;

import com.main.writeRoom.domain.Challenge.ChallengeRoutine;
import com.main.writeRoom.domain.mapping.ChallengeRoutineParticipation;
import com.main.writeRoom.web.dto.challenge.ChallengeRequestDTO;

import java.time.LocalDate;

public interface ChallengeRoutineCommandService {

    public ChallengeRoutine create(Long roomId, ChallengeRequestDTO.ChallengeRoutineDTO request);

    public void deadlineRange(LocalDate startDate, LocalDate deadline);

    public ChallengeRoutineParticipation giveUP(Long userId, Long challengeRoutineId);
}