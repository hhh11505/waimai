package com.example.library.service.impl;

import com.example.library.dao.QishouruzhangDao;
import com.example.library.model.Qishouruzhang;
import com.example.library.service.QishouruzhangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QishouruzhangServiceImpl implements QishouruzhangService {
    @Autowired
    private QishouruzhangDao ruzhangruzhangDao;

    @Override
    public List<Qishouruzhang> findAllQishouruzhang() {
        return ruzhangruzhangDao.findAllQishouruzhang();
    }
}
