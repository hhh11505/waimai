package com.example.library.service.impl;

import com.example.library.dao.QishouDao;
import com.example.library.dao.YonghuxinxiDao;
import com.example.library.model.Qishou;
import com.example.library.model.Yonghuxinxi;
import com.example.library.service.QishouService;
import com.example.library.service.YonghuxinxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class YonghuxinxiServiceImpl implements YonghuxinxiService {
    @Autowired
    private YonghuxinxiDao yonghuxinxiDao;
    @Override
    public List<Yonghuxinxi> findAllYonghuxinxi(){
        return yonghuxinxiDao.findAllYonghuxinxi();
    }
}
