package com.ssg.meowwms.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionList {
    List<OptionDTO> optionList;

    public void add(OptionDTO optionDTO){
        optionList.add(optionDTO);
    }

    public List<OptionDTO> getOptionList() {
        return optionList;
    }
}
