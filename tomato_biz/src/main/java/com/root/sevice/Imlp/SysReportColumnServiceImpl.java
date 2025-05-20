package com.root.sevice.Imlp;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.root.dto.CellDTO;
import com.root.dto.SheetDTO;
import com.root.entity.SysReportColumnEntity;
import com.root.mapper.SysReportColumnMapper;
import com.root.sevice.SysReportColumnService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class SysReportColumnServiceImpl extends ServiceImpl<SysReportColumnMapper, SysReportColumnEntity> implements SysReportColumnService {
    public Boolean SaveReportColumn(SheetDTO sheetDTO) {
        List<CellDTO> cellDTOS = sheetDTO.getCellDTOS();
        cellDTOS.forEach(cellDTO -> {
            for (CellDTO cellDTO2 : cellDTOS) {
                if (cellDTO2.getR() == cellDTO.getR() - 1 && (
                        cellDTO.getC() >= cellDTO2.getC() &&
                                cellDTO.getC() < cellDTO2.getC() + cellDTO2.getCs())) {
                    cellDTO.setPid(cellDTO2.getId());
                    break;
                }
            }
        });
        List<SysReportColumnEntity> sysReportColumnEntities = new CopyOnWriteArrayList<>();
        cellDTOS.forEach(cellDTO ->
                sysReportColumnEntities.add(cellDTO.cellDTOToSysReportColumnEntity(sheetDTO.getSysReportId()))
        );
        QueryWrapper<SysReportColumnEntity> sysReportColumnEntityQueryWrapper = new QueryWrapper();
        sysReportColumnEntityQueryWrapper.lambda().eq(SysReportColumnEntity::getSysReportId, sheetDTO.getSysReportId());
        this.remove(sysReportColumnEntityQueryWrapper);
        return this.saveBatch(sysReportColumnEntities);
    }

    public SheetDTO OutReportcolumn(String sysReportId) {
        QueryWrapper<SysReportColumnEntity> sysReportColumnEntityQueryWrapper = new QueryWrapper();
        sysReportColumnEntityQueryWrapper.lambda().eq(SysReportColumnEntity::getSysReportId, sysReportId);
        List<SysReportColumnEntity> sysReportColumnEntities = this.list(sysReportColumnEntityQueryWrapper);
        List<CellDTO> cellDTOS = new CopyOnWriteArrayList<>();
        List<CellDTO> cellDTOStorey = new ArrayList<>();
        AtomicInteger index = new AtomicInteger(0);
        do {
            AtomicInteger column = new AtomicInteger(0);

            List<CellDTO> cellDTOUpStoreys = new ArrayList<>(cellDTOStorey);
            cellDTOStorey.clear();
            sysReportColumnEntities.stream()
                    .filter(sysReportColumnEntity ->
                            cellDTOS.size() == 0 ?
                                    ObjectUtil.isEmpty(sysReportColumnEntity.getSysReportColumnPid()) :
                                    cellDTOUpStoreys.parallelStream().anyMatch(cellDTOUpStorey -> StrUtil.equals(cellDTOUpStorey.getId(), sysReportColumnEntity.getSysReportColumnPid())
                                    )).forEach((sysReportColumnEntity) -> {
                column.getAndIncrement();
                cellDTOStorey.add(sysReportColumnEntity.SysReportColumnEntityToCellDTO(index.get(), column.get()));
            });
            cellDTOS.addAll(cellDTOStorey);
            index.getAndIncrement();
        } while (cellDTOStorey.size() > 0);
        index.getAndDecrement();
        List<CellDTO> cellDTOS1 = cellDTOS.stream().filter(cellDTO ->
                cellDTOS.parallelStream().noneMatch(cellDTO1 -> StrUtil.equals(cellDTO.getId(), cellDTO1.getPid()))).collect(Collectors.toList());
        cellDTOS1.stream().forEach(cellDTO -> {
            if (cellDTO.getR() != index.get()) {
                cellDTO.setRs(index.get() - cellDTO.getR());
            }
        });
        cellDTOS.removeAll(cellDTOS1);
        cellDTOS.addAll(cellDTOS1);
        /**判断单元格是否有子项，通过子项数目决定行宽*/
        cellDTOS.stream().forEach(cellDTO -> {
            AtomicInteger childrenNum = new AtomicInteger(0);
            List<CellDTO> cellDTOSChildrens = new ArrayList<>();
            do {
                List<CellDTO> cellDTOUpStoreys = new ArrayList<>(cellDTOSChildrens);
                cellDTOStorey.clear();
                cellDTOSChildrens = cellDTOS1.stream()
                        .filter(cellDTO1 ->
                                childrenNum.get() == 0 ? StrUtil.equals(cellDTO.getId(), cellDTO1.getPid()) :
                                        cellDTOUpStoreys.parallelStream().anyMatch(cellDTOUpStorey -> StrUtil.equals(cellDTOUpStorey.getId(), cellDTO1.getPid())
                                        )).collect(Collectors.toList());
                childrenNum.addAndGet(cellDTOSChildrens.size());
            } while (cellDTOSChildrens.size() > 0);
            cellDTO.setCs(childrenNum.get() <= 1 ? 1 : childrenNum.get());
        });
        cellDTOS.stream().forEach(cellDTO -> {
            AtomicInteger columnNum=new AtomicInteger(0);
            cellDTOS.stream().filter(cellDTO1 -> cellDTO1.getR()==cellDTO.getR()&&cellDTO1.getC()<cellDTO.getC()).forEach(cellDTO1 ->columnNum.addAndGet(cellDTO1.getCs())
            );
            cellDTO.setC(columnNum.get());
        });
        return new SheetDTO().getSheetDTO(cellDTOS, sysReportId);

    }


}
