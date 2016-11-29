package com.letgo.letgotv.business.model;

import com.letgo.letgotv.backend.model.ResultEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos.buricchi on 08/11/2016.
 */
public class ResultListModel {

    private List<ResultModel> list;

    /**
     * Contructor for the Model Object
     *
     * @param entityList: List of entities
     */
    public ResultListModel(List<ResultEntity> entityList) {

        list = new ArrayList<>();

        if (entityList != null) {

            for (ResultEntity entity: entityList) {

                ResultModel model = new ResultModel(entity);

                list.add(model);
            }
        }
    }

    public List<ResultModel> getList() {

        return list;
    }

}