package com.sanwenyukaochi.security.bo;


import com.sanwenyukaochi.security.dto.ClipGroupDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class VideoSliceCallbackBO {
    private Long taskId;
    private Long videoId;
    private String videoPath;
    private String videoType;
    private List<ClipGroupDTO> clipGroups;
    private boolean addSubtitle;
}
