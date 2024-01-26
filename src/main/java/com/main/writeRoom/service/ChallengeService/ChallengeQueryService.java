package com.main.writeRoom.service.ChallengeService;

import com.main.writeRoom.domain.Challenge.ChallengeRoutine;
import com.main.writeRoom.web.dto.challenge.ChallengeResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ChallengeQueryService {

    public ChallengeRoutine findRoutine(Long challengeId);
    public List<ChallengeResponseDTO.NoteDTO> findNoteDate(Long userId, Long challengeId);
}