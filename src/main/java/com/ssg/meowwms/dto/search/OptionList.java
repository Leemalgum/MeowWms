package com.ssg.meowwms.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OptionList {
    List<OptionDTO> optionList;

    public OptionList(){
        optionList = new ArrayList<>();
    }

    public void add(OptionDTO optionDTO){
        optionList.add(optionDTO);
    }

    public List<OptionDTO> getOptionList() {
        return optionList;
    }
}
