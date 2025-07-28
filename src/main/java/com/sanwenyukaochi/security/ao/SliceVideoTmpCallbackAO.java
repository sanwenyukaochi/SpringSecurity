package com.sanwenyukaochi.security.ao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sanwenyukaochi.security.dto.ClipGroupDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SliceVideoTmpCallbackAO {
    private String taskId;
    private String videoId;
    private String videoPath;
    private String clipType;
    private String videoType;
    // private List<Clip> clips;
    private List<ClipGroupDTO> clipSections;
    private boolean addSubtitle;
}