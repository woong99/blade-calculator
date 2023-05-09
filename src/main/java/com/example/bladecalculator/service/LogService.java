package com.example.bladecalculator.service;

import com.example.bladecalculator.domain.LoginLogVO;
import com.example.bladecalculator.entity.LoginLog;
import com.example.bladecalculator.repository.LoginLogRepository;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogService {

    private final LoginLogRepository loginLogRepository;

    public Map<String, Object> getLoginLogs(int page) {
        Page<LoginLog> logs = loginLogRepository.findAll(PageRequest.of(page, 10, Sort.by("tryAt").descending()));

        Map<String, Object> map = new HashMap<>();
        map.put("logs", logs.getContent().stream().map(LoginLogVO::toLoginLogVO).toList());
        map.put("totalPages", logs.getTotalPages());
        map.put("size", logs.getSize());
        map.put("totalElements", logs.getTotalElements());
        return map;
    }
}
