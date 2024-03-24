package com.ssg.meowwms.service.stock;

import com.ssg.meowwms.domain.stock.StockTakingVO;
import com.ssg.meowwms.dto.stock.StockTakingDTO;
import com.ssg.meowwms.mapper.stock.StockTakingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class StockTakingServiceImpl implements StockTakingService {
    private final ModelMapper modelMapper;
    private final StockTakingMapper stockTakingMapper;


    @Override
    public void insertStocktaking(StockTakingDTO stockTakingDTO) {
        log.info("/StockTaking Service insertStocktaking...");
        try {
            StockTakingVO stockTakingVO = modelMapper.map(stockTakingDTO, StockTakingVO.class);
            stockTakingMapper.insertStocktaking(stockTakingVO);
            log.info("Insertion made");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStocktaking(StockTakingDTO stockTakingDTO) {
        log.info("/StockTaking Service updateStocktaking...");
        try {
            StockTakingVO stockTakingVO = modelMapper.map(stockTakingDTO, StockTakingVO.class);
            stockTakingMapper.updateStocktaking(stockTakingVO);
            log.info("Update made");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStocktaking(int stockTakingId) {
        log.info("/StockTaking Service deleteStocktaking...");
        try {
            if (stockTakingMapper.selectOneStocktaking(stockTakingId) != null ) {
                stockTakingMapper.deleteStocktaking(stockTakingId);
                log.info("Deletion Done");
            } else {
                log.error("Deletion Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StockTakingDTO> selectAllStocktaking() {
        log.info("/StockTaking Service selectAllStocktaking...");
        try {
            List<StockTakingDTO> stockTakingDTOList = stockTakingMapper.selectAllStocktaking().stream().map(
                    stockTakingVO -> modelMapper.map(stockTakingVO, StockTakingDTO.class)
            ).collect(Collectors.toList());

            log.info(stockTakingDTOList);

            return stockTakingDTOList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StockTakingDTO selectOneStocktaking(int stockTakingId) {
        log.info("/StockTaking Service selectOneStocktaking...");
        try {
            log.info(modelMapper.map(stockTakingMapper.selectOneStocktaking(stockTakingId), StockTakingDTO.class));
            return modelMapper.map(stockTakingMapper.selectOneStocktaking(stockTakingId), StockTakingDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
