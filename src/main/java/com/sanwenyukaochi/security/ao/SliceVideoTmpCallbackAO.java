package com.sanwenyukaochi.security.ao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sanwenyukaochi.security.entity.Clip;
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
    private List<ClipSection> clipSections;
    private boolean addSubtitle;

    @Data
    public static class ClipSection {
        private String start;
        private String end;
        private String summary;
        private List<CoreInfo> coreInfo;
    }

    @Data
    public static class CoreInfo {
        private String start;
        private String end;
        private String topic;
        private String type;
        private List<Clip.SubtitleDTO> sentenceInfo;
    }
}