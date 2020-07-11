package com.example.library.service.impl;

import com.example.library.dao.QishouDao;
import com.example.library.dao.UserDao;
import com.example.library.model.Qishou;
import com.example.library.model.User;
import com.example.library.service.QishouService;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QishouServiceImpl implements QishouService {
    @Autowired
    private QishouDao qishouDao;
    @Override
    public List<Qishou> findAllQishou(){
        return qishouDao.findAllQishou();
    }
}
