package com.root.websocket.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.root.dto.*;
import com.root.mapper.SysServiceMapper;
import com.root.mapper.SysServiceParamMapper;
import com.root.sevice.SysBaseServiceService;
import com.root.sevice.SysServiceService;
import com.root.util.DateUtil2;
import com.root.util.ExcelExporterUtil;
import com.root.util.NumberTomatoUtil;
import com.root.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/SysService")
public class SysServiceController {
    @Resource
    SysServiceMapper sysServiceMapper;
    @Resource
    SysServiceParamMapper sysServiceParamMapper;

    @Autowired
    SysServiceService sysServiceService;
    @Autowired
    SysBaseServiceService sysBaseServiceService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnMessage list(@RequestBody SysServiceDto sysServiceDto) {
        Map map = sysServiceService.esecSysService(sysServiceDto);
        //如果数据只有一条，就返回list，多条就返回map
        if (map.size() == 1) {
            return ResponseUtil.success(map.get("T1"));
        } else if (map.size() == 0) {
            return ResponseUtil.success(new ArrayList<>());
        } else {
            return ResponseUtil.success(map);
        }
    }
    @RequestMapping(value = "/exportToExcel", method = RequestMethod.GET)
    public void exportToExcel(HttpServletResponse response, SysServicePageDTO sysServicePageDTO)throws IOException {
        // 示例数据
        List<String> headers = Arrays.asList("ID", "Name", "Email");
        List<List<String>> data = Arrays.asList(
                Arrays.asList("1", "John Doe", "john@example.com"),
                Arrays.asList("2", "Jane Smith", "jane@example.com")
        );

        ExcelExporterUtil.exportToExcel(response,headers, data);

    }
    @RequestMapping(value = "/listPage", method = RequestMethod.POST)
    public ReturnMessage listPage(@RequestBody SysServicePageDTO sysServicePageDTO) {
        Map map =null;
        if (StrUtil.equals(sysServicePageDTO.getServiceType(), "基础服务")) {
            map =sysBaseServiceService.getBaseService(sysServicePageDTO.getSysBaseServiceDTO());
        }else {
            map = sysServiceService.esecSysService(sysServicePageDTO.getSysServiceDTO());
        }
        Map resultMap = new HashMap();
        List list = Optional.ofNullable(((List) map.get("T1"))).orElse(new ArrayList());
        list = (List) list.stream().filter(o -> {
                    //判断字段是否存在
                    if (!BeanUtil.isEmpty(sysServicePageDTO.getSelectText())) {
                        Boolean row = false;
                        for (Object o1 : ((Map) o).values()) {
                            if (!Convert.toStr(o1).matches(".*" + sysServicePageDTO.getSelectText() + ".*")) {
                                continue;
                            } else {
                                row = true;
                                break;
                            }
                        }
                        if (!row) {
                            return false;
                        }

                    }
                    ;
                    if (!BeanUtil.isEmpty(sysServicePageDTO.getFilterName()) && !BeanUtil.isEmpty(sysServicePageDTO.getFilterUp()) && !BeanUtil.isEmpty(sysServicePageDTO.getFilterDown())) {
                        if (DateUtil2.isData(Convert.toStr(((Map) o).get(sysServicePageDTO.getFilterName())))) {
                            DateTime data1 = cn.hutool.core.date.DateUtil.parse(Convert.toStr(((Map) o).get(sysServicePageDTO.getFilterName())));
                            DateTime data2 = cn.hutool.core.date.DateUtil.parse(Convert.toStr(((Map) o).get(sysServicePageDTO.getFilterUp())));
                            DateTime data3 = cn.hutool.core.date.DateUtil.parse(Convert.toStr(((Map) o).get(sysServicePageDTO.getFilterDown())));
                            if (data1.compareTo(data2) >= 0) {
                                return false;
                            }
                            if (data1.compareTo(data3) <= 0) {
                                return false;
                            }


                        } else if (NumberTomatoUtil.isNumeric(Convert.toStr(((Map) o).get(sysServicePageDTO.getFilterName())))) {
                            int i1 = Convert.toInt(((Map) o).get(sysServicePageDTO.getFilterName()));
                            int i2 = Convert.toInt(((Map) o).get(sysServicePageDTO.getFilterUp()));
                            int i3 = Convert.toInt(((Map) o).get(sysServicePageDTO.getFilterDown()));
                            if (i1 >= i2) {
                                return false;
                            }
                            if (i1 <= i3) {
                                return false;
                            }
                        } else {
                            String s1 = Convert.toStr(((Map) o).get(sysServicePageDTO.getFilterName()));
                            String s2 = Convert.toStr(((Map) o).get(sysServicePageDTO.getFilterUp()));
                            String s3 = Convert.toStr(((Map) o).get(sysServicePageDTO.getFilterDown()));
                            if (s1.compareTo(s2) >= 0) {
                                return false;
                            }
                            if (s1.compareTo(s3) <= 0) {
                                return false;
                            }
                        }
                    }
                    return true;

                }
        ).collect(Collectors.toList());

        if (!BeanUtil.isEmpty(sysServicePageDTO.getSortName()) && !BeanUtil.isEmpty(sysServicePageDTO.getSortType())) {

            if (sysServicePageDTO.getSortType()) {
                // 使用匿名比较器排序
                Collections.sort(list, new Comparator<Object>() {
                    @Override
                    public int compare(Object p1, Object p2) {
                        return Convert.toStr(((Map) p1).get(sysServicePageDTO.getSortName())).compareTo(Convert.toStr(((Map) p2).get(sysServicePageDTO.getSortName())));
                    }
                });

            } else {
                // 使用匿名比较器排序
                Collections.sort(list, new Comparator<Object>() {
                    @Override
                    public int compare(Object p1, Object p2) {
                        return Convert.toStr(((Map) p2).get(sysServicePageDTO.getSortName())).compareTo(Convert.toStr(((Map) p1).get(sysServicePageDTO.getSortName())));
                    }
                });
            }
        }

        int total = list.size();
        resultMap.put("total", total);
        if ((sysServicePageDTO.getNewPageNumber()) != 0 && (sysServicePageDTO.getNewPageSize()) != 0) {

            resultMap.put("data", list.subList(((sysServicePageDTO.getNewPageNumber() - 1) * sysServicePageDTO.getNewPageSize()) > list.size() ? list.size() : ((sysServicePageDTO.getNewPageNumber() - 1) * sysServicePageDTO.getNewPageSize()), ((sysServicePageDTO.getNewPageNumber()) * sysServicePageDTO.getNewPageSize()) > list.size() ? list.size() : ((sysServicePageDTO.getNewPageNumber()) * sysServicePageDTO.getNewPageSize())));
        } else {
            resultMap.put("data", list);

        }
        //如果数据只有一条，就返回list，多条就返回map
        return ResponseUtil.success(resultMap);
    }


    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public ReturnMessage set(@RequestBody SysServiceInsertDTO sysServiceInsertDTO) {
        String sysServiceName = sysServiceInsertDTO.getSysServiceName();
        String sysServiceValue = sysServiceInsertDTO.getSysServiceValue();
        String sysServiceSql = sysServiceInsertDTO.getSysServiceSql();
        if (0 != sysServiceMapper.insertSysService(sysServiceName, sysServiceValue, sysServiceSql)) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail();
    }

    @RequestMapping(value = "/setList", method = RequestMethod.POST)
    public ReturnMessage setList(@RequestBody List<SysServiceInsertDTO> sysServiceInsertDTO) {
        return ResponseUtil.fail();
    }

    @RequestMapping(value = "/batchInsertServiceParam", method = RequestMethod.POST)
    public ReturnMessage batchInsertServiceParam(@RequestBody InsertSysServiceParamDTO insertSysServiceParamDTO) {
        System.out.println(insertSysServiceParamDTO.getSysServiceValue());
        return ResponseUtil.mybatiesResult(sysServiceParamMapper.insertSelSysServiceParam(insertSysServiceParamDTO));
    }

    @RequestMapping(value = "/deleteSelSysServiceParam", method = RequestMethod.POST)
    public ReturnMessage deleteSelSysServiceParam(@RequestBody String sysServiceValue) {
        return ResponseUtil.mybatiesResult(sysServiceParamMapper.deleteSelSysServiceParam(sysServiceValue));
    }

}
