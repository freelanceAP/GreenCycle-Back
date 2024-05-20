package com.greenCycle.GreenCycle.infraestructure.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greenCycle.GreenCycle.api.dto.request.RequestReq;
import com.greenCycle.GreenCycle.api.dto.request.StatusCountReq;
import com.greenCycle.GreenCycle.api.dto.request.SummaryReq;
import com.greenCycle.GreenCycle.api.dto.response.BasicUserResp;
import com.greenCycle.GreenCycle.api.dto.response.RequestResp;
import com.greenCycle.GreenCycle.domain.entities.RequestEntity;
import com.greenCycle.GreenCycle.domain.entities.UserEntity;
import com.greenCycle.GreenCycle.domain.repositories.RequestRepository;
import com.greenCycle.GreenCycle.domain.repositories.UserRepository;
import com.greenCycle.GreenCycle.infraestructure.abstract_services.IRequestService;
import com.greenCycle.GreenCycle.util.enums.SortType;
import com.greenCycle.GreenCycle.util.enums.exception.BadRequestException;
import com.greenCycle.GreenCycle.util.enums.messages.ErrorMessages;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RequestService implements IRequestService {

    @Autowired
    private final RequestRepository requestRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public RequestResp create(RequestReq request) {

        RequestEntity entity = this.requestToEntity(request);
        // entity.setCertificate(new CertificateEntity());
        return this.entityToResponse(this.requestRepository.save(entity));
    }

    @Override
    public RequestResp get(Long id) {

        return this.entityToResponse(this.find(id));
    }

    @Override
    public RequestResp update(RequestReq request, Long id) {

        RequestEntity user = this.find(id);

        RequestEntity userUpdate = this.requestToEntity(request);
        userUpdate.setId(user.getId());
        // userUpdate.setCertificate(user.getCertificate());

        return this.entityToResponse(this.requestRepository.save(userUpdate));
    }

    @Override
    public void delete(Long id) {

        RequestEntity user = this.find(id);
        this.requestRepository.delete(user);
    }

    @Override
    public Page<RequestResp> getAll(int page, int size, SortType sortType) {
        if (page < 0)
            page = 0;

        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.requestRepository.findAll(pagination).map(this::entityToResponse);
    }

    private RequestResp entityToResponse(RequestEntity entity) {

        BasicUserResp basicUser = this.EntityBasicToUserResp(entity.getUser());
        RequestResp requestResp = new RequestResp();

        BeanUtils.copyProperties(entity, requestResp);
        requestResp.setUser(basicUser);

        return requestResp;
    }

    private BasicUserResp EntityBasicToUserResp(UserEntity entity) {

        return BasicUserResp.builder()
                .id(entity.getId())
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .nit(entity.getNit())
                .build();
    }

    private RequestEntity requestToEntity(RequestReq request) {

        return RequestEntity.builder()
                .quantityUnit(request.getQuantityUnit())
                .typeWaste(request.getTypeWaste())
                .description(request.getDescription())
                .dateTime(request.getDateTime())
                .status(request.getStatus())
                .user(this.userRepository.findById(request.getUserId()).orElseThrow())
                .build();
    }

    private RequestEntity find(Long id) {
        return this.requestRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Request")));
    }

    @Override
    public List<SummaryReq> getRequestsForLastFiveMonths() {
        LocalDateTime startDate = LocalDateTime.now().minusMonths(4).with(TemporalAdjusters.firstDayOfMonth())
                .with(LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.now().with(LocalTime.MAX);
        
        return mapToObjectList(requestRepository.findRequestsByDateRange(startDate, endDate));
    }

    @Override
    public List<SummaryReq> getRequestsForLastFiveMonthsById(Long id) {
        LocalDateTime startDate = LocalDateTime.now().minusMonths(4).with(TemporalAdjusters.firstDayOfMonth())
                .with(LocalTime.MIN);
        LocalDateTime endDate = LocalDateTime.now().with(LocalTime.MAX);

        return mapToObjectList(requestRepository.findRequestsByDateRangeById(id, startDate, endDate));
    }

    private List<SummaryReq> mapToObjectList(List<Object[]> results) {
        return results.stream()
                .map(this::mapToObject)
                .collect(Collectors.toList());
    }

    private SummaryReq mapToObject(Object[] result) {
        return SummaryReq.builder()
                .month((String) result[0])
                .totalRequests(((Number) result[1]).longValue())
                .build();
    }

    public StatusCountReq getTotalRequestsByStatus() {
        StatusCountReq results = requestRepository.countRequestsByStatus();
        return StatusCountReq.builder()
        .totalRequest(((Number)results[0]).longValue())
        .totalPending(((Number) results[1]).longValue())
        .totalAccepted(((Number) results[2]).longValue())
        .totalInCollection(((Number) results[3]).longValue())
        .totalInDisposition(((Number) results[4]).longValue())
        .totalFinished(((Number) results[5]).longValue())
        .build();

    }
}
