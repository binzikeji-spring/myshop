package com.binzikeji.my.shop.web.ui.api;

import com.binzikeji.my.shop.commons.utils.HttpClientUtils;
import com.binzikeji.my.shop.commons.utils.MapperUtils;
import com.binzikeji.my.shop.web.ui.dto.TbContent;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import java.util.List;

/**
 * 内容管理接口
 * @author: Chundekepa
 * @create: 2019-03-14 23:56
 **/
public class ContentsApi {

    public static List<TbContent> ppt(){
        List<TbContent> tbContents = null;
        String result = HttpClientUtils.doGet(API.API_CONTENTS_PPT + "/116");
        try {
            tbContents = MapperUtils.json2listByTree(result, "data", TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }
}
