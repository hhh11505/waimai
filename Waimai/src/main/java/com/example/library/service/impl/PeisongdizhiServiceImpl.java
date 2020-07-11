package com.example.library.service.impl;

import com.example.library.dao.PeisongdizhiDao;
import com.example.library.dao.YonghuxinxiDao;
import com.example.library.model.Peisongdizhi;
import com.example.library.model.Yonghuxinxi;
import com.example.library.service.PeisongdizhiService;
import com.example.library.service.YonghuxinxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PeisongdizhiServiceImpl implements PeisongdizhiService {
    @Autowired
    private PeisongdizhiDao peisongdizhiDao;
    @Override
    public List<Peisongdizhi> findAllPeisongdizhi(){
        return peisongdizhiDao.findAllPeisongdizhi();
    }
}
