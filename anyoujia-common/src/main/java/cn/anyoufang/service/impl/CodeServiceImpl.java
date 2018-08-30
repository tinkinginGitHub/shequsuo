package cn.anyoufang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.anyoufang.entity.TbCode;
import cn.anyoufang.entity.TbCodeExample;
import cn.anyoufang.mapper.TbCodeMapper;
import cn.anyoufang.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    TbCodeMapper codeMapper;

    /** 
     * 根据代码值获取代码名称
     */
    @Override
    @Cacheable(value = "codeService")
    public String getCodeName(
            String code) {
        return codeMapper.getCodeName(code);
    }

    /** 
     * 获取指定代码类型下所有的代码项
     */
    @Override
    @Cacheable(value = "codeService")
    public List<Map<String, Object>> getSubCode(
            String pcode) {
        return codeMapper.getSubCode(pcode);
    }

    @Override
    @Cacheable(value = "areaService")
    public List<Map<String, Object>> getAreas() {
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        List<String> areas = codeMapper.getAreas();
        for (String area : areas) {
            Map<String, Object> areaStreet = new HashMap<String, Object>();
            List<String> streets = codeMapper.getStreets(area);
            areaStreet.put("name", area);
            areaStreet.put("streets", streets);
            res.add(areaStreet);
        }
        return res;
    }

    @Override
    @Cacheable(value = "subwayService")
    public List<Map<String, Object>> getSubway() {
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        List<String> subways = codeMapper.getSubway();
        for (String subway : subways) {
            Map<String, Object> subwayStation = new HashMap<String, Object>();
            List<String> stations = codeMapper.getStations(subway);
            subwayStation.put("name", subway);
            subwayStation.put("station", stations);
            res.add(subwayStation);
        }
        return res;
    }

    @Override
    @Cacheable(value = "codeService")
    public List<TbCode> getCodes(String subcode) {
        TbCodeExample example = new TbCodeExample();
       TbCodeExample.Criteria criteria =  example.createCriteria();
       criteria.andCodeEqualTo(subcode);
       List<TbCode> list = codeMapper.selectByExample(example);
        return list;
    }

    @Override
    @Cacheable(value = "codeService")
    public List<Map<String, Object>> getCodes(String[] codes) {
        return codeMapper.getCodes(codes);
    }
}