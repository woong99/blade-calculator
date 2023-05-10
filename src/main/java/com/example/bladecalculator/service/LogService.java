package com.example.bladecalculator.service;

import com.example.bladecalculator.domain.LoginLogVO;
import com.example.bladecalculator.domain.PaginationVO;
import com.example.bladecalculator.entity.LoginLog;
import com.example.bladecalculator.repository.LoginLogRepository;
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

    public PaginationVO getLoginLogs(int page) {
        Page<LoginLog> logs = loginLogRepository.findAll(PageRequest.of(page, 10, Sort.by("tryAt").descending()));

        return new PaginationVO(page, logs, logs.getContent().stream().map(LoginLogVO::toLoginLogVO).toList());
    }
}
