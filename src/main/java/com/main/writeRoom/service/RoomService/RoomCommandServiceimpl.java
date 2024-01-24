package com.main.writeRoom.service.RoomService;

import static com.main.writeRoom.domain.mapping.Authority.MANAGER;

import com.main.writeRoom.apiPayload.status.ErrorStatus;
import com.main.writeRoom.aws.s3.AmazonS3Manager;
import com.main.writeRoom.aws.s3.Uuid;
import com.main.writeRoom.converter.RoomConverter;
import com.main.writeRoom.domain.Room;
import com.main.writeRoom.domain.User.User;
import com.main.writeRoom.domain.mapping.RoomParticipation;
import com.main.writeRoom.handler.RoomHandler;
import com.main.writeRoom.handler.UserHandler;
import com.main.writeRoom.repository.RoomParticipationRepository;
import com.main.writeRoom.repository.RoomRepository;
import com.main.writeRoom.repository.UserRepository;
import com.main.writeRoom.repository.UuidRepository;
import com.main.writeRoom.web.dto.room.RoomRequestDTO;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomCommandServiceimpl implements RoomCommandService {
    private final RoomParticipationRepository userRoomRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;

    @Override
    public Page<RoomParticipation> getMyRoomResultList(Long userId, Integer page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("room.updatedAt")));
        return userRoomRepository.findAllByUser(user, pageRequest);
    }

    @Override
    public Page<RoomParticipation> getUserRoomInfoList(Room room) {
        Page<RoomParticipation> userRoomInfoList = userRoomRepository.findAllByRoom(room, PageRequest.of(0, 10));
        return userRoomInfoList;
    }

    @Override
    public RoomParticipation getUserRoomInfo(Room room, User user) {
        RoomParticipation userRoomInfo = userRoomRepository.findByRoomAndUser(room, user);
        return userRoomInfo;
    }

    @Override
    @Transactional
    public Room createRoom(User user, RoomRequestDTO.CreateRoomDTO request, MultipartFile roomImg) {
        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String imgUrl = null;
        if (roomImg != null) {
            imgUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), roomImg);
        }
        Room room = RoomConverter.toRoom(request, imgUrl);

        RoomParticipation roomParticipation = RoomConverter.toUserRoom(room, user);
        userRoomRepository.save(roomParticipation);

        return roomRepository.save(room);
    }

    @Transactional
    public void deleteRoom(Room room, User user) {
        RoomParticipation roomParticipation = userRoomRepository.findByRoomAndUser(room, user);
        if (roomParticipation.getAuthority() != MANAGER) {
            throw new RoomHandler(ErrorStatus.AUTHORITY_NOT_FOUND);
        }
        roomRepository.delete(room);
    }
}
