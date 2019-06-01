package com.binzikeji.my.shop.web.api.web.controller.v1;

import com.binzikeji.my.shop.commons.dto.BaseResult;
import com.binzikeji.my.shop.domain.TbContent;
import com.binzikeji.my.shop.domain.TbContentCategory;
import com.binzikeji.my.shop.web.api.dao.TbContentDao;
import com.binzikeji.my.shop.web.api.service.TbContentService;
import com.binzikeji.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Bin
 * @Date 2019/3/14 16:47
 **/

@RestController
@RequestMapping(value = "${web.api.v1}/contents")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbcontent(Long id){
        TbContent tbContent = null;

        if (id == null){
            tbContent = new TbContent();
        }

        return tbContent;
    }

    @RequestMapping(value = "/ppt/{categroy_id}", method = RequestMethod.GET)
    public BaseResult findContentByCategroyId(@PathVariable("categroy_id") Long categroyId){
        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(categroyId);
        if (tbContents != null && tbContents.size() > 0){
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDTO dto = new TbContentDTO();
                BeanUtils.copyProperties(tbContent, dto);
                tbContentDTOS.add(dto);
            }
        }
        return BaseResult.success("成功", tbContentDTOS);
    }
}
